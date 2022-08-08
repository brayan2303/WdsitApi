/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpAuditLevelRuleQuantityEntity;
import net.woden.wdsit.entity.ScpAuditMotifEntity;
import net.woden.wdsit.entity.ScpAuditSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpAuditSerialModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpAuditSerialFinishService {
    
    @Autowired
    private DataSourceConnection ds;
    
     public ResponseModel list() {
        ArrayList<ScpAuditSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditSerialEntity s = new ScpAuditSerialEntity();
                s.setId(rs.getInt("Id"));
                s.setPalletId(rs.getInt("palletId"));
                s.setSerial(rs.getString("serial"));
                s.setMac(rs.getString("mac"));
                s.setSapCode(rs.getString("sapCode"));
                s.setDescription(rs.getString("description"));
                s.setTechnical(rs.getString("technical"));
                s.setRepairDate(rs.getString("repairDate"));
                s.setScrapMotif(rs.getString("scrapMotif"));
                s.setState(rs.getString("state"));
                s.setStateMotif(rs.getString("stateMotif"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                list.add(s);
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
     
       public ResponseModel listAudit() {
        ArrayList<ScpAuditSerialModel> listAud = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditListForAuditFinish()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditSerialModel s = new ScpAuditSerialModel();
                s.setId(rs.getInt("id"));
                s.setCodeAudit(rs.getString("codeAudit"));
                s.setAuditPreviousId(rs.getInt("auditPreviousId"));
                s.setAuditPreviousName(rs.getString("auditPreviousName"));
                s.setState(rs.getString("state"));
                s.setStateMotifId(rs.getInt("stateMotifId"));
                s.setStateMotifName(rs.getString("stateMotifName"));
                s.setOpenPallet(rs.getBoolean("openPallet"));
                s.setTypeAudit(rs.getString("typeAudit"));
                s.setTypeAuditName(rs.getString("typeAuditName"));
                s.setLevelRuleId(rs.getInt("levelRuleId"));
                s.setLevelRuleName(rs.getString("levelRuleName"));
                s.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                s.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                s.setUserId(rs.getInt("userId"));
                s.setUserName(rs.getString("userName"));
                s.setCreationDate(rs.getString("creationDate"));
                s.setActive(rs.getBoolean("active"));
                listAud.add(s);

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
        return new ResponseModel(getClass().getSimpleName(), "OK", listAud, 200);
    }

    public ResponseModel listMotif() {
        ArrayList<ScpAuditMotifEntity> listMotf = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditMotifListAudi()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditMotifEntity s = new ScpAuditMotifEntity();
                s.setId(rs.getInt("id"));
                s.setType(rs.getString("type"));
                s.setCode(rs.getString("code"));
                s.setDescription(rs.getString("description"));
                s.setCreationDate(rs.getString("creationDate"));
                s.setUserId(rs.getInt("userId"));
                s.setActive(rs.getBoolean("active"));
                listMotf.add(s);

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
        return new ResponseModel(getClass().getSimpleName(), "OK", listMotf, 200);
    }

    public ResponseModel findById(int levelRuleQuantityId) {
        ScpAuditLevelRuleQuantityEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityFindById(?)}");
            cs.setInt(1, levelRuleQuantityId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditLevelRuleQuantityEntity();
                b.setId(rs.getInt("id"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setQuantityMin(rs.getInt("quantityMin"));
                b.setQuantityMax(rs.getInt("quantityMax"));
                b.setNoveltyAccepted(rs.getInt("noveltyAccepted"));
                b.setNoveltyRejected(rs.getInt("noveltyRejected"));
                b.setShow(rs.getInt("show"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                b.setApprovedRejected(rs.getString("approvedRejected"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", b, 200);
    }

    public ResponseModel listLevel(int levelId) {
        ScpAuditSerialModel s = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditLevelRuleQuantityAudit(?)}");
            cs.setInt(1, levelId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpAuditSerialModel();
                s.setId(rs.getInt("id"));
                s.setCodeAudit(rs.getString("codeAudit"));
                s.setAuditPreviousId(rs.getInt("auditPreviousId"));
                s.setAuditPreviousName(rs.getString("auditPreviousName"));
                s.setState(rs.getString("state"));
                s.setStateMotifId(rs.getInt("stateMotifId"));
                s.setStateMotifName(rs.getString("stateMotifName"));
                s.setOpenPallet(rs.getBoolean("openPallet"));
                s.setTypeAudit(rs.getString("typeAudit"));
                s.setTypeAuditName(rs.getString("typeAuditName"));
                s.setLevelRuleId(rs.getInt("levelRuleId"));
                s.setLevelRuleName(rs.getString("levelRuleName"));
                s.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                s.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                s.setUserId(rs.getInt("userId"));
                s.setUserName(rs.getString("userName"));
                s.setCreationDate(rs.getString("creationDate"));
                s.setActive(rs.getBoolean("active"));
                s.setNoveltyAccepted(rs.getInt("noveltyAccepted"));
                s.setNoveltyRejected(rs.getInt("noveltyRejected"));
                s.setApprovedRejected(rs.getString("approvedRejected"));
                
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
        return new ResponseModel(getClass().getSimpleName(), "OK", s, 200);
    }
      public ResponseModel listCount(int levelId) {
        ScpAuditSerialModel s = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialReject(?)}");
            cs.setInt(1, levelId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpAuditSerialModel();
                s.setReject(rs.getInt("reject"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", s, 200);
    }

    public ResponseModel listAuditSerial(int id) {
        ArrayList<ScpAuditSerialEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                ScpAuditSerialEntity s = new ScpAuditSerialEntity();
                s.setId(rs.getInt("Id"));
                s.setPalletId(rs.getInt("palletId"));
                s.setSerial(rs.getString("serial"));
                s.setMac(rs.getString("mac"));
                s.setSapCode(rs.getString("sapCode"));
                s.setDescription(rs.getString("description"));
                s.setTechnical(rs.getString("technical"));
                s.setRepairDate(rs.getString("repairDate"));
                s.setScrapMotif(rs.getString("scrapMotif"));
                s.setState(rs.getString("state"));
                s.setStateMotif(rs.getString("stateMotif"));
                s.setUserId(rs.getInt("userId"));
                s.setCreationDate(rs.getString("creationDate"));
                list.add(s);
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
    
}
