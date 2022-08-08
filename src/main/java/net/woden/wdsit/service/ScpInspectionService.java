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
import net.woden.wdsit.entity.ScpInspectionEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpInspectionService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ScpInspectionEntity s, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {

            CallableStatement cs = cn.prepareCall("{call sp_ScpInspectionCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, s.getCheckList());
            cs.setString(2, s.getDescription());
            cs.setString(3, s.getSampling());
            cs.setString(4, s.getQty());
            cs.setString(5, s.getRejectionThreshold());
            cs.setInt(6, s.getAuditId());
            cs.setInt(7, userId);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(8);
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

    public ResponseModel update(ScpInspectionEntity s, int userIdupdate) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {

            CallableStatement cs = cn.prepareCall("{call sp_ScpInspectionUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, s.getId());
            cs.setString(2, s.getCheckList());
            cs.setString(3, s.getDescription());
            cs.setString(4, s.getSampling());
            cs.setString(5, s.getQty());
            cs.setString(6, s.getRejectionThreshold());
            cs.setInt(7, userIdupdate);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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

    public ResponseModel list(int auditId) {
        ArrayList<ScpInspectionEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpInspectionList(?)}");
            cs.setInt(1, auditId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpInspectionEntity s = new ScpInspectionEntity();
                s.setCheckList(rs.getString("checkList"));
                s.setDescription(rs.getString("description"));
                s.setSampling(rs.getString("sampling"));
                s.setCreationDate(rs.getString("creationDate"));
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

    public ResponseModel findById(int id) {
        ScpInspectionEntity s = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpInspectionList(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpInspectionEntity();
                s.setCheckList(rs.getString("checkList"));
                s.setDescription(rs.getString("description"));
                s.setSampling(rs.getString("sampling"));
                s.setCreationDate(rs.getString("creationDate"));
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

        return new ResponseModel(getClass().getSimpleName(), "OK", s, 200);
    }

    public ResponseModel delete(int id) { //eliminar info
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
