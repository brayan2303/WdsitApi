package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscWorkPlanEntity;
import net.woden.wdsit.model.BscWorkPlanModel;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscWorkPlanService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscWorkPlanEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanCreate(?,?,?,?)}");
            cs.setString(1,b.getName());
            cs.setInt(2,b.getStrategicObjetiveId());
            cs.setInt(3,b.getCreationUserId());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
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
    public ResponseModel update(BscWorkPlanEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getName());
            cs.setInt(3,b.getStrategicObjetiveId());
            cs.setInt(4,b.getCreationUserId());
            cs.setBoolean(5,b.isActive());
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
    public ResponseModel delete(int workPlanId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanDelete(?)}");
            cs.setInt(1,workPlanId);
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
    public ResponseModel list(int strategicObjetiveId) {
        ArrayList<BscWorkPlanEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanList(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscWorkPlanEntity b=new BscWorkPlanEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setYear(rs.getInt("year"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setCreationUserId(rs.getInt("creationUserId"));
                b.setCreationUser(rs.getString("creationUser"));
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
    public ResponseModel listActive(int strategicObjetiveId) {
        ArrayList<BscWorkPlanEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanListActive(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscWorkPlanEntity b=new BscWorkPlanEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setYear(rs.getInt("year"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setCreationUserId(rs.getInt("creationUserId"));
                b.setCreationUser(rs.getString("creationUser"));
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
    public ResponseModel total(int strategicObjetiveId) {
        ArrayList<BscWorkPlanModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanTotal(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscWorkPlanModel b=new BscWorkPlanModel();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setActivities(rs.getInt("activities"));
                b.setAdvances(rs.getInt("advances"));
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
    public ResponseModel percentage(int strategicObjetiveId) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanPercentage(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    public ResponseModel percentageAdvances(int workPlanId) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscWorkPlanPercentageAdvances(?)}");
            cs.setInt(1, workPlanId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
}
