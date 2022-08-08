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
import net.woden.wdsit.entity.ActiveFixedTypeEntryEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ActiveFixedTypeEntryService {
    
    @Autowired
    private DataSourceConnection ds;
     
       public ResponseModel create(ActiveFixedTypeEntryEntity a) {     //crear y almacenar info
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedTypePrductCreate(?,?)}");
            cs.setString(1, a.getName());
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
       
          public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedTypePrductDelete(?)}");
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
          
          
    public ResponseModel list() {                                   //listar en pantalla info
        ArrayList<ActiveFixedTypeEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedTypePrductList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedTypeEntryEntity a = new ActiveFixedTypeEntryEntity();
                a.setId(rs.getInt("Id"));
                a.setName(rs.getString("name"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setActive(rs.getBoolean("active"));
                list.add(a);
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
