package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MasAccountEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasAccountService {
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private EncryptUtility eu;
    
    public ResponseModel create(MasAccountEntity m){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasAccountCreate(?,?,?,?,?,?,?,?)}");
            cs.setString(1, m.getMail());
            cs.setString(2, m.getName());
            cs.setString(3, m.getPosition());
            cs.setString(4, m.getProvider());
            cs.setInt(5,m.getPort());
            cs.setString(6, this.eu.encode(m.getPassword()));
            cs.setInt(7, m.getCreationUserId());
            cs.registerOutParameter(8, Types.INTEGER);
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
    public ResponseModel update(MasAccountEntity m){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasAccountUpdate(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, m.getId());
            cs.setString(2, m.getMail());
            cs.setString(3, m.getName());
            cs.setString(4, m.getPosition());
            cs.setString(5, m.getProvider());
            cs.setInt(6,m.getPort());
            cs.setString(7, this.eu.encode(m.getPassword()));
            cs.setBoolean(8, m.isActive());
            cs.registerOutParameter(9, Types.INTEGER);
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
    public ResponseModel delete(int id){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasAccountDelete(?)}");
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
    public ResponseModel list(){
        ArrayList<MasAccountEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasAccountList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                MasAccountEntity m=new MasAccountEntity();
                m.setId(rs.getInt("id"));
                m.setMail(rs.getString("mail"));
                m.setName(rs.getString("name"));
                m.setPosition(rs.getString("position"));
                m.setProvider(rs.getString("provider"));
                m.setPort(rs.getInt("port"));
                m.setPassword(this.eu.decode(rs.getString("password")));
                m.setCreationDate(rs.getString("creationDate"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                m.setActive(rs.getBoolean("active"));
                list.add(m);
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
    public ResponseModel listActive(){
        ArrayList<MasAccountEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasAccountListActive()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                MasAccountEntity m=new MasAccountEntity();
                m.setId(rs.getInt("id"));
                m.setMail(rs.getString("mail"));
                m.setName(rs.getString("name"));
                m.setPosition(rs.getString("position"));
                m.setPort(rs.getInt("port"));
                m.setPassword(this.eu.decode(rs.getString("password")));
                m.setCreationDate(rs.getString("creationDate"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                m.setActive(rs.getBoolean("active"));
                list.add(m);
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
