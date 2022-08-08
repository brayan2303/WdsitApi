package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenApplicationPersonProfileEntity;
import net.woden.wdsit.entity.GenProfileEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenApplicationPersonProfileService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(int applicationId,int personId,int profileId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenApplicationPersonProfileCreate(?,?,?,?)}");
            cs.setInt(1,applicationId);
            cs.setInt(2,personId);
            cs.setInt(3,profileId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts=cs.getInt(4);
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
    public ResponseModel delete(int applicationPersonProfileId,int applicationId,int personId,int profileId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenApplicationPersonProfileDelete(?,?,?,?)}");
            cs.setInt(1,applicationPersonProfileId);
            cs.setInt(2,applicationId);
            cs.setInt(3,personId);
            cs.setInt(4,profileId);
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
    public ResponseModel list(int applicationId,int personId) {
        ArrayList<GenApplicationPersonProfileEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenApplicationPersonProfileList(?,?)}");
            cs.setInt(1,applicationId);
            cs.setInt(2,personId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                GenApplicationPersonProfileEntity g=new GenApplicationPersonProfileEntity();
                g.setId(rs.getInt("id"));
                g.setProfileId(rs.getInt("profileId"));
                g.setProfile(rs.getString("profile"));
                g.setActive(rs.getBoolean("active"));
                list.add(g);
            }
            cn.commit();
            cs.close();
            rs.close();;
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
    public ResponseModel listProfile(int personId,String applicationName) {
        GenProfileEntity g=null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_GenApplicationPersonProfileListProfile(?,?)}");
            cs.setInt(1,personId);
            cs.setString(2,applicationName);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                g=new GenProfileEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setApplicationId(rs.getInt("applicationId"));
                g.setApplication(rs.getString("application"));
                g.setActive(rs.getBoolean("active"));
            }
            cn.commit();
            cs.close();
            rs.close();;
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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }
}
