/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaRadioTechConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.model.ReportWipAutomaticModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ReportWipAutomaticService {

    @Autowired
    private DataSourceSapColombiaClaroConnection dsclaro;
    @Autowired
    private DataSourceSapColombiaClaroConnection dsPlataFormaM;
    @Autowired
    private DataSourceSapColombiaClaroConnection dsResEx;
    @Autowired
    private DataSourceSapColombiaDirectvConnection dsDirectv;
    @Autowired
    private DataSourceSapColombiaEtbConnection dsEtb;
    @Autowired
    private DataSourceSapColombiaHughesConnection dsHughes;
    @Autowired
    private DataSourceSapColombiaTigoConnection dsTigo;
    @Autowired
    private DataSourceSapColombiaRadioTechConnection dsRadioTech;
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel generateWip() throws IOException {
        this.deleteFiles();
        ArrayList<ReportWipAutomaticModel> lists = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsPm = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsRe = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsEt = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsTiB = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsTiM = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsDRS = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsDR = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsCL = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsCLS = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsCLF = new ArrayList();
        ArrayList<ReportWipAutomaticModel> listsRT = new ArrayList();

        Connection cnClaro = this.dsclaro.openConnection();
        Connection cnClaroSmart = this.dsclaro.openConnection();
        Connection cnClaroFon = this.dsclaro.openConnection();
        Connection cnDirectvSmart = this.dsDirectv.openConnection();
        Connection cnDirectv = this.dsDirectv.openConnection();
        Connection cnEtba = this.dsEtb.openConnection();
        Connection cnHughes = this.dsHughes.openConnection();
        Connection cnTigoB = this.dsTigo.openConnection();
        Connection cnTigoM = this.dsTigo.openConnection();
        Connection cnPlata = this.dsPlataFormaM.openConnection();
        Connection cnRedEx = this.dsResEx.openConnection();
        Connection cnRadioTech = this.dsRadioTech.openConnection();

        int claroSmartcard = 0;
        int claroFont = 0;
        int claro = 0;
        int plataformaMovil = 0;
        int redExterna = 0;
        int directv = 0;
        int directvSmartcard = 0;
        int etb = 0;
        int tigoB = 0;
        int tigoM = 0;
        int radioTech = 0;

        try {
            CallableStatement cs = cnHughes.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
            cs.setString(1, "");
            cs.setString(2, "COLOMBIA");
            cs.setString(3, "HUGHES");
            cs.setString(4, "BTA001");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                r.setCardCode(rs.getString("CardCode"));
                r.setCardName(rs.getString("CardName"));
                r.setSerial(rs.getString("Serial"));
                r.setMac(rs.getString("Mac"));
                r.setCodigoSap(rs.getString("CodigoSap"));
                r.setDescripcion(rs.getString("Descripcion"));
                r.setPallet(rs.getString("Pallet"));
                r.setTipologia(rs.getString("Tipologia"));
                r.setTipoOrigen(rs.getString("TipoOrigen"));
                r.setOrigen(rs.getString("Origen"));
                r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                r.setEstado(rs.getString("Estado"));
                r.setUbicacion(rs.getString("Ubicacion"));
                r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                r.setFechaIngreso(rs.getString("FechaIngreso"));
                r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                lists.add(r);

            }
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        List<List<String>> rows = new ArrayList();
        for (int i = 0; i < lists.size(); i++) {
            List<String> upload = new ArrayList<String>();
            upload.add(lists.get(i).getCardCode());
            upload.add(lists.get(i).getCardName());
            upload.add(lists.get(i).getSerial());
            upload.add(lists.get(i).getMac());
            upload.add(lists.get(i).getCodigoSap());
            upload.add(lists.get(i).getDescripcion());
            upload.add(lists.get(i).getPallet());
            upload.add(lists.get(i).getTipologia());
            upload.add(lists.get(i).getTipoOrigen());
            upload.add(lists.get(i).getOrigen());
            upload.add(lists.get(i).getGarantiaFabricante());
            upload.add(lists.get(i).getEstado());
            upload.add(lists.get(i).getUbicacion());
            upload.add(lists.get(i).getCantidadIngreso());
            upload.add(lists.get(i).getFechaIngreso());
            upload.add(lists.get(i).getEstadoSmartcard());
            rows.add(upload);

        }
        FileWriter fw = new FileWriter(this.en.getProperty("descarga.cliente.hughes.url") + "\\" + "WIP HUGHES.csv");
        fw.append("CardCode");
        fw.append("\\");
        fw.append("CardName");
        fw.append("\\");
        fw.append("Serial");
        fw.append("\\");
        fw.append("Mac");
        fw.append("\\");
        fw.append("CodigoSap");
        fw.append("\\");
        fw.append("Descripcion");
        fw.append("\\");
        fw.append("Pallet");
        fw.append("\\");
        fw.append("Tipologia");
        fw.append("\\");
        fw.append("TipoOrigen");
        fw.append("\\");
        fw.append("Origen");
        fw.append("\\");
        fw.append("GarantiaFabricante");
        fw.append("\\");
        fw.append("Estado");
        fw.append("\\");
        fw.append("Ubicacion");
        fw.append("\\");
        fw.append("CantidadIngreso");
        fw.append("\\");
        fw.append("FechaIngreso");
        fw.append("\\");
        fw.append("EstadoSmartcard");
        fw.append("\\");
        fw.append("\n");

        for (List<String> rowData : rows) {
            fw.append(String.join("\\", rowData));
            fw.append("\n");
        }
        fw.flush();
        fw.close();
        plataformaMovil = 1;

        if (plataformaMovil != 0) {
            try {
                CallableStatement cs = cnPlata.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "PLATAFORMA MOVIL");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsPm.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsPm = new ArrayList();
            for (int i = 0; i < listsPm.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsPm.get(i).getCardCode());
                upload.add(listsPm.get(i).getCardName());
                upload.add(listsPm.get(i).getSerial());
                upload.add(listsPm.get(i).getMac());
                upload.add(listsPm.get(i).getCodigoSap());
                upload.add(listsPm.get(i).getDescripcion());
                upload.add(listsPm.get(i).getPallet());
                upload.add(listsPm.get(i).getTipologia());
                upload.add(listsPm.get(i).getTipoOrigen());
                upload.add(listsPm.get(i).getOrigen());
                upload.add(listsPm.get(i).getGarantiaFabricante());
                upload.add(listsPm.get(i).getEstado());
                upload.add(listsPm.get(i).getUbicacion());
                upload.add(listsPm.get(i).getCantidadIngreso());
                upload.add(listsPm.get(i).getFechaIngreso());
                upload.add(listsPm.get(i).getEstadoSmartcard());
                rowsPm.add(upload);

            }
            FileWriter fwPm = new FileWriter(this.en.getProperty("descarga.cliente.plataforma.url") + "\\" + "WIP PLATAFORMA MOVIL.csv");
            fwPm.append("CardCode");
            fwPm.append("\\");
            fwPm.append("CardName");
            fwPm.append("\\");
            fwPm.append("Serial");
            fwPm.append("\\");
            fwPm.append("Mac");
            fwPm.append("\\");
            fwPm.append("CodigoSap");
            fwPm.append("\\");
            fwPm.append("Descripcion");
            fwPm.append("\\");
            fwPm.append("Pallet");
            fwPm.append("\\");
            fwPm.append("Tipologia");
            fwPm.append("\\");
            fwPm.append("TipoOrigen");
            fwPm.append("\\");
            fwPm.append("Origen");
            fwPm.append("\\");
            fwPm.append("GarantiaFabricante");
            fwPm.append("\\");
            fwPm.append("Estado");
            fwPm.append("\\");
            fwPm.append("Ubicacion");
            fwPm.append("\\");
            fwPm.append("CantidadIngreso");
            fwPm.append("\\");
            fwPm.append("FechaIngreso");
            fwPm.append("\\");
            fwPm.append("EstadoSmartcard");
            fwPm.append("\\");
            fwPm.append("\n");

            for (List<String> rowDataPm : rowsPm) {
                fwPm.append(String.join("\\", rowDataPm));
                fwPm.append("\n");
            }
            fwPm.flush();
            fwPm.close();
            redExterna = 1;
        }
        if (redExterna != 0) {
            try {
                CallableStatement cs = cnRedEx.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "RED EXTERNA");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsRe.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsRe = new ArrayList();
            for (int i = 0; i < listsRe.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsRe.get(i).getCardCode());
                upload.add(listsRe.get(i).getCardName());
                upload.add(listsRe.get(i).getSerial());
                upload.add(listsRe.get(i).getMac());
                upload.add(listsRe.get(i).getCodigoSap());
                upload.add(listsRe.get(i).getDescripcion());
                upload.add(listsRe.get(i).getPallet());
                upload.add(listsRe.get(i).getTipologia());
                upload.add(listsRe.get(i).getTipoOrigen());
                upload.add(listsRe.get(i).getOrigen());
                upload.add(listsRe.get(i).getGarantiaFabricante());
                upload.add(listsRe.get(i).getEstado());
                upload.add(listsRe.get(i).getUbicacion());
                upload.add(listsRe.get(i).getCantidadIngreso());
                upload.add(listsRe.get(i).getFechaIngreso());
                upload.add(listsRe.get(i).getEstadoSmartcard());
                rowsRe.add(upload);

            }
            FileWriter fwRe = new FileWriter(this.en.getProperty("descarga.cliente.red.url") + "\\" + "WIP RED EXTERNA.csv");
            fwRe.append("CardCode");
            fwRe.append("\\");
            fwRe.append("CardName");
            fwRe.append("\\");
            fwRe.append("Serial");
            fwRe.append("\\");
            fwRe.append("Mac");
            fwRe.append("\\");
            fwRe.append("CodigoSap");
            fwRe.append("\\");
            fwRe.append("Descripcion");
            fwRe.append("\\");
            fwRe.append("Pallet");
            fwRe.append("\\");
            fwRe.append("Tipologia");
            fwRe.append("\\");
            fwRe.append("TipoOrigen");
            fwRe.append("\\");
            fwRe.append("Origen");
            fwRe.append("\\");
            fwRe.append("GarantiaFabricante");
            fwRe.append("\\");
            fwRe.append("Estado");
            fwRe.append("\\");
            fwRe.append("Ubicacion");
            fwRe.append("\\");
            fwRe.append("CantidadIngreso");
            fwRe.append("\\");
            fwRe.append("FechaIngreso");
            fwRe.append("\\");
            fwRe.append("EstadoSmartcard");
            fwRe.append("\\");
            fwRe.append("\n");

            for (List<String> rowDataRe : rowsRe) {
                fwRe.append(String.join("\\", rowDataRe));
                fwRe.append("\n");
            }
            fwRe.flush();
            fwRe.close();
            etb = 1;
        }
        if (etb != 0) {
            try {
                CallableStatement cs = cnEtba.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "ETB");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsEt.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsEt = new ArrayList();
            for (int i = 0; i < listsEt.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsEt.get(i).getCardCode());
                upload.add(listsEt.get(i).getCardName());
                upload.add(listsEt.get(i).getSerial());
                upload.add(listsEt.get(i).getMac());
                upload.add(listsEt.get(i).getCodigoSap());
                upload.add(listsEt.get(i).getDescripcion());
                upload.add(listsEt.get(i).getPallet());
                upload.add(listsEt.get(i).getTipologia());
                upload.add(listsEt.get(i).getTipoOrigen());
                upload.add(listsEt.get(i).getOrigen());
                upload.add(listsEt.get(i).getGarantiaFabricante());
                upload.add(listsEt.get(i).getEstado());
                upload.add(listsEt.get(i).getUbicacion());
                upload.add(listsEt.get(i).getCantidadIngreso());
                upload.add(listsEt.get(i).getFechaIngreso());
                upload.add(listsEt.get(i).getEstadoSmartcard());
                rowsEt.add(upload);

            }
            FileWriter fwEt = new FileWriter(this.en.getProperty("descarga.cliente.etb.url") + "\\" + "WIP ETB.csv");
            fwEt.append("CardCode");
            fwEt.append("\\");
            fwEt.append("CardName");
            fwEt.append("\\");
            fwEt.append("Serial");
            fwEt.append("\\");
            fwEt.append("Mac");
            fwEt.append("\\");
            fwEt.append("CodigoSap");
            fwEt.append("\\");
            fwEt.append("Descripcion");
            fwEt.append("\\");
            fwEt.append("Pallet");
            fwEt.append("\\");
            fwEt.append("Tipologia");
            fwEt.append("\\");
            fwEt.append("TipoOrigen");
            fwEt.append("\\");
            fwEt.append("Origen");
            fwEt.append("\\");
            fwEt.append("GarantiaFabricante");
            fwEt.append("\\");
            fwEt.append("Estado");
            fwEt.append("\\");
            fwEt.append("Ubicacion");
            fwEt.append("\\");
            fwEt.append("CantidadIngreso");
            fwEt.append("\\");
            fwEt.append("FechaIngreso");
            fwEt.append("\\");
            fwEt.append("EstadoSmartcard");
            fwEt.append("\\");
            fwEt.append("\n");

            for (List<String> rowDataEt : rowsEt) {
                fwEt.append(String.join("\\", rowDataEt));
                fwEt.append("\n");
            }
            fwEt.flush();
            fwEt.close();
            tigoB = 1;
        }
        if (tigoB != 0) {
            try {
                CallableStatement cs = cnTigoB.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "TIGO");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsTiB.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsTib = new ArrayList();
            for (int i = 0; i < listsTiB.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsTiB.get(i).getCardCode());
                upload.add(listsTiB.get(i).getCardName());
                upload.add(listsTiB.get(i).getSerial());
                upload.add(listsTiB.get(i).getMac());
                upload.add(listsTiB.get(i).getCodigoSap());
                upload.add(listsTiB.get(i).getDescripcion());
                upload.add(listsTiB.get(i).getPallet());
                upload.add(listsTiB.get(i).getTipologia());
                upload.add(listsTiB.get(i).getTipoOrigen());
                upload.add(listsTiB.get(i).getOrigen());
                upload.add(listsTiB.get(i).getGarantiaFabricante());
                upload.add(listsTiB.get(i).getEstado());
                upload.add(listsTiB.get(i).getUbicacion());
                upload.add(listsTiB.get(i).getCantidadIngreso());
                upload.add(listsTiB.get(i).getFechaIngreso());
                upload.add(listsTiB.get(i).getEstadoSmartcard());
                rowsTib.add(upload);

            }
            FileWriter fwTiB = new FileWriter(this.en.getProperty("descarga.cliente.tigo.url") + "\\" + "WIP TIGO BOGOTA.csv");
            fwTiB.append("CardCode");
            fwTiB.append("\\");
            fwTiB.append("CardName");
            fwTiB.append("\\");
            fwTiB.append("Serial");
            fwTiB.append("\\");
            fwTiB.append("Mac");
            fwTiB.append("\\");
            fwTiB.append("CodigoSap");
            fwTiB.append("\\");
            fwTiB.append("Descripcion");
            fwTiB.append("\\");
            fwTiB.append("Pallet");
            fwTiB.append("\\");
            fwTiB.append("Tipologia");
            fwTiB.append("\\");
            fwTiB.append("TipoOrigen");
            fwTiB.append("\\");
            fwTiB.append("Origen");
            fwTiB.append("\\");
            fwTiB.append("GarantiaFabricante");
            fwTiB.append("\\");
            fwTiB.append("Estado");
            fwTiB.append("\\");
            fwTiB.append("Ubicacion");
            fwTiB.append("\\");
            fwTiB.append("CantidadIngreso");
            fwTiB.append("\\");
            fwTiB.append("FechaIngreso");
            fwTiB.append("\\");
            fwTiB.append("EstadoSmartcard");
            fwTiB.append("\\");
            fwTiB.append("\n");

            for (List<String> rowDataTib : rowsTib) {
                fwTiB.append(String.join("\\", rowDataTib));
                fwTiB.append("\n");
            }
            fwTiB.flush();
            fwTiB.close();
            tigoM = 1;
        }
        if (tigoM != 0) {
            try {
                CallableStatement cs = cnTigoM.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "TIGO");
                cs.setString(4, "MED001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsTiM.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsTiM = new ArrayList();
            for (int i = 0; i < listsTiM.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsTiM.get(i).getCardCode());
                upload.add(listsTiM.get(i).getCardName());
                upload.add(listsTiM.get(i).getSerial());
                upload.add(listsTiM.get(i).getMac());
                upload.add(listsTiM.get(i).getCodigoSap());
                upload.add(listsTiM.get(i).getDescripcion());
                upload.add(listsTiM.get(i).getPallet());
                upload.add(listsTiM.get(i).getTipologia());
                upload.add(listsTiM.get(i).getTipoOrigen());
                upload.add(listsTiM.get(i).getOrigen());
                upload.add(listsTiM.get(i).getGarantiaFabricante());
                upload.add(listsTiM.get(i).getEstado());
                upload.add(listsTiM.get(i).getUbicacion());
                upload.add(listsTiM.get(i).getCantidadIngreso());
                upload.add(listsTiM.get(i).getFechaIngreso());
                upload.add(listsTiM.get(i).getEstadoSmartcard());
                rowsTiM.add(upload);

            }
            FileWriter fwTiM = new FileWriter(this.en.getProperty("descarga.cliente.medellin.url") + "\\" + "WIP TIGO MEDELLIN.csv");
            fwTiM.append("CardCode");
            fwTiM.append("\\");
            fwTiM.append("CardName");
            fwTiM.append("\\");
            fwTiM.append("Serial");
            fwTiM.append("\\");
            fwTiM.append("Mac");
            fwTiM.append("\\");
            fwTiM.append("CodigoSap");
            fwTiM.append("\\");
            fwTiM.append("Descripcion");
            fwTiM.append("\\");
            fwTiM.append("Pallet");
            fwTiM.append("\\");
            fwTiM.append("Tipologia");
            fwTiM.append("\\");
            fwTiM.append("TipoOrigen");
            fwTiM.append("\\");
            fwTiM.append("Origen");
            fwTiM.append("\\");
            fwTiM.append("GarantiaFabricante");
            fwTiM.append("\\");
            fwTiM.append("Estado");
            fwTiM.append("\\");
            fwTiM.append("Ubicacion");
            fwTiM.append("\\");
            fwTiM.append("CantidadIngreso");
            fwTiM.append("\\");
            fwTiM.append("FechaIngreso");
            fwTiM.append("\\");
            fwTiM.append("EstadoSmartcard");
            fwTiM.append("\\");
            fwTiM.append("\n");

            for (List<String> rowDataTiM : rowsTiM) {
                fwTiM.append(String.join("\\", rowDataTiM));
                fwTiM.append("\n");
            }
            fwTiM.flush();
            fwTiM.close();
            directvSmartcard = 1;
        }
        if (directvSmartcard != 0) {
            try {
                CallableStatement cs = cnDirectvSmart.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "SMARTCARD");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "DIRECTV");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsDRS.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsDRS = new ArrayList();
            for (int i = 0; i < listsDRS.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsDRS.get(i).getCardCode());
                upload.add(listsDRS.get(i).getCardName());
                upload.add(listsDRS.get(i).getSerial());
                upload.add(listsDRS.get(i).getMac());
                upload.add(listsDRS.get(i).getCodigoSap());
                upload.add(listsDRS.get(i).getDescripcion());
                upload.add(listsDRS.get(i).getPallet());
                upload.add(listsDRS.get(i).getTipologia());
                upload.add(listsDRS.get(i).getTipoOrigen());
                upload.add(listsDRS.get(i).getOrigen());
                upload.add(listsDRS.get(i).getGarantiaFabricante());
                upload.add(listsDRS.get(i).getEstado());
                upload.add(listsDRS.get(i).getUbicacion());
                upload.add(listsDRS.get(i).getCantidadIngreso());
                upload.add(listsDRS.get(i).getFechaIngreso());
                upload.add(listsDRS.get(i).getEstadoSmartcard());
                rowsDRS.add(upload);

            }
            FileWriter fwDRS = new FileWriter(this.en.getProperty("descarga.cliente.directvSmart.url") + "\\" + "WIP DIRECTV SMARTCARD.csv");
            fwDRS.append("CardCode");
            fwDRS.append("\\");
            fwDRS.append("CardName");
            fwDRS.append("\\");
            fwDRS.append("Serial");
            fwDRS.append("\\");
            fwDRS.append("Mac");
            fwDRS.append("\\");
            fwDRS.append("CodigoSap");
            fwDRS.append("\\");
            fwDRS.append("Descripcion");
            fwDRS.append("\\");
            fwDRS.append("Pallet");
            fwDRS.append("\\");
            fwDRS.append("Tipologia");
            fwDRS.append("\\");
            fwDRS.append("TipoOrigen");
            fwDRS.append("\\");
            fwDRS.append("Origen");
            fwDRS.append("\\");
            fwDRS.append("GarantiaFabricante");
            fwDRS.append("\\");
            fwDRS.append("Estado");
            fwDRS.append("\\");
            fwDRS.append("Ubicacion");
            fwDRS.append("\\");
            fwDRS.append("CantidadIngreso");
            fwDRS.append("\\");
            fwDRS.append("FechaIngreso");
            fwDRS.append("\\");
            fwDRS.append("EstadoSmartcard");
            fwDRS.append("\\");
            fwDRS.append("\n");

            for (List<String> rowDataDRS : rowsDRS) {
                fwDRS.append(String.join("\\", rowDataDRS));
                fwDRS.append("\n");
            }
            fwDRS.flush();
            fwDRS.close();
            directv = 1;
        }
        if (directv != 0) {
            try {
                CallableStatement cs = cnDirectv.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "NO");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "DIRECTV");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsDR.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsDR = new ArrayList();
            for (int i = 0; i < listsDR.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsDR.get(i).getCardCode());
                upload.add(listsDR.get(i).getCardName());
                upload.add(listsDR.get(i).getSerial());
                upload.add(listsDR.get(i).getMac());
                upload.add(listsDR.get(i).getCodigoSap());
                upload.add(listsDR.get(i).getDescripcion());
                upload.add(listsDR.get(i).getPallet());
                upload.add(listsDR.get(i).getTipologia());
                upload.add(listsDR.get(i).getTipoOrigen());
                upload.add(listsDR.get(i).getOrigen());
                upload.add(listsDR.get(i).getGarantiaFabricante());
                upload.add(listsDR.get(i).getEstado());
                upload.add(listsDR.get(i).getUbicacion());
                upload.add(listsDR.get(i).getCantidadIngreso());
                upload.add(listsDR.get(i).getFechaIngreso());
                upload.add(listsDR.get(i).getEstadoSmartcard());
                rowsDR.add(upload);

            }
            FileWriter fwDR = new FileWriter(this.en.getProperty("descarga.cliente.directv.url") + "\\" + "WIP DIRECTV NO SMARTCARD.csv");
            fwDR.append("CardCode");
            fwDR.append("\\");
            fwDR.append("CardName");
            fwDR.append("\\");
            fwDR.append("Serial");
            fwDR.append("\\");
            fwDR.append("Mac");
            fwDR.append("\\");
            fwDR.append("CodigoSap");
            fwDR.append("\\");
            fwDR.append("Descripcion");
            fwDR.append("\\");
            fwDR.append("Pallet");
            fwDR.append("\\");
            fwDR.append("Tipologia");
            fwDR.append("\\");
            fwDR.append("TipoOrigen");
            fwDR.append("\\");
            fwDR.append("Origen");
            fwDR.append("\\");
            fwDR.append("GarantiaFabricante");
            fwDR.append("\\");
            fwDR.append("Estado");
            fwDR.append("\\");
            fwDR.append("Ubicacion");
            fwDR.append("\\");
            fwDR.append("CantidadIngreso");
            fwDR.append("\\");
            fwDR.append("FechaIngreso");
            fwDR.append("\\");
            fwDR.append("EstadoSmartcard");
            fwDR.append("\\");
            fwDR.append("\n");

            for (List<String> rowDataDR : rowsDR) {
                fwDR.append(String.join("\\", rowDataDR));
                fwDR.append("\n");
            }
            fwDR.flush();
            fwDR.close();
            claro = 1;
        }
        if (claro != 0) {
            try {
                CallableStatement cs = cnClaro.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "NO");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "CLARO");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsCL.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsCL = new ArrayList();
            for (int i = 0; i < listsCL.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsCL.get(i).getCardCode());
                upload.add(listsCL.get(i).getCardName());
                upload.add(listsCL.get(i).getSerial());
                upload.add(listsCL.get(i).getMac());
                upload.add(listsCL.get(i).getCodigoSap());
                upload.add(listsCL.get(i).getDescripcion());
                upload.add(listsCL.get(i).getPallet());
                upload.add(listsCL.get(i).getTipologia());
                upload.add(listsCL.get(i).getTipoOrigen());
                upload.add(listsCL.get(i).getOrigen());
                upload.add(listsCL.get(i).getGarantiaFabricante());
                upload.add(listsCL.get(i).getEstado());
                upload.add(listsCL.get(i).getUbicacion());
                upload.add(listsCL.get(i).getCantidadIngreso());
                upload.add(listsCL.get(i).getFechaIngreso());
                upload.add(listsCL.get(i).getEstadoSmartcard());
                rowsCL.add(upload);

            }
            FileWriter fwCL = new FileWriter(this.en.getProperty("descarga.cliente.claro.url") + "\\" + "WIP CLARO NO SMARTCARD.csv");
            fwCL.append("CardCode");
            fwCL.append("\\");
            fwCL.append("CardName");
            fwCL.append("\\");
            fwCL.append("Serial");
            fwCL.append("\\");
            fwCL.append("Mac");
            fwCL.append("\\");
            fwCL.append("CodigoSap");
            fwCL.append("\\");
            fwCL.append("Descripcion");
            fwCL.append("\\");
            fwCL.append("Pallet");
            fwCL.append("\\");
            fwCL.append("Tipologia");
            fwCL.append("\\");
            fwCL.append("TipoOrigen");
            fwCL.append("\\");
            fwCL.append("Origen");
            fwCL.append("\\");
            fwCL.append("GarantiaFabricante");
            fwCL.append("\\");
            fwCL.append("Estado");
            fwCL.append("\\");
            fwCL.append("Ubicacion");
            fwCL.append("\\");
            fwCL.append("CantidadIngreso");
            fwCL.append("\\");
            fwCL.append("FechaIngreso");
            fwCL.append("\\");
            fwCL.append("EstadoSmartcard");
            fwCL.append("\\");
            fwCL.append("\n");

            for (List<String> rowDataCL : rowsCL) {
                fwCL.append(String.join("\\", rowDataCL));
                fwCL.append("\n");
            }
            fwCL.flush();
            fwCL.close();
            claroFont = 1;
        }
        if (claroFont != 0) {
            try {
                CallableStatement cs = cnClaroFon.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "CLARO");
                cs.setString(4, "FON001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsCLF.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsCLF = new ArrayList();
            for (int i = 0; i < listsCLF.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsCLF.get(i).getCardCode());
                upload.add(listsCLF.get(i).getCardName());
                upload.add(listsCLF.get(i).getSerial());
                upload.add(listsCLF.get(i).getMac());
                upload.add(listsCLF.get(i).getCodigoSap());
                upload.add(listsCLF.get(i).getDescripcion());
                upload.add(listsCLF.get(i).getPallet());
                upload.add(listsCLF.get(i).getTipologia());
                upload.add(listsCLF.get(i).getTipoOrigen());
                upload.add(listsCLF.get(i).getOrigen());
                upload.add(listsCLF.get(i).getGarantiaFabricante());
                upload.add(listsCLF.get(i).getEstado());
                upload.add(listsCLF.get(i).getUbicacion());
                upload.add(listsCLF.get(i).getCantidadIngreso());
                upload.add(listsCLF.get(i).getFechaIngreso());
                upload.add(listsCLF.get(i).getEstadoSmartcard());
                rowsCLF.add(upload);

            }
            FileWriter fwCLF = new FileWriter(this.en.getProperty("descarga.cliente.claroFon.url") + "\\" + "WIP CLARO FONTIBON.csv");
            fwCLF.append("CardCode");
            fwCLF.append("\\");
            fwCLF.append("CardName");
            fwCLF.append("\\");
            fwCLF.append("Serial");
            fwCLF.append("\\");
            fwCLF.append("Mac");
            fwCLF.append("\\");
            fwCLF.append("CodigoSap");
            fwCLF.append("\\");
            fwCLF.append("Descripcion");
            fwCLF.append("\\");
            fwCLF.append("Pallet");
            fwCLF.append("\\");
            fwCLF.append("Tipologia");
            fwCLF.append("\\");
            fwCLF.append("TipoOrigen");
            fwCLF.append("\\");
            fwCLF.append("Origen");
            fwCLF.append("\\");
            fwCLF.append("GarantiaFabricante");
            fwCLF.append("\\");
            fwCLF.append("Estado");
            fwCLF.append("\\");
            fwCLF.append("Ubicacion");
            fwCLF.append("\\");
            fwCLF.append("CantidadIngreso");
            fwCLF.append("\\");
            fwCLF.append("FechaIngreso");
            fwCLF.append("\\");
            fwCLF.append("EstadoSmartcard");
            fwCLF.append("\\");
            fwCLF.append("\n");

            for (List<String> rowDataCLF : rowsCLF) {
                fwCLF.append(String.join("\\", rowDataCLF));
                fwCLF.append("\n");
            }
            fwCLF.flush();
            fwCLF.close();
            claroSmartcard = 1;
        }
        if (claroSmartcard != 0) {
            try {
                CallableStatement cs = cnClaroSmart.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "SMARTCARD");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "CLARO");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsCLS.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsCLS = new ArrayList();
            for (int i = 0; i < listsCLS.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsCLS.get(i).getCardCode());
                upload.add(listsCLS.get(i).getCardName());
                upload.add(listsCLS.get(i).getSerial());
                upload.add(listsCLS.get(i).getMac());
                upload.add(listsCLS.get(i).getCodigoSap());
                upload.add(listsCLS.get(i).getDescripcion());
                upload.add(listsCLS.get(i).getPallet());
                upload.add(listsCLS.get(i).getTipologia());
                upload.add(listsCLS.get(i).getTipoOrigen());
                upload.add(listsCLS.get(i).getOrigen());
                upload.add(listsCLS.get(i).getGarantiaFabricante());
                upload.add(listsCLS.get(i).getEstado());
                upload.add(listsCLS.get(i).getUbicacion());
                upload.add(listsCLS.get(i).getCantidadIngreso());
                upload.add(listsCLS.get(i).getFechaIngreso());
                upload.add(listsCLS.get(i).getEstadoSmartcard());
                rowsCLS.add(upload);

            }
            FileWriter fwCLS = new FileWriter(this.en.getProperty("descarga.cliente.claroSmart.url") + "\\" + "WIP CLARO SMARTCARD.csv");
            fwCLS.append("CardCode");
            fwCLS.append("\\");
            fwCLS.append("CardName");
            fwCLS.append("\\");
            fwCLS.append("Serial");
            fwCLS.append("\\");
            fwCLS.append("Mac");
            fwCLS.append("\\");
            fwCLS.append("CodigoSap");
            fwCLS.append("\\");
            fwCLS.append("Descripcion");
            fwCLS.append("\\");
            fwCLS.append("Pallet");
            fwCLS.append("\\");
            fwCLS.append("Tipologia");
            fwCLS.append("\\");
            fwCLS.append("TipoOrigen");
            fwCLS.append("\\");
            fwCLS.append("Origen");
            fwCLS.append("\\");
            fwCLS.append("GarantiaFabricante");
            fwCLS.append("\\");
            fwCLS.append("Estado");
            fwCLS.append("\\");
            fwCLS.append("Ubicacion");
            fwCLS.append("\\");
            fwCLS.append("CantidadIngreso");
            fwCLS.append("\\");
            fwCLS.append("FechaIngreso");
            fwCLS.append("\\");
            fwCLS.append("EstadoSmartcard");
            fwCLS.append("\\");
            fwCLS.append("\n");

            for (List<String> rowDataCLS : rowsCLS) {
                fwCLS.append(String.join("\\", rowDataCLS));
                fwCLS.append("\n");
            }
            fwCLS.flush();
            fwCLS.close();
            radioTech = 1;
        }
        
        if (radioTech != 0) {
            try {
                CallableStatement cs = cnRadioTech.prepareCall("{call wdn.pa_InformeWip(?,?,?,?)}");
                cs.setString(1, "SMARTCARD");
                cs.setString(2, "COLOMBIA");
                cs.setString(3, "CLARO");
                cs.setString(4, "BTA001");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    ReportWipAutomaticModel r = new ReportWipAutomaticModel();
                    r.setCardCode(rs.getString("CardCode"));
                    r.setCardName(rs.getString("CardName"));
                    r.setSerial(rs.getString("Serial"));
                    r.setMac(rs.getString("Mac"));
                    r.setCodigoSap(rs.getString("CodigoSap"));
                    r.setDescripcion(rs.getString("Descripcion"));
                    r.setPallet(rs.getString("Pallet"));
                    r.setTipologia(rs.getString("Tipologia"));
                    r.setTipoOrigen(rs.getString("TipoOrigen"));
                    r.setOrigen(rs.getString("Origen"));
                    r.setGarantiaFabricante(rs.getString("GarantiaFabricante"));
                    r.setEstado(rs.getString("Estado"));
                    r.setUbicacion(rs.getString("Ubicacion"));
                    r.setCantidadIngreso(rs.getString("CantidadIngreso"));
                    r.setFechaIngreso(rs.getString("FechaIngreso"));
                    r.setEstadoSmartcard(rs.getString("EstadoSmartcard"));
                    listsRT.add(r);

                }
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            List<List<String>> rowsRT = new ArrayList();
            for (int i = 0; i < listsRT.size(); i++) {
                List<String> upload = new ArrayList<String>();
                upload.add(listsRT.get(i).getCardCode());
                upload.add(listsRT.get(i).getCardName());
                upload.add(listsRT.get(i).getSerial());
                upload.add(listsRT.get(i).getMac());
                upload.add(listsRT.get(i).getCodigoSap());
                upload.add(listsRT.get(i).getDescripcion());
                upload.add(listsRT.get(i).getPallet());
                upload.add(listsRT.get(i).getTipologia());
                upload.add(listsRT.get(i).getTipoOrigen());
                upload.add(listsRT.get(i).getOrigen());
                upload.add(listsRT.get(i).getGarantiaFabricante());
                upload.add(listsRT.get(i).getEstado());
                upload.add(listsRT.get(i).getUbicacion());
                upload.add(listsRT.get(i).getCantidadIngreso());
                upload.add(listsRT.get(i).getFechaIngreso());
                upload.add(listsRT.get(i).getEstadoSmartcard());
                rowsRT.add(upload);

            }
            FileWriter fwRT = new FileWriter(this.en.getProperty("descarga.cliente.radioTech.url") + "\\" + "WIP RADIO TECH.csv");
            fwRT.append("CardCode");
            fwRT.append("\\");
            fwRT.append("CardName");
            fwRT.append("\\");
            fwRT.append("Serial");
            fwRT.append("\\");
            fwRT.append("Mac");
            fwRT.append("\\");
            fwRT.append("CodigoSap");
            fwRT.append("\\");
            fwRT.append("Descripcion");
            fwRT.append("\\");
            fwRT.append("Pallet");
            fwRT.append("\\");
            fwRT.append("Tipologia");
            fwRT.append("\\");
            fwRT.append("TipoOrigen");
            fwRT.append("\\");
            fwRT.append("Origen");
            fwRT.append("\\");
            fwRT.append("GarantiaFabricante");
            fwRT.append("\\");
            fwRT.append("Estado");
            fwRT.append("\\");
            fwRT.append("Ubicacion");
            fwRT.append("\\");
            fwRT.append("CantidadIngreso");
            fwRT.append("\\");
            fwRT.append("FechaIngreso");
            fwRT.append("\\");
            fwRT.append("EstadoSmartcard");
            fwRT.append("\\");
            fwRT.append("\n");

            for (List<String> rowDataCLS : rowsRT) {
                fwRT.append(String.join("\\", rowDataCLS));
                fwRT.append("\n");
            }
            fwRT.flush();
            fwRT.close();
        }
        
        
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel deleteFiles() {

        File directory1 = new File(this.en.getProperty("descarga.cliente.claro.url"));
        File directory2 = new File(this.en.getProperty("descarga.cliente.claroSmart.url"));
        File directory3 = new File(this.en.getProperty("descarga.cliente.claroFon.url"));
        File directory4 = new File(this.en.getProperty("descarga.cliente.etb.url"));
        File directory5 = new File(this.en.getProperty("descarga.cliente.tigo.url"));
        File directory6 = new File(this.en.getProperty("descarga.cliente.hughes.url"));
        File directory7 = new File(this.en.getProperty("descarga.cliente.red.url"));
        File directory8 = new File(this.en.getProperty("descarga.cliente.plataforma.url"));
        File directory9 = new File(this.en.getProperty("descarga.cliente.medellin.url"));
        File directory10 = new File(this.en.getProperty("descarga.cliente.directv.url"));
        File directory11 = new File(this.en.getProperty("descarga.cliente.directvSmart.url"));
        File directory12 = new File(this.en.getProperty("descarga.cliente.radioTech.url"));
        try {
            if (directory1.isDirectory()) {
                for (File f : directory1.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory2.isDirectory()) {
                for (File f : directory2.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory3.isDirectory()) {
                for (File f : directory3.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory4.isDirectory()) {
                for (File f : directory4.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory5.isDirectory()) {
                for (File f : directory5.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory6.isDirectory()) {
                for (File f : directory6.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory7.isDirectory()) {
                for (File f : directory7.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory8.isDirectory()) {
                for (File f : directory8.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory9.isDirectory()) {
                for (File f : directory9.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory10.isDirectory()) {
                for (File f : directory10.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory11.isDirectory()) {
                for (File f : directory11.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }if (directory12.isDirectory()) {
                for (File f : directory12.listFiles()) {
                    f.exists();
                    f.delete();
                }

            }
            System.out.println("Eliminacion completada");
        } catch (Exception e) {
            
        }
        return null;
    }

}
