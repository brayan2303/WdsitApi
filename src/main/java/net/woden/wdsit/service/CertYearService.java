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
import net.woden.wdsit.entity.CertYearEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class CertYearService {
    
    @Autowired
    private DataSourceConnection ds;
    
    /**
     * Crear un nuevo año
     * @param b de CertYearEntity b
     * @return Confirmacion de Repositorio
     */
    public ResponseModel create(CertYearEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertYearCreate(?,?)}");
            cs.setInt(1,b.getYear());
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
    
    public ResponseModel update(CertYearEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertYearUpdate(?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setInt(2,b.getYear());
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
            CallableStatement cs = cn.prepareCall("{call sp_CertYearDelete(?)}");
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
        ArrayList<CertYearEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertYearList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                CertYearEntity b=new CertYearEntity();
                b.setId(rs.getInt("id"));
                b.setYear(rs.getInt("year"));
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
        CertYearEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CertYearFindById(?)}");
            cs.setInt(1,certYearId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                b=new CertYearEntity();
                b.setId(rs.getInt("id"));
                b.setYear(rs.getInt("year"));
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
}
