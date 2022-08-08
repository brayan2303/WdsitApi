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
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ProMeasurementDetailVariableEntity;
import net.woden.wdsit.model.ProVariableValueModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ProMeasurementDetailVariableService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(ArrayList<ProMeasurementDetailVariableEntity>array) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProMeasurementDetailVariableCreate(?,?)}");
            for(ProMeasurementDetailVariableEntity b:array){
                cs.setInt(1,b.getMeasurementDetailId());
                cs.setInt(2,b.getVariableId());
                cs.addBatch();
            }
            inserts=cs.executeBatch().length;
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
    public ResponseModel update(ArrayList<ProMeasurementDetailVariableEntity>array) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProMeasurementDetailVariableUpdate(?,?)}");
            for(ProMeasurementDetailVariableEntity b:array){
                cs.setInt(1,b.getId());
                cs.setDouble(2,b.getValue());
                cs.addBatch();
            }
            updates=cs.executeBatch().length;
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }
    public ResponseModel list(int measurementDetailId) {
        ArrayList<ProVariableValueModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ProMeasurementDetailVariableList(?)}");
            cs.setInt(1,measurementDetailId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ProVariableValueModel b=new ProVariableValueModel();
                b.setFormulaVariableId(rs.getInt("formulaVariableId"));
                b.setType(rs.getString("type"));
                b.setName(rs.getString("name"));
                b.setDetailVariableId(rs.getInt("detailVariableId"));
                b.setValue(rs.getDouble("value"));
                b.setDefaultValue(rs.getInt("defaultValue"));
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
