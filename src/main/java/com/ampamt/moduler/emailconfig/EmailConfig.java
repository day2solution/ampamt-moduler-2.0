package com.ampamt.moduler.emailconfig;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	private final Logger logger = LoggerFactory.getLogger(EmailConfig.class);
	
	@Autowired
	 private Environment environment;
	
//	@Bean
//    public JavaMailSender getJavaMailSender() 
//    {
//		logger.debug("**************************start getJavaMailSender**********************");
//		
//		logger.debug("mail config started");
//		logger.debug("username="+environment.getRequiredProperty("smtp.username"));
//		logger.debug("password="+environment.getRequiredProperty("smtp.password"));
//		
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(environment.getRequiredProperty("smtp.host"));
//        mailSender.setPort(Integer.valueOf(environment.getRequiredProperty("smtp.port")));
//        mailSender.setUsername(environment.getRequiredProperty("smtp.username"));
//        mailSender.setPassword(environment.getRequiredProperty("smtp.password"));
//          
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", environment.getRequiredProperty("mail.transport.protocol"));
//        props.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
//        props.put("mail.smtp.starttls.enable",environment.getRequiredProperty("mail.smtp.starttls.enable"));
//        props.put("mail.smtp.ssl.enable",environment.getRequiredProperty("smtp.ssl.enable"));
//        props.put("mail.smtp.ssl.trust",environment.getRequiredProperty("smtp.ssl.trust"));
//        props.put("mail.debug", environment.getRequiredProperty("mail.debug"));
//         
//        
//        logger.debug("**************************end getJavaMailSender**********************");
//        return mailSender;
//    }
	
	@Bean
    public JavaMailSender getJavaMailSender() 
    {
		logger.debug("**************************start getJavaMailSender**********************");
		
		logger.debug("mail config started");
	
        JavaMailSenderImpl ampamtMailSender = new JavaMailSenderImpl();
        ampamtMailSender.setHost(environment.getRequiredProperty("ampamt.services.smtp.host"));
        ampamtMailSender.setPort(Integer.valueOf(environment.getRequiredProperty("ampamt.services.smtp.port")));
        ampamtMailSender.setUsername(environment.getRequiredProperty("ampamt.services.smtp.username"));
        ampamtMailSender.setPassword(environment.getRequiredProperty("ampamt.services.smtp.password"));
          
        Properties props = ampamtMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", environment.getRequiredProperty("ampamt.services.mail.transport.protocol"));
        props.put("mail.smtp.auth", environment.getRequiredProperty("ampamt.services.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable",environment.getRequiredProperty("ampamt.services.mail.smtp.starttls.enable"));
        props.put("mail.debug", environment.getRequiredProperty("ampamt.services.mail.debug"));
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
         
        
        logger.debug("**************************end getJavaMailSender**********************");
        return ampamtMailSender;
    }
}
