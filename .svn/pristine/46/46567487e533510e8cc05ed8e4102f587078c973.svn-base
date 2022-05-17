package com.ampamt.moduler;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = "com.ampamt.moduler")
public class HibernateUtil
{
	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	
	
	 @Autowired
	 private Environment environment;
	 
	  @Bean 
	  public LocalSessionFactoryBean sessionFactory() 
	  {
		  LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		  sessionFactory.setPackagesToScan("com.ampamt.moduler"); //adding beans to configuration helps to identify table for hibernate
		  sessionFactory.setHibernateProperties(mySqlHibernateProperties());

		  logger.debug("current sessionFactory in HibernateUtil="+sessionFactory.hashCode());
		  return sessionFactory; 
	  }
	  
	  @Bean 
	  public DataSource dataSource() 
	  {
		  DriverManagerDataSource dataSource =new DriverManagerDataSource();

		  dataSource.setConnectionProperties(mySqlHibernateProperties());
		  return dataSource; 
	  }
	  
	  private Properties OracleHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("oracle.hibernate.dialect"));
//	        properties.put("hibernate.show_sql", true);
	        properties.put("hibernate.format_sql", true);
	        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("oracle.db.driver"));
	        properties.put("hibernate.connection.url", environment.getRequiredProperty("oracle.db.url"));
	        properties.put("hibernate.connection.username", environment.getRequiredProperty("oracle.db.username"));
	        properties.put("hibernate.connection.password", environment.getRequiredProperty("oracle.db.password"));
	        properties.put("hibernate.current_session_context_class","thread");
	        return properties;        
	    }
	  private Properties mySqlHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("mysql.hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("mysql.hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("mysql.hibernate.format_sql"));
	        properties.put("hibernate.connection.driver_class", environment.getRequiredProperty("mysql.db.driver"));
	        properties.put("hibernate.connection.url", environment.getRequiredProperty("mysql.db.url"));
	        properties.put("hibernate.connection.username", environment.getRequiredProperty("mysql.db.username"));
	        properties.put("hibernate.connection.password", environment.getRequiredProperty("mysql.db.password"));
	        properties.put("hibernate.current_session_context_class","thread");
	        return properties;        
	    }
}
