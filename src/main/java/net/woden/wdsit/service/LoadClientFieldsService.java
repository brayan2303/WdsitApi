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
import net.woden.wdsit.entity.LoadClientFieldsEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientFieldsService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadClientFieldsEntity l, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsCreate(?,?,?,?,?)}");
            cs.setString(1, l.getName());
            cs.setString(2, l.getCodigo());
            cs.setInt(3, l.getParametrizationId());
            cs.setInt(4, userId);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel list(int id) {
        ArrayList<LoadClientFieldsEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsList(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientFieldsEntity l = new LoadClientFieldsEntity();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setCodigo(rs.getString("codigo"));
                l.setParametrizationId(rs.getInt("parametrizationId"));
                l.setActive(rs.getBoolean("active"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setUpdateDate(rs.getString("updateDate"));
                lists.add(l);
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsDelete(?)}");
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

    public ResponseModel update(LoadClientFieldsEntity l, int updateUserId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, l.getId());
            cs.setString(2, l.getName());
            cs.setString(3, l.getCodigo());
            cs.setInt(4, updateUserId);
            cs.setBoolean(5, l.isActive());
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

    public ResponseModel findById(int id) {
        LoadClientFieldsEntity l = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientFieldsEntity();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setCodigo(rs.getString("codigo"));
                l.setParametrizationId(rs.getInt("parametrizationId"));
                l.setActive(rs.getBoolean("active"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setUpdateDate(rs.getString("updateDate"));
            }
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

}
