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
import net.woden.wdsit.entity.ScpAuditPreviousEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditPreviousService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(int userId, ScpAuditPreviousEntity s){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ScpAuditPreviousCreate(?,?,?,?)}");
            cs.setString(1,s.getName());
            cs.setInt(2,s.getCustomerId());
            cs.setInt(3,userId);
            cs.registerOutParameter(4,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
            cn.commit();
            cs.close();
            cn.close();
        }catch(SQLException ex){
            return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(),ex.getMessage(),null,200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(),"OK",inserts,200);
    }
    
    public ResponseModel update(int auditPreviousId, ScpAuditPreviousEntity c) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousEdit(?,?,?,?,?)}");
            cs.setInt(1, auditPreviousId);
            cs.setString(2, c.getName());
            cs.setInt(3, c.getCustomerId());
            cs.setBoolean(4, c.isActive());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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
    
    public ResponseModel close(int auditPreviousId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousClose(?,?)}");
            cs.setInt(1, auditPreviousId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(2);
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
    
    public ResponseModel delete(int auditPreviousId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousDelete(?)}");
            cs.setInt(1, auditPreviousId);
            cs.execute();
            inserts = 1; 
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
    
    public ResponseModel list() {
        ArrayList<ScpAuditPreviousEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ScpAuditPreviousEntity b=new ScpAuditPreviousEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setState(rs.getString("state"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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
    
    public ResponseModel listAudit() {
        ArrayList<ScpAuditPreviousEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousListAudit()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ScpAuditPreviousEntity b=new ScpAuditPreviousEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setState(rs.getString("state"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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
    
    public ResponseModel listForSerials() {
        ArrayList<ScpAuditPreviousEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousListForSerials()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ScpAuditPreviousEntity b=new ScpAuditPreviousEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setState(rs.getString("state"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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
    
    public ResponseModel findById(int auditPreviousId) {
        ScpAuditPreviousEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousFindById(?)}");
            cs.setInt(1,auditPreviousId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new ScpAuditPreviousEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setState(rs.getString("state"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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
