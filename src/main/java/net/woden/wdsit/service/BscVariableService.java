package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscVariableService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscVariableEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscVariableCreate(?,?,?,?)}");
            cs.setString(1,b.getName());
            cs.setString(2,b.getValueType());
            cs.setInt(3,b.getDefaultValue());
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
    public ResponseModel update(BscVariableEntity b) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscVariableUpdate(?,?,?,?,?,?)}");
            cs.setInt(1,b.getId());
            cs.setString(2,b.getName());
            cs.setString(3,b.getValueType());
            cs.setInt(4,b.getDefaultValue());
            cs.setBoolean(5,b.isActive());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates=cs.getInt(6);
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
    public ResponseModel delete(int variableId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscVariableDelete(?)}");
            cs.setInt(1,variableId);
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
    public ResponseModel list() {
        ArrayList<BscVariableEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscVariableList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscVariableEntity b=new BscVariableEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setValueType(rs.getString("valueType"));
                b.setDefaultValue(rs.getInt("defaultValue"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
    public ResponseModel listActive() {
        ArrayList<BscVariableEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscVariableListActive()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                BscVariableEntity b=new BscVariableEntity();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setValueType(rs.getString("valueType"));
                b.setDefaultValue(rs.getInt("defaultValue"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
