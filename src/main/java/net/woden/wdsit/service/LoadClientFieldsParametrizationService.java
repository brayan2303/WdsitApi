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
import net.woden.wdsit.entity.LoadClientFieldsParametrizationEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientFieldsParametrizationService {
    
    @Autowired
    private DataSourceConnection ds;
    
     public ResponseModel create(LoadClientFieldsParametrizationEntity l) {     //crear y almacenar info
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationCreate(?,?,?,?,?,?,?)}");
            cs.setString(1, l.getParametrizationOne());
            cs.setString(2, l.getFunctionOne());
            cs.setString(3, l.getResultOne());
            cs.setString(4, l.getResultTwo());
            cs.setInt(5, l.getFieldParametrizationId());
            cs.setInt(6, l.getFieldId() );
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(7);
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
     
       public ResponseModel list(int fieldId) { //listar en pantalla info
        ArrayList<LoadClientFieldsParametrizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationList(?)}");
            cs.setInt(1, fieldId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientFieldsParametrizationEntity l = new LoadClientFieldsParametrizationEntity();
                l.setId(rs.getInt("Id"));
                l.setParametrizationOne(rs.getString("parametrizationOne"));
                l.setFunctionOne(rs.getString("functionOne"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setFieldId(rs.getInt("fieldId"));
                l.setName(rs.getString("name"));
                l.setFuncion(rs.getString("funcion"));
                l.setMetodo(rs.getString("metodo"));
                list.add(l);
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
       
         public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationDelete(?)}");
            cs.setInt(1, id);
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
}
