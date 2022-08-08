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
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrPqrsEntity;
import net.woden.wdsit.entity.PqrsClientSerialEntity;
import net.woden.wdsit.model.PqrPqrsFileModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrsClientSerialService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(PqrsClientSerialEntity p, int countryId) {
        String inserts = "";
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrsClientSerialCreate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, p.getType());
            cs.setString(2, p.getSerial());
            cs.setString(3, p.getPhoneMovil());
            cs.setString(4, p.getIdentification());
            cs.setString(5, p.getImei());
            cs.setString(6, p.getDescription());
            cs.setInt(7, p.getUserId());
            cs.setInt(8, p.getCategoryId());
            cs.setString(9, p.getCustomerId());
            cs.setInt(10, countryId);
            cs.registerOutParameter(11, Types.INTEGER);
            cs.registerOutParameter(12, Types.VARCHAR);
            cs.execute();
            if (cs.getInt(11) > 0) {
                inserts = cs.getString(12);
            }

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

     public ResponseModel list(String ticket, int creationPersonId) {
        ArrayList<PqrPqrsEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrsClientSerialList(?,?)}");
            cs.setString(1, ticket);
            cs.setInt(2, creationPersonId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity p = new PqrPqrsEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setSerial(rs.getString("serial"));
                p.setSummary(rs.getString("summary"));
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
            CallableStatement cs = cn.prepareCall("{call sp_PqrsClientSerialDelete(?)}");
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

    public ResponseModel findById(int id) {
        PqrsClientSerialEntity p = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrsClientSerialFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrsClientSerialEntity();
                p.setId(rs.getInt("id"));
                p.setType(rs.getString("type"));
                p.setSerial(rs.getString("serial"));
                p.setIdentification(rs.getString("identification"));
                p.setPhoneMovil(rs.getString("phoneMovil"));
                p.setImei(rs.getString("imei"));
                p.setDescription(rs.getString("description"));
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
        return new ResponseModel(getClass().getSimpleName(), "Error verificar por favor", p, 200);
    }

    public ResponseModel update(PqrsClientSerialEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrsClientSerialUpdate(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getType());
            cs.setString(3, p.getSerial());
            cs.setString(4, p.getIdentification());
            cs.setString(5, p.getImei());
            cs.setString(6, p.getPhoneMovil());
            cs.setString(7, p.getDescription());
            cs.setInt(8, p.getUserId());
            cs.registerOutParameter(9, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(9);
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
        return new ResponseModel(getClass().getSimpleName(), "Error", updates, 200);
    }

    public ResponseModel loadFile(String pqrsNumber, String type, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\" + type);
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

    public ResponseModel listFile(String pqrsNumber, String type) {
        ArrayList<PqrPqrsFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\" + type);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    PqrPqrsFileModel p = new PqrPqrsFileModel();
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

    public ResponseModel AllSerial(String ticket) {
        PqrPqrsEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrAllSerial(?)}");
            cs.setString(1, ticket);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrPqrsEntity();
                p.setId(rs.getInt("id"));
                p.setSerial(rs.getString("serial"));
                p.setSummary(rs.getString("summary"));
            }
            cn.close();
            cs.close();
            rs.close();
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

}
