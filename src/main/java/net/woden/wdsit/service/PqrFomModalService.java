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
import net.woden.wdsit.entity.PqrFomModalEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrFomModalService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(PqrFomModalEntity a, int countryId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFomModalCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, a.getTitleModal());
            cs.setString(2, a.getParagraphTitleModal());
            cs.setString(3, a.getIdentification());
            cs.setString(4, a.getIdentificationFunction());
            cs.setString(5, a.getSerial());
            cs.setString(6, a.getSerialFunction());
            cs.setString(7, a.getImei());
            cs.setString(8, a.getImeiFunction());
            cs.setString(9, a.getMovil());
            cs.setString(10, a.getMovilFunction());
            cs.setString(11, a.getDescription());
            cs.setString(12, a.getDescriptionFunction());
            cs.setString(13, a.getFilesAttachments());
            cs.setString(14, a.getFilesAttachmentsFuntion());
            cs.setString(15, a.getFilesAttachmentsButton());
            cs.setString(16, a.getButtonOne());
            cs.setInt(17, a.getLanguegeId());
            cs.setInt(18, countryId);
            cs.registerOutParameter(19, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(19);
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
        ArrayList<PqrFomModalEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormModalList(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrFomModalEntity a = new PqrFomModalEntity();
                a.setId(rs.getInt("id"));
                a.setTitleModal(rs.getString("titleModal"));
                a.setParagraphTitleModal(rs.getString("paragraphTitleModal"));
                a.setIdentification(rs.getString("identification"));
                a.setIdentificationFunction(rs.getString("identificationFunction"));
                a.setSerial(rs.getString("serial"));
                a.setSerialFunction(rs.getString("serialFunction"));
                a.setImei(rs.getString("imei"));
                a.setImeiFunction(rs.getString("imeiFunction"));
                a.setMovil(rs.getString("movil"));
                a.setMovilFunction(rs.getString("movilFunction"));
                a.setDescription(rs.getString("description"));
                a.setDescriptionFunction(rs.getString("descriptionFunction"));
                a.setFilesAttachments(rs.getString("filesAttachments"));
                a.setFilesAttachmentsFuntion(rs.getString("filesAttachmentsFuntion"));
                a.setFilesAttachmentsButton(rs.getString("filesAttachmentsButton"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setLanguegeId(rs.getInt("languegeId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUpdateDate(rs.getString("updateDate"));
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormModalDelete(?)}");
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

    public ResponseModel update(PqrFomModalEntity a) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrFomModalUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getTitleModal());
            cs.setString(3, a.getParagraphTitleModal());
            cs.setString(4, a.getIdentification());
            cs.setString(5, a.getIdentificationFunction());
            cs.setString(6, a.getSerial());
            cs.setString(7, a.getSerialFunction());
            cs.setString(8, a.getImei());
            cs.setString(9, a.getImeiFunction());
            cs.setString(10, a.getMovil());
            cs.setString(11, a.getMovilFunction());
            cs.setString(12, a.getDescription());
            cs.setString(13, a.getDescriptionFunction());
            cs.setString(14, a.getFilesAttachments());
            cs.setString(15, a.getFilesAttachmentsFuntion());
            cs.setString(16, a.getFilesAttachmentsButton());
            cs.setString(17, a.getButtonOne());
            cs.setInt(18, a.getLanguegeId());
            cs.setBoolean(19, a.getActive());
            cs.registerOutParameter(20, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(20);
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
        PqrFomModalEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrFormModalFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrFomModalEntity();
                a.setId(rs.getInt("id"));
                a.setId(rs.getInt("id"));
                a.setTitleModal(rs.getString("titleModal"));
                a.setParagraphTitleModal(rs.getString("paragraphTitleModal"));
                a.setIdentification(rs.getString("identification"));
                a.setIdentificationFunction(rs.getString("identificationFunction"));
                a.setSerial(rs.getString("serial"));
                a.setSerialFunction(rs.getString("serialFunction"));
                a.setImei(rs.getString("imei"));
                a.setImeiFunction(rs.getString("imeiFunction"));
                a.setMovil(rs.getString("movil"));
                a.setMovilFunction(rs.getString("movilFunction"));
                a.setDescription(rs.getString("description"));
                a.setDescriptionFunction(rs.getString("descriptionFunction"));
                a.setFilesAttachments(rs.getString("filesAttachments"));
                a.setFilesAttachmentsFuntion(rs.getString("filesAttachmentsFuntion"));
                a.setFilesAttachmentsButton(rs.getString("filesAttachmentsButton"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setLanguegeId(rs.getInt("languegeId"));
                a.setCreationDate(rs.getString("creationDate"));
                a.setUpdateDate(rs.getString("updateDate"));
                a.setLanguage(rs.getString("language"));
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
    
        public ResponseModel findByUserId(int id, int countryId) {
        PqrFomModalEntity a = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrFomModalListPerson(?,?)}");
            cs.setInt(1, id);
            cs.setInt(2, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new PqrFomModalEntity();
                a.setId(rs.getInt("id"));
                a.setTitleModal(rs.getString("titleModal"));
                a.setParagraphTitleModal(rs.getString("paragraphTitleModal"));
                a.setIdentification(rs.getString("identification"));
                a.setIdentificationFunction(rs.getString("identificationFunction"));
                a.setSerial(rs.getString("serial"));
                a.setSerialFunction(rs.getString("serialFunction"));
                a.setImei(rs.getString("imei"));
                a.setImeiFunction(rs.getString("imeiFunction"));
                a.setMovil(rs.getString("movil"));
                a.setMovilFunction(rs.getString("movilFunction"));
                a.setDescription(rs.getString("description"));
                a.setDescriptionFunction(rs.getString("descriptionFunction"));
                a.setFilesAttachments(rs.getString("filesAttachments"));
                a.setFilesAttachmentsFuntion(rs.getString("filesAttachmentsFuntion"));
                a.setFilesAttachmentsButton(rs.getString("filesAttachmentsButton"));
                a.setButtonOne(rs.getString("buttonOne"));
                a.setLanguegeId(rs.getInt("languegeId"));
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
