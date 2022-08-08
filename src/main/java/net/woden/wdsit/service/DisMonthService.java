package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DisMonthEntity;
import net.woden.wdsit.model.DisMonthModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.DayMonthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisMonthService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DayMonthUtility dmu;

    public ResponseModel create(DisMonthEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthCreate(?,?,?,?)}");
            cs.setInt(1,d.getMonth());
            cs.setInt(2,d.getYear());
            cs.setInt(3,this.dmu.getDays(d.getYear(),d.getMonth()));
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
    public ResponseModel update(DisMonthEntity d) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthUpdate(?,?,?,?,?)}");
            cs.setInt(1,d.getId());
            cs.setInt(2,d.getMonth());
            cs.setInt(3,d.getYear());
            cs.setInt(4,this.dmu.getDays(d.getYear(),d.getMonth()));
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }
    public ResponseModel delete(int monthId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthDelete(?)}");
            cs.setInt(1,monthId);
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
    public ResponseModel list() {
        ArrayList<DisMonthEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                DisMonthEntity d=new DisMonthEntity();
                d.setId(rs.getInt("id"));
                d.setMonth(rs.getInt("month"));
                d.setMonthName(rs.getString("monthName"));
                d.setYear(rs.getInt("year"));
                d.setDays(rs.getInt("days"));
                list.add(d);
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
    public ResponseModel listAll(int year) {
        ArrayList<DisMonthModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthListAll(?)}");
            cs.setInt(1, year);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                DisMonthModel d=new DisMonthModel();
                d.setId(rs.getInt("id"));
                d.setYear(rs.getInt("year"));
                d.setMonthId(rs.getInt("monthId"));
                d.setMonth(rs.getString("month"));
                d.setDays(rs.getInt("days"));
                d.setDaysExcluded(rs.getInt("daysExcluded"));
                list.add(d);
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
