package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscMonthEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscMonthService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel list(int measurementId) {
        ArrayList<BscMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMonthList(?)}");
            cs.setInt(1, measurementId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscMonthEntity b=new BscMonthEntity();
                b.setId(rs.getInt("id"));
                b.setCode(rs.getInt("code"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
    public ResponseModel listMeasurement(int measurementId) {
        ArrayList<BscMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscMonthListMeasurement(?)}");
            cs.setInt(1, measurementId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscMonthEntity b=new BscMonthEntity();
                b.setId(rs.getInt("id"));
                b.setCode(rs.getInt("code"));
                b.setName(rs.getString("name"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
