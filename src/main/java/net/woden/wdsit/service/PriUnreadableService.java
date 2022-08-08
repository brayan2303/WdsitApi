package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PriUnreadableEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriUnreadableService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(int countryId,int customerId,int creationUserId,int quantity) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriUnreadableCreate(?,?,?,?,?)}");
            cs.setInt(1,countryId);
            cs.setInt(2,customerId);
            cs.setInt(3,creationUserId);
            cs.setInt(4,quantity);
            cs.registerOutParameter(5,Types.INTEGER);
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
    public ResponseModel list(int countryId,int customerId,int creationUserId,int quantity) {
        ArrayList<PriUnreadableEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriUnreadableList(?,?,?,?)}");
            cs.setInt(1,countryId);
            cs.setInt(2,customerId);
            cs.setInt(3,creationUserId);
            cs.setInt(4,quantity);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PriUnreadableEntity p=new PriUnreadableEntity();
                p.setSerial(rs.getString("serial"));
                p.setMac(rs.getString("mac"));
                p.setCreationDate(rs.getString("creationDate").substring(0,10));
                list.add(p);
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
