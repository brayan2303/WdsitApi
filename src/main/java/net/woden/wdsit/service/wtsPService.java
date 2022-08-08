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
import net.woden.wdsit.connection.DataSourceWtsConnection;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.wtspModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class wtsPService {
    @Autowired
    private DataSourceWtsConnection ds;
    
        public ResponseModel listPhone(String phone) {
        ArrayList<wtspModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call wts_phone(?)}");
            cs.setString(1, phone);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                wtspModel w = new wtspModel();
                w.setOdsi(rs.getString("odsi"));
                w.setSegmento(rs.getString("segmento"));
                w.setOds(rs.getString("ods"));
                w.setFecha(rs.getString("fecha"));
                w.setSucursal(rs.getString("sucursal"));
                w.setSerial(rs.getString("serial"));
                w.setUser(rs.getString("user"));
                w.setTelephone(rs.getString("telephone"));
                lists.add(w);
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
    
      public ResponseModel listDocument(String document) {
        ArrayList<wtspModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call wts_document(?)}");
            cs.setString(1, document);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                wtspModel w = new wtspModel();
                w.setOdsi(rs.getString("odsi"));
                w.setSegmento(rs.getString("segmento"));
                w.setOds(rs.getString("ods"));
                w.setFecha(rs.getString("fecha"));
                w.setSucursal(rs.getString("sucursal"));
                w.setSerial(rs.getString("serial"));
                w.setUser(rs.getString("user"));
                w.setTelephone(rs.getString("telephone"));
                lists.add(w);
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
        public ResponseModel listSerial(String serial) {
        ArrayList<wtspModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call wts_serial(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                wtspModel w = new wtspModel();
                w.setOdsi(rs.getString("odsi"));
                w.setSegmento(rs.getString("segmento"));
                w.setOds(rs.getString("ods"));
                w.setFecha(rs.getString("fecha"));
                w.setSucursal(rs.getString("sucursal"));
                w.setSerial(rs.getString("serial"));
                w.setUser(rs.getString("user"));
                w.setTelephone(rs.getString("telephone"));           
                lists.add(w);
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

}
