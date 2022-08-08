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
import net.woden.wdsit.entity.LoadClientRuleInitEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientRuleInitService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadClientRuleInitEntity l) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRuleInitCreate(?,?,?,?)}");
            cs.setString(1, l.getName());
            cs.setString(2, l.getDescription());
            cs.setInt(3, l.getCustomerId());
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

    public ResponseModel update(LoadClientRuleInitEntity l) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRuleInitUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, l.getId());
            cs.setString(2, l.getName());
            cs.setString(3, l.getDescription());
            cs.setInt(4, l.getCustomerId());
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

    public ResponseModel list() {
        ArrayList<LoadClientRuleInitEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRuleInitList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientRuleInitEntity l = new LoadClientRuleInitEntity();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setDescription(rs.getString("description"));
                l.setCustomerId(rs.getInt("customerId"));
                l.setParameterization(rs.getString("parameterization"));
                l.setParameterizationId(rs.getInt("parameterizationId"));
                l.setActive(rs.getBoolean("active"));
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
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRuleInitDelete(?)}");
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
        LoadClientRuleInitEntity l = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRuleInitFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientRuleInitEntity();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                l.setDescription(rs.getString("description"));
                l.setCustomerId(rs.getInt("customerId"));
                l.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

}
