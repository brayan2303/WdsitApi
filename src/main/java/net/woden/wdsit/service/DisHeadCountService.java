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
import net.woden.wdsit.entity.DisHeadCountEntity;
import net.woden.wdsit.model.DisHeadCountModel;
import net.woden.wdsit.model.ResponseModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DisHeadCountService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(int year, int month, MultipartFile file) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            XSSFWorkbook libro = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFRow fila;
            Cell celda;
            DataFormatter formatter = new DataFormatter();
            File archivo = new File(this.en.getProperty("distribution.headCount.url")+"\\HeadCount.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                fila = hoja.getRow(i);
                bw.write("1");
                bw.write("\t");
                bw.write(String.valueOf(year));
                bw.write("\t");
                bw.write(String.valueOf(month));
                bw.write("\t");
                for (int j = 0; j < fila.getPhysicalNumberOfCells(); j++) {
                    celda = fila.getCell(j);
                    bw.write(formatter.formatCellValue(celda));
                    bw.write("\t");
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisHeadCountCreate(?)}");
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
    public ResponseModel delete(int year,int month) {
        int deletes=0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisHeadCountDelete(?,?)}");
            cs.setInt(1, year);
            cs.setInt(2, month);
            deletes=cs.executeUpdate();
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
    public ResponseModel list() {
        ArrayList<DisHeadCountModel>list=new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisHeadCountList()}");
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                DisHeadCountModel d=new DisHeadCountModel();
                d.setYear(rs.getInt("year"));
                d.setMonth(rs.getInt("month"));
                d.setMonthName(rs.getString("monthName"));
                d.setQuantity(rs.getInt("quantity"));
                list.add(d);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
    public ResponseModel findByYearMonth(int year,int month) {
        ArrayList<DisHeadCountEntity>list=new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_DisHeadCountFindByYearMonth(?,?)}");
            cs.setInt(1, year);
            cs.setInt(2,month);
            ResultSet rs=cs.executeQuery();
            while(rs.next()){
                DisHeadCountEntity d=new DisHeadCountEntity();
                d.setId(rs.getInt("id"));
                d.setYear(rs.getInt("year"));
                d.setMonth(rs.getInt("month"));
                d.setIdentification(rs.getInt("identification"));
                d.setName(rs.getString("name"));
                d.setPosition(rs.getString("position"));
                d.setCity(rs.getString("city"));
                d.setSegmentCode(rs.getString("segmentCode"));
                d.setSegment(rs.getString("segment"));
                d.setCenterCostCode(rs.getString("centerCostCode"));
                d.setCenterCost(rs.getString("centerCost"));
                d.setCustomerCode(rs.getString("customerCode"));
                d.setCustomer(rs.getString("customer"));
                list.add(d);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }
}
