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
import net.woden.wdsit.entity.ScpAuditPreviousSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpAuditPreviousSerialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditPreviousSerialService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection dssap;
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

    public ResponseModel create(int userId, int auditPreviousId, ScpAuditPreviousSerialEntity s) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousSerialCreate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, auditPreviousId);
            cs.setString(2, s.getSerial());
            cs.setString(3, s.getCodigoSap());
            cs.setString(4, s.getDescripcion());
            cs.setString(5, s.getMotivoScrap());
            cs.setString(6, s.getEstado());
            cs.setInt(7, userId);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(8);
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

    public ResponseModel createAll(int userId, int auditPreviousId, ArrayList<ScpAuditPreviousSerialEntity> sp) {
        int inserts = 0;

        for (ScpAuditPreviousSerialEntity s : sp) {
            Connection cn = this.ds.openConnection();
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousSerialCreate(?,?,?,?,?,?,?,?)}");
                cs.setInt(1, auditPreviousId);
                cs.setString(2, s.getSerial());
                cs.setString(3, s.getCodigoSap());
                cs.setString(4, s.getDescripcion());
                cs.setString(5, s.getMotivoScrap());
                cs.setString(6, s.getEstado());
                cs.setInt(7, userId);
                cs.registerOutParameter(8, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(8);
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

    public ResponseModel delete(int auditPreviousId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousSerialDelete(?)}");
            cs.setInt(1, auditPreviousId);
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

    public ResponseModel list(int auditPreviousId) {
        ArrayList<ScpAuditPreviousSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousSerialList(?)}");
            cs.setInt(1, auditPreviousId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditPreviousSerialEntity b = new ScpAuditPreviousSerialEntity();
                b.setId(rs.getInt("id"));
                b.setAuditPreviousId(rs.getInt("auditPreviousId"));
                b.setAuditPreviousName(rs.getString("auditPreviousName"));
                b.setSerial(rs.getString("serial"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setMotivoScrap(rs.getString("motivoScrap"));
                b.setEstado(rs.getString("estado"));
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

    public ResponseModel findBySerial(String serial, String cliente) {
        ScpAuditPreviousSerialModel b = null;
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
            cn = this.dssap.openConnection();
        }

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ScpAuditPreviousFindSerial(?,?)}");
            cs.setString(1, serial);
            cs.setString(2, cliente);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditPreviousSerialModel();
                b.setSerial(rs.getString("serial"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setDescripcion(rs.getString("descripcion"));
                b.setMotivoScrap(rs.getString("motivoScrap"));
                b.setEstado(rs.getString("estado"));
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
        if (b == null) {
            return new ResponseModel(getClass().getSimpleName(), "NO OK", "NO EXISTE EL SERIAL", 200);
        } else {
            return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
        }

    }

}
