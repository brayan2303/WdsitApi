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
import net.woden.wdsit.entity.PriVariableDetailEntity;
import net.woden.wdsit.model.PriVariableValueModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
    public class PriVariableDetailService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ArrayList<PriVariableDetailEntity> array) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriVariableDetailCreate(?)}");
            for (PriVariableDetailEntity p : array) {
                cs.setInt(1, p.getVariableId());
                cs.addBatch();
            }
            inserts = cs.executeBatch().length;
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

        public ResponseModel list(int varibleFormId) {
        ArrayList<PriVariableValueModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriVariableDetailList(?)}");
            cs.setInt(1, varibleFormId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriVariableValueModel p = new PriVariableValueModel();
                p.setVariableId(rs.getInt("variableId"));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
                p.setValue(rs.getString("value"));
                p.setIdInput(rs.getInt("idInput"));
                lists.add(p);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel update(ArrayList<PriVariableDetailEntity> array) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriVariableDetailUpdate(?,?,?)}");
            for (PriVariableDetailEntity p : array) {
                cs.setInt(1, p.getId());
                cs.setString(2, p.getValue());
                cs.addBatch();
            }
            update = cs.executeBatch().length;
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
