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
import net.woden.wdsit.entity.ProStrategicObjetiveEntity;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.ProPerspectiveModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProStrategicObjetiveService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProStrategicObjetiveEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveCreate(?,?,?,?)}");
            cs.setString(1,b.getName());
            cs.setDouble(2,b.getPercentage());
            cs.setInt(3,b.getPerspectiveId());
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
    public ResponseModel update(ProStrategicObjetiveEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getName());
            cs.setDouble(3,b.getPercentage());
            cs.setInt(4,b.getPerspectiveId());
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
    public ResponseModel delete(int strategicObjetiveId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveDelete(?)}");
            cs.setInt(1,strategicObjetiveId);
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
    public ResponseModel list(int perspectiveId) {
        ArrayList<ProStrategicObjetiveEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveList(?)}");
            cs.setInt(1, perspectiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ProStrategicObjetiveEntity b=new ProStrategicObjetiveEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPercentage(rs.getDouble("percentage"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setYear(rs.getInt("year"));
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
    public ResponseModel listActive(int perspectiveId) {
        ArrayList<ProStrategicObjetiveEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveListActive(?)}");
            cs.setInt(1,perspectiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ProStrategicObjetiveEntity b=new ProStrategicObjetiveEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPercentage(rs.getDouble("percentage"));
                b.setPerspectiveId(rs.getInt("perspectiveId"));
                b.setPerspective(rs.getString("perspective"));
                b.setYear(rs.getInt("year"));
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
    public ResponseModel percentage(int perspectiveId) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetivePercentage(?)}");
            cs.setInt(1, perspectiveId);
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
    public ResponseModel percentageMonth(int strategicObjetiveId) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetivePercentageMonth(?)}");
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
    public ResponseModel total(int perspectiveId) {
        ArrayList<ProPerspectiveModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProStrategicObjetiveTotal(?)}");
            cs.setInt(1,perspectiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ProPerspectiveModel b=new ProPerspectiveModel();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setIndicators(rs.getInt("indicators"));
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
