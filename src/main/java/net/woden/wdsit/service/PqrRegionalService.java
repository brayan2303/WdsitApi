package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrRegionalEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrRegionalService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrRegionalEntity p, int countryId) {
        int inserts=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrRegionalCreate(?,?,?,?)}");
            cs.setString(1,p.getName());
            cs.setInt(2,p.getCityId());
            cs.setInt(3, countryId);
            cs.registerOutParameter(4,Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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
    public ResponseModel update(PqrRegionalEntity p) {
        int updates=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrRegionalUpdate(?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2,p.getName());
            cs.setInt(3,p.getCityId());
            cs.setBoolean(4,p.isActive());
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
    public ResponseModel findAll(int cityId, int countryId) {
        ArrayList<PqrRegionalEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrRegionalFindAll(?,?)}");
            cs.setInt(1,cityId);
            cs.setInt(2, countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrRegionalEntity p=new PqrRegionalEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCityId(rs.getInt("cityId"));
                p.setActive(rs.getBoolean("active"));
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
    public ResponseModel list(int citytId, int countryId) {
        ArrayList<PqrRegionalEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrRegionalList(?,?)}");
            cs.setInt(1, citytId);
            cs.setInt(2, countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrRegionalEntity p=new PqrRegionalEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCountryId(rs.getInt("countryId"));
                p.setCountry(rs.getString("country"));
                p.setDepartmentId(rs.getInt("departmentId"));
                p.setDepartment(rs.getString("department"));
                p.setCityId(rs.getInt("cityId"));
                p.setCity(rs.getString("city"));
                p.setActive(rs.getBoolean("active"));
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
