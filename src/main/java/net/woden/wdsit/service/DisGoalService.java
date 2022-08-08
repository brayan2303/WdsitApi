package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DisGoalEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisGoalService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(DisGoalEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisGoalCreate(?,?,?,?,?,?)}");
            cs.setInt(1, d.getYear());
            cs.setInt(2, d.getMonth());
            cs.setString(3, d.getCustomer());
            cs.setString(4, d.getCodeFamily());
            cs.setInt(5, d.getGoal());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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
