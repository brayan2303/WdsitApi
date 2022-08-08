package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.WlsPrealertEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WlsPrealertService {

    @Autowired
    DataSourceConnection ds;

    public ResponseModel create(WlsPrealertEntity w) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertCreate(?,?,?,?)}");
            cs.setString(1, w.getName());
            cs.setInt(2, w.getProyectId());
            cs.setInt(3, w.getCreationUserId());
            cs.registerOutParameter(4, Types.VARCHAR);
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

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertDelete(?)}");
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
    public ResponseModel list(int proyectId) {
        ArrayList<WlsPrealertEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertList(?)}");
            cs.setInt(1, proyectId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                WlsPrealertEntity w=new WlsPrealertEntity();
                w.setId(rs.getInt("id"));
                w.setName(rs.getString("name"));
                w.setProyectId(rs.getInt("proyectId"));
                w.setProyect(rs.getString("proyect"));
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
}
