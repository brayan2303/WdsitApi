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
import net.woden.wdsit.entity.InvPartNumberDeftGeneralEntity;
import net.woden.wdsit.model.InvGeneraSerialModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvPartNumberDeftGeneralService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(InvPartNumberDeftGeneralEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvPartNumberDeftGeneralCreate(?,?,?,?,?)}");
            cs.setString(1, i.getStore());
            cs.setString(2, i.getPartNumber());
            cs.setString(3, i.getSerial());
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
    
        public ResponseModel list(int id) {
        ArrayList<InvGeneraSerialModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvPartNumberDeftGeneraList(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneraSerialModel a = new InvGeneraSerialModel();
                a.setId(rs.getInt("id"));
                a.setConteo(rs.getString("conteo"));
                a.setCodigo(rs.getString("codigo"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setSerial(rs.getString("partNumber"));
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
            CallableStatement cs = cn.prepareCall("{call sp_InvPartNumberDeftGeneralDelete(?)}");
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

        

}
