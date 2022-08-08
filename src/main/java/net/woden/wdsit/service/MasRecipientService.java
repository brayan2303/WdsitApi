package net.woden.wdsit.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MasRecipientEntity;
import net.woden.wdsit.model.ResponseModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MasRecipientService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(int mailId, int creationUserId, MultipartFile file) {
        int inserts = 0;
        int load = 0;
        Connection cn = this.ds.openConnection();
        File directory = new File(this.en.getProperty("correoMasivo.destinatarios.url"));
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            File archivo = new File(directory + "\\DESTINATARIOS.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < hoja.getPhysicalNumberOfRows(); i++) {
                fila = hoja.getRow(i);
                for (int j = 0; j < fila.getPhysicalNumberOfCells(); j++) {
                    celda = fila.getCell(j);
                    bw.write(celda.getStringCellValue());
                    bw.write("\t");
                }
                bw.write(String.valueOf(mailId));
                bw.write("\t");
                bw.write(String.valueOf(creationUserId));
                bw.write("\t");
                bw.write(format.format(date));
                bw.newLine();
            }
            bw.close();
            load = 1;
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (load == 1) {
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_MasRecipientCreate(?,?)}");
                cs.setInt(1, mailId);
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(2);
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
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel delete(int mailId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasRecipientDelete(?)}");
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

    public ResponseModel list(int mailId) {
        ArrayList<MasRecipientEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasRecipientList(?)}");
            cs.setInt(1, mailId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                MasRecipientEntity m = new MasRecipientEntity();
                m.setMail(rs.getString("mail"));
                m.setType(rs.getString("type"));
                m.setCreationUserId(rs.getInt("creationUserId"));
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
}
