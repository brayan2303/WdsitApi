package net.woden.wdsit.entity;

import java.io.Serializable;
import java.sql.Date;

public class CovFormEntity implements Serializable {

    private int id;
    private Date creationDate;
    private int IdentificationUser;
    private String creationUser;
    private String identificationUserCar;
    private String hasSymptoms;
    private Date symptomsDate;
    private String descripSymptims;
    private String concatPerson;
    private String parentsPerson;
    private Date cuarentDate;
    private String famBusiness;
    private String famParents;
    private String documentHos;
    private String name;
    private boolean active;
    private String company;
    private String typeDocument;
    private String position;
    private String bossName;
    private String eps;
    private String direction;
    private String phone;
    private String mobile;
    private String email;
    private String placeSymptoms;
    private String statedAnswer;
    private String publicAnswer;
    private int countryId;
    private int departmentId;
    private String city;
    private String positions; 
    private int cityId;
    private String dateArea;
    private String contract;
    private String segment;
    private String customer;
    private String statusHealthPunctual;
    private String costCenter;
    private String typeCase;
    private String scheduledAppointment;
    private String dateAppointment;
    private String proofCovid;
    private String dateExecutionProof;
    private String resultProof;
    private String dateNotificationResult;
    private String noApply;
    private String dateSecondProof;
    private String dateExecutionSecondProof;
    private String resultSecondProof;
    private String dateNotificationResultSecond;
    private String segmentResult;
    private String dateThirdProof;
    private String dateExecutionThirdProof;
    private String resultThirdProof;
    private String dateNotificationResultThird;
    private String followUp;
    private String status;
    private String dateReinstate;
    private String observationDelivery;
    private String timeIsolation;
    private String observationWorking;
    private String dayIsolation;
    private String dayInability;
    private String totalDayIsolation;
    private String consolidatedWork;
    private String statusHealth;
    private String siegeEpimiodological;
    private String observation;
    private String hospitalization;
    private String dateProofOne;
    private String dateInitiation;
    private String daysTotal;
    private String totalInfo;
    private String statusWokin;
    private String vacunation;

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getHasSymptoms() {
        return hasSymptoms;
    }

    public void setHasSymptoms(String hasSymptoms) {
        this.hasSymptoms = hasSymptoms;
    }

    public Date getSymptomsDate() {
        return symptomsDate;
    }

    public void setSymptomsDate(Date symptomsDate) {
        this.symptomsDate = symptomsDate;
    }

    public String getDescripSymptims() {
        return descripSymptims;
    }

    public void setDescripSymptims(String descripSymptims) {
        this.descripSymptims = descripSymptims;
    }

    public String getConcatPerson() {
        return concatPerson;
    }

    public void setConcatPerson(String concatPerson) {
        this.concatPerson = concatPerson;
    }

    public String getParentsPerson() {
        return parentsPerson;
    }

    public void setParentsPerson(String parentsPerson) {
        this.parentsPerson = parentsPerson;
    }

    public Date getCuarentDate() {
        return cuarentDate;
    }

    public void setCuarentDate(Date cuarentDate) {
        this.cuarentDate = cuarentDate;
    }

    public String getFamBusiness() {
        return famBusiness;
    }

    public void setFamBusiness(String famBusiness) {
        this.famBusiness = famBusiness;
    }

    public String getFamParents() {
        return famParents;
    }

    public void setFamParents(String famParents) {
        this.famParents = famParents;
    }

    public String getDocumentHos() {
        return documentHos;
    }

