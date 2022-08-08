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
import net.woden.wdsit.entity.GenPlantEntity;
import net.woden.wdsit.entity.GenPlantPersonEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class GenPlantPersonService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(GenPlantPersonEntity g) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPlantPersonCreate(?,?,?)}");
            cs.setInt(1, g.getPersonId());
            cs.setInt(2, g.getPlantId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel list() {
        ArrayList<GenPlantPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_GenPlantPersonList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPlantPersonEntity g = new GenPlantPersonEntity();
                g.setId(rs.getInt("id"));
                g.setPersonId(rs.getInt("personId"));
                g.setPlantId(rs.getInt("plantId"));
                list.add(g);
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

      public ResponseModel delete(int personId, int plantId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenplantPersonDelete(?,?)}");
            cs.setInt(1, personId);
            cs.setInt(2, plantId);
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
    
    
    public ResponseModel findAll(int personId){
    ArrayList<GenPlantEntity> list = new ArrayList();
    Connection cn = this.ds.openConnection();
    
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPlantPersonFindAll(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            GenPlantEntity p = new GenPlantEntity();
            p.setId(rs.getInt("id"));
            p.setCod(rs.getString("cod"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setActive(rs.getBoolean("active"));
            list.add(p);
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
