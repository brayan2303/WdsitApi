/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpCertificateEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpCertificateService {
    @Autowired
    private DataSourceConnection ds;
    
        public ResponseModel create(ScpCertificateEntity s) {
        int insert = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCertificateCreate(?,?,?,?)}");
            cs.setString(1, s.getName());
            cs.setString(2, s.getDescription());
            cs.setInt(3, s.getUserId());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(4);
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
        public ResponseModel list() {
        ArrayList<ScpCertificateEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCertificateList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpCertificateEntity s = new ScpCertificateEntity();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                s.setUpdateUserId(rs.getInt("updateUserId"));
                s.setUpdateDate(rs.getString("updateDate"));
                s.setActive(rs.getBoolean("active"));
                list.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
        
        public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCertificateDelete(?)}");
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
        ScpCertificateEntity s = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ScpCertificateFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpCertificateEntity();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                s.setUpdateUserId(rs.getInt("updateUserId"));
                s.setUpdateDate(rs.getString("updateDate"));
                s.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), s == null ? "Registro no encontrado" : "OK", s, 200);
    }
          public ResponseModel update(ScpCertificateEntity s) {     //crear y almacenar info
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCertificateUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, s.getId());
            cs.setString(2, s.getName());
            cs.setString(3, s.getDescription());
            cs.setInt(4, s.getUpdateUserId());
            cs.setBoolean(5, s.isActive());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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
