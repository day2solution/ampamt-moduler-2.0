package com.ampamt.moduler.service.email;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.email.EmailBean;
import com.ampamt.moduler.constant.ApplicationConstant;

@Service
public class EmailServiceImpl implements EmailService{

	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	


	@Override
	public String sendEmail(EmailBean emailBean) 
	{
		logger.debug("************** start sendEmail**************");
		String status=ApplicationConstant.STATUS_SUCCESS;
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
		
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setFrom(emailBean.getFrom());
    	helper.setTo(emailBean.getTo());
    	helper.setSubject(emailBean.getSubject());
    	helper.setText(emailBean.getEmailBody(),true);
		mailSender.send(message);
		
		status=ApplicationConstant.STATUS_SUCCESS;
		
		}
		catch(Exception e) {
			status=ApplicationConstant.STATUS_FAILED;
			logger.debug("exception during email send="+e);
			e.printStackTrace();
			
		}
		logger.debug("************** end sendOTPEmail**************");
		return status;
	}


	@Override
	public String sendOTPEmail(EmailBean emailBean) {
		logger.debug("************** start sendEmail**************");
		String status=ApplicationConstant.STATUS_SUCCESS;
		
		try {
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			MimeMessage message = mailSender.createMimeMessage();
	        Properties mailProperties = new Properties();
	        mailProperties.put("mail.smtp.auth", true);
	        mailProperties.put("mail.smtp.starttls.enable", true);
	        mailProperties.put("mail.transport.protocol", 25);
	        mailProperties.put("mail.debug", true);
	        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//	        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//	        mailProperties.put("mail.smtp.socketFactory.fallback", fallback);
	        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");

	        mailSender.setJavaMailProperties(mailProperties);
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(25);
	        mailSender.setProtocol("smtp");
	        mailSender.setUsername("ampamt.otp@gmail.com");
	        mailSender.setPassword("ampamt123456");
	        
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    	helper.setFrom(emailBean.getFrom());
	    	helper.setTo(emailBean.getTo());
	    	helper.setSubject(emailBean.getSubject());
	    	helper.setText(emailBean.getEmailBody(),true);
			mailSender.send(message);
			
			status=ApplicationConstant.STATUS_SUCCESS;
		
		}
		catch(Exception e) {
			status=ApplicationConstant.STATUS_FAILED;
			logger.debug("exception during email send="+e);
			e.printStackTrace();
			
		}
		logger.debug("sendOTPEmail status="+status);
		logger.debug("************** end sendOTPEmail**************");
		return status;
	}


	@Override
	public String sendIndiaEmail(EmailBean emailBean) {
		logger.debug("************** start sendIndiaEmail**************");
		String status=ApplicationConstant.STATUS_SUCCESS;
		
		try {
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			MimeMessage message = mailSender.createMimeMessage();
	        Properties mailProperties = new Properties();
	        mailProperties.put("mail.smtp.auth", true);
	        mailProperties.put("mail.smtp.starttls.enable", true);
	        mailProperties.put("mail.transport.protocol", 25);
	        mailProperties.put("mail.debug", true);
	        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        mailSender.setJavaMailProperties(mailProperties);
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(25);
	        mailSender.setUsername("ampamt.india@gmail.com");
	        mailSender.setPassword("Ampamt156666");
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    	helper.setFrom(emailBean.getFrom());
	    	helper.setTo(emailBean.getTo());
	    	helper.setSubject(emailBean.getSubject());
	    	helper.setText(emailBean.getEmailBody(),true);
	    	
	    	if(emailBean.getBase64String()!=null && emailBean.getFileName()!=null) {
	    		String base64String= emailBean.getBase64String().replaceFirst("data:application/pdf;base64,", "");
	    		byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64String);
	    		
	        	helper.addAttachment(emailBean.getFileName(), new ByteArrayResource(bytes));
			}

			mailSender.send(message);
			
			status=ApplicationConstant.STATUS_SUCCESS;
		
		}
		catch(Exception e) {
			status=ApplicationConstant.STATUS_FAILED;
			logger.debug("exception during email send="+e);
			e.printStackTrace();
			
		}
		logger.debug("************** end sendIndiaEmail**************");
		return status;
	}


	@Override
	public String sendServicesEmail(EmailBean emailBean) {
		logger.debug("************** start sendServicesEmail**************");
		String status=ApplicationConstant.STATUS_SUCCESS;
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
		
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setFrom(emailBean.getFrom());
    	helper.setTo(emailBean.getTo());
    	helper.setSubject(emailBean.getSubject());
    	helper.setText(emailBean.getEmailBody(),true);
		mailSender.send(message);
		
		status=ApplicationConstant.STATUS_SUCCESS;
		
		}
		catch(Exception e) {
			status=ApplicationConstant.STATUS_FAILED;
			logger.debug("exception during email send="+e);
			e.printStackTrace();
			
		}
		logger.debug("************** end sendServicesEmail**************");
		return status;
	}
	
	@Override
	public String sendDoctorWelcomeEmailWithAttachment(EmailBean emailBean) {
		logger.debug("************** start sendDoctorWelcomeEmailWithAttachment**************");
		String status=ApplicationConstant.STATUS_SUCCESS;
		
		try {
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			MimeMessage message = mailSender.createMimeMessage();
	        Properties mailProperties = new Properties();
	        mailProperties.put("mail.smtp.auth", true);
	        mailProperties.put("mail.smtp.starttls.enable", true);
	        mailProperties.put("mail.transport.protocol", 25);
	        mailProperties.put("mail.debug", true);
	        mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        mailSender.setJavaMailProperties(mailProperties);
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(25);
	        mailSender.setUsername("ampamt.india@gmail.com");
	        mailSender.setPassword("Ampamt156666");
	    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    	helper.setFrom(emailBean.getFrom());
	    	helper.setTo(emailBean.getTo());
	    	helper.setSubject(emailBean.getSubject());
	    	helper.setText(emailBean.getEmailBody(),true);
	    
	    	if(emailBean.getFilePath()!=null) {
	    		Path path = Paths.get(emailBean.getFilePath());
	    		logger.debug("path="+path);
	    		byte[] content = Files.readAllBytes(path);

	    		if(emailBean.getFileName()==null) {
	    			emailBean.setFileName("ampamt.pdf");
	    		}
	    		 helper.addAttachment(emailBean.getFileName(), new ByteArrayResource(content));
	    		 
	    	}
	       
			mailSender.send(message);
			
			status=ApplicationConstant.STATUS_SUCCESS;
		
		}
		catch(Exception e) {
			status=ApplicationConstant.STATUS_FAILED;
			logger.debug("exception during email send="+e);
			e.printStackTrace();
			
		}
		logger.debug("************** end sendDoctorWelcomeEmailWithAttachment**************");
		return status;
	}


	  public static void main(String [] args) 
	    {   

	    	File folderSql = new File("./assets/pdf/doctor_welcome_email.pdf");
	    	System.out.println("Reading files under the folder "+ folderSql.getAbsolutePath());
	    	File[] listOfFiles = folderSql.listFiles();

	    	for (int i = 0; i < listOfFiles.length; i++) {
	    	  if (listOfFiles[i].isFile()) {
	    	    System.out.println("File " + listOfFiles[i].getName());
	    	  } else if (listOfFiles[i].isDirectory()) {
	    	    System.out.println("Directory " + listOfFiles[i].getName());
	    	  }
	    	}
	    	if(folderSql.exists()) {
	    		System.out.println("found");
	    	}else {
	    		System.out.println("not found");
	    	}

			    
		   }
}
