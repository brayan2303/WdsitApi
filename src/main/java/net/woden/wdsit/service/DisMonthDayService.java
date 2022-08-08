package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.DisMonthDayEntity;
import net.woden.wdsit.model.DisMonthDayModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.DayMonthUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisMonthDayService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DayMonthUtility dmu;

    public ResponseModel create(DisMonthDayEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthDayCreate(?,?,?)}");
            cs.setInt(1,d.getMonthId());
            cs.setInt(2,d.getDay());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(3);
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
    public ResponseModel delete(int monthId,int day) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthDayDelete(?,?)}");
            cs.setInt(1,monthId);
            cs.setInt(2,day);
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
    public ResponseModel list(int monthId,int year,int month) {
        int days=0;
        ArrayList<Integer>listDays=new ArrayList();
        ArrayList<DisMonthDayModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthDayList(?)}");
            cs.setInt(1,monthId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                listDays.add(rs.getInt("day"));
            }
            days=this.dmu.getDays(year,month);
            for(int i=1;i<(days+1);i++){
                if(listDays.contains(i)){
                    DisMonthDayModel d=new DisMonthDayModel();
                    d.setDay(i);
                    d.setActive(false);
                    list.add(d);
                }else{
                    DisMonthDayModel d=new DisMonthDayModel();
                    d.setDay(i);
                    d.setActive(true);
                    list.add(d);
                }
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
    public ResponseModel days(int monthId,int year,String monthName) {
        int days=0;
        int month=0;
        ArrayList<Integer>listDays=new ArrayList();
        ArrayList<Integer>list=new ArrayList();
        Connection cn = this.ds.openConnection();
        switch (monthName) {
            case "Enero":
                month=1;
                break;
            case "Febrero":
                month=2;
                break;
            case "Marzo":
                month=3;
                break;
            case "Abril":
                month=4;
                break;
            case "Mayo":
                month=5;
                break;
            case "Junio":
                month=6;
                break;
            case "Julio":
                month=7;
                break;
            case "Agosto":
                month=8;
                break;
            case "Septiembre":
                month=9;
                break;
            case "Octubre":
                month=10;
                break;
            case "Noviembre":
                month=11;
                break;
            case "Diciembre":
                month=12;
                break;
            default:
                break;
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisMonthDayList(?)}");
            cs.setInt(1,monthId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                listDays.add(rs.getInt("day"));
            }
            days=this.dmu.getDays(year,month);
            for(int i=1;i<(days+1);i++){
                if(!listDays.contains(i)){
                    list.add(i);
                }
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
