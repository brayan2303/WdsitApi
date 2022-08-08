package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.TasTaskEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasTaskService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(TasTaskEntity t) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1,t.getTitle());
            cs.setString(2,t.getPriority());
            cs.setString(3,t.getStatus());
            cs.setString(4,t.getType());
            cs.setDate(5, java.sql.Date.valueOf(t.getStartDate()));
            cs.setInt(6,t.getRequestPersonId());
            cs.setInt(7,t.getAssignedPersonId());
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
    public ResponseModel update(TasTaskEntity t) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,t.getId());
            cs.setString(2,t.getTitle());
            cs.setString(3,t.getPriority());
            cs.setString(4,t.getStatus());
            cs.setString(5,t.getType());
            cs.setDate(6, java.sql.Date.valueOf(t.getStartDate()));
            cs.setDate(7, java.sql.Date.valueOf(t.getEndDate()));
            cs.setInt(8,t.getRequestPersonId());
            cs.setInt(9,t.getAssignedPersonId());
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
    public ResponseModel delete(int taskId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskDelete(?)}");
            cs.setInt(1,taskId);
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
    public ResponseModel close(int taskId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskClose(?,?)}");
            cs.setInt(1,taskId);
            cs.registerOutParameter(2,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(2);
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
    public ResponseModel findById(int taskId) {
        TasTaskEntity t=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskFindById(?)}");
            cs.setInt(1,taskId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                t=new TasTaskEntity();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setPriority(rs.getString("priority"));
                t.setStatus(rs.getString("status"));
                t.setType(rs.getString("type"));
                t.setStartDate(rs.getString("startDate"));
                t.setEndDate(rs.getString("endDate"));
                t.setRequestPersonId(rs.getInt("requestPersonId"));
                t.setAssignedPersonId(rs.getInt("assignedPersonId"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", t, 200);
    }
    public ResponseModel list(int assignedPersonId) {
        ArrayList<TasTaskEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskList(?)}");
            cs.setInt(1,assignedPersonId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                TasTaskEntity t=new TasTaskEntity();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setPriority(rs.getString("priority"));
                t.setStatus(rs.getString("status"));
                t.setType(rs.getString("type"));
                t.setStartDate(rs.getString("startDate"));
                t.setEndDate(rs.getString("endDate"));
                t.setRequestPersonId(rs.getInt("requestPersonId"));
                t.setRequestPerson(rs.getString("requestPerson"));
                t.setAssignedPersonId(rs.getInt("assignedPersonId"));
                t.setAssignedPerson(rs.getString("assignedPerson"));
                list.add(t);
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
    
        public ResponseModel listAll() {
        ArrayList<TasTaskEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TasTaskListAll()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                TasTaskEntity t=new TasTaskEntity();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setPriority(rs.getString("priority"));
                t.setStatus(rs.getString("status"));
                t.setType(rs.getString("type"));
                t.setStartDate(rs.getString("startDate"));
                t.setEndDate(rs.getString("endDate"));
                t.setRequestPersonId(rs.getInt("requestPersonId"));
                t.setRequestPerson(rs.getString("requestPerson"));
                t.setAssignedPersonId(rs.getInt("assignedPersonId"));
                t.setAssignedPerson(rs.getString("assignedPerson"));
                list.add(t);
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
