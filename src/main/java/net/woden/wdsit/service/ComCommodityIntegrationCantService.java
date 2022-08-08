/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourcConnectionIntegration;
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
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.model.ComCommodityEntrySapCodeModel;
import net.woden.wdsit.model.ComCommodityIntegrationCantModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ComCommodityIntegrationCantService {

    @Autowired
    private DataSourcConnectionIntegration dsi;

    @Autowired
    private DataSourceSapColombiaConnection dsc;

    @Autowired
    private GenCustomerService genCustomerS;

    @Autowired
    private ComCommodityEntryService ComCommodityEntryS;

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
    private GenCountryService genCountryS;

    public ResponseModel list(String pallet) {
        ArrayList<ComCommodityIntegrationCantModel> list = new ArrayList();
        Connection cn = this.dsi.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_cantServiLayerPallet(?)}");
            cs.setString(1, pallet);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ComCommodityIntegrationCantModel s = new ComCommodityIntegrationCantModel();
                s.setCantPrevious(rs.getString("cantPrevious"));
                s.setCantSend(rs.getString("cantSend"));
                s.setCantNew(rs.getString("cantNew"));
                s.setCodSap(rs.getString("codSap"));
                s.setDocEntry(rs.getString("docEntry"));
                s.setDocNum(rs.getString("docNum"));
                s.setPallet(rs.getString("pallet"));
                s.setCodeStatus(rs.getString("codeStatus"));
                list.add(s);
            }
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

    public ResponseModel sapCodeList(int comCommodityEntryId, int countryId) {
        ArrayList<ComCommodityEntrySapCodeModel> list = new ArrayList();
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();
        Connection cn = null;

        ComCommodityEntryEntity entry = (ComCommodityEntryEntity) this.ComCommodityEntryS.findById(comCommodityEntryId).getObject();
        if (entry != null) {
            GenCustomerEntity cc = (GenCustomerEntity) this.genCustomerS.findById(entry.getCustomerId()).getObject();
            String customer = ((GenCustomerEntity) (this.genCustomerS.findById(entry.getCustomerId()).getObject())).getDescription();
            if (cc != null) {
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
                    CallableStatement cs = cn.prepareCall("{call wdn.sp_comCommodityEntrySapCodeList(?)}");
                    cs.setString(1, cc.getDescription());
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        ComCommodityEntrySapCodeModel s = new ComCommodityEntrySapCodeModel();
                        s.setCodigoSap(rs.getString("codigoSap"));
                        s.setDescripcion(rs.getString("nombreSap"));
                        list.add(s);
                    }
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
            } else {
                return new ResponseModel(getClass().getSimpleName(), "ERROR", "No se encuentra el cliente", 200);
            }

        } else {
            return new ResponseModel(getClass().getSimpleName(), "ERROR", "No se encuentra la entrada solicitada", 200);
        }

    }

}
