package net.woden.wdsit.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.RepFieldEntity;
import net.woden.wdsit.entity.RepMailEntity;
import net.woden.wdsit.entity.RepReportEntity;
import net.woden.wdsit.model.ResponseModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class RepMailService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private RepFieldService repFieldS;
    @Autowired
    private RepReportService repReportS;

//    @Async
//    @EventListener(ApplicationReadyEvent.class)
    public void executeEmail() {
        while (true) {
            Date currentDate;
            String date;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<RepMailEntity> list = (ArrayList<RepMailEntity>) this.findAll().getObject();
            if (list.size() > 0) {
                currentDate=new Date();
                date = format.format(currentDate);
                for (RepMailEntity r : list) {
                    try {
                        Date mailDate = format.parse(r.getStartDate());
                        if (r.getPeriod().equals("Cada hora")) {
                            long time = currentDate.getTime() - mailDate.getTime();
                            if (time / 3600000 >= 1) {
                                ArrayList<String> mails = (ArrayList<String>) this.findMail(r.getId()).getObject();
                                this.send(r, mails);
                                this.updateStartDate(r.getId(),date);
                            }
                        } else if (r.getPeriod().equals("Diario")) {
                            long time = currentDate.getTime() - mailDate.getTime();
                            if (time / 86400000 >= 1) {
                                ArrayList<String> mails = (ArrayList<String>) this.findMail(r.getId()).getObject();
                                this.send(r, mails);
                                this.updateStartDate(r.getId(),date);
                            }
                        } else if (r.getPeriod().equals("Semanal")) {
                            long time = currentDate.getTime() - mailDate.getTime();
                            if (time / 604800000 >= 1) {
                                ArrayList<String> mails = (ArrayList<String>) this.findMail(r.getId()).getObject();
                                this.send(r, mails);
                                this.updateStartDate(r.getId(),date);
                            }
                        }
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ResponseModel create(RepMailEntity r) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailCreate(?,?,?,?,?,?)}");
            cs.setString(1, r.getTitle());
            cs.setString(2, r.getMessage());
            cs.setString(3, r.getPeriod());
            cs.setTimestamp(4, Timestamp.valueOf(r.getStartDate()));
            cs.setInt(5, r.getReportId());
            cs.registerOutParameter(6, Types.VARCHAR);
            cs.execute();
            inserts = cs.getInt(6);
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
    public ResponseModel update(RepMailEntity r) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, r.getId());
            cs.setString(2, r.getTitle());
            cs.setString(3, r.getMessage());
            cs.setString(4, r.getPeriod());
            cs.setTimestamp(5, Timestamp.valueOf(r.getStartDate()));
            cs.setInt(6, r.getReportId());
            cs.setBoolean(7, r.isActive());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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
    public ResponseModel updateStartDate(int mailId,String startDate) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailUpdateStartDate(?,?,?)}");
            cs.setInt(1, mailId);
            cs.setTimestamp(2, Timestamp.valueOf(startDate));
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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
            CallableStatement cs = cn.prepareCall("{call sp_RepMailDelete(?)}");
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
    public ResponseModel findById(int mailId) {
        RepMailEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailFindById(?)}");
            cs.setInt(1, mailId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new RepMailEntity();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setMessage(rs.getString("message"));
                r.setPeriod(rs.getString("period"));
                r.setStartDate(rs.getString("startDate"));
                r.setReportId(rs.getInt("reportId"));
                r.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }
    public ResponseModel list() {
        ArrayList<RepMailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepMailEntity r = new RepMailEntity();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setMessage(rs.getString("message"));
                r.setPeriod(rs.getString("period"));
                r.setStartDate(rs.getString("startDate"));
                r.setReportId(rs.getInt("reportId"));
                r.setReport(rs.getString("report"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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
    public ResponseModel findAll() {
        ArrayList<RepMailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailFindAll()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepMailEntity r = new RepMailEntity();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setMessage(rs.getString("message"));
                r.setPeriod(rs.getString("period"));
                r.setStartDate(rs.getString("startDate"));
                r.setReportId(rs.getInt("reportId"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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
    public ResponseModel findMail(int mailId) {
        ArrayList<String> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepMailFindMail(?)}");
            cs.setInt(1, mailId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("mail"));
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
    private ResponseModel send(RepMailEntity r, ArrayList<String> mails) {
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost(this.en.getProperty("spring.mail.pqrs.host"));
        mailSender.setPort(Integer.valueOf(this.en.getProperty("spring.mail.pqrs.port")));
        mailSender.setUsername(this.en.getProperty("spring.mail.pqrs.username"));
        mailSender.setPassword(this.en.getProperty("spring.mail.pqrs.password"));
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        int status = 0;
        String delimiter = "\\";
        String separator = "\n";
        String headers = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        RepReportEntity report = (RepReportEntity) this.repReportS.findById(r.getReportId()).getObject();
        ArrayList<RepFieldEntity> columns = (ArrayList<RepFieldEntity>) this.repFieldS.findByReportId(r.getReportId()).getObject();
        JSONArray rows = (JSONArray) this.repReportS.executeByMail(report.getId(), report.getStoreProcedure(), r.getStartDate().substring(0, 10), format.format(new Date())).getObject();
        File file = new File(this.en.getProperty("reporte.url") + "/" + r.getTitle());
        try {
            FileWriter archivo = new FileWriter(file + ".csv");
            for (int i = 0; i < columns.size(); i++) {
                headers = headers + columns.get(i).getName();
                if (i < columns.size() - 1) {
                    headers = headers + delimiter;
                }
            }
            archivo.append(headers);
            for (int i = 0; i < rows.size(); i++) {
                archivo.append(separator);
                JSONObject json = (JSONObject) rows.get(i);
                for (int j = 0; j < columns.size(); j++) {
                    for(Object key:json.keySet()){
                        if(((String)key).equals(columns.get(j).getName())){
                            archivo.append(json.get(key) == null ? "" : json.get(key).toString());
                            archivo.append(delimiter);
                        }
                    }
                }
            }
            archivo.flush();
            archivo.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom(this.en.getProperty("spring.mail.username"), "WODEN");
            message.setSubject(r.getTitle());
            for (String m : mails) {
                message.addTo(m);
            }
            message.setText(r.getMessage(), true);
            DataSource source = new FileDataSource(file + ".csv");
            message.addAttachment(report.getName()+".csv", source);
            mailSender.send(mimeMessage);
            status = 1;
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), 0, 500);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), 0, 500);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }
}
