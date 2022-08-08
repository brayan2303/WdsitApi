package net.woden.wdsit.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PqrPqrsEntity implements Serializable {

    @Id
    private int id;
    private String number;
    private String ticket;
    private int customerTypeId;
    private String customerType;
    private int proyectId;
    private String proyect;
    private String firstName;
    private String lastName;
    private int creationPersonId;
    private String creationPerson;
    private int assignedPersonId;
    private String assignedPerson;
    private int countryId;
    private String country;
    private int departmentId;
    private String department;
    private int cityId;
    private String city;
    private int regionalId;
    private String regional;
    private int tradeMarkId;
    private String tradeMark;
    private int contactMethodId;
    private String contactMethod;
    private int contactEmailId;
    private String contactEmail;
    private int categoryId;
    private String category;
    private int responsibleAreaId;
    private String responsibleArea;
    private String serial;
    private int typeId;
    private String type;
    private int levelNumber;
    private String summary;
    private String creationDate;
    private int entryNumber;
    private int statusId;
    private String status;
    private int finalStatusId;
    private String finalStatus;
    private int technicalId;
    private String technical;
    private int photographicRecordId;
    private String photographicRecord;
    private int diagnosticId;
    private String diagnostic;
    private String customerEscalationDate;
    private String customerEscalationResponseDate;
    private int internalEscalationAgentId;
    private String internalEscalationAgent;
    private int internalEscalationTime;
    private boolean internalEscalationActive;
    private String internalEscalationDate;
    private String internalEscalationResponseDate;
    private String responseDateCustomerPqrs;
    private int managementStatusId;
    private String managementStatus;
    private int finalContactMethodId;
    private String finalContactMethod;
    private int equipmentQuantity;
    private int attributablePqrsId;
    private String attributablePqrs;
    private String observations;
    private String unitShipmentDate;
    private String unitArrivalDate;
    private String tat;
    private String tatAcido;
    private String userPqrs;
    private String creationDateFile;
    private boolean automaticManual;
    private String summaryAlternative;
    private int filesId;
    private String datefiles;
    private String numberId;
    private String dateFile;
    private String editFiles;
    private String observation;
    private String nameLastname;
    private int countryGeneralId;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getProyectId() {
        return proyectId;
    }

    public void setProyectId(int proyectId) {
        this.proyectId = proyectId;
    }

    public String getProyect() {
        return proyect;
    }

    public void setProyect(String proyect) {
        this.proyect = proyect;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCreationPersonId() {
        return creationPersonId;
    }

    public void setCreationPersonId(int creationPersonId) {
        this.creationPersonId = creationPersonId;
    }

    public String getCreationPerson() {
        return creationPerson;
    }

    public void setCreationPerson(String creationPerson) {
        this.creationPerson = creationPerson;
    }

    public int getAssignedPersonId() {
        return assignedPersonId;
    }

    public void setAssignedPersonId(int assignedPersonId) {
        this.assignedPersonId = assignedPersonId;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(int regionalId) {
        this.regionalId = regionalId;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public int getTradeMarkId() {
        return tradeMarkId;
    }

    public void setTradeMarkId(int tradeMarkId) {
        this.tradeMarkId = tradeMarkId;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }

    public int getContactMethodId() {
        return contactMethodId;
    }

    public void setContactMethodId(int contactMethodId) {
        this.contactMethodId = contactMethodId;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public int getContactEmailId() {
        return contactEmailId;
    }

    public void setContactEmailId(int contactEmailId) {
        this.contactEmailId = contactEmailId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getResponsibleAreaId() {
        return responsibleAreaId;
    }

    public void setResponsibleAreaId(int responsibleAreaId) {
        this.responsibleAreaId = responsibleAreaId;
    }

    public String getResponsibleArea() {
        return responsibleArea;
    }

    public void setResponsibleArea(String responsibleArea) {
        this.responsibleArea = responsibleArea;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFinalStatusId() {
        return finalStatusId;
    }

    public void setFinalStatusId(int finalStatusId) {
        this.finalStatusId = finalStatusId;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public int getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(int technicalId) {
        this.technicalId = technicalId;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public int getPhotographicRecordId() {
        return photographicRecordId;
    }

    public void setPhotographicRecordId(int photographicRecordId) {
        this.photographicRecordId = photographicRecordId;
    }

    public String getPhotographicRecord() {
        return photographicRecord;
    }

    public void setPhotographicRecord(String photographicRecord) {
        this.photographicRecord = photographicRecord;
    }

    public int getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(int diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getCustomerEscalationDate() {
        return customerEscalationDate;
    }

    public void setCustomerEscalationDate(String customerEscalationDate) {
        this.customerEscalationDate = customerEscalationDate;
    }

    public String getCustomerEscalationResponseDate() {
        return customerEscalationResponseDate;
    }

    public void setCustomerEscalationResponseDate(String customerEscalationResponseDate) {
        this.customerEscalationResponseDate = customerEscalationResponseDate;
    }

    public int getInternalEscalationAgentId() {
        return internalEscalationAgentId;
    }

    public void setInternalEscalationAgentId(int internalEscalationAgentId) {
        this.internalEscalationAgentId = internalEscalationAgentId;
    }

    public String getInternalEscalationAgent() {
        return internalEscalationAgent;
    }

    public void setInternalEscalationAgent(String internalEscalationAgent) {
        this.internalEscalationAgent = internalEscalationAgent;
    }

    public int getInternalEscalationTime() {
        return internalEscalationTime;
    }

    public void setInternalEscalationTime(int internalEscalationTime) {
        this.internalEscalationTime = internalEscalationTime;
    }

    public boolean isInternalEscalationActive() {
        return internalEscalationActive;
    }

    public void setInternalEscalationActive(boolean internalEscalationActive) {
        this.internalEscalationActive = internalEscalationActive;
    }

    public String getInternalEscalationDate() {
        return internalEscalationDate;
    }

    public void setInternalEscalationDate(String internalEscalationDate) {
        this.internalEscalationDate = internalEscalationDate;
    }

    public String getInternalEscalationResponseDate() {
        return internalEscalationResponseDate;
    }

    public void setInternalEscalationResponseDate(String internalEscalationResponseDate) {
        this.internalEscalationResponseDate = internalEscalationResponseDate;
    }

    public String getResponseDateCustomerPqrs() {
        return responseDateCustomerPqrs;
    }

    public void setResponseDateCustomerPqrs(String responseDateCustomerPqrs) {
        this.responseDateCustomerPqrs = responseDateCustomerPqrs;
    }

    public int getManagementStatusId() {
        return managementStatusId;
    }

    public void setManagementStatusId(int managementStatusId) {
        this.managementStatusId = managementStatusId;
    }

    public String getManagementStatus() {
        return managementStatus;
    }

    public void setManagementStatus(String managementStatus) {
        this.managementStatus = managementStatus;
    }

    public int getFinalContactMethodId() {
        return finalContactMethodId;
    }

    public void setFinalContactMethodId(int finalContactMethodId) {
        this.finalContactMethodId = finalContactMethodId;
    }

    public String getFinalContactMethod() {
        return finalContactMethod;
    }

    public void setFinalContactMethod(String finalContactMethod) {
        this.finalContactMethod = finalContactMethod;
    }

    public int getEquipmentQuantity() {
        return equipmentQuantity;
    }

    public void setEquipmentQuantity(int equipmentQuantity) {
        this.equipmentQuantity = equipmentQuantity;
    }

    public int getAttributablePqrsId() {
        return attributablePqrsId;
    }

    public void setAttributablePqrsId(int attributablePqrsId) {
        this.attributablePqrsId = attributablePqrsId;
    }

    public String getAttributablePqrs() {
        return attributablePqrs;
    }

    public void setAttributablePqrs(String attributablePqrs) {
        this.attributablePqrs = attributablePqrs;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getUnitShipmentDate() {
        return unitShipmentDate;
    }

    public void setUnitShipmentDate(String unitShipmentDate) {
        this.unitShipmentDate = unitShipmentDate;
    }

    public String getUnitArrivalDate() {
        return unitArrivalDate;
    }

    public void setUnitArrivalDate(String unitArrivalDate) {
        this.unitArrivalDate = unitArrivalDate;
    }

    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
    }

    public String getTatAcido() {
        return tatAcido;
    }

    public void setTatAcido(String tatAcido) {
        this.tatAcido = tatAcido;
    }

    public String getUserPqrs() {
        return userPqrs;
    }

    public void setUserPqrs(String userPqrs) {
        this.userPqrs = userPqrs;
    }

    public String getCreationDateFile() {
        return creationDateFile;
    }

    public void setCreationDateFile(String creationDateFile) {
        this.creationDateFile = creationDateFile;
    }

    public boolean isAutomaticManual() {
        return automaticManual;
    }

    public void setAutomaticManual(boolean automaticManual) {
        this.automaticManual = automaticManual;
    }

    public String getSummaryAlternative() {
        return summaryAlternative;
    }

    public void setSummaryAlternative(String summaryAlternative) {
        this.summaryAlternative = summaryAlternative;
    }

    public int getFilesId() {
        return filesId;
    }

    public void setFilesId(int filesId) {
        this.filesId = filesId;
    }

    public String getDatefiles() {
        return datefiles;
    }

    public void setDatefiles(String datefiles) {
        this.datefiles = datefiles;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getDateFile() {
        return dateFile;
    }

    public void setDateFile(String dateFile) {
        this.dateFile = dateFile;
    }

    public String getEditFiles() {
        return editFiles;
    }

    public void setEditFiles(String editFiles) {
        this.editFiles = editFiles;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNameLastname() {
        return nameLastname;
    }

    public void setNameLastname(String nameLastname) {
        this.nameLastname = nameLastname;
    }

    public int getCountryGeneralId() {
        return countryGeneralId;
    }

    public void setCountryGeneralId(int countryGeneralId) {
        this.countryGeneralId = countryGeneralId;
    }
    
    
}
