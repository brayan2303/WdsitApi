/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaClaroConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaDirectvConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaEtbConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHpConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaHughesConnection;
import net.woden.wdsit.connection.DataSourceSapColombiaTigoConnection;
import net.woden.wdsit.entity.ScpAuditLevelRuleQuantityEntity;
import net.woden.wdsit.entity.ScpAuditMotifEntity;
import net.woden.wdsit.entity.ScpAuditSerialEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.model.ScpAuditSerialModel;
import net.woden.wdsit.model.ScpAuditSerialSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class ScpAuditSerialService {

    @Autowired
    private DataSourceConnection ds;

    @Autowired
    private DataSourceSapColombiaConnection dsWMS;

    @Autowired
    private Environment en;

    @Autowired
    private DataSourceSapColombiaClaroConnection dssColClaro;
    @Autowired
    private DataSourceSapColombiaDirectvConnection dssColDirec;
    @Autowired
    private DataSourceSapColombiaEtbConnection dssColEtb;
    @Autowired
    private DataSourceSapColombiaHpConnection dssColHp;
    @Autowired
    private DataSourceSapColombiaHughesConnection dssColHughes;
    @Autowired
    private DataSourceSapColombiaTigoConnection dssColTigo;

    public ResponseModel create(ScpAuditSerialEntity s) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialCreate(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, s.getPalletId());
            cs.setString(2, s.getSerial());
            cs.setString(3, s.getMac());
            cs.setString(4, s.getSapCode());
            cs.setString(5, s.getDescription());
            cs.setString(6, s.getTechnical());
            cs.setString(7, s.getRepairDate());
            cs.setString(8, s.getScrapMotif());
            cs.setString(9, s.getState());
            cs.setString(10, s.getStateMotif());
            cs.setInt(11, s.getUserId());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(12);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialDelete(?)}");
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

    public ResponseModel listAudit() {
        ArrayList<ScpAuditSerialModel> listAud = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditListForAudit()}");
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

    public ResponseModel listSearch(String serial) {

        ScpAuditSerialEntity s = null;
        Connection cn = this.ds.openConnection();

        ScpAuditSerialSearchModel p = null;
        Connection cns = this.dsWMS.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditPreviousSerialFindBySerial(?)}");
            cs.setString(1, serial);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                s = new ScpAuditSerialEntity();
                s.setId(rs.getInt("Id"));

                s.setSerial(rs.getString("serial"));

            }
            rs.close();
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        System.out.println(s);
        if (s != null) {

            try {
                CallableStatement cs = cns.prepareCall("{call wdn.sp_ScpAuditSerialSearch(?)}");
                cs.setString(1, serial);
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    p = new ScpAuditSerialSearchModel();
                    p.setSerial(rs.getString("Serial"));
                    p.setMac(rs.getString("Mac"));
                    p.setCodigoSap(rs.getString("CodigoSap"));
                    p.setDescripcion(rs.getString("Descripcion"));
                    p.setFechaReparacion(rs.getString("FechaReparacion"));
                    p.setUsuario(rs.getString("Usuario"));
                    p.setMotivoScrap(rs.getString("MotivoScrap"));
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
            return new ResponseModel(getClass().getSimpleName(), p == null ? "El serial no se encuentra en la auditoria previa" : "OK", p, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), s == null ? "El serial no se encuentra en la auditoria previa" : "OK", s, 200);
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

    public ResponseModel loadFile(int id, String serial, MultipartFile[] files) throws IOException {
        int inserts = 0;
        int load = 0;
        Connection cn = this.ds.openConnection();
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("scrap.image.url"));
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File archivo = new File(directory1 + "\\" + serial + ".jpg");

        for (MultipartFile file : files) {
            try {
                bi = ImageIO.read(file.getInputStream());
                ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory1 + "\\" + serial + ".jpg"));

                load = 1;
            } catch (IOException ex) {
                load = 0;
                return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
            }
        }
        if (load == 1) {
            try {
                cn.setAutoCommit(false);
                CallableStatement cs = cn.prepareCall("{call sp_ScpSerialImagenCreate(?,?,?)}");
                cs.setInt(1, id);
                cs.setString(2, serial);
                cs.registerOutParameter(3, Types.INTEGER);
                cs.execute();
                inserts = cs.getInt(3);
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

        }
        return new ResponseModel(getClass().getSimpleName(), "OK", inserts, 200);
    }

    public ResponseModel updateClose(int id) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_ScpAuditSerialCloseUpdate(?,?)}");
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
