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
import net.woden.wdsit.entity.WlsPrealertSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class WlsPrealertSerialService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(String customer, int prealertId, MultipartFile file) {
        int inserts = 0;
        int load = 0;
        Connection cn = this.ds.openConnection();
        File directory1 = new File(this.en.getProperty("wls.logistica.prealertas.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(directory1 + "\\" + customer);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            fila = hoja.getRow(0);
            if (fila.getPhysicalNumberOfCells() == 15) {
                if (fila.getCell(0).getStringCellValue().equalsIgnoreCase("serial")) {
                    if (fila.getCell(1).getStringCellValue().equalsIgnoreCase("mac")) {
                        if (fila.getCell(2).getStringCellValue().equalsIgnoreCase("codigoSap")) {
                            if (fila.getCell(3).getStringCellValue().equalsIgnoreCase("descripcion")) {
                                if (fila.getCell(4).getStringCellValue().equalsIgnoreCase("cantidad")) {
                                    if (fila.getCell(5).getStringCellValue().equalsIgnoreCase("falla")) {
                                        if (fila.getCell(6).getStringCellValue().equalsIgnoreCase("origen")) {
                                            if (fila.getCell(7).getStringCellValue().equalsIgnoreCase("pedido")) {
                                                if (fila.getCell(8).getStringCellValue().equalsIgnoreCase("tramite")) {
                                                    if (fila.getCell(9).getStringCellValue().equalsIgnoreCase("novedad")) {
                                                        if (fila.getCell(10).getStringCellValue().equalsIgnoreCase("garantia")) {
                                                            if (fila.getCell(11).getStringCellValue().equalsIgnoreCase("tipoOrigen")) {
                                                                if (fila.getCell(12).getStringCellValue().equalsIgnoreCase("guia")) {
                                                                    if (fila.getCell(13).getStringCellValue().equalsIgnoreCase("seq")) {
                                                                        if (fila.getCell(14).getStringCellValue().equalsIgnoreCase("ssid")) {
                                                                            File archivo = new File(directory2 + "\\" + prealertId + ".txt");
                                                                            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                                                                            for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                                                                                fila = hoja.getRow(i);
                                                                                bw.write(String.valueOf(prealertId));
                                                                                bw.write("\t");
                                                                                for (int j = 0; j < 15; j++) {
                                                                                    celda = fila.getCell(j);
                                                                                    if(celda!=null){
                                                                                        bw.write(celda.getStringCellValue().equals("")?"0":celda.getStringCellValue());
                                                                                    bw.write("\t");
                                                                                    }else{
                                                                                        bw.write("");
                                                                                        bw.write("\t");
                                                                                    }                                                                                    
                                                                                }
                                                                                bw.newLine();
                                                                            }
                                                                            bw.close();
                                                                            load = 1;
                                                                        } else {
                                                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna SSID", null, 200);
                                                                        }
                                                                    } else {
                                                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna SQE", null, 200);
                                                                    }
                                                                } else {
                                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Guia", null, 200);
                                                                }
                                                            } else {
                                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna TipoOrigen", null, 200);
                                                            }
                                                        } else {
                                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna Garantia", null, 200);
                                                        }
                                                    } else {
                                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Novedad", null, 200);
                                                    }
                                                } else {
                                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Tramite", null, 200);
                                                }
                                            } else {
                                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna Pedido", null, 200);
                                            }
                                        } else {
                                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna Origen", null, 200);
                                        }
                                    } else {
                                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Falla", null, 200);
                                    }
                                } else {
                                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Cantidad", null, 200);
                                }
                            } else {
                                return new ResponseModel(getClass().getSimpleName(), "Falta la columna Descripcion", null, 200);
                            }
                        } else {
                            return new ResponseModel(getClass().getSimpleName(), "Falta la columna CodigoSap", null, 200);
                        }
                    } else {
                        return new ResponseModel(getClass().getSimpleName(), "Falta la columna Mac", null, 200);
                    }
                } else {
                    return new ResponseModel(getClass().getSimpleName(), "Falta la columna Serial", null, 200);
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
                CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertSerialCreate(?,?,?)}");
                cs.setString(1, customer);
                cs.setInt(2, prealertId);
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
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }
    public ResponseModel delete(int prealertId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertSerialDelete(?)}");
            cs.setInt(1, prealertId);
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
    public ResponseModel list(int prealertId) {
        ArrayList<WlsPrealertSerialEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_WlsPrealertSerialList(?)}");
            cs.setInt(1, prealertId);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                WlsPrealertSerialEntity w=new WlsPrealertSerialEntity();
                w.setSerial(rs.getString("serial"));
                w.setMac(rs.getString("mac"));
                w.setSapCode(rs.getString("sapCode"));
                w.setDescription(rs.getString("description"));
                w.setQuantity(rs.getInt("quantity"));
                w.setFailure(rs.getString("failure"));
                w.setOrigin(rs.getString("origin"));
                w.setOrder(rs.getString("order"));
                w.setProcedure(rs.getString("procedure"));
                w.setNovelty(rs.getString("novelty"));
                w.setWarranty(rs.getString("warranty"));
                w.setOriginType(rs.getString("originType"));
                w.setGuide(rs.getString("guide"));
                w.setSeq(rs.getString("seq"));
                w.setSsid(rs.getString("ssid"));
                list.add(w);
            }
            cn.commit();
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
