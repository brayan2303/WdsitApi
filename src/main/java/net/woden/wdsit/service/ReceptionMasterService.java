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
import net.woden.wdsit.entity.ReceptionMasterEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ReceptionMasterService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ReceptionMasterEntity r, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterCreate(?,?,?,?)}");
            cs.setString(1, r.getName());
            cs.setInt(2, r.getReceptionMasterTypeId());
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

    public ResponseModel update(ReceptionMasterEntity r, int userId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterUpdate(?,?,?,?,?)}");
            cs.setInt(1, r.getId());
            cs.setString(2, r.getName());
            cs.setInt(3, r.getReceptionMasterTypeId());
            cs.setBoolean(4, r.isActive());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterDelete(?)}");
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
        ArrayList<ReceptionMasterEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReceptionMasterEntity r = new ReceptionMasterEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setCreationDate(rs.getString("creationDate"));
                r.setReceptionMasterTypeId(rs.getInt("ReceptionMasterTypeId"));
                r.setActive(rs.getBoolean("active"));
                r.setType(rs.getString("type"));
                list.add(r);
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

    public ResponseModel findById(int id) {
        ReceptionMasterEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReceptionMasterEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setCreationDate(rs.getString("creationDate"));
                r.setReceptionMasterTypeId(rs.getInt("ReceptionMasterTypeId"));
                r.setActive(rs.getBoolean("active"));
 

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

    public ResponseModel findByName(String type) {
        ArrayList<ReceptionMasterEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReceptionMasterFindByName(?)}");
            cs.setString(1, type);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReceptionMasterEntity r = new ReceptionMasterEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setCreationDate(rs.getString("creationDate"));
                r.setReceptionMasterTypeId(rs.getInt("ReceptionMasterTypeId"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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



}
