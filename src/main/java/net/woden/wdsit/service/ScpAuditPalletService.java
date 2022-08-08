/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHpConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.entity.ScpAuditPalletEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpAuditCrossingModel;
import net.woden.wdsit.model.ScpAuditPalletModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditPalletService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaConnection dsWMS;
    @Autowired
    private DataSourceSapColombiaClaroConnection dssColClaro;
    @Autowired
    private DataSourceSapColombiaDirectvConnection dssColDirec;
    @Autowired
    private DataSourceSapColombiaEtbConnection dssColEtb;
    @Autowired
    private DataSourceSapColombiaHpConnection dssColHp;
    @Autowired
    private DataSourceSapColombiaHughesConnection dssColHughes;
    @Autowired
    private DataSourceSapColombiaTigoConnection dssColTigo;

    public ResponseModel create(int userId, ScpAuditPalletEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletCreate(?,?,?,?,?)}");
            cs.setInt(1, p.getAuditId());
            cs.setString(2, p.getPallet());
            cs.setString(3, p.getTypology());
            cs.setString(4, p.getState());
            cs.setInt(5, p.getQuantity());
            cs.setInt(6, userId);
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(7);
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

    public ResponseModel createAll(int userId, int auditId, ArrayList<ScpAuditPalletEntity> sp) {
        int inserts = 0;
        for (ScpAuditPalletEntity p : sp) {
            Connection cn = this.ds.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletCreate(?,?,?,?,?,?,?)}");
                cs.setInt(1, auditId);
                cs.setString(2, p.getPallet());
                cs.setString(3, p.getTypology());
                cs.setString(4, p.getState());
                cs.setInt(5, p.getQuantity());
                cs.setInt(6, userId);
                cs.registerOutParameter(7, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(7);
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
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel delete(int auditId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletDelete(?)}");
            cs.setInt(1, auditId);
            cs.execute();
            inserts = 1;
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
        ArrayList<ScpAuditPalletEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditPalletEntity b = new ScpAuditPalletEntity();
                b.setId(rs.getInt("id"));
                b.setAuditId(rs.getInt("auditId"));
                b.setAuditName(rs.getString("auditName"));
                b.setPallet(rs.getString("pallet"));
                b.setTypology(rs.getString("typology"));
                b.setQuantity(rs.getInt("quantity"));
                b.setState(rs.getString("state"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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

    public ResponseModel findByAuditId(int auditId) {
        ArrayList<ScpAuditPalletEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletFindByAuditId(?)}");
            cs.setInt(1, auditId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditPalletEntity b = new ScpAuditPalletEntity();
                b.setId(rs.getInt("id"));
                b.setAuditId(rs.getInt("auditId"));
                b.setAuditName(rs.getString("auditName"));
                b.setPallet(rs.getString("pallet"));
                b.setTypology(rs.getString("typology"));
                b.setState(rs.getString("state"));
                b.setQuantity(rs.getInt("quantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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

    public ResponseModel findById(int auditPalletId) {
        ScpAuditPalletEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPalletFindById(?)}");
            cs.setInt(1, auditPalletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditPalletEntity();
                b.setId(rs.getInt("id"));
                b.setAuditId(rs.getInt("auditId"));
                b.setCustomer(rs.getString("customer"));
                b.setAuditName(rs.getString("auditName"));
                b.setPallet(rs.getString("pallet"));
                b.setTypology(rs.getString("typology"));
                b.setState(rs.getString("state"));
                b.setQuantity(rs.getInt("quantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel findByPalletWMS(String pallet, String cliente) {
        ScpAuditPalletModel b = null;
        Connection cn = null;
        if (cliente.equals("CLARO")) {
            cn = this.dssColClaro.openConnection();
        } else if (cliente.equals("PLATAFORMA MOVIL")) {
            cn = this.dssColClaro.openConnection();
        } else if (cliente.equals("RED EXTERNA")) {
            cn = this.dssColClaro.openConnection();
        } else if (cliente.equals("DIRECTV")) {
            cn = this.dssColDirec.openConnection();
        } else if (cliente.equals("ETB")) {
            cn = this.dssColEtb.openConnection();
        } else if (cliente.equals("HP INC")) {
            cn = this.dssColHp.openConnection();
        } else if (cliente.equals("HUGHES")) {
            cn = this.dssColHughes.openConnection();
        } else if (cliente.equals("TIGO")) {
            cn = this.dssColTigo.openConnection();
        } else {
            cn = this.dsWMS.openConnection();
        }

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ScpAuditFindByPallet(?,?)}");
            cs.setString(1, pallet);
            cs.setString(2, cliente);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditPalletModel();
                b.setPallet(rs.getString("pallet"));
                b.setTypology(rs.getString("typology"));
                b.setQuantity(rs.getInt("quantity"));
                b.setState(rs.getString("state"));
                b.setPallet(rs.getString("pallet"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel findSerialsByPalletWMS(String pallet, String customer) {
        ArrayList<ScpAuditCrossingModel> list = new ArrayList();
        Connection cn = null;
        if (customer.equals("CLARO")) {
            cn = this.dssColClaro.openConnection();
        } else if (customer.equals("PLATAFORMA MOVIL")) {
            cn = this.dssColClaro.openConnection();
        } else if (customer.equals("RED EXTERNA")) {
            cn = this.dssColClaro.openConnection();
        } else if (customer.equals("DIRECTV")) {
            cn = this.dssColDirec.openConnection();
        } else if (customer.equals("ETB")) {
            cn = this.dssColEtb.openConnection();
        } else if (customer.equals("HP INC")) {
            cn = this.dssColHp.openConnection();
        } else if (customer.equals("HUGHES")) {
            cn = this.dssColHughes.openConnection();
        } else if (customer.equals("TIGO")) {
            cn = this.dssColTigo.openConnection();
        } else {
            cn = this.dsWMS.openConnection();
        }

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ScpAuditFindSerialsByPallet(?)}");
            cs.setString(1, pallet);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditCrossingModel b = new ScpAuditCrossingModel();
                b.setSerial(rs.getString("serial"));
                b.setEstado(rs.getString("estado"));
                b.setPallet(rs.getString("pallet"));
                b.setMotivoScrap(rs.getString("motivoScrap"));
                list.add(b);
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
