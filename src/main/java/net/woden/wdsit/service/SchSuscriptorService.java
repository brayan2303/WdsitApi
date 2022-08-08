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
import net.woden.wdsit.connection.DataSourceScheduleConnection;
import net.woden.wdsit.entity.SchSuscriptorEntity;
import net.woden.wdsit.model.ResponseModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SchSuscriptorService {

    @Autowired
    private DataSourceScheduleConnection dss;
    @Autowired
    private Environment en;

    public ResponseModel create(int customerId, MultipartFile file) {
        int inserts = 0;
        Connection cn = this.dss.openConnection();
        File archivo;
        BufferedWriter bw=null;
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            archivo = new File(this.en.getProperty("agenda.archivo.url") + "\\SUSCRIPTOR.txt");
            bw = new BufferedWriter(new FileWriter(archivo));
            if (hoja.getRow(0).getPhysicalNumberOfCells() == 9) {
                if (hoja.getRow(0).getCell(0).getStringCellValue().equalsIgnoreCase("CEDULA")) {
                    if (hoja.getRow(0).getCell(1).getStringCellValue().equalsIgnoreCase("NOMBRE SUSCRIPTOR")) {
                        if (hoja.getRow(0).getCell(2).getStringCellValue().equalsIgnoreCase("CORREO")) {
                            if (hoja.getRow(0).getCell(3).getStringCellValue().equalsIgnoreCase("TELEFONO 1")) {
                                if (hoja.getRow(0).getCell(4).getStringCellValue().equalsIgnoreCase("TELEFONO 2")) {
                                    if (hoja.getRow(0).getCell(5).getStringCellValue().equalsIgnoreCase("DIRECCION")) {
                                        if (hoja.getRow(0).getCell(6).getStringCellValue().equalsIgnoreCase("DEPARTAMENTO")) {
                                            if (hoja.getRow(0).getCell(7).getStringCellValue().equalsIgnoreCase("CIUDAD")) {
                                                if (hoja.getRow(0).getCell(8).getStringCellValue().equalsIgnoreCase("Row_id pedido")) {
                                                    for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                                        fila = hoja.getRow(i);
                                                        bw.write("1");
                                                        bw.write("\t");
                                                        bw.write(String.valueOf(customerId));
                                                        bw.write("\t");
                                                        for (int j = 0; j < fila.getPhysicalNumberOfCells(); j++) {
                                                            switch (fila.getCell(j).getCellType()) {
                                                                case HSSFCell.CELL_TYPE_BLANK:
                                                                    bw.write("");
                                                                    bw.write("\t");
                                                                    break;
                                                                case HSSFCell.CELL_TYPE_NUMERIC:
                                                                    bw.write(String.valueOf(fila.getCell(j).getNumericCellValue()));
                                                                    bw.write("\t");
                                                                    break;
                                                                case HSSFCell.CELL_TYPE_STRING:
                                                                    bw.write(fila.getCell(j).getStringCellValue());
                                                                    bw.write("\t");
                                                                    break;
                                                                default:
                                                                    bw.write("");
                                                                    bw.write("\t");
                                                                    break;
                                                            }
                                                        }
                                                        bw.newLine();
                                                    }
                                                    bw.close();
                                                } else {
                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Row_id pedido", null, 200);
                                                }
                                            } else {
                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna ciudad", null, 200);
                                            }
                                        } else {
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna departamento", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna direccion", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna telefono 2", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna telefono 1", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna correo", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna nombre suscriptor", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna cedula", null, 200);
                }
            } else {
                return new ResponseModel(getClass().getSimpleName(), "Formato incorrecto", null, 200);
            }
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }finally{
            try {
                bw.close();
            } catch (IOException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_SuscriptorCreate(?)}");
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
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel list() {
        ArrayList<SchSuscriptorEntity> lista = new ArrayList();
        Connection cn = this.dss.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_SuscriptorList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                SchSuscriptorEntity s = new SchSuscriptorEntity();
                s.setId(rs.getInt("id"));
                s.setClienteId(rs.getInt("clienteId"));
                s.setCliente(rs.getString("cliente"));
                s.setIdentificacion(rs.getString("identificacion"));
                s.setNombre(rs.getString("nombre"));
                s.setCorreo(rs.getString("correo"));
                s.setTelefono1(rs.getString("telefono1"));
                s.setTelefono2(rs.getString("telefono2"));
                s.setDireccion(rs.getString("direccion"));
                s.setDepartamento(rs.getString("departamento"));
                s.setCiudad(rs.getString("ciudad"));
                s.setRowIdPedido(rs.getString("rowIdPedido"));
                lista.add(s);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lista, 200);
    }
}
