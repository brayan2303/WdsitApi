package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrTracingEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrTracingService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrTracingEntity p) {
        int inserts=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTracingCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1,p.getPqrNumber());
            cs.setString(2,p.getPqrTicket());
            cs.setInt(3,p.getPqrTypeId());
            cs.setString(4,p.getType());
            cs.setInt(5,p.getEventStatusId());
            cs.setString(6,p.getObservations());
            cs.setInt(7,p.getUserId());
            cs.registerOutParameter(8,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(8);
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
    public ResponseModel list(String pqrNumber) {
        ArrayList<PqrTracingEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTracingList(?)}");
            cs.setString(1,pqrNumber);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrTracingEntity p=new PqrTracingEntity();
                p.setId(rs.getInt("id"));
                p.setPqrNumber(rs.getString("pqrNumber"));
                p.setPqrTicket(rs.getString("pqrTicket"));
                p.setPqrTypeId(rs.getInt("pqrTypeId"));
                p.setPqrType(rs.getString("pqrType"));
                p.setType(rs.getString("type"));
                p.setEventStatusId(rs.getInt("eventStatusId"));
                p.setEventStatus(rs.getString("eventStatus"));
                p.setObservations(rs.getString("observations"));
                p.setDate(rs.getString("date").substring(0,16));
                p.setUserId(rs.getInt("userId"));
                p.setUser(rs.getString("user"));
                list.add(p);
            }
            cn.commit();
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
