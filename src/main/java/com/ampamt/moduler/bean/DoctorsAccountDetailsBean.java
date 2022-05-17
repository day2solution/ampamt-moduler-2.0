package com.ampamt.moduler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="doctors_account_details")
@JsonInclude(Include.NON_NULL)
public class DoctorsAccountDetailsBean {

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="BLOOD_GROUP")
	private String bloodGroup;
	
	@Column(name="MARITAL_STATUS")
	private String maritalStatus;
	
	@Column(name="PRACTICING_AS")
	private String practicingAs;
	
	@Column(name="PRACTICING")
	private String practicing;
	
	@Column(name="PRACTICE_CITY")
	private String practiceCity;
	
	@Column(name="NATIONALITY")
	private String nationality;
	
	@Column(name="RELIGION")
	private String religion;
	
	@Column(name="FATHER_NAME")
	private String fatherName;
	
	@Column(name="MOTHER_NAME")
	private String motherName;
	
	@Column(name="WIFE_HUSBAND_NAME")
	private String wifeHusbandName;
	
	@Column(name="COMPANY_TITLE")
	private String companyTitle;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="EXPERIENC_ESTABLISHMENT")
	private String experiencEstablishment;
	
	@Column(name="REGIST_OFFICE_ADD")
	private String registOfficeAdd;
	
	@Column(name="PERMANENT_ADDRESS")
	private String permanentAddress;
	
	@Column(name="CONTACT_NO")
	private String contactNo;
	
	@Column(name="OTHER_CONTACT_NO")
	private String otherContactNo;
	
	@Column(name="WHATSAPP_NO")
	private String whatsappNo;
	
	@Column(name="EMERGENCY_NO")
	private String emergencyNo;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
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
//	private String isAdmin;
	
	@Column(name="PROFILE_PIC")
	private String profilePic;
	
	@Column(name="NAME_TITLE")
	private String nameTitle;
	
	@Column(name="HAVE_RAMP")
	private String haveRamp;
	
	@Column(name="APPLY_RAMP")
	private String applyRamp;
	
	@Column(name="ADMIN_FLAG")
	private String adminFlag;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="COUNTRY")
	private String country;
	
	@Column(name="REFERENCE")
	private String reference;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPracticingAs() {
		return practicingAs;
	}

	public void setPracticingAs(String practicingAs) {
		this.practicingAs = practicingAs;
	}

	public String getPracticing() {
		return practicing;
	}

	public void setPracticing(String practicing) {
		this.practicing = practicing;
	}

	public String getPracticeCity() {
		return practiceCity;
	}

	public void setPracticeCity(String practiceCity) {
		this.practiceCity = practiceCity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getWifeHusbandName() {
		return wifeHusbandName;
	}

	public void setWifeHusbandName(String wifeHusbandName) {
		this.wifeHusbandName = wifeHusbandName;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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

	public String getEmergencyNo() {
		return emergencyNo;
	}

	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getIsAdmin() {
//		return isAdmin;
//	}
//
//	public void setIsAdmin(String isAdmin) {
//		this.isAdmin = isAdmin;
//	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getNameTitle() {
		return nameTitle;
	}

	public void setNameTitle(String nameTitle) {
		this.nameTitle = nameTitle;
	}

	public String getHaveRamp() {
		return haveRamp;
	}

	public void setHaveRamp(String haveRamp) {
		this.haveRamp = haveRamp;
	}

	public String getApplyRamp() {
		return applyRamp;
	}

	public void setApplyRamp(String applyRamp) {
		this.applyRamp = applyRamp;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