    public void setDocumentHos(String documentHos) {
        this.documentHos = documentHos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getIdentificationUser() {
        return IdentificationUser;
    }

    public void setIdentificationUser(int IdentificationUser) {
        this.IdentificationUser = IdentificationUser;
    }

    public String getIdentificationUserCar() {
        return identificationUserCar;
    }

    public void setIdentificationUserCar(String identificationUserCar) {
        this.identificationUserCar = identificationUserCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlaceSymptoms() {
        return placeSymptoms;
    }

    public void setPlaceSymptoms(String placeSymptoms) {
        this.placeSymptoms = placeSymptoms;
    }

    public String getStatedAnswer() {
        return statedAnswer;
    }

    public void setStatedAnswer(String statedAnswer) {
        this.statedAnswer = statedAnswer;
    }

    public String getPublicAnswer() {
        return publicAnswer;
    }

    public void setPublicAnswer(String publicAnswer) {
        this.publicAnswer = publicAnswer;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getDateArea() {
        return dateArea;
    }

    public void setDateArea(String dateArea) {
        this.dateArea = dateArea;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatusHealthPunctual() {
        return statusHealthPunctual;
    }

    public void setStatusHealthPunctual(String statusHealthPunctual) {
        this.statusHealthPunctual = statusHealthPunctual;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getTypeCase() {
        return typeCase;
    }

    public void setTypeCase(String typeCase) {
        this.typeCase = typeCase;
    }

    public String getScheduledAppointment() {
        return scheduledAppointment;
    }

    public void setScheduledAppointment(String scheduledAppointment) {
        this.scheduledAppointment = scheduledAppointment;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(String dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getProofCovid() {
        return proofCovid;
    }

    public void setProofCovid(String proofCovid) {
        this.proofCovid = proofCovid;
    }

    public String getDateExecutionProof() {
        return dateExecutionProof;
    }

    public void setDateExecutionProof(String dateExecutionProof) {
        this.dateExecutionProof = dateExecutionProof;
    }

    public String getResultProof() {
        return resultProof;
    }

    public void setResultProof(String resultProof) {
        this.resultProof = resultProof;
    }

    public String getDateNotificationResult() {
        return dateNotificationResult;
    }

    public void setDateNotificationResult(String dateNotificationResult) {
        this.dateNotificationResult = dateNotificationResult;
    }

    public String getNoApply() {
        return noApply;
    }

    public void setNoApply(String noApply) {
        this.noApply = noApply;
    }

    public String getDateSecondProof() {
        return dateSecondProof;
    }

    public void setDateSecondProof(String dateSecondProof) {
        this.dateSecondProof = dateSecondProof;
    }

    public String getDateExecutionSecondProof() {
        return dateExecutionSecondProof;
    }

    public void setDateExecutionSecondProof(String dateExecutionSecondProof) {
        this.dateExecutionSecondProof = dateExecutionSecondProof;
    }

    public String getResultSecondProof() {
        return resultSecondProof;
    }

    public void setResultSecondProof(String resultSecondProof) {
        this.resultSecondProof = resultSecondProof;
    }

    public String getDateNotificationResultSecond() {
        return dateNotificationResultSecond;
    }

    public void setDateNotificationResultSecond(String dateNotificationResultSecond) {
        this.dateNotificationResultSecond = dateNotificationResultSecond;
    }

    public String getSegmentResult() {
        return segmentResult;
    }

    public void setSegmentResult(String segmentResult) {
        this.segmentResult = segmentResult;
    }

    public String getDateThirdProof() {
        return dateThirdProof;
    }

    public void setDateThirdProof(String dateThirdProof) {
        this.dateThirdProof = dateThirdProof;
    }

    public String getDateExecutionThirdProof() {
        return dateExecutionThirdProof;
    }

    public void setDateExecutionThirdProof(String dateExecutionThirdProof) {
        this.dateExecutionThirdProof = dateExecutionThirdProof;
    }

    public String getResultThirdProof() {
        return resultThirdProof;
    }

    public void setResultThirdProof(String resultThirdProof) {
        this.resultThirdProof = resultThirdProof;
    }

    public String getDateNotificationResultThird() {
        return dateNotificationResultThird;
    }

    public void setDateNotificationResultThird(String dateNotificationResultThird) {
        this.dateNotificationResultThird = dateNotificationResultThird;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateReinstate() {
        return dateReinstate;
    }

    public void setDateReinstate(String dateReinstate) {
        this.dateReinstate = dateReinstate;
    }

    public String getObservationDelivery() {
        return observationDelivery;
    }

    public void setObservationDelivery(String observationDelivery) {
        this.observationDelivery = observationDelivery;
    }

    public String getTimeIsolation() {
        return timeIsolation;
    }

    public void setTimeIsolation(String timeIsolation) {
        this.timeIsolation = timeIsolation;
    }

    public String getObservationWorking() {
        return observationWorking;
    }

    public void setObservationWorking(String observationWorking) {
        this.observationWorking = observationWorking;
    }

    public String getDayIsolation() {
        return dayIsolation;
    }

    public void setDayIsolation(String dayIsolation) {
        this.dayIsolation = dayIsolation;
    }

    public String getDayInability() {
        return dayInability;
    }

    public void setDayInability(String dayInability) {
        this.dayInability = dayInability;
    }

    public String getTotalDayIsolation() {
        return totalDayIsolation;
    }

    public void setTotalDayIsolation(String totalDayIsolation) {
        this.totalDayIsolation = totalDayIsolation;
    }

    public String getConsolidatedWork() {
        return consolidatedWork;
    }

    public void setConsolidatedWork(String consolidatedWork) {
        this.consolidatedWork = consolidatedWork;
    }

    public String getStatusHealth() {
        return statusHealth;
    }

    public void setStatusHealth(String statusHealth) {
        this.statusHealth = statusHealth;
    }

    public String getSiegeEpimiodological() {
        return siegeEpimiodological;
    }

    public void setSiegeEpimiodological(String siegeEpimiodological) {
        this.siegeEpimiodological = siegeEpimiodological;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(String hospitalization) {
        this.hospitalization = hospitalization;
    }

    public String getDateProofOne() {
        return dateProofOne;
    }

    public void setDateProofOne(String dateProofOne) {
        this.dateProofOne = dateProofOne;
    }

    public String getDateInitiation() {
        return dateInitiation;
    }

    public void setDateInitiation(String dateInitiation) {
        this.dateInitiation = dateInitiation;
    }

    public String getDaysTotal() {
        return daysTotal;
    }

    public void setDaysTotal(String daysTotal) {
        this.daysTotal = daysTotal;
    }

    public String getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(String totalInfo) {
        this.totalInfo = totalInfo;
    }

    public String getStatusWokin() {
        return statusWokin;
    }

    public void setStatusWokin(String statusWokin) {
        this.statusWokin = statusWokin;
    }

    public String getVacunation() {
        return vacunation;
    }

    public void setVacunation(String vacunation) {
        this.vacunation = vacunation;
    }

   
    
    
    
}
