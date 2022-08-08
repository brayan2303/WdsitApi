package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.BscFormulaVariableEntity;
import net.woden.wdsit.entity.BscVariableEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BscFormulaVariableService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(BscFormulaVariableEntity b) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscFormulaVariableCreate(?,?,?,?)}");
            cs.setInt(1,b.getFormulaId());
            cs.setString(2,b.getType());
            cs.setInt(3,b.getVariableId());
            cs.registerOutParameter(4,Types.INTEGER);
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

    public ResponseModel delete(int formulaVariableId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscFormulaVariableDelete(?)}");
            cs.setInt(1, formulaVariableId);
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

    public ResponseModel list(int formulaId) {
        ArrayList<BscFormulaVariableEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscFormulaVariableList(?)}");
            cs.setInt(1, formulaId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BscFormulaVariableEntity b = new BscFormulaVariableEntity();
                b.setId(rs.getInt("id"));
                b.setFormulaId(rs.getInt("formulaId"));
                b.setType(rs.getString("type"));
                b.setVariableId(rs.getInt("variableId"));
                b.setVariable(rs.getString("variable"));
                list.add(b);
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
    public ResponseModel listMeasurementId(int measurementId) {
        ArrayList<BscVariableEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_BscFormulaVariableListMeasurementId(?)}");
            cs.setInt(1, measurementId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                BscVariableEntity b = new BscVariableEntity();
                b.setId(rs.getInt("id"));
                list.add(b);
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
}
