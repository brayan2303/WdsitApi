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
import net.woden.wdsit.entity.ActiveFixedAssigmentEntity;
import net.woden.wdsit.entity.ActiveFixedEntryEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ActiveFixedEntryService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ActiveFixedEntryEntity a) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryCreate(?,?,?,?,?,?,?,?,?,?)}");
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
        ArrayList<ActiveFixedEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedEntryEntity a = new ActiveFixedEntryEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getString("identification"));
                a.setName(rs.getString("name"));
                a.setArea(rs.getString("area"));
                a.setPosition(rs.getString("position"));
                a.setEquipment(rs.getString("equipment"));
                a.setSerial(rs.getString("serial"));
                a.setSerialEquipment(rs.getString("serialEquipment"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setPersonEntry(rs.getString("personEntry"));
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
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryDelete(?)}");
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

    public ResponseModel findByIdentification(String serial) {

        ActiveFixedAssigmentEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedExitProvedFindByIdentification(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setMail(rs.getString("mail"));
                a.setCostCenter(rs.getString("costCenter"));
                a.setPosition(rs.getString("position"));
                a.setCity(rs.getString("city"));
                a.setProductEquip(rs.getString("productEquip"));
                a.setSerial(rs.getString("serial"));
                a.setExitPermanent(rs.getString("exitPermanent"));
                a.setStatusEquipament(rs.getString("statusEquipament"));
                a.setPersonRes(rs.getString("personRes"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setActive(rs.getBoolean("active"));
                a.setAnswer(rs.getString("answer"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setProduct(rs.getString("product"));
                a.setNameProduct(rs.getString("nameProduct"));
                a.setExitEntry(rs.getBoolean("exitEntry"));

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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "El equipo no tiene salida habilitada" : "OK", a, 200);

    }

    public ResponseModel findBySerial(String serial) {

        ActiveFixedAssigmentEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryAprocedFindBySerial(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new ActiveFixedAssigmentEntity();
                a.setId(rs.getInt("id"));
                a.setIdentification(rs.getInt("identification"));
                a.setName(rs.getString("name"));
                a.setMail(rs.getString("mail"));
                a.setCostCenter(rs.getString("costCenter"));
                a.setPosition(rs.getString("position"));
                a.setCity(rs.getString("city"));
                a.setProductEquip(rs.getString("productEquip"));
                a.setSerial(rs.getString("serial"));
                a.setExitPermanent(rs.getString("exitPermanent"));
                a.setStatusEquipament(rs.getString("statusEquipament"));
                a.setPersonRes(rs.getString("personRes"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setActive(rs.getBoolean("active"));
                a.setAnswer(rs.getString("answer"));
                a.setApprovedRejected(rs.getBoolean("approvedRejected"));
                a.setProduct(rs.getString("product"));
                a.setNameProduct(rs.getString("nameProduct"));
                a.setExitEntry(rs.getBoolean("exitEntry"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "El equipo no tiene salidas Registradas, Por favor hacer la salida" : "OK", a, 200);
    }

    public ResponseModel updateObservation(ActiveFixedEntryEntity a) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedEntryUpdateObservation(?,?,?)}");
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
