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
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.RepFlagLogEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class RepFlagLogService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(RepFlagLogEntity r) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_RepFlagLogCreate(?,?,?)}");
            cs.setInt(1, r.getUserId());
            cs.setInt(2, r.getReportId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel update(RepFlagLogEntity r) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_RepFlagLogUpdate(?,?,?)}");
            cs.setInt(1, r.getUserId());
            cs.setInt(2, r.getReportId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

    public ResponseModel listValidation(int reportId, int userId) {
        RepFlagLogEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepFlagLogValidation(?,?)}");
            cs.setInt(1, reportId);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new RepFlagLogEntity();
                r.setValidacionReport(rs.getString("validacionReport"));
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
        return new ResponseModel(getClass().getSimpleName(), r == null ? "Registro no encontrado" : "OK", r, 200);
    }
    
        public ResponseModel listValidationTime(int reportId, int userId) {
        RepFlagLogEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepFlagLogTimeReport(?,?)}");
            cs.setInt(1, reportId);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new RepFlagLogEntity();
                r.setTimes(rs.getString("times"));
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
        return new ResponseModel(getClass().getSimpleName(), r == null ? "Registro no encontrado" : "OK", r, 200);
    }
}
