/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHpConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorConnection;
import net.woden.wdsit.connection.DataSourceSapPeruConnection;
import net.woden.wdsit.entity.ComCommodityEntryEntity;
import net.woden.wdsit.entity.ComCustomerEntity;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ComCommodityEntryPreAlertModel;
import net.woden.wdsit.model.ComCommodityEntryPreAlertTCPIPModel;
import net.woden.wdsit.model.ComCommodityPalletGenerateModel;
import net.woden.wdsit.model.ComCommodityPalletModel;
import net.woden.wdsit.model.ComOriginModel;
import net.woden.wdsit.model.ComOriginTypeModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

/**
 *
 * @author m.pulido
 */
@Service
public class ComCommodityEntryService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection dsc;
    @Autowired
    private ComCustomerService comCustomerS;
    @Autowired
    private Environment en;
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
    private DataSourceSapColombiaConnection dssColombia;
    @Autowired
    private DataSourceSapEcuadorConnection dssEcuador;
    @Autowired
    private DataSourceSapPeruConnection dssPeru;
    @Autowired
    private GenCustomerService genCustomerS;
    @Autowired
    private GenCountryService genCountryS;

    public ResponseModel create(int userId, int countryId, int coustomerId, ComCommodityEntryEntity c) {
        String inserts = "";
        Connection cn = null;
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();
        String customer = ((GenCustomerEntity) (this.genCustomerS.findById(coustomerId).getObject())).getDescription();
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
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryCreate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, c.getCustomerId());
            cs.setString(2, c.getCity());
            cs.setString(3, c.getOrigin());
            cs.setString(4, c.getOriginType());
            cs.setString(5, c.getAgentIdentification());
            cs.setBoolean(6, c.isAutomatic());
            cs.setInt(7, userId);
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.execute();
            inserts = cs.getString(8);
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

    public ResponseModel update(int entryId, ComCommodityEntryEntity c) {
        int update = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryEdit(?,?,?,?,?,?,?)}");
            cs.setInt(1, entryId);
            cs.setString(2, c.getCity());
            cs.setString(3, c.getOrigin());
            cs.setString(4, c.getOriginType());
            cs.setString(5, c.getAgentIdentification());
            cs.setBoolean(6, c.isActive());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            update = cs.getInt(7);
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

    public ResponseModel closeEntry(int entryId, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryClose(?,?,?)}");
            cs.setInt(1, entryId);
            cs.setInt(2, userId);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel approvedEntry(int entryId, int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryChangeApproved(?,?,?)}");
            cs.setInt(1, entryId);
            cs.setInt(2, userId);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel delete(int entryId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryDelete(?)}");
            cs.setInt(1, entryId);
            inserts = 1;
            cs.execute();
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


    public ResponseModel originList(int countryId, int coustomerId) {
        ArrayList<ComOriginModel> list = new ArrayList();
        Connection cn = null;
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();
        String customer = ((GenCustomerEntity) (this.genCustomerS.findById(coustomerId).getObject())).getDescription();
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
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ComCommodityEntryOrigin()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComOriginModel b = new ComOriginModel();
                b.setOrigin(rs.getString("origen"));
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



    public ResponseModel originTypeList(String origin,int countryId, int coustomerId) {
        ArrayList<ComOriginTypeModel> list = new ArrayList();
        Connection cn = null;
         String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();
        String customer = ((GenCustomerEntity) (this.genCustomerS.findById(coustomerId).getObject())).getDescription();
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
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ComCommodityEntryOriginType(?)}");
            cs.setString(1, origin);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComOriginTypeModel b = new ComOriginTypeModel();
                b.setOriginType(rs.getString("TipoOrigen"));
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

    public ResponseModel listByUserId(int userId) {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryListByUserId(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel findById(int entryId) {
        ComCommodityEntryEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryFindById(?)}");
            cs.setInt(1, entryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel list() {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel sapList(int sapId) {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntrySapList(?)}");
            cs.setInt(1, sapId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel listByNumber(String number) {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryListByNumber(?)}");
            cs.setString(1, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel listActive() {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryPreAlertListActive()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel listApproved() {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryApproved()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel listToSap(int customerId) {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryListToSap(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel listActiveEntry() {
        ArrayList<ComCommodityEntryEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryPreAlertListActiveUserId()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityEntryEntity b = new ComCommodityEntryEntity();
                b.setId(rs.getInt("id"));
                b.setId(rs.getInt("id"));
                b.setNumber(rs.getString("number"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCity(rs.getString("city"));
                b.setOrigin(rs.getString("origin"));
                b.setOriginType(rs.getString("originType"));
                b.setAgentIdentification(rs.getString("agentIdentification"));
                b.setState(rs.getString("state"));
                b.setAutomatic(rs.getBoolean("automatic"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("Active"));
                b.setApprovedUserId(rs.getInt("approvedUserId"));
                b.setApprovedUserName(rs.getString("approvedUserName"));
                b.setApprovedDate(rs.getString("approvedDate"));
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

    public ResponseModel carguePreAlerta(ComCommodityEntryPreAlertTCPIPModel c) {
        Connection cn = this.ds.openConnection();
        String inserts = "";
        ComCustomerEntity cc = (ComCustomerEntity) this.comCustomerS.findByName(c.getProject()).getObject();

        if (cc != null) {
            try {
                CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryCreate(?,?,?,?,?,?,?,?)}");
                cs.setInt(1, cc.getCustomerId());
                cs.setString(2, c.getCity());
                cs.setString(3, "RETIEVAL");
                cs.setString(4, c.getAgentName());
                cs.setString(5, c.getAgentIdentification());
                cs.setBoolean(6, c.isAutomatic());
                cs.setInt(7, c.getUserCreationId());
                cs.registerOutParameter(8, Types.VARCHAR);
                cs.execute();
                inserts = cs.getString(8);
                cs.close();

                for (int i = 0; i < c.getAccessory().size(); i++) {
                    cs = cn.prepareCall("{call sp_ComCommodityEntryLoadTCPIPCreate(?,?,?,?,?,?,?,?,?)}");
                    ComCommodityEntryPreAlertModel b = c.getAccessory().get(i);
                    cs.setString(1, inserts);
                    cs.setString(2, b.getOrders());
                    cs.setString(3, b.getTransact());
                    cs.setString(4, b.getCodeAccessory());
                    cs.setString(5, b.getNameAccessory());
                    cs.setInt(6, b.getQuantity());
                    cs.setString(7, b.getAssignDate());
                    cs.setString(8, b.getRecolectionDate());
                    cs.setInt(9, c.getUserCreationId());
                    cs.execute();
                    cs.close();
                }

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
        } else {
            return new ResponseModel(getClass().getSimpleName(), "ERROR", "El proyecto: " + c.getProject() + " no esta parametrizado!", 200);
        }
    }

    /**
     * Generacion de documento
     */
    public ResponseModel generatePallet(String codeEntry) {

        ComCommodityPalletModel b = null;
        Connection cn = this.ds.openConnection();
        Map<String, Object> parametros;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityFindCodeEntry(?)}");
            cs.setString(1, codeEntry);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ComCommodityPalletModel();
                b.setCliente(rs.getString("cliente"));
                b.setPosicion(rs.getString("posicion"));
                b.setFecha(rs.getString("fecha"));
                b.setPallet(rs.getString("pallet"));
                b.setCodigoSap(rs.getString("codigoSap"));
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
            parametros.put("CodigoSap", b.getCodigoSap());
            parametros.put("Modelo", b.getModelo());
            parametros.put("Usuario", b.getUsuario());
            parametros.put("Cantidad", b.getCantidad());

            ComCommodityPalletGenerateModel p = null;
            FileInputStream fis;
            try {
                try {
                    JasperReport reporte = JasperCompileManager.compileReport(this.en.getProperty("accesorios.recursos.url") + "\\pallet.jrxml");
                    JasperPrint pl = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfFile(pl, this.en.getProperty("accesorios.pallet.url") + "\\" + codeEntry + ".pdf");
                } catch (JRException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }

                File file = new File(this.en.getProperty("accesorios.pallet.url") + "\\" + codeEntry + ".pdf");
                p = new ComCommodityPalletGenerateModel();

                fis = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];
                fis.read(bytes);
                fis.close();

                p.setName(codeEntry);
                p.setFile(bytes);
                p.setType("pdf");

            } catch (IOException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
        } else {
            return new ResponseModel(getClass().getSimpleName(), "NO", "No existe el numero de entrada " + codeEntry, 200);
        }
    }

    public ResponseModel findByPallet(int entrySapB1Id) {
        ComCommodityEntryEntity c = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryPallet(?)}");
            cs.setInt(1, entrySapB1Id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new ComCommodityEntryEntity();
                c.setNumber(rs.getString("number"));
            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

}
