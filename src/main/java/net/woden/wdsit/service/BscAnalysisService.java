package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscAnalysisEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscAnalysisService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscAnalysisEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisCreate(?,?,?)}");
            cs.setInt(1,b.getMeasurementDetailId());
            cs.setString(2,b.getAnalysis());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(3);
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
    public ResponseModel update(BscAnalysisEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisUpdate(?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getAnalysis());
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
    public ResponseModel delete(int analysisId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisDelete(?)}");
            cs.setInt(1,analysisId);
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
    public ResponseModel list(int measurementDetailId) {
        ArrayList<BscAnalysisEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisList(?)}");
            cs.setInt(1, measurementDetailId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscAnalysisEntity b=new BscAnalysisEntity();
                b.setId(rs.getInt("id"));
                b.setMeasurementDetailId(rs.getInt("measurementDetailId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicator(rs.getString("indicator"));
                b.setMonth(rs.getString("month"));
                b.setActionPlanId(rs.getInt("actionPlanId"));
                b.setActionPlan(rs.getString("actionPlan"));
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
    public ResponseModel listMonth(int measurementId,int monthId) {
        ArrayList<BscAnalysisEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisListMonth(?,?)}");
            cs.setInt(1, measurementId);
            cs.setInt(2,monthId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscAnalysisEntity b=new BscAnalysisEntity();
                b.setId(rs.getInt("id"));
                b.setAnalysis(rs.getString("analysis"));
                b.setMeasurementDetailId(rs.getInt("measurementDetailId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicator(rs.getString("indicator"));
                b.setMonth(rs.getString("month"));
                b.setActionPlanId(rs.getInt("actionPlanId"));
                b.setActionPlan(rs.getString("actionPlan"));
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
    public ResponseModel findById(int analysisId) {
        BscAnalysisEntity b=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscAnalysisFindById(?)}");
            cs.setInt(1, analysisId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new BscAnalysisEntity();
                b.setId(rs.getInt("id"));
                b.setMeasurementDetailId(rs.getInt("measurementDetailId"));
                b.setAnalysis(rs.getString("analysis"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicator(rs.getString("indicator"));
                b.setMonth(rs.getString("month"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }
}
