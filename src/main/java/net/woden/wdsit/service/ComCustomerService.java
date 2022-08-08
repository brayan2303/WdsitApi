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
import net.woden.wdsit.entity.ComCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ComCustomerService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(int userId,ComCustomerEntity c) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCustomerCreate(?,?,?,?)}");
            cs.setString(1, c.getProject());
            cs.setInt(2, c.getCustomerId());
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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
    
    public ResponseModel update(int comCustomerId, ComCustomerEntity c) {
        int update = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCustomerUpdate(?,?,?,?,?)}");
            cs.setInt(1, comCustomerId);
            cs.setString(2, c.getProject());
            cs.setInt(3, c.getCustomerId());
            cs.setBoolean(4, c.isActive());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            update = cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
    }
    
    public ResponseModel delete(int comCustomerId) {
        int delete = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCustomerDelete(?)}");
            cs.setInt(1, comCustomerId);
            delete = 1; 
            cs.execute();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", delete, 200);
    }
    
    public ResponseModel list() {
        ArrayList<ComCustomerEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCustomerList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ComCustomerEntity b=new ComCustomerEntity();
                b.setId(rs.getInt("id"));
                b.setProject(rs.getString("project"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomer(rs.getString("customer"));
                b.setUserId(rs.getInt("userId"));
                b.setUserId(rs.getInt("userId"));
                b.setUser(rs.getString("usuario"));
                b.setActive(rs.getBoolean("Active"));
                b.setCreationDate(rs.getString("creationDate"));
                list.add(b);
            }
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
    
    public ResponseModel findByName(String project) {
        ComCustomerEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCustomerFindByName(?)}");
            cs.setString(1,project);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new ComCustomerEntity();
                b.setId(rs.getInt("id"));
                b.setProject(rs.getString("project"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomer(rs.getString("customer"));
                b.setUserId(rs.getInt("userId"));
                b.setUser(rs.getString("usuario"));
                b.setActive(rs.getBoolean("Active"));
                b.setCreationDate(rs.getString("creationDate"));
            }
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
