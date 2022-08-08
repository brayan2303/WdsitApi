package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import net.woden.wdsit.connection.DataSourceWmsConnection;
import net.woden.wdsit.entity.GenCountryEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.GenPlantEntity;
import net.woden.wdsit.entity.RepFieldEntity;
import net.woden.wdsit.entity.RepFilterEntity;
import net.woden.wdsit.entity.RepReportEntity;
import net.woden.wdsit.entity.RepReportPersonEntity;
import net.woden.wdsit.model.RepReportWModel;
import net.woden.wdsit.model.ResponseModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepReportService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceSapColombiaConnection dssColombia;
    @Autowired
    private DataSourceSapEcuadorConnection dssEcuador;
    @Autowired
    private DataSourceSapPeruConnection dssPeru;
    @Autowired
    private DataSourceWmsConnection dsw;
    @Autowired
    private RepFilterService repFilterS;
    @Autowired
    private RepFieldService repFieldS;
    @Autowired
    private GenCustomerService genCustomerS;
    @Autowired
    private GenCountryService genCountryS;
    @Autowired
    private GenPlantService genPlantS;
    @Autowired
    private DataSourceSapColombiaClaroConnection dssColClaro;
    @Autowired
    private DataSourceSapColombiaRadioTechConnection dssColRadio;
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

    public ResponseModel create(RepReportEntity r) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportCreate(?,?,?,?,?)}");
            cs.setString(1, r.getName());
            cs.setString(2, r.getTypeQuery());
            cs.setString(3, r.getStoreProcedure());
            cs.setInt(4, r.getSectionId());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel update(RepReportEntity r) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, r.getId());
            cs.setString(2, r.getName());
            cs.setString(3, r.getTypeQuery());
            cs.setString(4, r.getStoreProcedure());
            cs.setInt(5, r.getSectionId());
            cs.setBoolean(6, r.isActive());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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

    public ResponseModel delete(int reportId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportDelete(?)}");
            cs.setInt(1, reportId);
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

    public ResponseModel add(RepReportPersonEntity r) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportAdd(?,?,?)}");
            cs.setInt(1, r.getPersonId());
            cs.setInt(2, r.getReportId());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel remove(int personId, int reportId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportRemove(?,?)}");
            cs.setInt(1, personId);
            cs.setInt(2, reportId);
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

    public ResponseModel findAll(int personId) {
        ArrayList<RepReportEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportFindAll(?)}");
            cs.setInt(1, personId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepReportEntity r = new RepReportEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setTypeQuery(rs.getString("typeQuery"));
                r.setStoreProcedure(rs.getString("storeProcedure"));
                r.setCreationDate(rs.getDate("creationDate"));
                r.setSectionId(rs.getInt("sectionId"));
                r.setSection(rs.getString("section"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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

    public ResponseModel findById(int reportId) {
        RepReportEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportFindById(?)}");
            cs.setInt(1, reportId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new RepReportEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setTypeQuery(rs.getString("typeQuery"));
                r.setStoreProcedure(rs.getString("storeProcedure"));
                r.setCreationDate(rs.getDate("creationDate"));
                r.setSectionId(rs.getInt("sectionId"));
                r.setSection(rs.getString("section"));
                r.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }

    public ResponseModel findByName(String reportName) {
        RepReportEntity r = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportFindByName(?)}");
            cs.setString(1, reportName);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new RepReportEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setTypeQuery(rs.getString("typeQuery"));
                r.setStoreProcedure(rs.getString("storeProcedure"));
                r.setCreationDate(rs.getDate("creationDate"));
                r.setSectionId(rs.getInt("sectionId"));
                r.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);
    }

    public ResponseModel findByPersonId(int personId, int countryId) {
        ArrayList<RepReportEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportFindByPersonId(?,?)}");
            cs.setInt(1, personId);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepReportEntity r = new RepReportEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setTypeQuery(rs.getString("typeQuery"));
                r.setStoreProcedure(rs.getString("storeProcedure"));
                r.setCreationDate(rs.getDate("creationDate"));
                r.setSectionId(rs.getInt("sectionId"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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

    public ResponseModel list() {
        ArrayList<RepReportEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_RepReportList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepReportEntity r = new RepReportEntity();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setTypeQuery(rs.getString("typeQuery"));
                r.setStoreProcedure(rs.getString("storeProcedure"));
                r.setCreationDate(rs.getDate("creationDate"));
                r.setSectionId(rs.getInt("sectionId"));
                r.setSection(rs.getString("section"));
                r.setActive(rs.getBoolean("active"));
                list.add(r);
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

    public ResponseModel execute(String type, int reportId, String storeProcedure, int countryId, int customerId, int plantId, RepReportWModel wrapper) {
        ArrayList<RepFilterEntity> filterList = (ArrayList<RepFilterEntity>) this.repFilterS.findByReportId(reportId).getObject();
        ArrayList<RepFieldEntity> fieldList = (ArrayList<RepFieldEntity>) this.repFieldS.findByReportId(reportId).getObject();
        String customer = ((GenCustomerEntity) this.genCustomerS.findById(customerId).getObject()).getDescription();
        String country = ((GenCountryEntity) this.genCountryS.findById(countryId).getObject()).getName();
        String plant = "";

        if (plantId != 0) {
            plant = ((GenPlantEntity) this.genPlantS.findById(plantId).getObject()).getCod();
        }

        LinkedHashMap object = new LinkedHashMap();
        JSONArray jsonArray = new JSONArray();
        if (wrapper.getData().length > 0) {
            object = (LinkedHashMap) wrapper.getData()[0];
        }
        Connection cn = this.ds.openConnection();

        if (reportId == 45) {
            cn = this.dssColHughes.openConnection();
        } else if (type.equals("Wms Sap")) {
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
                }else if (customer.equals("RADIO-TECH")) {
                    cn = this.dssColRadio.openConnection();
                }else {
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
                    cn = this.dssEcDirectv.openConnection();
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
            }
        } else {
            cn = this.ds.openConnection();
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call " + storeProcedure.replaceAll("0", "?") + "}");
            for (int i = 0; i < filterList.size(); i++) {
                if (object.containsKey(filterList.get(i).getName())) {
                    switch (filterList.get(i).getType()) {
                        case "text":
                        case "list":
                        case "array":
                            cs.setString(filterList.get(i).getName(), object.values().toArray()[i].toString());
                            break;
                        case "number":
                            cs.setInt(filterList.get(i).getName(), Integer.valueOf(object.values().toArray()[i].toString()));
                            break;
                        case "date":
                            cs.setString(filterList.get(i).getName(), object.values().toArray()[i].toString());
                            break;
                        default:
                            break;
                    }
                }
            }
            cs.setString("Cliente", customer);
            cs.setString("Pais", country);
            if (plantId != 0) {
                cs.setString("Planta", plant);
            }
            JSONObject json;
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                json = new JSONObject();
                for (RepFieldEntity f : fieldList) {
                    json.put(f.getName(), rs.getString(f.getName()));
                }
                jsonArray.add(json);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", jsonArray, 200);
    }

    public ResponseModel executeByReportName(String reportName, String customer, int meta) {
        RepReportEntity repReportEntity = (RepReportEntity) this.findByName(reportName).getObject();
        ArrayList<RepFieldEntity> fieldList = (ArrayList<RepFieldEntity>) this.repFieldS.findByReportId(repReportEntity.getId()).getObject();
        JSONObject json = null;
        Connection cn = this.dssColombia.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call " + repReportEntity.getStoreProcedure().replaceAll("0", "?") + "}");
            if (meta == 0) {
                cs.setString(1, customer);
            } else {
                cs.setString(1, customer);
                cs.setInt(2, meta);
            }
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                json = new JSONObject();
                for (RepFieldEntity f : fieldList) {
                    json.put(f.getName(), rs.getString(f.getName()));
                }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", json, 200);
    }

    public ResponseModel executeByMail(int reportId, String storeProcedure, String initialDate, String finalDate) {
        ArrayList<RepFilterEntity> filterList = (ArrayList<RepFilterEntity>) this.repFilterS.findByReportId(reportId).getObject();
        ArrayList<RepFieldEntity> fieldList = (ArrayList<RepFieldEntity>) this.repFieldS.findByReportId(reportId).getObject();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        JSONArray jsonArray = new JSONArray();
        Connection cn = this.dssColombia.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call " + storeProcedure.replaceAll("0", "?") + "}");
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getName().equals("FechaInicio")) {
                    try {
                        date = format.parse(initialDate);
                        cs.setDate(filterList.get(i).getName(), new java.sql.Date(date.getTime()));
                    } catch (ParseException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                } else if (filterList.get(i).getName().equals("FechaFinal")) {
                    try {
                        date = format.parse(finalDate);
                        cs.setDate(filterList.get(i).getName(), new java.sql.Date(date.getTime()));
                    } catch (ParseException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                JSONObject json = new JSONObject();
                for (RepFieldEntity f : fieldList) {
                    json.put(f.getName(), rs.getString(f.getName()));
                }
                jsonArray.add(json);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", jsonArray, 200);
    }
}
