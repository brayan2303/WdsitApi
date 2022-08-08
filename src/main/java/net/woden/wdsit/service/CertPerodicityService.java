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
import net.woden.wdsit.entity.CertPerodicityEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class CertPerodicityService {
    
    @Autowired
    private DataSourceConnection ds;
    
    /**
     * Crear un nuevo año
     * @param b de CertYearEntity b
     * @return Confirmacion de Repositorio
     */
    public ResponseModel create(CertPerodicityEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityCreate(?,?)}");
            cs.setString(1,b.getPeriodicity());
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
    
    public ResponseModel update(CertPerodicityEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityUpdate(?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getPeriodicity());
            cs.setBoolean(3,b.isActive());
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
    
    
    public ResponseModel delete(int certYearId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityDelete(?)}");
            cs.setInt(1,certYearId);
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
        ArrayList<CertPerodicityEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertPerodicityEntity b=new CertPerodicityEntity();
                b.setId(rs.getInt("id"));
                b.setPeriodicity(rs.getString("periodicity"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getDate("creationDate"));
                b.setModificationDate(rs.getDate("modificationDate"));
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
    
    
    public ResponseModel findById(int certYearId) {
        CertPerodicityEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertPeriodicityFindById(?)}");
            cs.setInt(1,certYearId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new CertPerodicityEntity();
                b.setId(rs.getInt("id"));
                b.setPeriodicity(rs.getString("periodicity"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getDate("creationDate"));
                b.setModificationDate(rs.getDate("modificationDate"));
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
    
    public ResponseModel findAll(int certificateId) {
        ArrayList<CertPerodicityEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertPeriodicityFindAll(?)}");
            cs.setInt(1,certificateId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertPerodicityEntity b=new CertPerodicityEntity();
                b.setId(rs.getInt("id"));
                b.setPeriodicity(rs.getString("periodicity"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getDate("creationDate"));
                b.setModificationDate(rs.getDate("modificationDate"));
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
    
    public ResponseModel findAllByCertId(int certificateId) {
        ArrayList<CertPerodicityEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertCertPeriodicityFindAllByCertId(?)}");
            cs.setInt(1,certificateId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertPerodicityEntity b=new CertPerodicityEntity();
                b.setId(rs.getInt("id"));
                b.setPeriodicity(rs.getString("periodicity"));
                b.setActive(rs.getBoolean("active"));
                b.setCreationDate(rs.getDate("creationDate"));
                b.setModificationDate(rs.getDate("modificationDate"));
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
