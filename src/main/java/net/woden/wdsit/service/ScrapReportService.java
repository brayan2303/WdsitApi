/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.ScpAuditEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScrapReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author b.algecira
 */
@Service
public class ScrapReportService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private Environment en;

    public ResponseModel generateReport(int id) throws JRException{
        int x = id;
        System.err.println(x);
        ScpAuditEntity b = null;
        Connection cn = this.ds.openConnection();
        Map<String, Object> parametros;

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                b = new ScpAuditEntity();
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
                System.err.println(b);
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
        
        

        parametros = new HashMap();
        parametros.put("id", String.valueOf(b.getId()));
        ScrapReportModel p = null;
        FileInputStream fis;
        try {
            
            cn = this.ds.openConnection();

            JasperReport report = JasperCompileManager.compileReport(this.en.getProperty("scrap.service.url")+"\\newReport.jrxml");
            JasperPrint pl = JasperFillManager.fillReport(report, parametros, cn);
            JasperExportManager.exportReportToPdfFile(pl, this.en.getProperty("scrap.reporte.url") + "\\" + id + ".pdf");
            File file = new File(this.en.getProperty("scrap.reporte.url") + "\\" + id + ".pdf");
            p = new ScrapReportModel();
            fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();
            p.setName(id);
            p.setFile(bytes);
            p.setType("pdf");
            try {
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

        } catch (IOException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }

        return new ResponseModel(getClass().getSimpleName(), "OK", p, 200);
    }

}
