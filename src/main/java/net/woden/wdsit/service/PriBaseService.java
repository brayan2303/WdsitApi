package net.woden.wdsit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PriBaseEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PriBaseService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(String customer, MultipartFile file) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        File directory = new File(this.en.getProperty("impresion.bases.url") + "\\" + customer);
        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        try {
            File f = new File(directory+"\\"+customer + ".txt");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriBaseCreate(?,?)}");
            cs.setString(1, customer);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel delete(String customer) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriBaseDelete(?)}");
            cs.setString(1, customer);
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

    public ResponseModel findSerial(String serial, String customer) {
        PriBaseEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriBaseFindSerial(?,?)}");
            cs.setString(1, serial);
            cs.setString(2, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PriBaseEntity();
                p.setSerial(rs.getString("serial"));
                p.setStatus(rs.getString("status"));
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK_BASE", p, 200);
    }

    public ResponseModel list(String customer) {
        ArrayList<PriBaseEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriBaseList(?)}");
            cs.setString(1, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriBaseEntity i = new PriBaseEntity();
                i.setSerial(rs.getString("serial"));
                i.setStatus(rs.getString("status"));
                list.add(i);
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
