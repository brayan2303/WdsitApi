/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpFirmImageEntity;
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
public class ScpFirmImageService {

    @Autowired
    private Environment en;

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel loadFile(int userId, String name, MultipartFile[] files) throws IOException {
        int inserts = 0;
        int load = 0;
        Connection cn = this.ds.openConnection();
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("scrap.firma.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File archivo = new File(directory1 + "\\" + userId + ".jpg");

        for (MultipartFile file : files) {
            try {
                bi = ImageIO.read(file.getInputStream());
                ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + userId + ".jpg"));

                load = 1;
            } catch (IOException ex) {
                load = 0;
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load == 1) {
            try {

                CallableStatement cs = cn.prepareCall("{call sp_ScpFirmImageCreate(?,?)}");
                cs.setInt(1, userId);
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(2);
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

    public ResponseModel list() {
        ArrayList<ScpFirmImageEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpFirmImageList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpFirmImageEntity s = new ScpFirmImageEntity();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setImage(rs.getString("image"));
                s.setFirm(rs.getString("firm"));
                lists.add(s);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialDelete(?)}");
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

}
