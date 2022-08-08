package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.GenPersonCustomerEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenPersonCustomerService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(GenPersonCustomerEntity g){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonCustomerCreate(?,?,?)}");
            cs.setInt(1, g.getPersonId());
            cs.setInt(2, g.getCustomerId());
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
    public ResponseModel delete(int personId,int customerId){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonCustomerDelete(?,?)}");
            cs.setInt(1, personId);
            cs.setInt(2, customerId);
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
    public ResponseModel list(int personId,int countryId) {
        ArrayList<GenCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonCustomerList(?,?)}");
            cs.setInt(1,personId);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenCustomerEntity g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setDescription(rs.getString("description"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    public ResponseModel listCustomer(int personId,int countryId) {
        ArrayList<GenPersonCustomerEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenPersonCustomerListCustomer(?,?)}");
            cs.setInt(1,personId);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonCustomerEntity g = new GenPersonCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setPersonId(rs.getInt("personId"));
                g.setCustomerId(rs.getInt("customerId"));
                g.setPerson(rs.getString("person"));
                g.setCustomer(rs.getString("customer"));
                list.add(g);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
