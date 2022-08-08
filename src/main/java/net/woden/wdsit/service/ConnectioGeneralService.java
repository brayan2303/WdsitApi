/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ConnectioGeneralEntity;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ConnectioGeneralService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private EncryptUtility eu;

    public ResponseModel create(ConnectioGeneralEntity c, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, this.eu.encode(c.getDriver()));
            cs.setString(2, this.eu.encode(c.getUrl()));
            cs.setString(3, this.eu.encode(c.getName()));
            cs.setString(4, this.eu.encode(c.getPassword()));
            cs.setInt(5, c.getCustomerId());
            cs.setInt(6, c.getCountryId());
            cs.setInt(7, userId);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(8);
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

    public ResponseModel update(ConnectioGeneralEntity c, int userId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, c.getId());
            cs.setString(2, this.eu.encode(c.getDriver()));
            cs.setString(3, this.eu.encode(c.getUrl()));
            cs.setString(4, this.eu.encode(c.getName()));
            cs.setString(5, this.eu.encode(c.getPassword()));
            cs.setInt(6, c.getCustomerId());
            cs.setInt(7, c.getCountryId());
            cs.setInt(8, userId);
            cs.setBoolean(9, c.isActive());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(10);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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
        ArrayList<ConnectioGeneralEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ConnectioGeneralEntity c = new ConnectioGeneralEntity();
                c.setId(rs.getInt("id"));
                c.setDriver(rs.getString("driver"));
                c.setUrl(rs.getString("url"));
                c.setName(rs.getString("name"));
                c.setPassword(rs.getString("password"));
                c.setCustomer(rs.getString("customer"));
                c.setCountry(rs.getString("country"));
                c.setActive(rs.getBoolean("active"));
                list.add(c);
            }
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
        ConnectioGeneralEntity c = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new ConnectioGeneralEntity();
                c.setId(rs.getInt("id"));
                c.setDriver(this.eu.decode(rs.getString("driver")));
                c.setUrl(this.eu.decode(rs.getString("url")));
                c.setName(this.eu.decode(rs.getString("name")));
                c.setPassword(this.eu.decode(rs.getString("password")));
                c.setCustomerId(rs.getInt("customerId"));
                c.setCountryId(rs.getInt("countryId"));
                c.setCreationDate(rs.getString("creationDate"));
                c.setUserId(rs.getInt("userId"));
                c.setUpdateDate(rs.getString("updateDate"));
                c.setUserIdUpdate(rs.getInt("userIdUpdate"));
                c.setActive(rs.getBoolean("active"));

            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel findByIdCustomer(int customerId) {
        ArrayList<ConnectioGeneralEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralFindByCustomerId(?)}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ConnectioGeneralEntity c = new ConnectioGeneralEntity();
                c.setId(rs.getInt("id"));
                c.setDriver(rs.getString("driver"));
                c.setUrl(rs.getString("url"));
                c.setName(rs.getString("name"));
                c.setPassword(rs.getString("password"));
                c.setCustomerId(rs.getInt("customerId"));
                c.setCreationDate(rs.getString("creationDate"));
                c.setUserId(rs.getInt("userId"));
                c.setUpdateDate(rs.getString("updateDate"));
                c.setUserIdUpdate(rs.getInt("userIdUpdate"));
                c.setActive(rs.getBoolean("active"));
                list.add(c);
            }
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

    public ResponseModel findAll() {
        ArrayList<GenCustomerEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralCustomer()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCustomerEntity g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel listCountry() {
        ArrayList<GenCountryEntity> listCountry = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ConnectioGeneralFindByCountryId()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCountryEntity g = new GenCountryEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setActive(rs.getBoolean("active"));
                listCountry.add(g);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", listCountry, 200);
    }
}
