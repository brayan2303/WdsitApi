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
import net.woden.wdsit.entity.ActiveFixedAssigmentEntity;
import net.woden.wdsit.entity.ActiveFixedEntity;
import net.woden.wdsit.model.ActiveFixedAssigListPersonModel;
import net.woden.wdsit.model.ActiveFixedAssigmentFeaturModel;
import net.woden.wdsit.model.ActiveFixedAssigmentModel;
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
public class ActiveFixedAssigmentService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel create(ActiveFixedAssigmentEntity a) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigmentCreate(?,?,?,?,?,?)}");
            cs.setInt(1, a.getIdentification());
            cs.setString(2, a.getName());
            cs.setString(3, a.getMail());
            cs.setString(4, a.getCostCenter());
            cs.setString(5, a.getPosition());
            cs.setString(6, a.getCity());
            cs.setString(7, a.getProductEquip());
            cs.setString(8, a.getSerial());
            cs.setString(9, a.getExitPermanent());
            cs.setString(10, a.getStatusEquipament());
            cs.setString(11, a.getPersonRes());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(12);
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

    public ResponseModel list() {                                   //listar en pantalla info
        ArrayList<ActiveFixedAssigmentEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigmentList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
   
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigmentDelete(?)}");
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

    public ResponseModel update(ActiveFixedAssigmentEntity a, int updateUser) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigmentUpdateIdentification(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setInt(2, a.getIdentification());
            cs.setString(3, a.getName());
            cs.setString(4, a.getMail());
            cs.setString(5, a.getCostCenter());
            cs.setString(6, a.getPosition());
            cs.setString(7, a.getCity());
            cs.setString(8, a.getPersonRes());
            cs.setBoolean(9, a.getActive());
            cs.setInt(10, updateUser);
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(11);

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

    public ResponseModel findById(int id) {
        ActiveFixedAssigmentEntity a = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFiexdAssigmentFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setMail(rs.getString("mail"));
                a.setCostCenter(rs.getString("costCenter"));
                a.setPosition(rs.getString("position"));
                a.setCity(rs.getString("city"));
                a.setProductEquip(rs.getString("productEquip"));
                a.setSerial(rs.getString("serial"));
                a.setPersonRes(rs.getString("personRes"));
                a.setCreationDate(rs.getDate("creationDate"));
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

    public ResponseModel loadFile(int identification, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("archivos.activos.url") + "\\" + identification);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File directory2 = new File(this.en.getProperty("archivos.activos.url") + "\\" + identification + "\\" + creationDate);
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

    public ResponseModel listFeatur(int productId) {
        ArrayList<ActiveFixedEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedListFeatur(?)}");
            cs.setInt(1, productId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedEntity a = new ActiveFixedEntity();
                a.setId(rs.getInt("id"));
                a.setName(rs.getString("name"));
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

    public ResponseModel listFile(int identification, String creationDate) {
        ArrayList<ActiveFixedAssigmentModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("archivos.activos.url") + "\\" + identification + "\\" + creationDate);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    ActiveFixedAssigmentModel a = new ActiveFixedAssigmentModel();
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

    public ResponseModel assigFeaturAll(int id) {
        ArrayList<ActiveFixedAssigmentFeaturModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigamenFeaturAll(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigmentFeaturModel a = new ActiveFixedAssigmentFeaturModel();
                a.setName(rs.getString("name"));
                a.setDescription(rs.getString("description"));
                a.setSerial(rs.getString("serial"));
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

    public ResponseModel listPerson() {
        ArrayList<ActiveFixedAssigListPersonModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveAssigListPerson()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigListPersonModel a = new ActiveFixedAssigListPersonModel();
                a.setId(rs.getInt("id"));
                a.setNames(rs.getString("names"));
                list.add(a);

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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listAnswer(String personRes) {
        ArrayList<ActiveFixedAssigmentEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
     
       CallableStatement cs = cn.prepareCall("{call sp_activeFixedExitAnswerList(?)}");
            cs.setString(1, personRes);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setProduct(rs.getString("product"));
                a.setNameRes(rs.getString("nameRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
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
      public ResponseModel listAnswerAll() {
        ArrayList<ActiveFixedAssigmentEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
     
       CallableStatement cs = cn.prepareCall("{call sp_activeFixedExitAnswerListAll()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setProduct(rs.getString("product"));
                a.setNameRes(rs.getString("nameRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
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

    public ResponseModel aprovedRejected(int id, boolean status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_activeFixedExitAnswerAprovedRejected(?,?,?)}");
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
    public ResponseModel updateAnswer(int id, String answer) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssigmentUpdate(?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, answer);
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
    
     public ResponseModel entryExit(String serial, boolean status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_activeFixedExitEntryCompany(?,?,?)}");
            cs.setString(1, serial);
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
     
     public ResponseModel listExitVerif() {
        ArrayList<ActiveFixedAssigmentEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_activeFixedExitAnswerList(?)}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedAssigmentEntity a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setPersonRes(rs.getString("personRes"));
                a.setProduct(rs.getString("product"));
                a.setNameRes(rs.getString("nameRes"));
                a.setSerial(rs.getString("serial"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setAnswer(rs.getString("answer"));
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
     
     public ResponseModel findByIdentification(String serial){
       
         ActiveFixedAssigmentEntity a = null;
         Connection cn = this.ds.openConnection();
         
         try {
             CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedReturnFindByIdentification(?)}");
             cs.setString(1, serial);
             ResultSet rs = cs.executeQuery();
             while(rs.next()){
             a = new ActiveFixedAssigmentEntity();
             a.setId(rs.getInt("id"));
             a.setIdentification(rs.getInt("identification"));
             a.setName(rs.getString("name"));
             a.setMail(rs.getString("mail"));
             a.setCostCenter(rs.getString("costCenter"));
             a.setPosition(rs.getString("position"));
             a.setCity(rs.getString("city"));
             a.setPersonRes(rs.getString("personRes"));
             a.setProductEquip(rs.getString("productEquip"));
             a.setProduct(rs.getString("product"));
             a.setPerson(rs.getString("person"));
             a.setSerial(rs.getString("serial"));
             a.setExitEntry(rs.getBoolean("exitEntry"));
             }
             cs.close();
             rs.close();
             cs.close();
         } catch (SQLException ex) {
             return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
         }finally{
             try {
                 cn.close();
             } catch (SQLException ex) {
                 return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
             }
         }
         return new ResponseModel(getClass().getSimpleName(), a == null ? "El serial no tiene usuario asignado" : "OK", a, 200);
     }
     public ResponseModel updateDate(ActiveFixedAssigmentEntity a){
          int update = 0;
          Connection cn = this.ds.openConnection();
          try {
             CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssignmentExitEntryDate(?,?,?,?)}");
             cs.setInt(1, a.getId());
             cs.setString(2, a.getExitDate());
             cs.setString(3, a.getEntryDate());
             cs.registerOutParameter(4, Types.INTEGER);
             cs.execute();
             update = cs.getInt(4);
             cs.close();
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
         return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
     }
      
     public ResponseModel dateById(int id) {
        ActiveFixedAssigmentEntity a = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedAssignmentUpdateExitEntry(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setEntryDate(rs.getString("entryDate"));
                a.setExitDate(rs.getString("exitDate"));
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
}
