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
import net.woden.wdsit.entity.ScpAuditMotifEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditMotifService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(int userId, ScpAuditMotifEntity s){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ScpAuditMotifCreate(?,?,?,?,?)}");
            cs.setString(1,s.getType());
            cs.setString(2,s.getCode());
            cs.setString(3,s.getDescription());
            cs.setInt(4,userId);
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(5);
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
    
    public ResponseModel update(int auditMotifId, ScpAuditMotifEntity c) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditMotifEdit(?,?,?,?,?,?)}");
            cs.setInt(1, auditMotifId);
            cs.setString(2, c.getType());
            cs.setString(3, c.getCode());
            cs.setString(4, c.getDescription());
            cs.setBoolean(5, c.isActive());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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
    
    public ResponseModel delete(int auditMotifId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditMotifDelete(?)}");
            cs.setInt(1, auditMotifId);
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
        ArrayList<ScpAuditMotifEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditMotifList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ScpAuditMotifEntity b=new ScpAuditMotifEntity();
                b.setId(rs.getInt("id"));
                b.setType(rs.getString("type"));
                b.setCode(rs.getString("code"));
                b.setDescription(rs.getString("description"));
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
    
    public ResponseModel findById(int auditMotifId) {
        ScpAuditMotifEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditMotifFindById(?)}");
            cs.setInt(1,auditMotifId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new ScpAuditMotifEntity();
                b.setId(rs.getInt("id"));
                b.setType(rs.getString("type"));
                b.setCode(rs.getString("code"));
                b.setDescription(rs.getString("description"));
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
