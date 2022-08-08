/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ProNotificationEntity;
import net.woden.wdsit.entity.ProNotificationUserEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProNotificationService {
    
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private EncryptUtility eu;
    @Autowired
    private ProNotificationUserService proNotificationUserS;

    public ResponseModel create(ProNotificationEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationCreate(?,?,?,?)}");
            cs.setString(1, b.getName());
            cs.setString(2, b.getMessage());
            cs.setString(3, b.getVariables());
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

    public ResponseModel update(ProNotificationEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, b.getId());
            cs.setString(2, b.getName());
            cs.setString(3, b.getMessage());
            cs.setString(4, b.getVariables());
            cs.setBoolean(5, b.isActive());
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

    public ResponseModel delete(int notificationId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationDelete(?)}");
            cs.setInt(1, notificationId);
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

    public ResponseModel findByName(String name) {
        ProNotificationEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationFindByName(?)}");
            cs.setString(+1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ProNotificationEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setMessage(rs.getString("message"));
                b.setVariables(rs.getString("variables"));
                b.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel list() {
        ArrayList<ProNotificationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProNotificationEntity b = new ProNotificationEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setMessage(rs.getString("message"));
                b.setVariables(rs.getString("variables"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
    
    public ResponseModel listMail(String notificationName,int perspectiveId, int measurementId,int actionPlanId) {
        ArrayList<ProNotificationUserEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationListMail(?,?,?,?)}");
            cs.setString(1, notificationName);
            cs.setInt(2,perspectiveId);
            cs.setInt(3,measurementId);
            cs.setInt(4,actionPlanId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProNotificationUserEntity b=new ProNotificationUserEntity();
                b.setId(rs.getInt("id"));
                b.setUser(rs.getString("user"));
                b.setMail(rs.getString("mail"));
                list.add(b);
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

    public ResponseModel send(String name,ArrayList<ProNotificationUserEntity>mails ,ArrayList<String> variables) {
        ProNotificationEntity notification = (ProNotificationEntity) this.findByName(name).getObject();
        int status = 0;
        if (notification != null) {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.en.getProperty("spring.mail.host"));
            mailSender.setPort(Integer.valueOf(this.en.getProperty("spring.mail.port")));
            mailSender.setUsername(this.en.getProperty("spring.mail.username"));
            mailSender.setPassword(this.eu.decode(this.en.getProperty("spring.mail.password")));
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            try {
                MimeMessage[] listMailSend = new MimeMessage[mails.size()];
                String messageHtml = notification.getMessage();
                if (mails.size() > 0) {
                    for (int m = 0; m < mails.size(); m++) {
                        MimeMessage mimeMessage = mailSender.createMimeMessage();
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                        message.setFrom(mailSender.getUsername());
                        message.setSubject(notification.getName());
                        message.addTo(mails.get(m).getMail());
                        for (int v = 0; v < notification.getVariables().split("\\,").length; v++) {
                            if (notification.getVariables().split("\\,")[v].equals("{userName}")) {
                                messageHtml = messageHtml.replace(notification.getVariables().split("\\,")[v], mails.get(m).getUser());
                            } else {
                                messageHtml = messageHtml.replace(notification.getVariables().split("\\,")[v], variables.get(v - 1));
                            }
                        }
                        message.setText(messageHtml, true);
                        listMailSend[m] = mimeMessage;
                        messageHtml = notification.getMessage();
                    }
                    mailSender.send(listMailSend);
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
