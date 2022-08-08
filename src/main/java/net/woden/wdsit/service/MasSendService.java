package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.MasSendEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasSendService {
    @Autowired
    private DataSourceConnection ds;
    
    public ResponseModel create(MasSendEntity m){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendCreate(?,?,?,?)}");
            cs.setInt(1, m.getMailId());
            cs.setInt(2, m.getCreationUserId());
            cs.setInt(3, m.getApprovalUserId());
            cs.registerOutParameter(4, Types.INTEGER);
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
    public ResponseModel approval(int id){
        int inserts=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendApproval(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(2);
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
    public ResponseModel send(int id,String message,int sendingUserId){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendSend(?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, message);
            cs.setInt(3, sendingUserId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(4);
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
    public ResponseModel approveReject(int id,String message){
        int updates=0;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendAproveReject(?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, message);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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
    public ResponseModel find(int mailId){
        MasSendEntity m=null;
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendFind(?)}");
            cs.setInt(1, mailId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                m=new MasSendEntity();
                m.setId(rs.getInt("id"));
                m.setMessage(rs.getString("message"));
                m.setMailId(rs.getInt("mailId"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setApprovalDate(rs.getString("approvalDate"));
                m.setApprovalUserId(rs.getInt("approvalUserId"));
                m.setSendingDate(rs.getString("sendingDate"));
                m.setSendingUserId(rs.getInt("sendingUserId"));
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
    public ResponseModel list(int mailId,int approvalUserId){
        ArrayList<MasSendEntity>list=new ArrayList();
        Connection cn=this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_MasSendList(?,?)}");
            cs.setInt(1, mailId);
            cs.setInt(2, approvalUserId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                MasSendEntity m=new MasSendEntity();
                m.setId(rs.getInt("id"));
                m.setMessage(rs.getString("message"));
                m.setMailId(rs.getInt("mailId"));
                m.setMail(rs.getString("mail"));
                m.setCreationDate(rs.getString("creationDate"));
                m.setCreationUserId(rs.getInt("creationUserId"));
                m.setCreationUser(rs.getString("creationUser"));
                m.setApprovalDate(rs.getString("approvalDate"));
                m.setApprovalUserId(rs.getInt("approvalUserId"));
                m.setApprovalUser(rs.getString("approvalUser"));
                m.setSendingDate(rs.getString("sendingDate"));
                m.setSendingUserId(rs.getInt("sendingUserId"));
                m.setSendingUser(rs.getString("sendingUser"));
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
