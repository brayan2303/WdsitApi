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
import net.woden.wdsit.entity.ProActivityEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProActivityService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProActivityEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActivityCreate(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,b.getName());
            cs.setString(2,b.getDescription());
            cs.setDouble(3,b.getPercentage());
            cs.setString(4,b.getDeliverables());
            cs.setDate(5, java.sql.Date.valueOf(b.getDeliverDate()));
            cs.setInt(6,b.getWorkPlanId());
            cs.setInt(7,b.getResponsibleUserId());
            cs.setInt(8,b.getCreationUserId());
            cs.registerOutParameter(9, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(9);
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
    public ResponseModel update(ProActivityEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActivityUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getName());
            cs.setString(3,b.getDescription());
            cs.setDouble(4,b.getPercentage());
            cs.setString(5,b.getDeliverables());
            cs.setDate(6, java.sql.Date.valueOf(b.getDeliverDate()));
            cs.setInt(7,b.getWorkPlanId());
            cs.setInt(8,b.getResponsibleUserId());
            cs.setInt(9,b.getCreationUserId());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(10);
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
    public ResponseModel delete(int activityId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActivityDelete(?)}");
            cs.setInt(1,activityId);
            deletes=cs.executeUpdate();
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
    public ResponseModel openClose(int activityId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActivityOpenClose(?,?,?)}");
            cs.setInt(1,activityId);
            cs.setString(2,status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(3);
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
    public ResponseModel list(int workPlanId) {
        ArrayList<ProActivityEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProActivityList(?)}");
            cs.setInt(1, workPlanId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ProActivityEntity b=new ProActivityEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setDescription(rs.getString("description"));
                b.setPercentage(rs.getDouble("percentage"));
                b.setDeliverables(rs.getString("deliverables"));
                b.setDeliverDate(rs.getString("deliverDate"));
                b.setYear(rs.getInt("year"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setWorkPlanId(rs.getInt("workPlanId"));
                b.setWorkPlan(rs.getString("workPlan"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
                b.setCreationUserId(rs.getInt("creationUserId"));
                b.setCreationUser(rs.getString("creationUser"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                list.add(b);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
}
