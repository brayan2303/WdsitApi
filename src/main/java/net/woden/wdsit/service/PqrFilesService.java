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
import net.woden.wdsit.entity.PqrFilesEnity;
import net.woden.wdsit.model.PqrFilesModel;
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
public class PqrFilesService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(PqrFilesEnity p, int countryId) {
        int insert = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesCreate(?,?,?,?,?,?)}");
            cs.setString(1, p.getName());
            cs.setString(2, p.getTypeClient());
            cs.setString(3, p.getDescription());
            cs.setInt(4, p.getCategoryId());
            cs.setInt(5, countryId);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(6);
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

    public ResponseModel update(PqrFilesEnity p) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getName());
            cs.setString(3, p.getTypeClient());
            cs.setString(4, p.getDescription());
            cs.setInt(5, p.getCategoryId());
            cs.setBoolean(6, p.getActive());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            update = cs.getInt(7);
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesDelete(?)}");
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

    public ResponseModel list(int countryId) {
        ArrayList<PqrFilesEnity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesList(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrFilesEnity p = new PqrFilesEnity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setTypeClient(rs.getString("typeClient"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("categoryId"));
                p.setActive(rs.getBoolean("active"));
                p.setCategory(rs.getString("category"));      
                list.add(p);
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

    public ResponseModel findById(int id) {
        PqrFilesEnity p = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrFilesEnity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setTypeClient(rs.getString("typeClient"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("categoryId"));          
                p.setActive(rs.getBoolean("active"));
                p.setCategory(rs.getString("category"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }

    public ResponseModel findAll(String name, int countryId) {
        ArrayList<PqrFilesEnity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesFindAll(?,?)}");
            cs.setString(1, name);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrFilesEnity p = new PqrFilesEnity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("categoryId"));
                p.setCategory(rs.getString("category"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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

    public ResponseModel findAllCustomer(String name, int countryId) {
        ArrayList<PqrFilesEnity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFilesFindAllCustomer(?,?)}");
            cs.setString(1, name);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrFilesEnity p = new PqrFilesEnity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCategoryId(rs.getInt("categoryId"));
                p.setCategory(rs.getString("category"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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

    public ResponseModel loadFile(String name, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("pqrs.apoyo.url") + "\\" + name);
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

    public ResponseModel listFile(String name) {
        ArrayList<PqrFilesModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("pqrs.apoyo.url") + "\\" + name);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    PqrFilesModel p = new PqrFilesModel();
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

}
