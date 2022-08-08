package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrMailEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class PqrMailService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;
    @Autowired
    private PqrMailStatusService pqrMailStatusS;
    @Autowired
    private PqrMailPersonService pqrMailPersonS;

    public ResponseModel create(PqrMailEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailCreate(?,?,?,?)}");
            cs.setString(1, p.getSubject());
            cs.setString(2, p.getMessage());
            cs.setString(3, p.getVariables());
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();
            inserts = cs.getInt(4);
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

    public ResponseModel update(PqrMailEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getSubject());
            cs.setString(3, p.getMessage());
            cs.setString(4, p.getVariables());
            cs.setBoolean(5, p.isActive());
            cs.registerOutParameter(6, Types.VARCHAR);
            cs.execute();
            updates = cs.getInt(6);
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

    public ResponseModel delete(int mailId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailDelete(?)}");
            cs.setInt(1, mailId);
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
        ArrayList<PqrMailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrMailEntity p = new PqrMailEntity();
                p.setId(rs.getInt("id"));
                p.setSubject(rs.getString("subject"));
                p.setMessage(rs.getString("message"));
                p.setVariables(rs.getString("variables"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel send(int id, ArrayList<String> variables) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.en.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.valueOf(this.en.getProperty("spring.mail.port")));
        mailSender.setUsername(this.en.getProperty("spring.mail.username"));
        mailSender.setPassword(this.eu.decodePqrs(this.en.getProperty("spring.mail.password")));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        ArrayList<PqrMailEntity> notification;
        int status = 0;
        notification = (ArrayList<PqrMailEntity>) this.pqrMailStatusS.find(id).getObject();
        if (notification.size() > 0) {
            try {
                for (PqrMailEntity pme : notification) {
                    ArrayList<String> mails = (ArrayList<String>) this.pqrMailPersonS.find(pme.getId()).getObject();
                    String messageHtml=pme.getMessage();
                    for (String m : mails) {
                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                        message.setFrom(mailSender.getUsername());
                        message.setSubject(pme.getSubject());
                        message.addTo(m);
                        messageHtml=messageHtml.replace("{Nombres}",m);
                        messageHtml=messageHtml.replace("{Pqrs}",variables.get(0));
                        messageHtml=messageHtml.replace("{Estado}",variables.get(1));
                        messageHtml=messageHtml.replace("{Fecha}",Timestamp.valueOf(variables.get(2)).toString().substring(0,16));
                        messageHtml=messageHtml.replace("{Agente}",variables.get(3));
                        message.setText(messageHtml,true);
                        mailSender.send(mimeMessage);
                        messageHtml=pme.getMessage();
                    }
                    status = 1;
                }
            } catch (MailException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), 0, 500);
            } catch (MessagingException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), 0, 500);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }
}
