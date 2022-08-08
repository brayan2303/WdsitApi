package net.woden.wdsit.service;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.InvPalletEntity;
import net.woden.wdsit.model.InvCyclicPalletModel;
import net.woden.wdsit.model.InvCyclicPalletSerialsModel;
import net.woden.wdsit.model.InvPalletCoutingModel;
import net.woden.wdsit.model.ResponseModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class InvPalletService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment e;

    public ResponseModel create(InvPalletEntity i) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletCreate(?,?,?,?,?,?)}");
            cs.setInt(1, i.getCyclicId());
            cs.setString(2, i.getPallet());
            cs.setString(3, i.getSapCode());
            cs.setString(4, i.getLocation());
            cs.setString(5, i.getType());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(6);
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

    public ResponseModel delete(int cyclicId, String number) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletDelete(?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, number);
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

    public ResponseModel approveReject(int palletId, String status) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletApproveReject(?,?,?)}");
            cs.setInt(1, palletId);
            cs.setString(2, status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

    public ResponseModel find(int cyclicId, String number) {
        InvPalletEntity i = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletFind(?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new InvPalletEntity();
                i.setId(rs.getInt("id"));
                i.setCyclicId(rs.getInt("cyclicId"));
                i.setPallet(rs.getString("pallet"));
                i.setSapCode(rs.getString("sapCode"));
                i.setLocation(rs.getString("location"));
                i.setType(rs.getString("type"));
                i.setStatus(rs.getString("status"));
            }
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", i, 200);
    }

    public ResponseModel findAll(int cyclicId, String system, String typeSampling, String type, String customer) {
        ArrayList<InvCyclicPalletModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletFindAll(?,?,?,?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, system);
            cs.setString(3, typeSampling);
            cs.setString(4, type);
            cs.setString(5, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvCyclicPalletModel i = new InvCyclicPalletModel();
                i.setNumber(rs.getString("number"));
                i.setLocation(rs.getString("location"));
                i.setSapCode(rs.getString("sapCode"));
                i.setQuantity(rs.getInt("quantity"));
                i.setActive(rs.getBoolean("active"));
                list.add(i);
            }
            cn.commit();
            rs.close();
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

    public ResponseModel findPending(int cyclicId) {
        int total = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletFindPending(?)}");
            cs.setInt(1, cyclicId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
            cn.commit();
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", total, 200);
    }

    public ResponseModel findSerials(int cyclicId, String system, String type, String number, String customer, int option) {
        ArrayList<InvCyclicPalletSerialsModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletFindSerials(?,?,?,?,?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, system);
            cs.setString(3, type);
            cs.setString(4, number);
            cs.setString(5, customer);
            cs.setInt(6, option);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvCyclicPalletSerialsModel i = new InvCyclicPalletSerialsModel();
                i.setSerial(rs.getString("serial"));
                i.setMac(rs.getString("mac"));
                i.setSapCode(rs.getString("sapCode"));
                i.setPallet(rs.getString("pallet"));
                list.add(i);
            }
            cn.commit();
            rs.close();
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

    public ResponseModel findCouting(int palletId) {
        ArrayList<InvPalletCoutingModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletFindCouting(?)}");
            cs.setInt(1, palletId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvPalletCoutingModel i = new InvPalletCoutingModel();
                i.setCoutingType(rs.getString("coutingType"));
                i.setStatus(rs.getString("status"));
                i.setType(rs.getString("type"));
                i.setQuantity(rs.getInt("quantity"));
                i.setStartDate(rs.getString("startDate"));
                i.setEndDate(rs.getString("endDate"));
                i.setAssistant(rs.getString("assistant"));
                list.add(i);
            }
            cn.commit();
            rs.close();
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

    public ResponseModel list(int cyclicId, String system, String type, String customer) {
        ArrayList<InvCyclicPalletModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletList(?,?,?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, system);
            cs.setString(3, type);
            cs.setString(4, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvCyclicPalletModel i = new InvCyclicPalletModel();
                i.setId(rs.getInt("id"));
                i.setNumber(rs.getString("number"));
                i.setType(rs.getString("type"));
                i.setSapCode(rs.getString("sapCode"));
                i.setLocation(rs.getString("location"));
                i.setQuantity(rs.getInt("quantity"));
                i.setStatus(rs.getString("status"));
                list.add(i);
            }
            cn.commit();
            rs.close();
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

    public ResponseModel listAll(int cyclicId, String system, String customer) {
        ArrayList<InvCyclicPalletModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_InvPalletListAll(?,?,?)}");
            cs.setInt(1, cyclicId);
            cs.setString(2, system);
            cs.setString(3, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                InvCyclicPalletModel i = new InvCyclicPalletModel();
                i.setId(rs.getInt("id"));
                i.setNumber(rs.getString("number"));
                i.setType(rs.getString("type"));
                i.setSapCode(rs.getString("sapCode"));
                i.setLocation(rs.getString("location"));
                i.setQuantity(rs.getInt("quantity"));
                i.setCouting1(rs.getString("couting1"));
                i.setCouting2(rs.getString("couting2"));
                i.setCouting3(rs.getString("couting3"));
                i.setCouting4(rs.getString("couting4"));
                i.setAssistant(rs.getString("assistant"));
                i.setStatus(rs.getString("status"));
                list.add(i);
            }
            cn.commit();
            rs.close();
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

    public ResponseModel print(String customer, String status, InvCyclicPalletModel i) {
        String bytes = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        File directory = new File(this.e.getProperty("inventario.pallets.url") + "\\" + customer);
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        Map<String, Object> parameters = new HashMap();
        parameters.put("Cliente", customer);
        parameters.put("Posicion", i.getLocation());
        parameters.put("Fecha", format.format(new Date()));
        parameters.put("Pallet", i.getNumber());
        parameters.put("CodigoSap", i.getSapCode());
        parameters.put("Estado", status);
        parameters.put("Cantidad", i.getQuantity());
        if (i.getCouting4().equals("SIN CUARTO CONTEO")) {
            if (i.getCouting3().equals("SIN TERCER CONTEO")) {
                if (i.getCouting2().equals("SIN SEGUNDO CONTEO")) {
                    if (i.getCouting1().equals("SIN PRIMER CONTEO")) {
                        parameters.put("Conteo", "SIN CONTEOS");
                    } else {
                        parameters.put("Conteo", i.getCouting1());
                    }
                } else {
                    parameters.put("Conteo", i.getCouting2());
                }
            } else {
                parameters.put("Conteo", i.getCouting3());
            }
        } else {
            parameters.put("Conteo", i.getCouting4());
        }
        parameters.put("Usuario", i.getAssistant());
        try {
            JasperReport report = JasperCompileManager.compileReport(this.e.getProperty("inventario.pallets.url") + "\\Pallet.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, directory + "\\" + i.getNumber() + ".pdf");
            File file = new File(directory + "\\" + i.getNumber() + ".pdf");
            bytes = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        } catch (JRException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (IOException ex) {
            Logger.getLogger(InvPalletService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", bytes, 200);
    }
}
