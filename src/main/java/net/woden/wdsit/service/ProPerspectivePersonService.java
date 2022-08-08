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
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.entity.ProPerspectiveEntity;
import net.woden.wdsit.entity.ProPerspectivePersonEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProPerspectivePersonService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProPerspectivePersonEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProPerspectivePersonCreate(?,?,?)}");
            cs.setInt(1, p.getPerspectiveId());
            cs.setInt(2, p.getPersonId());
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
    public ResponseModel delete(int perspectiveId,int personId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProPerspectivePersonDelete(?,?)}");
            cs.setInt(1, perspectiveId);
            cs.setInt(2, personId);
            deletes=cs.executeUpdate();
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
    public ResponseModel list(int perspectiveId) {
        ArrayList<GenPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProPerspectivePersonList(?)}");
            cs.setInt(1,perspectiveId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
            }
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    public ResponseModel listActive(int year,int personId,int countryId) {
        ArrayList<ProPerspectiveEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProPerspectivePersonListActive(?,?,?)}");
            cs.setInt(1, year);
            cs.setInt(2,personId);
            cs.setInt(3,countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProPerspectiveEntity b = new ProPerspectiveEntity();
                b.setId(rs.getInt("id"));
                b.setCode(rs.getString("code"));
                b.setName(rs.getString("name"));
                b.setObjetive(rs.getString("objetive"));
                b.setColor(rs.getString("color"));
                b.setCountryId(rs.getInt("countryId"));
                b.setCountry(rs.getString("country"));
                b.setLeaderId(rs.getInt("leaderId"));
                b.setLeader(rs.getString("leader"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
}
