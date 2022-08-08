/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.entity.GenCountryAbbreviationEntity;
import net.woden.wdsit.model.ReportValidateLoadCountryModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ReportValidateLoadCountryService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaTigoConnection dsTigo;

    @Autowired
    private Environment en;

    public ResponseModel listCountry(String country) throws IOException, SQLException {
        ArrayList<ReportValidateLoadCountryModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        Connection cns = this.dsTigo.openConnection();
        int load = 0;
        int ints = 0;
        int gerateInt = 0;
        int maxId = 0;
        String abbreviation = "";
        String date = "";
        if (!"COLOMBIA".equals(country)) {
            try {
                CallableStatement cs = cn.prepareCall("{call sp_ReportValidateCountryCenterAmerican(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportValidateLoadCountryModel r = new ReportValidateLoadCountryModel();
                    r.setCountry(rs.getString("COUNTRY"));
                    r.setProcessedDate(rs.getString("PROCESSED_DATE"));
                    r.setModelCode(rs.getString("MODEL_CODE"));
                    r.setModelDescription(rs.getString("MODEL_DESCRIPTION"));
                    r.setCpeCategory(rs.getString("CPE_CATEGORY"));
                    r.setSerialOne(rs.getString("SERIAL 1"));
                    r.setSerialTwo(rs.getString("SERIAL 2"));
                    r.setSerialTheer(rs.getString("SERIAL 3"));
                    r.setLadPhase(rs.getString("LAB_PHASE"));
                    lists.add(r);
                    load = 1;
                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (load != 0) {

                GenCountryAbbreviationEntity r = null;
                this.ds.openConnection();
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_GenCountryAbbreviationFindByCountry(?)}");
                    cs.setString(1, country);
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        r.setAbbreviation(rs.getString("abbreviation"));
                        abbreviation = r.getAbbreviation();

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                        date = dtf.format(LocalDateTime.now());

                        ints = 1;
                    }
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
            if (ints != 0) {
                int inserts = 0;
                Connection cnn = this.ds.openConnection();
                try {
                    CallableStatement cs = cnn.prepareCall("{call sp_LoadValidateLogOneCreate(?,?)}");
                    cs.setString(1, country);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(2);
                    gerateInt = 1;
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } finally {
                    try {
                        cnn.close();
                    } catch (SQLException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
            if (gerateInt != 0) {

                GenCountryAbbreviationEntity r = null;
                Connection cnns = this.ds.openConnection();
                try {
                    CallableStatement cs = cnns.prepareCall("{call sp_LoadValidateLogOneIdMax()}");
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        maxId = r.getId();

                    }
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
            List<List<String>> rows = new ArrayList();
            for (int i = 0; i < lists.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(lists.get(i).getCountry());
                upload.add(lists.get(i).getProcessedDate());
                upload.add(lists.get(i).getModelCode());
                upload.add(lists.get(i).getModelDescription());
                upload.add(lists.get(i).getCpeCategory());
                upload.add(lists.get(i).getSerialOne());
                upload.add(lists.get(i).getSerialTwo());
                upload.add(lists.get(i).getSerialTheer());
                upload.add(lists.get(i).getLadPhase());
                rows.add(upload);

            }
            FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.one.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_LABPROCESSED_" + date + ".csv");
            csvWriter.append("COUNTRY");
            csvWriter.append("|");
            csvWriter.append("PROCESSED_DATE");
            csvWriter.append("|");
            csvWriter.append("MODEL_CODE");
            csvWriter.append("|");
            csvWriter.append("MODEL_DESCRIPTION");
            csvWriter.append("|");
            csvWriter.append("CPE_CATEGORY");
            csvWriter.append("|");
            csvWriter.append("SERIAL 1");
            csvWriter.append("|");
            csvWriter.append("SERIAL 2");
            csvWriter.append("|");
            csvWriter.append("SERIAL 3");
            csvWriter.append("|");
            csvWriter.append("LAB_PHASE");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append(String.join("|", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } else if ("COLOMBIA".equals(country)) {
            try {
                CallableStatement cs = cns.prepareCall("{call wdn.sp_ReportValidaCountryColombia()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportValidateLoadCountryModel r = new ReportValidateLoadCountryModel();
                    r.setCountry(rs.getString("COUNTRY"));
                    r.setProcessedDate(rs.getString("PROCESSED_DATE"));
                    r.setModelCode(rs.getString("MODEL_CODE"));
                    r.setModelDescription(rs.getString("MODEL_DESCRIPTION"));
                    r.setCpeCategory(rs.getString("CPE_CATEGORY"));
                    r.setSerialOne(rs.getString("SERIAL 1"));
                    r.setSerialTwo(rs.getString("SERIAL 2"));
                    r.setSerialTheer(rs.getString("SERIAL 3"));
                    r.setLadPhase(rs.getString("LAB_PHASE"));
                    lists.add(r);
                    load = 1;
                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (load != 0) {

                GenCountryAbbreviationEntity r = null;
                this.ds.openConnection();
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_GenCountryAbbreviationFindByCountry(?)}");
                    cs.setString(1, country);
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        r.setAbbreviation(rs.getString("abbreviation"));
                        abbreviation = r.getAbbreviation();

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                        date = dtf.format(LocalDateTime.now());

                        ints = 1;
                    }
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
            if (ints != 0) {
                int inserts = 0;
                Connection cnn = this.ds.openConnection();
                try {
                    CallableStatement cs = cnn.prepareCall("{call sp_LoadValidateLogOneCreate(?,?)}");
                    cs.setString(1, country);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(2);
                    gerateInt = 1;
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } finally {
                    try {
                        cnn.close();
                    } catch (SQLException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
            if (gerateInt != 0) {

                GenCountryAbbreviationEntity r = null;
                Connection cnns = this.ds.openConnection();
                try {
                    CallableStatement cs = cnns.prepareCall("{call sp_LoadValidateLogOneIdMax()}");
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        maxId = r.getId();

                    }
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
            List<List<String>> rows = new ArrayList();
            for (int i = 0; i < lists.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(lists.get(i).getCountry());
                upload.add(lists.get(i).getProcessedDate());
                upload.add(lists.get(i).getModelCode());
                upload.add(lists.get(i).getModelDescription());
                upload.add(lists.get(i).getCpeCategory());
                upload.add(lists.get(i).getSerialOne());
                upload.add(lists.get(i).getSerialTwo());
                upload.add(lists.get(i).getSerialTheer());
                upload.add(lists.get(i).getLadPhase());
                rows.add(upload);

            }
            FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.one.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_LABPROCESSED_" + date + ".csv");
            csvWriter.append("COUNTRY");
            csvWriter.append("|");
            csvWriter.append("PROCESSED_DATE");
            csvWriter.append("|");
            csvWriter.append("MODEL_CODE");
            csvWriter.append("|");
            csvWriter.append("MODEL_DESCRIPTION");
            csvWriter.append("|");
            csvWriter.append("CPE_CATEGORY");
            csvWriter.append("|");
            csvWriter.append("SERIAL 1");
            csvWriter.append("|");
            csvWriter.append("SERIAL 2");
            csvWriter.append("|");
            csvWriter.append("SERIAL 3");
            csvWriter.append("|");
            csvWriter.append("LAB_PHASE");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append(String.join("|", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } else {

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel listCountryRetry(String country) throws IOException {
        ArrayList<ReportValidateLoadCountryModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        Connection cns = this.dsTigo.openConnection();
        int load = 0;
        int ints = 0;
        int gerateInt = 0;
        int maxId = 0;
        String abbreviation = "";
        String date = "";

        if (!"COLOMBIA".equals(country)) {
            try {
                CallableStatement cs = cn.prepareCall("{call sp_ReportValidateCountryCenterAmericanTwo(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportValidateLoadCountryModel r = new ReportValidateLoadCountryModel();
                    r.setCountry(rs.getString("COUNTRY"));
                    r.setProcessedDate(rs.getString("PRODUCTION_DATE"));
                    r.setModelCode(rs.getString("MODEL_CODE"));
                    r.setModelDescription(rs.getString("MODEL_DESCRIPTION"));
                    r.setCpeCategory(rs.getString("CPE_CATEGORY"));
                    r.setSerialOne(rs.getString("SERIAL 1"));
                    r.setSerialTwo(rs.getString("SERIAL 2"));
                    r.setSerialTheer(rs.getString("SERIAL 3"));
                    r.setRepairLevel(rs.getString("REPAIR_LEVEL"));
                    r.setDiagnostic(rs.getString("DIAGNOSTIC"));
                    r.setRepairCost(rs.getString("REPAIR_COST"));
                    r.setCurrency(rs.getString("CURRENCY"));
                    lists.add(r);
                    load = 1;
                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (load != 0) {

                GenCountryAbbreviationEntity r = null;
                this.ds.openConnection();
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_GenCountryAbbreviationFindByCountry(?)}");
                    cs.setString(1, country);
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        r.setAbbreviation(rs.getString("abbreviation"));
                        abbreviation = r.getAbbreviation();

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                        date = dtf.format(LocalDateTime.now());

                        ints = 1;
                    }
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
            if (ints != 0) {
                int inserts = 0;
                Connection cnn = this.ds.openConnection();
                try {
                    CallableStatement cs = cnn.prepareCall("{call sp_LoadValidateLogTwoCreate(?,?)}");
                    cs.setString(1, country);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(2);
                    gerateInt = 1;
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } finally {
                    try {
                        cnn.close();
                    } catch (SQLException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
            if (gerateInt != 0) {

                GenCountryAbbreviationEntity r = null;
                Connection cnns = this.ds.openConnection();
                try {
                    CallableStatement cs = cnns.prepareCall("{call sp_LoadValidateLogTwoIdMax()}");
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        maxId = r.getId();

                    }
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
            List<List<String>> rows = new ArrayList();
            for (int i = 0; i < lists.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(lists.get(i).getCountry());
                upload.add(lists.get(i).getProcessedDate());
                upload.add(lists.get(i).getModelCode());
                upload.add(lists.get(i).getModelDescription());
                upload.add(lists.get(i).getCpeCategory());
                upload.add(lists.get(i).getSerialOne());
                upload.add(lists.get(i).getSerialTwo());
                upload.add(lists.get(i).getSerialTheer());
                upload.add(lists.get(i).getRepairLevel());
                upload.add(lists.get(i).getDiagnostic());
                upload.add(lists.get(i).getRepairCost());
                upload.add(lists.get(i).getCurrency());

                rows.add(upload);

            }
            FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.two.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_LABPRODUCE_" + date + ".csv");
            csvWriter.append("COUNTRY");
            csvWriter.append("|");
            csvWriter.append("PROCESSED_DATE");
            csvWriter.append("|");
            csvWriter.append("MODEL_CODE");
            csvWriter.append("|");
            csvWriter.append("MODEL_DESCRIPTION");
            csvWriter.append("|");
            csvWriter.append("CPE_CATEGORY");
            csvWriter.append("|");
            csvWriter.append("SERIAL 1");
            csvWriter.append("|");
            csvWriter.append("SERIAL 2");
            csvWriter.append("|");
            csvWriter.append("SERIAL 3");
            csvWriter.append("|");
            csvWriter.append("REPAIR_LEVEL");
            csvWriter.append("|");
            csvWriter.append("DIAGNOSTIC");
            csvWriter.append("|");
            csvWriter.append("REPAIR_COST");
            csvWriter.append("|");
            csvWriter.append("CURRENCY");
            csvWriter.append("|");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append(String.join("|", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } else if ("COLOMBIA".equals(country)) {
            try {
                CallableStatement cs = cns.prepareCall("{call wdn.sp_ReportValidaCountryColombiaTwo()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportValidateLoadCountryModel r = new ReportValidateLoadCountryModel();
                    r.setCountry(rs.getString("COUNTRY"));
                    r.setProcessedDate(rs.getString("PRODUCTION_DATE"));
                    r.setModelCode(rs.getString("MODEL_CODE"));
                    r.setModelDescription(rs.getString("MODEL_DESCRIPTION"));
                    r.setCpeCategory(rs.getString("CPE_CATEGORY"));
                    r.setSerialOne(rs.getString("SERIAL 1"));
                    r.setSerialTwo(rs.getString("SERIAL 2"));
                    r.setSerialTheer(rs.getString("SERIAL 3"));
                    r.setRepairLevel(rs.getString("REPAIR_LEVEL"));
                    r.setDiagnostic(rs.getString("DIAGNOSTIC"));
                    r.setRepairCost(rs.getString("REPAIR_COST"));
                    r.setCurrency(rs.getString("CURRENCY"));
                    lists.add(r);
                    load = 1;
                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (load != 0) {

                GenCountryAbbreviationEntity r = null;
                this.ds.openConnection();
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_GenCountryAbbreviationFindByCountry(?)}");
                    cs.setString(1, country);
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        r.setAbbreviation(rs.getString("abbreviation"));
                        abbreviation = r.getAbbreviation();

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                        date = dtf.format(LocalDateTime.now());

                        ints = 1;
                    }
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
            if (ints != 0) {
                int inserts = 0;
                Connection cnn = this.ds.openConnection();
                try {
                    CallableStatement cs = cnn.prepareCall("{call sp_LoadValidateLogTwoCreate(?,?)}");
                    cs.setString(1, country);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(2);
                    gerateInt = 1;
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } finally {
                    try {
                        cnn.close();
                    } catch (SQLException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }
                }
            }
            if (gerateInt != 0) {

                GenCountryAbbreviationEntity r = null;
                Connection cnns = this.ds.openConnection();
                try {
                    CallableStatement cs = cnns.prepareCall("{call sp_LoadValidateLogTwoIdMax()}");
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        r = new GenCountryAbbreviationEntity();
                        r.setId(rs.getInt("id"));
                        maxId = r.getId();

                    }
                } catch (SQLException ex) {
                    ex.getMessage();
                }
            }
            List<List<String>> rows = new ArrayList();
            for (int i = 0; i < lists.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(lists.get(i).getCountry());
                upload.add(lists.get(i).getProcessedDate());
                upload.add(lists.get(i).getModelCode());
                upload.add(lists.get(i).getModelDescription());
                upload.add(lists.get(i).getCpeCategory());
                upload.add(lists.get(i).getSerialOne());
                upload.add(lists.get(i).getSerialTwo());
                upload.add(lists.get(i).getSerialTheer());
                upload.add(lists.get(i).getRepairLevel());
                upload.add(lists.get(i).getDiagnostic());
                upload.add(lists.get(i).getRepairCost());
                upload.add(lists.get(i).getCurrency());

                rows.add(upload);

            }
            FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.two.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_LABPRODUCE_" + date + ".csv");
            csvWriter.append("COUNTRY");
            csvWriter.append("|");
            csvWriter.append("PROCESSED_DATE");
            csvWriter.append("|");
            csvWriter.append("MODEL_CODE");
            csvWriter.append("|");
            csvWriter.append("MODEL_DESCRIPTION");
            csvWriter.append("|");
            csvWriter.append("CPE_CATEGORY");
            csvWriter.append("|");
            csvWriter.append("SERIAL 1");
            csvWriter.append("|");
            csvWriter.append("SERIAL 2");
            csvWriter.append("|");
            csvWriter.append("SERIAL 3");
            csvWriter.append("|");
            csvWriter.append("REPAIR_LEVEL");
            csvWriter.append("|");
            csvWriter.append("DIAGNOSTIC");
            csvWriter.append("|");
            csvWriter.append("REPAIR_COST");
            csvWriter.append("|");
            csvWriter.append("CURRENCY");
            csvWriter.append("|");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append(String.join("|", rowData));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

        } else {

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel createLogOne(String country) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadValidateLogOneCreate(?,?)}");
            cs.setString(1, country);
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

    public ResponseModel createLogTwo(String country) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadValidateLogTwoCreate(?,?)}");
            cs.setString(1, country);
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
}
