/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.InvHPonWTSModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvHPonWTSService{

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel InvHPonWTSLocation() {
        InvHPonWTSModel data = new InvHPonWTSModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvHPonWTSLocation()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    
        public ResponseModel InvHPonWTSWarehouse() {
        InvHPonWTSModel data = new InvHPonWTSModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvHPonWTSWarehouse()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }

}
