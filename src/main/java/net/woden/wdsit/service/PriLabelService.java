package net.woden.wdsit.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHpConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaRadioTechConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorConnection;
import net.woden.wdsit.connection.DataSourceSapPeruConnection;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.InvCardEntity;
import net.woden.wdsit.entity.PriFieldEntity;
import net.woden.wdsit.entity.PriLabelEntity;
import net.woden.wdsit.entity.PriPermissionEntity;
import net.woden.wdsit.entity.PriPrinterEntity;
import net.woden.wdsit.entity.PriPrnCodeEntity;
import net.woden.wdsit.model.SerialSapModel;
import net.woden.wdsit.model.PriLabelWModel;
import net.woden.wdsit.model.PriUnreadableModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriLabelService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection dss;
    @Autowired
    private PriPrnCodeService priPrnCodeS;
    @Autowired
    private PriFieldService priFieldS;
    @Autowired
    private PriPrinterService priPrinterS;
    @Autowired
    private GenCustomerService genCustomerS;
    @Autowired
    private GenCountryService genCountryS;
    @Autowired
    private DataSourceSapColombiaConnection dssColombia;
    @Autowired
    private DataSourceSapColombiaRadioTechConnection dssColRadio;
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
    @Autowired
    private DataSourceSapEcuadorConnection dssEcuador;
    @Autowired
    private DataSourceSapPeruConnection dssPeru;

    public ResponseModel create(PriLabelEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelCreate(?,?,?,?,?,?)}");
            cs.setString(1, p.getName());
            cs.setInt(2, p.getPrintCount());
            cs.setString(3, p.getLink());
            cs.setInt(4, p.getCustomerId());
            cs.setInt(5, p.getSectionId());
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

    public ResponseModel update(PriLabelEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getName());
            cs.setInt(3, p.getPrintCount());
            cs.setString(4, p.getLink());
            cs.setInt(5, p.getCustomerId());
            cs.setInt(6, p.getSectionId());
            cs.setBoolean(7, p.isActive());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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

    public ResponseModel delete(int labelId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelDelete(?)}");
            cs.setInt(1, labelId);
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

    public ResponseModel findByCustomerId(int customerId) {
        ArrayList<PriLabelEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelFindByCustomerId(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriLabelEntity g = new PriLabelEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setPrintCount(rs.getInt("printCount"));
                g.setCustomerId(rs.getInt("customerId"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel findById(int id) {
        PriLabelEntity g = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new PriLabelEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setPrintCount(rs.getInt("printCount"));
                g.setCustomerId(rs.getInt("customerId"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByName(String name) {
        PriLabelEntity g = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelFindByName(?)}");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new PriLabelEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setPrintCount(rs.getInt("printCount"));
                g.setCustomerId(rs.getInt("customerId"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findSerial(String serial, int customerId, int countryId) {
        SerialSapModel s = null;
        Connection cn = null;
        String customer = ((GenCustomerEntity) this.genCustomerS.findById(customerId).getObject()).getDescription();
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();

        if (country.equals("COLOMBIA")) {
            if (customer.equals("CLARO")) {
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
            } else if (customer.equals("RADIO-TECH")) {
                cn = this.dssColRadio.openConnection();
            }  else {
                cn = this.dssColombia.openConnection();
            }

        } else if (country.equals("ECUADOR")) {
            cn = this.dssEcuador.openConnection();
        } else if (country.equals("PERU")) {
            cn = this.dssPeru.openConnection();
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call wdn.pa_Serial(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new SerialSapModel();
                s.setSerial(rs.getString("Serial"));
                s.setMac(rs.getString("Mac"));
                s.setCodigoSap(rs.getString("CodigoSap"));
                s.setDescripcion(rs.getString("Descripcion"));
                s.setFecha(rs.getDate("Fecha"));
                s.setCampo1(rs.getString("Campo1"));
                s.setCampo2(rs.getString("Campo2"));
                s.setCampo3(rs.getString("Campo3"));
                s.setCampo4(rs.getString("Campo4"));
                s.setCampo5(rs.getString("Campo5"));
                s.setCampo6(rs.getString("Campo6"));
                s.setCampo7(rs.getString("Campo7"));
                s.setCampo8(rs.getString("Campo8"));
                s.setCampo9(rs.getString("Campo9"));
                s.setCampo10(rs.getString("Campo10"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", s, 200);
    }

    public ResponseModel findPallet(int customerId, int countryId, String pallet, int caja) {
        ArrayList<SerialSapModel> list = new ArrayList();
        Connection cn = null;
        String customer = ((GenCustomerEntity) this.genCustomerS.findById(customerId).getObject()).getDescription();
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();

        if (country.equals("COLOMBIA")) {
            if (customer.equals("CLARO")) {
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
                cn = this.dssColombia.openConnection();
            }

        } else if (country.equals("ECUADOR")) {
            cn = this.dssEcuador.openConnection();
        } else if (country.equals("PERU")) {
            cn = this.dssPeru.openConnection();
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call wdn.pa_Pallet(?,?,?,?)}");
            cs.setString(1, customer);
            cs.setString(2, pallet);
            cs.setInt(3, caja);
            cs.setString(4, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                SerialSapModel p = new SerialSapModel();
                p.setSerial(rs.getString("Serial"));
                p.setMac(rs.getString("Mac"));
                p.setCodigoSap(rs.getString("CodigoSap"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setFecha(rs.getDate("Fecha"));
                p.setPallet(rs.getString("Pallet"));
                p.setNCajaEmpaque(rs.getString("NCajaEmpaque"));
                p.setNombreUsuario(rs.getString("NombreUsuario"));
                p.setCampo1(rs.getString("Campo1"));
                p.setCampo2(rs.getString("Campo2"));
                p.setCampo3(rs.getString("Campo3"));
                p.setCampo4(rs.getString("Campo4"));
                p.setCampo5(rs.getString("Campo5"));
                p.setCampo6(rs.getString("Campo6"));
                p.setCampo7(rs.getString("Campo7"));
                p.setCampo8(rs.getString("Campo8"));
                p.setCampo9(rs.getString("Campo9"));
                p.setCampo10(rs.getString("Campo10"));
                p.setEstado(rs.getString("Estado"));
                list.add(p);
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

    public ResponseModel findPalletUnreadable(String pallet) {
        ArrayList<PriUnreadableModel> list = new ArrayList();
        Connection cn = this.dss.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call wdn.pa_SerialIlegiblePallet(?)}");
            cs.setString(1, pallet);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriUnreadableModel p = new PriUnreadableModel();
                p.setSerial(rs.getString("SerialIlegible"));
                p.setMac(rs.getString("MacIlegible"));
                p.setCodigoSap(rs.getString("CodigoSap"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setFecha(rs.getString("Fecha").substring(0, 10));
                list.add(p);
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

    public ResponseModel print(int printerId, int labelId, PriLabelWModel p) {
        int status;
        ArrayList<Object> rows = p.getRows();
        PriLabelEntity label = (PriLabelEntity) this.findById(labelId).getObject();
        ArrayList<PriFieldEntity> listField = (ArrayList<PriFieldEntity>) this.priFieldS.findByLabelId(labelId).getObject();
        ArrayList<PriPrnCodeEntity> listPrn = (ArrayList<PriPrnCodeEntity>) this.priPrnCodeS.findByLabelId(labelId).getObject();
        PriPrinterEntity printer = (PriPrinterEntity) this.priPrinterS.findById(printerId).getObject();
        String zplCommand = "";
        String zplCommand1 = "";

        if (label.getSection().equals("Ingreso") || label.getLink().equals("/priPrincipal/entry/")) {
            if (rows.size() <= label.getPrintCount()) {
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == rows.size()) {
                        zplCommand = prn.getCodePrn();
                        break;
                    }
                }
                for (PriFieldEntity f : listField) {
                    for (int j = 0; j < rows.size(); j++) {
                        LinkedHashMap object = (LinkedHashMap) rows.get(j);
                        zplCommand = zplCommand.replace(f.getCode() + (j + 1), (object.values().toArray()[f.getPosition()] == null) ? "" : object.values().toArray()[f.getPosition()].toString());
                    }
                }
            } else {
                int cociente = (rows.size() / label.getPrintCount());
                int residuo = (rows.size() % label.getPrintCount());
                int contador = 0;
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == label.getPrintCount()) {
                        zplCommand = prn.getCodePrn();
                        break;
                    }
                }
                for (int i = 0; i < cociente; i++) {
                    zplCommand1 = zplCommand1 + zplCommand;
                    for (int j = 0; j < label.getPrintCount(); j++) {
                        LinkedHashMap object = (LinkedHashMap) rows.get(contador);
                        for (PriFieldEntity f : listField) {
                            zplCommand1 = zplCommand1.replace(f.getCode() + (j + 1), object.values().toArray()[f.getPosition()] == null ? "" : object.values().toArray()[f.getPosition()].toString());
                        }
                        contador++;
                    }
                }
                if (residuo > 0) {
                    for (PriPrnCodeEntity prn : listPrn) {
                        if (prn.getName() == residuo) {
                            zplCommand = prn.getCodePrn();
                            break;
                        }
                    }

                    zplCommand1 = zplCommand1 + zplCommand;

                    for (int i = 0; i < residuo; i++) {
                        LinkedHashMap object = (LinkedHashMap) rows.get(contador);
                        for (PriFieldEntity f : listField) {
                            zplCommand1 = zplCommand1.replace(f.getCode() + (i + 1), object.values().toArray()[f.getPosition()] == null ? "" : object.values().toArray()[f.getPosition()].toString());
                        }
                        contador++;
                    }
                }
                zplCommand = zplCommand1;
            }
        } else if (label.getSection().equals("Etiquetado") || label.getLink().equals("/priPrincipal/label/")) {
            if (rows.size() <= label.getPrintCount()) {
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == rows.size()) {
                        zplCommand = prn.getCodePrn();
                        break;
                    }
                }
                for (int i = 0; i < rows.size(); i++) {
                    LinkedHashMap object = (LinkedHashMap) rows.get(i);
                    for (PriFieldEntity f : listField) {
                        zplCommand = zplCommand.replace(f.getCode() + (i + 1), (object.values().toArray()[f.getPosition()] == null) ? "" : object.values().toArray()[f.getPosition()].toString());
                        zplCommand = zplCommand.replace("descripcion", object.values().toArray()[3].toString());
                        zplCommand = zplCommand.replace("fecha", object.values().toArray()[4].toString());
                        zplCommand = zplCommand.replace("codigosap", object.values().toArray()[2].toString());
                    }
                }
            } else {
                int cociente = (rows.size() / label.getPrintCount());
                int residuo = (rows.size() % label.getPrintCount());
                int contador = 0;
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == label.getPrintCount()) {
                        zplCommand = prn.getCodePrn();
                        break;
                    }
                }
                for (int i = 0; i < cociente; i++) {
                    zplCommand1 = zplCommand1 + zplCommand;
                    for (int j = 0; j < label.getPrintCount(); j++) {
                        LinkedHashMap object = (LinkedHashMap) rows.get(contador);
                        for (PriFieldEntity f : listField) {
                            zplCommand1 = zplCommand1.replace(f.getCode() + (j + 1), object.values().toArray()[f.getPosition()] == null ? "" : object.values().toArray()[f.getPosition()].toString());
                        }
                        zplCommand1 = zplCommand1.replace("descripcion", object.values().toArray()[3].toString());
                        zplCommand1 = zplCommand1.replace("fecha", object.values().toArray()[4].toString());
                        zplCommand1 = zplCommand1.replace("codigosap", object.values().toArray()[2].toString());
                        contador++;
                    }
                }
                zplCommand = "";
                if (residuo > 0) {
                    for (PriPrnCodeEntity prn : listPrn) {
                        if (prn.getName() == residuo) {
                            zplCommand1 = prn.getCodePrn();
                            break;
                        }
                    }
                    for (int i = 0; i < residuo; i++) {
                        LinkedHashMap object = (LinkedHashMap) rows.get(contador);
                        for (PriFieldEntity f : listField) {
                            zplCommand1 = zplCommand1.replace(f.getCode() + (i + 1), object.values().toArray()[f.getPosition()] == null ? "" : object.values().toArray()[f.getPosition()].toString());
                        }
                        zplCommand1 = zplCommand1.replace("descripcion", object.values().toArray()[3].toString());
                        zplCommand1 = zplCommand1.replace("fecha", object.values().toArray()[4].toString());
                        zplCommand1 = zplCommand1.replace("codigosap", object.values().toArray()[2].toString());
                    }
                }
                zplCommand = zplCommand + zplCommand1;
            }
        } else if (label.getSection().equals("Empaque") && !label.getLink().equals("/priPrincipal/label/")) {
            if (rows.size() <= label.getPrintCount()) {
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == rows.size()) {
                        zplCommand = prn.getCodePrn();
                        break;
                    }
                }
                LinkedHashMap object = (LinkedHashMap) rows.get(0);
                String date = object.values().toArray()[4].toString();
                String user = object.values().toArray()[7].toString();
                String family = object.values().toArray()[3].toString();
                String pallet = object.values().toArray()[5].toString();
                String sapCode = object.values().toArray()[2].toString();
                String caja = object.values().toArray()[6].toString();

                for (int i = 0; i < rows.size(); i++) {
                    for (PriFieldEntity f : listField) {
                        if (i >= 9) {
                            switch (i) {
                                case 9:
                                    zplCommand = zplCommand.replace(f.getCode() + "0", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    zplCommand = zplCommand.replace("caja", ((LinkedHashMap) rows.get(i)).values().toArray()[6] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[6].toString());
                                    break;
                                case 10:
                                    zplCommand = zplCommand.replace(f.getCode() + "A", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 11:
                                    zplCommand = zplCommand.replace(f.getCode() + "B", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 12:
                                    zplCommand = zplCommand.replace(f.getCode() + "C", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 13:
                                    zplCommand = zplCommand.replace(f.getCode() + "D", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 14:
                                    zplCommand = zplCommand.replace(f.getCode() + "E", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 15:
                                    zplCommand = zplCommand.replace(f.getCode() + "F", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 16:
                                    zplCommand = zplCommand.replace(f.getCode() + "G", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 17:
                                    zplCommand = zplCommand.replace(f.getCode() + "H", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 18:
                                    zplCommand = zplCommand.replace(f.getCode() + "I", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                case 19:
                                    zplCommand = zplCommand.replace(f.getCode() + "J", ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            zplCommand = zplCommand.replace(f.getCode() + (i + 1), ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(i)).values().toArray()[f.getPosition()].toString());
                        }
                    }
                }
                zplCommand = zplCommand.replace("familia", family.length() > 25 ? family.substring(0, 25) : family);
                zplCommand = zplCommand.replace("pallet", pallet);
                zplCommand = zplCommand.replace("fecha", date);
                zplCommand = zplCommand.replace("codigosap", sapCode);
                zplCommand = zplCommand.replace("usuario", user);
                zplCommand = zplCommand.replace("caja", caja);
            } else {
                int cociente = rows.size() / label.getPrintCount();
                int residuo = rows.size() % label.getPrintCount();
                int contador = 0;
                for (int i = 0; i < cociente; i++) {
                    for (PriPrnCodeEntity prn : listPrn) {
                        if (prn.getName() == label.getPrintCount()) {
                            zplCommand = zplCommand + prn.getCodePrn();
                            break;
                        }
                    }
                    LinkedHashMap object = (LinkedHashMap) rows.get(0);
                    String date = object.values().toArray()[4].toString();
                    String user = object.values().toArray()[7].toString();
                    String family = object.values().toArray()[3].toString();
                    String pallet = object.values().toArray()[5].toString();
                    String sapCode = object.values().toArray()[2].toString();
                    String caja = object.values().toArray()[6].toString();

                    for (int j = 0; j < label.getPrintCount(); j++) {
                        for (PriFieldEntity f : listField) {
                            zplCommand = zplCommand.replace(f.getCode() + (j + 1), ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                            if (j >= 9) {
                                switch (j) {
                                    case 9:
                                        zplCommand = zplCommand.replace(f.getCode() + "0", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        zplCommand = zplCommand.replace("caja", ((LinkedHashMap) rows.get(contador)).values().toArray()[6] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[6].toString());
                                        break;
                                    case 10:
                                        zplCommand = zplCommand.replace(f.getCode() + "A", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 11:
                                        zplCommand = zplCommand.replace(f.getCode() + "B", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 12:
                                        zplCommand = zplCommand.replace(f.getCode() + "C", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 13:
                                        zplCommand = zplCommand.replace(f.getCode() + "D", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 14:
                                        zplCommand = zplCommand.replace(f.getCode() + "E", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 15:
                                        zplCommand = zplCommand.replace(f.getCode() + "F", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 16:
                                        zplCommand = zplCommand.replace(f.getCode() + "G", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 17:
                                        zplCommand = zplCommand.replace(f.getCode() + "H", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 18:
                                        zplCommand = zplCommand.replace(f.getCode() + "I", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 19:
                                        zplCommand = zplCommand.replace(f.getCode() + "J", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        contador++;
                    }
                    zplCommand = zplCommand.replace("familia", family.length() > 25 ? family.substring(0, 25) : family);
                    zplCommand = zplCommand.replace("pallet", pallet);
                    zplCommand = zplCommand.replace("fecha", date);
                    zplCommand = zplCommand.replace("codigosap", sapCode);
                    zplCommand = zplCommand.replace("usuario", user);
                    zplCommand = zplCommand.replace("caja", caja);
                }
                if (residuo > 0) {
                    for (PriPrnCodeEntity prn : listPrn) {
                        if (prn.getName() == residuo) {
                            zplCommand1 = prn.getCodePrn();
                            break;
                        }
                    }
                    LinkedHashMap object = (LinkedHashMap) rows.get(0);
                    String date = object.values().toArray()[4].toString();
                    String user = object.values().toArray()[7].toString();
                    String family = object.values().toArray()[3].toString();
                    String pallet = object.values().toArray()[5].toString();
                    String sapCode = object.values().toArray()[2].toString();
                    String caja = object.values().toArray()[6].toString();

                    for (int i = 0; i < residuo; i++) {
                        for (PriFieldEntity f : listField) {
                            zplCommand1 = zplCommand1.replace(f.getCode() + (i + 1), ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                            if (i >= 9) {
                                switch (i) {
                                    case 9:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "0", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        zplCommand1 = zplCommand1.replace("caja", ((LinkedHashMap) rows.get(contador)).values().toArray()[6] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[6].toString());
                                        break;
                                    case 10:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "A", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 11:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "B", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 12:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "C", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 13:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "D", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 14:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "E", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 15:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "F", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 16:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "G", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 17:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "H", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 18:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "I", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    case 19:
                                        zplCommand1 = zplCommand1.replace(f.getCode() + "J", ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()] == null ? "" : ((LinkedHashMap) rows.get(contador)).values().toArray()[f.getPosition()].toString());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        contador++;
                    }
                    zplCommand1 = zplCommand1.replace("familia", family.length() > 25 ? family.substring(0, 25) : family);
                    zplCommand1 = zplCommand1.replace("pallet", pallet);
                    zplCommand1 = zplCommand1.replace("fecha", date);
                    zplCommand1 = zplCommand1.replace("codigosap", sapCode);
                    zplCommand1 = zplCommand1.replace("usuario", user);
                    zplCommand1 = zplCommand1.replace("caja", caja);
                }
                zplCommand = zplCommand + zplCommand1;
            }
            String qr = "";
            for (int j = 1; j <= rows.size(); j++) {
                LinkedHashMap objectS = (LinkedHashMap) rows.get(j - 1);
                String serial = objectS.values().toArray()[0].toString();
                String newline = System.lineSeparator();
                if (j == 1) {
                    qr = qr + serial + "\n";
                } else {
                    if (j % 2 == 0) {
                        qr = qr + serial + "\n";
                    } else {
                        qr = qr + serial + "\n";
                    }
                }
            }

            zplCommand = zplCommand.replace("codigo_qr", qr);
            System.out.println(qr);
        }
        try {
            Socket clientSocket = new Socket(printer.getIp(), 9100);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(zplCommand);
            clientSocket.close();
            status = 1;
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
// byte[] by = zplCommand.getBytes();
// DocFlavor docFormat = DocFlavor.BYTE_ARRAY.AUTOSENSE;
// Doc doc = new SimpleDoc(by, docFormat, null);
// PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
// String printerName;
// PrintService service = null;
// for (int i = 0; i < services.length; i++) {
// PrintServiceAttribute attr = services[i].getAttribute(PrinterName.class);
// printerName = ((PrinterName) attr).getValue();
// if (printerName.equals(printer.getCustomer() + "_" + printer.getLabelType())) {
// service = services[i];
// break;
// }
// }
// if (service != null) {
// DocPrintJob printJob = service.createPrintJob();
// try {
// printJob.print(doc, new HashPrintRequestAttributeSet());
// status = 1;
// } catch (PrintException ex) {
// return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
// }
// } else {
// return new ResponseModel(getClass().getSimpleName(), "Impresora no encontrada", null, 200);
// }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }

    public ResponseModel printCard(int printerId, String labelName, ArrayList<InvCardEntity> array) {
        int status;
        PriLabelEntity label = (PriLabelEntity) this.findByName(labelName).getObject();
        ArrayList<PriPrnCodeEntity> listPrn = (ArrayList<PriPrnCodeEntity>) this.priPrnCodeS.findByLabelId(label.getId()).getObject();
        PriPrinterEntity printer = (PriPrinterEntity) this.priPrinterS.findById(printerId).getObject();
        String zplCommand = "";
        String zplCommand1 = "";

        if (array.size() <= label.getPrintCount()) {
            for (PriPrnCodeEntity prn : listPrn) {
                if (prn.getName() == array.size()) {
                    zplCommand = prn.getCodePrn();
                    break;
                }
            }
            for (InvCardEntity c : array) {
                zplCommand = zplCommand.replace("cyclic", c.getCyclic());
                zplCommand = zplCommand.replace("customer", c.getCustomer());
                zplCommand = zplCommand.replace("code", c.getCode());
                zplCommand = zplCommand.replace("date", c.getDate());
                zplCommand = zplCommand.replace("loadDate", c.getLoadDate());
                zplCommand = zplCommand.replace("audited", c.getAuditor() != null ? c.getAuditor() : "");
                zplCommand = zplCommand.replace("coutingType", c.getCoutingType());
                zplCommand = zplCommand.replace("location", c.getLocation() != null ? c.getLocation() : "");
                zplCommand = zplCommand.replace("pallet", c.getPallet() != null ? c.getPallet() : "");
            }
        } else {
            int cociente = (array.size() / label.getPrintCount());
            int residuo = (array.size() % label.getPrintCount());
            int contador = 0;
            for (PriPrnCodeEntity prn : listPrn) {
                if (prn.getName() == label.getPrintCount()) {
                    zplCommand = prn.getCodePrn();
                    break;
                }
            }
            for (int i = 0; i < cociente; i++) {
                zplCommand1 = zplCommand1 + zplCommand;
                for (int j = 0; j < label.getPrintCount(); j++) {
                    zplCommand1 = zplCommand1.replace("cyclic", array.get(contador).getCyclic());
                    zplCommand1 = zplCommand1.replace("customer", array.get(contador).getCustomer());
                    zplCommand1 = zplCommand1.replace("code", array.get(contador).getCode());
                    zplCommand1 = zplCommand1.replace("date", array.get(contador).getDate());
                    zplCommand1 = zplCommand1.replace("loadDate", array.get(contador).getLoadDate());
                    zplCommand1 = zplCommand1.replace("audited", array.get(contador).getAuditor() != null ? array.get(contador).getAuditor() : "");
                    zplCommand1 = zplCommand1.replace("coutingType", array.get(contador).getCoutingType());
                    zplCommand1 = zplCommand1.replace("location", array.get(contador).getLocation() != null ? array.get(contador).getLocation() : "");
                    zplCommand1 = zplCommand1.replace("pallet", array.get(contador).getPallet() != null ? array.get(contador).getPallet() : "");
                    contador++;
                }
            }
            zplCommand = "";
            if (residuo > 0) {
                for (PriPrnCodeEntity prn : listPrn) {
                    if (prn.getName() == residuo) {
                        zplCommand1 = prn.getCodePrn();
                        break;
                    }
                }
                for (int i = 0; i < residuo; i++) {
                    zplCommand1 = zplCommand1.replace("cyclic", array.get(contador).getCyclic());
                    zplCommand1 = zplCommand1.replace("customer", array.get(contador).getCustomer());
                    zplCommand1 = zplCommand1.replace("code", array.get(contador).getCode());
                    zplCommand1 = zplCommand1.replace("date", array.get(contador).getDate());
                    zplCommand1 = zplCommand1.replace("loadDate", array.get(contador).getLoadDate());
                    zplCommand1 = zplCommand1.replace("audited", array.get(contador).getAuditor() != null ? array.get(contador).getAuditor() : "");
                    zplCommand1 = zplCommand1.replace("coutingType", array.get(contador).getCoutingType());
                    zplCommand1 = zplCommand1.replace("location", array.get(contador).getLocation() != null ? array.get(contador).getLocation() : "");
                    zplCommand1 = zplCommand1.replace("pallet", array.get(contador).getPallet() != null ? array.get(contador).getPallet() : "");
                }
            }
            zplCommand = zplCommand + zplCommand1;
        }
        try {
            Socket clientSocket = new Socket(printer.getIp(), 9100);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(zplCommand);
            clientSocket.close();
            status = 1;
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
// byte[] by = zplCommand.getBytes();
// DocFlavor docFormat = DocFlavor.BYTE_ARRAY.AUTOSENSE;
// Doc doc = new SimpleDoc(by, docFormat, null);
// PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
// String printerName;
// PrintService service = null;
// for (int i = 0; i < services.length; i++) {
// PrintServiceAttribute attr = services[i].getAttribute(PrinterName.class);
// printerName = ((PrinterName) attr).getValue();
// if (printerName.equals(printer.getCustomer() + "_")) {
// service = services[i];
// break;
// }
// }
// if (service != null) {
// DocPrintJob printJob = service.createPrintJob();
// try {
// printJob.print(doc, new HashPrintRequestAttributeSet());
// status = 1;
// } catch (PrintException ex) {
// return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
// }
// } else {
// return new ResponseModel(getClass().getSimpleName(), "Impresora no encontrada", null, 200);
// }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }

    public ResponseModel list() {
        ArrayList<PriLabelEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriLabelEntity g = new PriLabelEntity();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setPrintCount(rs.getInt("printCount"));
                g.setCustomerId(rs.getInt("customerId"));
                g.setCustomer(rs.getString("customer"));
                g.setLink(rs.getString("link"));
                g.setSectionId(rs.getInt("sectionId"));
                g.setSection(rs.getString("section"));
                g.setActive(rs.getBoolean("active"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel printHistory(int userId, String pallet, int box) {

        Connection cn = this.ds.openConnection();
        String respuesta = "";
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriPalletPerson(?,?,?)}");
            cs.setString(1, pallet);
            cs.setInt(2, box);
            cs.setInt(3, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                respuesta = rs.getString("Respuesta");
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
        return new ResponseModel(getClass().getSimpleName(), respuesta, null, 200);
    }

    public ResponseModel createHistory(int userId, String pallet, int box) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriPalletHistoy(?,?,?,?)}");
            cs.setString(1, pallet);
            cs.setInt(2, box);
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

    public ResponseModel userPermissionList() {
        ArrayList<PriPermissionEntity> lista = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriPermissionList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PriPermissionEntity g = new PriPermissionEntity();
                g.setId(rs.getInt("id"));
                g.setUserId(rs.getInt("userId"));
                g.setUserName(rs.getString("userName"));
                lista.add(g);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }

    public ResponseModel userPermissionCreate(int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriPermissionCreate(?,?)}");
            cs.setInt(1, userId);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(2);
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

    public ResponseModel userPermissionDelete(int userId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriPermissionDelete(?)}");
            cs.setInt(1, userId);
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

    public ResponseModel validationSerial(String serial, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelValidationSerialCreate(?,?,?)}");
            cs.setString(1, serial);
            cs.setInt(2, userId);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getName(), "OK", inserts, 200);
    }

    public ResponseModel listValidation(String serial, int userId) {
        PriPermissionEntity i = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelValidationSerialList(?,?)}");
            cs.setString(1, serial);
            cs.setInt(2, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                i = new PriPermissionEntity();
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

    public ResponseModel deleteSeries() {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PriLabelValidationSerialDelete()}");
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
}
