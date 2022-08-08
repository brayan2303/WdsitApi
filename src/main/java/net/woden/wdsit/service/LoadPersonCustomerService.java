/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.LoadPersonCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadPersonCustomerService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(LoadPersonCustomerEntity g) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadPersonCustomerCreate(?,?,?)}");
            cs.setInt(1, g.getPersonId());
            cs.setInt(2, g.getCustomerId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel list() {
        ArrayList<LoadPersonCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadPersonCustomerLis(?)}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadPersonCustomerEntity g = new LoadPersonCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setPersonId(rs.getInt("personId"));
                g.setCustomerId(rs.getInt("customerId"));

                list.add(g);
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

    public ResponseModel delete(int personId, int customerId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadpersonCustomerDelete(?,?)}");
            cs.setInt(1, personId);
            cs.setInt(2, customerId);
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

    public ResponseModel findAll(int personId) {
        ArrayList<GenCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFindAll(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCustomerEntity a = new GenCustomerEntity();
                a.setId(rs.getInt("id"));
                a.setCode(rs.getString("code"));
                a.setDescription(rs.getString("description"));
                a.setIncomeActive(rs.getBoolean("incomeActive"));
                a.setActive(rs.getBoolean("active"));
                list.add(a);
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

    public ResponseModel findCustomerByPersonId(int personId) {
        GenCustomerEntity customer=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFindCustomerByPersonId(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                customer=new GenCustomerEntity();
                customer.setId(rs.getInt("id"));
                customer.setCode(rs.getString("code"));
                customer.setDescription(rs.getString("description"));
                customer.setIncomeActive(rs.getBoolean("incomeActive"));
                customer.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", customer, 200);
    }
    
        public ResponseModel findCustomerByPersonIdList(int personId) {
        ArrayList<GenCustomerEntity> customer= new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFindCustomerByPersonIdList(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCustomerEntity l= new GenCustomerEntity();
                l.setId(rs.getInt("id"));
                l.setCode(rs.getString("code"));
                l.setDescription(rs.getString("description"));
                l.setIncomeActive(rs.getBoolean("incomeActive"));
                l.setActive(rs.getBoolean("active"));
                customer.add(l);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", customer, 200);
    }
}
