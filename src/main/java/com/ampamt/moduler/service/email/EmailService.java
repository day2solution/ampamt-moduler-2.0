package com.ampamt.moduler.service.email;

import com.ampamt.moduler.bean.email.EmailBean;

public interface EmailService {

	String sendEmail(EmailBean emailBean);
	String sendOTPEmail(EmailBean emailBean);
	String sendIndiaEmail(EmailBean emailBean);
	String sendServicesEmail(EmailBean emailBean);
	String sendDoctorWelcomeEmailWithAttachment(EmailBean emailBean);

}
