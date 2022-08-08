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
import net.woden.wdsit.connection.DataSourceSapEcuadorClaroConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorCntConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorHughesConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorPuntoNetConnection;
import net.woden.wdsit.connection.DataSourceSapEcuadorTvCableConnection;
import net.woden.wdsit.connection.DataSourceSapPeruClaroConnection;
import net.woden.wdsit.connection.DataSourceSapPeruConnection;
import net.woden.wdsit.connection.DataSourceSapPeruDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapPeruTelefonicaConnection;
import net.woden.wdsit.entity.DisDailyOperationEntity;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.model.DisDailyOperationModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisDailyOperationService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection spc;
    @Autowired
    private GenCountryService genCountryS;
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
    @Autowired
    private DataSourceSapColombiaConnection dssColombia;
    @Autowired
    private DataSourceSapPeruClaroConnection dssPerClaro;
    @Autowired
    private DataSourceSapPeruTelefonicaConnection dssPerTelefonica;
    @Autowired
    private DataSourceSapPeruDirectvConnection dssPerDirectv;
    @Autowired
    private DataSourceSapEcuadorClaroConnection dssEcClaro;
    @Autowired
    private DataSourceSapEcuadorCntConnection dssEcCnt;
    @Autowired
    private DataSourceSapEcuadorDirectvConnection dssEcDirectv;
    @Autowired
    private DataSourceSapEcuadorHughesConnection dssEcHughes;
    @Autowired
    private DataSourceSapEcuadorPuntoNetConnection dssEcPuntoNet;
    @Autowired
    private DataSourceSapEcuadorTvCableConnection dssEcTvCable;

    public ResponseModel create(int day, DisDailyOperationEntity d) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisDailyOperationCreate(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, d.getCountryId());
            cs.setInt(2, d.getDepartmentId());
            cs.setInt(3, d.getCityId());
            cs.setInt(4, d.getCustomerId());
            cs.setInt(5, d.getYear());
            cs.setInt(6, d.getMonthId());
            cs.setInt(7, day);
            cs.setInt(8, d.getGoalLogistic());
            cs.setInt(9, d.getGoalProduction());
            cs.setInt(10, d.getGoalReconditioning());
            cs.setInt(11, d.getGoalMakeover());
            cs.setInt(12, d.getGoalRepair());
            cs.registerOutParameter(13, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(13);
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

    public ResponseModel update(int id, int goal, String type) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisDailyOperationUpdate(?,?,?,?)}");
            cs.setInt(1, id);
            cs.setInt(2, goal);
            cs.setString(3, type);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(4);
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

    public ResponseModel list(int countryId, int year, int monthId, int day) {
        ArrayList<DisDailyOperationModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisDailyOperationList(?,?,?,?)}");
            cs.setInt(1, countryId);
            cs.setInt(2, year);
            cs.setInt(3, monthId);
            cs.setInt(4, day);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                DisDailyOperationModel d = new DisDailyOperationModel();
                d.setId(rs.getInt("id"));
                d.setCustomerId(rs.getInt("customerId"));
                d.setCustomer(rs.getString("customer"));
                d.setCountryId(rs.getInt("countryId"));
                d.setDepartmentId(rs.getInt("departmentId"));
                d.setCityId(rs.getInt("cityId"));
                d.setCity(rs.getString("city"));
                d.setYear(rs.getInt("year"));
                d.setMonthId(rs.getInt("monthId"));
                d.setLogistic(rs.getInt("logistic"));
                d.setAccumulatedLogistic(rs.getInt("accumulatedLogistic"));
                d.setGoalLogistic(rs.getInt("goalLogistic"));
                d.setProduction(rs.getInt("production"));
                d.setAccumulatedProduction(rs.getInt("accumulatedProduction"));
                d.setGoalProduction(rs.getInt("goalProduction"));
                d.setReconditioning(rs.getInt("reconditioning"));
                d.setAccumulatedReconditioning(rs.getInt("accumulatedReconditioning"));
                d.setGoalReconditioning(rs.getInt("goalReconditioning"));
                d.setMakeover(rs.getInt("makeover"));
                d.setAccumulatedMakeover(rs.getInt("accumulatedMakeover"));
                d.setGoalMakeover(rs.getInt("goalMakeover"));
                d.setRepair(rs.getInt("repair"));
                d.setAccumulatedRepair(rs.getInt("accumulatedRepair"));
                d.setGoalRepair(rs.getInt("goalRepair"));
                d.setDispatch(rs.getInt("dispatch"));
                list.add(d);
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

    public ResponseModel codigoFamiliaList(String customer, int countryId) {
        ArrayList<DisDailyOperationModel> listCod = new ArrayList();
        Connection cn = null;
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();

        if (country.equals("COLOMBIA")) {
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
            } else if(customer.equals("PLATAFORMA MOVIL")){
                cn = this.dssColClaro.openConnection();
            } else if(customer.equals("RED EXTERNA")){
                cn = this.dssColClaro.openConnection();
            } else {
                cn = this.dssColombia.openConnection();
            }

        } else if (country.equals("ECUADOR")) {
            if (customer.equals("CLARO - ECUADOR")) {
                cn = this.dssEcClaro.openConnection();
            } else if (customer.equals("CNT - ECUADOR")) {
                cn = this.dssEcCnt.openConnection();
            } else if (customer.equals("DIRECTV - ECUADOR")) {
                cn = this.dssEcDirectv.openConnection();
            } else if (customer.equals("HUGHES - ECUADOR")) {
                cn = this.dssEcHughes.openConnection();
            } else if (customer.equals("PUNTONET - ECUADOR")) {
                cn = this.dssEcPuntoNet.openConnection();
            } else if (customer.equals("TVCABLE - ECUADOR")) {
                cn = this.dssEcTvCable.openConnection();
            } else {
                cn = this.dssEcuador.openConnection();
            }

        } else if (country.equals("PERU")) {
            if (customer.equals("CLARO -  PERU")) {
                cn = this.dssPerClaro.openConnection();
            } else if (customer.equals("TELEFONICA - PERU")) {
                cn = this.dssPerTelefonica.openConnection();
            } else if (customer.equals("DIRECTV - PERU")) {
                cn = this.dssPerDirectv.openConnection();
            } else {
                cn = this.dssPeru.openConnection();
            }
        } else {
            cn = this.ds.openConnection();
        }
        if (country.equals("COLOMBIA")) {
            try {
                CallableStatement cs = cn.prepareCall("{call wdn.pa_DayliOperationCodigoFamilia(?)}");
                cs.setString(1, customer);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("ECUADOR")) {
            try {
                CallableStatement cs = cn.prepareCall("{call wdn.pa_DayliOperationCodigoFamilia(?)}");
                cs.setString(1, customer);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("PERU")) {
            try {
                CallableStatement cs = cn.prepareCall("{call wdn.pa_DayliOperationCodigoFamilia(?)}");
                cs.setString(1, customer);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("HONDURAS")) {
            try {
                CallableStatement cs = cn.prepareCall("{call dbo.pa_CodigosFamiliaCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("EL SALVADOR")) {
            try {
                CallableStatement cs = cn.prepareCall("{call dbo.pa_CodigosFamiliaCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("COSTA RICA")) {
            try {
                CallableStatement cs = cn.prepareCall("{call dbo.pa_CodigosFamiliaCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        } else if (country.equals("PANAMA")) {
            try {
                CallableStatement cs = cn.prepareCall("{call dbo.pa_CodigosFamiliaCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }else if (country.equals("PARAGUAY")) {
            try {
                CallableStatement cs = cn.prepareCall("{call dbo.pa_CodigosFamiliaCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    DisDailyOperationModel d = new DisDailyOperationModel();
                    d.setCodigoFamilia(rs.getString("CodigoFamilia"));
                    listCod.add(d);
                }
                rs.close();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }  
        return new ResponseModel(getClass().getSimpleName(), "OK", listCod, 200);
    }
}
