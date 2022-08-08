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
import net.woden.wdsit.entity.MeeSupportEntity;
import net.woden.wdsit.model.MeeSupportFileModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MeeSupportService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private GenPersonService genPersonS;
    @Autowired
    private EncryptUtility eu;

    public ResponseModel create(MeeSupportEntity m) {
        int identity = 0;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportCreate(?,?,?,?,?)}");
            cs.setInt(1, m.getMeetingId());
            cs.setString(2, m.getSupport());
            cs.setInt(3, m.getResponsibleUserId());
            cs.setInt(4, m.getCreationUserId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            identity = cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", identity, 200);
    }

        public ResponseModel update(MeeSupportEntity m) {
        int updates = 0;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, m.getId());
            cs.setString(2, m.getSupport());
            cs.setString(3, m.getStatus());
            cs.setString(4, m.getStartDate());
            cs.setString(5, m.getEndDate());
            cs.setString(6, m.getAnswerFinish());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel findById(int supportId) {
        MeeSupportEntity m = null;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportFindById(?)}");
            cs.setInt(1, supportId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", m, 200);
    }

    public ResponseModel list(int meetingId) {
        ArrayList<MeeSupportEntity> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportList(?)}");
            cs.setInt(1, meetingId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeSupportEntity m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                list.add(m);
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listByUserId(int responsibleUserId, String startDate, String endDate,String states) {
        ArrayList<MeeSupportEntity> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {

            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportListByUserId(?,?,?,?)}");
            cs.setInt(1, responsibleUserId);
            cs.setString(2, startDate);
            cs.setString(3, endDate);
            cs.setString(4, states);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeSupportEntity m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setMeeting(rs.getString("meeting"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                list.add(m);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listByCreationUserId(int meetingId, int creationUserId) {
        ArrayList<MeeSupportEntity> list = new ArrayList();
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportListByCreationUserId(?,?)}");
            cs.setInt(1, meetingId);
            cs.setInt(2, creationUserId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MeeSupportEntity m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setMeeting(rs.getString("meeting"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                list.add(m);
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel loadFile(int meetingId, int supportId, MultipartFile file) {
        int load;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("reunion.apoyo.url") + "\\" + meetingId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(directory1 + "\\" + supportId);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFiles(int meetingId, int supportId) {
        ArrayList<MeeSupportFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("reunion.apoyo.url") + "\\" + meetingId + "\\" + supportId);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (String f : directory.list()) {
                File file = new File(directory + "/" + f);
                try {
                    fis = new FileInputStream(file);
                    byte[] bytes = new byte[(int) file.length()];
                    fis.read(bytes);
                    fis.close();
                    MeeSupportFileModel m = new MeeSupportFileModel();
                    m.setSupportId(supportId);
                    m.setName(f.split("\\.", 2)[0]);
                    m.setFile(bytes);
                    m.setType(f.split("\\.", 2)[1]);
                    list.add(m);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel deleteFile(int meetingId, int supportId, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("reunion.apoyo.url") + "\\" + meetingId + "\\" + supportId);

        for (String f : directory.list()) {
            if (f.split("\\.", 2)[0].equals(fileName)) {
                File file = new File(directory + "/" + f);
                if (file.delete()) {
                    deletes = 1;
                }
                break;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel sendEmail(int supportId, int remitenteId, int destinatarioId) {
        GenPersonEntity remitente = (GenPersonEntity) this.genPersonS.findById(remitenteId).getObject();
        GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        MeeSupportEntity support=(MeeSupportEntity)this.findById(supportId).getObject();
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
                    + "<td style=\"padding: 10px; text-align: center; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Solicitud de apoyo</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Hola <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\"><strong>{remitente}&nbsp;</strong>te ha solicitado lo siguiente:</p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">{apoyo}</p>\n"
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
                    + "<td style=\"padding-bottom: 10px; font-size: 10px; color: #666666; text-align: left; font-weight: 300;\">&copy; 2020 Woden Colombia S.A. Todos los derechos reservados.</td>\n"
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
            messageHtml = messageHtml.replace("{destinatario}", destinatario.getFirstName() + " " + destinatario.getLastName()).replace("{remitente}", remitente.getFirstName()+" "+remitente.getLastName()).replace("{apoyo}", support.getSupport());
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decode(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Solicitud de apoyo");
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            status = 1;
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (MessagingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }
    public ResponseModel listStatus(String states){
    ArrayList<MeeSupportEntity> list = new ArrayList();
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportListStatus(?)}");
            cs.setString(1, states);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            MeeSupportEntity m = new MeeSupportEntity();
            m.setId(rs.getInt("id"));
            m.setMeetingId(rs.getInt("meetingId"));
            m.setSupport(rs.getString("support"));
            m.setStatus(rs.getString("status"));
            m.setStartDate(rs.getString("startDate"));
            m.setEndDate(rs.getString("endDate"));
            m.setResponsibleUserId(rs.getInt("responsibleUserId"));
            m.setCreationUserId(rs.getInt("creationUserId"));
            m.setCreationUser(rs.getString("responsibleUser"));
            m.setCreationUser(rs.getString("creationUser"));
            list.add(m);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK ", list, 200);
    }
    
    public ResponseModel sendEmailStatus (int supportId, int remitenteId, int destinatarioId, String status) {
        MeeSupportEntity m = null;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportFindById(?)}");
            cs.setInt(1, supportId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                m.setAnswerFinish(rs.getString("answerFinish"));
            }
            cn.commit();
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
        
        GenPersonEntity remitente = (GenPersonEntity) this.genPersonS.findById(m.getResponsibleUserId()).getObject();
        GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(m.getCreationUserId()).getObject();
        
        
        int send = 0;
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
                    + "<td style=\"padding: 10px; text-align: center; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Solicitud de apoyo</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Hola <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Queremos informarte que <strong>{remitente}&nbsp;</strong>Ha gestionado el siguiente apoyo <strong>{apoyo}</strong> y se encuentra en un estado:</p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\"><strong>{estado}</strong></p>\n"
                    +"<p style=\"color: #9e9e9e; margin: 5px;\">Ademas de informarte de las siguientes observaciones:</p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\"><strong>{respuesta}</strong></p>\n"
                    +"<p style=\"color: #9e9e9e; margin: 5px;\">Gracias por tu apoyo!!!</p>\n"
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
                    + "<td style=\"padding-bottom: 10px; font-size: 10px; color: #666666; text-align: left; font-weight: 300;\">&copy; 2020 Woden Colombia S.A. Todos los derechos reservados.</td>\n"
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
            messageHtml = messageHtml.replace("{destinatario}", destinatario.getFirstName() + " " + destinatario.getLastName()).replace("{remitente}", remitente.getFirstName()+" "+remitente.getLastName()).replace("{apoyo}", m.getSupport()).replace("{estado}", m.getStatus()).replace("{respuesta}", m.getAnswerFinish());
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decode(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Estado solicitud de apoyo");
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            send = 1;
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (MessagingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", send, 200);
    }
     
     public ResponseModel findByStatus(String status) {
        MeeSupportEntity m = null;
        Connection cn = ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MeeSupportFindById(?)}");
            cs.setString(1, status);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                m = new MeeSupportEntity();
                m.setId(rs.getInt("id"));
                m.setMeetingId(rs.getInt("meetingId"));
                m.setSupport(rs.getString("support"));
                m.setStatus(rs.getString("status"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setStartDate(rs.getString("startDate"));
                m.setEndDate(rs.getString("endDate"));
                m.setResponsibleUserId(rs.getInt("responsibleUserId"));
                m.setResponsibleUser(rs.getString("responsibleUser"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", m, 200);
    }
}
