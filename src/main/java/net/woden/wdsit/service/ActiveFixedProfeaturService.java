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
import net.woden.wdsit.entity.ActiveFixedProfeaturEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ActiveFixedProfeaturService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ActiveFixedProfeaturEntity g) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedProdfearCreate(?,?,?)}");
            cs.setInt(1, g.getFeaturesId());
            cs.setInt(2, g.getProductId());
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
        ArrayList<ActiveFixedProfeaturEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedProdfearList(?)}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ActiveFixedProfeaturEntity g = new ActiveFixedProfeaturEntity();
                g.setId(rs.getInt("id"));
                g.setFeaturesId(rs.getInt("featuresId"));
                g.setProductId(rs.getInt("productId"));
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

    public ResponseModel delete(int productId, int featuresId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ActiveFixedProfeaturDelete(?,?)}");
            cs.setInt(1, productId);
            cs.setInt(2, featuresId);
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
