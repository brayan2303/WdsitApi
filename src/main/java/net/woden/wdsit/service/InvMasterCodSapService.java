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
import net.woden.wdsit.entity.InvMasterCodSapEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvMasterCodSapService {
    
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(InvMasterCodSapEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterCodSapCreate(?,?,?,?,?)}");
            cs.setString(1, i.getCodSap());
            cs.setInt(2, i.getInitTrim());
            cs.setInt(3, i.getEndTrim());
            cs.setInt(4, userId);
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
    
    public ResponseModel update(InvMasterCodSapEntity i, int userId) {
        int upaates = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterCodSapUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, i.getId());
            cs.setString(2, i.getCodSap());
            cs.setInt(3, i.getInitTrim());
            cs.setInt(4, i.getEndTrim());
            cs.setInt(5, userId);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            upaates = cs.getInt(6);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", upaates, 200);
    }
    
    public ResponseModel delete(int id) {        
        int deletes = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterCodSapDelete(?)}");
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
    
    public ResponseModel list() {
        ArrayList<InvMasterCodSapEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterCodSapList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterCodSapEntity i = new InvMasterCodSapEntity();
                i.setId(rs.getInt("id"));
                i.setCodSap(rs.getString("codSap"));
                i.setInitTrim(rs.getInt("initTrim"));
                i.setEndTrim(rs.getInt("endTrim"));
                i.setActive(rs.getBoolean("active"));
                i.setCreationDate(rs.getString("creationDate"));
                lists.add(i);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }
    
    public ResponseModel findById(int id) {
        InvMasterCodSapEntity i = null;
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterCodSapFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterCodSapEntity();
                i.setId(rs.getInt("id"));
                i.setCodSap(rs.getString("codSap"));
                i.setInitTrim(rs.getInt("initTrim"));
                i.setEndTrim(rs.getInt("endTrim"));
                i.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }
    
}
