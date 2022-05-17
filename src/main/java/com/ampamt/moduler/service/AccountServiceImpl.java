package com.ampamt.moduler.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.AmpamtAccountBean;
import com.ampamt.moduler.bean.AmpamtAuditTrailBean;
import com.ampamt.moduler.bean.AmpamtTherapiesBean;
import com.ampamt.moduler.bean.BusinessAccountDetailsBean;
import com.ampamt.moduler.bean.BusinessDocumentsDetailsBean;
import com.ampamt.moduler.bean.DoctorsAccountDetailsBean;
import com.ampamt.moduler.bean.DoctorsDocumentsDetailsBean;
import com.ampamt.moduler.bean.email.EmailBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.constant.CommonConstant;
import com.ampamt.moduler.dao.AccountDao;
import com.ampamt.moduler.emailtemplates.EmailTemplate;
import com.ampamt.moduler.model.BusinessAccountModel;
import com.ampamt.moduler.model.ChangePasswordModel;
import com.ampamt.moduler.model.DoctorAccountModel;
import com.ampamt.moduler.model.DoctorAccountStatusModel;
import com.ampamt.moduler.model.DocumentsVerificationModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.model.UserSearchModel;
import com.ampamt.moduler.security.AES;
import com.ampamt.moduler.service.email.EmailService;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountDao accountDao;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	private Environment environment;

	@Override
	public SuccessModel createDoctorsAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		SuccessModel successModel = accountDao.createDoctorsAccountDetails(doctorsAccountDetailsBean);
		return successModel;
	}

	@Override
	public SuccessModel updateDoctorAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		logger.debug("***************start updateBusinessAccountDetails******************");
		AmpamtAccountBean ampamtAccountBean = new AmpamtAccountBean();
		ampamtAccountBean.setId(doctorsAccountDetailsBean.getId());
		ampamtAccountBean.setEmailId(doctorsAccountDetailsBean.getEmailId());
		ampamtAccountBean.setContactNo(doctorsAccountDetailsBean.getContactNo());
		ampamtAccountBean.setAccountType(doctorsAccountDetailsBean.getAccountType());
		SuccessModel successModel = accountDao.updateAmpamtAccount(ampamtAccountBean);
		if (successModel.getStatus() != null
				&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {

			successModel = accountDao.updateDoctorAccountDetails(doctorsAccountDetailsBean);
		}
		return successModel;
	}

	@Override
	public SuccessModel deleteDoctorAccountDetails(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		SuccessModel successModel = accountDao.deleteDoctorAccountDetails(doctorsAccountDetailsBean);
		return successModel;
	}

	@Override
	public SuccessModel createDoctorsDocumentsDetails(DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		SuccessModel successModel = accountDao.createDoctorsDocumentsDetails(doctorsDocumentsDetailsBean);
		return successModel;
	}

	@Override
	public List<DoctorsAccountDetailsBean> getDoctordetail(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = accountDao
				.getDoctordetail(doctorsAccountDetailsBean);
		return doctorsAccountDetailsList;
	}

	@Override
	public List<DoctorsAccountDetailsBean> getDoctorsLists(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = accountDao
				.getDoctorsLists(doctorsAccountDetailsBean);

		return doctorsAccountDetailsList;
	}

	@Override
	public SuccessModel createAccountId(AmpamtAccountBean ampamtAccount) {
		SuccessModel successModel = accountDao.createAccountId(ampamtAccount);
		return successModel;
	}

	@Override
	public SuccessModel createDoctorAccount(DoctorAccountModel doctorAccountModel) {
		logger.debug("***************start createDoctorAccount******************");
		AmpamtAccountBean ampamtAccount = new AmpamtAccountBean();
		ampamtAccount.setAccountType(ApplicationConstant.ACCOUNT_DOCTOR);
		ampamtAccount.setActiveFlag(ApplicationConstant.FLAG_N);
		ampamtAccount.setStatus(ApplicationConstant.STATUS_PENDING);

		ampamtAccount.setEmailId(doctorAccountModel.getEmailId().trim().toLowerCase());
		ampamtAccount.setUsername(doctorAccountModel.getEmailId().trim().toLowerCase());
		ampamtAccount.setContactNo(doctorAccountModel.getContactNo());
		List<AmpamtAccountBean> ampamtAccountList = accountDao.getAmpamtAccountDetail(ampamtAccount);
		if (ampamtAccountList.size()>0) {
			SuccessModel successModel3=new SuccessModel();
			successModel3.setAccountExists(ApplicationConstant.FLAG_Y);
			successModel3.setStatus(ApplicationConstant.STATUS_FAILED);
			successModel3.setMsg(ApplicationConstant.FOUND);
			return successModel3;
		}

		AES.setKey("");
		AES.encrypt(doctorAccountModel.getPassword());
		ampamtAccount.setContactNo(doctorAccountModel.getContactNo());
		ampamtAccount.setPassword(AES.getEncryptedString());
		SuccessModel successModel = accountDao.createAccountId(ampamtAccount);
		DoctorsAccountDetailsBean doctorsAccountDetailsBean = new DoctorsAccountDetailsBean();
		if (successModel.getStatus() != null
				&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			doctorsAccountDetailsBean.setId(successModel.getId());
			doctorsAccountDetailsBean.setFirstName(doctorAccountModel.getFirstName());
			doctorsAccountDetailsBean.setGender(doctorAccountModel.getGender());
			doctorsAccountDetailsBean.setDob(doctorAccountModel.getDob());
			doctorsAccountDetailsBean.setBloodGroup(doctorAccountModel.getBloodGroup());
			doctorsAccountDetailsBean.setMaritalStatus(doctorAccountModel.getMaritalStatus());
			doctorsAccountDetailsBean.setPracticingAs(doctorAccountModel.getPracticingAs());
			doctorsAccountDetailsBean.setPracticing(doctorAccountModel.getPracticing());
			doctorsAccountDetailsBean.setPracticeCity(doctorAccountModel.getPracticeCity());
			doctorsAccountDetailsBean.setNationality(doctorAccountModel.getNationality());
			doctorsAccountDetailsBean.setReligion(doctorAccountModel.getReligion());
			doctorsAccountDetailsBean.setFatherName(doctorAccountModel.getFather());
			doctorsAccountDetailsBean.setMotherName(doctorAccountModel.getMother());
			doctorsAccountDetailsBean.setCompanyName(doctorAccountModel.getCompanyName());
			doctorsAccountDetailsBean.setExperiencEstablishment(doctorAccountModel.getExperiencEstablishment());
			doctorsAccountDetailsBean.setRegistOfficeAdd(doctorAccountModel.getRegistOfficeAdd());
			doctorsAccountDetailsBean.setContactNo(doctorAccountModel.getContactNo());
			doctorsAccountDetailsBean.setOtherContactNo(doctorAccountModel.getOtherContactNo());
			doctorsAccountDetailsBean.setWhatsappNo(doctorAccountModel.getWhatsappNo());
			doctorsAccountDetailsBean.setEmergencyNo(doctorAccountModel.getEmergencyNo());
			doctorsAccountDetailsBean.setEmailId(doctorAccountModel.getEmailId().trim().toLowerCase());
			doctorsAccountDetailsBean.setAadharNo(doctorAccountModel.getAadharNo());
			doctorsAccountDetailsBean.setPanNo(doctorAccountModel.getPanNo());
			doctorsAccountDetailsBean.setPassportNo(doctorAccountModel.getPassportNo());
			doctorsAccountDetailsBean.setPermanentAddress(doctorAccountModel.getPermanentAddress());
			doctorsAccountDetailsBean.setMiddleName(doctorAccountModel.getMiddleName());
			doctorsAccountDetailsBean.setLastName(doctorAccountModel.getLastName());
			doctorsAccountDetailsBean.setCity(doctorAccountModel.getCity());
			doctorsAccountDetailsBean.setState(doctorAccountModel.getState());
			doctorsAccountDetailsBean.setCountry(doctorAccountModel.getCountry());
			doctorsAccountDetailsBean.setReference(doctorAccountModel.getReference());
			doctorsAccountDetailsBean.setCompanyTitle(doctorAccountModel.getCompanyTitle());
			doctorsAccountDetailsBean.setZipCode(doctorAccountModel.getZipCode());
			if (doctorAccountModel.isTncAccepted()) {
				doctorsAccountDetailsBean.setTncAccepted(ApplicationConstant.FLAG_Y);
			} else {
				doctorsAccountDetailsBean.setTncAccepted(ApplicationConstant.FLAG_N);
			}
			
			if (doctorAccountModel.getTncAcceptedFlag()!=null) {
				doctorsAccountDetailsBean.setTncAccepted(doctorAccountModel.getTncAcceptedFlag());
			} else {
				doctorsAccountDetailsBean.setTncAccepted(ApplicationConstant.FLAG_N);
			}
			doctorsAccountDetailsBean.setActiveFlag(ApplicationConstant.FLAG_N);
			doctorsAccountDetailsBean.setAccountType(ApplicationConstant.ACCOUNT_DOCTOR);
			doctorsAccountDetailsBean.setStatus(ApplicationConstant.STATUS_PENDING);
			doctorsAccountDetailsBean.setNameTitle(doctorAccountModel.getNameTitle());
			doctorsAccountDetailsBean.setHaveRamp(doctorAccountModel.getHaveRamp());
			if (doctorAccountModel.getApplyRamp()) {
				doctorsAccountDetailsBean.setApplyRamp(ApplicationConstant.FLAG_Y);
			} else {
				doctorsAccountDetailsBean.setApplyRamp(ApplicationConstant.FLAG_N);
			}

			if (doctorAccountModel.getApplyRampFlag()!=null) {
				doctorsAccountDetailsBean.setApplyRamp(doctorAccountModel.getApplyRampFlag());
			} else {
				doctorsAccountDetailsBean.setApplyRamp(ApplicationConstant.FLAG_N);
			}
			doctorsAccountDetailsBean.setAdminFlag(ApplicationConstant.FLAG_N);
			SuccessModel successModel2 = accountDao.createDoctorsAccountDetails(doctorsAccountDetailsBean);

			if (successModel2.getStatus() != null
					&& successModel2.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
				DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean = new DoctorsDocumentsDetailsBean();
				doctorsDocumentsDetailsBean.setId(successModel.getId());
				accountDao.createDoctorsDocumentsDetails(doctorsDocumentsDetailsBean);
			}
		}

		logger.debug("***************end createDoctorAccount******************");
		return successModel;
	}

	@Override
	public SuccessModel createBusinessAccount(BusinessAccountModel businessAccountModel) {
		logger.debug("***************start createBusinessAccount******************");
		AmpamtAccountBean ampamtAccount = new AmpamtAccountBean();
		ampamtAccount.setAccountType(ApplicationConstant.ACCOUNT_BUSINESS);
		ampamtAccount.setActiveFlag(ApplicationConstant.FLAG_N);
		ampamtAccount.setStatus(ApplicationConstant.STATUS_PENDING);

		ampamtAccount.setEmailId(businessAccountModel.getEmailId().trim().toLowerCase());
		ampamtAccount.setUsername(businessAccountModel.getEmailId().trim().toLowerCase());

		ampamtAccount.setContactNo(businessAccountModel.getContactNo());
		List<AmpamtAccountBean> ampamtAccountList = accountDao.getAmpamtAccountDetail(ampamtAccount);
		if (ampamtAccountList.size()>0) {
			SuccessModel successModel3=new SuccessModel();
			successModel3.setAccountExists(ApplicationConstant.FLAG_Y);
			successModel3.setStatus(ApplicationConstant.STATUS_FAILED);
			successModel3.setMsg(ApplicationConstant.FOUND);
			return successModel3;
		}

		AES.setKey("");
		AES.encrypt(businessAccountModel.getPassword());
		ampamtAccount.setContactNo(businessAccountModel.getContactNo());
		ampamtAccount.setPassword(AES.getEncryptedString());
		SuccessModel successModel = accountDao.createAccountId(ampamtAccount);
		if (successModel.getStatus() != null
				&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			BusinessAccountDetailsBean businessAccountDetailsBean = new BusinessAccountDetailsBean();

			businessAccountDetailsBean.setId(successModel.getId());
			businessAccountDetailsBean.setCompanyName(businessAccountModel.getCompanyName());
			businessAccountDetailsBean.setCompanyTitle(businessAccountModel.getCompanyTitle());
			businessAccountDetailsBean.setOwnerNameTitle(businessAccountModel.getOwnerNameTitle());
			businessAccountDetailsBean.setCompanyOwnerName(businessAccountModel.getCompanyOwnerName());
			businessAccountDetailsBean.setOwnerGender(businessAccountModel.getOwnerGender());
			businessAccountDetailsBean.setRepresentativeName(businessAccountModel.getRepresentativeName());
			businessAccountDetailsBean.setRepresentativeNameTitle(businessAccountModel.getRepresentativeNameTitle());
			businessAccountDetailsBean.setRepresentativeGender(businessAccountModel.getRepresentativeGender());
			businessAccountDetailsBean.setOwnerNationality(businessAccountModel.getOwnerNationality());
			businessAccountDetailsBean.setOwnerReligion(businessAccountModel.getOwnerReligion());
			businessAccountDetailsBean.setOwnerDetail(businessAccountModel.getOwnerDetail());
			businessAccountDetailsBean.setSector(businessAccountModel.getSector());
			businessAccountDetailsBean.setExperiencEstablishment(businessAccountModel.getExperiencEstablishment());
			businessAccountDetailsBean.setRegistOfficeAdd(businessAccountModel.getRegistOfficeAdd());
			businessAccountDetailsBean.setLandmark(businessAccountModel.getLandmark());
			businessAccountDetailsBean.setCity(businessAccountModel.getCity());
			businessAccountDetailsBean.setState(businessAccountModel.getState());
			businessAccountDetailsBean.setZipCode(businessAccountModel.getZipCode());
			businessAccountDetailsBean.setContactNo(businessAccountModel.getContactNo());
			businessAccountDetailsBean.setLandlineNo(businessAccountModel.getLandlineNo());
			businessAccountDetailsBean.setOtherContactNo(businessAccountModel.getOtherContactNo());
			businessAccountDetailsBean.setWhatsappNo(businessAccountModel.getWhatsappNo());
			businessAccountDetailsBean.setEmailId(businessAccountModel.getEmailId().trim().toLowerCase());
			businessAccountDetailsBean.setOtherEmailId(businessAccountModel.getOtherEmailId());
			businessAccountDetailsBean.setAadharNo(businessAccountModel.getAadharNo());
			businessAccountDetailsBean.setPanNo(businessAccountModel.getPanNo());
			businessAccountDetailsBean.setPassportNo(businessAccountModel.getPassportNo());
			if (businessAccountModel.isTncAccepted()) {
				businessAccountDetailsBean.setTncAccepted(ApplicationConstant.FLAG_Y);
			} else {
				businessAccountDetailsBean.setTncAccepted(ApplicationConstant.FLAG_N);
			}
			businessAccountDetailsBean.setActiveFlag(ApplicationConstant.FLAG_N);
			businessAccountDetailsBean.setAccountType(ApplicationConstant.ACCOUNT_BUSINESS);
			businessAccountDetailsBean.setStatus(ApplicationConstant.STATUS_PENDING);
			businessAccountDetailsBean.setServiceStates(businessAccountModel.getServiceStates());
			businessAccountDetailsBean.setServiceCities(businessAccountModel.getServiceCities());
			businessAccountDetailsBean.setAdminFlag(ApplicationConstant.FLAG_N);
			SuccessModel successModel2 = accountDao.createBusinessAccountDetails(businessAccountDetailsBean);
			if (successModel2.getStatus() != null
					&& successModel2.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
				BusinessDocumentsDetailsBean businessDocumentsDetailsBean = new BusinessDocumentsDetailsBean();
				businessDocumentsDetailsBean.setId(successModel.getId());
				accountDao.createBusinessDocumentsDetails(businessDocumentsDetailsBean);
			}

		}
		logger.debug("***************end createBusinessAccount******************");
		return successModel;
	}

	@Override
	public List<DoctorsDocumentsDetailsBean> getDocumentdetail(
			DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		List<DoctorsDocumentsDetailsBean> doctorsDocumentsDetailsList = accountDao
				.getDocumentdetail(doctorsDocumentsDetailsBean);
		return doctorsDocumentsDetailsList;

	}

	@Override
	public SuccessModel updateDoctorDocuments(DoctorsDocumentsDetailsBean doctorsDocumentsDetailsBean) {
		SuccessModel successModel = accountDao.updateDoctorDocuments(doctorsDocumentsDetailsBean);

		return successModel;
	}

	@Override
	public SuccessModel doctorLogin(AmpamtAccountBean ampamtAccount) {
		SuccessModel successModel = accountDao.doctorLogin(ampamtAccount);
		if (successModel != null && successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			AmpamtAccountBean ampamtAccountBean = new AmpamtAccountBean();
			ampamtAccountBean.setId(successModel.getId());
			successModel.setDocumentsVerificationModelList(accountDao.documentsVerification(ampamtAccountBean));
		}

		return successModel;
	}

	@Override
	public SuccessModel deleteBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {
		SuccessModel successModel = accountDao.deleteBusinessAccountDetails(businessAccountDetailsBean);

		return successModel;
	}

	@Override
	public SuccessModel createBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {
		SuccessModel successModel = accountDao.createBusinessAccountDetails(businessAccountDetailsBean);

		return successModel;
	}

	@Override
	public SuccessModel createBusinessDocumentsDetails(BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		SuccessModel successModel = accountDao.createBusinessDocumentsDetails(businessDocumentsDetailsBean);

		return successModel;
	}

	@Override
	public List<BusinessDocumentsDetailsBean> getBusinessDocumentdetail(
			BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		List<BusinessDocumentsDetailsBean> businessDocumentsDetailsBeanList = accountDao
				.getBusinessDocumentdetail(businessDocumentsDetailsBean);

		return businessDocumentsDetailsBeanList;
	}

	@Override
	public SuccessModel updateBusinessDocuments(BusinessDocumentsDetailsBean businessDocumentsDetailsBean) {
		SuccessModel successModel = accountDao.updateBusinessDocuments(businessDocumentsDetailsBean);

		return successModel;
	}

	@Override
	public SuccessModel updateBusinessAccountDetails(BusinessAccountDetailsBean businessAccountDetailsBean) {

		logger.debug("***************start updateBusinessAccountDetails******************");
		AmpamtAccountBean ampamtAccountBean = new AmpamtAccountBean();
		ampamtAccountBean.setId(businessAccountDetailsBean.getId());
		ampamtAccountBean.setEmailId(businessAccountDetailsBean.getEmailId());
		ampamtAccountBean.setContactNo(businessAccountDetailsBean.getContactNo());
		ampamtAccountBean.setAccountType(businessAccountDetailsBean.getAccountType());
		SuccessModel successModel = accountDao.updateAmpamtAccount(ampamtAccountBean);
		if (successModel.getStatus() != null
				&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			successModel = accountDao.updateBusinessAccountDetails(businessAccountDetailsBean);
		}

		logger.debug("***************end updateBusinessAccountDetails******************");
		return successModel;
	}

	@Override
	public List<BusinessAccountDetailsBean> getBusinessAccdetail(
			BusinessAccountDetailsBean businessAccountDetailsBean) {
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountDao
				.getBusinessAccdetail(businessAccountDetailsBean);
		return businessAccountDetailsBeanList;
	}

	@Override
	public List<BusinessAccountDetailsBean> getBusinessAccLists(BusinessAccountDetailsBean businessAccountDetailsBean) {
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountDao
				.getBusinessAccLists(businessAccountDetailsBean);
		return businessAccountDetailsBeanList;
	}

	@Override
	public SuccessModel businessLogin(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel successModel = accountDao.businessLogin(ampamtAccountBean);
		if (successModel != null && successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			AmpamtAccountBean ampamtAccountBean2 = new AmpamtAccountBean();
			ampamtAccountBean2.setId(successModel.getId());
			successModel.setDocumentsVerificationModelList(accountDao.documentsVerification(ampamtAccountBean2));
		}
		return successModel;
	}

	@Override
	public SuccessModel checkAmpamtAccount(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel successModel = accountDao.checkAmpamtAccount(ampamtAccountBean);

		return successModel;
	}

	@Override
	public SuccessModel changePassword(ChangePasswordModel changePasswordModel) {

		SuccessModel successModel = accountDao.changePassword(changePasswordModel);

		return successModel;
	}

	@Override
	public SuccessModel setOTP(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel scuccessModel = accountDao.setOTP(ampamtAccountBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel changeCurrentPassword(ChangePasswordModel changePasswordModel) {
		AmpamtAccountBean ampamtAccountBean = new AmpamtAccountBean();
		ampamtAccountBean.setPassword(changePasswordModel.getCurrentPassword());
		ampamtAccountBean.setAccountType(changePasswordModel.getAccountType());
		SuccessModel successModel = accountDao.checkAmpamtAccount(ampamtAccountBean);
		if (successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)
				&& successModel.getMsg().equalsIgnoreCase(ApplicationConstant.FOUND)) {
			successModel = accountDao.changePassword(changePasswordModel);
		}
		return successModel;
	}

	@Override
	public SuccessModel approveAccount(AmpamtAccountBean ampamtAccountBean) {
		ampamtAccountBean.setActiveFlag(ApplicationConstant.FLAG_Y);
		ampamtAccountBean.setStatus(ApplicationConstant.STATUS_CONFIRMED);
		SuccessModel scuccessModel = accountDao.approveAccount(ampamtAccountBean);
		if (scuccessModel != null && scuccessModel.getStatus() != null
				&& scuccessModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {

			if (ampamtAccountBean != null && ampamtAccountBean.getAccountType() != null
					&& ampamtAccountBean.getAccountType().equalsIgnoreCase(ApplicationConstant.ACCOUNT_DOCTOR)) {
				DoctorsAccountDetailsBean doctorsAccountDetailsBean = new DoctorsAccountDetailsBean();
				doctorsAccountDetailsBean.setActiveFlag(ampamtAccountBean.getActiveFlag());
				doctorsAccountDetailsBean.setAccountType(ApplicationConstant.ACCOUNT_DOCTOR);
				doctorsAccountDetailsBean.setEmailId(ampamtAccountBean.getEmailId());
				doctorsAccountDetailsBean.setId(ampamtAccountBean.getId());
				doctorsAccountDetailsBean.setStatus(ampamtAccountBean.getStatus());
				scuccessModel = accountDao.approveDoctorAccount(doctorsAccountDetailsBean);
				if (scuccessModel != null && scuccessModel.getStatus() != null
						&& scuccessModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
				EmailBean emailBean=new EmailBean();
				EmailTemplate emailTemplate=new EmailTemplate();
				
				List<DoctorsAccountDetailsBean> doctorsAccountDetailsBeanList =accountDao.getDoctordetail(doctorsAccountDetailsBean);
				String doctorName=null;
				if(doctorsAccountDetailsBeanList!=null && doctorsAccountDetailsBeanList.size()>0) {
					String title=doctorsAccountDetailsBeanList.get(0).getNameTitle();
					String firstName=doctorsAccountDetailsBeanList.get(0).getFirstName();
					String lastName=doctorsAccountDetailsBeanList.get(0).getLastName();
					doctorName=(title!=null?title:"")+(firstName!=null?(" "+firstName):"")+(lastName!=null?(" "+lastName):"");
				}
				
				
				emailBean.setEmailBody(emailTemplate.getWelcomeEmailTemplate(doctorName));
				emailBean.setFrom("ampamt.india@gmail.com");
				emailBean.setTo(ampamtAccountBean.getEmailId());
				emailBean.setFileName("welcome_pdf.pdf");
				String pdfpath=environment.getProperty("home.file.welcome.pdf.path")+"//doctor_welcome_email.pdf";
				emailBean.setFilePath(pdfpath);
				
				
				emailBean.setSubject(CommonConstant.DOCTOR_WELCOME_EMAIL_SUBJECT);
//				EmailController emailController=new EmailController();
				scuccessModel.setStatus(emailService.sendDoctorWelcomeEmailWithAttachment(emailBean));
				}

			}
			if (ampamtAccountBean != null && ampamtAccountBean.getAccountType() != null
					&& ampamtAccountBean.getAccountType().equalsIgnoreCase(ApplicationConstant.ACCOUNT_BUSINESS)) {

				BusinessAccountDetailsBean businessAccountDetailsBean = new BusinessAccountDetailsBean();
				businessAccountDetailsBean.setActiveFlag(ampamtAccountBean.getActiveFlag());
				businessAccountDetailsBean.setAccountType(ApplicationConstant.ACCOUNT_BUSINESS);
				businessAccountDetailsBean.setEmailId(ampamtAccountBean.getEmailId());
				businessAccountDetailsBean.setId(ampamtAccountBean.getId());
				businessAccountDetailsBean.setStatus(ampamtAccountBean.getStatus());
				scuccessModel = accountDao.approveBusinessAccount(businessAccountDetailsBean);
			}
		}
		return scuccessModel;
	}

	@Override
	public SuccessModel getTotalUsers(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel scuccessModel = accountDao.getTotalUsers(ampamtAccountBean);
		return scuccessModel;
	}

	@Override
	public List<BusinessAccountDetailsBean> searchServiceProviders(
			BusinessAccountDetailsBean businessAccountDetailsBean) {
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountDao
				.searchServiceProviders(businessAccountDetailsBean);
		return businessAccountDetailsBeanList;
	}

	@Override
	public List<UserSearchModel> searchUsers(UserSearchModel userSearchModel) {
		List<UserSearchModel> userSearchModelList = accountDao.searchUsers(userSearchModel);
		return userSearchModelList;
	}

	@Override
	public SuccessModel approveDoctorAccount(DoctorsAccountDetailsBean doctorsAccountDetailsBean) {
		SuccessModel scuccessModel = accountDao.approveDoctorAccount(doctorsAccountDetailsBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel approveBusinessAccount(BusinessAccountDetailsBean businessAccountDetailsBean) {
		SuccessModel scuccessModel = accountDao.approveBusinessAccount(businessAccountDetailsBean);
		return scuccessModel;
	}

	@Override
	public List<AmpamtTherapiesBean> getTherapyLists(AmpamtTherapiesBean ampamtTherapiesBean) {
		List<AmpamtTherapiesBean> ampamtTherapiesBeanList = accountDao.getTherapyLists(ampamtTherapiesBean);
		return ampamtTherapiesBeanList;
	}

	@Override
	public SuccessModel userExcelDownload(UserSearchModel userSearchModel) {
		logger.debug("*******************start userExcelDownload***************************");
		SuccessModel successModel = new SuccessModel();

		if (userSearchModel != null && userSearchModel.getAccountType() != null) {

			if (userSearchModel.getAccountType().equalsIgnoreCase(ApplicationConstant.ACCOUNT_BUSINESS)) {
				successModel = userBusinessExcelDownload(userSearchModel);
			}

			if (userSearchModel.getAccountType().equalsIgnoreCase(ApplicationConstant.ACCOUNT_DOCTOR)) {
				successModel = userDoctorExcelDownload(userSearchModel);
			}
		}
		logger.debug("*******************end userExcelDownload***************************");
		return successModel;
	}

	@Override
	public SuccessModel userBusinessExcelDownload(UserSearchModel userSearchModel) {
		logger.debug("*******************start userBusinessExcelDownload***************************");
		BusinessAccountDetailsBean businessAccountDetailsBean = new BusinessAccountDetailsBean();
		List<BusinessAccountDetailsBean> businessAccountDetailsBeanList = accountDao
				.getBusinessAccLists(businessAccountDetailsBean);
		String pdfByteStream = "";
		SuccessModel successModel = new SuccessModel();
		try {
			File file = File.createTempFile("BUSINESS_USERS", ".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet("Details ");
			XSSFRow row = null;
			CellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			int rownumber = 0;
			spreadsheet.setDefaultColumnWidth(22);

			font.setColor(HSSFColorPredefined.WHITE.getIndex());
			font.setBold(true);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setFont(font);
			cellStyle.setFillForegroundColor(HSSFColorPredefined.DARK_RED.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			row = spreadsheet.createRow(rownumber);

			Cell cell = row.createCell(0);
			cell.setCellValue(new XSSFRichTextString("ACCOUNT_ID"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(1);
			cell.setCellValue(new XSSFRichTextString("COMPANY_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(2);
			cell.setCellValue(new XSSFRichTextString("COMPANY_TITLE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(3);
			cell.setCellValue(new XSSFRichTextString("OWNER_NAME_TITLE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(4);
			cell.setCellValue(new XSSFRichTextString("COMPANY_OWNER_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(5);
			cell.setCellValue(new XSSFRichTextString("OWNER_GENDER"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(6);
			cell.setCellValue(new XSSFRichTextString("OWNER_NATIONALITY"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(7);
			cell.setCellValue(new XSSFRichTextString("OWNER_RELIGION"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(8);
			cell.setCellValue(new XSSFRichTextString("OWNER_DETAIL"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(9);
			cell.setCellValue(new XSSFRichTextString("REPRESENTATIVE_NAME_TITLE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(10);
			cell.setCellValue(new XSSFRichTextString("REPRESENTATIVE_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(11);
			cell.setCellValue(new XSSFRichTextString("REPRESENTATIVE_GENDER"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(12);
			cell.setCellValue(new XSSFRichTextString("SECTOR"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(13);
			cell.setCellValue(new XSSFRichTextString("EXPERIENC_ESTABLISHMENT"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(14);
			cell.setCellValue(new XSSFRichTextString("REGIST_OFFICE_ADD"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(15);
			cell.setCellValue(new XSSFRichTextString("LANDMARK"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(16);
			cell.setCellValue(new XSSFRichTextString("CITY"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(17);
			cell.setCellValue(new XSSFRichTextString("STATE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(18);
			cell.setCellValue(new XSSFRichTextString("ZIP_CODE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(19);
			cell.setCellValue(new XSSFRichTextString("CONTACT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(20);
			cell.setCellValue(new XSSFRichTextString("LANDLINE_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(21);
			cell.setCellValue(new XSSFRichTextString("OTHER_CONTACT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(22);
			cell.setCellValue(new XSSFRichTextString("WHATSAPP_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(23);
			cell.setCellValue(new XSSFRichTextString("EMAIL_ID"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(24);
			cell.setCellValue(new XSSFRichTextString("OTHER_EMAIL_ID"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(25);
			cell.setCellValue(new XSSFRichTextString("AADHAR_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(26);
			cell.setCellValue(new XSSFRichTextString("PAN_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(27);
			cell.setCellValue(new XSSFRichTextString("PASSPORT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(28);
			cell.setCellValue(new XSSFRichTextString("TNC_ACCEPTED"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(29);
			cell.setCellValue(new XSSFRichTextString("ACCOUNT_STATUS"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(30);
			cell.setCellValue(new XSSFRichTextString("CREATE_DATE"));
			cell.setCellStyle(cellStyle);

			if (businessAccountDetailsBeanList.size() > 0) {
				String createDate = "-";
				String accStatus = "-";
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
				for (int i = 0; i < businessAccountDetailsBeanList.size(); i++) {

					createDate = "-";
					if (businessAccountDetailsBeanList.get(i).getCreateDate() != null) {
						createDate = sdf.format(businessAccountDetailsBeanList.get(i).getCreateDate());
					}

					accStatus = "-";
					if (businessAccountDetailsBeanList.get(i).getActiveFlag() != null) {
						if (businessAccountDetailsBeanList.get(i).getActiveFlag()
								.equalsIgnoreCase(ApplicationConstant.FLAG_Y)) {
							accStatus = "Active";
						} else {
							accStatus = "Inactive";
						}

					}
					row = spreadsheet.createRow(++rownumber);
					cell = row.createCell(0);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getId()));

					cell = row.createCell(1);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getCompanyName()));

					cell = row.createCell(2);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getCompanyTitle()));

					cell = row.createCell(3);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOwnerNameTitle()));

					cell = row.createCell(4);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getCompanyOwnerName()));

					cell = row.createCell(5);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOwnerGender()));

					cell = row.createCell(6);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOwnerNationality()));

					cell = row.createCell(7);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOwnerReligion()));

					cell = row.createCell(8);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOwnerDetail()));

					cell = row.createCell(9);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getRepresentativeNameTitle()));

					cell = row.createCell(10);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getRepresentativeName()));

					cell = row.createCell(11);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getRepresentativeGender()));

					cell = row.createCell(12);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getSector()));

					cell = row.createCell(13);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getExperiencEstablishment()));

					cell = row.createCell(14);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getRegistOfficeAdd()));

					cell = row.createCell(15);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getLandmark()));

					cell = row.createCell(16);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getCity()));

					cell = row.createCell(17);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getState()));

					cell = row.createCell(18);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getZipCode()));

					cell = row.createCell(19);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getContactNo()));

					cell = row.createCell(20);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getLandlineNo()));

					cell = row.createCell(21);
					cell.setCellValue(
							new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOtherContactNo()));

					cell = row.createCell(22);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getWhatsappNo()));

					cell = row.createCell(23);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getEmailId()));

					cell = row.createCell(24);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getOtherEmailId()));

					cell = row.createCell(25);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getAadharNo()));

					cell = row.createCell(26);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getPanNo() != null
							? businessAccountDetailsBeanList.get(i).getPanNo().toUpperCase()
							: "-"));

					cell = row.createCell(27);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getPassportNo()));

					cell = row.createCell(28);
					cell.setCellValue(new XSSFRichTextString(businessAccountDetailsBeanList.get(i).getTncAccepted()));

					cell = row.createCell(29);
					cell.setCellValue(new XSSFRichTextString(accStatus));

					cell = row.createCell(30);
					cell.setCellValue(new XSSFRichTextString(createDate));

				}
			} else {
				row = spreadsheet.createRow(++rownumber);
				cell = row.createCell(0);
				cell.setCellValue(new XSSFRichTextString("No records found"));
			}

			FileOutputStream fileOut = new java.io.FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			byte[] byteStream = loadFile(file);
			logger.info("byteSteam: " + byteStream.toString());
			pdfByteStream = CommonConstant.START_OF_XLSX_EXCEL_JSON + DatatypeConverter.printBase64Binary(byteStream);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setData(pdfByteStream);

		} catch (IOException e) {
			successModel.setData(ApplicationConstant.NOTDATA);
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("*******************end userBusinessExcelDownload***************************");
		return successModel;
	}

	public byte[] loadFile(File file) throws IOException {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// File is too large
			}
			byte[] bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
			is.close();
			return bytes;
		} finally {
			if (is != null)
				is.close();
		}
	}

	@Override
	public SuccessModel userDoctorExcelDownload(UserSearchModel userSearchModel) {
		logger.debug("*******************start userDoctorExcelDownload***************************");
		DoctorsAccountDetailsBean doctorsAccountDetailsBean = new DoctorsAccountDetailsBean();
		List<DoctorsAccountDetailsBean> doctorsAccountDetailsList = accountDao
				.getDoctorsLists(doctorsAccountDetailsBean);

		String pdfByteStream = "";
		SuccessModel successModel = new SuccessModel();
		try {
			File file = File.createTempFile("DOCTOR_USERS", ".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet spreadsheet = workbook.createSheet("Details ");
			XSSFRow row = null;
			CellStyle cellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			int rownumber = 0;
			spreadsheet.setDefaultColumnWidth(22);

			font.setColor(HSSFColorPredefined.WHITE.getIndex());
			font.setBold(true);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setFont(font);
			cellStyle.setFillForegroundColor(HSSFColorPredefined.DARK_RED.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			row = spreadsheet.createRow(rownumber);

			Cell cell = row.createCell(0);
			cell.setCellValue(new XSSFRichTextString("ACCOUNT_ID"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(1);
			cell.setCellValue(new XSSFRichTextString("COMPANY_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(2);
			cell.setCellValue(new XSSFRichTextString("COMPANY_TITLE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(3);
			cell.setCellValue(new XSSFRichTextString("NAME_TITLE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(4);
			cell.setCellValue(new XSSFRichTextString("FIRST_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(5);
			cell.setCellValue(new XSSFRichTextString("MIDDLE_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(6);
			cell.setCellValue(new XSSFRichTextString("LAST_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(7);
			cell.setCellValue(new XSSFRichTextString("GENDER"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(8);
			cell.setCellValue(new XSSFRichTextString("DATE_OF_BIRTH"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(9);
			cell.setCellValue(new XSSFRichTextString("BLOOD_GROUP"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(10);
			cell.setCellValue(new XSSFRichTextString("MARITAL_STATUS"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(11);
			cell.setCellValue(new XSSFRichTextString("PRACTICING_AS"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(12);
			cell.setCellValue(new XSSFRichTextString("PRACTICING"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(13);
			cell.setCellValue(new XSSFRichTextString("NATIONALITY"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(14);
			cell.setCellValue(new XSSFRichTextString("RELIGION"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(15);
			cell.setCellValue(new XSSFRichTextString("FATHER_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(16);
			cell.setCellValue(new XSSFRichTextString("MOTHER_NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(17);
			cell.setCellValue(new XSSFRichTextString("WIFE/HUSBAND NAME"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(18);
			cell.setCellValue(new XSSFRichTextString("REGIST_OFFICE_ADD"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(19);
			cell.setCellValue(new XSSFRichTextString("CONTACT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(20);
			cell.setCellValue(new XSSFRichTextString("OTHER_CONTACT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(21);
			cell.setCellValue(new XSSFRichTextString("EMERGENCY_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(22);
			cell.setCellValue(new XSSFRichTextString("WHATSAPP_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(23);
			cell.setCellValue(new XSSFRichTextString("EMAIL_ID"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(24);
			cell.setCellValue(new XSSFRichTextString("AADHAR_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(25);
			cell.setCellValue(new XSSFRichTextString("PAN_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(26);
			cell.setCellValue(new XSSFRichTextString("PASSPORT_NO"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(27);
			cell.setCellValue(new XSSFRichTextString("TNC_ACCEPTED"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(28);
			cell.setCellValue(new XSSFRichTextString("ACCOUNT_TYPE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(29);
			cell.setCellValue(new XSSFRichTextString("ACCOUNT_STATUS"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(30);
			cell.setCellValue(new XSSFRichTextString("CREATE_DATE"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(31);
			cell.setCellValue(new XSSFRichTextString("EXPERIENC_ESTABLISHMENT"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(32);
			cell.setCellValue(new XSSFRichTextString("HAVE_RAMP"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(33);
			cell.setCellValue(new XSSFRichTextString("APPLY_RAMP"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(34);
			cell.setCellValue(new XSSFRichTextString("PERMANENT_ADDRESS"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(35);
			cell.setCellValue(new XSSFRichTextString("CITY"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(36);
			cell.setCellValue(new XSSFRichTextString("STATE"));
			cell.setCellStyle(cellStyle);
			
			cell = row.createCell(37);
			cell.setCellValue(new XSSFRichTextString("COUNTRY"));
			cell.setCellStyle(cellStyle);

			cell = row.createCell(38);
			cell.setCellValue(new XSSFRichTextString("REFERENCE"));
			cell.setCellStyle(cellStyle);

			if (doctorsAccountDetailsList.size() > 0) {
				String createDate = "-";
				String accStatus = "-", dob = "-";
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
				for (int i = 0; i < doctorsAccountDetailsList.size(); i++) {

					createDate = "-";
					if (doctorsAccountDetailsList.get(i).getCreateDate() != null) {
						createDate = sdf.format(doctorsAccountDetailsList.get(i).getCreateDate());
					}

					accStatus = "-";
					if (doctorsAccountDetailsList.get(i).getActiveFlag() != null) {
						if (doctorsAccountDetailsList.get(i).getActiveFlag()
								.equalsIgnoreCase(ApplicationConstant.FLAG_Y)) {
							accStatus = "Active";
						} else {
							accStatus = "Inactive";
						}

					}
					dob = "-";
					if (doctorsAccountDetailsList.get(i).getDob() != null) {
						dob = sdf.format(doctorsAccountDetailsList.get(i).getDob());
					}
					row = spreadsheet.createRow(++rownumber);
					cell = row.createCell(0);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getId()));

					cell = row.createCell(1);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getCompanyName()));

					cell = row.createCell(2);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getCompanyTitle()));

					cell = row.createCell(3);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getNameTitle()));

					cell = row.createCell(4);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getFirstName()));

					cell = row.createCell(5);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getMiddleName()));

					cell = row.createCell(6);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getLastName()));

					cell = row.createCell(7);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getGender()));

					cell = row.createCell(8);
					cell.setCellValue(new XSSFRichTextString(dob));

					cell = row.createCell(9);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getBloodGroup()));

					cell = row.createCell(10);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getMaritalStatus()));

					cell = row.createCell(11);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getPracticingAs()));

					cell = row.createCell(12);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getPracticing()));

					cell = row.createCell(13);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getNationality()));

					cell = row.createCell(14);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getReligion()));

					cell = row.createCell(15);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getFatherName()));

					cell = row.createCell(16);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getMotherName()));

					cell = row.createCell(17);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getWifeHusbandName()));

					cell = row.createCell(18);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getRegistOfficeAdd()));

					cell = row.createCell(19);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getContactNo()));

					cell = row.createCell(20);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getOtherContactNo()));

					cell = row.createCell(21);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getEmergencyNo()));

					cell = row.createCell(22);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getWhatsappNo()));

					cell = row.createCell(23);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getEmailId()));

					cell = row.createCell(24);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getAadharNo()));

					cell = row.createCell(25);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getPanNo() != null
							? doctorsAccountDetailsList.get(i).getPanNo().toUpperCase()
							: "-"));

					cell = row.createCell(26);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getPassportNo()));

					cell = row.createCell(27);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getTncAccepted()));

					cell = row.createCell(28);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getAccountType()));

					cell = row.createCell(29);
					cell.setCellValue(new XSSFRichTextString(accStatus));

					cell = row.createCell(30);
					cell.setCellValue(new XSSFRichTextString(createDate));

					cell = row.createCell(31);
					cell.setCellValue(
							new XSSFRichTextString(doctorsAccountDetailsList.get(i).getExperiencEstablishment()));

					cell = row.createCell(32);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getHaveRamp()));

					cell = row.createCell(33);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getApplyRamp()));

					cell = row.createCell(34);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getPermanentAddress()));

					cell = row.createCell(35);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getCity()));

					cell = row.createCell(36);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getState()));

					cell = row.createCell(37);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getCountry()));
					
					cell = row.createCell(38);
					cell.setCellValue(new XSSFRichTextString(doctorsAccountDetailsList.get(i).getReference()));

				}
			} else {
				row = spreadsheet.createRow(++rownumber);
				cell = row.createCell(0);
				cell.setCellValue(new XSSFRichTextString("No records found"));
			}

			FileOutputStream fileOut = new java.io.FileOutputStream(file);
			workbook.write(fileOut);
			fileOut.close();
			byte[] byteStream = loadFile(file);
			logger.info("byteSteam: " + byteStream.toString());
			pdfByteStream = CommonConstant.START_OF_XLSX_EXCEL_JSON + DatatypeConverter.printBase64Binary(byteStream);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setData(pdfByteStream);

		} catch (IOException e) {
			successModel.setData(ApplicationConstant.NOTDATA);
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("*******************end userDoctorExcelDownload***************************");
		return successModel;
	}

	@Override
	public SuccessModel createAuditTrail(String clientIp, String accountid, String action, String module) {
		SuccessModel successModel = accountDao.createAuditTrail(clientIp, accountid, action, module);
		return successModel;
	}

	@Override
	public SuccessModel verifyOtp(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel successModel = accountDao.verifyOtp(ampamtAccountBean);
		if (successModel != null && successModel.getStatus() != null
				&& successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
//			ampamtAccountBean.setAccountType(ampamtAccountBean.getAccountType());
//			ampamtAccountBean.setId(ampamtAccountBean.getId());
			ampamtAccountBean.setEmailId(successModel.getEmailId());
			approveAccount(ampamtAccountBean);
		}

		return successModel;
	}

	@Override
	public SuccessModel updateAmpamtAccount(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel successModel = accountDao.updateAmpamtAccount(ampamtAccountBean);

		return successModel;
	}

	@Override
	public List<DocumentsVerificationModel> documentsVerificationDetails(AmpamtAccountBean ampamtAccountBean) {
		List<DocumentsVerificationModel> documentsVerificationModelList=accountDao.documentsVerification(ampamtAccountBean);
		return documentsVerificationModelList;
	}

	@Override
	public List<AmpamtAccountBean> getAmpamtAccountDetail(AmpamtAccountBean ampamtAccountBean) {
		List<AmpamtAccountBean> ampamtAccountBeanList = accountDao.getAmpamtAccountDetail(ampamtAccountBean);
		return ampamtAccountBeanList;
	}

	@Override
	public List<DoctorAccountStatusModel> getDoctorsAccountListByStatus(DoctorAccountStatusModel doctorAccountStatusModel) {
		List<DoctorAccountStatusModel> doctorAccountStatusModelList = accountDao.getDoctorsAccountListByStatus(doctorAccountStatusModel);
		return doctorAccountStatusModelList;
	}

	@Override
	public SuccessModel verifyChangePasswordOtp(AmpamtAccountBean ampamtAccountBean) {
		SuccessModel successModel = accountDao.verifyOtp(ampamtAccountBean);
		return successModel;
	}

	@Override
	public SuccessModel createAuditTrailByBean(AmpamtAuditTrailBean ampamtAuditTrailBean) {
		
		return accountDao.createAuditTrailByBean(ampamtAuditTrailBean);
	}

}
