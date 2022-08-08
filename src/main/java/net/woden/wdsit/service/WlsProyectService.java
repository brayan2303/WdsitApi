package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.WlsProyectEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WlsProyectService {

    @Autowired
    DataSourceConnection ds;

    public ResponseModel create(WlsProyectEntity w) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, w.getName());
            cs.setString(2, w.getPrefix());
            cs.setInt(3, w.getCountryId());
            cs.setInt(4, w.getCustomerId());
            cs.setInt(5, w.getServerId());
            cs.setString(6, w.getDataBaseName());
            cs.setInt(7, w.getCreationUserId());
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.execute();
            inserts = cs.getInt(8);
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

    public ResponseModel update(WlsProyectEntity w) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectUpdate(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, w.getId());
            cs.setString(2, w.getName());
            cs.setString(3, w.getPrefix());
            cs.setInt(4, w.getCountryId());
            cs.setInt(5, w.getCustomerId());
            cs.setInt(6, w.getServerId());
            cs.setString(7, w.getDataBaseName());
            cs.setBoolean(8, w.isActive());
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.execute();
            updates = cs.getInt(9);
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
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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
        ArrayList<WlsProyectEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                WlsProyectEntity w=new WlsProyectEntity();
                w.setId(rs.getInt("id"));
                w.setName(rs.getString("name"));
                w.setPrefix(rs.getString("prefix"));
                w.setCountryId(rs.getInt("countryId"));
                w.setCountry(rs.getString("country"));
                w.setCustomerId(rs.getInt("customerId"));
                w.setCustomer(rs.getString("customer"));
                w.setServerId(rs.getInt("serverId"));
                w.setServer(rs.getString("server"));
                w.setDataBaseName(rs.getString("dataBaseName"));
                w.setCreationDate(rs.getString("creationDate"));
                w.setCreationUserId(rs.getInt("creationUserId"));
                w.setCreationUser(rs.getString("creationUser"));
                w.setActive(rs.getBoolean("active"));
                list.add(w);
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
    public ResponseModel listByCustomer(int countryId,int customerId) {
        ArrayList<WlsProyectEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectListByCustomer(?,?)}");
            cs.setInt(1, countryId);
            cs.setInt(2, customerId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                WlsProyectEntity w=new WlsProyectEntity();
                w.setId(rs.getInt("id"));
                w.setName(rs.getString("name"));
                w.setPrefix(rs.getString("prefix"));
                w.setCountryId(rs.getInt("countryId"));
                w.setCountry(rs.getString("country"));
                w.setCustomerId(rs.getInt("customerId"));
                w.setCustomer(rs.getString("customer"));
                w.setServerId(rs.getInt("serverId"));
                w.setServer(rs.getString("server"));
                w.setDataBaseName(rs.getString("dataBaseName"));
                w.setCreationDate(rs.getString("creationDate"));
                w.setCreationUserId(rs.getInt("creationUserId"));
                w.setCreationUser(rs.getString("creationUser"));
                w.setActive(rs.getBoolean("active"));
                list.add(w);
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
    public ResponseModel findById(int id) {
        WlsProyectEntity w=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsProyectFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                w=new WlsProyectEntity();
                w.setId(rs.getInt("id"));
                w.setName(rs.getString("name"));
                w.setPrefix(rs.getString("prefix"));
                w.setCountryId(rs.getInt("countryId"));
                w.setCountry(rs.getString("country"));
                w.setCustomerId(rs.getInt("customerId"));
                w.setCustomer(rs.getString("customer"));
                w.setServerId(rs.getInt("serverId"));
                w.setServer(rs.getString("server"));
                w.setDataBaseName(rs.getString("dataBaseName"));
                w.setCreationDate(rs.getString("creationDate"));
                w.setCreationUserId(rs.getInt("creationUserId"));
                w.setCreationUser(rs.getString("creationUser"));
                w.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", w, 200);
    }
}
