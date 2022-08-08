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
import net.woden.wdsit.entity.PqrLanguageEntity;
import net.woden.wdsit.entity.PqrLenguageFormClientEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrLenguageFormClientService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrLenguageFormClientEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, p.getTittlePqr());
            cs.setString(2, p.getParagraphTittle());
            cs.setString(3, p.getNames());
            cs.setString(4, p.getEmail());
            cs.setString(5, p.getEmailAnex());
            cs.setString(6, p.getDetailGeneral());
            cs.setString(7, p.getDocuments());
            cs.setString(8, p.getTitleTable());
            cs.setString(9, p.getColumOne());
            cs.setString(10, p.getColumTwo());
            cs.setString(11, p.getColumTheer());
            cs.setString(12, p.getColumFour());
            cs.setString(13, p.getColumFive());
            cs.setString(14, p.getButtonSend());
            cs.setInt(15, p.getUserId());
            cs.setInt(16, p.getLanguageId());
            cs.registerOutParameter(17, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(17);
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
        ArrayList<PqrLenguageFormClientEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrLenguageFormClientEntity p = new PqrLenguageFormClientEntity();
                p.setId(rs.getInt("id"));
                p.setTittlePqr(rs.getString("tittlePqr"));
                p.setParagraphTittle(rs.getString("paragraphTittle"));
                p.setNames(rs.getString("names"));
                p.setEmail(rs.getString("email"));
                p.setEmailAnex(rs.getString("emailAnex"));
                p.setDetailGeneral(rs.getString("detailGeneral"));
                p.setDocuments(rs.getString("documents"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setColumOne(rs.getString("columOne"));
                p.setColumTwo(rs.getString("columTwo"));
                p.setColumTheer(rs.getString("columTheer"));
                p.setColumFour(rs.getString("columFour"));
                p.setColumFive(rs.getString("columFive"));
                p.setButtonSend(rs.getString("buttonSend"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setUpdateDate(rs.getString("updateDate"));
                p.setUserId(rs.getInt("userId"));
                p.setActive(rs.getBoolean("active"));
                p.setLanguage(rs.getString("language"));
                lists.add(p);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }

    public ResponseModel delete(int id) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientDelete(?)}");
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

    public ResponseModel findById(int id) {
        PqrLenguageFormClientEntity p = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrLenguageFormClientEntity();
                p.setId(rs.getInt("id"));
                p.setTittlePqr(rs.getString("tittlePqr"));
                p.setParagraphTittle(rs.getString("paragraphTittle"));
                p.setNames(rs.getString("names"));
                p.setEmail(rs.getString("email"));
                p.setEmailAnex(rs.getString("emailAnex"));
                p.setDetailGeneral(rs.getString("detailGeneral"));
                p.setDocuments(rs.getString("documents"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setColumOne(rs.getString("columOne"));
                p.setColumTwo(rs.getString("columTwo"));
                p.setColumTheer(rs.getString("columTheer"));
                p.setColumFour(rs.getString("columFour"));
                p.setColumFive(rs.getString("columFive"));
                p.setButtonSend(rs.getString("buttonSend"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setUpdateDate(rs.getString("updateDate"));
                p.setUserId(rs.getInt("userId"));
                p.setActive(rs.getBoolean("active"));
                p.setLanguageId(rs.getInt("languageId"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }

    public ResponseModel update(PqrLenguageFormClientEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getTittlePqr());
            cs.setString(3, p.getParagraphTittle());
            cs.setString(4, p.getNames());
            cs.setString(5, p.getEmail());
            cs.setString(6, p.getEmailAnex());
            cs.setString(7, p.getDetailGeneral());
            cs.setString(8, p.getDocuments());
            cs.setString(9, p.getTitleTable());
            cs.setString(10, p.getColumOne());
            cs.setString(11, p.getColumTwo());
            cs.setString(12, p.getColumTheer());
            cs.setString(13, p.getColumFour());
            cs.setString(14, p.getColumFive());
            cs.setString(15, p.getButtonSend());
            cs.setInt(16, p.getUserId());
            cs.setBoolean(17, p.getActive());
            cs.setInt(18, p.getLanguageId());
            cs.registerOutParameter(19, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(19);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel findAll(int countryId) {
        ArrayList<PqrLanguageEntity> lists = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLanguageFindAll(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrLanguageEntity p = new PqrLanguageEntity();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setActive(rs.getBoolean("active"));
                lists.add(p);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", lists, 200);
    }
    
      public ResponseModel labelFindAll(int userId) {
        PqrLenguageFormClientEntity p = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrLenguageFormClientLanguageId(?)}");
            cs.setInt(1, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrLenguageFormClientEntity();
                p.setId(rs.getInt("id"));
                p.setTittlePqr(rs.getString("tittlePqr"));
                p.setParagraphTittle(rs.getString("paragraphTittle"));
                p.setNames(rs.getString("names"));
                p.setEmail(rs.getString("email"));
                p.setEmailAnex(rs.getString("emailAnex"));
                p.setDetailGeneral(rs.getString("detailGeneral"));
                p.setDocuments(rs.getString("documents"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setTitleTable(rs.getString("titleTable"));
                p.setColumOne(rs.getString("columOne"));
                p.setColumTwo(rs.getString("columTwo"));
                p.setColumTheer(rs.getString("columTheer"));
                p.setColumFour(rs.getString("columFour"));
                p.setColumFive(rs.getString("columFive"));
                p.setButtonSend(rs.getString("buttonSend"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setUpdateDate(rs.getString("updateDate"));
                p.setUserId(rs.getInt("userId"));
                p.setActive(rs.getBoolean("active"));
                p.setLanguageId(rs.getInt("languageId"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }


}
