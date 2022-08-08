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
import net.woden.wdsit.entity.ProNotificationUserEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProNotificationUserService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ProNotificationUserEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationUserCreate(?,?,?)}");
            cs.setInt(1, b.getNotificationId());
            cs.setInt(2, b.getUserId());
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
    public ResponseModel delete(int notificationId,int userId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationUserDelete(?,?)}");
            cs.setInt(1, notificationId);
            cs.setInt(2, userId);
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
    public ResponseModel list(String notificationName) {
        ArrayList<ProNotificationUserEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationUserList(?)}");
            cs.setString(1,notificationName);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProNotificationUserEntity b=new ProNotificationUserEntity();
                b.setId(rs.getInt("id"));
                b.setNotificationId(rs.getInt("notificationId"));
                b.setUserId(rs.getInt("userId"));
                b.setUser(rs.getString("user"));
                b.setMail(rs.getString("mail"));
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
    public ResponseModel findAll(int notificationId) {
        ArrayList<GenPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProNotificationUserFindAll(?)}");
            cs.setInt(1,notificationId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity g = new GenPersonEntity();
                g.setId(rs.getInt("id"));
                g.setIdentification(rs.getInt("identification"));
                g.setFirstName(rs.getString("firstName"));
                g.setLastName(rs.getString("lastName"));
                g.setUserName(rs.getString("userName"));
                g.setPassword(rs.getString("password"));
                g.setMail(rs.getString("mail"));
                g.setCreationDate(rs.getDate("creationDate"));
                g.setLoginDate(rs.getDate("loginDate"));
                g.setCenterCostId(rs.getInt("centerCostId"));
                g.setPositionId(rs.getInt("positionId"));
                g.setCityId(rs.getInt("cityId"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    
}
