/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.LoadClientPrealertEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientPrealertService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(String customer, int customerId, int loadId, MultipartFile file) {
        int inserts = 0;
        int load = 0;
        int celdas = 0;
            int columna = 0;
        Connection cn = this.ds.openConnection();
        
        File directory1 = new File(this.en.getProperty("carga.clientes.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File archivo = new File(directory1 + "\\" + loadId + ".txt");
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            fila = hoja.getRow(0);
            
            if (fila.getPhysicalNumberOfCells() == 5) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("CLIENTE")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("SERIAL EQUIPO")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("CODIGO SAP")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("ESTADO")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("TIPOLOGIA")) {
                                    
                                    BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                                    for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                        celdas=i;
                                        fila = hoja.getRow(i);
                                        for (int j = 0; j < 6; j++) {
                                            columna= j;
                                            if (j == 0) {
                                                bw.write(String.valueOf(customerId));
                                            } else {
                                                celda = fila.getCell(j - 1);
                                                if (celda != null) {
                                                    String contenido = "";
                                                    try {
                                                        contenido = celda.getStringCellValue() + "";
                                                    } catch (Exception ex) {
                                                        try {
                                                            contenido = celda.getNumericCellValue() + "";
                                                        } catch (Exception e) {
                                                           return new ResponseModel(getClass().getSimpleName(), "La fila " + (celdas+1) +" NO tiene un formato valido", null, 200);
                                                        }   
                                                    }
                                                    if (j == 1) {
                                                        if (contenido.equals(customer) == true) {
                                                                bw.write(contenido);
                                                        } else {
                                                            return new ResponseModel(getClass().getSimpleName(), "Error, el cliente no corresponde", null, 200);
                                                        }
                                                    } else if (j == 2) {
                                                        bw.write(contenido.replaceAll("[^A-Za-z0-9][-]", ""));
                                                    } else {
                                                        bw.write(contenido);
                                                    }

                                                } else {
                                                    bw.write(" ");
                                                }
                                            }
                                            bw.write("\t");

                                        }
                                        bw.newLine();
                                    }
                                    bw.close();
                                    load = 1;

                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna tipo", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna estado", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna de Cod. Sap", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna serial", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna cliente", null, 200);
                }

            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientCreate(?,?)}");
                cs.setInt(1, loadId);
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
                    archivo.delete();
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel delete(int customer) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientDelete(?)}");
            cs.setInt(1, customer);
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

    public ResponseModel list(int customerId) {
        ArrayList<LoadClientPrealertEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientList(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientPrealertEntity a = new LoadClientPrealertEntity();
                a.setCustomer(rs.getString("customer"));
                a.setCLIENTE(rs.getString("CLIENTE"));
                a.setSERIAL_EQUIPO(rs.getString("SERIAL_EQUIPO"));
                a.setCODIGO_SAP(rs.getString("CODIGO_SAP"));
                a.setESTADO(rs.getString("ESTADO"));
                a.setTIPOLOGIA(rs.getString("TIPOLOGIA"));
                list.add(a);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel listAll() {
        ArrayList<LoadClientPrealertEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientListAll()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientPrealertEntity a = new LoadClientPrealertEntity();
                a.setCustomer(rs.getString("customer"));
                a.setCLIENTE(rs.getString("CLIENTE"));
                a.setSERIAL_EQUIPO(rs.getString("SERIAL_EQUIPO"));
                a.setCODIGO_SAP(rs.getString("CODIGO_SAP"));
                a.setESTADO(rs.getString("ESTADO"));
                a.setTIPOLOGIA(rs.getString("TIPOLOGIA"));
                list.add(a);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

}
