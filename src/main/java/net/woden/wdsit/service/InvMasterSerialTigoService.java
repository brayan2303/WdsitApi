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
import net.woden.wdsit.entity.InvMasterSerialTigoEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvMasterSerialTigoService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(InvMasterSerialTigoEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialCreateTigo(?,?,?,?,?,?)}");
            cs.setString(1, i.getSerial());
            cs.setString(2, i.getMac());
            cs.setInt(3, userId);
            cs.setString(4, i.getStatus());
            cs.setInt(5, i.getPalletId());
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialDeleteTIgo(?)}");
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

    public ResponseModel list(int palletId) {                                   //listar en pantalla info
        ArrayList<InvMasterSerialTigoEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialListTigo(?)}");
            cs.setInt(1, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterSerialTigoEntity i = new InvMasterSerialTigoEntity();
                i.setId(rs.getInt("Id"));
                i.setSerial(rs.getString("serial"));
                i.setMac(rs.getString("mac"));
                i.setUserId(rs.getInt("userId"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setPallet(rs.getString("pallet"));
                i.setCodigoSap(rs.getString("codigoSap"));
                i.setLocation(rs.getString("location"));
                i.setTypology(rs.getString("typology"));
                i.setStatus(rs.getString("status"));
                i.setActive(rs.getBoolean("active"));
                list.add(i);
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

    public ResponseModel validationRR(String serial, String codigoSap) {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvInventoryIQ09Validation(?,?)}");
            cs.setString(1, serial);
            cs.setString(2, codigoSap);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel validationRRSerial(String serial) {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvInventoryIQ09ValidationSerial(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel validationMissing(String serial) {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvValidationSeriesTigoMissing(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel validationSpare(String serial) {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvValidationSeriesTigoSpare(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel validationSerial(String serial) {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialValidationTigo(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setValidation(rs.getString("validation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel update(int id) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitUpdateStatusTigo(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            update = cs.getInt(2);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", update, 200);
    }

    public ResponseModel missingCount() {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialTigoMissingCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setMissingFound(rs.getString("missingFound"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel missingCountFound() {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvValidationSeriesTigoMissingCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setMissing(rs.getString("missing"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel spareCount() {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialTigoSpareCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setSpareFound(rs.getString("spareFound"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel spareCountFound() {                                   //listar en pantalla info
        InvMasterSerialTigoEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call InvValidationSeriesTigoSpareCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialTigoEntity();
                i.setSpare(rs.getString("spare"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

}
