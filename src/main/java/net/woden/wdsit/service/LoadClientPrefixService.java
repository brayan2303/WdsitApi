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
import net.woden.wdsit.entity.LoadClientPrefixEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientPrefixService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadClientPrefixEntity l, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCreate(?,?,?,?)}");
            cs.setString(1, l.getPrefix());
            cs.setInt(2, l.getParametrizationId());
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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

    public ResponseModel list(int parametrizationId) {
        ArrayList<LoadClientPrefixEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixList(?)}");
            cs.setInt(1, parametrizationId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientPrefixEntity l = new LoadClientPrefixEntity();
                l.setId(rs.getInt("id"));
                l.setPrefix(rs.getString("prefix"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setActive(rs.getBoolean("active"));
                l.setParametrizationId(rs.getInt("parametrizationId"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
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
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel findById(int id) {
        LoadClientPrefixEntity l = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixFinbById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientPrefixEntity();
                l.setId(rs.getInt("id"));
                l.setPrefix(rs.getString("prefix"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setActive(rs.getBoolean("active"));
                l.setParametrizationId(rs.getInt("parametrizationId"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
                l.setUpdateDate(rs.getString("updateDate"));
            }
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }
    public ResponseModel update(LoadClientPrefixEntity l, int userId){
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixUpdate(?,?,?,?,?)}");
            cs.setInt(1, l.getId());
            cs.setString(2, l.getPrefix());
            cs.setInt(3, userId);
            cs.setBoolean(4, l.isActive());
                cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
         return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200); 
    }
}
