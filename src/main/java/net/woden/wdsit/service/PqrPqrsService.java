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
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.GenPersonEntity;
import net.woden.wdsit.entity.PqrCustomerEntity;
import net.woden.wdsit.entity.PqrMessageSendEntity;
import net.woden.wdsit.entity.PqrPqrsEntity;
import net.woden.wdsit.model.DataModel;
import net.woden.wdsit.model.PqrAgentModel;
import net.woden.wdsit.model.PqrPqrsFileModel;
import net.woden.wdsit.model.PqrStatusModel;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.EncryptUtility;
import net.woden.wdsit.util.HolyDaysUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PqrPqrsService {
    
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    @Autowired
    private GenPersonService genPersonS;
    @Autowired
    private EncryptUtility eu;
    @Autowired
    private PqrCustomerService pqrCustomer;
    @Autowired
    private PqrMessageSendService PqrMessageSendS;
    
    public ResponseModel create(PqrPqrsEntity p, int countryGeneralId) {
        String numberPqrs = "";
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsCreate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, p.getTicket());
            cs.setInt(2, p.getCustomerTypeId());
            cs.setInt(3, p.getProyectId());
            cs.setString(4, p.getFirstName());
            cs.setString(5, p.getLastName());
            cs.setInt(6, p.getCreationPersonId());
            cs.setInt(7, p.getCountryId());
            cs.setInt(8, p.getDepartmentId());
            cs.setInt(9, p.getCityId());
            cs.setInt(10, p.getRegionalId());
            cs.setString(11, p.getSerial());
            cs.setInt(12, p.getTypeId());
            cs.setTimestamp(13, Timestamp.valueOf(p.getCreationDate()));
            cs.setInt(14, p.getStatusId());
            cs.setInt(15, p.getTradeMarkId());
            cs.setInt(16, p.getContactMethodId());
            cs.setInt(17, p.getContactEmailId());
            cs.setInt(18, p.getResponsibleAreaId());
            cs.setString(19, p.getSummary());
            cs.setInt(20, p.getManagementStatusId());
            cs.setInt(21, p.getCategoryId());
            cs.setInt(22, p.getEquipmentQuantity());
            cs.setInt(23, p.getEntryNumber());
            cs.setInt(24, p.getLevelNumber());
            cs.setInt(25, countryGeneralId);
            cs.registerOutParameter(26, Types.INTEGER);
            cs.registerOutParameter(27, Types.VARCHAR);
            cs.execute();
            if (cs.getInt(26) > 0) {
                numberPqrs = cs.getString(27);
            }
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
        return new ResponseModel(getClass().getSimpleName(), "OK", numberPqrs, 200);
    }
    
    public ResponseModel update(PqrPqrsEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setString(2, p.getTicket());
            cs.setInt(3, p.getCustomerTypeId());
            cs.setInt(4, p.getProyectId());
            cs.setString(5, p.getFirstName());
            cs.setString(6, p.getLastName());
            cs.setInt(7, p.getCountryId());
            cs.setInt(8, p.getDepartmentId());
            cs.setInt(9, p.getCityId());
            cs.setInt(10, p.getRegionalId());
            cs.setString(11, p.getSerial());
            cs.setInt(12, p.getTypeId());
            cs.setTimestamp(13, Timestamp.valueOf(p.getCreationDate()));
            cs.setInt(14, p.getStatusId());
            cs.setInt(15, p.getTradeMarkId());
            cs.setInt(16, p.getContactMethodId());
            cs.setInt(17, p.getContactEmailId());
            cs.setInt(18, p.getResponsibleAreaId());
            cs.setString(19, p.getSummary());
            cs.setInt(20, p.getCategoryId());
            cs.setInt(21, p.getEquipmentQuantity());
            cs.setInt(22, p.getAttributablePqrsId());
            cs.setInt(23, p.getEntryNumber());
            cs.setInt(24, p.getLevelNumber());
            cs.registerOutParameter(25, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(25);
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
    
    public ResponseModel eventUpdate(PqrPqrsEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsEventUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setInt(2, p.getEntryNumber());
            cs.setInt(3, p.getTechnicalId());
            cs.setInt(4, p.getPhotographicRecordId());
            cs.setInt(5, p.getDiagnosticId());
            if (p.getCustomerEscalationDate() != null) {
                cs.setTimestamp(6, Timestamp.valueOf(p.getCustomerEscalationDate()));
            } else {
                cs.setDate(6, null);
            }
            if (p.getCustomerEscalationResponseDate() != null) {
                cs.setTimestamp(7, Timestamp.valueOf(p.getCustomerEscalationResponseDate()));
            } else {
                cs.setDate(7, null);
            }
            cs.setInt(8, p.getInternalEscalationAgentId());
            if (p.getInternalEscalationDate() != null) {
                cs.setTimestamp(9, Timestamp.valueOf(p.getInternalEscalationDate()));
            } else {
                cs.setDate(9, null);
            }
            if (p.getInternalEscalationResponseDate() != null) {
                cs.setTimestamp(10, Timestamp.valueOf(p.getInternalEscalationResponseDate()));
            } else {
                cs.setDate(10, null);
            }
            cs.setInt(11, p.getManagementStatusId());
            cs.setString(12, p.getObservations());
            if (p.getUnitShipmentDate() != null) {
                cs.setTimestamp(13, Timestamp.valueOf(p.getUnitShipmentDate()));
            } else {
                cs.setDate(13, null);
            }
            if (p.getUnitArrivalDate() != null) {
                cs.setTimestamp(14, Timestamp.valueOf(p.getUnitArrivalDate()));
            } else {
                cs.setDate(14, null);
            }
            cs.setInt(15, p.getAttributablePqrsId());
            cs.setInt(16, p.getFinalStatusId());
            cs.registerOutParameter(17, Types.INTEGER);
            cs.execute();
            updates = cs.getInt(17);
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
    
    public ResponseModel delete(int pqrsId) {
        int deletes = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsDelete(?)}");
            cs.setInt(1, pqrsId);
            deletes = cs.executeUpdate();
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
    
    public ResponseModel manage(String pqrsId, int assignedPersonId, int statusId, String type) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsManage(?,?,?,?,?)}");
            cs.setString(1, pqrsId);
            cs.setInt(2, assignedPersonId);
            cs.setInt(3, statusId);
            cs.setString(4, type);
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
    
    public ResponseModel finish(String pqrsId, int managementStatusId, String responseDateCustomerPqrs, int finalContactMethodId, int statusId, String obervations, int procedureId) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsFinish(?,?,?,?,?,?,?,?)}");
            cs.setString(1, pqrsId);
            cs.setInt(2, managementStatusId);
            cs.setTimestamp(3, Timestamp.valueOf(responseDateCustomerPqrs));
            cs.setInt(4, finalContactMethodId);
            cs.setInt(5, statusId);
            cs.setString(6, obervations);
            cs.setInt(7, procedureId);
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
    
    public ResponseModel find(String identificationNumber, String ticket, String numero, String serialImei, int countryId) {
        ArrayList<PqrPqrsEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsFind(?,?,?,?,?)}");
            cs.setString(1, identificationNumber);
            cs.setString(2, ticket);
            cs.setString(3, numero);
            cs.setString(4, serialImei);
            cs.setInt(5, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity p = new PqrPqrsEntity();
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setType(rs.getString("type"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel findCustomer(int userId, String identificationNumber, String ticket, String numero, String serialImei, String name) {
        ArrayList<PqrPqrsEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsFindCustomer(?,?,?,?,?,?)}");
            cs.setInt(1, userId);
            cs.setString(2, identificationNumber);
            cs.setString(3, ticket);
            cs.setString(4, numero);
            cs.setString(5, serialImei);
            cs.setString(6, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity p = new PqrPqrsEntity();
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setType(rs.getString("type"));
                p.setStatus(rs.getString("status"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel findById(String pqrsId) {
        PqrPqrsEntity p = null;
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsFindById(?)}");
            cs.setString(1, pqrsId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrPqrsEntity();
                p.setId(rs.getInt("id"));
                p.setNumber(rs.getString("number"));
                p.setTicket(rs.getString("ticket"));
                p.setCustomerTypeId(rs.getInt("customerTypeId"));
                p.setCustomerType(rs.getString("customerType"));
                p.setProyectId(rs.getInt("proyectId"));
                p.setProyect(rs.getString("proyect"));
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
                p.setCreationPersonId(rs.getInt("creationPersonId"));
                p.setCreationPerson(rs.getString("creationPerson"));
                p.setAssignedPersonId(rs.getInt("assignedPersonId"));
                p.setAssignedPerson(rs.getString("assignedPerson"));
                p.setCountryId(rs.getInt("countryId"));
                p.setCountry(rs.getString("country"));
                p.setDepartmentId(rs.getInt("departmentId"));
                p.setDepartment(rs.getString("department"));
                p.setCityId(rs.getInt("cityId"));
                p.setCity(rs.getString("city"));
                p.setRegionalId(rs.getInt("regionalId"));
                p.setRegional(rs.getString("regional"));
                p.setTradeMarkId(rs.getInt("tradeMarkId"));
                p.setTradeMark(rs.getString("tradeMark"));
                p.setContactMethodId(rs.getInt("contactMethodId"));
                p.setContactMethod(rs.getString("contactMethod"));
                p.setContactEmailId(rs.getInt("contactEmailId"));
                p.setContactEmail(rs.getString("contactEmail"));
                p.setCategoryId(rs.getInt("categoryId"));
                p.setCategory(rs.getString("category"));
                p.setResponsibleAreaId(rs.getInt("responsibleAreaId"));
                p.setResponsibleArea(rs.getString("responsibleArea"));
                p.setSerial(rs.getString("serial"));
                p.setTypeId(rs.getInt("typeId"));
                p.setType(rs.getString("type"));
                p.setLevelNumber(rs.getInt("levelNumber"));
                p.setSummary(rs.getString("summary"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setEntryNumber(rs.getInt("entryNumber"));
                p.setStatusId(rs.getInt("statusId"));
                p.setStatus(rs.getString("status"));
                p.setFinalStatusId(rs.getInt("finalStatusId"));
                p.setFinalStatus(rs.getString("finalStatus"));
                p.setTechnicalId(rs.getInt("technicalId"));
                p.setTechnical(rs.getString("technical"));
                p.setPhotographicRecordId(rs.getInt("photographicRecordId"));
                p.setPhotographicRecord(rs.getString("photographicRecord"));
                p.setDiagnosticId(rs.getInt("diagnosticId"));
                p.setDiagnostic(rs.getString("diagnostic"));
                p.setCustomerEscalationDate(rs.getString("customerEscalationDate"));
                p.setCustomerEscalationResponseDate(rs.getString("customerEscalationResponseDate"));
                p.setInternalEscalationAgentId(rs.getInt("internalEscalationAgentId"));
                p.setInternalEscalationAgent(rs.getString("internalEscalationAgent"));
                p.setInternalEscalationTime(rs.getInt("internalEscalationTime"));
                p.setInternalEscalationActive(rs.getBoolean("internalEscalationActive"));
                p.setInternalEscalationDate(rs.getString("internalEscalationDate"));
                p.setInternalEscalationResponseDate(rs.getString("internalEscalationResponseDate"));
                p.setResponseDateCustomerPqrs(rs.getString("responseDateCustomerPqrs"));
                p.setManagementStatusId(rs.getInt("managementStatusId"));
                p.setManagementStatus(rs.getString("managementStatus"));
                p.setFinalContactMethodId(rs.getInt("finalContactMethodId"));
                p.setFinalContactMethod(rs.getString("finalContactMethod"));
                p.setEquipmentQuantity(rs.getInt("equipmentQuantity"));
                p.setAttributablePqrsId(rs.getInt("attributablePqrsId"));
                p.setAttributablePqrs(rs.getString("attributablePqrs"));
                p.setObservations(rs.getString("observations"));
                p.setUnitShipmentDate(rs.getString("unitShipmentDate"));
                p.setUnitArrivalDate(rs.getString("unitArrivalDate"));
                p.setCreationDateFile(rs.getString("creationDateFile"));
                p.setAutomaticManual(rs.getBoolean("automaticManual"));
                p.setSummaryAlternative(rs.getString("summaryAlternative"));
                p.setFilesId(rs.getInt("filesId"));
            }
            cn.commit();
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
    
    public ResponseModel findByNumber(String number) {
        PqrPqrsEntity p = new PqrPqrsEntity();;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsFindByNumber(?)}");
            cs.setString(1, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNumber(rs.getString("number"));
                p.setTicket(rs.getString("ticket"));
                p.setCustomerTypeId(rs.getInt("customerTypeId"));
                p.setCustomerType(rs.getString("customerType"));
                p.setProyectId(rs.getInt("proyectId"));
                p.setProyect(rs.getString("proyect"));
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
                p.setCreationPersonId(rs.getInt("creationPersonId"));
                p.setCreationPerson(rs.getString("creationPerson"));
                p.setAssignedPersonId(rs.getInt("assignedPersonId"));
                p.setAssignedPerson(rs.getString("assignedPerson"));
                p.setCountryId(rs.getInt("countryId"));
                p.setCountry(rs.getString("country"));
                p.setDepartmentId(rs.getInt("departmentId"));
                p.setDepartment(rs.getString("department"));
                p.setCityId(rs.getInt("cityId"));
                p.setCity(rs.getString("city"));
                p.setRegionalId(rs.getInt("regionalId"));
                p.setRegional(rs.getString("regional"));
                p.setTradeMarkId(rs.getInt("tradeMarkId"));
                p.setTradeMark(rs.getString("tradeMark"));
                p.setContactMethodId(rs.getInt("contactMethodId"));
                p.setContactMethod(rs.getString("contactMethod"));
                p.setContactEmailId(rs.getInt("contactEmailId"));
                p.setContactEmail(rs.getString("contactEmail"));
                p.setCategoryId(rs.getInt("categoryId"));
                p.setCategory(rs.getString("category"));
                p.setResponsibleAreaId(rs.getInt("responsibleAreaId"));
                p.setResponsibleArea(rs.getString("responsibleArea"));
                p.setSerial(rs.getString("serial"));
                p.setTypeId(rs.getInt("typeId"));
                p.setType(rs.getString("type"));
                p.setLevelNumber(rs.getInt("levelNumber"));
                p.setSummary(rs.getString("summary"));
                p.setCreationDate(rs.getString("creationDate"));
                p.setEntryNumber(rs.getInt("entryNumber"));
                p.setStatusId(rs.getInt("statusId"));
                p.setStatus(rs.getString("status"));
                p.setFinalStatusId(rs.getInt("finalStatusId"));
                p.setFinalStatus(rs.getString("finalStatus"));
                p.setTechnicalId(rs.getInt("technicalId"));
                p.setTechnical(rs.getString("technical"));
                p.setPhotographicRecord(rs.getString("photographicRecord"));
                p.setDiagnostic(rs.getString("diagnostic"));
                p.setCustomerEscalationDate(rs.getString("customerEscalationDate"));
                p.setCustomerEscalationResponseDate(rs.getString("customerEscalationResponseDate"));
                p.setInternalEscalationAgentId(rs.getInt("internalEscalationAgentId"));
                p.setInternalEscalationAgent(rs.getString("internalEscalationAgent"));
                p.setInternalEscalationTime(rs.getInt("internalEscalationTime"));
                p.setInternalEscalationActive(rs.getBoolean("internalEscalationActive"));
                p.setInternalEscalationDate(rs.getString("internalEscalationDate"));
                p.setInternalEscalationResponseDate(rs.getString("internalEscalationResponseDate"));
                p.setResponseDateCustomerPqrs(rs.getString("responseDateCustomerPqrs"));
                p.setManagementStatusId(rs.getInt("managementStatusId"));
                p.setManagementStatus(rs.getString("managementStatus"));
                p.setFinalContactMethodId(rs.getInt("finalContactMethodId"));
                p.setFinalContactMethod(rs.getString("finalContactMethod"));
                p.setEquipmentQuantity(rs.getInt("equipmentQuantity"));
                p.setAttributablePqrsId(rs.getInt("attributablePqrsId"));
                p.setAttributablePqrs(rs.getString("attributablePqrs"));
                p.setObservations(rs.getString("observations"));
                p.setUnitShipmentDate(rs.getString("unitShipmentDate"));
                p.setUnitArrivalDate(rs.getString("unitArrivalDate"));
                p.setObservation(rs.getString("observation"));
                p.setAutomaticManual(rs.getBoolean("automaticManual"));
                p.setFilesId(rs.getInt("filesId"));
                p.setDatefiles(rs.getString("datefiles"));
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
    
    public ResponseModel list(int personId, String status, String initialDate, String finalDate, String number, int countryId) {
        ArrayList<PqrPqrsEntity> list = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsList(?,?,?,?,?,?)}");
            cs.setInt(1, personId);
            cs.setString(2, status);
            cs.setString(3, initialDate);
            cs.setString(4, finalDate);
            cs.setString(5, number);
            cs.setInt(6, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity p = new PqrPqrsEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setTypeId(rs.getInt("typeId"));
                p.setType(rs.getString("type"));
                p.setCreationDate(rs.getString("creationDate").substring(0, 16));
                p.setStatus(rs.getString("status"));
                p.setCategory(rs.getString("category"));
                p.setProyect(rs.getString("proyect"));
                p.setCustomerType(rs.getString("customerType"));
                if (rs.getString("status").equals("Terminada")) {
                    HolyDaysUtility h = new HolyDaysUtility();
                    p.setTat(String.valueOf(h.getBusinessDays(rs.getString("creationDate").substring(0, 10), rs.getString("responseDateCustomerPqrs").substring(0, 10))));
                } else {
                    HolyDaysUtility h = new HolyDaysUtility();
                    p.setTat(String.valueOf(h.getBusinessDays(rs.getString("creationDate").substring(0, 10), format.format(new Date()))));
                }
                p.setTatAcido(rs.getString("tatAcido"));
                p.setAssignedPerson(rs.getString("assignedPerson"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel listPerson(int personId, String status, String initialDate, String finalDate, String number, int countryId) {
        ArrayList<PqrPqrsEntity> list = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsListPerson(?,?,?,?,?,?)}");
            cs.setInt(1, personId);
            cs.setString(2, status);
            cs.setString(3, initialDate);
            cs.setString(4, finalDate);
            cs.setString(5, number);
            cs.setInt(6, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity p = new PqrPqrsEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setTypeId(rs.getInt("typeId"));
                p.setType(rs.getString("type"));
                p.setCreationDate(rs.getString("creationDate").substring(0, 16));
                p.setStatus(rs.getString("status"));
                p.setProyect(rs.getString("proyect"));
                p.setCategory(rs.getString("category"));
                p.setCustomerType(rs.getString("customerType"));
                if (rs.getString("status").equals("Terminada")) {
                    HolyDaysUtility h = new HolyDaysUtility();
                    p.setTat(String.valueOf(h.getBusinessDays(rs.getString("creationDate").substring(0, 10), rs.getString("responseDateCustomerPqrs").substring(0, 10))));
                } else {
                    HolyDaysUtility h = new HolyDaysUtility();
                    p.setTat(String.valueOf(h.getBusinessDays(rs.getString("creationDate").substring(0, 10), format.format(new Date()))));
                }
                p.setTatAcido(rs.getString("tatAcido"));
                p.setAssignedPerson(rs.getString("assignedPerson"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel tat(int countryId) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTat(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    
    public ResponseModel tatAcido(int countryId) {
        DataModel data = new DataModel();
        ArrayList<String> dataX = new ArrayList();
        ArrayList<Integer> dataY = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTatAcido(?)}");
            cs.setInt(1, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                dataX.add(rs.getString("dataX"));
                dataY.add(rs.getInt("dataY"));
            }
            data.setDataX(dataX);
            data.setDataY(dataY);
            cn.commit();
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
        return new ResponseModel(getClass().getSimpleName(), "OK", data, 200);
    }
    
    public ResponseModel totalMonth(String initialDate, String finalDate, int countryId) {
        ArrayList<PqrStatusModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTotalMonth(?,?,?)}");
            cs.setString(1, initialDate);
            cs.setString(2, finalDate);
            cs.setInt(3, countryId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrStatusModel p = new PqrStatusModel();
                p.setName(rs.getString("name"));
                p.setValue(rs.getInt("value"));
                p.setColor(rs.getString("color"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel totalAgent(String initialDate, String finalDate) {
        ArrayList<PqrAgentModel> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrTotalAgent(?,?)}");
            cs.setString(1, initialDate);
            cs.setString(2, finalDate);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrAgentModel p = new PqrAgentModel();
                p.setAgent(rs.getString("agent"));
                p.setAssigned(rs.getInt("assigned"));
                p.setProcess(rs.getInt("process"));
                p.setFinished(rs.getInt("finished"));
                p.setScaled(rs.getInt("scaled"));
                list.add(p);
            }
            cn.commit();
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
    
    public ResponseModel loadFile(String pqrsNumber, String type, MultipartFile[] files) {
        int load = 0;
        BufferedImage bi;
        File directory1 = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber);
        if (!directory1.isDirectory()) {
            directory1.mkdir();
        }
        File directory2 = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\" + type);
        if (!directory2.isDirectory()) {
            directory2.mkdir();
        }
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().split("\\.", 2)[1].equals("png") || file.getOriginalFilename().split("\\.", 2)[1].equals("jpg")) {
                try {
                    bi = ImageIO.read(file.getInputStream());
                    ImageIO.write(bi, file.getOriginalFilename().split("\\.", 2)[1], new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]));
                    load = 1;
                } catch (IOException ex) {
                    load = 0;
                    return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
                }
            } else {
                try {
                    File f = new File(directory2 + "\\" + file.getOriginalFilename().split("\\.", 2)[0] + "." + file.getOriginalFilename().split("\\.", 2)[1]);
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
    
    public ResponseModel deleteFile(String pqrsNumber, String type, String fileName) {
        int deletes = 0;
        File directory = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\" + type);
        
        for (String f : directory.list()) {
            if (f.equals(fileName)) {
                File file = new File(directory + "/" + f);
                if (file.delete()) {
                    deletes = 1;
                }
                break;
            }
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
    
    public ResponseModel deleteFileByPqrs(String pqrsNumber) {
        int deletes = 0;
        File directoryPqrs = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber);
        if (directoryPqrs.isDirectory()) {
            File directoryInicio = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\INICIO");
            if (directoryInicio.isDirectory()) {
                for (File f : directoryInicio.listFiles()) {
                    f.delete();
                }
                directoryInicio.delete();
            }
            File directoryFin = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\FIN");
            if (directoryFin.isDirectory()) {
                for (File f : directoryFin.listFiles()) {
                    f.delete();
                }
                directoryFin.delete();
            }
            if (directoryPqrs.delete()) {
                deletes = 1;
            }
        } else {
            deletes = 1;
        }
        
        return new ResponseModel(getClass().getSimpleName(), "OK", deletes, 200);
    }
    
    public ResponseModel listFile(String pqrsNumber, String type) {
        ArrayList<PqrPqrsFileModel> list = new ArrayList();
        File directory = new File(this.en.getProperty("pqrs.evidencias.url") + "\\" + pqrsNumber + "\\" + type);
        FileInputStream fis;
        if (directory.isDirectory()) {
            for (File f : directory.listFiles()) {
                try {
                    fis = new FileInputStream(f);
                    byte[] bytes = new byte[(int) f.length()];
                    fis.read(bytes);
                    fis.close();
                    PqrPqrsFileModel p = new PqrPqrsFileModel();
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
    
    public ResponseModel updateCustomerEscalation(String number) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsEscalationCustomer(?,?)}");
            cs.setString(1, number);
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
    
    public ResponseModel updateCustomerEscalationFinish(String number) {
        int updates = 0;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsEscalationCustomerFinish(?,?)}");
            cs.setString(1, number);
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
    
    public ResponseModel sendEmail(String id, String number, int destinatarioId) {
        if (id == null || id == "0" || id == "") {
            PqrMessageSendEntity politica = (PqrMessageSendEntity) this.PqrMessageSendS.findByIdMail(destinatarioId).getObject();
            GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        }
        PqrMessageSendEntity politica = (PqrMessageSendEntity) this.PqrMessageSendS.findByIdMail(destinatarioId).getObject();
        GenPersonEntity destinatario = (GenPersonEntity) this.genPersonS.findById(destinatarioId).getObject();
        PqrCustomerEntity destinatarioListaFront = (PqrCustomerEntity) this.pqrCustomer.findById(id).getObject();
        PqrPqrsEntity support = (PqrPqrsEntity) this.findByNumber(number).getObject();
        
        int status = 0;
        Properties props = new Properties();
        props.put("mail.smtp.host", this.en.getProperty("spring.mail.host"));
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", this.en.getProperty("spring.mail.port"));
        props.put("mail.smtp.mail.sender", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.user", this.en.getProperty("spring.mail.username"));
        props.put("mail.smtp.auth", "true");
        Session session;
        session = Session.getDefaultInstance(props);
        try {
            String messageHtml = "<div style=\"margin: 0; padding: 0;\"><center>\n"
                    + "<table style=\"font-family: Roboto, Helvetica, 'Myriad Pro'; background: #ffffff; margin: 0px; padding: 0px; width: 600px; height: 695px;\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td valign=\"top\">\n"
                    + "<div style=\"margin: 0 auto; text-align: left; width: 600px;\" align=\"left\">\n"
                    + "<table style=\"border-collapse: collapse; width: 600px; border-style: none; margin-left: auto; margin-right: auto;\" border=\"0\" cellspacing=\"0px\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<div style=\"text-align: center; height: 100px; width: 100%; background-color: #ea6109;\"><img style=\"height: 60%; margin-top: 20px;\" src=\"http://app.woden.com.co/wdsit/assets/images/Logo.png\" /></div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 30px;\">\n"
                    + "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 540px; background: #ea6109; border-radius: 0px 0px 21px 21px;\">\n"
                    + "<table style=\"width: 545px; height: 350px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #ffffff; padding: 25px 20px; border-radius: 20px; border: 1px solid #efecec;\">\n"
                    + "<table style=\"height: 298px;\" border=\"0\" width=\"485\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 30px;\">\n"
                    + "<td style=\"text-align: center; font-size: 30px; color: #354a5f; height: 30px;\"><strong>Notificaciones WDSIT</strong></td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 20px;\">\n"
                    + "<td style=\"padding: 10px; text-align: center; font-size: 18px; color: #354a5f; height: 20px;\"><strong>Respuesta final</strong></td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td dir=\"ltr\" style=\"font-size: 18px; color: #666666; text-align: left; height: 18px; width: 100%; padding: 0px !important;\">\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">¡Hola!  <strong>{destinatario}</strong></p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\"> Woden S.A.S confirma que se ha emitido la respuesta a su requerimiento identificado con N° de PQRS: <strong>{pqrs}</strong> </p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">¡Gracias por comunicarte con nosotros!</p>\n"
                    + "<br>"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">Servicio al Cliente</p>\n"
                    + "<p style=\"color: #9e9e9e; margin: 5px; text-align: justify;\">Woden S.A.S </p>\n"
                    + "<br>"
                    + "<i style=\"color: #9e9e9e; margin: 5px; text-align: justify;\"><strong>{politica}</strong></i>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td dir=\"ltr\" style=\"padding-top: 40px; text-align: center; color: #4285f4; font-size: 16px; font-weight: 300; height: 21px; width: 425.219px;\"><span style=\"background-color: #ffffff; color: #95a5a6;\">WODEN WDSIT</span></td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "<td style=\"width: 30px; vertical-align: top;\">\n"
                    + "<table style=\"padding: 0px; margin: 0px; width: 30px; height: 109px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "<tbody>\n"
                    + "<tr style=\"height: 88px;\">\n"
                    + "<td style=\"background: #ea6109; height: 88px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "<tr style=\"height: 21px;\">\n"
                    + "<td style=\"background: #ffffff; height: 21px; width: 15.2188px;\">&nbsp;</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td>\n"
                    + "<table style=\"width: 500px!important;\" border=\"0\" width=\"500\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n"
                    + "<tbody>\n"
                    + "<tr>\n"
                    + "<td style=\"padding-bottom: 10px; font-size: 10px; color: #666666; text-align: left; font-weight: 300;\">&copy; 2020 Woden S.A.S Todos los derechos reservados.</td>\n"
                    + "</tr>\n"
                    + "<tr>\n"
                    + "<td style=\"background: #eeeeee; padding: .75pt .75pt .75pt .75pt;\">\n"
                    + "<p style=\"margin-right: 0cm; margin-bottom: 13.5pt; margin-left: 0cm; line-height: 12.75pt; text-align: justify;\"><strong><span style=\"font-size: 8.5pt; color: black;\">NOTA DE CONFIDENCIALIDAD</span></strong><span style=\"font-size: 8.5pt; color: black;\">&nbsp;<br />Este mensaje y los archivos anexos, en caso de que existan, contienen informaci&oacute;n de WODEN que es confidencial y para uso exclusivo de la persona o entidad de destino. Debido a que puede contener informaci&oacute;n privilegiada, confidencial o que de alguna manera est&aacute; protegida contra su distribuci&oacute;n no autorizada, agradecemos que si ha recibido este correo electr&oacute;nico por error, notificarlo de manera inmediata al remitente. La Protecci&oacute;n de datos est&aacute; dada para el cumplimiento del Decreto 1581 del 2012. Protege el Medio Ambiente; piensa antes de imprimir este mensaje.<u></u></span></p>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</div>\n"
                    + "</td>\n"
                    + "</tr>\n"
                    + "</tbody>\n"
                    + "</table>\n"
                    + "</center></div>";
            String mail = "jrojas@woden.com.co";
            String mail2 = "ycastano@woden.com.co";
            messageHtml = messageHtml.replace("{destinatario}", destinatario.getFirstName() + " " + destinatario.getLastName()).replace("{pqrs}", support.getNumber()).replace("{politica}", politica.getDescription());
            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), this.eu.decodePqrs(this.en.getProperty("spring.mail.password")));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject("Finalización de PQRS");
            message.setContent(messageHtml, "text/html; charset=utf-8");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail2));
            if (destinatarioListaFront.getEmailAnnexed() == null) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(destinatarioListaFront.getEmails()));
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                status = 1;
            } else {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(destinatarioListaFront.getEmailAnnexed() + "," + destinatarioListaFront.getEmails()));
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                status = 1;
            }
        } catch (MailException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        } catch (MessagingException ex) {
            return new ResponseModel(getClass().getSimpleName(), ex.getMessage(), null, 200);
        }
        return new ResponseModel(getClass().getSimpleName(), "OK", status, 200);
    }
    
    public ResponseModel escalationAgent(String number) {
        Connection cn = this.ds.openConnection();
        int agentId = 0;
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsEscalationAgent(?)}");
            cs.setString(1, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                agentId = rs.getInt("assignedPersonId");
                
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
        return new ResponseModel(getClass().getSimpleName(), "OK", agentId, 200);
    }
    
    public ResponseModel listReport(int userId, String initialDate, String finalDate) {
        ArrayList<PqrPqrsEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();
        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_dashBoardReportPqr(?,?,?)}");
            cs.setInt(1, userId);
            cs.setString(2, initialDate);
            cs.setString(3, finalDate);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsEntity pe = new PqrPqrsEntity();
                pe.setNumber(rs.getString("Numero"));
                pe.setTicket(rs.getString("Ticket"));
                pe.setNameLastname(rs.getString("nameLastname"));
                pe.setCustomerType(rs.getString("Tipo Cliente"));
                pe.setProyect(rs.getString("Proyecto"));
                pe.setUserPqrs(rs.getString("Usuario Pqrs"));
                pe.setCreationPerson(rs.getString("Usuario Creacion"));
                pe.setAssignedPerson(rs.getString("Usuario Asignado"));
                pe.setCountry(rs.getString("Pais"));
                pe.setDepartment(rs.getString("Departamento"));
                pe.setCity(rs.getString("Ciudad"));
                pe.setRegional(rs.getString("Regional"));
                pe.setTradeMark(rs.getString("Marca-Modelo"));
                pe.setContactMethod(rs.getString("Metodo Contacto Inicial"));
                pe.setContactEmail(rs.getString("Correo Contacto"));
                pe.setCategory(rs.getString("Categoria"));
                pe.setResponsibleArea(rs.getString("Area Responsable"));
                pe.setSerial(rs.getString("Serial"));
                pe.setType(rs.getString("Tipo"));
                pe.setSummary(rs.getString("Resumen"));
                pe.setCreationDate(rs.getString("Fecha Creacion"));
                pe.setEntryNumber(rs.getInt("Numero Ingresos"));
                pe.setStatus(rs.getString("Estado"));
                pe.setFinalStatus(rs.getString("Estado Final"));
                pe.setTechnical(rs.getString("Tecnico Responsable"));
                pe.setPhotographicRecord(rs.getString("Registro Fotografico"));
                pe.setDiagnostic(rs.getString("Diagnostico Tecnico-Reparacion"));
                pe.setCustomerEscalationDate(rs.getString("Fecha Escalacion Cliente"));
                pe.setCustomerEscalationResponseDate(rs.getString("Fecha Respuesta Escalacion Cliente"));
                pe.setInternalEscalationAgent(rs.getString("Usuario Escalacion Interna"));
                pe.setInternalEscalationDate(rs.getString("Fecha Escalacion Interna"));
                pe.setInternalEscalationResponseDate(rs.getString("Fecha Respuesta Escalacion Interna"));
                pe.setResponseDateCustomerPqrs(rs.getString("Fecha Respuesta Pqrs Cliente"));
                pe.setManagementStatus(rs.getString("Estado Gestion"));
                pe.setFinalContactMethod(rs.getString("Metodo Contacto Final"));
                pe.setEquipmentQuantity(rs.getInt("Cantidad Equipos"));
                pe.setAttributablePqrs(rs.getString("Pqrs Atribuible"));
                pe.setObservations(rs.getString("Observaciones Woden"));
                pe.setUnitShipmentDate(rs.getString("Fecha Envio Unidades"));
                pe.setUnitArrivalDate(rs.getString("Fecha Llegada Unidades"));
                pe.setLevelNumber(rs.getInt("Nivel"));
                list.add(pe);
            }
            cn.commit();
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
    
    public ResponseModel searchTypeTicket(String number) {
        PqrPqrsEntity d = null;
        Connection cn = this.ds.openConnection();
        try {
            CallableStatement cs = cn.prepareCall("{call sp_PqrsValidateTickets(?)}");
            cs.setString(1, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                d = new PqrPqrsEntity();
                d.setNumberId(rs.getString("numberId"));
                d.setDateFile(rs.getString("dateFile"));
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
        return new ResponseModel(getClass().getSimpleName(), "OK", d, 200);
    }
}
