/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.entity.GenCountryAbbreviationEntity;
import net.woden.wdsit.model.ReportValidateLoadWfsmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ReportValidateLoadWfsmService {

    @Autowired
    private Environment en;

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaTigoConnection dsTigo;

    public ArrayList<ReportValidateLoadWfsmModel> wfsm(String abreviatura, String country, String customer) throws Exception {

        String rowOne = "";
        String rowTwo = "";
        String rowTheer = "";
        String rowFour = "";
        String rowFive = "";
        String rowSix = "";
        String rowSixR = "";
        String rowOneR = "";
        String rowTwoR = "";
        String cpeCategory = "";
        String modelCode = "";
        String codSap = "";
        String modelDescription = "";
        String dxType = "";
        String region = "";
        String abbreviation = "";
        String date = "";
        int load = 0;
        int inset = 0;
        int generateId = 0;
        int maxId = 0;
        String fechaTexto = "";
        ArrayList<ReportValidateLoadWfsmModel> listWfsm = new ArrayList();
        ArrayList<ReportValidateLoadWfsmModel> lists = new ArrayList();

        Connection cn = this.ds.openConnection();
        Connection cns = this.dsTigo.openConnection();

        //WFSM CONEXION
        FileReader archCSV = null;
        CSVReader csvReader = null;
        String urls = (this.en.getProperty("milicom.root.wfsm") + abreviatura + "/" + customer);
        StringBuilder resultado = new StringBuilder();
        URL url = new URL(urls);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
            String linea = null;
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
                load = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ABRIEVATURA PAIS

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
                    inset = 1;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                    date = dtf.format(LocalDateTime.now());
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        if (inset != 0) {
            int inserts = 0;
            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadValidateLogTheerCreate(?,?)}");
                cs.setString(1, country);
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(2);
                generateId = 1;
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }

        if (generateId != 0) {

            GenCountryAbbreviationEntity r = null;
            this.ds.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadValidateLogTheerIdMax()}");
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

        //GENERACION DE ARCHIVO
        String urlsW = "http://wfsapi.tcpip.tech/api/reportes/" + resultado.toString();
        URL ficheroUrl = new URL(urlsW);
        OutputStream outputStream = null;
        try (InputStream inputStream = ficheroUrl.openStream()) {
            outputStream = new FileOutputStream(this.en.getProperty("milicom.data.url") + "\\" + abreviatura + customer + "REVERSE_COLLECTION.csv");

            byte[] b = new byte[2048];
            int longitud;
            while ((longitud = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, longitud);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //LECTURA DE ARCHIVO Y GENERACION DE LISTADO
        try {
            archCSV = new FileReader(this.en.getProperty("milicom.data.url") + "\\" + abreviatura + customer + "REVERSE_COLLECTION.csv");
            CSVParser coma = new CSVParserBuilder().withSeparator(',').build();
            csvReader = new CSVReaderBuilder(archCSV).withCSVParser(coma).build();
            String[] fila = null;
            int i = 0;

            while ((fila = csvReader.readNext()) != null) {
                if (i != 0) {
                    rowOne = fila[6];
                    rowOneR = rowOne.replace("=", "").replace("'", "").replaceAll("\"", "");
                    rowTwo = fila[7];
                    rowTheer = fila[8];
                    rowFour = fila[9];
                    rowFive = fila[19]; //fecha

                    Date fechaTexto1 = new Date(rowFive.toString());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fechaTextoG = formatter.format(fechaTexto1);

                    rowSix = fila[32];
                    rowSixR = rowSix.replace("=", "").replace("'", "").replaceAll("\"", "");

                    CallableStatement cs = cn.prepareCall("{call sp_ReporValidateReportFileSerial(?,?)}");
                    cs.setString(1, rowSixR);
                    cs.setString(2, country);
                    ResultSet rs = cs.executeQuery();
                    ReportValidateLoadWfsmModel r = null;
                    while (rs.next()) {
                        r = new ReportValidateLoadWfsmModel();
                        r.setModelCode(rs.getString("modelCode"));
                        r.setDxType(rs.getString("dxType"));
                        r.setRegion(rs.getString("region"));
                    }

                    if (r != null) {
                        codSap = r.getModelCode();
                        dxType = r.getDxType();
                        region = r.getRegion();
                        CallableStatement cbs = cns.prepareCall("{call wdn.pa_ReportLoadValidateSearchCodeSap(?)}");
                        cbs.setString(1, codSap);
                        ResultSet rst = cbs.executeQuery();
                        ReportValidateLoadWfsmModel rv = null;
                        while (rst.next()) {
                            rv = new ReportValidateLoadWfsmModel();
                            rv.setCodigoSap(rst.getString("CodigoSap"));
                            rv.setNombreSap(rst.getString("NombreSap"));
                            rv.setCodigoFamilia(rst.getString("CodigoFamilia"));
                        }
                        if (rv != null) {
                            modelCode = codSap;
                            modelDescription = rv.getNombreSap();

                            ReportValidateLoadWfsmModel rF = new ReportValidateLoadWfsmModel();
                            rF.setCountry(country);
                            rF.setCollectionDate(fechaTextoG);
                            rF.setContractorName(rowOne);
                            rF.setModelCode(codSap);
                            rF.setModelDescription(modelDescription);
                            rF.setCpeCategory(rv.getCodigoFamilia());
                            if ("".equals(dxType)) {
                                rF.setDxType("NO CRUZA");
                            } else {
                                rF.setDxType(dxType);
                            }
                            rF.setSerialOne(rowSixR);
                            rF.setAddress(rowTwo);
                            rF.setRegion(region);
                            rF.setCity(rowTheer);
                            rF.setDepartement(rowFour);
                            listWfsm.add(rF);
                        } else {

                            ReportValidateLoadWfsmModel rF = new ReportValidateLoadWfsmModel();
                            rF.setCountry(country);
                            rF.setCollectionDate(fechaTextoG);
                            rF.setContractorName(rowOne);
                            rF.setModelCode("NO CRUZA");
                            rF.setModelDescription("NO CRUZA");
                            rF.setCpeCategory("NO CRUZA");
                            if ("".equals(dxType)) {
                                rF.setDxType("NO CRUZA");
                            } else {
                                rF.setDxType(dxType);
                            }
                            rF.setSerialOne(rowSixR);
                            rF.setAddress(rowTwo);
                            rF.setRegion(region);
                            rF.setCity(rowTheer);
                            rF.setDepartement(rowFour);
                            listWfsm.add(rF);

                        }

                    } else {
                        ReportValidateLoadWfsmModel rF = new ReportValidateLoadWfsmModel();
                        rF.setCountry(country);
                        rF.setCollectionDate(fechaTextoG);
                        rF.setContractorName(rowOne);
                        rF.setModelCode("NO CRUZA");
                        rF.setModelDescription("NO CRUZA");
                        rF.setCpeCategory("NO CRUZA");
                        if ("".equals(dxType)) {
                            rF.setDxType("NO CRUZA");
                        } else {
                            rF.setDxType(dxType);
                        }
                        rF.setSerialOne(rowSixR);
                        rF.setAddress(rowTwo);
                        rF.setRegion(region);
                        rF.setCity(rowTheer);
                        rF.setDepartement(rowFour);
                        listWfsm.add(rF);

                    }
                }
                i++;

            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                archCSV.close();
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < listWfsm.size(); i++) {
            List<String> upload = new ArrayList<String>();
            upload.add(listWfsm.get(i).getCountry());
            upload.add(listWfsm.get(i).getCollectionDate());
            upload.add(listWfsm.get(i).getContractorName());
            upload.add(listWfsm.get(i).getModelCode());
            upload.add(listWfsm.get(i).getModelDescription());
            upload.add(listWfsm.get(i).getCpeCategory());
            upload.add(listWfsm.get(i).getDxType());
            upload.add(listWfsm.get(i).getSerialOne());
            upload.add(listWfsm.get(i).getAddress());
            upload.add(listWfsm.get(i).getRegion());
            upload.add(listWfsm.get(i).getCity());
            upload.add(listWfsm.get(i).getDepartement());
            rows.add(upload);

        }

        FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.theer.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_COLLECTION_"+ date +".csv");
        csvWriter.append("COUNTRY");
        csvWriter.append("|");
        csvWriter.append("COLLECTION_DATE");
        csvWriter.append("|");
        csvWriter.append("CONTRACTOR_NAME");
        csvWriter.append("|");
        csvWriter.append("MODEL_CODE");
        csvWriter.append("|");
        csvWriter.append("MODEL_DESCRIPTION");
        csvWriter.append("|");
        csvWriter.append("CPE_CATEGORY");
        csvWriter.append("|");
        csvWriter.append("DX_TYPE");
        csvWriter.append("|");
        csvWriter.append("SERIAL 1");
        csvWriter.append("|");
        csvWriter.append("ADDRESS");
        csvWriter.append("|");
        csvWriter.append("REGION");
        csvWriter.append("|");
        csvWriter.append("CITY");
        csvWriter.append("|");
        csvWriter.append("DEPARTAMENT");
        csvWriter.append("|");
        csvWriter.append("\n");

        for (List<String> rowData : rows) {
            csvWriter.append(String.join("|", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

        return listWfsm;

    }
}
