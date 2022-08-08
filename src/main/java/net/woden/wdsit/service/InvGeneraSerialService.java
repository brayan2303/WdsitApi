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
import net.woden.wdsit.entity.InvGeneraSerialEntity;
import net.woden.wdsit.model.InvGeneraSerialModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvGeneraSerialService {
    
        @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(InvGeneraSerialEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneraSerialCreate(?,?,?,?)}");
            cs.setString(1, i.getSerial());
            cs.setInt(2, i.getTypeId());
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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

    public ResponseModel list(int id) {
        ArrayList<InvGeneraSerialModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneraSerialList(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneraSerialModel a = new InvGeneraSerialModel();
                a.setId(rs.getInt("id"));
                a.setConteo(rs.getString("conteo"));
                a.setCodigo(rs.getString("codigo"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setSerial(rs.getString("serial"));
                a.setName(rs.getString("name"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setActive(rs.getBoolean("active"));
                 a.setCloseOpen(rs.getBoolean("closeOpen"));
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

        public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneraSerialDelete(?)}");
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



    public ResponseModel findById(int id) {
        InvGeneraSerialEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneraSerialFindbyId(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneraSerialEntity();
         a.setId(rs.getInt("id"));
                a.setSerial(rs.getString("serial"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setActive(rs.getBoolean("active"));
            }
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }
    
}
