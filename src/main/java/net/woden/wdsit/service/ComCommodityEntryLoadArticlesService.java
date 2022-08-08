/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ComCommodityEntryLoadArticlesEntity;
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
 * @author m.pulido
 */
@Service
public class ComCommodityEntryLoadArticlesService {
    
    @Autowired
    private DataSourceConnection ds;
    
    @Autowired
    private Environment en;
    
    public ResponseModel validate(int loadId, MultipartFile file) {
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            fila = hoja.getRow(0);
            
            if (fila.getPhysicalNumberOfCells() == 7) {
                    if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("PEDIDO")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("TRAMITE")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("CODIGO_ACCESORIO")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("NOMBRE_ACCESORIO")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("CANTIDAD")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("FECHA_RECOLECCION")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("FECHA_ASIGNACION")) {
                                            File directory = new File(this.en.getProperty("accesorios.previous.url")+"\\"+loadId);
                                            if(!directory.isDirectory()){
                                                directory.mkdir();
                                            }
                                            if (directory.isDirectory()) {
                                                String nombreArchivo = file.getOriginalFilename();
                                                
                                                file.transferTo(new File(this.en.getProperty("accesorios.previous.url")+"\\"+loadId+"\\"+nombreArchivo));
                                                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
                                            } else {
                                                return new ResponseModel(getClass().getSimpleName(), "NO", "Erroe en la carpeta por favor hablar con el administrador de la plataforma", 200);
                                            }
                                        } else{
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna FECHA_ASIGNACION", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna FECHA_RECOLECCION", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna CANTIDAD", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna de NOMBRE_ACCESORIO", null, 200);
                            }
                        } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna de CODIGO_ACCESORIO", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna TRAMITE", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna PEDIDO", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
    }
    
    public ResponseModel charge(int loadId, int userId) {
        
        File directory = new File(this.en.getProperty("accesorios.previous.url")+"\\"+ loadId);
        String encontrado="";
        int load = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for (File f : directory.listFiles()) {
            encontrado = f.getAbsolutePath();
        }
        
        try {
            File archivo = new File(this.en.getProperty("accesorios.charge.url") + "\\" + loadId + ".txt");
            InputStream file = new FileInputStream(encontrado);
            XSSFWorkbook libro = new XSSFWorkbook(file);
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            fila = hoja.getRow(0);
            
            if (fila.getPhysicalNumberOfCells() == 7) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("PEDIDO")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("TRAMITE")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("CODIGO_ACCESORIO")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("NOMBRE_ACCESORIO")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("CANTIDAD")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("FECHA_RECOLECCION")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("FECHA_ASIGNACION")) {
                                            
                                            //Definicion Variables
                                            BufferedWriter bw = new BufferedWriter( new FileWriter(archivo) );
                                            int celdas = 0;
                                            
                                            for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                            celdas=i;
                                            fila = hoja.getRow(i);
                                            
                                            for(int q = 0; q < 2; q++){
                                                bw.write(String.valueOf(loadId));
                                                bw.write("\t");
                                            }
                                            
                                            for (int j = 0; j < 7; j++) {
                                                celda = fila.getCell(j);
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
                                                    bw.write(contenido);
                                                } else {
                                                    bw.write(" ");
                                                }
                                                
                                                bw.write("\t");
                                            }
                                            
                                            for(int x = 0; x < 3; x++){
                                                if(x == 0){
                                                    bw.write(String.valueOf(userId));
                                                    bw.write("\t");
                                                }else if(x == 1){
                                                    bw.write(String.valueOf(dtf.format(LocalDateTime.now())));
                                                    bw.write("\t");
                                                }else if(x == 2){
                                                    bw.write(String.valueOf(1));
                                                }
                                            }
                                            bw.newLine();
                                        }
                                        bw.close();
                                        load = 1;
                                        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
                                        } else{
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna FECHA_ASIGNACION", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna FECHA_RECOLECCION", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna CANTIDAD", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna de NOMBRE_ACCESORIO", null, 200);
                            }
                        } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna de CODIGO_ACCESORIO", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna TRAMITE", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna PEDIDO", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
    }
    
    
    public ResponseModel chargeLoad(int loadId) {
        int inserts = 0; 
       Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryLoadArticlesCreateMass(?,?)}");
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
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
    
    public ResponseModel listByEntryId(int entryId) {
        ArrayList<ComCommodityEntryLoadArticlesEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ComCommodityEntryLoadArticlesByEntryId(?)}");
            cs.setInt(1, entryId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                ComCommodityEntryLoadArticlesEntity b=new ComCommodityEntryLoadArticlesEntity();
                b.setId(rs.getInt("id"));
                b.setIdCommodityEntry(rs.getInt("idCommodityEntry"));
                b.setIdCommodityEntryNumber(rs.getString("idCommodityEntryNumber"));
                b.setOrders(rs.getString("orders"));
                b.setTransact(rs.getString("transact"));
                b.setCodeAccesory(rs.getString("codeAccesory"));
                b.setNameAccesory(rs.getString("nameAccesory"));
                b.setQuantity(rs.getInt("quantity"));
                b.setRecolectionDate(rs.getString("recolectionDate"));
                b.setAssignDate(rs.getString("assignDate"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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
