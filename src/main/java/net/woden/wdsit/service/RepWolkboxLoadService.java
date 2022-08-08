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
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.RepWolkboxLoadEntity;
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
public class RepWolkboxLoadService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(int loadId, MultipartFile file) {
        int inserts = 0;
        int load = 0;
        int celdas = 0;
        int columna = 0;

        Connection cn = this.ds.openConnection();

        File directory1 = new File(this.en.getProperty("carga.wolkbox.url"));
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

            if (fila.getPhysicalNumberOfCells() == 10) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("ID CALL")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("AGENT NAME")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("ID CUSTOMER")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("DATE")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("DESCRIPTION COD1")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("CALL COMMENTS")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("TYPE OF CALL")) {
                                            if (fila.getCell(7).getStringCellValue().equalsIgnoreCase("TELEPHONE")) {
                                                if (fila.getCell(8).getStringCellValue().equalsIgnoreCase("DURATION(SEG)")) {
                                                    if (fila.getCell(9).getStringCellValue().equalsIgnoreCase("ID CAMPAING")) {
                                                        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                                                        for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                                            fila = hoja.getRow(i);
                                                            for (int j = 0; j < 11; j++) {
                                                                celda = fila.getCell(j);
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
                                                                if (j != 10) {
                                                                    bw.write("\t");
                                                                }
                                                            }
                                                            bw.newLine();
                                                        }
                                                        bw.close();
                                                        load = 1;
                                                    } else {
                                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna ID CAMPAING", null, 200);
                                                    }
                                                } else {
                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna DURATION(SEG)", null, 200);
                                                }
                                            } else {
                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna TELEPHONE", null, 200);
                                            }
                                        } else {
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna TYPE OF CALL", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna CALL COMMENTS", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna DESCRIPTION COD1", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna DATE", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna ID CUSTOMER", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna AGENT NAME", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna ID CALL", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato de archivo invalido", null, 200);
            }

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        System.out.println(load);
        if (load == 1) {
            try {
                CallableStatement cs = cn.prepareCall("{call sp_RepWolkboxLoadCreate(?,?)}");
                cs.setInt(1, loadId);
                System.out.println(loadId);
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
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel list() {
        ArrayList<RepWolkboxLoadEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_RepWolkboxLoadList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                RepWolkboxLoadEntity r = new RepWolkboxLoadEntity();
                r.setIdCall(rs.getString("idCall"));
                r.setAgentName(rs.getString("agentName"));
                r.setId(rs.getString("id"));
                r.setDate(rs.getString("date"));
                r.setDescription(rs.getString("description"));
                r.setComments(rs.getString("comments"));
                r.setTypeCall(rs.getString("typeCall"));
                r.setTelephone(rs.getString("telephone"));
                r.setDuration(rs.getString("duration"));
                r.setIdCampaing(rs.getString("idCampaing"));
                list.add(r);
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
        return new ResponseModel(getClass().getCanonicalName(), "OK", list, 200);
    }
}
