package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MasAccountEntity;
import net.woden.wdsit.entity.MasMailEntity;
import net.woden.wdsit.entity.MasRecipientEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

@Service
public class MasMailService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private MasRecipientService masRecipientS;
    @Autowired
    private MasRecipientLogService masRecipientLogS;
    @Autowired
    private MasSendService masSendS;
    @Autowired
    private EncryptUtility eu;

    public ResponseModel create(MasMailEntity m) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasMailCreate(?,?,?,?,?)}");
            cs.setString(1, m.getSubject());
            cs.setString(2, m.getMessage());
            cs.setInt(3, m.getAccountId());
            cs.setInt(4, m.getCreationUserId());
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

    public ResponseModel update(MasMailEntity m) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasMailUpdate(?,?,?,?,?)}");
            cs.setInt(1, m.getId());
            cs.setString(2, m.getSubject());
            cs.setString(3, m.getMessage());
            cs.setInt(4, m.getAccountId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasMailDelete(?)}");
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

    public ResponseModel list() {
        ArrayList<MasMailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasMailList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MasMailEntity m = new MasMailEntity();
                m.setId(rs.getInt("id"));
                m.setSubject(rs.getString("subject"));
                m.setMessage(rs.getString("message"));
                m.setAccountId(rs.getInt("accountId"));
                m.setAccount(new MasAccountEntity(rs.getInt("accountId"), rs.getString("mail"), rs.getString("name"), rs.getString("position"), rs.getString("provider"), rs.getInt("port"), this.eu.decode(rs.getString("password")), rs.getString("creationDate"), rs.getInt("creationUserId"), rs.getString("creationUser"), rs.getBoolean("active")));
                m.setCreationDate(rs.getString("creationDate"));
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

    public ResponseModel findById(int id) {
        MasMailEntity m = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasMailFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                m = new MasMailEntity();
                m.setId(rs.getInt("id"));
                m.setSubject(rs.getString("subject"));
                m.setMessage(rs.getString("message"));
                m.setAccountId(rs.getInt("accountId"));
                m.setAccount(new MasAccountEntity(rs.getInt("accountId"), rs.getString("mail"), rs.getString("name"), rs.getString("position"), rs.getString("provider"), rs.getInt("port"), this.eu.decode(rs.getString("password")), rs.getString("creationDate"), rs.getInt("creationUserId"), rs.getString("creationUser"), rs.getBoolean("active")));
                m.setCreationDate(rs.getString("creationDate"));
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

    public ResponseModel send(int id, int sendingUserId) {
        MasMailEntity mail = (MasMailEntity) this.findById(id).getObject();
        ArrayList<MasRecipientEntity> mails = (ArrayList) this.masRecipientS.list(id).getObject();
        int status = 0;
        String mailAccount="";
        String mailType="";
        if (mail != null) {
            Properties props = new Properties();
            props.put("mail.smtp.host", mail.getAccount().getProvider());
            props.put("mail.smtp.starttls.enable", "true");
            //props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.port", mail.getAccount().getPort());
            props.put("mail.smtp.mail.sender", mail.getAccount().getMail());
            props.put("mail.smtp.user", mail.getAccount().getMail());
            props.put("mail.smtp.auth", "true");
            Session session;
            session = Session.getDefaultInstance(props);
            try {
                String messageHtml = "<table style=\"font-family: Arial,sans-serif;\">\n"
                        + "    <tr>\n"
                        + "        <td><span>{{Mensaje}}</span></td>\n"
                        + "    </tr>\n"
                        + "    <tr>\n"
                        + "        <td>\n"
                        + "            <tr>\n"
                        + "                <td>\n"
                        + "                    <span style=\"color: #E75300;font-size: 18px;\"><b>{{Nombre}}</b></span>\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "            <tr>\n"
                        + "                <td style=\"font-size: 14px;color: #9e9e9e;\">\n"
                        + "                    <span>{{Cargo}}</span>\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "        </td>\n"
                        + "        <td>\n"
                        + "            <img src=\"http://app.woden.com.co/wdsit/assets/images/LogoInicio.jpg\" style=\"margin-top: 10px;\">\n"
                        + "        </td>\n"
                        + "    </tr>\n"
                        + "    <tr>\n"
                        + "        <td>\n"
                        + "            <div style=\"font-size: 12px;font-style: oblique;color: #9e9e9e;margin-top:10px;\">\n"
                        + "                NOTA DE CONFIDENCIALIDAD: Este mensaje y los archivos anexos, en caso de que existan, contienen información de WODEN que es confidencial y para uso exclusivo de la persona o entidad de destino. Debido a que puede contener información privilegiada, confidencial o que de alguna manera está protegida contra su distribución no autorizada, agradecemos que, si ha recibido este correo electrónico por error, notificarlo de manera inmediata al remitente.\n"
                        + "                La Protección de datos está dada para el cumplimiento de la ley 1581 del 2012. Protege el Medio Ambiente; piensa antes de imprimir este mensaje.\n"
                        + "            </div> \n"
                        + "        </td>\n"
                        + "    </tr>\n"
                        + "</table>";
                messageHtml = messageHtml.replace("{{Mensaje}}", mail.getMessage()).replace("{{Nombre}}", mail.getAccount().getName()).replace("{{Cargo}}", mail.getAccount().getPosition());
                Transport t = session.getTransport("smtp");
                t.connect((String) props.get("mail.smtp.user"), mail.getAccount().getPassword());
                for (int i = 0; i < mails.size(); i++) {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
                    message.setSubject(mail.getSubject());
                    message.setContent(messageHtml, "text/html; charset=utf-8");
                    mailAccount=mails.get(i).getMail();
                    mailType=mails.get(i).getType();
                    if (mails.get(i).getType().equalsIgnoreCase("to")) {
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mails.get(i).getMail()));
                    } else if (mails.get(i).getType().equalsIgnoreCase("cc")) {
                        message.addRecipient(Message.RecipientType.CC, new InternetAddress(mails.get(i).getMail()));
                    } else if (mails.get(i).getType().equalsIgnoreCase("cco")) {
                        message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mails.get(i).getMail()));
                    }
                    t.sendMessage(message, message.getAllRecipients());
                    this.masRecipientLogS.create(id,mails.get(i).getMail(),mails.get(i).getType(),"Enviado");
                }
//                if (mails.size() < 5) {
//                    MimeMessage message = new MimeMessage(session);
//                    message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
//                    message.setSubject(mail.getSubject());
//                    message.setContent(messageHtml, "text/html; charset=utf-8");
//                    for (int j = 0; j < mails.size(); j++) {
//                        if (mails.get(count).getType().equalsIgnoreCase("to")) {
//                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mails.get(count).getMail()));
//                        } else if (mails.get(count).getType().equalsIgnoreCase("cc")) {
//                            message.addRecipient(Message.RecipientType.CC, new InternetAddress(mails.get(count).getMail()));
//                        } else if (mails.get(count).getType().equalsIgnoreCase("cco")) {
//                            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mails.get(count).getMail()));
//                        }
//                        count++;
//                    }
//                    t.sendMessage(message, message.getAllRecipients());
//                } else if (mails.size() >= 5) {
//                    int cociente = mails.size() / 5;
//                    int residuo = mails.size() % 5;
//                    for (int i = 0; i < cociente; i++) {
//                        MimeMessage message = new MimeMessage(session);
//                        message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
//                        message.setSubject(mail.getSubject());
//                        message.setContent(messageHtml, "text/html; charset=utf-8");
//                        for (int j = 0; j < 5; j++) {
//                            if (mails.get(count).getType().equalsIgnoreCase("to")) {
//                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mails.get(count).getMail()));
//                            } else if (mails.get(count).getType().equalsIgnoreCase("cc")) {
//                                message.addRecipient(Message.RecipientType.CC, new InternetAddress(mails.get(count).getMail()));
//                            } else if (mails.get(count).getType().equalsIgnoreCase("cco")) {
//                                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mails.get(count).getMail()));
//                            }
//                            count++;
//                        }
//                        t.sendMessage(message, message.getAllRecipients());
//                        Thread.sleep(2000);
//                    }
//                    if (residuo > 0) {
//                        MimeMessage message = new MimeMessage(session);
//                        message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
//                        message.setSubject(mail.getSubject());
//                        message.setContent(messageHtml, "text/html; charset=utf-8");
//                        for (int j = 0; j < residuo; j++) {
//                            if (mails.get(count).getType().equalsIgnoreCase("to")) {
//                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mails.get(count).getMail()));
//                            } else if (mails.get(count).getType().equalsIgnoreCase("cc")) {
//                                message.addRecipient(Message.RecipientType.CC, new InternetAddress(mails.get(count).getMail()));
//                            } else if (mails.get(count).getType().equalsIgnoreCase("cco")) {
//                                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mails.get(count).getMail()));
//                            }
//                            count++;
//                        }
//                        t.sendMessage(message, message.getAllRecipients());
//                        Thread.sleep(2000);
//                    }
//                }
                t.close();
                status = 1;
                this.masSendS.send(id, "Enviado", sendingUserId);
            } catch (MailException ex) {
                this.masSendS.send(id, ex.getMessage(), sendingUserId);
                this.masRecipientLogS.create(id,mailAccount,mailType,ex.getMessage());
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            } catch (MessagingException ex) {
                this.masSendS.send(id, ex.getMessage(), sendingUserId);
                this.masRecipientLogS.create(id,mailAccount,mailType,ex.getMessage());
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }
}
