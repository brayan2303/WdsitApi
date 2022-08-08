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
import net.woden.wdsit.entity.PqrPageClientInitEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrPageClientInitService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrPageClientInitEntity a, int countryId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, a.getNameLastName());
            cs.setString(2, a.getNameLastNameFunction());
            cs.setString(3, a.getEmail());
            cs.setString(4, a.getEmailFunction());
            cs.setString(5, a.getEmailAnex());
            cs.setString(6, a.getEmailAnexFuntion());
            cs.setString(7, a.getDetailGeneral());
            cs.setString(8, a.getDetailGeneralFunction());
            cs.setString(9, a.getDocumentsAnex());
            cs.setString(10, a.getDocumentsAnexFuntion());
            cs.setString(11, a.getTitlePrincipal());
            cs.setString(12, a.getParagraphTitle());
            cs.setInt(13, a.getLanguageId());
            cs.setInt(14, countryId);
            cs.registerOutParameter(15, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(15);
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
        ArrayList<PqrPageClientInitEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitList(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPageClientInitEntity a = new PqrPageClientInitEntity();
                a.setId(rs.getInt("id"));
                a.setNameLastName(rs.getString("nameLastName"));
                a.setNameLastNameFunction(rs.getString("nameLastNameFunction"));
                a.setEmail(rs.getString("email"));
                a.setEmailFunction(rs.getString("emailFunction"));
                a.setEmailAnex(rs.getString("emailAnex"));
                a.setEmailAnexFuntion(rs.getString("emailAnexFuntion"));
                a.setDetailGeneral(rs.getString("detailGeneral"));
                a.setDetailGeneralFunction(rs.getString("detailGeneralFunction"));
                a.setDocumentsAnex(rs.getString("documentsAnex"));
                a.setDocumentsAnexFuntion(rs.getString("documentsAnexFuntion"));
                a.setTitlePrincipal(rs.getString("titlePrincipal"));
                a.setParagraphTitle(rs.getString("paragraphTitle"));
                a.setLanguageId(rs.getInt("languageId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUpdateDate(rs.getString("updateDate"));
                a.setActive(rs.getBoolean("active"));
                a.setLanguage(rs.getString("language"));
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitDelete(?)}");
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

    public ResponseModel update(PqrPageClientInitEntity a) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getNameLastName());
            cs.setString(3, a.getNameLastNameFunction());
            cs.setString(4, a.getEmail());
            cs.setString(5, a.getEmailFunction());
            cs.setString(6, a.getEmailAnex());
            cs.setString(7, a.getEmailAnexFuntion());
            cs.setString(8, a.getDetailGeneral());
            cs.setString(9, a.getDetailGeneralFunction());
            cs.setString(10, a.getDocumentsAnex());
            cs.setString(11, a.getDocumentsAnexFuntion());
            cs.setString(12, a.getTitlePrincipal());
            cs.setString(13, a.getParagraphTitle());
            cs.setInt(14, a.getLanguageId());
            cs.setBoolean(15, a.isActive());
            cs.registerOutParameter(16, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(16);
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
        PqrPageClientInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrPageClientInitEntity();
                a.setId(rs.getInt("id"));
                a.setNameLastName(rs.getString("nameLastName"));
                a.setNameLastNameFunction(rs.getString("nameLastNameFunction"));
                a.setEmail(rs.getString("email"));
                a.setEmailFunction(rs.getString("emailFunction"));
                a.setEmailAnex(rs.getString("emailAnex"));
                a.setEmailAnexFuntion(rs.getString("emailAnexFuntion"));
                a.setDetailGeneral(rs.getString("detailGeneral"));
                a.setDetailGeneralFunction(rs.getString("detailGeneralFunction"));
                a.setDocumentsAnex(rs.getString("documentsAnex"));
                a.setDocumentsAnexFuntion(rs.getString("documentsAnexFuntion"));
                a.setTitlePrincipal(rs.getString("titlePrincipal"));
                a.setParagraphTitle(rs.getString("paragraphTitle"));
                a.setLanguageId(rs.getInt("languageId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUpdateDate(rs.getString("updateDate"));
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
        PqrPageClientInitEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPageClientInitListPerson(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrPageClientInitEntity();
                a.setId(rs.getInt("id"));
                a.setNameLastName(rs.getString("nameLastName"));
                a.setNameLastNameFunction(rs.getString("nameLastNameFunction"));
                a.setEmail(rs.getString("email"));
                a.setEmailFunction(rs.getString("emailFunction"));
                a.setEmailAnex(rs.getString("emailAnex"));
                a.setEmailAnexFuntion(rs.getString("emailAnexFuntion"));
                a.setDetailGeneral(rs.getString("detailGeneral"));
                a.setDetailGeneralFunction(rs.getString("detailGeneralFunction"));
                a.setDocumentsAnex(rs.getString("documentsAnex"));
                a.setDocumentsAnexFuntion(rs.getString("documentsAnexFuntion"));
                a.setTitlePrincipal(rs.getString("titlePrincipal"));
                a.setParagraphTitle(rs.getString("paragraphTitle"));
                a.setLanguageId(rs.getInt("languageId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUpdateDate(rs.getString("updateDate"));
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
