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
import java.util.Random;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceScheduleConnection;
import net.woden.wdsit.entity.SchClientBaseEntity;
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
public class SchClientBaseService {
    
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private DataSourceScheduleConnection dsc;
    @Autowired
    private Environment en;


    public ResponseModel create(int customerId, MultipartFile file) {
         int load = 0;
         int cargaId = (int) Math.floor(Math.random()*(100-10+1)+10);
         File directory = new File(this.en.getProperty("agendamiento.base.url"));
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        File archivo = new File(directory + "\\" + cargaId + ".txt");
       try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            fila = hoja.getRow(0);
            
            if (fila.getPhysicalNumberOfCells() == 9) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("IDENTIFICACION")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("NOMBRE COMPLETO")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("EMAIL")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("TELEFONO")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("CELULAR")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("DIRECCION")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("DEPARTAMENTO")) {
                                            if (fila.getCell(7).getStringCellValue().equalsIgnoreCase("PROVINCIA")) {
                                                if (fila.getCell(8).getStringCellValue().equalsIgnoreCase("DISTRITO")) {
                                                    BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                                                    for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                                        try{
                                                        fila = hoja.getRow(i);
                                                        for (int j = 0; j < 11; j++) {
                                                            if (j == 0 || j == 1) {
                                                                bw.write("" + customerId);
                                                            } else {
                                                                celda = fila.getCell(j - 2);
                                                                if (celda != null) {
                                                                    String contenido = "";
                                                                    try {
                                                                        contenido = celda.getStringCellValue() + "";
                                                                    } catch (Exception ex) {
                                                                        contenido = celda.getNumericCellValue() + "";
                                                                    }
                                                                    bw.write(contenido);

                                                                } else {
                                                                    bw.write("0");
                                                                }
                                                            }
                                                            if(j != 10)
                                                            {
                                                                bw.write("\t");
                                                            }
                                                        }
                                                        bw.newLine();
                                                        } catch(Exception ex){
                                                             return new ResponseModel(getClass().getSimpleName(),ex.getMessage(), null, 200);
                                                        }
                                                    }
                                                    bw.close();
                                                    load=1;
                                                }else {    
                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna DISTRITO", null, 200);
                                                }
                                            }else {    
                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna PROVINCIA", null, 200);
                                            }
                                        } else {
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna DEPARAMENTO", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna DIRECCION", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna CELULAR", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna TELEFONO", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna de EMAIL", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna NOMBRE COMPLETO", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna IDENTIFICACION", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
       if (load == 1) {
           int inserts = 0;
           Connection cn = this.dsc.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_SchClientBaseCreate(?,?)}");
                cs.setInt(1, cargaId);
                cs.registerOutParameter(2, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(2);
                cn.commit();
                cs.close();
                cn.close();
            } catch (SQLException ex) {
                archivo.delete();
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
       return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }
    
    public ResponseModel createHistory(int customerId, int userId) {
        Connection cn = this.ds.openConnection();
        int inserts = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_SchChargeHistoryCreate(?,?,?)}");
            cs.setInt(1, customerId);
            cs.setInt(2, userId);
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
    
    public ResponseModel list(int customerId) {
        ArrayList<SchClientBaseEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_SchClientBaseListAll(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                SchClientBaseEntity l = new SchClientBaseEntity();
                l.setCustomer(rs.getString("customer"));
                l.setIdentification(rs.getInt("identification"));
                l.setNameComplete(rs.getString("nameComplete"));
                l.setEmail(rs.getString("email"));
                l.setTelephone(rs.getString("telephone"));
                l.setCelphone(rs.getString("celphone"));
                l.setAddress(rs.getString("address"));
                l.setDepartament(rs.getString("department"));
                l.setProvince(rs.getString("province"));
                l.setDistrict(rs.getString("district"));
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
    
    public ResponseModel delete(int customerId) {
        Connection cn = this.ds.openConnection();
        int deletes = 0;
        try {

            CallableStatement cs = cn.prepareCall("{call sp_SchClientBaseDelete(?)}");
            cs.setInt(1, customerId);
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
    
    public ResponseModel searchByIdentification(int identification) {
        SchClientBaseEntity b = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_SchClientBaseFindByIdentification(?)}");
            cs.setInt(1, identification);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                b = new SchClientBaseEntity();
                b.setIdentification(rs.getInt("identification"));
                b.setNameComplete(rs.getString("nameComplete"));
                b.setEmail(rs.getString("email"));
                b.setTelephone(rs.getString("telephone"));
                b.setCelphone(rs.getString("celphone"));
                b.setAddress(rs.getString("address"));
                b.setDepartament(rs.getString("department"));
                b.setProvince(rs.getString("province"));
                b.setDistrict(rs.getString("district"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }
}
