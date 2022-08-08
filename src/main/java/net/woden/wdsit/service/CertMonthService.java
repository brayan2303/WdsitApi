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
import net.woden.wdsit.entity.CertCertEntity;
import net.woden.wdsit.entity.CertMonthEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class CertMonthService {
    
    @Autowired
    private DataSourceConnection ds;
    
    /**
     * Crear un nuevo año
     * @param c de CertCertEntity c
     * @return Confirmacion de Repositorio
     */
    public ResponseModel create(CertMonthEntity c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertMonthCreate(?,?)}");
            cs.setString(1,c.getName());
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(2);
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
    
    public ResponseModel update(CertMonthEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertMonthUpdate(?,?,?,?)}");
            cs.setInt(1,c.getId());
            cs.setString(2,c.getName());
            cs.setBoolean(3,c.isActive());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(4);
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
    
    
    public ResponseModel delete(int certMonthId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertMonthDelete(?)}");
            cs.setInt(1,certMonthId);
            deletes=cs.executeUpdate();
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
        ArrayList<CertMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertMonthList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertMonthEntity b=new CertMonthEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
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
    
    
    public ResponseModel findById(int certMonthId) {
        CertMonthEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertMonthFindById(?)}");
            cs.setInt(1,certMonthId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new CertMonthEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
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
    
    public ResponseModel findAll(int certPeriodicityId) {
        ArrayList<CertMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityMonthFindAll(?)}");
            cs.setInt(1,certPeriodicityId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertMonthEntity b=new CertMonthEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
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
    
    public ResponseModel findAllByPeriodicityId(int certPeriodicityId) {
        ArrayList<CertMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityMonthFindAllByPeriodicityId(?)}");
            cs.setInt(1,certPeriodicityId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertMonthEntity b=new CertMonthEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setModificationDate(rs.getString("modificationDate"));
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
    
}
