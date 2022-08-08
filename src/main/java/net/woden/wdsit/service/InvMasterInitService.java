/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.entity.InvMasterInitEntity;
import net.woden.wdsit.model.ComCommodityPalletGenerateModel;
import net.woden.wdsit.model.InvMasterInitPalletModel;
import net.woden.wdsit.entity.InvMasterSerialEntity;
import net.woden.wdsit.model.InvMasterModel;
import net.woden.wdsit.model.InvMasterSerialQuantityModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class InvMasterInitService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(InvMasterInitEntity i, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitCreate(?,?,?,?,?,?,?)}");
            cs.setString(1, i.getPallet());
            cs.setString(2, i.getCodigoSap());
            cs.setString(3, i.getLocation());
            cs.setString(4, i.getTypology());
            cs.setString(5, "Pendiente");
            cs.setInt(6, userId);
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(7);
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

    public ResponseModel update(InvMasterInitEntity i, int userId) {
        int update = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, i.getId());
            cs.setString(2, i.getPallet());
            cs.setString(3, i.getCodigoSap());
            cs.setString(4, i.getLocation());
            cs.setString(5, i.getTypology());
            cs.setBoolean(6, i.isActive());
            cs.setInt(7, userId);
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            update = cs.getInt(8);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitDelete(?)}");
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

    public ResponseModel list(int userId) {                                   //listar en pantalla info
        ArrayList<InvMasterInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitList(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterInitEntity i = new InvMasterInitEntity();
                i.setId(rs.getInt("Id"));
                i.setPallet(rs.getString("pallet"));
                i.setCodigoSap(rs.getString("codigoSap"));
                i.setLocation(rs.getString("location"));
                i.setTypology(rs.getString("typology"));
                i.setStatus(rs.getString("status"));
                i.setActive(rs.getBoolean("active"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setUserIdCreation(rs.getInt("userIdCreation"));
                i.setUpdateDate(rs.getString("updateDate"));
                i.setUserIdUpdate(rs.getInt("userIdUpdate"));
                i.setUserCreation(rs.getString("userCreation"));
                i.setUserUpdate(rs.getString("userUpdate"));
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

    public ResponseModel findByid(int id) {                                   //listar en pantalla info
        InvMasterInitEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterInitEntity();
                i.setId(rs.getInt("Id"));
                i.setPallet(rs.getString("pallet"));
                i.setCodigoSap(rs.getString("codigoSap"));
                i.setLocation(rs.getString("location"));
                i.setTypology(rs.getString("typology"));
                i.setStatus(rs.getString("status"));
                i.setActive(rs.getBoolean("active"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setUserIdCreation(rs.getInt("userIdCreation"));
                i.setUpdateDate(rs.getString("updateDate"));
                i.setUserIdUpdate(rs.getInt("userIdUpdate"));
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

    public ResponseModel listMasterType(String type) {                                   //listar en pantalla info
        ArrayList<InvMasterModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterListAll(?)}");
            cs.setString(1, type);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterModel i = new InvMasterModel();
                i.setId(rs.getInt("Id"));
                i.setCode(rs.getString("Code"));
                i.setDescription(rs.getString("Description"));
                i.setType(rs.getString("Type"));
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

    public ResponseModel listByAudit() {
        ArrayList<InvMasterInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitListByAudit()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterInitEntity i = new InvMasterInitEntity();
                i.setId(rs.getInt("Id"));
                i.setPallet(rs.getString("pallet"));
                i.setCodigoSap(rs.getString("codigoSap"));
                i.setLocation(rs.getString("location"));
                i.setTypology(rs.getString("typology"));
                i.setStatus(rs.getString("status"));
                i.setActive(rs.getBoolean("active"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setUserIdCreation(rs.getInt("userIdCreation"));
                i.setUpdateDate(rs.getString("updateDate"));
                i.setUserIdUpdate(rs.getInt("userIdUpdate"));
                i.setUserCreation(rs.getString("userCreation"));
                i.setUserUpdate(rs.getString("userUpdate"));
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

    public ResponseModel pallet(int userId) {                                   //listar en pantalla info
        InvMasterModel i = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InventoryPalletGenerator(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterModel();
                i.setPallet(rs.getString("pallet"));
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

    public ResponseModel changeState(int palletId, String state, int userId) {

        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitChangeState(?,?,?,?)}");
            cs.setInt(1, palletId);
            cs.setString(2, state);
            cs.setInt(3, userId);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(4);
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

    /**
     * Generacion de documento
     */
    public ResponseModel generatePallet(String palletId) {

        InvMasterInitPalletModel b = null;
        Connection cn = this.ds.openConnection();
        Map<String, Object> parametros;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitGeneratePallet(?)}");
            cs.setString(1, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new InvMasterInitPalletModel();
                b.setCliente(rs.getString("cliente"));
                b.setPosicion(rs.getString("posicion"));
                b.setFecha(rs.getString("fecha"));
                b.setPallet(rs.getString("pallet"));
                b.setCodigoSap(rs.getString("codigoSap"));
                b.setTipologia(rs.getString("tipologia"));
                b.setModelo(rs.getString("modelo"));
                b.setUsuario(rs.getString("usuario"));
                b.setCantidad(rs.getInt("cantidad"));
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

        if (b != null) {
            parametros = new HashMap();
            parametros.put("Cliente", b.getCliente());
            parametros.put("Posicion", b.getPosicion());
            parametros.put("Fecha", b.getFecha());
            parametros.put("Pallet", b.getPallet());
            parametros.put("Tipologia", b.getTipologia());
            parametros.put("CodigoSap", b.getCodigoSap());
            parametros.put("Modelo", b.getModelo());
            parametros.put("Usuario", b.getUsuario());
            parametros.put("Cantidad", b.getCantidad());

            ComCommodityPalletGenerateModel p = null;
            FileInputStream fis;
            try {
                try {
                    JasperReport reporte = JasperCompileManager.compileReport(this.en.getProperty("inventario.claro.recursos.url") + "\\pallet.jrxml");
                    JasperPrint pl = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfFile(pl, this.en.getProperty("inventario.claro.pallet.url") + "\\" + b.getPallet() + ".pdf");
                } catch (JRException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }

                File file = new File(this.en.getProperty("inventario.claro.pallet.url") + "\\" + b.getPallet() + ".pdf");
                p = new ComCommodityPalletGenerateModel();

                fis = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fis.read(bytes);
                fis.close();

                p.setName(b.getPallet());
                p.setFile(bytes);
                p.setType("pdf");

            } catch (IOException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
        } else {
            return new ResponseModel(getClass().getSimpleName(), "NO", "No existe el numero de entrada " + palletId, 200);
        }
    }

    public ResponseModel findQuantity(int palletId) {
        ArrayList<InvMasterSerialQuantityModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitFindSerialQuantity(?)}");
            cs.setInt(1, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterSerialQuantityModel i = new InvMasterSerialQuantityModel();
                i.setPallet(rs.getString("Pallet"));
                i.setType(rs.getString("type"));
                i.setQuantity(rs.getInt("quantity"));
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

    public ResponseModel validationPallet(int palletId) {                                   //listar en pantalla info
        InvMasterSerialEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitValidationPallet(?)}");
            cs.setInt(1, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialEntity();
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

    public ResponseModel serialCount(String status, int palletId) {                                   //listar en pantalla info
        InvMasterSerialEntity i = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterSerialCount(?,?)}");
            cs.setString(1, status);
            cs.setInt(2, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvMasterSerialEntity();
                i.setTotal(rs.getString("total"));
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

    public ResponseModel updateLocation(InvMasterInitEntity i) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitUpdateLocation(?,?,?)}");
            cs.setInt(1, i.getId());
            cs.setString(2, i.getLocation());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

    public ResponseModel personAll() {
        ArrayList<GenPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitPersonAll()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                GenPersonEntity i = new GenPersonEntity();
                i.setId(rs.getInt("id"));
                i.setUserName(rs.getString("userName"));
                i.setFirstName(rs.getString("firstName"));
                i.setLastName(rs.getString("lastName"));
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

    public ResponseModel updatePerson(InvMasterInitEntity i) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitUpdatePerson(?,?,?,?)}");
            cs.setInt(1, i.getId());
            cs.setInt(2, i.getUserIdUpdate());
            cs.setInt(3, i.getUserAuthorization());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(4);
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
    
        public ResponseModel listAll() {                                   //listar en pantalla info
        ArrayList<InvMasterInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_InvMasterInitListAll()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvMasterInitEntity i = new InvMasterInitEntity();
                i.setId(rs.getInt("Id"));
                i.setPallet(rs.getString("pallet"));
                i.setCodigoSap(rs.getString("codigoSap"));
                i.setLocation(rs.getString("location"));
                i.setTypology(rs.getString("typology"));
                i.setStatus(rs.getString("status"));
                i.setActive(rs.getBoolean("active"));
                i.setCreationDate(rs.getString("creationDate"));
                i.setUserIdCreation(rs.getInt("userIdCreation"));
                i.setUpdateDate(rs.getString("updateDate"));
                i.setUserIdUpdate(rs.getInt("userIdUpdate"));
                i.setUserCreation(rs.getString("userCreation"));
                i.setUserUpdate(rs.getString("userUpdate"));
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

}
