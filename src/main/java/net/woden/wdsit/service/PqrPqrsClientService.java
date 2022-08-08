/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.service;

import java.awt.image.BufferedImage;
import java.io.File;
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
import javax.imageio.ImageIO;
import net.woden.wdsit.connection.DataSourceConnection;
import net.woden.wdsit.entity.PqrPqrsClientEntity;
import net.woden.wdsit.model.ResponseModel;
import net.woden.wdsit.util.HolyDaysUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author b.algecira
 */
@Service
public class PqrPqrsClientService {
    
    @Autowired
    private DataSourceConnection ds;
    @Autowired
    private Environment en;
    
      public ResponseModel create(PqrPqrsClientEntity p) {
        int inserts = 0;
        Connection cn = this.ds.openConnection();

        try {
    
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientCreate(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, p.getTicket());
            cs.setString(2, p.getFirstName());
            cs.setString(3, p.getLastName());
            cs.setInt(4, p.getCreationPersonId());
            cs.setInt(5, p.getTypeId());
            cs.setInt(6, p.getResponsibleAreaId());
            cs.setString(7, p.getSummary());
            cs.setInt(8, p.getManagementStatusId());
            cs.setInt(9, p.getCategoryId());
            cs.setInt(10, p.getEquipmentQuantity());
            cs.setInt(11, p.getLevelNumber());
            cs.registerOutParameter(12, Types.INTEGER);
            cs.registerOutParameter(13, Types.VARCHAR);
            cs.execute();
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
        public ResponseModel update(PqrPqrsClientEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
        
            public ResponseModel eventUpdate(PqrPqrsClientEntity p) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientEventUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getId());
            cs.setInt(2, p.getEntryNumber());
            cs.setInt(3, p.getTechnicalId());
            cs.setInt(4,p.getPhotographicRecordId());
            cs.setInt(5,p.getDiagnosticId());
            if(p.getCustomerEscalationDate()!=null){
                cs.setTimestamp(6,Timestamp.valueOf(p.getCustomerEscalationDate()));
            }else{
                cs.setDate(6,null);
            }
            if(p.getCustomerEscalationResponseDate()!=null){
                cs.setTimestamp(7,Timestamp.valueOf(p.getCustomerEscalationResponseDate()));
            }else{
                cs.setDate(7,null);
            }
            cs.setInt(8,p.getInternalEscalationAgentId());
            if(p.getInternalEscalationDate()!=null){
                cs.setTimestamp(9,Timestamp.valueOf(p.getInternalEscalationDate()));
            }else{
                cs.setDate(9,null);
            }
            if(p.getInternalEscalationResponseDate()!=null){
                cs.setTimestamp(10,Timestamp.valueOf(p.getInternalEscalationResponseDate()));
            }else{
                cs.setDate(10,null);
            }
            cs.setInt(11,p.getManagementStatusId());
            cs.setString(12, p.getObservations());
            if(p.getUnitShipmentDate()!=null){
                cs.setTimestamp(13,Timestamp.valueOf(p.getUnitShipmentDate()));
            }else{
                cs.setDate(13,null);
            }
            if(p.getUnitArrivalDate()!=null){
                cs.setTimestamp(14,Timestamp.valueOf(p.getUnitArrivalDate()));
            }else{
                cs.setDate(14,null);
            }
            cs.setInt(15,p.getAttributablePqrsId());
            cs.setInt(16,p.getFinalStatusId());
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
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientDelete(?)}");
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

    public ResponseModel manage(int pqrsId, int assignedPersonId, int statusId, String type) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientManage(?,?,?,?,?)}");
            cs.setInt(1, pqrsId);
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

    public ResponseModel finish(int pqrsId, int managementStatusId, String responseDateCustomerPqrs, int finalContactMethodId, int statusId, String obervations) {
        int updates = 0;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientFinish(?,?,?,?,?,?,?)}");
            cs.setInt(1, pqrsId);
            cs.setInt(2, managementStatusId);
            cs.setTimestamp(3, Timestamp.valueOf(responseDateCustomerPqrs));
            cs.setInt(4, finalContactMethodId);
            cs.setInt(5, statusId);
            cs.setString(6, obervations);
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

    public ResponseModel find(String identificationNumber, String ticket, String numero, String serialImei) {
        ArrayList<PqrPqrsClientEntity> list = new ArrayList();
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientFind(?,?,?,?)}");
            cs.setString(1, identificationNumber);
            cs.setString(2, ticket);
            cs.setString(3, numero);
            cs.setString(4, serialImei);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsClientEntity p = new PqrPqrsClientEntity();
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

    public ResponseModel findById(int pqrsId) {
        PqrPqrsClientEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientFindById(?)}");
            cs.setInt(1, pqrsId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrPqrsClientEntity();
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
        PqrPqrsClientEntity p = null;
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientFindByNumber(?)}");
            cs.setString(1, number);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                p = new PqrPqrsClientEntity();
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

    public ResponseModel list(int personId, String status,String initialDate,String finalDate) {
        ArrayList<PqrPqrsClientEntity> list = new ArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Connection cn = this.ds.openConnection();

        try {
            cn.setAutoCommit(false);
            CallableStatement cs = cn.prepareCall("{call sp_PqrPqrsClientList(?,?,?,?)}");
            cs.setInt(1, personId);
            cs.setString(2, status);
            cs.setString(3, initialDate);
            cs.setString(4, finalDate);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PqrPqrsClientEntity p = new PqrPqrsClientEntity();
                p.setId(rs.getInt("id"));
                p.setTicket(rs.getString("ticket"));
                p.setNumber(rs.getString("number"));
                p.setTypeId(rs.getInt("typeId"));
                p.setType(rs.getString("type"));
                p.setCreationDate(rs.getString("creationDate").substring(0, 16));
                p.setStatus(rs.getString("status"));
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
}
/*
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
}
*/
