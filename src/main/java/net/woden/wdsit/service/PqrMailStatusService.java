 package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrMailEntity;
import net.woden.wdsit.entity.PqrMailStatusEntity;
import net.woden.wdsit.entity.PqrStatusEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PqrMailStatusService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrMailStatusEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailStatusCreate(?,?,?)}");
            cs.setInt(1, p.getMailId());
            cs.setInt(2, p.getStatusId());
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
    public ResponseModel delete(int mailId,int statusId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailStatusDelete(?,?)}");
            cs.setInt(1, mailId);
            cs.setInt(2, statusId);
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
    public ResponseModel find(int statusId) {
        ArrayList<PqrMailEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailStatusFind(?)}");
            cs.setInt(1,statusId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrMailEntity p = new PqrMailEntity();
                p.setId(rs.getInt("id"));
                p.setSubject(rs.getString("subject"));
                p.setMessage(rs.getString("message"));
                p.setVariables(rs.getString("variables"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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
    public ResponseModel findAll(int mailId) {
        ArrayList<PqrStatusEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrMailStatusFindAll(?)}");
            cs.setInt(1,mailId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrStatusEntity p = new PqrStatusEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setIcon(rs.getString("icon"));
                p.setColor(rs.getString("color"));
                p.setActive(rs.getBoolean("active"));
                list.add(p);
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
