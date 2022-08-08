package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscPerspectiveEntity;
import net.woden.wdsit.model.BscPerspectiveModel;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscPerspectiveService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscPerspectiveEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1,b.getCode());
            cs.setString(2,b.getName());
            cs.setString(3,b.getObjetive());
            cs.setInt(4,b.getYear());
            cs.setString(5,b.getColor());
            cs.setInt(6,b.getCountryId());
            cs.setInt(7,b.getLeaderId());
            cs.registerOutParameter(8, Types.INTEGER);
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
    public ResponseModel update(BscPerspectiveEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getCode());
            cs.setString(3,b.getName());
            cs.setString(4,b.getObjetive());
            cs.setInt(5,b.getYear());
            cs.setString(6,b.getColor());
            cs.setInt(7,b.getCountryId());
            cs.setInt(8,b.getLeaderId());
            cs.setBoolean(9,b.isActive());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(10);
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
    public ResponseModel delete(int perspectiveId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveDelete(?)}");
            cs.setInt(1,perspectiveId);
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
    public ResponseModel list(int year,int countryId) {
        ArrayList<BscPerspectiveEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveList(?,?)}");
            cs.setInt(1, year);
            cs.setInt(2, countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscPerspectiveEntity b=new BscPerspectiveEntity();
                b.setId(rs.getInt("id"));
                b.setCode(rs.getString("code"));
                b.setName(rs.getString("name"));
                b.setObjetive(rs.getString("objetive"));
                b.setYear(rs.getInt("year"));
                b.setColor(rs.getString("color"));
                b.setCountryId(rs.getInt("countryId"));
                b.setCountry(rs.getString("country"));
                b.setLeaderId(rs.getInt("leaderId"));
                b.setLeader(rs.getString("leader"));
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
    public ResponseModel listActive(int year,int personId,int countryId) {
        ArrayList<BscPerspectiveEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveListActive(?,?,?)}");
            cs.setInt(1,year);
            cs.setInt(2,personId);
            cs.setInt(3,countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscPerspectiveEntity b=new BscPerspectiveEntity();
                b.setId(rs.getInt("id"));
                b.setCode(rs.getString("code"));
                b.setName(rs.getString("name"));
                b.setObjetive(rs.getString("objetive"));
                b.setYear(rs.getInt("year"));
                b.setColor(rs.getString("color"));
                b.setCountryId(rs.getInt("countryId"));
                b.setCountry(rs.getString("country"));
                b.setLeaderId(rs.getInt("leaderId"));
                b.setLeader(rs.getString("leader"));
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
    public ResponseModel total(int year,int countryId) {
        ArrayList<BscPerspectiveModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectiveTotal(?,?)}");
            cs.setInt(1,year);
            cs.setInt(2,countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscPerspectiveModel b=new BscPerspectiveModel();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setStrategicObjetives(rs.getInt("strategicObjetives"));
                b.setIndicators(rs.getInt("indicators"));
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
    public ResponseModel percentage(int year) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectivePercentage(?)}");
            cs.setInt(1, year);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    public ResponseModel percentageMonth(int perspectiveId) {
        DataModel data=new DataModel();
        ArrayList<String>dataX=new ArrayList();
        ArrayList<Integer>dataY=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscPerspectivePercentageMonth(?)}");
            cs.setInt(1, perspectiveId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
}
