/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpCrossingWmsEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpCrossingCountModel;
import net.woden.wdsit.model.ScpCrossingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpCrossingWmsService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ScpCrossingWmsEntity s) {
        int insert = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCrossingWmsCreate(?,?,?)}");
            cs.setString(1, s.getSerial());
            cs.setString(2, s.getPallet());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            insert = cs.getInt(3);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", insert, 200);
    }

    public ResponseModel listCrossing(int auditPreviousId) {
        ArrayList<ScpCrossingModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCrossing(?)}");
            cs.setInt(1, auditPreviousId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpCrossingModel s = new ScpCrossingModel();
                s.setId(rs.getInt("id"));
                s.setWdsit(rs.getString("wdsit"));
                s.setWms(rs.getString("wms"));
                s.setWmsVsWdist(rs.getString("wmsVsWdist"));
                s.setWdistVswms(rs.getString("wdistVswms"));
                lists.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

      public ResponseModel listCrossingCount(int auditPreviousId) {
        ScpCrossingCountModel s = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpCrossingCount(?)}");
            cs.setInt(1, auditPreviousId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpCrossingCountModel();
                s.setId(rs.getInt("id"));
                s.setWdsit(rs.getString("wdsit"));
                s.setWms(rs.getString("wms"));
                s.setWmsVsWdist(rs.getString("wmsVsWdist"));
                s.setWdistVswms(rs.getString("wdistVswms"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", s, 200);
    }

}
