/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.entity.LoadClientRuleGeneralEntity;
import net.woden.wdsit.model.LoadClientRuleGeneralModel;
import net.woden.wdsit.model.LoadRuleGeneralModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientRuleGeneralService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaConnection dsc;

    @Autowired
    private Environment en;

    @Autowired
    private LoadRuleGeneralService lrs;

    @Autowired
    private EncryptUtility eu;

    @Autowired
    private GenPersonService genPersonS;

    public ResponseModel createHughes() throws SQLException, IOException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";
        int inset = 0;
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationHughesSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationHughesCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationHughesEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationHughesTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientHughesListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
                if (!directory1.isDirectory()) {
                    directory1.mkdir();
                }

                File archivo = new File(directory1 + "\\" + "HUGHES" + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionHughes = listSerial.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionHughes = codsapList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionHughes = estadoList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionHughes = tipologiaList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else {

                            }

                            //ListFinal.add(a);
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                        lc.setCustomer(customerName);
                        lc.setSerial(serialFinal);
                        lc.setCodSap(codSapFinal);
                        lc.setStatus(estadoFinal);
                        lc.setTypology(tipologiaFinal);
                        bw.write(0 + "\t" + lc.getCustomer() + "\t" + lc.getSerial() + "\t" + lc.getCodSap() + "\t" + lc.getStatus() + "\t" + lc.getTypology());
                        bw.newLine();
                        listFieldFinal.add(lc);
                        inset = 1;
                    }

                }
                bw.close();

            }
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreateHughes(?)}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(1);
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
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createRedExterna() throws SQLException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationRedExSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationRedExCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationRedExEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationRedExTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientRedExListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionHughes = listSerial.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionHughes = codsapList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionHughes = estadoList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionHughes = tipologiaList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else {

                            }

                            //ListFinal.add(a);
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                        lc.setCustomer(customerName);
                        lc.setSerial(serialFinal);
                        lc.setCodSap(codSapFinal);
                        lc.setStatus(estadoFinal);
                        lc.setTypology(tipologiaFinal);

                        listFieldFinal.add(lc);
                    }

                }

            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createPlataformaMovil() throws SQLException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationPlataformaSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationPlataformaCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationPlataformaEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationPlataformaTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientPlataformaListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionHughes = listSerial.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionHughes = codsapList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionHughes = estadoList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("ID LPN") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("PRODUCTO") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionHughes = tipologiaList.get(j).getMetodo();
                        if (funcionHughes.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionHughes.equals(text)) {
                            if (tipologiaList.get(j).getName().equals("ID LPN") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("PRODUCTO") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else {

                            }

                            //ListFinal.add(a);
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                        lc.setCustomer(customerName);
                        lc.setSerial(serialFinal);
                        lc.setCodSap(codSapFinal);
                        lc.setStatus(estadoFinal);
                        lc.setTypology(tipologiaFinal);

                        listFieldFinal.add(lc);
                    }

                }

            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createEtb() throws SQLException, IOException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";
        int inset = 0;
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationEtbSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationEtbCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationEtbEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationEtbTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientEtbListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
                if (!directory1.isDirectory()) {
                    directory1.mkdir();
                }

                File archivo = new File(directory1 + "\\" + "ETB" + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionEtb = listSerial.get(j).getMetodo();
                        if (funcionEtb.equals(concat)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionEtb = codsapList.get(j).getMetodo();
                        if (funcionEtb.equals(concat)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionEtb = estadoList.get(j).getMetodo();
                        if (funcionEtb.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionEtb = tipologiaList.get(j).getMetodo();
                        if (funcionEtb.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionEtb.equals(text)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else {

                            }

                            //ListFinal.add(a);
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                        lc.setCustomer(customerName);
                        lc.setSerial(serialFinal);
                        lc.setCodSap(codSapFinal);
                        lc.setStatus(estadoFinal);
                        lc.setTypology(tipologiaFinal);
                        bw.write(0 + "\t" + lc.getCustomer() + "\t" + lc.getSerial() + "\t" + lc.getCodSap() + "\t" + lc.getStatus() + "\t" + lc.getTypology());
                        bw.newLine();
                        listFieldFinal.add(lc);
                        inset = 1;

                    }

                }
                bw.close();

            }
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreateEtb(?)}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(1);
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
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createDirectv() throws SQLException, IOException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";
        int inset = 0;
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationDirectvSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationDirectvCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationDirectvEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationDirectvTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientDirectvListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    lc.setAnexx(rs.getString("annexCustomer"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
                if (!directory1.isDirectory()) {
                    directory1.mkdir();
                }

                File archivo = new File(directory1 + "\\" + "DIRECTV" + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String filter = "FILTRAR";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionDirectv = listSerial.get(j).getMetodo();
                        if (funcionDirectv.equals(concat)) {
                            if (listSerial.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else if (funcionDirectv.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else if (funcionDirectv.equals(filter)) {

                            if ("Subinventario".equals(listSerial.get(j).getName()) == true) {
                                serialFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                serialFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                serialFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                serialFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            }

                        } else {
                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionDirectv = codsapList.get(j).getMetodo();
                        if (funcionDirectv.equals(concat)) {
                            if (codsapList.get(j).getName().equals("Número de Item") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("Número de Item") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("Número de Item") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("Número de Item") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(filter)) {

                            if ("Subinventario".equals(listSerial.get(j).getName()) == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de Item") == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            }

                        } else {
                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionDirectv = estadoList.get(j).getMetodo();
                        if (funcionDirectv.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(filter)) {
                            if ("Subinventario".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.filtrar(estadoList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.filtrar(estadoList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                estadoFinal += this.lrs.filtrar(estadoList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de Item") == true) {
                                estadoFinal += this.lrs.filtrar(estadoList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.filtrar(listSerial.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            }

                        } else {
                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionDirectv = tipologiaList.get(j).getMetodo();
                        if (funcionDirectv.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionDirectv.equals(text)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            }

                            //ListFinal.add(a);
                        } else if (funcionDirectv.equals(filter)) {

                            if ("Subinventario".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.filtrar(tipologiaList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.filtrar(tipologiaList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie maestro Deco") == true) {
                                tipologiaFinal += this.lrs.filtrar(tipologiaList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de Item") == true) {
                                tipologiaFinal += this.lrs.filtrar(tipologiaList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.filtrar(tipologiaList.get(j).getResultOne(), listField.get(i).getAnexx(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else {
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();

                        lc.setSerial(serialFinal);
                        String serie = lc.getSerial();
                        if ("".equals(serie)) {
                        } else {
                            lc.setCustomer(customerName);
                            lc.setSerial(serialFinal);
                            lc.setCodSap(codSapFinal);
                            lc.setStatus(estadoFinal);
                            lc.setTypology(tipologiaFinal);
                            bw.write(0 + "\t" + lc.getCustomer() + "\t" + lc.getSerial() + "\t" + lc.getCodSap() + "\t" + lc.getStatus() + "\t" + lc.getTypology());
                            bw.newLine();
                            listFieldFinal.add(lc);
                            inset = 1;
                        }

                    }

                }
                bw.close();

            }
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreateDirectv(?)}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(1);
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
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createClaro() throws SQLException, IOException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";
        int inset = 0;
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationClaroSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationClaroCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationClaroEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationClaroTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientClaroListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    lc.setTypology(rs.getString("annexCustomer"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
                if (!directory1.isDirectory()) {
                    directory1.mkdir();
                }

                File archivo = new File(directory1 + "\\" + "CLARO" + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String searchGeneral = "BUSQUEDA MULTIPLE CONCATENAR";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionClaro = listSerial.get(j).getMetodo();
                        if (funcionClaro.equals(concat)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else if (funcionClaro.equals(searchGeneral)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.busquedasMultiples(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getCodSap(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.busquedasMultiples(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getCodSap(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.busquedasMultiples(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getCodSap(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Lote de stock") == true) {
                                serialFinal += this.lrs.busquedasMultiples(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getCodSap(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }
                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionClaro = codsapList.get(j).getMetodo();
                        if (funcionClaro.equals(concat)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Lote de stock") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Lote de stock") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Lote de stock") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Lote de stock") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionClaro = estadoList.get(j).getMetodo();
                        if (funcionClaro.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Lote de stock") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionClaro = tipologiaList.get(j).getMetodo();
                        if (funcionClaro.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Lote de stock") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Lote de stock") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Lote de stock") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Lote de stock") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionClaro.equals(text)) {
                            if (tipologiaList.get(j).getName().equals("Lote de stock") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else {

                            }

                            //ListFinal.add(a);
                        }
                        LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                        lc.setCustomer(customerName);
                        lc.setSerial(serialFinal);
                        lc.setCodSap(codSapFinal);
                        lc.setStatus(estadoFinal);
                        lc.setTypology(tipologiaFinal);
                        bw.write(0 + "\t" + lc.getCustomer() + "\t" + lc.getSerial() + "\t" + lc.getCodSap() + "\t" + lc.getStatus() + "\t" + lc.getTypology());
                        bw.newLine();
                        listFieldFinal.add(lc);
                        inset = 1;
                    }

                }
                bw.close();

            }
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreateClaro(?)}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(1);
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
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel createTigo() throws SQLException, IOException {
        ArrayList<LoadRuleGeneralModel> listSerial = new ArrayList();
        ArrayList<LoadRuleGeneralModel> codsapList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> estadoList = new ArrayList();
        ArrayList<LoadRuleGeneralModel> tipologiaList = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listField = new ArrayList();
        ArrayList<LoadClientRuleGeneralEntity> listFieldFinal = new ArrayList();

        Connection cn = this.ds.openConnection();
        int load = 0;
        int loadOne = 0;
        int loadTwo = 0;
        int loadTheer = 0;
        String customerName = "";
        int inset = 0;
        int inserts = 0;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationTigoSerial()}");

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                l.setMetodo(rs.getString("metodo"));
                l.setFuncion(rs.getString("funcion"));
                l.setResultOne(rs.getString("resultOne"));
                l.setResultTwo(rs.getString("resultTwo"));
                l.setField(rs.getString("field"));
                l.setName(rs.getString("name"));
                listSerial.add(l);
                loadOne = 1;
            }

            rs.close();
            cs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (loadOne != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationTigoCodSap()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    codsapList.add(l);
                    loadTwo = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTwo != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationTigoEstado()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    estadoList.add(l);
                    loadTheer = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (loadTheer != 0) {

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsParametrizationTigoTipoligia()}");

                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadRuleGeneralModel l = new LoadRuleGeneralModel();
                    l.setMetodo(rs.getString("metodo"));
                    l.setFuncion(rs.getString("funcion"));
                    l.setResultOne(rs.getString("resultOne"));
                    l.setResultTwo(rs.getString("resultTwo"));
                    l.setField(rs.getString("field"));
                    l.setName(rs.getString("name"));
                    tipologiaList.add(l);
                    load = 1;
                }

                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load != 0) {
            this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientTigoListAll()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(rs.getString("customer"));
                    lc.setSerial(rs.getString("serialCustomer"));
                    lc.setCodSap(rs.getString("codSap"));
                    lc.setTypology(rs.getString("annexCustomer"));
                    customerName = lc.getCustomer();
                    listField.add(lc);
                }
                rs.close();
                cs.close();

            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
            if (!listField.isEmpty()) {

                File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
                if (!directory1.isDirectory()) {
                    directory1.mkdir();
                }

                File archivo = new File(directory1 + "\\" + "TIGO" + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

                String concat = "CONCATENAR";
                String replaces = "REEMPLAZAR";
                String concatNumber = "CONCATENAR NUMERO";
                String extrae = "EXTRAER";
                String text = "GENERAR TEXTO";
                String search = "BUSQUEDA";
                String serialFinal = "";
                String codSapFinal = "";
                String estadoFinal = "";
                String tipologiaFinal = "";

                // Recorrido Lista De La tatalba
                for (int i = 0; i < listField.size(); i++) {

                    serialFinal = "";
                    codSapFinal = "";
                    estadoFinal = "";
                    tipologiaFinal = "";
                    //Creacion de un objeto a retornar IQ09 WMS SAP
                    //Enviar a construir el campo SERIAL
                    //Variables= Consulta y ejecutar los metodos
                    //Lista Serial
                    for (int j = 0; j < listSerial.size(); j++) {
                        listSerial.get(j).getFuncion();
                        String funcionTigo = listSerial.get(j).getMetodo();
                        if (funcionTigo.equals(concat)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if ("Almacén".equals(listSerial.get(j).getName()) == true) {
                                serialFinal += this.lrs.concatenar(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(replaces)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if ("Almacén".equals(listSerial.get(j).getName()) == true) {
                                serialFinal += this.lrs.reemplazar(listSerial.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(concatNumber)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if ("Almacén".equals(listSerial.get(j).getName()) == true) {
                                serialFinal += this.lrs.concaternarNumero(listSerial.get(j).getResultOne(), listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(extrae)) {
                            if (listSerial.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getSerial(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getCodSap(), listSerial.get(j).getFuncion());
                            } else if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getStatus(), listSerial.get(j).getFuncion());
                            } else if ("Almacén".equals(listSerial.get(j).getName()) == true) {
                                serialFinal += this.lrs.extraer(listField.get(i).getTypology(), listSerial.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(text)) {
                            if (listSerial.get(j).getName() == null == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                serialFinal += this.lrs.generarTexto(listSerial.get(j).getResultOne(), listSerial.get(j).getFuncion());

                            }

                        } else {

                        }

                    }

                    //Codigo SAP
                    for (int j = 0; j < codsapList.size(); j++) {
                        codsapList.get(j).getFuncion();
                        String funcionTigo = codsapList.get(j).getMetodo();
                        if (funcionTigo.equals(concat)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if ("Almacén".equals(codsapList.get(j).getName()) == true) {
                                codSapFinal += this.lrs.concatenar(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(replaces)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if ("Almacén".equals(codsapList.get(j).getName()) == true) {
                                codSapFinal += this.lrs.reemplazar(codsapList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(concatNumber)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if ("Almacén".equals(codsapList.get(j).getName()) == true) {
                                codSapFinal += this.lrs.concaternarNumero(codsapList.get(j).getResultOne(), listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(extrae)) {
                            if (codsapList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getCodSap(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getSerial(), codsapList.get(j).getFuncion());
                            } else if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getStatus(), codsapList.get(j).getFuncion());
                            } else if ("Almacén".equals(codsapList.get(j).getName()) == true) {
                                codSapFinal += this.lrs.extraer(listField.get(i).getTypology(), codsapList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(text)) {
                            if (codsapList.get(j).getName() == null == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                codSapFinal += this.lrs.generarTexto(codsapList.get(j).getResultOne(), codsapList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Estado
                    for (int j = 0; j < estadoList.size(); j++) {
                        estadoList.get(j).getFuncion();
                        String funcionTigo = estadoList.get(j).getMetodo();
                        if (funcionTigo.equals(concat)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.concatenar(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(replaces)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), estadoList.get(j).getResultTwo(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), listSerial.get(j).getResultTwo(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), codsapList.get(j).getResultTwo(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.reemplazar(estadoList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(concatNumber)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.concaternarNumero(estadoList.get(j).getResultOne(), listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(extrae)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getStatus(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getSerial(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getCodSap(), estadoList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.extraer(listField.get(i).getTypology(), estadoList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(text)) {
                            if (estadoList.get(j).getName() == null == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Número de serie") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if (estadoList.get(j).getName().equals("Material") == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            } else if ("Almacén".equals(estadoList.get(j).getName()) == true) {
                                estadoFinal += this.lrs.generarTexto(estadoList.get(j).getResultOne(), estadoList.get(j).getFuncion());
                            }

                        } else {

                        }

                    }

                    //Tipologia
                    for (int j = 0; j < tipologiaList.size(); j++) {
                        tipologiaList.get(j).getFuncion();
                        String funcionTigo = tipologiaList.get(j).getMetodo();
                        if (funcionTigo.equals(concat)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.concatenar(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(replaces)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.reemplazar(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(concatNumber)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.concaternarNumero(tipologiaList.get(j).getResultOne(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(extrae)) {
                            if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getSerial(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getCodSap(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getStatus(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.extraer(listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            }

                        } else if (funcionTigo.equals(text)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());

                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.generarTexto(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getFuncion());
                            } else {

                            }

                        } else if (funcionTigo.equals(search)) {
                            if (tipologiaList.get(j).getName() == null == true) {
                                tipologiaFinal += this.lrs.busqueda(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Número de serie") == true) {
                                tipologiaFinal += this.lrs.busqueda(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if (tipologiaList.get(j).getName().equals("Material") == true) {
                                tipologiaFinal += this.lrs.busqueda(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else if ("Almacén".equals(tipologiaList.get(j).getName()) == true) {
                                tipologiaFinal += this.lrs.busqueda(tipologiaList.get(j).getResultOne(), tipologiaList.get(j).getResultTwo(), listField.get(i).getTypology(), tipologiaList.get(j).getFuncion());
                            } else {

                            }
                        } else {
                        }

                    }
                    LoadClientRuleGeneralEntity lc = new LoadClientRuleGeneralEntity();
                    lc.setCustomer(customerName);
                    lc.setSerial(serialFinal);
                    lc.setCodSap(codSapFinal);
                    lc.setStatus(estadoFinal);
                    lc.setTypology(tipologiaFinal);
                    bw.write(0 + "\t" + lc.getCustomer() + "\t" + lc.getSerial() + "\t" + lc.getCodSap() + "\t" + lc.getStatus() + "\t" + lc.getTypology());
                    bw.newLine();
                    listFieldFinal.add(lc);
                    inset = 1;

                }
                bw.close();

            }
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreateTigo(?)}");
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(1);
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
            }

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", listFieldFinal, 200);
    }

    public ResponseModel sendEmail(int destinatarioId) {
        GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        int status = 0;
        Properties props = new Properties();
        props.put("mail.smtp.host", this.en.getProperty("spring.mail.host"));
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", this.en.getProperty("spring.mail.port"));
        props.put("mail.smtp.mail.sender", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.user", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getDefaultInstance(props);
        try {
            String messageHtml = "<div style=\"margin: 0; padding: 0;\"><center>\n"
                    + "<table style=\"font-family: Roboto, Helvetica, 'Myriad Pro'; background: #ffffff; margin: 0px; padding: 0px; width: 600px; height: 695px;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td valign=\"top\">\n"
                    + "<div style=\"margin: 0 auto; text-align: left; width: 600px;\" align=\"left\">\n"
                    + "<table style=\"border-collapse: collapse; width: 600px; border-style: none; margin-left: auto; margin-right: auto;\" border=\"0\" cellspacing=\"0px\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"text-align: center; height: 100px; width: 100%; background-color: #ea6109;\"><img style=\"height: 60%; margin-top: 20px;\" src=\"http://app.woden.com.co/wdsit/assets/images/Logo.png\" /></div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 30px;\">\n"
                    + "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 540px; background: #ea6109; border-radius: 0px 0px 21px 21px;\">\n"
                    + "<table style=\"width: 545px; height: 350px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #ffffff; padding: 25px 20px; border-radius: 20px; border: 1px solid #efecec;\">\n"
                    + "<table style=\"height: 298px;\" border=\"0\" width=\"485\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 30px;\">\n"
                    + "<td style=\"text-align: center; font-size: 30px; color: #354a5f; height: 30px;\"><strong>Notificaciones WDSIT</strong></td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 20px;\">\n"
                    + "<td style=\"padding: 10px; text-align: center; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Generacio IQ09</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Hola!! <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Queremos decirte que el archivo IQ09 fue generado </p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px;\">Además de recordarte que estará próximo a ser cargado WMS SAP</p>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td dir=\"ltr\" style=\"padding-top: 40px; text-align: center; color: #4285f4; font-size: 16px; font-weight: 300; height: 21px; width: 425.219px;\"><span style=\"background-color: #ffffff; color: #95a5a6;\">WODEN WDSIT</span></td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table style=\"width: 500px!important;\" border=\"0\" width=\"500\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 10px; font-size: 10px; color: #666666; text-align: left; font-weight: 300;\">&copy; 2020 Woden Colombia S.A. Todos los derechos reservados.</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #eeeeee; padding: .75pt .75pt .75pt .75pt;\">\n"
                    + "<p style=\"margin-right: 0cm; margin-bottom: 13.5pt; margin-left: 0cm; line-height: 12.75pt; text-align: justify;\"><strong><span style=\"font-size: 8.5pt; color: black;\">NOTA DE CONFIDENCIALIDAD</span></strong><span style=\"font-size: 8.5pt; color: black;\">&nbsp;<br />Este mensaje y los archivos anexos, en caso de que existan, contienen informaci&oacute;n de WODEN que es confidencial y para uso exclusivo de la persona o entidad de destino. Debido a que puede contener informaci&oacute;n privilegiada, confidencial o que de alguna manera est&aacute; protegida contra su distribuci&oacute;n no autorizada, agradecemos que si ha recibido este correo electr&oacute;nico por error, notificarlo de manera inmediata al remitente. La Protecci&oacute;n de datos est&aacute; dada para el cumplimiento del Decreto 1581 del 2012. Protege el Medio Ambiente; piensa antes de imprimir este mensaje.<u></u></span></p>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</center></div>";
            messageHtml = messageHtml.replace("{destinatario}", destinatario.getFirstName() + " " + destinatario.getLastName());
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decode(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Generacion de IQ09");
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            status = 1;
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (MessagingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }

    public ResponseModel listRedExterna() throws IOException {
        int inset = 0;
        int inserts = 0;
        ArrayList<LoadClientRuleGeneralModel> lists = new ArrayList();
        Connection cn = this.dsc.openConnection();
        Connection cnc = this.ds.openConnection();
        File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File archivo = new File(directory1 + "\\" + "RED EXTERNA" + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

        try {
            CallableStatement cs = cn.prepareCall("{call wdn.sp_generateIq09RedExterna()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientRuleGeneralModel l = new LoadClientRuleGeneralModel();
                l.setCustomer(rs.getString("customer"));
                l.setSerial(rs.getString("serial"));
                l.setCodSap(rs.getString("codSap"));
                l.setStatus(rs.getString("status"));
                l.setTypology(rs.getString("typology"));
                bw.write(0 + "\t" + l.getCustomer() + "\t" + l.getSerial() + "\t" + l.getCodSap() + "\t" + l.getStatus() + "\t" + l.getTypology());
                bw.newLine();
                lists.add(l);
                inset = 1;
            }
            bw.close();
            if (inset != 0) {
                try {
                    cn.setAutoCommit(false);
                    CallableStatement cst = cnc.prepareCall("{call sp_LoadClientCreateRedExterna(?)}");
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.execute();
                    inserts = cst.getInt(1);
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
            }
            rs.close();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel listPlataformaMovil() throws IOException {
        int inset = 0;
        int inserts = 0;
        ArrayList<LoadClientRuleGeneralModel> lists = new ArrayList();
        Connection cn = this.dsc.openConnection();
        Connection cnc = this.ds.openConnection();
        File directory1 = new File(this.en.getProperty("create.clientes.carga.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File archivo = new File(directory1 + "\\" + "PLATAFORMAMOVIL" + ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        try {
            CallableStatement cs = cn.prepareCall("{call wdn.sp_generateIq09PlataFormaMovil()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientRuleGeneralModel l = new LoadClientRuleGeneralModel();
                l.setCustomer(rs.getString("customer"));
                l.setSerial(rs.getString("serial"));
                l.setCodSap(rs.getString("codSap"));
                l.setStatus(rs.getString("status"));
                l.setTypology(rs.getString("typology"));
                bw.write(0 + "\t" + l.getCustomer() + "\t" + l.getSerial() + "\t" + l.getCodSap() + "\t" + l.getStatus() + "\t" + l.getTypology());
                bw.newLine();
                lists.add(l);
                inset = 1;
            }
            bw.close();
            rs.close();
            cs.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (inset != 0) {
            try {
                cn.setAutoCommit(false);
                CallableStatement cst = cnc.prepareCall("{call sp_LoadClientCreatePlatafor(?)}");
                cst.registerOutParameter(1, Types.INTEGER);
                cst.execute();
                inserts = cst.getInt(1);
                cn.commit();
                cst.close();
                cnc.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            } finally {
                try {
                    cn.close();
                    cnc.close();
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel delete(String customer) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrealertDeleteAuto(?)}");
            cs.setString(1, customer);
            deletes = cs.executeUpdate();
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

    public ResponseModel listGeneral() {
        ArrayList<LoadClientRuleGeneralModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrealertGeneral()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientRuleGeneralModel l = new LoadClientRuleGeneralModel();
                l.setCustomer(rs.getString("customer"));
                l.setSerial(rs.getString("serial"));
                l.setCodSap(rs.getString("codSap"));
                l.setStatus(rs.getString("status"));
                l.setTypology(rs.getString("typology"));
                lists.add(l);
            }

            rs.close();
            cs.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

}
