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
import net.woden.wdsit.entity.CovFormDayEntity;
import net.woden.wdsit.model.CovFormDayModel;
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
public class CovFormDayService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(CovFormDayEntity a) {     //crear y almacenar info
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormDayCreate(?,?,?,?,?,?,?)}");
            cs.setString(1, a.getHasSymptoms());
            cs.setDate(2, a.getSymptomsDate());
            cs.setString(3, a.getDescripSymptims());
            cs.setString(4, a.getTypeDocument());
            cs.setInt(5, a.getIdentificationUser());
            cs.setString(6, a.getName());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(7);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormDayDelete(?)}");
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

    public ResponseModel list() {                                   //listar en pantalla info
        ArrayList<CovFormDayEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormDayList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormDayEntity a = new CovFormDayEntity();
                a.setId(rs.getInt("Id"));
                a.setHasSymptoms(rs.getString("hasSymptoms"));
                a.setSymptomsDate(rs.getDate("symptomsDate"));
                a.setIdentificationUserCar(rs.getString("identificationUserCar"));
                a.setDescripSymptims(rs.getString("descripSymptims"));
                a.setTypeDocument(rs.getString("typeDocument"));
                a.setCreationUser(rs.getString("creationUser"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setName(rs.getString("name"));
                a.setActive(rs.getBoolean("active"));
                list.add(a);
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

    public ResponseModel update(CovFormDayEntity a) {         //actualizar info
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getHasSymptoms());
            cs.setDate(3, a.getSymptomsDate());
            cs.setString(4, a.getDescripSymptims());
            cs.setString(5, a.getTypeDocument());
            cs.setInt(6, a.getIdentificationUser());
            cs.setBoolean(7, a.isActive());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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

    public ResponseModel findById(int id) {                                   //listar en pantalla info
        CovFormDayEntity a = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new CovFormDayEntity();
                a.setHasSymptoms(rs.getString("hasSymptoms"));
                a.setSymptomsDate(rs.getDate("symptomsDate"));
                a.setDescripSymptims(rs.getString("descripSymptims"));
                a.setTypeDocument(rs.getString("typeDocument"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", a, 200);
    }

       public ResponseModel loadFile(int identification,String type, String creationDate,   MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification );
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        
        File directory2 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification + "\\" + type);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        
        File directory3 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification + "\\" + type + "\\" + creationDate);
        if (!directory3.isDirectory()) {
            directory3.mkdir();
        }
     

        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0]  + "." + file.getOriginalFilename().split("\\.", 2)[1] );
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

    public ResponseModel listFile(int userId) {
        ArrayList<CovFormDayModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("covid.evidencias.url") + "\\" + userId);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    CovFormDayModel p = new CovFormDayModel();
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
    
        public ResponseModel listFile(int identification, String type, String creationDate) {
        ArrayList<CovFormDayModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification +"\\"+type+"\\"+creationDate);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    CovFormDayModel p = new CovFormDayModel();
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
         public ResponseModel activeInactive(int id,boolean status) {         //actualizar info
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormDayActiveInactive(?,?,?)}");
            cs.setInt(1, id);
            cs.setBoolean(2, status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

}
