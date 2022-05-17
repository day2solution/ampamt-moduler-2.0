package com.ampamt.moduler.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="doctors_documents_details")
@JsonInclude(Include.NON_NULL)
public class DoctorsDocumentsDetailsBean {

	@Id
	@Column(name="DOCUMENT_ID")
	private String documentId;
	
	@Column(name="ID")
	private String id;
	
	@Column(name="PROFILE_PIC")
	private String profilePic;
	
	@Column(name="AADHAR")
	private String aadhar;
	
	@Column(name="AADHAR_BACK")
	private String aadharBack;
	
	@Column(name="PANBACK")
	private String panBack;
	
	@Column(name="PANFRONT")
	private String panFront;
	
	@Column(name="QUALI_CERTIFICATE1")
	private String qualiCertificate1;
	
	@Column(name="QUALI_CERTIFICATE2")
	private String qualiCertificate2;
	
	@Column(name="QUALI_CERTIFICATE3")
	private String qualiCertificate3;
	
	@Column(name="QUALI_CERTIFICATE4")
	private String qualiCertificate4;
	
	@Column(name="QUALI_CERTIFICATE5")
	private String qualiCertificate5;
	
	@Column(name="QUALI_CERTIFICATE6")
	private String qualiCertificate6;
	
	@Column(name="RAMP_CERTIFICATE")
	private String rampCertificate;
	
	@Column(name="PROF_CERTIFICATE")
	private String profCertificate;
	
	@Column(name="MEDI_COUNCIL_REGISTRATION")
	private String mediCouncilRegistration;
	
	@Column(name="HEALTH_DEP_CERTIFICATE")
	private String healthDepCertificate;
	
	@Column(name="AYUSH_DEP_REGISTRATION")
	private String ayushDepRegistration;
	
	@Column(name="OTHER_LIC_CERTIFICATE1")
	private String otherLicCertificate1;
	
	@Column(name="OTHER_LIC_CERTIFICATE2")
	private String otherLicCertificate2;
	
	@Column(name="OTHER_LIC_CERTIFICATE3")
	private String otherLicCertificate3;
	
	@Column(name="PROFESSIONAL_WORKIMG1")
	private String professionalWorkimg1;
	
	@Column(name="PROFESSIONAL_WORKIMG2")
	private String professionalWorkimg2;
	
	@Column(name="PROFESSIONAL_WORKIMG3")
	private String professionalWorkimg3;
	
	@Column(name="WEBSITE_LINK")
	private String websiteLink;
	
	@Column(name="FACEBOOK_LINK")
	private String facebookLink;
	
	@Column(name="INSTAGRAM_LINK")
	private String instagramLink;
	
	@Column(name="YOUTUBE_LINK")
	private String youtubeLink;
	
	@Column(name="TWITTER_LINK")
	private String twitterLink;
	
	@Column(name="EXPERIENCE_COMMENTS")
	private String experienceComments;
	
	@Column(name="ACHIEVEMENTS_COMMENTS")
	private String achievementsComments;
	
	@Column(name="SIGNATURE_IMG")
	private String signatureImg;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQualiCertificate1() {
		return qualiCertificate1;
	}

	public void setQualiCertificate1(String qualiCertificate1) {
		this.qualiCertificate1 = qualiCertificate1;
	}

	public String getQualiCertificate2() {
		return qualiCertificate2;
	}

	public void setQualiCertificate2(String qualiCertificate2) {
		this.qualiCertificate2 = qualiCertificate2;
	}

	public String getQualiCertificate3() {
		return qualiCertificate3;
	}

	public void setQualiCertificate3(String qualiCertificate3) {
		this.qualiCertificate3 = qualiCertificate3;
	}

	public String getQualiCertificate4() {
		return qualiCertificate4;
	}

	public void setQualiCertificate4(String qualiCertificate4) {
		this.qualiCertificate4 = qualiCertificate4;
	}

