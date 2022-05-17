package com.ampamt.moduler.controller.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ampamt.moduler.bean.AmpamtAccountBean;
import com.ampamt.moduler.bean.AmpamtAuditTrailBean;
import com.ampamt.moduler.bean.AmpamtTherapiesBean;
import com.ampamt.moduler.bean.BusinessAccountDetailsBean;
import com.ampamt.moduler.bean.BusinessDocumentsDetailsBean;
import com.ampamt.moduler.bean.DoctorsAccountDetailsBean;
import com.ampamt.moduler.bean.DoctorsDocumentsDetailsBean;
import com.ampamt.moduler.bean.email.EmailBean;
import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.controller.upload.UploadDocumentsController;
import com.ampamt.moduler.emailtemplates.EmailTemplate;
import com.ampamt.moduler.model.BusinessAccountModel;
import com.ampamt.moduler.model.BusinessImageUploadModel;
import com.ampamt.moduler.model.ChangePasswordModel;
import com.ampamt.moduler.model.DoctorAccountModel;
import com.ampamt.moduler.model.DoctorAccountStatusModel;
import com.ampamt.moduler.model.DoctorsImageUploadModel;
import com.ampamt.moduler.model.DocumentsVerificationModel;
import com.ampamt.moduler.model.PaymentOrderModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.model.UserSearchModel;
import com.ampamt.moduler.service.AccountService;
import com.ampamt.moduler.service.email.EmailService;
import com.ampamt.moduler.service.payment.PaymentService;
import com.ampamt.moduler.service.transaction.TransactionService;
import com.ampamt.moduler.util.CommonUtility;
import com.ampamt.moduler.util.IpUtil;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/account")
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	EmailService emailService; 
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value = "/get-doctor-detail", method = RequestMethod.POST)
	public ResponseEntity<List<DoctorsAccountDetailsBean>> getDoctordetail(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) throws IOException {
		logger.debug("*******************start getDoctordetail***************************");
	
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = accountService.getDoctordetail(doctorsAccountDetailsBean);

		logger.debug("doctorsAccountDetailsList size="+doctorsAccountDetailsList.size());
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList2=null;
		if(doctorsAccountDetailsList.size()>0) {
			doctorsAccountDetailsList2=new ArrayList<DoctorsAccountDetailsBean>();
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			DoctorsAccountDetailsBean doctorsAccountDetailsBean2=new DoctorsAccountDetailsBean();
			String imagePath=environment.getProperty("home.file.path")+"DOCTOR//IMAGES";
			
			for(int i=0;i<doctorsAccountDetailsList.size();i++) {
				imagePath=imagePath+"//"+doctorsAccountDetailsList.get(i).getId();
				doctorsAccountDetailsBean2=doctorsAccountDetailsList.get(i);
				doctorsAccountDetailsBean2.setProfilePic(uploadDocumentsController.getImages(imagePath+"//PROFILE_"+doctorsAccountDetailsList.get(i).getId()+".jpeg"));
				doctorsAccountDetailsList2.add(doctorsAccountDetailsBean2);
				logger.debug("doctorsAccountDetailsList2 size="+doctorsAccountDetailsList2.size());
			}
		}
		logger.debug("*******************end getDoctordetail***************************");

		return new ResponseEntity<>(doctorsAccountDetailsList2, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-doctors-list", method = RequestMethod.POST)
	public ResponseEntity<List<DoctorsAccountDetailsBean>> getDoctorsLists(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("*******************start getDoctorsLists***************************");
	
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = accountService.getDoctorsLists(doctorsAccountDetailsBean);
		accountService.createAuditTrail(IpUtil.getClientIp(), doctorsAccountDetailsBean.getId(), "get-doctors-list", "get-doctors-list");
		logger.debug("*******************end getDoctorsLists***************************");
		return new ResponseEntity<>(doctorsAccountDetailsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-therapy-list", method = RequestMethod.POST)
	public ResponseEntity<List<AmpamtTherapiesBean>> getTherapyLists(@RequestBody AmpamtTherapiesBean ampamtTherapiesBean) {
		logger.debug("*******************start getTherapyLists***************************");
	
		List<AmpamtTherapiesBean> ampamtTherapiesBeanList = accountService.getTherapyLists(ampamtTherapiesBean);

		logger.debug("*******************end getTherapyLists***************************");
		return new ResponseEntity<>(ampamtTherapiesBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-id", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createAccountId(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start createDoctAccount***************************");
	
		SuccessModel successModel = accountService.createAccountId(ampamtAccountBean);

		logger.debug("*******************end createDoctAccount***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:8100")
	@RequestMapping(value = "/doctor-login", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> doctorLogin(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start doctorLogin***************************");
	
		SuccessModel scuccessModel = accountService.doctorLogin(ampamtAccountBean);
		
		
		if(scuccessModel.getDocumentsVerificationModelList()!=null 
				&& String.valueOf(scuccessModel.getDocumentsVerificationModelList().get(0).getAllDocUploaded()).equalsIgnoreCase(ApplicationConstant.FLAG_Y)
				&& Double.valueOf(scuccessModel.getDocumentsVerificationModelList().get(0).getAvailableAmount())>0) {
			
		
		if(scuccessModel!=null && scuccessModel.getActiveFlag()!=null && scuccessModel.getActiveFlag().equalsIgnoreCase(ApplicationConstant.FLAG_N)) {
			logger.debug("doctor sending otp");
			String otp=CommonUtility.generateOTP();
			AmpamtAccountBean ampamtAccountBean2=new AmpamtAccountBean();
			ampamtAccountBean2.setOtp(otp);
			ampamtAccountBean2.setAccountType(scuccessModel.getAccountType());
			ampamtAccountBean2.setId(scuccessModel.getId());
			accountService.setOTP(ampamtAccountBean2);
			DoctorsAccountDetailsBean doctorsAccountDetailsBean=new DoctorsAccountDetailsBean();
			doctorsAccountDetailsBean.setId(scuccessModel.getId());
			scuccessModel.setStatus(ApplicationConstant.STATUS_FAILED);
			List<DoctorsAccountDetailsBean> doctorsAccountDetailsBeanList=accountService.getDoctordetail(doctorsAccountDetailsBean);
			if(doctorsAccountDetailsBeanList.size()>0) {
				doctorsAccountDetailsBean=doctorsAccountDetailsBeanList.get(0);
				EmailBean emailBean=new EmailBean();
				EmailTemplate emailTemplate=new EmailTemplate();
				
				logger.debug("doctor otp="+otp);
				emailBean.setEmailBody(emailTemplate.getEmailverificationTemplate(doctorsAccountDetailsBean.getFirstName(),otp));
				emailBean.setFrom("ampamt.otp@gmail.com");
				emailBean.setTo(doctorsAccountDetailsBean.getEmailId());
				emailBean.setSubject("Email address verfication");
				
				scuccessModel.setStatus(emailService.sendOTPEmail(emailBean));
				scuccessModel.setOtp(otp);
				scuccessModel.setMsg("OTP Verification");
				scuccessModel.setStatusType(ApplicationConstant.OTP);
				scuccessModel.setId(doctorsAccountDetailsBean.getId());
			}
			
		}
	}
		
		
		logger.debug("*******************end doctorLogin***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/business-login", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> businessLogin(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start businessLogin***************************");
	
		SuccessModel scuccessModel = accountService.businessLogin(ampamtAccountBean);
		if(scuccessModel.getDocumentsVerificationModelList()!=null 
				&& String.valueOf(scuccessModel.getDocumentsVerificationModelList().get(0).getAllDocUploaded()).equalsIgnoreCase(ApplicationConstant.FLAG_Y))
		{
		if(scuccessModel!=null && scuccessModel.getActiveFlag()!=null && scuccessModel.getActiveFlag().equalsIgnoreCase(ApplicationConstant.FLAG_N)) {
			logger.debug("business sending otp");
			String otp=CommonUtility.generateOTP();
			AmpamtAccountBean ampamtAccountBean2=new AmpamtAccountBean();
			ampamtAccountBean2.setOtp(otp);
			ampamtAccountBean2.setAccountType(scuccessModel.getAccountType());
			ampamtAccountBean2.setId(scuccessModel.getId());
			accountService.setOTP(ampamtAccountBean2);
			BusinessAccountDetailsBean businessAccountDetailsBean=new BusinessAccountDetailsBean();
			businessAccountDetailsBean.setId(scuccessModel.getId());
			scuccessModel.setStatus(ApplicationConstant.STATUS_FAILED);
			List<BusinessAccountDetailsBean> businessAccountDetailsBeanList=accountService.getBusinessAccdetail(businessAccountDetailsBean);
			if(businessAccountDetailsBeanList.size()>0) {
				businessAccountDetailsBean=businessAccountDetailsBeanList.get(0);
				EmailBean emailBean=new EmailBean();
				EmailTemplate emailTemplate=new EmailTemplate();
			
				logger.debug("business otp="+otp);
				emailBean.setEmailBody(emailTemplate.getEmailverificationTemplate(businessAccountDetailsBean.getCompanyName(),otp));
				emailBean.setFrom("ampamt.otp@gmail.com");
				emailBean.setTo(businessAccountDetailsBean.getEmailId());
				emailBean.setSubject("Email address verfication");
				scuccessModel.setStatus(emailService.sendOTPEmail(emailBean));
				scuccessModel.setOtp(otp);
				scuccessModel.setMsg("OTP Verification");
				scuccessModel.setStatusType(ApplicationConstant.OTP);
			}
			
		}
		}
		accountService.createAuditTrail(IpUtil.getClientIp(), scuccessModel.getId(), "business-login with status "+scuccessModel.getStatus()+" email-id>"+ampamtAccountBean.getEmailId()+" app_version="+ampamtAccountBean.getStatus(), "business-login");
		logger.debug("*******************end businessLogin***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/check-ampamt-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> checkAmpamtAccount(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start checkAmpamtAccount***************************");
	
		SuccessModel scuccessModel = accountService.checkAmpamtAccount(ampamtAccountBean);
		
		logger.debug("*******************end checkAmpamtAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-current-password", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> changeCurrentPassword(@RequestBody ChangePasswordModel changePasswordModel) {
		logger.debug("*******************start changePassword***************************");
	
		SuccessModel scuccessModel = accountService.changeCurrentPassword(changePasswordModel);

		logger.debug("*******************end changePassword***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> changePassword(@RequestBody ChangePasswordModel changePasswordModel) {
		logger.debug("*******************start changePassword***************************");
	
		SuccessModel scuccessModel = accountService.changePassword(changePasswordModel);

		logger.debug("*******************end changePassword***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-doctor-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createDoctorAccount(@RequestBody DoctorAccountModel doctorAccountModel) {
		logger.debug("*******************start createDoctAccount***************************");
	
		SuccessModel scuccessModel = accountService.createDoctorAccount(doctorAccountModel);
		accountService.createAuditTrail(IpUtil.getClientIp(), doctorAccountModel.getEmailId(), "create-doctor-account", "create-doctor-account");
		logger.debug("*******************end createDoctAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-business-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createBusinessAccount(@RequestBody BusinessAccountModel businessAccountModel) {
		logger.debug("*******************start createBusinessAccount***************************");
	
		SuccessModel scuccessModel = accountService.createBusinessAccount(businessAccountModel);
		accountService.createAuditTrail(IpUtil.getClientIp(), businessAccountModel.getEmailId(), "create-business-account", "create-business-account");
		logger.debug("*******************end createBusinessAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-doct-account-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createDoctorsAccountDetails(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("*******************start createAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.createDoctorsAccountDetails(doctorsAccountDetailsBean);
		accountService.createAuditTrail(IpUtil.getClientIp(), doctorsAccountDetailsBean.getEmailId(), "create-doct-account-details", "create-doct-account-details");
		logger.debug("*******************end createAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-doctors-documents", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createDoctorsDocumentsDetails(@RequestBody DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		logger.debug("*******************start createDoctorsDocumentsDetails***************************");
	
		SuccessModel scuccessModel = accountService.createDoctorsDocumentsDetails(doctorsDocumentsDetailsBean);
		accountService.createAuditTrail(IpUtil.getClientIp(), doctorsDocumentsDetailsBean.getId(), "create-doctors-documents", "create-doctors-documents");
		logger.debug("*******************end createDoctorsDocumentsDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-doc-document-detail", method = RequestMethod.POST)
	public ResponseEntity<List<DoctorsImageUploadModel>> getDocumentdetail(@RequestBody DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) throws IOException {
		logger.debug("*******************start getDocumentdetail***************************");
	
		List<DoctorsDocumentsDetailsBean> doctorsDocumentsDetailsList = accountService.getDocumentdetail(doctorsDocumentsDetailsBean);
		List<DoctorsImageUploadModel> doctorsImageUploadModelList=new ArrayList<DoctorsImageUploadModel>();
		
		if(doctorsDocumentsDetailsList.size()>0) {
			DoctorsImageUploadModel doctorsImageUploadModel=new DoctorsImageUploadModel();
			
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			String imagePath=environment.getProperty("home.file.path")+"DOCTOR//IMAGES";
			for(int i=0;i<doctorsDocumentsDetailsList.size();i++) {
				doctorsImageUploadModel=new DoctorsImageUploadModel();
				imagePath=imagePath+"//"+doctorsDocumentsDetailsList.get(i).getId();
				
				doctorsImageUploadModel.setId(doctorsDocumentsDetailsList.get(i).getId());
				doctorsImageUploadModel.setAyushDepRegistration(doctorsDocumentsDetailsList.get(i).getAyushDepRegistration());
				doctorsImageUploadModel.setHealthDepCertificate(doctorsDocumentsDetailsList.get(i).getHealthDepCertificate());
				doctorsImageUploadModel.setOtherLicCertificate1(doctorsDocumentsDetailsList.get(i).getOtherLicCertificate1());
				doctorsImageUploadModel.setOtherLicCertificate2(doctorsDocumentsDetailsList.get(i).getOtherLicCertificate2());
				doctorsImageUploadModel.setOtherLicCertificate3(doctorsDocumentsDetailsList.get(i).getOtherLicCertificate3());
				doctorsImageUploadModel.setProfCertificate(doctorsDocumentsDetailsList.get(i).getProfCertificate());
				doctorsImageUploadModel.setProfessionalWorkimg1(doctorsDocumentsDetailsList.get(i).getProfessionalWorkimg1());
				doctorsImageUploadModel.setProfessionalWorkimg2(doctorsDocumentsDetailsList.get(i).getProfessionalWorkimg2());
				doctorsImageUploadModel.setProfessionalWorkimg3(doctorsDocumentsDetailsList.get(i).getProfessionalWorkimg3());
				doctorsImageUploadModel.setProfilePic(doctorsDocumentsDetailsList.get(i).getProfilePic());
				doctorsImageUploadModel.setAadhar(doctorsDocumentsDetailsList.get(i).getAadhar());
				doctorsImageUploadModel.setAadharBack(doctorsDocumentsDetailsList.get(i).getAadharBack());
				doctorsImageUploadModel.setPanBack(doctorsDocumentsDetailsList.get(i).getPanBack());
				doctorsImageUploadModel.setPanFront(doctorsDocumentsDetailsList.get(i).getPanFront());
				doctorsImageUploadModel.setQualiCertificate1(doctorsDocumentsDetailsList.get(i).getQualiCertificate1());
				doctorsImageUploadModel.setQualiCertificate2(doctorsDocumentsDetailsList.get(i).getQualiCertificate2());
				doctorsImageUploadModel.setQualiCertificate3(doctorsDocumentsDetailsList.get(i).getQualiCertificate3());
				doctorsImageUploadModel.setQualiCertificate4(doctorsDocumentsDetailsList.get(i).getQualiCertificate4());
				doctorsImageUploadModel.setQualiCertificate5(doctorsDocumentsDetailsList.get(i).getQualiCertificate5());
				doctorsImageUploadModel.setQualiCertificate6(doctorsDocumentsDetailsList.get(i).getQualiCertificate6());
				doctorsImageUploadModel.setRampCertificate(doctorsDocumentsDetailsList.get(i).getRampCertificate());
				doctorsImageUploadModel.setSignatureImg(doctorsDocumentsDetailsList.get(i).getSignatureImg());
				doctorsImageUploadModel.setWebsiteLink(doctorsDocumentsDetailsList.get(i).getWebsiteLink());
				doctorsImageUploadModel.setYoutubeLink(doctorsDocumentsDetailsList.get(i).getYoutubeLink());
				doctorsImageUploadModel.setInstagramLink(doctorsDocumentsDetailsList.get(i).getInstagramLink());
				doctorsImageUploadModel.setTwitterLink(doctorsDocumentsDetailsList.get(i).getTwitterLink());
				doctorsImageUploadModel.setFacebookLink(doctorsDocumentsDetailsList.get(i).getFacebookLink());
				doctorsImageUploadModel.setAchievementsComments(doctorsDocumentsDetailsList.get(i).getAchievementsComments());
				doctorsImageUploadModel.setExperienceComments(doctorsDocumentsDetailsList.get(i).getExperienceComments());
				doctorsImageUploadModel.setMediCouncilRegistration(doctorsDocumentsDetailsList.get(i).getMediCouncilRegistration());
				
				doctorsImageUploadModel.setProfilePicB64Img(uploadDocumentsController.getImages(imagePath+"//PROFILE_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setAyushDepRegistrationB64Img(uploadDocumentsController.getImages(imagePath+"//AYUSHDEP_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setHealthDepCertificateB64Img(uploadDocumentsController.getImages(imagePath+"//HEALTHDEP_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				
				doctorsImageUploadModel.setQualiCertificate1B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION1_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setQualiCertificate2B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION2_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setQualiCertificate3B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION3_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setQualiCertificate4B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION4_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setQualiCertificate5B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION5_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setQualiCertificate6B64Img(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION6_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				
				doctorsImageUploadModel.setRampCertificateB64Img(uploadDocumentsController.getImages(imagePath+"//RAMP_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setProfCertificateB64Img(uploadDocumentsController.getImages(imagePath+"//PROFCERT_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setMedicalCouncilB64Img(uploadDocumentsController.getImages(imagePath+"//MEDICOUNCIL_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				
				doctorsImageUploadModel.setOtherLicCertificate1B64Img(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT1_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setOtherLicCertificate2B64Img(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT2_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setOtherLicCertificate3B64Img(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT3_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				
				doctorsImageUploadModel.setProfessionalWorkimg1B64Img(uploadDocumentsController.getImages(imagePath+"//PROFWORK1_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setProfessionalWorkimg2B64Img(uploadDocumentsController.getImages(imagePath+"//PROFWORK2_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setProfessionalWorkimg3B64Img(uploadDocumentsController.getImages(imagePath+"//PROFWORK3_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				
				doctorsImageUploadModel.setSignatureB64Img(uploadDocumentsController.getImages(imagePath+"//SIGNATURE_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setAadharImgBase64(uploadDocumentsController.getImages(imagePath+"//AADHAR_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setAadharBackImgBase64(uploadDocumentsController.getImages(imagePath+"//AADHARBACK_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setPanFrontImgBase64(uploadDocumentsController.getImages(imagePath+"//PANFRONT_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModel.setPanBackImgBase64(uploadDocumentsController.getImages(imagePath+"//PANBACK_"+doctorsDocumentsDetailsList.get(i).getId()+".jpeg"));
				doctorsImageUploadModelList.add(doctorsImageUploadModel);
			}
		}

		logger.debug("*******************end getDocumentdetail***************************");

		return new ResponseEntity<>(doctorsImageUploadModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-doctor-document", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateDoctorDocuments(@RequestBody DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		logger.debug("*******************start updateDoctorDocuments***************************");
	
		SuccessModel scuccessModel = accountService.updateDoctorDocuments(doctorsDocumentsDetailsBean);

		logger.debug("*******************end updateDoctorDocuments***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-doctors-acc-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateDoctorAccountDetails(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("*******************start updateAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.updateDoctorAccountDetails(doctorsAccountDetailsBean);

		logger.debug("*******************end updateAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete-doctors-acc-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> deleteDoctorAccountDetails(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("*******************start deleteAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.deleteDoctorAccountDetails(doctorsAccountDetailsBean);

		logger.debug("*******************end deleteAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/create-business-account-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createBusinessAccountDetails(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start createBusinessAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.createBusinessAccountDetails(businessAccountDetailsBean);

		logger.debug("*******************end createBusinessAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-business-documents", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createBusinessDocumentsDetails(@RequestBody BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		logger.debug("*******************start createBusinessDocumentsDetails***************************");
	
		SuccessModel scuccessModel = accountService.createBusinessDocumentsDetails(businessDocumentsDetailsBean);

		logger.debug("*******************end createBusinessDocumentsDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-busi-document-detail", method = RequestMethod.POST)
	public ResponseEntity<List<BusinessImageUploadModel>> getDocumentdetail(@RequestBody BusinessDocumentsDetailsBean businessDocumentsDetailsBean) throws IOException {
		logger.debug("*******************start getDocumentdetail***************************");
	
		List<BusinessDocumentsDetailsBean> businessDocumentsDetailsBeanList = accountService.getBusinessDocumentdetail(businessDocumentsDetailsBean);

		BusinessImageUploadModel businessImageUploadModel=new BusinessImageUploadModel();
		List<BusinessImageUploadModel> businessImageUploadModelList=new ArrayList<BusinessImageUploadModel>();
		if(businessDocumentsDetailsBeanList.size()>0) {
			
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			String imagePath=environment.getProperty("home.file.path")+"BUSINESS//IMAGES";
			for(int i=0;i<businessDocumentsDetailsBeanList.size();i++) {
				businessImageUploadModel=new BusinessImageUploadModel();
				
				imagePath=imagePath+"//"+businessDocumentsDetailsBeanList.get(i).getId();
				businessImageUploadModel.setUserId(businessDocumentsDetailsBeanList.get(i).getId());
				businessImageUploadModel.setAboutCompany(businessDocumentsDetailsBeanList.get(i).getAboutCompany());
				businessImageUploadModel.setCompanyAadhar(businessDocumentsDetailsBeanList.get(i).getCompanyAadhar());
				businessImageUploadModel.setCompanyLogo(businessDocumentsDetailsBeanList.get(i).getCompanyLogo());
				businessImageUploadModel.setCompanyPan(businessDocumentsDetailsBeanList.get(i).getCompanyPan());
				businessImageUploadModel.setCompanyAadhar(businessDocumentsDetailsBeanList.get(i).getCompanyAadhar());
				businessImageUploadModel.setComplianceCertificate(businessDocumentsDetailsBeanList.get(i).getComplianceCertificate());
				businessImageUploadModel.setDetailAboutProduct(businessDocumentsDetailsBeanList.get(i).getDetailAboutProduct());
				businessImageUploadModel.setDocumentId(businessDocumentsDetailsBeanList.get(i).getDocumentId());
				businessImageUploadModel.setFacebookLink(businessDocumentsDetailsBeanList.get(i).getFacebookLink());
				businessImageUploadModel.setGstCertificate(businessDocumentsDetailsBeanList.get(i).getGstCertificate());
				businessImageUploadModel.setGumastaCertificate(businessDocumentsDetailsBeanList.get(i).getGumastaCertificate());
				businessImageUploadModel.setId(businessDocumentsDetailsBeanList.get(i).getId());
				businessImageUploadModel.setInstagramLink(businessDocumentsDetailsBeanList.get(i).getInstagramLink());
				businessImageUploadModel.setIsoCertificate(businessDocumentsDetailsBeanList.get(i).getIsoCertificate());
				businessImageUploadModel.setMsmeCertificate(businessDocumentsDetailsBeanList.get(i).getMsmeCertificate());
				businessImageUploadModel.setOtherLicCertificate1(businessDocumentsDetailsBeanList.get(i).getOtherLicCertificate1());
				businessImageUploadModel.setOtherLicCertificate2(businessDocumentsDetailsBeanList.get(i).getOtherLicCertificate2());
				businessImageUploadModel.setOtherLicCertificate3(businessDocumentsDetailsBeanList.get(i).getOtherLicCertificate3());
				businessImageUploadModel.setProfCertificate(businessDocumentsDetailsBeanList.get(i).getProfCertificate());
				businessImageUploadModel.setProfessionalWorkimg1(businessDocumentsDetailsBeanList.get(i).getProfessionalWorkimg1());
				businessImageUploadModel.setProfessionalWorkimg2(businessDocumentsDetailsBeanList.get(i).getProfessionalWorkimg2());
				businessImageUploadModel.setProfessionalWorkimg3(businessDocumentsDetailsBeanList.get(i).getProfessionalWorkimg3());
				businessImageUploadModel.setQualiCertificate1(businessDocumentsDetailsBeanList.get(i).getQualiCertificate1());
				businessImageUploadModel.setQualiCertificate2(businessDocumentsDetailsBeanList.get(i).getQualiCertificate2());
				businessImageUploadModel.setQualiCertificate3(businessDocumentsDetailsBeanList.get(i).getQualiCertificate3());
				businessImageUploadModel.setQualiCertificate4(businessDocumentsDetailsBeanList.get(i).getQualiCertificate4());
				businessImageUploadModel.setQualiCertificate5(businessDocumentsDetailsBeanList.get(i).getQualiCertificate5());
				businessImageUploadModel.setQualiCertificate6(businessDocumentsDetailsBeanList.get(i).getQualiCertificate6());
				businessImageUploadModel.setWebsiteLink(businessDocumentsDetailsBeanList.get(i).getWebsiteLink());
				businessImageUploadModel.setTwitterLink(businessDocumentsDetailsBeanList.get(i).getTwitterLink());
				businessImageUploadModel.setYoutubeLink(businessDocumentsDetailsBeanList.get(i).getYoutubeLink());
				businessImageUploadModel.setInstagramLink(businessDocumentsDetailsBeanList.get(i).getInstagramLink());

				businessImageUploadModel.setCompanyLogoBase64(uploadDocumentsController.getImages(imagePath+"//COMPLOGO_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setMsmeCertificateBase64(uploadDocumentsController.getImages(imagePath+"//MSME_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setCompanyPanBase64(uploadDocumentsController.getImages(imagePath+"//COMPPAN_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setCompanyAadharBase64(uploadDocumentsController.getImages(imagePath+"//COMPAADHAR_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setGumastaCertificateBase64(uploadDocumentsController.getImages(imagePath+"//GUMASTACERT_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setIsoCertificateBase64(uploadDocumentsController.getImages(imagePath+"//ISOCERT_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setGstCertificateBase64(uploadDocumentsController.getImages(imagePath+"//GSTCERT_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setComplianceCertificateBase64(uploadDocumentsController.getImages(imagePath+"//COMPLIANCECERT_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				
				businessImageUploadModel.setQualiCertificate1Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION1_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setQualiCertificate2Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION2_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setQualiCertificate3Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION3_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setQualiCertificate4Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION4_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setQualiCertificate5Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION5_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setQualiCertificate6Base64(uploadDocumentsController.getImages(imagePath+"//QUALIFICATION6_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));

				businessImageUploadModel.setProfCertificateBase64(uploadDocumentsController.getImages(imagePath+"//PROFCERT_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				
				businessImageUploadModel.setProfessionalWorkimg1Base64(uploadDocumentsController.getImages(imagePath+"//PROFWORK1_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setProfessionalWorkimg2Base64(uploadDocumentsController.getImages(imagePath+"//PROFWORK2_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setProfessionalWorkimg3Base64(uploadDocumentsController.getImages(imagePath+"//PROFWORK3_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				
				businessImageUploadModel.setOtherLicCertificate1Base64(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT1_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setOtherLicCertificate2Base64(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT2_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				businessImageUploadModel.setOtherLicCertificate3Base64(uploadDocumentsController.getImages(imagePath+"//OTHERLICCERT3_"+businessDocumentsDetailsBeanList.get(i).getId()+".jpeg"));
				
				businessImageUploadModelList.add(businessImageUploadModel);
			}
		}
		logger.debug("*******************end getDocumentdetail***************************");

		return new ResponseEntity<>(businessImageUploadModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-business-document", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateBusinessDocuments(@RequestBody BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		logger.debug("*******************start updateBusinessDocuments***************************");
	
		SuccessModel scuccessModel = accountService.updateBusinessDocuments(businessDocumentsDetailsBean);

		logger.debug("*******************end updateBusinessDocuments***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-business-acc-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateBusinessAccountDetails(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start updateBusinessAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.updateBusinessAccountDetails(businessAccountDetailsBean);

		logger.debug("*******************end updateBusinessAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-ampamt-acc", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateAmpamtAccount(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start updateAmpamtAccount***************************");
	
		SuccessModel scuccessModel = accountService.updateAmpamtAccount(ampamtAccountBean);

		logger.debug("*******************end updateAmpamtAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete-business-acc-details", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> deleteBusinessAccountDetails(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start deleteBusinessAccountDetails***************************");
	
		SuccessModel scuccessModel = accountService.deleteBusinessAccountDetails(businessAccountDetailsBean);

		logger.debug("*******************end deleteBusinessAccountDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-business-acc-detail", method = RequestMethod.POST)
	public ResponseEntity<List<BusinessAccountDetailsBean>> getBusinessAccdetail(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) throws IOException {
		logger.debug("*******************start getBusinessAccdetail***************************");
	
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountService.getBusinessAccdetail(businessAccountDetailsBean);
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList2=new ArrayList<BusinessAccountDetailsBean>();
		BusinessAccountDetailsBean businessAccountDetailsBean2=new BusinessAccountDetailsBean();
		if(businessAccountDetailsBeanList.size()>0) {
			String imagePath=environment.getProperty("home.file.path")+"BUSINESS//IMAGES";
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			for(int i=0;i<businessAccountDetailsBeanList.size();i++) {
				imagePath=imagePath+"//"+businessAccountDetailsBeanList.get(i).getId();
				businessAccountDetailsBean2=new BusinessAccountDetailsBean();
				businessAccountDetailsBean2=businessAccountDetailsBeanList.get(i);
				businessAccountDetailsBean2.setCompanyLogo(uploadDocumentsController.getImages(imagePath+"//COMPLOGO_"+businessAccountDetailsBeanList.get(i).getId()+".jpeg"));
				businessAccountDetailsBeanList2.add(businessAccountDetailsBean2);
			}
		}
		logger.debug("*******************end getBusinessAccdetail***************************");

		return new ResponseEntity<>(businessAccountDetailsBeanList2, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-business-list", method = RequestMethod.POST)
	public ResponseEntity<List<BusinessAccountDetailsBean>> getBusinessAccLists(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start getBusinessAccLists***************************");
	
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountService.getBusinessAccLists(businessAccountDetailsBean);

		accountService.createAuditTrail(IpUtil.getClientIp(), businessAccountDetailsBean.getId(), "get-business-list", "get-business-list");
		logger.debug("*******************end getBusinessAccLists***************************");
		return new ResponseEntity<>(businessAccountDetailsBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/set-otp", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> setOTP(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start setOTP***************************");
	
		SuccessModel scuccessModel = accountService.setOTP(ampamtAccountBean);

		logger.debug("*******************end setOTP***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/verify-otp", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> verifyOtp(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start verifyOtp***************************");
	
		SuccessModel scuccessModel = accountService.verifyOtp(ampamtAccountBean);

		logger.debug("*******************end verifyOtp***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/verify-change-password-otp", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> verifyChangePasswordOtp(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start verifyOtp***************************");
	
		SuccessModel scuccessModel = accountService.verifyChangePasswordOtp(ampamtAccountBean);

		logger.debug("*******************end verifyOtp***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/approve-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> approveAccount(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start approveAccount***************************");
	
		SuccessModel scuccessModel = accountService.approveAccount(ampamtAccountBean);

		logger.debug("*******************end approveAccount***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/approve-doc-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> approveDoctorAccount(@RequestBody DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("*******************start approveDoctorAccount***************************");
	
		SuccessModel scuccessModel = accountService.approveDoctorAccount(doctorsAccountDetailsBean);

		logger.debug("*******************end approveDoctorAccount***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/approve-business-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> approveBusinessAccount(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start approveBusinessAccount***************************");
	
		SuccessModel scuccessModel = accountService.approveBusinessAccount(businessAccountDetailsBean);

		logger.debug("*******************end approveBusinessAccount***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-totalusers-count", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> getTotalUsers(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start getTotalUsers***************************");
	
		SuccessModel scuccessModel = accountService.getTotalUsers(ampamtAccountBean);
		scuccessModel.setMsgTitle("App Support");
		scuccessModel.setMsg("We are upgrading to new experience and enhancement please uninstall this app and install our new app by clicking on INSTALL button");
		logger.debug("*******************end getTotalUsers***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search-service-providers", method = RequestMethod.POST)
	public ResponseEntity<List<BusinessAccountDetailsBean>> searchServiceProviders(@RequestBody BusinessAccountDetailsBean businessAccountDetailsBean) {
		logger.debug("*******************start searchServiceProviders***************************");
	
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountService.searchServiceProviders(businessAccountDetailsBean);

		logger.debug("*******************end searchServiceProviders***************************");
		return new ResponseEntity<>(businessAccountDetailsBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search-users", method = RequestMethod.POST)
	public ResponseEntity<List<UserSearchModel>> searchUsers(@RequestBody UserSearchModel userSearchModel) {
		logger.debug("*******************start searchUsers***************************");
	
		List<UserSearchModel> userSearchModelList = accountService.searchUsers(userSearchModel);

		logger.debug("*******************end searchUsers***************************");
		return new ResponseEntity<>(userSearchModelList, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/excel-user-download", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> userExcelDownload(@RequestBody UserSearchModel userSearchModel) {
		logger.debug("*******************start userExcelDownload***************************");
	
		SuccessModel successModel = accountService.userExcelDownload(userSearchModel);
		accountService.createAuditTrail(IpUtil.getClientIp(), successModel.getAccountType(), "excel-user-download with status"+successModel.getStatus(), "excel-download");
		logger.debug("*******************end userExcelDownload***************************");
		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/excel-doctor-download", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> userDoctorExcelDownload(@RequestBody UserSearchModel userSearchModel) {
		logger.debug("*******************start userDoctorExcelDownload***************************");
	
		SuccessModel successModel = accountService.userDoctorExcelDownload(userSearchModel);

		logger.debug("*******************end userDoctorExcelDownload***************************");
		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/excel-business-download", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> userBusinessExcelDownload(@RequestBody UserSearchModel userSearchModel) {
		logger.debug("*******************start userBusinessExcelDownload***************************");
	
		SuccessModel successModel = accountService.userBusinessExcelDownload(userSearchModel);
	
		logger.debug("*******************end userBusinessExcelDownload***************************");
		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-documentVerification-details", method = RequestMethod.POST)
	public ResponseEntity<List<DocumentsVerificationModel>> documentsVerificationDetails(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start documentsVerificationDetails***************************");
	
		List<DocumentsVerificationModel> documentsVerificationModelList = accountService.documentsVerificationDetails(ampamtAccountBean);
		if(documentsVerificationModelList.size()>0) {
			double availableAmount=Double.parseDouble(documentsVerificationModelList.get(0).getAvailableAmount());
			if(availableAmount<=0) {
				logger.debug("call razorpay api");
				AmpamtTransactionBean ampamtTransactionBean=new AmpamtTransactionBean();
				ampamtTransactionBean.setAccountId(ampamtAccountBean.getId());
				List<AmpamtTransactionBean>  ampamtTransactionBeanList=transactionService.getTransactionDetail(ampamtTransactionBean);
				if(ampamtTransactionBeanList.size()>0) {
					SuccessModel successModel=new SuccessModel();
					PaymentOrderModel paymentOrderModel=new PaymentOrderModel();
					for(int i=0;i<ampamtTransactionBeanList.size();i++) {
						ampamtTransactionBean=new AmpamtTransactionBean();
						ampamtTransactionBean=ampamtTransactionBeanList.get(i);
						paymentOrderModel=paymentService.getOrderDetails(ampamtTransactionBean);
						logger.debug("running for i="+i);
						logger.debug("ampamtTransactionBean AccountId="+ampamtTransactionBean.getAccountId());
						logger.debug("ampamtTransactionBean TransactionId="+ampamtTransactionBean.getTransactionId());
						logger.debug("paymentOrderModel Status="+paymentOrderModel.getStatus());
						logger.debug("paymentOrderModel OrderId="+paymentOrderModel.getOrderId());
						
						if(paymentOrderModel!=null && paymentOrderModel.getStatus()!=null && 
								paymentOrderModel.getStatus().trim().toUpperCase().equalsIgnoreCase(ApplicationConstant.STATUS_PAID)) {
							
							ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_Y);
							ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_SUCCESS);
							ampamtTransactionBean.setUpdateDate(new Date());
							ampamtTransactionBean.setRemarks("updated by API CALL");
							successModel=transactionService.updateTransaction(ampamtTransactionBean);
							if(successModel!=null && successModel.getStatus()!=null 
									&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
								documentsVerificationModelList = accountService.documentsVerificationDetails(ampamtAccountBean);
							}
							break;
						}
						
					}
				}else {
					logger.debug("no payment request found");
				}
				
				
			}else {
				logger.debug("not needed to call razorpay api");
			}
		}

		logger.debug("*******************end documentsVerificationDetails***************************");
		return new ResponseEntity<>(documentsVerificationModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-ampamt-account-detail", method = RequestMethod.POST)
	public ResponseEntity<List<AmpamtAccountBean>> getAmpamtAccountDetail(@RequestBody AmpamtAccountBean ampamtAccountBean) {
		logger.debug("*******************start getAmpamtAccountDetail***************************");
	
		List<AmpamtAccountBean> ampamtAccountBeanList = accountService.getAmpamtAccountDetail(ampamtAccountBean);

		logger.debug("*******************end getAmpamtAccountDetail***************************");
		return new ResponseEntity<>(ampamtAccountBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-doctor-acc-list-status", method = RequestMethod.POST)
	public ResponseEntity<List<DoctorAccountStatusModel>> getDoctorsAccountListByStatus(@RequestBody DoctorAccountStatusModel doctorAccountStatusModel) {
		logger.debug("*******************start getDoctorsAccountListByStatus***************************");
	
		List<DoctorAccountStatusModel> doctorAccountStatusModelList = accountService.getDoctorsAccountListByStatus(doctorAccountStatusModel);
		accountService.createAuditTrail(IpUtil.getClientIp(), doctorAccountStatusModel.getId(), "get-doctor-acc-list-status", "get-doctor-acc-list-status");
		logger.debug("*******************end getDoctorsAccountListByStatus***************************");
		return new ResponseEntity<>(doctorAccountStatusModelList, HttpStatus.OK);
	}
	
	public void createAuditTrailByBean(String userId,String module,String appVersion,String action) {
		AmpamtAuditTrailBean ampamtAuditTrailBean=new AmpamtAuditTrailBean();
//		accountService.createAuditTrail(IpUtil.getClientIp(), scuccessModel.getId(), "doctor-login with status "+scuccessModel.getStatus()+" email-id>"+scuccessModel.getEmailId()+" app_version="+ampamtAccountBean.getStatus(), "doctor-login");
		ampamtAuditTrailBean.setAccountId(userId);
		ampamtAuditTrailBean.setModule(module);
		ampamtAuditTrailBean.setClientip(IpUtil.getClientIp());
		ampamtAuditTrailBean.setServerIp("192.168.0.151");
		ampamtAuditTrailBean.setAuditDate(new Date());
		ampamtAuditTrailBean.setAppVersion(appVersion);
		ampamtAuditTrailBean.setAction(action);
		accountService.createAuditTrailByBean(ampamtAuditTrailBean);
	}
}
