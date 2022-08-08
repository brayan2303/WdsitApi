/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.LoadClientStarParameterizationEntity;
import net.woden.wdsit.model.LoadClientStarParameterizationModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientStarParameterizationService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadClientStarParameterizationEntity l, int userId) {
        int insert = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationCreate(?,?,?,?)}");
            cs.setString(1, l.getDescription());
            cs.setInt(2, l.getCustomerId());
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(4);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", insert, 200);
    }

    public ResponseModel list() {
        ArrayList<LoadClientStarParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParametrizationList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientStarParameterizationEntity l = new LoadClientStarParameterizationEntity();
                l.setId(rs.getInt("id"));
                l.setDescription(rs.getString("description"));
                l.setCustomerId(rs.getInt("customerId"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setUpdateDate(rs.getString("updateDate"));
                l.setActive(rs.getBoolean("active"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
                l.setCustomer(rs.getString("customer"));
                l.setName(rs.getString("name"));
                list.add(l);
            }
            rs.close();
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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

    public ResponseModel update(LoadClientStarParameterizationEntity l, int updateUserId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, l.getId());
            cs.setString(2, l.getDescription());
            cs.setInt(3, l.getCustomerId());
            cs.setInt(4, updateUserId);
            cs.setBoolean(5, l.getActive());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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

    public ResponseModel findById(int id) {
        LoadClientStarParameterizationEntity l = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientStarParameterizationEntity();
                l.setId(rs.getInt("id"));
                l.setDescription(rs.getString("description"));
                l.setCustomerId(rs.getInt("customerId"));
                l.setActive(rs.getBoolean("active"));
                l.setUpdateDate(rs.getString("updateDate"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setUserId(rs.getInt("userId"));
                l.setUpdateUserId(rs.getInt("updateUserId"));
            }
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }
    public ResponseModel listCustomer(){
    ArrayList<LoadClientStarParameterizationModel> listCustomer = new ArrayList();
    Connection cn = this.ds.openConnection();
    
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientStarParameterizationCustomers()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            LoadClientStarParameterizationModel l = new LoadClientStarParameterizationModel();
            l.setId(rs.getInt("id"));
            l.setCode(rs.getString("code"));
            l.setDescription(rs.getString("description"));
            l.setActive(rs.getBoolean("active"));
            listCustomer.add(l);
            }
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listCustomer, 200);
    }
}
