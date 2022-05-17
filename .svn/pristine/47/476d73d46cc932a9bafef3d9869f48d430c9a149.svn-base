package com.ampamt.moduler.controller.email;

import java.io.File;
import java.security.SecureRandom;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ampamt.moduler.bean.AmpamtAccountBean;
import com.ampamt.moduler.bean.email.EmailBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.constant.CommonConstant;
import com.ampamt.moduler.emailtemplates.EmailTemplate;
import com.ampamt.moduler.model.EmailModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.service.AccountService;
import com.ampamt.moduler.service.email.EmailService;


@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/email-service")
public class EmailController {

	private final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	AccountService accountService;

	@Autowired
	EmailService emailService; 
	
	@RequestMapping(value = "/send-otp-email", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> sendOTPEmail(@RequestBody AmpamtAccountBean ampamtAccountBean) throws MessagingException {
		logger.debug("*******************start sendOTPEmail***************************");
		if(ampamtAccountBean.getOtp()==null) {
			SecureRandom scRandom=new SecureRandom();
			String otp=String.format("%06d", scRandom.nextInt(999999));
			logger.debug("otp="+otp);
			ampamtAccountBean.setOtp(otp);
		}
		SuccessModel successModel=accountService.setOTP(ampamtAccountBean);
		
		if(successModel!=null && successModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS) && ampamtAccountBean.getOtp()!=null) {
			EmailTemplate emailTemplate=new EmailTemplate();
			String emailBody=emailTemplate.getPasswordResetTemplate(ampamtAccountBean.getOtp(),null);
			String subject="Password Reset OTP For AMPAMT Account";
			EmailBean emailBean=new EmailBean();
			emailBean.setFrom("ampamt.otp@gmail.com");
			emailBean.setTo(ampamtAccountBean.getEmailId());
			emailBean.setSubject(subject);
			emailBean.setEmailBody(emailBody);
			
			logger.debug("password reset emailtbody=>"+emailBody);
		    
			successModel.setStatus(emailService.sendOTPEmail(emailBean));
		}else {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
		}
		
		logger.debug("*******************end sendOTPEmail***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/send-service-email", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> sendServiceEmailMessgae(@RequestBody EmailModel emailModel) throws MessagingException {
		logger.debug("*******************start sendServiceEmailMessgae***************************");

		String name=emailModel.getName(),message=emailModel.getMessage(),regards=emailModel.getRegards();
		
		EmailTemplate emailTemplate=new EmailTemplate();
		String emailBody=emailTemplate.getMessageTemplate(emailModel);
		String subject="Customer Enquiry Details";
		EmailBean emailBean=new EmailBean();
		
		emailBean.setFrom("ampamt.services@gmail.com");
		emailBean.setTo("ampamt.services@gmail.com");
		emailBean.setSubject(subject);
		emailBean.setEmailBody(emailBody);
		
		logger.debug("sendEmailMessgae emailtbody=>"+emailBody);
		SuccessModel successModel=new SuccessModel();
		successModel.setStatus(emailService.sendEmail(emailBean));


		logger.debug("*******************end sendServiceEmailMessgae***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/send-account-verification-email", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> sendIdverificationEmail(@RequestBody EmailModel emailModel) {
		logger.debug("*******************start sendWelcomeEmail***************************");
		SuccessModel scuccessModel=new SuccessModel();
		if(emailModel!=null && emailModel.getOtp()!=null && emailModel.getEmailId()!=null && emailModel.getName()!=null) {
			EmailBean emailBean=new EmailBean();
			EmailTemplate emailTemplate=new EmailTemplate();
			emailBean.setEmailBody(emailTemplate.getEmailverificationTemplate(emailModel.getName(), emailModel.getOtp()));
			emailBean.setFrom("ampamt.india@gmail.com");
			emailBean.setTo(emailModel.getEmailId());
			emailBean.setSubject("Email Address Verfication");
			scuccessModel.setStatus(emailService.sendIndiaEmail(emailBean));
		}else {
			scuccessModel.setStatus(ApplicationConstant.STATUS_FAILED);
		}
		

		logger.debug("*******************end sendWelcomeEmail***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/send-pdfdata-email", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> sendPdfDataEmail(@RequestBody EmailModel emailModel) {
		logger.debug("*******************start sendPdfDataEmail***************************");
		SuccessModel scuccessModel=new SuccessModel();
		if(emailModel!=null && emailModel.getBase64String()!=null) {
			EmailBean emailBean=new EmailBean();
			
			emailBean.setEmailBody("Please Find Attachment for user "+emailModel.getUserId());
			emailBean.setFrom("ampamt.india@gmail.com");
			String fileExtension=".pdf";
			String fileName="AMPAMT_CUST_ACC_DTL";
			if(emailModel.getEmailId()!=null && !"".equals(emailModel.getEmailId())) {
				emailBean.setTo(emailModel.getEmailId());
			}else {
				emailBean.setTo(ApplicationConstant.AMPAMT_EMAIL);
			}
			
			if(emailModel.getUserId()!=null) {
				fileName=emailModel.getUserId()+"_ACC_DTL";

			}
			if(emailModel.getFileExtension()!=null && !"".equals(emailModel.getFileExtension())) {
				fileExtension=emailModel.getFileExtension();
			}
			emailBean.setBase64String(emailModel.getBase64String());
			logger.debug("fileName="+fileName+" fileExtension="+fileExtension);
			emailBean.setFileName(fileName+fileExtension);
			emailBean.setSubject("Customer Details Document");
			scuccessModel.setStatus(emailService.sendIndiaEmail(emailBean));
		}else {
			scuccessModel.setStatus(ApplicationConstant.STATUS_FAILED);
		}
		

		logger.debug("*******************end sendPdfDataEmail***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/send-welcome-email", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> sendDoctorWelcomeEmail(@RequestBody EmailModel emailModel) {
		logger.debug("*******************start sendWelcomeEmail***************************");
		
		File folderSql = new File("./assets/pdf/");
    	System.out.println("Reading files under the folder "+ folderSql.getAbsolutePath());
    	
    	if(folderSql.exists()) {
    		System.out.println("found");
    	}else {
    		System.out.println("not found");
    	}
    	
    	
		SuccessModel scuccessModel=new SuccessModel();
		if(emailModel!=null && emailModel.getEmailId()!=null && emailModel.getName()!=null) {
			EmailBean emailBean=new EmailBean();
			EmailTemplate emailTemplate=new EmailTemplate();
			emailBean.setEmailBody(emailTemplate.getWelcomeEmailTemplate(emailModel.getName()));
			emailBean.setFrom("ampamt.india@gmail.com");
			emailBean.setTo(emailModel.getEmailId());
			emailBean.setFileName("welcome_pdf.pdf");
			
			emailBean.setFilePath("./assets/pdf/doctor_welcome_email.pdf");
			
			
			emailBean.setSubject(CommonConstant.DOCTOR_WELCOME_EMAIL_SUBJECT);
			scuccessModel.setStatus(emailService.sendDoctorWelcomeEmailWithAttachment(emailBean));
		}else {
			scuccessModel.setStatus(ApplicationConstant.STATUS_FAILED);
		}
		

		logger.debug("*******************end sendWelcomeEmail***************************");
		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
}