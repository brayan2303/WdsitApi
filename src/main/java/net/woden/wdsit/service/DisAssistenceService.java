package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DisAssistenceEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.DayMonthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisAssistenceService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DayMonthUtility dmu;

    public ResponseModel create(DisAssistenceEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisAssistenceCreate(?,?,?,?)}");
            cs.setInt(1,d.getMonthId());
            cs.setInt(2,d.getHeadCountId());
            cs.setInt(3,d.getDay());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
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
    public ResponseModel delete(int monthId,int headCountId,int day) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisAssistenceDelete(?,?,?)}");
            cs.setInt(1,monthId);
            cs.setInt(2,headCountId);
            cs.setInt(3,day);
            deletes=cs.executeUpdate();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
    public ResponseModel list(int monthId) {
        ArrayList<DisAssistenceEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisAssistenceList(?)}");
            cs.setInt(1,monthId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                DisAssistenceEntity d=new DisAssistenceEntity();
                d.setId(rs.getInt("id"));
                d.setHeadCountId(rs.getInt("headCountId"));
                d.setMonthId(rs.getInt("monthId"));
                d.setDay(rs.getInt("day"));
                list.add(d);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
