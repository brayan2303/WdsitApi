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
import net.woden.wdsit.connection.DataSourceSapEcuadorConnection;
import net.woden.wdsit.connection.DataSourceSapPeruConnection;
import net.woden.wdsit.entity.ComCommodityEntrySapB1Entity;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ComCommodityLocationModel;
import net.woden.wdsit.model.ComEntrySapListEntryModel;
import net.woden.wdsit.model.ComEntrySapListModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ComCommodityEntrySapB1Service {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaConnection dsc;
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
    
    public ResponseModel locationList(int countryId, int coustomerId) {
        ArrayList<ComCommodityLocationModel>list=new ArrayList();
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
            CallableStatement cs = cn.prepareCall("{call wdn.sp_ComCommodityLocation()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityLocationModel b = new ComCommodityLocationModel();
                b.setWarehouse(rs.getString("warehouse"));
                b.setLocation(rs.getString("location"));
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

    public ResponseModel create(ComCommodityEntrySapB1Entity c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryToSapCreate(?,?,?,?)}");
            cs.setString(1, c.getCustomerName());
            cs.setString(2, c.getLocation());
            cs.setInt(3, c.getUserId());
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

    public ResponseModel update(ComCommodityEntrySapB1Entity c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryToSapUpdate(?,?,?,?)}");
            cs.setInt(1, c.getId());
            cs.setString(2, c.getStatusCode());
            cs.setString(3, c.getDocumentCode());
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

    public ResponseModel addEntryToSap(ComEntrySapListModel c) {
        String inserts = "";
        Connection cn = this.ds.openConnection();

        try {
            for (ComEntrySapListEntryModel o : c.getEntrys()) {
                CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryDetailCreate(?,?)}");
                cs.setInt(1, c.getEntrySapB1Id());
                cs.setString(2, o.getNumber());
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
    }

    public ResponseModel findByEntryId(int entryId) {
        ComCommodityEntrySapB1Entity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntrySapFindById(?)}");
            cs.setInt(1, entryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ComCommodityEntrySapB1Entity();
                b.setId(rs.getInt("id"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setCustomerName(rs.getString("customerName"));
                b.setState(rs.getString("state"));
                b.setLocation(rs.getString("location"));
                b.setDocumentCode(rs.getString("documentCode"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
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
}
