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
import net.woden.wdsit.entity.ScpAuditLevelRuleQuantityEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditLevelRuleQuantityService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(int userId, ScpAuditLevelRuleQuantityEntity s){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try{
            cn.setAutoCommit(false);
            CallableStatement cs=cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityCreate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1,s.getLevelRuleId());
            cs.setInt(2,userId);
            cs.setInt(3,s.getQuantityMin());
            cs.setInt(4,s.getQuantityMax());
            cs.setInt(5,s.getNoveltyAccepted());
            cs.setInt(6,s.getNoveltyRejected());
            cs.setInt(7,s.getShow());
            cs.registerOutParameter(8,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(8);
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
    
    public ResponseModel update(int levelRuleQuantityId, ScpAuditLevelRuleQuantityEntity c) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityEdit(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, levelRuleQuantityId);
            cs.setInt(2, c.getLevelRuleId());
            cs.setInt(3, c.getQuantityMin());
            cs.setInt(4, c.getQuantityMax());
            cs.setInt(5, c.getNoveltyAccepted());
            cs.setInt(6, c.getNoveltyRejected());
            cs.setInt(7, c.getShow());
            cs.setBoolean(8, c.isActive());
            cs.registerOutParameter(9, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(9);
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
    
    public ResponseModel delete(int levelRuleQuantityId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityDelete(?)}");
            cs.setInt(1, levelRuleQuantityId);
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
        ArrayList<ScpAuditLevelRuleQuantityEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ScpAuditLevelRuleQuantityEntity b=new ScpAuditLevelRuleQuantityEntity();
                b.setId(rs.getInt("id"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setQuantityMin(rs.getInt("quantityMin"));
                b.setQuantityMax(rs.getInt("quantityMax"));
                b.setNoveltyAccepted(rs.getInt("noveltyAccepted"));
                b.setNoveltyRejected(rs.getInt("noveltyRejected"));
                b.setShow(rs.getInt("show"));
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
    
    public ResponseModel findById(int levelRuleQuantityId) {
        ScpAuditLevelRuleQuantityEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityFindById(?)}");
            cs.setInt(1,levelRuleQuantityId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new ScpAuditLevelRuleQuantityEntity();
                b.setId(rs.getInt("id"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setQuantityMin(rs.getInt("quantityMin"));
                b.setQuantityMax(rs.getInt("quantityMax"));
                b.setNoveltyAccepted(rs.getInt("noveltyAccepted"));
                b.setNoveltyRejected(rs.getInt("noveltyRejected"));
                b.setShow(rs.getInt("show"));
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
