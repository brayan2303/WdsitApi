package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrMasterEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrMasterService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrMasterEntity p, int countryId) {
        int inserts=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterCreate(?,?,?,?,?)}");
            cs.setString(1, p.getName());
            cs.setString(2,p.getValue());
            cs.setInt(3,p.getMasterTypeId());
            cs.setInt(4, countryId);
            cs.registerOutParameter(5,Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(5);
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
    public ResponseModel update(PqrMasterEntity p) {
        int updates=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,p.getId());
            cs.setString(2, p.getName());
            cs.setString(3,p.getValue());
            cs.setInt(4,p.getMasterTypeId());
            cs.setBoolean(5,p.isActive());
            cs.registerOutParameter(6,Types.INTEGER);
            cs.execute();
            updates=cs.getInt(6);
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
    public ResponseModel delete(int id) {
        int deletes=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterDelete(?)}");
            cs.setInt(1,id);
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
    public ResponseModel findAll(String masterType, int countryId) {
        ArrayList<PqrMasterEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterFindAll(?,?)}");
            cs.setString(1, masterType);
            cs.setInt(2, countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrMasterEntity p=new PqrMasterEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setValue(rs.getString("value"));
                p.setMasterTypeId(rs.getInt("masterTypeId"));
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
    public ResponseModel findId(String masterType,String name) {
        int id=0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterFindId(?,?)}");
            cs.setString(1, masterType);
            cs.setString(2, name);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                id=rs.getInt("id");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", id, 200);
    }
    public ResponseModel list(int countryId) {
        ArrayList<PqrMasterEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMasterList(?)}");
            cs.setInt(1, countryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                PqrMasterEntity p=new PqrMasterEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setValue(rs.getString("value"));
                p.setMasterTypeId(rs.getInt("masterTypeId"));
                p.setMasterType(rs.getString("masterType"));
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
