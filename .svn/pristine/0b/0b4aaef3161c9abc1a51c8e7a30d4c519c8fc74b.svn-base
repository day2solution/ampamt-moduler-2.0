package com.ampamt.moduler.emailtemplates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.EmailModel;

public class EmailTemplate {

	private final Logger logger = LoggerFactory.getLogger(EmailTemplate.class);
	public String getPasswordResetTemplate(String otp,String toName) {
		logger.debug("********************start getPasswordResetTemplate**********************");
		String emailBody="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<title>Password Reset OTP</title>\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"div.header,.fixed-footer {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				" padding:10px;\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"div.textBody {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"header\"><img height='100px' width='100px' src='"+ApplicationConstant.AMPAMT_IMG_URL+"'></div>\r\n" + 
				"<br>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div class=\"textBody\">Dear "+(toName!=null?toName:"")+",</div>\r\n" + 
				"<div class=\"textBody\">\r\n" + 
				"<p>\r\n" + 
				"Your OTP (One Time Password) is &nbsp;<b>"+otp.trim()+"</b> and is valid for 5 minutes.\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Please do not share this OTP with anyone.\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"fixed-footer\">\r\n" + 
				"<p>\r\n" + 
				"This email is sent from an account we use for sending messages. So if you want to contact us,reply to this email or visit our website "
				+ "<a href=\"www.ampamt.com/\" target=\"_blank\">www.ampamt.com</a>\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"</html>";
		
		logger.debug("getPasswordResetTemplate emailBody="+emailBody);
		
		logger.debug("********************end getPasswordResetTemplate**********************");
		return emailBody;
		
	}
	
	
	public String getMessageTemplate(EmailModel emailModel) {
		logger.debug("********************start getMessageTemplate**********************");
		String emailBody="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<title>Customer Enquiry Details</title>\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"div.header,.fixed-footer {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				" padding:10px;\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"div.textBody {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"table {\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"table, th, td {\r\n" + 
				"  border: 1px solid #5C0202;\r\n" + 
				"  padding:5px;\r\n" + 
				"}\r\n" + 
				"th{\r\n" + 
				"background-color: #5C0202;\r\n" + 
				"  color: white;\r\n" + 
				"}"
				+ ".logoimg{\r\n" + 
				"height:100px;\r\n" + 
				" width:100px;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"header\"><img class='logoimg' src='"+ApplicationConstant.AMPAMT_IMG_URL+"'/></div>\r\n" + 
				"<br>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div class=\"textBody\">Dear Team,</div>\r\n" + 
				"<div class=\"textBody\">\r\n" + 
				"<p>\r\n" + 
				"Please find the following enquiry details regarding &nbsp;<b>"+emailModel.getRegards().toUpperCase()+"</b>.<br><br>\r\n" + 
				"\r\n" + 
				"<table border=\"1px\">\r\n" + 
				"<thead>\r\n" + 
				"<th colspan='100%'>\r\n" + 
				"Customer Details\r\n" + 
				"</th>\r\n" + 
				"</thead>\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"User ID\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getUserId()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"Account Type\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getAccountType()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"Name\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getName()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"Contact No.\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getContactNo()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"Email-ID\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getEmailId()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"City\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getCity()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"State\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getState()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td>\r\n" + 
				"Message\r\n" + 
				"</td>\r\n" + 
				"<td>\r\n" + 
				""+emailModel.getMessage()+"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</p>\r\n" + 
				"\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"fixed-footer\">\r\n" + 
				"<p>\r\n" + 
//				"This email is sent from an account we use for sending messages. So if you want to contact us,reply to this email or visit our website "
				 "<a href=\"www.ampamt.com/\" target=\"_blank\">www.ampamt.com</a>\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		logger.debug("getMessageTemplate emailBody="+emailBody);
		
		logger.debug("********************end getMessageTemplate**********************");
		return emailBody;
		
	}
	public String getEmailverificationTemplate(String name,String otp) {
		logger.debug("********************start getUserWelcomeTemplate**********************");
		String emailBody="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<title>Email address verfication</title>\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"div.header,.fixed-footer {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				" padding:10px;\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"div.textBody {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"header\"><img height=\"100px\" width=\"100px\"  src='"+ApplicationConstant.AMPAMT_IMG_URL+"'></div>\r\n" + 
				"<br>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div class=\"textBody\">Welcome to <b>\"Alternative Medical Practice And Medicine Treatment\"</b></div>\r\n\r\n\r\n" + 
				"<div class=\"textBody\">Dear "+(name!=null?name:"Sir/Madam")+",</div>\r\n" + 
				"<div class=\"textBody\">\r\n" + 
				"<p>\r\n" + 
				"We received a request for verification of this e-mail address for <b>AMPAMT</b> Application registration.\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Your OTP (One Time Password) is &nbsp;<b>"+otp.trim()+"</b>\r\n" + 
				"<br>\r\n" + 
				"Please use OTP, to proceed ahead in the application.\r\n" + 
				"\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Regards, \r\n" + 
				"<br>\r\n" + 
				"Administrator,\r\n" + 
				"<br>\r\n" + 
				"AMPAMT online application.\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Note:This is a system generated message, please do not reply to it.\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Notice: The information contained in this e-mail message and/or attachments to it may contain confidential or privileged information. If you are not the intended recipient, any dissemination, use, review, distribution, printing or copying of the information contained in this e-mail message and/or attachments to it are strictly prohibited. If you have received this communication in error, please notify us by reply e-mail or telephone and immediately and permanently delete the message and any attachments.\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"</html>";
		
		logger.debug("********************end getUserWelcomeTemplate**********************");
		return emailBody;
	}
	public String getWelcomeEmailTemplate(String name) {
		
		String emailBody="";
		emailBody="<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<title>Welcome</title>\r\n" + 
				"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"<head>\r\n" + 
				"<style>\r\n" + 
				"div.header,.fixed-footer {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				" padding:10px;\r\n" + 
				" \r\n" + 
				"}\r\n" + 
				"div.textBody {\r\n" + 
				"  max-width:500px;\r\n" + 
				"  margin: auto;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"header\"><img height='100px' width='100px' src='https://ampamt.com/assets/images/icon.png'></div>\r\n" + 
				"<br>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div class=\"textBody\">To ,<br>"+(name!=null?name:"")+"</div>\r\n" + 
				"<div class=\"textBody\">\r\n" + 
				"<p>\r\n" + 
				"The Registered members from &#34;AMPAMT&#34;\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Dear Applicant,\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"With reference to your application for Therapist/ Practitioner/ R.M.P(AM)/ Dr. (AM) registration in the AMPAMT mobile application, kindly follow the procedure for obtaining the Certificate of Therapist / Practitioner / R.A.M.P / R.M.P(AM) / Dr. (AM) as mentioned below in the attached file: \r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"Kindly Download the document attached and follow the instruction.\r\n" + 
				"</p>\r\n" + 
				"<p>\r\n" + 
				"\r\n" + 
				"Alternate Medical Council Of India<br>\r\n" + 
				"Administration (Back Office)\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"fixed-footer\">\r\n" + 
				"<p>\r\n" + 
				"In case of any related query or issue please feel free to contact us on 9930369699 or visit our website <a href=\"www.ampamt.com/\" target=\"_blank\">www.ampamt.com</a>\r\n" + 
				"</p>\r\n" + 
				"</div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		logger.debug("welcome emailBody="+emailBody);
		return emailBody;
	}
}
