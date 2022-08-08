package net.woden.wdsit.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.entity.GenCountryAbbreviationEntity;
import net.woden.wdsit.entity.ReportValidateLoadEntity;
import net.woden.wdsit.entity.ReportValidateLoadFindEntity;
import net.woden.wdsit.model.LoadValidateDepartamentModel;
import net.woden.wdsit.model.ReportLoadValidateSearchCodeSapModel;
import net.woden.wdsit.model.ReportValidateLoadLogModel;
import net.woden.wdsit.model.ReportValidateLoadNameArchiveModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ReportValidateModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author f.casallas
 */
@Service
public class ReportValidateLoadService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private DataSourceSapColombiaConnection dsc;

    int column1, column2, column3, column4, column5, column6, column7, column8 = 0;
    String Columna1, Columna2, Columna3, Columna4, Columna5, Columna6, Columna7, Columna8 = "";

    SimpleDateFormat dtf = new SimpleDateFormat("yyyyMMdd");
    Calendar calendar = Calendar.getInstance();
    Date dateObj = calendar.getTime();
    String date = dtf.format(dateObj);

    String carpetaArchivo = "";
    FileInputStream fis;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Row row;
    int countryId = 0;
    String nameArchive = "";

    public ResponseModel loadFile(int idCountry, int userId, String typeFiles, MultipartFile[] files) {
        Random rand = new Random();
        int numberRandom = 0;
        numberRandom = rand.nextInt(1000);
        int load = 0;
        BufferedImage bi;
        String carpeta = "" + idCountry + userId + date + numberRandom;

        countryId = idCountry;

        File directory1 = new File(this.en.getProperty("reportValidateFile.url") + "\\" + carpeta);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory1 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    nameArchive = f.getName();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
            if (load == 1) {
                int inserts = 0;
                Connection cn = this.ds.openConnection();
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadLogCreate(?,?,?,?,?,?)}");
                    cs.setString(1, carpeta);
                    cs.setInt(2, userId);
                    cs.setInt(3, idCountry);
                    cs.setString(4, date);
                    cs.setString(5, nameArchive);
                    cs.registerOutParameter(6, Types.INTEGER);
                    cs.execute();
                    inserts = cs.getInt(6);
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
                load = 0;
                return new ResponseModel(getClass().getSimpleName(), null, null, 200);

            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", carpeta, 200);
    }

    public ResponseModel validateFile(int userId, int idCountry, String typeFiles) {

        if (carpetaArchivo == null) {
            return new ResponseModel(getClass().getSimpleName(), "Error carpeta vacia", null, 200);
        } else {
            ReportValidateLoadFindEntity rv = null;
            Connection cns = this.ds.openConnection();
            try {
                cns.setAutoCommit(false);
                CallableStatement cs = cns.prepareCall("{call sp_ReportValidateFindArchive(?,?)}");
                cs.setInt(1, userId);
                cs.setInt(2, idCountry);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    rv = new ReportValidateLoadFindEntity();
                    rv.setCodeLoad(rs.getString("codeLoad"));
                    carpetaArchivo = rs.getString("codeLoad");
                }
                cns.commit();
                rs.close();
                cs.close();
                cns.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), "OK", rv, 200);
            }

            File directory1 = new File(this.en.getProperty("reportValidateFile.url") + "\\" + carpetaArchivo);

            if (directory1.isDirectory()) {
                for (File f : directory1.listFiles()) {
                    try {
                        ReportValidateModel l = new ReportValidateModel();
                        l.setName(f.getName());
                        fis = new FileInputStream(directory1 + "\\" + f.getName());
                        // Validación extensión del Archivo.
                        String ext = "";
                        int i = f.getName().lastIndexOf('.');
                        if (i > 0) {
                            ext = f.getName().substring(i + 1);
                        }
                        if ("xlsx".equals(typeFiles)) {
                            ArrayList<ReportValidateLoadEntity> list = new ArrayList();
                            Connection cn = this.ds.openConnection();
                            try {
                                CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadList(?)}");
                                cs.setInt(1, idCountry);
                                ResultSet rs = cs.executeQuery();
                                while (rs.next()) {
                                    ReportValidateLoadEntity r = new ReportValidateLoadEntity();
                                    r.setModelCode(rs.getString("modelCode"));
                                    r.setModelDescription(rs.getString("modelDescription"));
                                    r.setDxType(rs.getString("dxType"));
                                    r.setSerialEquipo(rs.getString("serialEquipo"));
                                    r.setAddress(rs.getString("address"));
                                    r.setRegion(rs.getString("region"));
                                    r.setCity(rs.getString("city"));
                                    r.setDepartment(rs.getString("department"));
                                    Columna1 = rs.getString("modelCode");
                                    Columna2 = rs.getString("modelDescription");
                                    Columna3 = rs.getString("dxType");
                                    Columna4 = rs.getString("serialEquipo");
                                    Columna5 = rs.getString("address");
                                    Columna6 = rs.getString("region");
                                    Columna7 = rs.getString("city");
                                    Columna8 = rs.getString("department");
                                }

                                workbook = new XSSFWorkbook(fis);
                                sheet = workbook.getSheetAt(0);
                                row = sheet.getRow(0);
                                boolean status1 = false;
                                boolean status2 = false;
                                boolean status3 = false;
                                boolean status4 = false;
                                boolean status5 = false;
                                boolean status6 = false;
                                boolean status7 = false;
                                boolean status8 = false;
                                for (Cell cell : row) {

                                    if (cell.getStringCellValue() == null ? Columna1 == null : cell.getStringCellValue().equals(Columna1)) {
                                        column1 = cell.getColumnIndex();
                                        status1 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna2 == null : cell.getStringCellValue().equals(Columna2)) {
                                        column2 = cell.getColumnIndex();
                                        status2 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna3 == null : cell.getStringCellValue().equals(Columna3)) {
                                        column3 = cell.getColumnIndex();
                                        status3 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna4 == null : cell.getStringCellValue().equals(Columna4)) {
                                        column4 = cell.getColumnIndex();
                                        status4 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna5 == null : cell.getStringCellValue().equals(Columna5)) {
                                        column5 = cell.getColumnIndex();
                                        status5 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna6 == null : cell.getStringCellValue().equals(Columna6)) {
                                        column6 = cell.getColumnIndex();
                                        status6 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna7 == null : cell.getStringCellValue().equals(Columna7)) {
                                        column7 = cell.getColumnIndex();
                                        status7 = true;
                                    }
                                    if (cell.getStringCellValue() == null ? Columna8 == null : cell.getStringCellValue().equals(Columna8)) {
                                        column8 = cell.getColumnIndex();
                                        status8 = true;
                                    }
                                }
                                if (status1 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();
                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna1, 200);
                                } else if (status2 == false) {

                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna2, 200);
                                } else if (status3 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna3, 200);
                                } else if (status4 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna4, 200);
                                } else if (status5 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna5, 200);
                                } else if (status6 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna6, 200);
                                } else if (status7 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();
                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna7, 200);
                                } else if (status8 == false) {
                                    fis.close();
                                    f.delete();
                                    directory1.delete();

                                    return new ResponseModel(getClass().getSimpleName(), "Error", "Falta y/o está mal escrita la Columna : " + Columna8, 200);
                                }

                                fis.close();
                                cs.close();
                                rs.close();
                                cn.close();

                            } catch (Exception e) {
                                return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
                            }

                        }
                        if ("csv".equals(typeFiles)) {
                            FileReader file = null;
                            CSVReader csvReader = null;
                            file = new FileReader(directory1 + "\\" + f.getName());
                            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
                            csvReader = new CSVReaderBuilder(file).withCSVParser(conPuntoYComa).build();
                            String[] fila = null;
                            while ((fila = csvReader.readNext()) != null) {
                                System.out.println(Arrays.toString(fila));

                            }
                            fis.close();
                            file.close();
                            csvReader.close();
                            f.delete();
                        }
                        if ("txt".equals(ext)) {
                        }
                    } catch (CsvValidationException | IOException e) {
                        return new ResponseModel(getClass().getSimpleName(), e.getMessage(), null, 200);
                    }
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", null, 200);
    }

    public ResponseModel ReportValidateFindCountry(int countryId) {
        ArrayList<ReportValidateLoadFindEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ReportValidateFindCountry(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReportValidateLoadFindEntity r = new ReportValidateLoadFindEntity();
                r.setCodeLoad(rs.getString("codeLoad"));
                r.setUserName(rs.getString("userName"));
                r.setNameCountry(rs.getString("nameCountry"));
                r.setCreationDate(rs.getString("creationDate"));
                list.add(r);
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

    public ResponseModel ReportValidateLoadArchive(int userId) throws IOException {
        String codeSap = "";
        Connection cn = this.ds.openConnection();
        String code = "";
        File document = new File(this.en.getProperty("reportValidateLoadFile.url") + "\\" + carpetaArchivo + userId + countryId + ".txt");
        if (document.isFile() == false) {
            FileWriter fw = new FileWriter(document);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Row file : sheet) {

            }

            for (Row file : sheet) {
                code = " ";
                Cell celdaSearch = file.getCell(column6);
                LoadValidateDepartamentModel l = null;
                try {
                    CallableStatement cs = cn.prepareCall("{call sp_LoadValidateDepartamentFindByAbbreviation(?)}");
                    cs.setString(1, celdaSearch + "");
                    ResultSet rs = cs.executeQuery();
                    while (rs.next()) {
                        l = new LoadValidateDepartamentModel();
                        l.setCode(rs.getString("code"));
                        code = l.getCode();
                    }
                } catch (Exception e) {
                }

                String celda = "";

                Cell c1 = file.getCell(column1);
                Cell c2 = file.getCell(column2);
                Cell c3 = file.getCell(column3);
                Cell c4 = file.getCell(column4);
                Cell c5 = file.getCell(column5);
                Cell c6 = file.getCell(column6);
                Cell c7 = file.getCell(column7);
                Cell c8 = file.getCell(column8);
                DataFormatter dataFormatter = new DataFormatter();
                String value1 = dataFormatter.formatCellValue(c1);
                String value2 = dataFormatter.formatCellValue(c2);
                String value3 = dataFormatter.formatCellValue(c3);
                String value4 = dataFormatter.formatCellValue(c4);
                String value5 = dataFormatter.formatCellValue(c5);
                String value6 = dataFormatter.formatCellValue(c6);
                String value7 = dataFormatter.formatCellValue(c7);
                String value8 = dataFormatter.formatCellValue(c8);
                if (code == null) {
                    celda = carpetaArchivo + "\t"
                            + userId + "\t"
                            + countryId + "\t"
                            + value1 + "\t"
                            + value2 + "\t"
                            + value3 + "\t"
                            + value4 + "\t"
                            + value5 + "\t"
                            + value6 + "\t"
                            + value7 + "\t"
                            + value8;
                    bw.write(celda);
                    bw.newLine();
                } else {
                    celda = carpetaArchivo + "\t"
                            + userId + "\t"
                            + countryId + "\t"
                            + value1 + "\t"
                            + value2 + "\t"
                            + value3 + "\t"
                            + value4 + "\t"
                            + value5 + "\t"
                            + code + "\t"
                            + value7 + "\t"
                            + value8;
                    bw.write(celda);
                    bw.newLine();
                }

            }
            bw.close();
            int inserts = 0;
            int loadData = 0;
            Connection cnn = this.ds.openConnection();
            try {
                CallableStatement cst = cnn.prepareCall("{call sp_ReportValidateLoadFileCreate(?,?,?,?)}");
                cst.setString(1, carpetaArchivo);
                cst.setInt(2, userId);
                cst.setInt(3, countryId);
                System.out.println(carpetaArchivo+userId+countryId);
                cst.registerOutParameter(4, Types.INTEGER);
                cst.execute();
                inserts = cst.getInt(4);
                loadData = 1;
                cst.close();
                cnn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            } finally {
                try {
                    cnn.close();
                    fis.close();
                } catch (SQLException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
                if (loadData == 1) {
                    ArrayList<ReportValidateLoadLogModel> list = new ArrayList();
                    Connection cnnd = this.ds.openConnection();
                    int dateS = 0;
                    try {
                        cn.setAutoCommit(false);
                        CallableStatement cs = cnnd.prepareCall("{call sp_ReportLoadValidateFindSearchSap(?,?)}");
                        cs.setInt(1, countryId);
                        cs.setInt(2, userId);
                        ResultSet rs = cs.executeQuery();
                        while (rs.next()) {
                            ReportValidateLoadLogModel r = new ReportValidateLoadLogModel();
                            r.setModelCode(rs.getString("validateSAP"));
                            list.add(r);
                            dateS = 1;
                        }
                        rs.close();
                        cs.close();
                    } catch (SQLException ex) {
                        return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                    }

                    return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
                }
            }
            return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), null, null, 200);
    }

    public ResponseModel ReportValidateLoadDownload(int userId, String codeLoad) {
        ArrayList<ReportValidateLoadLogModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadDownload(?,?)}");
            cs.setInt(1, userId);
            cs.setString(2, codeLoad);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReportValidateLoadLogModel r = new ReportValidateLoadLogModel();
                r.setModelCode(rs.getString("modelCode"));
                r.setModelDescription(rs.getString("modelDescription"));
                r.setDxType(rs.getString("dxType"));
                r.setSerialEquipo(rs.getString("serialEquipo"));
                r.setAddress(rs.getString("address"));
                r.setRegion(rs.getString("region"));
                r.setCity(rs.getString("city"));
                r.setDepartment(rs.getString("department"));
                list.add(r);

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

    public ResponseModel ReportValidateLoadDeleteArchive(int userId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadDelete(?,?)}");
            cs.setInt(1, userId);
            cs.setString(2, carpetaArchivo);
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
        File directory1 = new File(this.en.getProperty("reportValidateFile.url") + "\\" + carpetaArchivo);
        if (directory1.exists() && directory1.isDirectory()) {
            File f;
            for (String archivo : directory1.list()) {
                f = new File(directory1 + File.separator + archivo);
                if (f.isDirectory()) {
                    f.delete();
                    f.deleteOnExit();
                } else { //Es un archivo
                    f.delete();
                    f.deleteOnExit();
                }
            }
            directory1.delete();
            directory1.deleteOnExit();
        }
        File directory2 = new File(this.en.getProperty("reportValidateLoadFile.url") + "\\" + carpetaArchivo + userId + ".txt");
        if (directory2.isFile()) {
            directory2.delete();
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }

    public ResponseModel ReportValidateNameArchive(int userId) {
        ReportValidateLoadNameArchiveModel r = null;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ReportValidateNameArchive(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                r = new ReportValidateLoadNameArchiveModel();
                r.setNumberCountry(rs.getInt("numberCountry"));
                r.setAbreviatureCountry(rs.getString("abreviatureCountry"));
                r.setDateCreation(rs.getString("dateCreation"));
                r.setNameArchive(rs.getString("nameArchive"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", r, 200);

    }

    public ResponseModel listAll(String country) throws IOException {

        ArrayList<ReportValidateLoadLogModel> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        String abbreviation = "";
        String date = "";
        int load = 0;
        int inset = 0;
        int maxId = 0;
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_ReportValidateLoadDownloadCountry(?)}");
            cs.setString(1, country);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ReportValidateLoadLogModel r = new ReportValidateLoadLogModel();
                r.setModelCode(rs.getString("modelCode"));
                r.setModelDescription(rs.getString("modelDescription"));
                r.setDxType(rs.getString("dxType"));
                r.setSerialEquipo(rs.getString("serialEquipo"));
                r.setAddress(rs.getString("address"));
                r.setRegion(rs.getString("region"));
                r.setCity(rs.getString("city"));
                r.setDepartment(rs.getString("department"));
                lists.add(r);
                load = 1;

            }
            cn.commit();
            rs.close();
            cs.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        if (load != 0) {
            GenCountryAbbreviationEntity r = null;
            this.ds.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_GenCountryAbbreviationFindByCountry(?)}");
                cs.setString(1, country);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    r = new GenCountryAbbreviationEntity();
                    r.setId(rs.getInt("id"));
                    r.setAbbreviation(rs.getString("abbreviation"));
                    abbreviation = r.getAbbreviation();
                    inset = 1;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                    date = dtf.format(LocalDateTime.now());
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        if (inset != 0) {
            GenCountryAbbreviationEntity r = null;
            this.ds.openConnection();
            try {
                CallableStatement cs = cn.prepareCall("{call sp_LoadValidateReportLogIdMax()}");
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    r = new GenCountryAbbreviationEntity();
                    r.setId(rs.getInt("id"));
                    maxId = r.getId();

                }
            } catch (SQLException ex) {
                ex.getMessage();
            }

        }

            List<List<String>> rows = new ArrayList();
        for (int i = 0; i < lists.size(); i++) {
            List<String> upload = new ArrayList<String>();
            upload.add(lists.get(i).getModelCode());
            upload.add(lists.get(i).getModelDescription());
            upload.add(lists.get(i).getDxType());
            upload.add(lists.get(i).getSerialEquipo());
            upload.add(lists.get(i).getAddress());
            upload.add(lists.get(i).getRegion());
            upload.add(lists.get(i).getCity());
            upload.add(lists.get(i).getDepartment());
            rows.add(upload);

        }

        FileWriter csvWriter = new FileWriter(this.en.getProperty("milicom.four.url") + "\\" + "id" + maxId + "_" + abbreviation + "Reverse_CPERAvailable_"+ date +".csv");
        csvWriter.append("MODEL_CODE");
        csvWriter.append("|");
        csvWriter.append("MODEL_DESCRIPTION");
        csvWriter.append("|");
        csvWriter.append("DX_TYPE");
        csvWriter.append("|");
        csvWriter.append("SERIAL 1");
        csvWriter.append("|");
        csvWriter.append("ADDRESS");
        csvWriter.append("|");
        csvWriter.append("REGION");
        csvWriter.append("|");
        csvWriter.append("CITY");
        csvWriter.append("|");
        csvWriter.append("DEPARTAMENT");
        csvWriter.append("|");
        csvWriter.append("\n");

        for (List<String> rowData : rows) {
            csvWriter.append(String.join("|", rowData));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);

    }
}
