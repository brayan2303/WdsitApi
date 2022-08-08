/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.LoadClientParameterizationEntity;
import net.woden.wdsit.model.LoadClientParameterizationModel;
import net.woden.wdsit.model.ResponseModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class LoadClientParameterizationService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(int id, int customerId) {
        String celda = null;
        int pas = 0;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCountryParameterization(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setCodigo(rs.getString("codigo"));
                list.add(l);
                celda = l.getCodigo();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(celda);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        Celda1 = cell1;
                        w.setCodOne(Celda1.getStringCellValue());

                        try {
                            String contenido = w.getCodOne();
                            if (contenido != null) {
                                if (x != (total - 1)) {
                                    bw.write(customerId + "\t" + contenido + "\t" + contenido);
                                    bw.newLine();
                                } else {
                                    bw.write(customerId + "\t" + contenido + "\t" + contenido);
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();
                archivo.delete();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createClaro(int id, int customerId) {
        String serialCustomer = null;
        String annexedCustomer = null;
        String codSap = null;
        String prefix = null;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        int pas = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsClaro()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexedCustomer(rs.getString("annexedCustomer"));
                l.setCodSap(rs.getString("codSap"));

                serialCustomer = l.getSerialCustomer();
                annexedCustomer = l.getAnnexedCustomer();
                codSap = l.getCodSap();

                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                XSSFCell Celda3 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }

                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(codSap);
                int idxForColumn3 = mapNameColumns.get(annexedCustomer);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        XSSFCell cell3 = (XSSFCell) dataRow.getCell(idxForColumn3);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        Celda3 = cell3;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());
                        w.setCodethree(Celda3.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String annexd = w.getCodTwo();
                            String codsap = w.getCodethree();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    }
                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createTigo(int id, int customerId) {
        String celda = null;
        int pas = 0;
        String serialCustomer = null;
        String annexedCustomer = null;
        String prefix = null;
        String codSap = null;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsTigo()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexedCustomer(rs.getString("annexedCustomer"));
                l.setCodSap(rs.getString("codSap"));

                serialCustomer = l.getSerialCustomer();
                annexedCustomer = l.getAnnexedCustomer();
                codSap = l.getCodSap();

                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                XSSFCell Celda3 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(annexedCustomer);
                int idxForColumn3 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        XSSFCell cell3 = (XSSFCell) dataRow.getCell(idxForColumn3);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        Celda3 = cell3;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());
                        w.setCodethree(Celda3.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String annexd = w.getCodTwo();
                            String codsap = w.getCodethree();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    }
                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();

            } catch (Exception e) {

                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {

            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createEtb(int id, int customerId) {
        String celda = null;
        String serialCustomer = null;
        String prefix = null;
        String codSap = null;
        int pas = 0;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsGeneral(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setCodSap(rs.getString("codSap"));

                serialCustomer = l.getSerialCustomer();
                codSap = l.getCodSap();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String codsap = w.getCodTwo();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    }

                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            String directorio = this.en.getProperty("load.clientes.url") + "\\" + customerId;
            File f = new File(directorio);
            f.delete();

            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createDirectv(int id, int customerId) {
        String celda = null;
        int pas = 0;
        String serialCustomer = null;
        String annexedCustomer = null;
        String prefix = null;
        String codSap = null;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsDirectv()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexedCustomer(rs.getString("annexedCustomer"));
                l.setCodSap(rs.getString("codSap"));

                serialCustomer = l.getSerialCustomer();
                annexedCustomer = l.getAnnexedCustomer();
                codSap = l.getCodSap();

                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                XSSFCell Celda3 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(annexedCustomer);
                int idxForColumn3 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        XSSFCell cell3 = (XSSFCell) dataRow.getCell(idxForColumn3);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        Celda3 = cell3;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());
                        w.setCodethree(Celda3.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String annexd = w.getCodTwo();
                            String codsap = w.getCodethree();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                        bw.newLine();
                                    }
                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + annexd + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();

            } catch (Exception e) {

                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {

            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createHughes(int id, int customerId) {
        String celda = null;
        String serialCustomer = null;
        String prefix = null;
        String codSap = null;
        int pas = 0;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsGeneral(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setCodSap(rs.getString("codSap"));

                serialCustomer = l.getSerialCustomer();
                codSap = l.getCodSap();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String codsap = w.getCodTwo();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    }

                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            System.out.println("hola mundo3");
            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createPlataformaMovil(int id, int customerId) {
        String celda = null;
        String serialCustomer = null;
        String prefix = null;
        String codSap = null;
        int pas = 0;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsGeneral(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setCodSap(rs.getString("codSap"));
                serialCustomer = l.getSerialCustomer();
                codSap = l.getCodSap();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String codsap = w.getCodTwo();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    }

                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            System.out.println("hola mundo3");
            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel createRedExterna(int id, int customerId) {
        String celda = null;
        String serialCustomer = null;
        String prefix = null;
        String codSap = null;
        int pas = 0;
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientFieldsGeneral(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setCodSap(rs.getString("codSap"));
                serialCustomer = l.getSerialCustomer();
                codSap = l.getCodSap();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPrefixCustomer(?)}");
            cs.setInt(1, customerId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setPrefix(rs.getString("prefix"));
                prefix = l.getPrefix();
                pas = 1;
            }
            cs.close();
            rs.close();

        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        if (pas != 0) {
            File directory1 = new File(this.en.getProperty("create.clientes.url"));
            if (!directory1.isDirectory()) {
                directory1.mkdir();
            }

            try {
                File archivo = new File(directory1 + "\\" + customerId + ".txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                this.ds.openConnection();
                XSSFCell Celda1 = null;
                XSSFCell Celda2 = null;
                Workbook workbook = WorkbookFactory.create(new FileInputStream(this.en.getProperty("load.clientes.url") + "\\" + customerId + "\\" + customerId + ".xlsx"));
                Map<String, Integer> mapNameColumns = new HashMap<>();
                XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
                int total = sheet.getPhysicalNumberOfRows();
                Row row = sheet.getRow(0);
                short minColIx = row.getFirstCellNum();
                short maxColIx = row.getLastCellNum();

                for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                    Cell cell = row.getCell(colIx);
                    mapNameColumns.put(cell.getStringCellValue(), cell.getColumnIndex());
                }
                int idxForColumn1 = mapNameColumns.get(serialCustomer);
                int idxForColumn2 = mapNameColumns.get(codSap);

                for (int x = 1; x <= (total - 1); x++) {
                    try {
                        LoadClientParameterizationEntity w = new LoadClientParameterizationEntity();
                        Row dataRow = sheet.getRow(x);
                        XSSFCell cell1 = (XSSFCell) dataRow.getCell(idxForColumn1);
                        XSSFCell cell2 = (XSSFCell) dataRow.getCell(idxForColumn2);
                        Celda1 = cell1;
                        Celda2 = cell2;
                        w.setCodOne(Celda1.getStringCellValue());
                        w.setCodTwo(Celda2.getStringCellValue());

                        try {
                            String serial = w.getCodOne();
                            String codsap = w.getCodTwo();
                            if (serial != null) {
                                if (x != (total - 1)) {
                                    if (prefix != null) {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    } else {
                                        bw.write(customerId + "\t" + serial + "\t" + codsap);
                                        bw.newLine();
                                    }

                                } else {
                                    bw.write(customerId + "\t" + serial + "\t" + codsap);
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    } catch (Exception e) {

                    }
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", "PASO", 200);
        } else {
            System.out.println("hola mundo3");
            return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
        }

    }

    public ResponseModel loadFile(int customerId, int id, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("load.clientes.url") + "\\" + customerId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        String nameFile = "";
        String extFile = "";
        String nameSend = "";
        String status = "";

        for (MultipartFile file : files) {
            nameFile = file.getOriginalFilename().split("\\.", 2)[0];
            extFile = file.getOriginalFilename().split("\\.", 2)[1];
            nameSend = (nameFile + "." + extFile);
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                    status = "OK";
                } catch (IOException ex) {
                    load = 1;
                    status = ex.getMessage();
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    File a = new File(directory1 + "\\" + customerId + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                    f.renameTo(a);
                    status = "OK";
                } catch (IOException ex) {
                    status = ex.getMessage();  
                    load = 1;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    
                }
            }
        }
        if (load != 0) {
            int inserts = 0;
            Connection cn = this.ds.openConnection();

            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadClientLogBulkInsertCreate(?,?,?,?,?)}");
                cs.setInt(1, id);
                cs.setString(2, nameSend);
                cs.setString(3, status);
                cs.setInt(4, customerId);
                cs.registerOutParameter(5, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(5);
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
        } else {
            return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
        }

    }
    
    
        public ResponseModel loadFileLog(int customerId, int id, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("carga.clientes.url") + "\\" + customerId);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("carga.clientes.url") + "\\" + customerId + "\\" + id);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        File directory3 = new File(this.en.getProperty("carga.clientes.url") + "\\" + customerId + "\\" + id + "\\" + creationDate);
        if (!directory3.isDirectory()) {
            directory3.mkdir();
        }
     

        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 1;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) { 
                    load = 1;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    
                }
            }   
        }
            return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }


    public ResponseModel createLogIq(int userId, String status) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientLogGenerateIQCreate(?,?,?)}");
            cs.setInt(1, userId);
            cs.setString(2, status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(3);
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

    public ResponseModel createLogDoc(int userId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientLogDocCreate(?,?)}");
            cs.setInt(1, userId);
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

    public ResponseModel load(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomersCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadClaro(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientClaroCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadTigo(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientTigoCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadEtb(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientEtbCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadDirectv(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientDirectvCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadHughes(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientHughesCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadPlataformaMovil(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPlataformaMovilCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel loadRedExterna(int customerId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRedExternaCreate(?,?)}");
            cs.setInt(1, customerId);
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

    public ResponseModel findByClaro() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerClaro()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByTigo() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerTigo()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByEtb() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerEtb()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByDirectv() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerDirectv()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByHughes() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerHughes()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByPlatafomaMovil() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerPlataFormaMovil()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel findByRedExterna() {
        GenCustomerEntity g = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientCustomerRedExterna()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                g = new GenCustomerEntity();
                g.setId(rs.getInt("id"));
                g.setCode(rs.getString("code"));
                g.setDescription(rs.getString("description"));
                g.setIncomeActive(rs.getBoolean("incomeActive"));
                g.setActive(rs.getBoolean("active"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", g, 200);
    }

    public ResponseModel claroCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientClaroCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel tigoCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientTigoCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel etbCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientEtbCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel directvCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientDirecTvCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel plataformaMovilCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClienPlataformaMovilCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel redExternaCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClienPlataRedExternaCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel hughesCount() {
        LoadClientParameterizationModel l = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientHughesCount()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                l = new LoadClientParameterizationModel();
                l.setTotal(rs.getInt("total"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", l, 200);
    }

    public ResponseModel deleteClaro(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientClaroDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deleteTigo(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientTigoDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deleteEtb(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientEtbDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deleteDirectv(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientDirectvDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deletePlataformaMovil(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPlataformaMovilDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deleteRedExterna(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRedExternaDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel deleteHughes(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientHughesDelete(?)}");
            cs.setInt(1, id);
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

    public ResponseModel claroList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientClaroList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexCustomer(rs.getString("annexCustomer"));
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

    public ResponseModel tigoList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientTigoList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexCustomer(rs.getString("annexCustomer"));
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

    public ResponseModel etbList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientEtbList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
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

    public ResponseModel directvList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientDirectvList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
                l.setAnnexCustomer(rs.getString("annexCustomer"));
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

    public ResponseModel plataformaMovilList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientPlataformaMovilList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
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

    public ResponseModel redExternaList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClientRedExternaList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
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

    public ResponseModel hughesList() {
        ArrayList<LoadClientParameterizationEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_LoadClienHughesList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                LoadClientParameterizationEntity l = new LoadClientParameterizationEntity();
                l.setSerialCustomer(rs.getString("serialCustomer"));
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

    public ResponseModel deleteFiles(int customerId) {
        int deletes = 0;
        File directoryLoad = new File(this.en.getProperty("load.clientes.url") + "\\" + customerId);
        File create = new File(this.en.getProperty("create.clientes.url") + "\\" + customerId + ".txt");
        if (directoryLoad.isDirectory()) {
            File directory1 = new File(this.en.getProperty("load.clientes.url") + "\\" + customerId);
            if (directory1.isDirectory()) {
                for (File f : directory1.listFiles()) {
                    f.delete();
                }
                directory1.delete();
            }
            if (create.delete()) {
                System.err.println("Archivo eliminado");
            } else {
                System.err.println("Error al eliminar");
            }

        } else {
            deletes = 1;
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

}
