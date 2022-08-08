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
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class MessageService {
    
    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;
    @Autowired
    private GenPersonService genPersonS;
    @Autowired
    private EncryptUtility eu;
    
    
        public void sendEmail(String nameFiles, GenPersonEntity destinatario) {
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
                    + "<td style=\"padding: 10px; text-align: center; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Envio FTP</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Hola!! <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Queremos decirte que se genero satisfactoriamente el envio de los siguientes archivos</p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">{archivos}</p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Gracias por tu atencion</p>\n"
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
            messageHtml = messageHtml.replace("{destinatario}", destinatario.getFirstName()+" "+destinatario.getLastName()).replace("{archivos}", nameFiles);
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decode(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Envio FTP");
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            status = 1;
        } catch (MailException ex) {
             System.out.println("Error de en vio cath 1");
        } catch (MessagingException ex) {
                         System.out.println("Error de en vio cath 2");
        }
                     System.out.println("Error de en vio");
    }
    
}
