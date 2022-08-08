/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.entity.PqrCustomerEntity;
import net.woden.wdsit.entity.PqrMessageSendEntity;
import net.woden.wdsit.model.PqrAllDescriptionModel;
import net.woden.wdsit.model.PqrCustomerFileModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrCustomerService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;
    @Autowired
    private GenPersonService genPersonS;
    @Autowired
    private EncryptUtility eu;
    @Autowired
    private PqrMessageSendService PqrMessageSendS;

    public ResponseModel create(PqrCustomerEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerCreate(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, p.getTicket());
            cs.setString(2, p.getName());
            cs.setString(3, p.getLastName());
            cs.setString(4, p.getEmails());
            cs.setString(5, p.getDescription());
            cs.setInt(6, p.getIdentification());
            cs.setInt(7, p.getUserId());
            cs.setString(8, p.getEmailAnnexed());
            cs.registerOutParameter(9, Types.INTEGER);
            inserts = cs.getInt(9);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel createTicket(int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerTicketCreate(?,?)}");

            cs.setInt(1, userId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();

            inserts = cs.getInt(2);

            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel list(int userId) {
        ArrayList<PqrCustomerEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerList(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrCustomerEntity p = new PqrCustomerEntity();
                p.setId(rs.getInt("id"));
                p.setTicketId(rs.getString("ticketId"));
                p.setName(rs.getString("name"));
                p.setLastName(rs.getString("lastName"));
                p.setEmails(rs.getString("emails"));
                p.setDescription(rs.getString("description"));
                p.setIdentification(rs.getInt("identification"));
                p.setCreationDate(rs.getDate("creationDate"));
                p.setActive(rs.getBoolean("active"));
                p.setUserId(rs.getInt("userId"));
                p.setEmailAnnexed(rs.getString("emailAnnexed"));
                lists.add(p);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel update(String ticket, PqrCustomerEntity p, int countryId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerUpdate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, ticket);
            cs.setString(2, p.getName());
            cs.setString(3, p.getEmails());
            cs.setString(4, p.getDescription());
            cs.setString(5, p.getEmailAnnexed());
            cs.setInt(6, countryId);
            cs.setString(7, p.getTicket());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel findById(String id) {
        PqrCustomerEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerFindByIdEnd(?)}");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrCustomerEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setName(rs.getString("name"));
                p.setLastName(rs.getString("lastName"));
                p.setEmails(rs.getString("emails"));
                p.setDescription(rs.getString("description"));
                p.setIdentification(rs.getInt("identification"));
                p.setCreationDate(rs.getDate("creationDate"));
                p.setActive(rs.getBoolean("active"));
                p.setUserId(rs.getInt("userId"));
                p.setEmailAnnexed(rs.getString("emailAnnexed"));
            }
            cs.close();
            rs.close();
            cn.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }

    public ResponseModel findByIdTicket(String id) {
        PqrCustomerEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerFindById(?)}");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrCustomerEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setName(rs.getString("name"));
                p.setLastName(rs.getString("lastName"));
                p.setEmails(rs.getString("emails"));
                p.setDescription(rs.getString("description"));
                p.setIdentification(rs.getInt("identification"));
                p.setCreationDate(rs.getDate("creationDate"));
                p.setActive(rs.getBoolean("active"));
                p.setUserId(rs.getInt("userId"));
            }
            cs.close();
            rs.close();
            cn.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }

    public ResponseModel loadFile(String ticket, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("pqrs.general.url") + "\\" + ticket);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File directory2 = new File(this.en.getProperty("pqrs.general.url") + "\\" + ticket + "\\" + creationDate);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }

        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFile(String ticket, String creationDate) {
        ArrayList<PqrCustomerFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("pqrs.general.url") + "\\" + ticket + "\\" + creationDate);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    PqrCustomerFileModel p = new PqrCustomerFileModel();
                    p.setName(f.getName());
                    p.setFile(bytes);
                    p.setType(f.getName().split("\\.", 2)[1]);
                    list.add(p);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel allDescription(String ticketId, int userId) {
        ArrayList<PqrAllDescriptionModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrAllDescription(?,?)}");
            cs.setString(1, ticketId);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrAllDescriptionModel p = new PqrAllDescriptionModel();
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setName(rs.getString("name"));
                p.setProject(rs.getString("project"));
                p.setCity(rs.getString("city"));
                p.setDescription(rs.getString("description"));
                p.setStatusSystem(rs.getString("statusSystem"));
                p.setType(rs.getString("type"));
                p.setSerial(rs.getString("serial"));
                p.setCreationDate(rs.getString("creationDate"));
                lists.add(p);

            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel listAll(String id, int userId) {
        ArrayList<PqrCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrCustomerFindById(?,?)}");
            cs.setString(1, id);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrCustomerEntity p = new PqrCustomerEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setName(rs.getString("name"));
                p.setLastName(rs.getString("lastName"));
                p.setEmails(rs.getString("emails"));
                p.setDescription(rs.getString("description"));
                p.setIdentification(rs.getInt("identification"));
                p.setCreationDate(rs.getDate("creationDate"));
                p.setActive(rs.getBoolean("active"));
                p.setUserId(rs.getInt("userId"));
                list.add(p);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), null, null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), null, null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel sendEmail(String id, int destinatarioId) {
        if (id == null || id == "0" || id == "") {
            PqrMessageSendEntity politica = (PqrMessageSendEntity) this.PqrMessageSendS.findByIdMail(destinatarioId).getObject();
            GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        }
        PqrMessageSendEntity politica = (PqrMessageSendEntity) this.PqrMessageSendS.findByIdMail(destinatarioId).getObject();
        GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        PqrCustomerEntity destinatarioListaFront = (PqrCustomerEntity) this.findById(id).getObject();
        int status = 0;
        Properties props = new Properties();
        props.put("mail.smtp.host", this.en.getProperty("spring.mail.host"));
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", this.en.getProperty("spring.mail.port"));
        props.put("mail.smtp.mail.sender", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.user", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getDefaultInstance(props);
        try {
            String messageHtml = "<div style=\"margin: 0; padding: 0;\"><center>\n"
                    + "<table style=\"font-family: Roboto, Helvetica, 'Myriad Pro'; background: #ffffff; margin: 0px; padding: 0px; width: 600px; height: 695px;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td valign=\"top\">\n"
                    + "<div style=\"margin: 0 auto; text-align: left; width: 600px;\" align=\"left\">\n"
                    + "<table style=\"border-collapse: collapse; width: 600px; border-style: none; margin-left: auto; margin-right: auto;\" border=\"0\" cellspacing=\"0px\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"text-align: center; height: 100px; width: 100%; background-color: #ea6109;\"><img style=\"height: 60%; margin-top: 20px;\" src=\"http://app.woden.com.co/wdsit/assets/images/Logo.png\" /></div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 30px;\">\n"
                    + "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 540px; background: #ea6109; border-radius: 0px 0px 21px 21px;\">\n"
                    + "<table style=\"width: 545px; height: 350px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #ffffff; padding: 25px 20px; border-radius: 20px; border: 1px solid #efecec;\">\n"
                    + "<table style=\"height: 298px;\" border=\"0\" width=\"485\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 30px;\">\n"
                    + "<td style=\"text-align: center; font-size: 30px; color: #354a5f; height: 30px;\"><strong>Notificaciones WDSIT</strong></td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 20px;\">\n"
                    + "<td style=\"padding: 10px; text-align: justify; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Generación de ticket</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">¡Hola! <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">Hemos recibido tu solicitud con el número de Ticket <strong>{ticket}</strong>. Estaremos dando respuesta dentro del término establecido contractualmente.</p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">Servicio al Cliente</p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">Woden S.A.S </p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">¡Gracias por comunicarte con nosotros!</p>\n"
                    + "<br>\n"
                    + "<i style=\"color: #9e9e9e; margin: 5px; text-align: justify;\"><strong>{politica}</strong></i>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td dir=\"ltr\" style=\"padding-top: 40px; text-align: center; color: #4285f4; font-size: 16px; font-weight: 300; height: 21px; width: 425.219px;\"><span style=\"background-color: #ffffff; color: #95a5a6;\">WODEN WDSIT</span></td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table style=\"width: 500px!important;\" border=\"0\" width=\"500\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 10px; font-size: 10px; color: #666666; text-align: left; font-weight: 300;\">&copy; 2020 Woden S.A.S Todos los derechos reservados.</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #eeeeee; padding: .75pt .75pt .75pt .75pt;\">\n"
                    + "<p style=\"margin-right: 0cm; margin-bottom: 13.5pt; margin-left: 0cm; line-height: 12.75pt; text-align: justify;\"><strong><span style=\"font-size: 8.5pt; color: black;\">NOTA DE CONFIDENCIALIDAD</span></strong><span style=\"font-size: 8.5pt; color: black;\">&nbsp;<br />Este mensaje y los archivos anexos, en caso de que existan, contienen informaci&oacute;n de WODEN que es confidencial y para uso exclusivo de la persona o entidad de destino. Debido a que puede contener informaci&oacute;n privilegiada, confidencial o que de alguna manera est&aacute; protegida contra su distribuci&oacute;n no autorizada, agradecemos que si ha recibido este correo electr&oacute;nico por error, notificarlo de manera inmediata al remitente. La Protecci&oacute;n de datos est&aacute; dada para el cumplimiento del Decreto 1581 del 2012. Protege el Medio Ambiente; piensa antes de imprimir este mensaje.<u></u></span></p>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</center></div>";

            String mail = "jrojas@woden.com.co";
            String mail2 = "ycastano@woden.com.co";
            messageHtml = messageHtml.replace("{destinatario}", destinatarioListaFront.getName()).replace("{pqrs}", destinatarioListaFront.getDescription()).replace("{ticket}", "" + destinatarioListaFront.getTicket()).replace("{politica}", politica.getDescription());
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decodePqrs(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Generación de ticket: " + destinatarioListaFront.getTicket());
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail2));
            if (destinatarioListaFront.getEmailAnnexed() == null) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(destinatarioListaFront.getEmails()));
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                status = 1;
            } else {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(destinatarioListaFront.getEmailAnnexed() + "," + destinatarioListaFront.getEmails()));
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                status = 1;
            }
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (MessagingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
    }

    public ResponseModel emailLog(String userId, int destinatarioId, String status, String typeStatus) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailLog(?,?,?,?,?)}");
            cs.setString(1, userId);
            cs.setInt(2, destinatarioId);
            cs.setString(3, status);
            cs.setString(4, typeStatus);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
            cn.commit();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
}
