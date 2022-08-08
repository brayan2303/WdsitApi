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
import net.woden.wdsit.entity.ActiveFixedReturnEntity;
import net.woden.wdsit.model.ActiveFixedReturnModel;
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
public class ActiveFixedReturnService {
    
    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel create(ActiveFixedReturnEntity a){
    int inserts = 0;
    Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedReturnCreate(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,a.getIdentification());
            cs.setString(2, a.getName());
            cs.setString(3, a.getMail());
            cs.setString(4, a.getCostCenter());
            cs.setString(5, a.getPosition());
            cs.setString(6, a.getCity());
            cs.setString(7, a.getPersonRes());
            cs.setString(8, a.getEquipment());
            cs.setString(9, a.getSerial());
            cs.setString(10, a.getCommentary());
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(11);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(),ex.getMessage(), null,200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
    
    public ResponseModel list(){
        ArrayList<ActiveFixedReturnEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedReturnList()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            ActiveFixedReturnEntity a = new ActiveFixedReturnEntity();
            a.setId(rs.getInt("id"));
            a.setIdentification(rs.getInt("identification"));
            a.setName(rs.getString("name"));
            a.setMail(rs.getString("mail"));
            a.setCostCenter(rs.getString("costCenter"));
            a.setPosition(rs.getString("position"));
            a.setCity(rs.getString("city"));
            a.setPersonRes(rs.getString("personRes"));
            a.setEquipment(rs.getString("equipment"));
            a.setSerial(rs.getString("serial"));
            a.setCommentary(rs.getString("commentary"));
            a.setCreationDate(rs.getDate("creationDate"));
            a.setActive(rs.getBoolean("active"));
            list.add(a);
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
      return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedReturnDelete(?)}");
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
     public ResponseModel loadFile(int identification, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("archivos.return.url") + "\\" + identification);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File directory2 = new File(this.en.getProperty("archivos.return.url") + "\\" + identification + "\\" + creationDate);
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
     
      public ResponseModel listFile(int identification, String creationDate) {
        ArrayList<ActiveFixedReturnModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("archivos.return.url") + "\\" + identification + "\\" + creationDate);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    ActiveFixedReturnModel a = new ActiveFixedReturnModel();
                    a.setName(f.getName());
                    a.setFile(bytes);
                    a.setType(f.getName().split("\\.", 2)[1]);
                    list.add(a);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
       public ResponseModel updateSerial(String serial) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_activeFixedAsssigmentReturnExit(?,?)}");
            cs.setString(1, serial);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            update = cs.getInt(2);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
    }


}
