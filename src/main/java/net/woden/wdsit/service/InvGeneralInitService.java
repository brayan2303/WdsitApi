/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvGeneralInitEntity;
import net.woden.wdsit.model.InvGeneralInitModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvGeneralInitService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(InvGeneralInitEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitCreate(?,?,?,?,?,?)}");
            cs.setString(1, i.getStore());
            cs.setString(2, i.getCountingType());
            cs.setString(3, i.getGoodDeft());
            cs.setString(4, i.getParameterizationId());
            cs.setInt(5, userId);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setId(rs.getInt("id"));
                a.setCountingType(rs.getString("countingType"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setParameterizationId(rs.getString("parameterizationId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUserName(rs.getString("userName"));
                a.setTipo(rs.getString("tipo"));
                a.setParametrizacion(rs.getString("parametrizacion"));
                a.setActive(rs.getBoolean("active"));
                a.setCloseOpen(rs.getBoolean("closeOpen"));
                a.setStore(rs.getString("store"));
                a.setType(rs.getString("type"));
                list.add(a);
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
    
      public ResponseModel listDeft() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitListDeft()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setId(rs.getInt("id"));
                a.setCountingType(rs.getString("countingType"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setParameterizationId(rs.getString("parameterizationId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUserName(rs.getString("userName"));
                a.setTipo(rs.getString("tipo"));
                a.setParametrizacion(rs.getString("parametrizacion"));
                a.setActive(rs.getBoolean("active"));
                a.setCloseOpen(rs.getBoolean("closeOpen"));
                a.setStore(rs.getString("store"));
                a.setType(rs.getString("type"));
                list.add(a);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitDelete(?)}");
            cs.setInt(1, id);
            deletes = cs.executeUpdate();
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

    public ResponseModel findById(int id) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setCountingType(rs.getString("countingType"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setParameterizationId(rs.getString("parameterizationId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel update(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitUpdateStatus(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
            cs.close();
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

    public ResponseModel findByValidation(String countingType, String goodDeft, String store, String parameterizationId) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitValidation(?,?,?,?)}");
            cs.setString(1, countingType);
            cs.setString(2, goodDeft);
            cs.setString(3, store);
            cs.setString(4, parameterizationId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setValidacion(rs.getString("Validacion"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel listWarehouse() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitStore()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setWarehouse(rs.getString("warehouse"));
                list.add(a);
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

    public ResponseModel listWarehouseDeft() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitStoreDeft()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setWarehouse(rs.getString("warehouse"));
                list.add(a);
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

    public ResponseModel listLocation(String warehouse) {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitLocation(?)}");
            cs.setString(1, warehouse);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setLocation(rs.getString("location"));
                list.add(a);
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

    public ResponseModel listPartNumber() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_invGeneralInitPartNumber()}");
        
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setPartNumber(rs.getString("partNumber"));
                a.setDescription(rs.getString("description"));
                list.add(a);
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
    
        public ResponseModel listPartNumberSerial(String partNumber, String serial) {
         InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitValidationParteNumberSerial(?,?)}");
            cs.setString(1, partNumber);
            cs.setString(2, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setValidacionSerial(rs.getString("validacionSerial"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }


    public ResponseModel findByIdCondition(int id) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitCondition(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setCondition(rs.getString("condition"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel findByIdQuantity(int id) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitQuantity(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setQuantity(rs.getInt("quantity"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel updateCounting(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneraCountingUpdateCounting(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
            cs.close();
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
    
        public ResponseModel updateCountingActive(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralCountigUpdateStatus(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
            cs.close();
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

    public ResponseModel findByValidationSerial(String serial, int id) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitValidationSerial(?,?)}");
            cs.setString(1, serial);
            cs.setInt(2, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setValidacionSerial(rs.getString("validacionSerial"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel findByValidationDeft(String countingType, String goodDeft, String parameterizationId) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitValidationDeft(?,?,?)}");
            cs.setString(1, countingType);
            cs.setString(2, goodDeft);
            cs.setString(3, parameterizationId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setValidacion(rs.getString("Validacion"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel findByValidationPartNumber(String partNumber) {
        InvGeneralInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitValidationPartNumber(?)}");
            cs.setString(1, partNumber);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new InvGeneralInitEntity();
                a.setValidacionPart(rs.getString("validacionPart"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }

    public ResponseModel listPerson() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitPerson()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setName(rs.getString("name"));
                list.add(a);
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

    public ResponseModel listInvHPonWTSPart() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvHPonWTSPart()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setWarehouse(rs.getString("warehouse"));
                a.setCounting(rs.getInt("counting"));
                list.add(a);
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

    public ResponseModel listInvHPonWTSDiv() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvHPonWTSDiv()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setWarehouse(rs.getString("warehouse"));
                a.setCounting(rs.getInt("counting"));
                list.add(a);
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

    public ResponseModel listCrossing() {
        ArrayList<InvGeneralInitModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGeneralInitCrossing()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitModel a = new InvGeneralInitModel();
                a.setStore(rs.getString("store"));
                a.setGoodDeft(rs.getString("goodDeft"));
                a.setCountingType(rs.getString("countingType"));
                a.setParameterizationId(rs.getString("parameterizationId"));
                a.setCantidadIngresada(rs.getString("cantidadIngresada"));
                a.setName(rs.getString("name"));
                list.add(a);
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
    
        public ResponseModel listInvDash() {
        ArrayList<InvGeneralInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvGenralDash()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvGeneralInitEntity a = new InvGeneralInitEntity();
                a.setWarehouse(rs.getString("warehouse"));
                a.setCounting(rs.getInt("counting"));
                list.add(a);
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
}