	public String getQualiCertificate5() {
		return qualiCertificate5;
	}

	public void setQualiCertificate5(String qualiCertificate5) {
		this.qualiCertificate5 = qualiCertificate5;
	}

	public String getQualiCertificate6() {
		return qualiCertificate6;
	}

	public void setQualiCertificate6(String qualiCertificate6) {
		this.qualiCertificate6 = qualiCertificate6;
	}

	public String getRampCertificate() {
		return rampCertificate;
	}

	public void setRampCertificate(String rampCertificate) {
		this.rampCertificate = rampCertificate;
	}

	public String getProfCertificate() {
		return profCertificate;
	}

	public void setProfCertificate(String profCertificate) {
		this.profCertificate = profCertificate;
	}

	
	public String getMediCouncilRegistration() {
		return mediCouncilRegistration;
	}

	public void setMediCouncilRegistration(String mediCouncilRegistration) {
		this.mediCouncilRegistration = mediCouncilRegistration;
	}

	public String getHealthDepCertificate() {
		return healthDepCertificate;
	}

	public void setHealthDepCertificate(String healthDepCertificate) {
		this.healthDepCertificate = healthDepCertificate;
	}

	public String getAyushDepRegistration() {
		return ayushDepRegistration;
	}

	public void setAyushDepRegistration(String ayushDepRegistration) {
		this.ayushDepRegistration = ayushDepRegistration;
	}

	public String getOtherLicCertificate1() {
		return otherLicCertificate1;
	}

	public void setOtherLicCertificate1(String otherLicCertificate1) {
		this.otherLicCertificate1 = otherLicCertificate1;
	}

	public String getOtherLicCertificate2() {
		return otherLicCertificate2;
	}

	public void setOtherLicCertificate2(String otherLicCertificate2) {
		this.otherLicCertificate2 = otherLicCertificate2;
	}

	public String getOtherLicCertificate3() {
		return otherLicCertificate3;
	}

	public void setOtherLicCertificate3(String otherLicCertificate3) {
		this.otherLicCertificate3 = otherLicCertificate3;
	}

	public String getProfessionalWorkimg1() {
		return professionalWorkimg1;
	}

	public void setProfessionalWorkimg1(String professionalWorkimg1) {
		this.professionalWorkimg1 = professionalWorkimg1;
	}

	public String getProfessionalWorkimg2() {
		return professionalWorkimg2;
	}

	public void setProfessionalWorkimg2(String professionalWorkimg2) {
		this.professionalWorkimg2 = professionalWorkimg2;
	}

	public String getProfessionalWorkimg3() {
		return professionalWorkimg3;
	}

	public void setProfessionalWorkimg3(String professionalWorkimg3) {
		this.professionalWorkimg3 = professionalWorkimg3;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getInstagramLink() {
		return instagramLink;
	}

	public void setInstagramLink(String instagramLink) {
		this.instagramLink = instagramLink;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getTwitterLink() {
		return twitterLink;
	}

	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}

	public String getExperienceComments() {
		return experienceComments;
	}

	public void setExperienceComments(String experienceComments) {
		this.experienceComments = experienceComments;
	}

	public String getAchievementsComments() {
		return achievementsComments;
	}

	public void setAchievementsComments(String achievementsComments) {
		this.achievementsComments = achievementsComments;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getSignatureImg() {
		return signatureImg;
	}

	public void setSignatureImg(String signatureImg) {
		this.signatureImg = signatureImg;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPanBack() {
		return panBack;
	}

	public void setPanBack(String panBack) {
		this.panBack = panBack;
	}

	public String getPanFront() {
		return panFront;
	}

	public void setPanFront(String panFront) {
		this.panFront = panFront;
	}

	public String getAadharBack() {
		return aadharBack;
	}

	public void setAadharBack(String aadharBack) {
		this.aadharBack = aadharBack;
	}
	
	
}
