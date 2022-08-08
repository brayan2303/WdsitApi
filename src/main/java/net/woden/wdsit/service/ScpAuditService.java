/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpAuditEntity;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author m.pulido
 */
@Service
public class ScpAuditService {

    @Autowired
    private DataSourceConnection ds;

    public ResponseModel create(int userId, ScpAuditEntity s) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditCreate(?,?,?,?,?)}");
            cs.setInt(1, s.getAuditPreviousId());
            cs.setInt(2, s.getLevelRuleId());
            cs.setString(3, s.getTypeAudit());
            cs.setInt(4, userId);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(5);
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

    public ResponseModel update(int auditId, ScpAuditEntity c) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditEdit(?,?,?,?,?,?)}");
            cs.setInt(1, auditId);
            cs.setInt(2, c.getAuditPreviousId());
            cs.setInt(3, c.getLevelRuleId());
            cs.setString(4, c.getTypeAudit());
            cs.setBoolean(5, c.isActive());
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
    }

    public ResponseModel close(int auditId, int quantity) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditClose(?,?,?)}");
            cs.setInt(1, auditId);
            cs.setInt(2, quantity);
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

    public ResponseModel delete(int auditId) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditDelete(?)}");
            cs.setInt(1, auditId);
            cs.execute();
            inserts = 1;
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
        ArrayList<ScpAuditEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditEntity b = new ScpAuditEntity();
                b.setId(rs.getInt("id"));
                b.setCodeAudit(rs.getString("codeAudit"));
                b.setAuditPreviousId(rs.getInt("auditPreviousId"));
                b.setAuditPreviousName(rs.getString("auditPreviousName"));
                b.setState(rs.getString("state"));
                b.setStateMotifId(rs.getInt("stateMotifId"));
                b.setStateMotifName(rs.getString("stateMotifName"));
                b.setOpenPallet(rs.getBoolean("openPallet"));
                b.setTypeAudit(rs.getString("typeAudit"));
                b.setTypeAuditName(rs.getString("typeAuditName"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                b.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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

    public ResponseModel listForPallet() {
        ArrayList<ScpAuditEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditListForPallet()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditEntity b = new ScpAuditEntity();
                b.setId(rs.getInt("id"));
                b.setCodeAudit(rs.getString("codeAudit"));
                b.setAuditPreviousId(rs.getInt("auditPreviousId"));
                b.setAuditPreviousName(rs.getString("auditPreviousName"));
                b.setState(rs.getString("state"));
                b.setStateMotifId(rs.getInt("stateMotifId"));
                b.setStateMotifName(rs.getString("stateMotifName"));
                b.setOpenPallet(rs.getBoolean("openPallet"));
                b.setTypeAudit(rs.getString("typeAudit"));
                b.setTypeAuditName(rs.getString("typeAuditName"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                b.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                list.add(b);
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

    public ResponseModel listForCrossing() {
        ArrayList<ScpAuditEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditListForCrossing()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ScpAuditEntity b = new ScpAuditEntity();
                b.setId(rs.getInt("id"));
                b.setCodeAudit(rs.getString("codeAudit"));
                b.setAuditPreviousId(rs.getInt("auditPreviousId"));
                b.setAuditPreviousName(rs.getString("auditPreviousName"));
                b.setState(rs.getString("state"));
                b.setStateMotifId(rs.getInt("stateMotifId"));
                b.setStateMotifName(rs.getString("stateMotifName"));
                b.setOpenPallet(rs.getBoolean("openPallet"));
                b.setTypeAudit(rs.getString("typeAudit"));
                b.setTypeAuditName(rs.getString("typeAuditName"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                b.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
                b.setCustomer(rs.getString("customer"));
                list.add(b);
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

    public ResponseModel findById(int auditId) {
        ScpAuditEntity b = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditFindById(?)}");
            cs.setInt(1, auditId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditEntity();
                b.setId(rs.getInt("id"));
                b.setCodeAudit(rs.getString("codeAudit"));
                b.setCustomer(rs.getString("customer"));
                b.setAuditPreviousId(rs.getInt("auditPreviousId"));
                b.setAuditPreviousName(rs.getString("auditPreviousName"));
                b.setState(rs.getString("state"));
                b.setStateMotifId(rs.getInt("stateMotifId"));
                b.setStateMotifName(rs.getString("stateMotifName"));
                b.setOpenPallet(rs.getBoolean("openPallet"));
                b.setTypeAudit(rs.getString("typeAudit"));
                b.setTypeAuditName(rs.getString("typeAuditName"));
                b.setLevelRuleId(rs.getInt("levelRuleId"));
                b.setLevelRuleName(rs.getString("levelRuleName"));
                b.setLevelRuleQuantityId(rs.getInt("levelRuleQuantityId"));
                b.setLevelRuleQuantity(rs.getInt("levelRuleQuantity"));
                b.setUserId(rs.getInt("userId"));
                b.setUserName(rs.getString("userName"));
                b.setCreationDate(rs.getString("creationDate"));
                b.setActive(rs.getBoolean("active"));
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

    public ResponseModel updateClose(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditCloseUpdate(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
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

    public ResponseModel updateAuditApproved(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditApproved(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
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

    public ResponseModel updateAuditRejected(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditRejected(?,?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(2);
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
}
