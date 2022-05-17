package com.ampamt.moduler.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class HeaderInterceptorConfig extends WebMvcConfigurerAdapter{

	@Autowired
	RequestHeaderInterceptor requestHeaderInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(requestHeaderInterceptor);
	   }
}
