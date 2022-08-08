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
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpResultService {
    
    @Autowired
    private DataSourceConnection ds;
    
     public ResponseModel resultCrossing(int id) {
        ArrayList<ScpResultModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpResultCrossing(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpResultModel s = new ScpResultModel();
                s.setSerial(rs.getString("serial"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setPallet(rs.getString("pallet"));
                s.setCodigoSap(rs.getString("codigoSap"));
                s.setMotivoScrap(rs.getString("motivoScrap"));
                list.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);

    }
        public ResponseModel resultSerial(int id) {
        ArrayList<ScpResultModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpResultSerial(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpResultModel s = new ScpResultModel();
                s.setSerial(rs.getString("serial"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setCodigoSap(rs.getString("codigoSap"));
                s.setMotivoScrap(rs.getString("motivoScrap"));
                list.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);

    }
         public ResponseModel resultLevel(int id) {
        ArrayList<ScpResultModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpResultLevel(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpResultModel s = new ScpResultModel();
                s.setQuantityMin(rs.getString("quantityMin"));
                s.setQuantityMax(rs.getString("quantityMax"));
                s.setNoveltyAccepted(rs.getString("noveltyAccepted"));
                s.setNoveltyRejected(rs.getString("noveltyRejected"));
                s.setShow(rs.getString("show"));
                list.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);

    }
}
