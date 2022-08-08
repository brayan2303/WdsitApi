package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHpConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorConnection;
import net.woden.wdsit.connection.DataSourceSapPeruConnection;
import net.woden.wdsit.entity.DashBoardClientEntity;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.GenPlantEntity;
import net.woden.wdsit.model.DashBoardClientModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author f.casallas
 */
@Service
public class DashBoardClientService {

    @Autowired
    private DataSourceSapColombiaConnection dsc;
    @Autowired
    private GenCustomerService genCustomer;
    @Autowired
    private GenPlantService genPlantS;
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
    private GenCountryService genCountryS;
    @Autowired
    private DataSourceSapColombiaConnection dssColombia;
    @Autowired
    private DataSourceSapEcuadorConnection dssEcuador;
    @Autowired
    private DataSourceSapPeruConnection dssPeru;

    public ResponseModel FindByCustomerId(int customerId, int countryId) {
        ArrayList<DashBoardClientEntity> list = new ArrayList();
        Connection cn = null;
        String customer = ((GenCustomerEntity) this.genCustomer.findById(customerId).getObject()).getDescription();
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
            CallableStatement cs = cn.prepareCall(("{call wdn.pa_DshCliFamily(?)}"));
            cs.setString(1, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DashBoardClientEntity d = new DashBoardClientEntity();
                d.setFamilia(rs.getString("familia"));
                list.add(d);
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

    public ResponseModel listFamily(int plantId, int customerId, String familia, int countryId) {
        ArrayList<DashBoardClientModel> list = new ArrayList();
        Connection cn = this.dsc.openConnection();
        String customer = ((GenCustomerEntity) this.genCustomer.findById(customerId).getObject()).getDescription();
        String plant = ((GenPlantEntity) this.genPlantS.findById(plantId).getObject()).getCod();
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
            CallableStatement cs = cn.prepareCall(("{call wdn.pa_DshCliLayoutCard(?,?,?)}"));
            cs.setString(1, customer);
            cs.setString(2, plant);
            cs.setString(3, familia);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DashBoardClientModel d = new DashBoardClientModel();
                d.setCantidad(rs.getInt("Cantidad"));
                d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                d.setCliente(rs.getString("Cliente"));
                list.add(d);
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

    public ResponseModel findByFamily(String customer, String familia, int countryId) {
        ArrayList<DashBoardClientModel> list = new ArrayList();
        Connection cn = null;
        String cliente = ((GenCustomerEntity) this.genCustomer.findByCustomer(customer).getObject()).getDescription();
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
            CallableStatement cs = cn.prepareCall(("{call wdn.pa_DshCliLayout(?,?)}"));
            cs.setString(1, cliente);
            cs.setString(2, familia);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DashBoardClientModel d = new DashBoardClientModel();
                d.setCantidad(rs.getInt("Cantidad"));
                d.setCodigoSap(rs.getString(("CodigoSap")));
                d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                d.setCliente(rs.getString("Cliente"));
                list.add(d);
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