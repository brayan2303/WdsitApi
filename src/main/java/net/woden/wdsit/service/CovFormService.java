package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.CovFormEntity;
import net.woden.wdsit.entity.GenCenterCostEntity;
import net.woden.wdsit.entity.GenCustomerEntity;
import net.woden.wdsit.entity.GenSegmentEntity;
import net.woden.wdsit.model.CovFormFileModel;
import net.woden.wdsit.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CovFormService {

    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;

    public ResponseModel create(CovFormEntity a) {     //crear y almacenar info
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, a.getHasSymptoms());
            cs.setDate(2, a.getSymptomsDate());
            cs.setString(3, a.getDescripSymptims());
            cs.setString(4, a.getConcatPerson());
            cs.setString(5, a.getParentsPerson());
            cs.setDate(6, a.getCuarentDate());
            cs.setString(7, a.getFamBusiness());
            cs.setString(8, a.getFamParents());
            cs.setString(9, a.getDocumentHos());
            cs.setInt(10, a.getIdentificationUser());
            cs.setString(11, a.getName());
            cs.setString(12, a.getCompany());
            cs.setString(13, a.getTypeDocument());
            cs.setString(14, a.getPosition());
            cs.setString(15, a.getBossName());
            cs.setString(16, a.getEps());
            cs.setString(17, a.getDirection());
            cs.setString(18, a.getPhone());
            cs.setString(19, a.getMobile());
            cs.setString(20, a.getEmail());
            cs.setString(21, a.getPlaceSymptoms());
            cs.setString(22, a.getStatedAnswer());
            cs.setString(23, a.getPublicAnswer());
            cs.setInt(24, a.getCityId());
            cs.registerOutParameter(25, Types.INTEGER);
            cs.execute();
            inserts = cs.getInt(25);
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

    public ResponseModel delete(int id) {                                 //eliminar info
        int deletes = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormDelete(?)}");
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

    public ResponseModel update(CovFormEntity a) {         //actualizar info
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, a.getId());
            cs.setString(2, a.getHasSymptoms());
            cs.setDate(3, a.getSymptomsDate());
            cs.setString(4, a.getDescripSymptims());
            cs.setString(5, a.getConcatPerson());
            cs.setString(6, a.getParentsPerson());
            cs.setDate(7, a.getCuarentDate());
            cs.setString(8, a.getFamBusiness());
            cs.setString(9, a.getFamParents());
            cs.setString(10, a.getDocumentHos());
            cs.setInt(11, a.getIdentificationUser());
            cs.setBoolean(12, a.isActive());
            cs.registerOutParameter(13, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(13);
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

    public ResponseModel activeInactive(int id, boolean status) {         //actualizar info
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormActiveInactive(?,?,?)}");
            cs.setInt(1, id);
            cs.setBoolean(2, status);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(3);
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

    public ResponseModel list() {                                   //listar en pantalla info
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormList()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity a = new CovFormEntity();
                a.setId(rs.getInt("Id"));
                a.setHasSymptoms(rs.getString("hasSymptoms"));
                a.setSymptomsDate(rs.getDate("symptomsDate"));
                a.setDescripSymptims(rs.getString("descripSymptims"));
                a.setConcatPerson(rs.getString("concatPerson"));
                a.setParentsPerson(rs.getString("parentsPerson"));
                a.setCuarentDate(rs.getDate("cuarentDate"));
                a.setFamBusiness(rs.getString("famBusiness"));
                a.setFamParents(rs.getString("famParents"));
                a.setCreationUser(rs.getString("creationUser"));
                a.setDocumentHos(rs.getString("documentHos"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setName(rs.getString("name"));
                a.setCompany(rs.getString("company"));
                a.setTypeDocument(rs.getString("typeDocument"));
                a.setPosition(rs.getString("position"));
                a.setBossName(rs.getString("bossName"));
                a.setEps(rs.getString("eps"));
                a.setDirection(rs.getString("direction"));
                a.setPhone(rs.getString("phone"));
                a.setMobile(rs.getString("mobile"));
                a.setEmail(rs.getString("email"));
                a.setPlaceSymptoms(rs.getString("placeSymptoms"));
                a.setStatedAnswer(rs.getString("statedAnswer"));
                a.setPublicAnswer(rs.getString("publicAnswer"));
                a.setCityId(rs.getInt("cityId"));
                a.setCity(rs.getString("city"));
                a.setPositions(rs.getString("positions"));
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

    public ResponseModel findById(int identificationUser) {                                   //listar en pantalla info
        CovFormEntity a = null;
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormFindById(?)}");
            cs.setInt(1, identificationUser);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                a = new CovFormEntity();
                a.setId(rs.getInt("id"));
                a.setHasSymptoms(rs.getString("hasSymptoms"));
                a.setSymptomsDate(rs.getDate("symptomsDate"));
                a.setDescripSymptims(rs.getString("descripSymptims"));
                a.setConcatPerson(rs.getString("concatPerson"));
                a.setParentsPerson(rs.getString("parentsPerson"));
                a.setCuarentDate(rs.getDate("cuarentDate"));
                a.setFamBusiness(rs.getString("famBusiness"));
                a.setFamParents(rs.getString("famParents"));
                a.setCreationUser(rs.getString("creationUser"));
                a.setDocumentHos(rs.getString("documentHos"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setName(rs.getString("name"));
                a.setCompany(rs.getString("company"));
                a.setTypeDocument(rs.getString("typeDocument"));
                a.setPosition(rs.getString("position"));
                a.setBossName(rs.getString("bossName"));
                a.setEps(rs.getString("eps"));
                a.setDirection(rs.getString("direction"));
                a.setPhone(rs.getString("phone"));
                a.setMobile(rs.getString("mobile"));
                a.setEmail(rs.getString("email"));
                a.setPlaceSymptoms(rs.getString("placeSymptoms"));
                a.setStatedAnswer(rs.getString("statedAnswer"));
                a.setPublicAnswer(rs.getString("publicAnswer"));
                a.setCityId(rs.getInt("cityId"));
                a.setActive(rs.getBoolean("active"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", a, 200);
    }

    public ResponseModel loadFile(int identification, String type, String creationDate, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }

        File directory2 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification + "\\" + type);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }

        File directory3 = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification + "\\" + type + "\\" + creationDate);
        if (!directory3.isDirectory()) {
            directory3.mkdir();
        }

        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory3 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
                    f.createNewFile();
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(file.getBytes());
                    fos.close();
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", load, 200);
    }

    public ResponseModel listFile(int identification, String type, String creationDate) {
        ArrayList<CovFormFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("covid.evidencias.url") + "\\" + identification + "\\" + type + "\\" + creationDate);
        FileInputStream fis;

        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    CovFormFileModel p = new CovFormFileModel();
                    p.setName(f.getName());
                    p.setFile(bytes);
                    p.setType(f.getName().split("\\.", 2)[1]);
                    list.add(p);
                } catch (FileNotFoundException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                } catch (IOException ex) {
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
    }

    public ResponseModel updateinitialization(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormInitializationUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getDateArea());
            cs.setString(3, c.getStatusHealthPunctual());
            cs.setString(4, c.getTypeCase());
            cs.setString(5, c.getScheduledAppointment());
            cs.setString(6, c.getDateAppointment());
            cs.setString(7, c.getDateInitiation());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateAnnexed(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormAnnexedUpdate(?,?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getContract());
            cs.setString(3, c.getSegment());
            cs.setString(4, c.getCustomer());
            cs.setString(5, c.getCostCenter());
            cs.setString(6, c.getNoApply());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(7);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateProof(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofUpdate(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getProofCovid());
            cs.setString(3, c.getDateProofOne());
            cs.setString(4, c.getDateExecutionProof());
            cs.setString(5, c.getResultProof());
            cs.setString(6, c.getDateNotificationResult());
            cs.setString(7, c.getNoApply());
            cs.registerOutParameter(8, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(8);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateProofSecond(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofSecondUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getDateSecondProof());
            cs.setString(3, c.getDateExecutionSecondProof());
            cs.setString(4, c.getResultSecondProof());
            cs.setString(5, c.getDateNotificationResultSecond());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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

    public ResponseModel updateProofThird(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofThirdUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getDateThirdProof());
            cs.setString(3, c.getDateExecutionThirdProof());
            cs.setString(4, c.getResultThirdProof());
            cs.setString(5, c.getDateNotificationResultThird());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateFollowUp(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofFollowUpUpdate(?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getSegmentResult());
            cs.setString(3, c.getFollowUp());
            cs.setString(4, c.getStatusHealth());
            cs.setString(5, c.getStatusWokin());
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(6);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateWorking(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormWorkingUpdate(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getDateReinstate());
            cs.setString(3, c.getObservationDelivery());
            cs.setString(4, c.getTimeIsolation());
            cs.setString(5, c.getObservationWorking());
            cs.setString(6, c.getDayIsolation());
            cs.setString(7, c.getTotalDayIsolation());
            cs.setString(8, c.getConsolidatedWork());
            cs.setString(9, c.getVacunation());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(10);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel updateObservation(int id, CovFormEntity c) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_CovFormObservationUpdate(?,?,?,?,?)}");
            cs.setInt(1, id);
            cs.setString(2, c.getSiegeEpimiodological());
            cs.setString(3, c.getObservation());
            cs.setString(4, c.getHospitalization());
            cs.registerOutParameter(5, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(5);
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
        return new ResponseModel(getClass().getSimpleName(), "OK", updates, 200);
    }

    public ResponseModel initializatioFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormInitializatioFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateArea(rs.getString("dateArea"));
                c.setStatusHealthPunctual(rs.getString("statusHealthPunctual"));
                c.setTypeCase(rs.getString("typeCase"));
                c.setScheduledAppointment(rs.getString("scheduledAppointment"));
                c.setDateAppointment(rs.getString("dateAppointment"));
                c.setDateInitiation(rs.getString("dateInitiation"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel annexedFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormAnnexedFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setContract(rs.getString("contract"));
                c.setSegment(rs.getString("segment"));
                c.setCustomer(rs.getString("customer"));
                c.setCostCenter(rs.getString("costCenter"));
                c.setNoApply(rs.getString("noApply"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel proofFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setProofCovid(rs.getString("proofCovid"));
                c.setDateProofOne(rs.getString("dateProofOne"));
                c.setDateExecutionProof(rs.getString("dateExecutionProof"));
                c.setResultProof(rs.getString("resultProof"));
                c.setDateNotificationResult(rs.getString("dateNotificationResult"));
                c.setNoApply(rs.getString("noApply"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel proofSecondFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofSecondFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateSecondProof(rs.getString("dateSecondProof"));
                c.setDateExecutionSecondProof(rs.getString("dateExecutionSecondProof"));
                c.setResultSecondProof(rs.getString("resultSecondProof"));
                c.setDateNotificationResultSecond(rs.getString("dateNotificationResultSecond"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel proofThirdFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofThirdFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateThirdProof(rs.getString("dateThirdProof"));
                c.setDateExecutionThirdProof(rs.getString("dateExecutionThirdProof"));
                c.setResultThirdProof(rs.getString("resultThirdProof"));
                c.setDateNotificationResultThird(rs.getString("dateNotificationResultThird"));

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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel proofFollowUpFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofFollowUpFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setSegmentResult(rs.getString("segmentResult"));
                c.setFollowUp(rs.getString("followUp"));
                c.setStatusHealth(rs.getString("statusHealth"));
                c.setStatusWokin(rs.getString("statusWokin"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel workingFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormWorkingFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateReinstate(rs.getString("dateReinstate"));
                c.setObservationDelivery(rs.getString("observationDelivery"));
                c.setTimeIsolation(rs.getString("timeIsolation"));
                c.setObservationWorking(rs.getString("observationWorking"));
                c.setDayIsolation(rs.getString("dayIsolation"));
                c.setTotalDayIsolation(rs.getString("totalDayIsolation"));
                c.setConsolidatedWork(rs.getString("consolidatedWork"));
                c.setVacunation(rs.getString("vacunation"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel observationFindById(int id) {
        CovFormEntity c = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormObservationFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setSiegeEpimiodological(rs.getString("siegeEpimiodological"));
                c.setObservation(rs.getString("observation"));
                c.setHospitalization(rs.getString("hospitalization"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", c, 200);
    }

    public ResponseModel initializatioFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormInitializatioFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateArea(rs.getString("dateArea"));
                c.setStatusHealthPunctual(rs.getString("statusHealthPunctual"));
                c.setTypeCase(rs.getString("typeCase"));
                c.setScheduledAppointment(rs.getString("scheduledAppointment"));
                c.setDateAppointment(rs.getString("dateAppointment"));
                c.setDateInitiation(rs.getString("dateInitiation"));
                list.add(c);

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

    public ResponseModel annexedFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormAnnexedFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setContract(rs.getString("contract"));
                c.setSegment(rs.getString("segment"));
                c.setCustomer(rs.getString("customer"));
                c.setCostCenter(rs.getString("costCenter"));
                c.setNoApply(rs.getString("noApply"));
                list.add(c);
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

    public ResponseModel proofFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setProofCovid(rs.getString("proofCovid"));
                c.setDateProofOne(rs.getString("dateProofOne"));
                c.setDateExecutionProof(rs.getString("dateExecutionProof"));
                c.setResultProof(rs.getString("resultProof"));
                c.setDateNotificationResult(rs.getString("dateNotificationResult"));
                c.setNoApply(rs.getString("noApply"));
                list.add(c);
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

    public ResponseModel proofSecondFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofSecondFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateSecondProof(rs.getString("dateSecondProof"));
                c.setDateExecutionSecondProof(rs.getString("dateExecutionSecondProof"));
                c.setResultSecondProof(rs.getString("resultSecondProof"));
                c.setDateNotificationResultSecond(rs.getString("dateNotificationResultSecond"));
                list.add(c);
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

    public ResponseModel proofThirdFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofThirdFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateThirdProof(rs.getString("dateThirdProof"));
                c.setDateExecutionThirdProof(rs.getString("dateExecutionThirdProof"));
                c.setResultThirdProof(rs.getString("resultThirdProof"));
                c.setDateNotificationResultThird(rs.getString("dateNotificationResultThird"));
                list.add(c);

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

    public ResponseModel proofFollowUpFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormProofFollowUpFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setSegmentResult(rs.getString("segmentResult"));
                c.setFollowUp(rs.getString("followUp"));
                c.setStatusHealth(rs.getString("statusHealth"));
                list.add(c);
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

    public ResponseModel workingFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormWorkingFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setDateReinstate(rs.getString("dateReinstate"));
                c.setObservationDelivery(rs.getString("observationDelivery"));
                c.setTimeIsolation(rs.getString("timeIsolation"));
                c.setObservationWorking(rs.getString("observationWorking"));
                c.setDayIsolation(rs.getString("dayIsolation"));
                c.setTotalDayIsolation(rs.getString("totalDayIsolation"));
                c.setConsolidatedWork(rs.getString("consolidatedWork"));
                list.add(c);
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

    public ResponseModel observationFindByIdArray(int id) {
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_CovFormObservationFindById(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity c = new CovFormEntity();
                c.setId(rs.getInt("id"));
                c.setSiegeEpimiodological(rs.getString("siegeEpimiodological"));
                c.setObservation(rs.getString("observation"));
                c.setHospitalization(rs.getString("hospitalization"));
                list.add(c);
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
    
   public ResponseModel listCustomer(){
   ArrayList<GenCustomerEntity> list = new ArrayList();
   Connection cn = this.ds.openConnection();
   
       try {
           CallableStatement cs = cn.prepareCall("{call sp_CovFormCustomerList()}");
           ResultSet rs = cs.executeQuery();
           while(rs.next()){
           GenCustomerEntity c = new GenCustomerEntity();
           c.setId(rs.getInt("id"));
           c.setCode(rs.getString("code"));
           c.setDescription(rs.getString("description"));
           c.setIncomeActive(rs.getBoolean("incomeActive"));
           list.add(c);
           }
           cs.close();
           rs.close();
           cn.close();
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
       }finally{
           try {
               cn.close();
           } catch (SQLException ex) {
               return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
           }
       }
       return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
   }
   public ResponseModel listSegment(){
   ArrayList<GenSegmentEntity> list = new ArrayList();
   Connection cn = this.ds.openConnection();
   
       try {
           CallableStatement cs = cn.prepareCall("{call sp_CovFormListSegment()}");
           ResultSet rs = cs.executeQuery();
           while(rs.next()){
           GenSegmentEntity c = new GenSegmentEntity();
           c.setId(rs.getInt("id"));
           c.setCode(rs.getString("code"));
           c.setDescription(rs.getString("description"));
           c.setIncomeActive(rs.getBoolean("incomeActive"));
           list.add(c);
           }
           cs.close();
           rs.close();
           cn.close();
           
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
       }finally{
           try {
               cn.close();
           } catch (SQLException ex) {
               return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
           }
       }
       return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
   }
   public ResponseModel listCostCenter(){
   ArrayList<GenCenterCostEntity>list = new ArrayList();
       Connection cn = this.ds.openConnection();
       
       try {
           CallableStatement cs = cn.prepareCall("{call sp_CovFormCostCenterList}");
           ResultSet rs = cs.executeQuery();
           while(rs.next()){
           GenCenterCostEntity c = new GenCenterCostEntity();
           c.setId(rs.getInt("id"));
           c.setCode(rs.getString("code"));
           c.setDescription(rs.getString("description"));
           c.setIncomeActive(rs.getBoolean("incomeActive"));
           list.add(c);
           
           }
           cs.close();
           rs.close();
           cn.close();
           
       } catch (SQLException ex) {
           return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
       }finally{
           try {
               cn.close();
           } catch (SQLException ex) {
               return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
           }
       }
    return new ResponseModel(getClass().getSimpleName(), "OK", list, 200);
   }
   
     public ResponseModel listAll(int id) {                                   //listar en pantalla info
        ArrayList<CovFormEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {

            CallableStatement cs = cn.prepareCall("{call sp_CovFormListAll(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                CovFormEntity a = new CovFormEntity();
                a.setId(rs.getInt("Id"));
                a.setHasSymptoms(rs.getString("hasSymptoms"));
                a.setSymptomsDate(rs.getDate("symptomsDate"));
                a.setDescripSymptims(rs.getString("descripSymptims"));
                a.setConcatPerson(rs.getString("concatPerson"));
                a.setParentsPerson(rs.getString("parentsPerson"));
                a.setCuarentDate(rs.getDate("cuarentDate"));
                a.setFamBusiness(rs.getString("famBusiness"));
                a.setFamParents(rs.getString("famParents"));
                a.setCreationUser(rs.getString("creationUser"));
                a.setDocumentHos(rs.getString("documentHos"));
                a.setCreationDate(rs.getDate("creationDate"));
                a.setIdentificationUser(rs.getInt("identificationUser"));
                a.setName(rs.getString("name"));
                a.setCompany(rs.getString("company"));
                a.setTypeDocument(rs.getString("typeDocument"));
                a.setPosition(rs.getString("position"));
                a.setBossName(rs.getString("bossName"));
                a.setEps(rs.getString("eps"));
                a.setDirection(rs.getString("direction"));
                a.setPhone(rs.getString("phone"));
                a.setMobile(rs.getString("mobile"));
                a.setEmail(rs.getString("email"));
                a.setPlaceSymptoms(rs.getString("placeSymptoms"));
                a.setStatedAnswer(rs.getString("statedAnswer"));
                a.setPublicAnswer(rs.getString("publicAnswer"));
                a.setCityId(rs.getInt("cityId"));
                a.setCity(rs.getString("city"));
                a.setPositions(rs.getString("positions"));
                a.setActive(rs.getBoolean("active"));
                a.setDateArea(rs.getString("dateArea"));
                a.setContract(rs.getString("contract"));
                a.setSegment(rs.getString("segment"));
                a.setCustomer(rs.getString("customer"));
                a.setStatusHealthPunctual(rs.getString("statusHealthPunctual"));
                a.setCostCenter(rs.getString("costCenter"));
                a.setTypeCase(rs.getString("typeCase"));
                a.setScheduledAppointment(rs.getString("scheduledAppointment"));
                a.setDateAppointment(rs.getString("dateAppointment"));
                a.setProofCovid(rs.getString("proofCovid"));
                a.setDateExecutionProof(rs.getString("dateExecutionProof"));
                a.setResultProof(rs.getString("resultProof"));
                a.setDateNotificationResult(rs.getString("dateNotificationResult"));
                a.setNoApply(rs.getString("noApply"));
                a.setDateSecondProof(rs.getString("dateSecondProof"));
                a.setResultSecondProof(rs.getString("resultSecondProof"));
                a.setDateNotificationResultSecond(rs.getString("dateNotificationResultSecond"));
                a.setSegmentResult(rs.getString("segmentResult"));
                a.setDateThirdProof(rs.getString("dateThirdProof"));
                a.setDateExecutionThirdProof(rs.getString("dateExecutionThirdProof"));
                a.setResultThirdProof(rs.getString("resultThirdProof"));
                a.setDateNotificationResultThird(rs.getString("dateNotificationResultThird"));
                a.setFollowUp(rs.getString("followUp"));
                a.setDateReinstate(rs.getString("dateReinstate"));
                a.setObservationDelivery(rs.getString("observationDelivery"));
                a.setTimeIsolation(rs.getString("timeIsolation"));
                a.setObservationWorking(rs.getString("observationWorking"));
                a.setDayIsolation(rs.getString("dayIsolation"));
                a.setDayInability(rs.getString("dayInability"));
                a.setTotalDayIsolation(rs.getString("totalDayIsolation"));
                a.setConsolidatedWork(rs.getString("consolidatedWork"));
                a.setStatusHealth(rs.getString("statusHealth"));
                a.setSiegeEpimiodological(rs.getString("siegeEpimiodological"));
                a.setObservation(rs.getString("observation"));
                a.setHospitalization(rs.getString("hospitalization"));
                a.setDateInitiation(rs.getString("dateInitiation"));
                a.setDaysTotal(rs.getString("daysTotal"));
                a.setTotalInfo(rs.getString("totalInfo"));
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
}
