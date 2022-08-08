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
import net.woden.wdsit.model.ConnectionGeneralLogModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ConnectionPasswordService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private EncryptUtility eu;

    public ResponseModel password(String pass) {
        ConnectionGeneralLogModel c = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectionPasswordValidate(?)}");
            cs.setString(1, this.eu.encode(pass));
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new ConnectionGeneralLogModel();
                c.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }
}
