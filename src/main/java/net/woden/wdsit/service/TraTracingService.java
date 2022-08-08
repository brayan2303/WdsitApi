package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.TraFaseEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.TraDetailModel;
import net.woden.wdsit.model.TraTimelineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraTracingService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel findSystem(int countryId,String customer,String serial) {
        String result=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TraTracingFindSystem(?,?,?)}");
            cs.setInt(1,countryId);
            cs.setString(2,customer);
            cs.setString(3,serial);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                result=rs.getString(1);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", result, 200);
    }
    public ResponseModel findFase(int countryId,String customer,String system,String serial) {
        ArrayList<TraFaseEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TraTracingFindFase(?,?,?,?)}");
            cs.setInt(1,countryId);
            cs.setString(2,customer);
            cs.setString(3, system);
            cs.setString(4,serial);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                TraFaseEntity t=new TraFaseEntity();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setIcon(rs.getString("icon"));
                t.setSystem(rs.getString("system"));
                t.setPosition(rs.getInt("position"));
                list.add(t);
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
    public ResponseModel findDetail(int countryId,String system,String customer,String fase,int serialId) {
        TraDetailModel t=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TraTracingFindDetail(?,?,?,?,?)}");
            cs.setInt(1,countryId);
            cs.setString(2,system);
            cs.setString(3, customer);
            cs.setString(4, fase);
            cs.setInt(5,serialId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                t=new TraDetailModel();
                t.setFase(fase);
                t.setDate(rs.getString("date").substring(0,10));
                t.setHour(rs.getString("date").substring(11, 16));
                t.setUser(rs.getString("user"));
                t.setPallet(rs.getString("pallet"));
                t.setLocation(rs.getString("location"));
                t.setBox(rs.getString("box"));
                t.setFailure(rs.getString("failure"));
                t.setScrapReason(rs.getString("scrapReason"));
                t.setTechnical(rs.getString("technical"));
                t.setStatus(rs.getString("status"));
                t.setDescription(rs.getString("description"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", t, 200);
    }
    public ResponseModel findTimeline(int countryId,String system,String customer,String serial) {
        ArrayList<TraTimelineModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_TraTracingFindTimeline(?,?,?,?)}");
            cs.setInt(1,countryId);
            cs.setString(2,system);
            cs.setString(3, customer);
            cs.setString(4,serial);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                TraTimelineModel t=new TraTimelineModel();
                t.setFase(rs.getString("fase"));
                t.setDate(rs.getString("date").substring(0, 16));
                t.setUser(rs.getString("user"));
                t.setDescription(rs.getString("description"));
                list.add(t);
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
