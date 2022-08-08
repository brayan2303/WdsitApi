/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ProActionPlanEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProActionPlanService {
    
       @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProActionPlanEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanCreate(?,?,?,?,?,?)}");
            cs.setString(1, b.getName());
            cs.setString(2, b.getObjetive());
            cs.setDate(3, java.sql.Date.valueOf(b.getEndDate()));
            cs.setInt(4, b.getAnalysisId());
            cs.setInt(5, b.getResponsibleUserId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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

    public ResponseModel update(ProActionPlanEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, b.getId());
            cs.setString(2, b.getName());
            cs.setString(3, b.getObjetive());
            cs.setDate(4, java.sql.Date.valueOf(b.getEndDate()));
            cs.setInt(5, b.getAnalysisId());
            cs.setInt(6, b.getResponsibleUserId());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel delete(int actionPlanId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanDelete(?)}");
            cs.setInt(1, actionPlanId);
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
    
    public ResponseModel openClose(int actionPlanId,boolean openClose) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanOpenClose(?,?,?)}");
            cs.setInt(1,actionPlanId);
            cs.setBoolean(2,openClose);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel approveReject(int actionPlanId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanApproveReject(?,?,?)}");
            cs.setInt(1,actionPlanId);
            cs.setString(2,status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }
    public ResponseModel findById(int actionPlanId) {
        ProActionPlanEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanFindById(?)}");
            cs.setInt(1, actionPlanId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ProActionPlanEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setObjetive(rs.getString("objetive"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setEndDate(rs.getString("endDate"));
                b.setAnalysisId(rs.getInt("analysisId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel list(int analysisId) {
        ArrayList<ProActionPlanEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActionPlanList(?)}");
            cs.setInt(1, analysisId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProActionPlanEntity b = new ProActionPlanEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setObjetive(rs.getString("objetive"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setEndDate(rs.getString("endDate"));
                b.setAnalysisId(rs.getInt("analysisId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                list.add(b);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
}
