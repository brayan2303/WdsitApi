/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
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
import net.woden.wdsit.entity.ReceptionHomeEntity;
import net.woden.wdsit.entity.ReceptionMasterEntity;
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
public class ReceptionHomeService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(ReceptionHomeEntity r, int userId) {
        int insert = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, r.getTypeDocument());
            cs.setString(2, r.getIdentification());
            cs.setString(3, r.getName());
            cs.setString(4, r.getLastName());
            cs.setString(5, r.getPhone());
            cs.setString(6, r.getEmail());
            cs.setString(7, r.getEntity());
            cs.setString(8, r.getEps());
            cs.setString(9, r.getArl());
            cs.setString(10, r.getVisit());
            cs.setString(11, r.getLicense());
            cs.setString(12, r.getTypeVisit());
            cs.setString(13, r.getTeam());
            cs.setString(14, r.getBrand());
            cs.setString(15, r.getSerial());
            cs.setString(16, r.getImage());
            cs.setInt(17, userId);
            cs.registerOutParameter(18, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(18);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", insert, 200);
    }
    
        public ResponseModel createExit(ReceptionHomeEntity r, int userId) {
        int insert = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeCreateExit(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, r.getTypeDocument());
            cs.setString(2, r.getIdentification());
            cs.setString(3, r.getName());
            cs.setString(4, r.getLastName());
            cs.setString(5, r.getPhone());
            cs.setString(6, r.getEmail());
            cs.setString(7, r.getEntity());
            cs.setString(8, r.getEps());
            cs.setString(9, r.getArl());
            cs.setString(10, r.getVisit());
            cs.setString(11, r.getLicense());
            cs.setString(12, r.getTypeVisit());
            cs.setString(13, r.getTeam());
            cs.setString(14, r.getBrand());
            cs.setString(15, r.getSerial());
            cs.setString(16, r.getImage());
            cs.setInt(17, userId);
            cs.registerOutParameter(18, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(18);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", insert, 200);
    }

    public ResponseModel update(ReceptionHomeEntity r, int userId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, r.getId());
            cs.setString(2, r.getTypeDocument());
            cs.setString(3, r.getIdentification());
            cs.setString(4, r.getName());
            cs.setString(5, r.getLastName());
            cs.setString(6, r.getPhone());
            cs.setString(7, r.getEmail());
            cs.setString(8, r.getEntity());
            cs.setString(9, r.getEps());
            cs.setString(10, r.getArl());
            cs.setString(11, r.getVisit());
            cs.setString(12, r.getLicense());
            cs.setString(13, r.getTypeVisit());
            cs.setString(14, r.getTeam());
            cs.setString(15, r.getBrand());
            cs.setString(16, r.getSerial());
            cs.setInt(17, userId);
            cs.registerOutParameter(18, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(18);
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
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeDelete(?)}");
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

    public ResponseModel list() {
        ArrayList<ReceptionHomeEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReceptionHomeEntity r = new ReceptionHomeEntity();
                r.setId(rs.getInt("id"));
                r.setTypeDocument(rs.getString("typeDocument"));
                r.setIdentification(rs.getString("identification"));
                r.setName(rs.getString("name"));
                r.setLastName(rs.getString("lastName"));
                r.setPhone(rs.getString("phone"));
                r.setEmail(rs.getString("email"));
                r.setEntity(rs.getString("entity"));
                r.setEps(rs.getString("eps"));
                r.setArl(rs.getString("arl"));
                r.setVisit(rs.getString("visit"));
                r.setLicense(rs.getString("license"));
                r.setTypeVisit(rs.getString("typeVisit"));
                r.setTeam(rs.getString("team"));
                r.setBrand(rs.getString("brand"));
                r.setSerial(rs.getString("serial"));
                r.setCreationDate(rs.getString("creationDate"));
                r.setUserId(rs.getInt("userId"));
                r.setUserIdUpdate(rs.getInt("UserIdUpdate"));
                r.setUpdateDate(rs.getString("updateDate"));
                r.setUserCreation(rs.getString("userCreation"));
                r.setUserUpdate(rs.getString("userUpdate"));
                lists.add(r);
            }
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel findById(int id) {
        ReceptionHomeEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionHomeEntity();
                r.setId(rs.getInt("id"));
                r.setTypeDocument(rs.getString("typeDocument"));
                r.setIdentification(rs.getString("identification"));
                r.setName(rs.getString("name"));
                r.setLastName(rs.getString("lastName"));
                r.setPhone(rs.getString("phone"));
                r.setEmail(rs.getString("email"));
                r.setEntity(rs.getString("entity"));
                r.setEps(rs.getString("eps"));
                r.setArl(rs.getString("arl"));
                r.setVisit(rs.getString("visit"));
                r.setLicense(rs.getString("license"));
                r.setTypeVisit(rs.getString("typeVisit"));
                r.setTeam(rs.getString("team"));
                r.setBrand(rs.getString("brand"));
                r.setSerial(rs.getString("serial"));
            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }

    public ResponseModel findByIdentification(String identification) {
        ReceptionHomeEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeFindByIdentification(?)}");
            cs.setString(1, identification);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionHomeEntity();
                r.setId(rs.getInt("id"));
                r.setTypeDocument(rs.getString("typeDocument"));
                r.setIdentification(rs.getString("identification"));
                r.setName(rs.getString("name"));
                r.setLastName(rs.getString("lastName"));
                r.setPhone(rs.getString("phone"));
                r.setEmail(rs.getString("email"));
                r.setEntity(rs.getString("entity"));
                r.setEps(rs.getString("eps"));
                r.setArl(rs.getString("arl"));
                r.setVisit(rs.getString("visit"));
                r.setLicense(rs.getString("license"));
                r.setTypeVisit(rs.getString("typeVisit"));
                r.setTeam(rs.getString("team"));
                r.setBrand(rs.getString("brand"));
                r.setSerial(rs.getString("serial"));
                r.setImage(rs.getString("image"));
                r.setActive(rs.getBoolean("active"));
            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }

    public ResponseModel findByIdentificationTicket(String identification) {
        ReceptionHomeEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionHomeIdentificationTicket(?)}");
            cs.setString(1, identification);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionHomeEntity();
                r.setIdentification(rs.getString("identification"));
                r.setName(rs.getString("name"));
                r.setLastName(rs.getString("lastName"));
                r.setEps(rs.getString("eps"));
                r.setArl(rs.getString("arl"));
                r.setVisit(rs.getString("visit"));
                r.setTypeVisit(rs.getString("typeVisit"));
                r.setImage(rs.getString("image"));
            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }

    public ResponseModel loadFile(int identification, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("reception.register.url") + "\\" + identification);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
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

    public ResponseModel findType(int typeId) {
        ReceptionMasterEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterType(?)}");
            cs.setInt(1, typeId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionMasterEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setCreationDate(rs.getString("creationDate"));
                r.setReceptionMasterTypeId(rs.getInt("ReceptionMasterTypeId"));
                r.setActive(rs.getBoolean("active"));
                r.setType(rs.getString("type"));

            }
            rs.close();
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
    
        public ResponseModel findByIdentificationPerson(String identification) {
        ReceptionHomeEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionIdentification(?)}");
            cs.setString(1, identification);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionHomeEntity();
                r.setId(rs.getInt("id"));
                r.setIdentification(rs.getString("identification"));
                r.setName(rs.getString("name"));
                r.setLastName(rs.getString("lastName"));
                r.setEmail(rs.getString("email"));

            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }


}
