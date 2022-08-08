/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.WeekCountYesNoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service

public class WeekCountYesNoService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel getDate(String date) {
        WeekCountYesNoModel w = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_WeekCountYesNo(?)}");
            cs.setString(1, date);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                w = new WeekCountYesNoModel();
                w.setValidacion(rs.getString("Validacion"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", w, 200);
    }

}
