package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MasApprovalEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasApprovalService {
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private EncryptUtility eu;
    
    public ResponseModel create(MasApprovalEntity m){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalCreate(?,?,?)}");
            cs.setInt(1, m.getMailId());
            cs.setInt(2, m.getApprovalUserId());
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
    public ResponseModel update(MasApprovalEntity m){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalUpdate(?,?,?,?,?)}");
            cs.setInt(1, m.getId());
            cs.setInt(2, m.getMailId());
            cs.setInt(3, m.getApprovalUserId());
            cs.setBoolean(4,m.isActive());
            cs.registerOutParameter(5, Types.INTEGER);
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
    public ResponseModel delete(int id){
        int deletes=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalDelete(?)}");
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
        ArrayList<MasApprovalEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                MasApprovalEntity m=new MasApprovalEntity();
                m.setId(rs.getInt("id"));
                m.setMailId(rs.getInt("mailId"));
                m.setMail(rs.getString("subject"));
                m.setApprovalUserId(rs.getInt("approvalUserId"));
                m.setApprovalUser(rs.getString("approvalUser"));
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
        ArrayList<MasApprovalEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalListActive()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                MasApprovalEntity m=new MasApprovalEntity();
                m.setId(rs.getInt("id"));
                m.setMailId(rs.getInt("mailId"));
                m.setMail(rs.getString("subject"));
                m.setApprovalUserId(rs.getInt("approvalUserId"));
                m.setApprovalUser(rs.getString("approvalUser"));
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
    public ResponseModel find(int mailId){
        MasApprovalEntity m=null;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasApprovalFind(?)}");
            cs.setInt(1, mailId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                m=new MasApprovalEntity();
                m.setId(rs.getInt("id"));
                m.setMailId(rs.getInt("mailId"));
                m.setMail(rs.getString("subject"));
                m.setApprovalUserId(rs.getInt("approvalUserId"));
                m.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", m, 200);
    }
}
