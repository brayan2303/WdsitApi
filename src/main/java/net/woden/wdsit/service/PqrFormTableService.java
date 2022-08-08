/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrFormTableEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrFormTableService {
    
    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrFormTableEntity a, int countryId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableCreate(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, a.getTitleTable());
            cs.setString(2, a.getColumnOne());
            cs.setString(3, a.getColumnTwo());
            cs.setString(4, a.getColumnTheer());
            cs.setString(5, a.getColumnFour());
            cs.setString(6, a.getColumnFive());
            cs.setString(7, a.getButtonOne());
            cs.setString(8, a.getButtonTwo());
            cs.setInt(9, a.getLanguageId());
            cs.setInt(10, countryId);
            cs.registerOutParameter(11, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(11);
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

    public ResponseModel list(int countryId) {
        ArrayList<PqrFormTableEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableList(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrFormTableEntity a = new PqrFormTableEntity();
                a.setId(rs.getInt("id"));
                a.setTitleTable(rs.getString("titleTable"));
                a.setColumnOne(rs.getString("columnOne"));
                a.setColumnTwo(rs.getString("columnTwo"));
                a.setColumnTheer(rs.getString("columnTheer"));
                a.setColumnFour(rs.getString("columnFour"));
                a.setColumnFive(rs.getString("columnFive"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setButtonTwo(rs.getString("buttonTwo"));
                a.setCretionDate(rs.getString("cretionDate"));
                a.setLanguage(rs.getString("language"));
                a.setActive(rs.getBoolean("active"));
                list.add(a);
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

        public ResponseModel delete(int id) {//eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableDelete(?)}");
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


    public ResponseModel update(PqrFormTableEntity a) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableUpdate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getTitleTable());
            cs.setString(3, a.getColumnOne());
            cs.setString(4, a.getColumnTwo());
            cs.setString(5, a.getColumnTheer());
            cs.setString(6, a.getColumnFour());
            cs.setString(7, a.getColumnFive());
            cs.setString(8, a.getButtonOne());
            cs.setString(9, a.getButtonTwo());
            cs.setInt(10, a.getLanguageId());
            cs.setBoolean(11, a.getActive());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(12);
            cs.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel findById(int id) {
        PqrFormTableEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrFormTableEntity();
              a.setId(rs.getInt("id"));
                a.setTitleTable(rs.getString("titleTable"));
                a.setColumnOne(rs.getString("columnOne"));
                a.setColumnTwo(rs.getString("columnTwo"));
                a.setColumnTheer(rs.getString("columnTheer"));
                a.setColumnFour(rs.getString("columnFour"));
                a.setColumnFive(rs.getString("columnFive"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setButtonTwo(rs.getString("buttonTwo"));
                a.setCretionDate(rs.getString("cretionDate"));
                a.setLanguageId(rs.getInt("languageId"));
                a.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Registro no encontrado" : "OK", a, 200);
    }
    
     public ResponseModel findByUserId(int id) {
        PqrFormTableEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormTableListPerson(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrFormTableEntity();
              a.setId(rs.getInt("id"));
                a.setTitleTable(rs.getString("titleTable"));
                a.setColumnOne(rs.getString("columnOne"));
                a.setColumnTwo(rs.getString("columnTwo"));
                a.setColumnTheer(rs.getString("columnTheer"));
                a.setColumnFour(rs.getString("columnFour"));
                a.setColumnFive(rs.getString("columnFive"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setButtonTwo(rs.getString("buttonTwo"));
                a.setCretionDate(rs.getString("cretionDate"));
                a.setLanguageId(rs.getInt("languageId"));
                a.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), a == null ? "Por favor asignar un lenguaje" : "OK", a, 200);
    }
}
