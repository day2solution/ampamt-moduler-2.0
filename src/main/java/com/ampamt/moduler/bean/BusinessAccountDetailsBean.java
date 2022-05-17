package com.ampamt.moduler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="business_account_details")
@JsonInclude(Include.NON_NULL)
public class BusinessAccountDetailsBean {

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="COMPANY_TITLE")
	private String companyTitle;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="COMPANY_OWNER_NAME")
	private String companyOwnerName;
	
	@Column(name="OWNER_GENDER")
	private String ownerGender;
	
	@Column(name="REPRESENTATIVE_NAME")
	private String representativeName;
	
	@Column(name="REPRESENTATIVE_GENDER")
	private String representativeGender;
	
	@Column(name="OWNER_NATIONALITY")
	private String ownerNationality;
	
	@Column(name="OWNER_RELIGION")
	private String ownerReligion;
	
	@Column(name="OWNER_DETAIL")
	private String ownerDetail;
	
	@Column(name="SECTOR")
	private String sector;
	
	@Column(name="EXPERIENC_ESTABLISHMENT")
	private String experiencEstablishment;
	
	@Column(name="REGIST_OFFICE_ADD")
	private String registOfficeAdd;
	
	@Column(name="LANDMARK")
	private String landmark;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="LANDLINE_NO")
	private String landlineNo;
	
	@Column(name="OTHER_CONTACT_NO")
	private String otherContactNo;
	
	@Column(name="WHATSAPP_NO")
	private String whatsappNo;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="OTHER_EMAIL_ID")
	private String otherEmailId;
	
	@Column(name="AADHAR_NO")
	private String aadharNo;
	
	@Column(name="PAN_NO")
	private String panNo;
	
	@Column(name="PASSPORT_NO")
	private String passportNo;
	
	@Column(name="TNC_ACCEPTED")
	private String tncAccepted;
	
	@Column(name="ACTIVE_FLAG")
	private String activeFlag;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
//	@Column(name="IS_ADMIN")
//	private Date isAdmin;
	
	@Column(name="COMPANY_LOGO")
	private String companyLogo;
	
	@Column(name="REPRESENTATIVE_NAME_TITLE")
	private String representativeNameTitle;
	
	@Column(name="OWNER_NAME_TITLE")
	private String ownerNameTitle;
	
	@Column(name="SERVICE_STATES")
	private String serviceStates;
	
	@Column(name="SERVICE_CITIES")
	private String serviceCities;
	
	@Column(name="ADMIN_FLAG")
	private String adminFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyOwnerName() {
		return companyOwnerName;
	}

	public void setCompanyOwnerName(String companyOwnerName) {
		this.companyOwnerName = companyOwnerName;
	}

	public String getOwnerGender() {
		return ownerGender;
	}

	public void setOwnerGender(String ownerGender) {
		this.ownerGender = ownerGender;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getRepresentativeGender() {
		return representativeGender;
	}

	public void setRepresentativeGender(String representativeGender) {
		this.representativeGender = representativeGender;
	}

	

	public String getOwnerNationality() {
		return ownerNationality;
	}

	public void setOwnerNationality(String ownerNationality) {
		this.ownerNationality = ownerNationality;
	}

	public String getOwnerReligion() {
		return ownerReligion;
	}

	public void setOwnerReligion(String ownerReligion) {
		this.ownerReligion = ownerReligion;
	}

	public String getOwnerDetail() {
		return ownerDetail;
	}

	public void setOwnerDetail(String ownerDetail) {
		this.ownerDetail = ownerDetail;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getExperiencEstablishment() {
		return experiencEstablishment;
	}

	public void setExperiencEstablishment(String experiencEstablishment) {
		this.experiencEstablishment = experiencEstablishment;
	}

	public String getRegistOfficeAdd() {
		return registOfficeAdd;
	}

	public void setRegistOfficeAdd(String registOfficeAdd) {
		this.registOfficeAdd = registOfficeAdd;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getOtherContactNo() {
		return otherContactNo;
	}

	public void setOtherContactNo(String otherContactNo) {
		this.otherContactNo = otherContactNo;
	}

	public String getWhatsappNo() {
		return whatsappNo;
	}

	public void setWhatsappNo(String whatsappNo) {
		this.whatsappNo = whatsappNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOtherEmailId() {
		return otherEmailId;
	}

	public void setOtherEmailId(String otherEmailId) {
		this.otherEmailId = otherEmailId;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getTncAccepted() {
		return tncAccepted;
	}

	public void setTncAccepted(String tncAccepted) {
		this.tncAccepted = tncAccepted;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

//	public Date getIsAdmin() {
//		return isAdmin;
//	}
//
//	public void setIsAdmin(Date isAdmin) {
//		this.isAdmin = isAdmin;
//	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getRepresentativeNameTitle() {
		return representativeNameTitle;
	}

	public void setRepresentativeNameTitle(String representativeNameTitle) {
		this.representativeNameTitle = representativeNameTitle;
	}

	public String getOwnerNameTitle() {
		return ownerNameTitle;
	}

	public void setOwnerNameTitle(String ownerNameTitle) {
		this.ownerNameTitle = ownerNameTitle;
	}

	public String getServiceStates() {
		return serviceStates;
	}

	public void setServiceStates(String serviceStates) {
		this.serviceStates = serviceStates;
	}

	public String getServiceCities() {
		return serviceCities;
	}

	public void setServiceCities(String serviceCities) {
		this.serviceCities = serviceCities;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}
	
	
}
