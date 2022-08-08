package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscActionEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscActionService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscActionEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionCreate(?,?,?,?,?,?)}");
            cs.setString(1,b.getName());
            cs.setString(2,b.getDeliverables());
            cs.setDate(3, java.sql.Date.valueOf(b.getEndDate()));
            cs.setInt(4,b.getActionPlanId());
            cs.setInt(5,b.getResponsibleUserId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(6);
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
    public ResponseModel update(BscActionEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getName());
            cs.setString(3,b.getDeliverables());
            cs.setDate(4, java.sql.Date.valueOf(b.getEndDate()));
            cs.setInt(5,b.getResponsibleUserId());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(6);
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
    public ResponseModel openClose(int actionId,boolean openClose) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionOpenClose(?,?,?)}");
            cs.setInt(1,actionId);
            cs.setBoolean(2,openClose);
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
    public ResponseModel delete(int actionId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionDelete(?)}");
            cs.setInt(1,actionId);
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
    public ResponseModel findById(int actionId) {
        BscActionEntity b=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionFindById(?)}");
            cs.setInt(1,actionId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new BscActionEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setDeliverables(rs.getString("deliverables"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setEndDate(rs.getString("endDate"));
                b.setActionPlanId(rs.getInt("actionPlanId"));
                b.setActionPlan(rs.getString("actionPlan"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
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
    public ResponseModel findOpen(int actionPlanId) {
        int count=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionFindOpen(?)}");
            cs.setInt(1,actionPlanId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                count=rs.getInt("cantidad");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", count, 200);
    }
    public ResponseModel list(int actionPlanId) {
        ArrayList<BscActionEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscActionList(?)}");
            cs.setInt(1,actionPlanId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscActionEntity b=new BscActionEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setDeliverables(rs.getString("deliverables"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setEndDate(rs.getString("endDate"));
                b.setActionPlanId(rs.getInt("actionPlanId"));
                b.setStatusId(rs.getInt("statusId"));
                b.setStatus(rs.getString("status"));
                b.setResponsibleUserId(rs.getInt("responsibleUserId"));
                b.setResponsibleUser(rs.getString("responsibleUser"));
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
