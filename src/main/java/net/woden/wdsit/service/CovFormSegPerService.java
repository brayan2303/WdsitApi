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
import net.woden.wdsit.entity.CovFormSegPerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class CovFormSegPerService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(CovFormSegPerEntity a) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormSegPerCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getIdentificationUser());//
            cs.setString(2, a.getName());//
            cs.setString(3, a.getPosition());//
            cs.setString(4, a.getArea());//
            cs.setString(5, a.getCity());//
            cs.setString(6, a.getMeetingPlace());//
            cs.setString(7, a.getCough());//
            cs.setString(8, a.getBodyPain());//
            cs.setString(9, a.getFatigue());//
            cs.setString(10, a.getSoreThroat());//
            cs.setString(11, a.getHeadache());//
            cs.setString(12, a.getRunnyNose());//
            cs.setString(13, a.getRespiratoryDistress());//
            cs.setString(14, a.getSmellStaste());//
            cs.setString(15, a.getTemperature());//
            cs.setString(16, a.getContactPerson());//
            cs.setString(17, a.getCloseContact());//
            cs.setString(18, a.getBloodTest());//
            cs.setString(19, a.getNoseTest());//
            cs.setString(20, a.getPositiveIsolation());//
            cs.setString(21, a.getPositiveDisability());//
            cs.setString(22, a.getPlaceOutside());//
            cs.setString(23, a.getPlaceInside());//
            cs.setString(24, a.getPersons());//
            cs.registerOutParameter(25, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(25);
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
            CallableStatement cs = cn.prepareCall("{call sp_CovFormSegPerDelete(?)}");
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
        ArrayList<CovFormSegPerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormSegPerList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                CovFormSegPerEntity a = new CovFormSegPerEntity();
                a.setId(rs.getInt("id"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setName(rs.getString("name"));
                a.setCreationUser(rs.getString("creationUser"));
                a.setPosition(rs.getString("position"));
                 a.setCarPosition(rs.getString("carPosition"));
                a.setArea(rs.getString("area"));
                a.setCityId(rs.getString("cityId"));
                a.setCity(rs.getString("city"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setMeetingPlace(rs.getString("meetingPlace"));
                a.setCough(rs.getString("cough"));
                a.setBodyPain(rs.getString("bodyPain"));
                a.setFatigue(rs.getString("fatigue"));
                a.setSoreThroat(rs.getString("soreThroat"));
                a.setHeadache(rs.getString("headache"));
                a.setRunnyNose(rs.getString("runnyNose"));
                a.setRespiratoryDistress(rs.getString("respiratoryDistress"));
                a.setSmellStaste(rs.getString("smellStaste"));
                a.setTemperature(rs.getString("temperature"));
                a.setContactPerson(rs.getString("contactPerson"));
                a.setCloseContact(rs.getString("closeContact"));
                a.setBloodTest(rs.getString("bloodTest"));
                a.setNoseTest(rs.getString("noseTest"));
                a.setPositiveIsolation(rs.getString("positiveIsolation"));
                a.setPositiveDisability(rs.getString("positiveDisability"));
                a.setPlaceOutside(rs.getString("placeOutside"));
                a.setPlaceInside(rs.getString("placeInside"));
                a.setPersons(rs.getString("persons"));
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
