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
import net.woden.wdsit.entity.LoadClientPersonHistoryEntity;
import net.woden.wdsit.model.LoadClientPersonCrossingModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientPersonHistoryService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadClientPersonHistoryEntity l) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadPersonClientHistoryCreate(?,?,?)}");
            cs.setString(1, l.getCustomerId());
            cs.setString(2, l.getUserId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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
    
    public ResponseModel list(){
        ArrayList<LoadClientPersonCrossingModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPersonCrossing()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
            LoadClientPersonCrossingModel l = new LoadClientPersonCrossingModel();
            l.setCliente(rs.getString("CLIENTE"));
            l.setSerial_equipo(rs.getString("SERIAL_EQUIPO"));
            l.setCodigo_sap(rs.getString("CODIGO_SAP"));
            l.setEstado(rs.getString("ESTADO"));
            l.setTipologia(rs.getString("TIPOLOGIA"));
            list.add(l);
            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
    return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
