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
import net.woden.wdsit.entity.ProIndicatorEntity;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.DataMultiModel;
import net.woden.wdsit.model.DataSingleModel;
import net.woden.wdsit.model.ProPerspectiveModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProIndicatorService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProIndicatorEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorCreate(?,?,?,?,?)}");
            cs.setString(1, b.getName());
            cs.setDouble(2, b.getPercentage());
            cs.setString(3, b.getDirection());
            cs.setInt(4, b.getStrategicObjetiveId());
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

    public ResponseModel update(ProIndicatorEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, b.getId());
            cs.setString(2, b.getName());
            cs.setDouble(3, b.getPercentage());
            cs.setString(4, b.getDirection());
            cs.setInt(5, b.getStrategicObjetiveId());
            cs.setBoolean(6, b.isActive());
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

    public ResponseModel delete(int indicatorId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorDelete(?)}");
            cs.setInt(1, indicatorId);
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

    public ResponseModel list(int strategicObjetiveId) {
        ArrayList<ProIndicatorEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorList(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProIndicatorEntity b = new ProIndicatorEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPercentage(rs.getDouble("percentage"));
                b.setDirection(rs.getString("direction"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
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

    public ResponseModel listActive(int strategicObjetiveId) {
        ArrayList<ProIndicatorEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorListActive(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProIndicatorEntity b = new ProIndicatorEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPercentage(rs.getDouble("percentage"));
                b.setDirection(rs.getString("direction"));
                b.setStrategicObjetiveId(rs.getInt("strategicObjetiveId"));
                b.setStrategicObjetive(rs.getString("strategicObjetive"));
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

    public ResponseModel percentage(int strateticObjetiveId) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorPercentage(?)}");
            cs.setInt(1, strateticObjetiveId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }

    public ResponseModel percentageMonth(int indicatorId) {
        ArrayList<DataMultiModel> list = new ArrayList();
        DataMultiModel data;
        ArrayList<DataSingleModel> dataList=null;
        int id1, id2 = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorPercentageMonth(?)}");
            cs.setInt(1, indicatorId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                id1 = rs.getInt("id");
                if (id2 == 0) {
                    data = new DataMultiModel();
                    dataList = new ArrayList();
                    DataSingleModel d = new DataSingleModel();
                    d.setX(rs.getString("dataX"));
                    d.setY(rs.getDouble("dataY"));
                    dataList.add(d);
                    data.setData(dataList);
                    list.add(data);
                    id2 = rs.getInt("id");
                } else {
                    if (id1 == id2) {
                        DataSingleModel d = new DataSingleModel();
                        d.setX(rs.getString("dataX"));
                        d.setY(rs.getDouble("dataY"));
                        dataList.add(d);
                        id2 = rs.getInt("id");
                    } else {
                        data = new DataMultiModel();
                        dataList = new ArrayList();
                        DataSingleModel d = new DataSingleModel();
                        d.setX(rs.getString("dataX"));
                        d.setY(rs.getDouble("dataY"));
                        dataList.add(d);
                        data.setData(dataList);
                        list.add(data);
                        id2 = rs.getInt("id");
                    }
                }
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
        ArrayList<ProPerspectiveModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProIndicatorTotal(?)}");
            cs.setInt(1, strategicObjetiveId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProPerspectiveModel b = new ProPerspectiveModel();
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
