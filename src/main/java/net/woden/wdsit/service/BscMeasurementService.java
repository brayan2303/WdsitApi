package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscMeasurementEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscMeasurementService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscMeasurementEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1,b.getProyectPlan());
            cs.setInt(2,b.getIndicatorId());
            cs.setInt(3,b.getFormulaId());
            cs.setInt(4,b.getFrecuencyId());
            cs.setString(5,b.getGoalType());
            cs.setDouble(6,b.getGoal());
            cs.setInt(7,b.getResponsibleUserId());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(8);
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
    public ResponseModel update(BscMeasurementEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getProyectPlan());
            cs.setInt(3,b.getIndicatorId());
            cs.setInt(4,b.getFormulaId());
            cs.setInt(5,b.getFrecuencyId());
            cs.setString(6,b.getGoalType());
            cs.setDouble(7,b.getGoal());
            cs.setInt(8,b.getResponsibleUserId());
            cs.setBoolean(9,b.isActive());
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
    public ResponseModel delete(int measurementId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementDelete(?)}");
            cs.setInt(1,measurementId);
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
    public ResponseModel list(int indicatorId,int personId) {
        ArrayList<BscMeasurementEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementList(?,?)}");
            cs.setInt(1, indicatorId);
            cs.setInt(2, personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscMeasurementEntity b=new BscMeasurementEntity();
                b.setId(rs.getInt("id"));
                b.setProyectPlan(rs.getString("proyectPlan"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicatorId(rs.getInt("indicatorId"));
                b.setIndicator(rs.getString("indicator"));
                b.setDirection(rs.getString("direction"));
                b.setFormulaId(rs.getInt("formulaId"));
                b.setFormula(rs.getString("formula"));
                b.setFrecuencyId(rs.getInt("frecuencyId"));
                b.setFrecuency(rs.getString("frecuency"));
                b.setYear(rs.getInt("year"));
                b.setGoalType(rs.getString("goalType"));
                b.setGoal(rs.getDouble("goal"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
                b.setLeaderId(rs.getInt("leaderId"));
                b.setActive(rs.getBoolean("active"));
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
    public ResponseModel listActive(int indicatorId,int personId) {
        ArrayList<BscMeasurementEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMeasurementListActive(?,?)}");
            cs.setInt(1,indicatorId);
            cs.setInt(2, personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscMeasurementEntity b=new BscMeasurementEntity();
                b.setId(rs.getInt("id"));
                b.setProyectPlan(rs.getString("proyectPlan"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setIndicatorId(rs.getInt("indicatorId"));
                b.setIndicator(rs.getString("indicator"));
                b.setFormulaId(rs.getInt("formulaId"));
                b.setFormula(rs.getString("formula"));
                b.setFrecuencyId(rs.getInt("frecuencyId"));
                b.setFrecuency(rs.getString("frecuency"));
                b.setYear(rs.getInt("year"));
                b.setGoalType(rs.getString("goalType"));
                b.setGoal(rs.getDouble("goal"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
                b.setLeaderId(rs.getInt("leaderId"));
                b.setActive(rs.getBoolean("active"));
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
