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
import net.woden.wdsit.entity.ActiveFixedExitEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ActiveFixedExitService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ActiveFixedExitEntity a) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExidCreate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, a.getIdentification());
            cs.setString(2, a.getName());
            cs.setString(3, a.getArea());
            cs.setString(4, a.getPosition());
            cs.setString(5, a.getEquipment());
            cs.setString(6, a.getSerial());
            cs.setString(7, a.getSerialEquipment());
            cs.setString(8, a.getAssociatedSerial());
            cs.setInt(9, a.getUserId());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(10);
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
        ArrayList<ActiveFixedExitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExitList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedExitEntity a = new ActiveFixedExitEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getString("identification"));
                a.setName(rs.getString("name"));
                a.setArea(rs.getString("area"));
                a.setPosition(rs.getString("position"));
                a.setEquipment(rs.getString("equipment"));
                a.setSerial(rs.getString("serial"));
                a.setSerialEquipment(rs.getString("serialEquipment"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setPersonExit(rs.getString("personExit"));
                a.setAssociatedSerial(rs.getString("associatedSerial"));
                a.setObservation(rs.getString("observation"));
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExitDelete(?)}");
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

    public ResponseModel updateObservation(ActiveFixedExitEntity a) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExitUpdateObservation(?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getObservation());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            update = cs.getInt(3);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
    }

}
