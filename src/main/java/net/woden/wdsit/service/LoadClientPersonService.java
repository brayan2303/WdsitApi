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
import javax.mail.Multipart;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.LoadClientPersonEntity;

import net.woden.wdsit.model.ResponseModel;
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
public class LoadClientPersonService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel create(String customer, int customerId, int loadId, MultipartFile file) {
        int inserts = 0;
        int load = 0;

        Connection cn = this.ds.openConnection();

        File directory = new File(this.en.getProperty("carga.personas.url"));
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        File archivo = new File(directory + "\\" + loadId + ".txt");
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            fila = hoja.getRow(0);
            if (fila.getPhysicalNumberOfCells() == 14) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("Material")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("Texto breve de material")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("Número de serie")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("Fabricante número de serie")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("Almacén")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("Lote")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("Lote de stock")) {
                                            if (fila.getCell(7).getStringCellValue().equalsIgnoreCase("Status del sistema")) {
                                                if (fila.getCell(8).getStringCellValue().equalsIgnoreCase("Modificado el")) {
                                                    if (fila.getCell(9).getStringCellValue().equalsIgnoreCase("Modificado por")) {
                                                        if (fila.getCell(10).getStringCellValue().equalsIgnoreCase("Creado por")) {
                                                            if (fila.getCell(11).getStringCellValue().equalsIgnoreCase("Creado el")) {
                                                                if (fila.getCell(12).getStringCellValue().equalsIgnoreCase("Tp.stocks (contab.refer.)")) {
                                                                    if (fila.getCell(13).getStringCellValue().equalsIgnoreCase("Material")) {
                                                                        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                                                                        for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                                                            fila = hoja.getRow(i);
                                                                            for (int j = 0; j < 15; j++) {
                                                                                if (j == 0) {
                                                                                    bw.write("" + customerId);
                                                                                } else {
                                                                                    celda = fila.getCell(j - 1);
                                                                                    if (celda != null) {
                                                                                        String contenido = "";
                                                                                        try {
                                                                                            contenido = celda.getStringCellValue() + "";
                                                                                        } catch (Exception ex) {
                                                                                            contenido = celda.getNumericCellValue() + "";
                                                                                        }
                                                                                        bw.write(contenido);

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
                                                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Material", null, 200);
                                                                    }
                                                                } else {
                                                                    return new ResponseModel(getClass().getSimpleName(), " Falta la columna Tp.stocks (contab.refer.)", null, 200);
                                                                }
                                                            } else {
                                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna Creado el", null, 200);
                                                            }
                                                        } else {
                                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna Creado por", null, 200);
                                                        }
                                                    } else {
                                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Modificado por", null, 200);
                                                    }
                                                } else {
                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Modificado el", null, 200);
                                                }
                                            } else {
                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna Status del sistema", null, 200);
                                            }
                                        } else {
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna Lote de stock", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Lote", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Almacén", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna Fabricante número de serie", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna Número de serie", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Texto breve de material", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Material", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (load == 1) {
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientPersonCreate(?,?)}");
                cs.setInt(1, loadId);
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(2);
                cn.commit();
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
        }
      
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
    
    public ResponseModel list(int customerId) {
        ArrayList<LoadClientPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPersonListAll(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientPersonEntity l = new LoadClientPersonEntity();
                l.setMaterial(rs.getString("material"));
                l.setTextMaterial(rs.getString("textMaterial"));
                l.setNumSerie(rs.getString("numSerie"));
                l.setFactoryNumSerie(rs.getString("factoryNumSerie"));
                l.setWarehouse(rs.getString("warehouse"));
                l.setLot(rs.getString("lot"));
                l.setLotStock(rs.getString("lotStock"));
                l.setStatusSystem(rs.getString("statusSystem"));
                l.setModificationDate(rs.getString("modificationDate"));
                l.setModificationUser(rs.getString("modificationUser"));
                l.setCreation(rs.getString("creation"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setTpStock(rs.getString("tpStock"));
                l.setMaterialTwo(rs.getString("materialTwo"));
                list.add(l);
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

    public ResponseModel delete(int customer) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPersonDelete(?)}");
            cs.setInt(1, customer);
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

    public ResponseModel listCustomer(int customer) {
        ArrayList<LoadClientPersonEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPersonListCustomer(?)}");
            cs.setInt(1, customer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientPersonEntity l = new LoadClientPersonEntity();
                l.setMaterial(rs.getString("material"));
                l.setTextMaterial(rs.getString("textMaterial"));
                l.setNumSerie(rs.getString("numSerie"));
                l.setFactoryNumSerie(rs.getString("factoryNumSerie"));
                l.setWarehouse(rs.getString("warehouse"));
                l.setLot(rs.getString("lot"));
                l.setLotStock(rs.getString("lotStock"));
                l.setStatusSystem(rs.getString("statusSystem"));
                l.setModificationDate(rs.getString("modificationDate"));
                l.setModificationUser(rs.getString("modificationUser"));
                l.setCreation(rs.getString("creation"));
                l.setCreationDate(rs.getString("creationDate"));
                l.setTpStock(rs.getString("tpStock"));
                l.setMaterialTwo(rs.getString("materialTwo"));
                l.setEstadoCodigoSap(rs.getString("estadoCodigoSap"));
                l.setEstadoUbicacion(rs.getString("estadoUbicacion"));
                list.add(l);
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
