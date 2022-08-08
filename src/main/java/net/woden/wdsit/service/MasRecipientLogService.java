package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasRecipientLogService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(int mailId,String mail,String type,String status) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasRecipientLogCreate(?,?,?,?,?)}");
            cs.setInt(1, mailId);
            cs.setString(2, mail);
            cs.setString(3,type);
            cs.setString(4, status);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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
}
