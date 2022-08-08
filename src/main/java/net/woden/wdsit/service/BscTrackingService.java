package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscTrackingEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscTrackingService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscTrackingEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscTrackingCreate(?,?,?,?,?)}");
            cs.setString(1, b.getDescription());
            cs.setDate(2, java.sql.Date.valueOf(b.getStartDate()));
            cs.setInt(3,b.getActionPlanId());
            cs.setInt(4,b.getCreationUserId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel update(BscTrackingEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscTrackingUpdate(?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2, b.getDescription());
            cs.setDate(3, java.sql.Date.valueOf(b.getStartDate()));
            cs.setInt(4,b.getActionPlanId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
    public ResponseModel approveReject(int trackingId,String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscTrackingApproveReject(?,?,?)}");
            cs.setInt(1,trackingId);
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
    public ResponseModel delete(int trackingId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscTrackingDelete(?)}");
            cs.setInt(1, trackingId);
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
    public ResponseModel list(int actionPlanId) {
        ArrayList<BscTrackingEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscTrackingList(?)}");
            cs.setInt(1, actionPlanId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscTrackingEntity b=new BscTrackingEntity();
                b.setId(rs.getInt("id"));
                b.setDescription(rs.getString("description"));
                b.setStartDate(rs.getString("startDate"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                b.setActionPlanId(rs.getInt("actionPlanId"));
                b.setActionPlan(rs.getString("actionPlan"));
                b.setCreationUserId(rs.getInt("creationUserId"));
                b.setCreationUser(rs.getString("creationUser"));
                b.setAnalysisId(rs.getInt("analysisId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setMonthId(rs.getInt("monthId"));
                b.setMonth(rs.getString("month"));
                b.setMeasurementId(rs.getInt("measurementId"));
                b.setMeasurement(rs.getString("measurement"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicatorId(rs.getInt("indicatorId"));
                b.setIndicator(rs.getString("indicator"));
                b.setYear(rs.getInt("year"));
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
