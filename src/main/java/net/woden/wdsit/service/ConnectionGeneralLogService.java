/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
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
public class ConnectionGeneralLogService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private EncryptUtility eu;

    public ResponseModel create(ConnectionGeneralLogModel c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectionGeneralLogCreate(?,?,?,?,?)}");
            cs.setInt(1, c.getUserId());
            cs.setString(2, c.getObservation());
            cs.setString(3, this.eu.encode(c.getCustomer()));
            cs.setString(4, this.eu.encode(c.getUrl()));
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

}
