package com.ampamt.moduler.interceptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ampamt.moduler.constant.Application;
import com.ampamt.moduler.constant.Platform;
import com.ampamt.moduler.exception.CustomeException;
import com.ampamt.moduler.util.IpUtil;
import com.ampamt.moduler.util.RequestHeaderUtil;

@Component
public class RequestHeaderInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(RequestHeaderInterceptor.class);
	private static final Set<String> URI_NOT_REQUIRE_HEADER = new HashSet<>();
	static {
        URI_NOT_REQUIRE_HEADER.add("/swagger/**");
        URI_NOT_REQUIRE_HEADER.add("/swagger-resources/**");
        URI_NOT_REQUIRE_HEADER.add("/rest/payment/get-payment-response");
        
    }
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.debug("Pre Handle method is Calling");
		  String platform = request.getHeader(Platform.Constant.PLATFORM_HEADER);
	        String ip = request.getHeader(IpUtil.IP);
	        String application = request.getHeader(Application.Constant.APPLICATION_HEADER);
	        RequestHeaderUtil requestHeaderUtil = new RequestHeaderUtil(platform, ip, application);
	//
	        if (requireHeader(request)) {
//	            validateRequestHeader(requestHeaderUtil);
	        }

	        request.setAttribute(RequestHeaderUtil.HEADER_NAME, requestHeaderUtil);
	        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.debug("Post Handle method is Calling");
		logger.debug("getStatus=" + response.getStatus());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		logger.debug("Request and Response is completed");
	}
	

    private void validateRequestHeader(RequestHeaderUtil requestHeaderUtil) throws CustomeException {

        String errorMessage = "[RequestHeaderInterceptor] Missing request header %s for method parameter of type String";

        if (StringUtils.isEmpty(requestHeaderUtil.getPlatform())) {
            throw new CustomeException(String.format(errorMessage, Platform.Constant.PLATFORM_HEADER));
        }

        else if (StringUtils.isEmpty(requestHeaderUtil.getIp())) {
            throw new CustomeException(String.format(errorMessage, IpUtil.IP));
        }

        else if (StringUtils.isEmpty(requestHeaderUtil.getApplication())) {
            throw new CustomeException(String.format(errorMessage, Application.Constant.APPLICATION_HEADER));
        } else {
        	validateHeaderValue(requestHeaderUtil);
        }
    }

//    private void validateAppUser(RequestHeaderUtil requestHeaderUtil) throws CustomeException {
//    	 
//        Application application = Application.getEnumByValue(requestHeaderUtil.getApplication());
//        
//        Platform platform=Platform.getEnumByValue(requestHeaderUtil.getPlatform());
//        
//        logger.debug("requestHeaderUtil.getApplication()="+requestHeaderUtil.getApplication());
////  	  logger.debug("application.getImplementedAppUsers()="+application.getImplementedAppUsers());
//  	  
//        logger.debug("application="+application);
//        logger.debug("platform="+platform);
//        if (application == null || platform==null)
//        {
//        	throw new CustomeException(String.format("[RequestHeaderInterceptor] Missing/Invalid required header %s.", requestHeaderUtil.getPlatform()));
//        }
//        if (application != null && CollectionUtils.isNotEmpty(application.getImplementedAppUsers())) {
//        	logger.debug("application.getImplementedAppUsers()="+application.getImplementedAppUsers());
//            AppUser appUser = ApiUtil.getAppUser();
//            logger.debug("appUser="+appUser);
//            if (appUser != null) {
//                requestHeaderUtil.setUser(appUser);
//            } else {
//                throw new CustomeException(String.format("[RequestHeaderInterceptor] Missing/Invalid required header %s.", ApplicationUser.Constant.APPLICATION_USER_HEADER));
//            }
//        }
//    }

    private Boolean requireHeader(HttpServletRequest request) {

        List<RequestMatcher> antPathRequestMatchers = new ArrayList<>();
        for (String pattern : URI_NOT_REQUIRE_HEADER) {
            antPathRequestMatchers.add(new AntPathRequestMatcher(pattern));
        }
        RequestMatcher requestMatcher = new OrRequestMatcher(antPathRequestMatchers);

        if (requestMatcher.matches(request)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
    public void validateHeaderValue(RequestHeaderUtil requestHeaderUtil) throws CustomeException{
		
		 Application application = Application.getEnumByValue(requestHeaderUtil.getApplication());
	        
	     Platform platform=Platform.getEnumByValue(requestHeaderUtil.getPlatform());
	     
	     if(application==null)
	     {
	    	 throw new CustomeException(String.format("[RequestHeaderInterceptor] Missing/Invalid required header %s.", Application.Constant.APPLICATION_HEADER));
	     }
	     
	     if(platform==null)
	     {
	    	 throw new CustomeException(String.format("[RequestHeaderInterceptor] Missing/Invalid required header %s.", Platform.Constant.PLATFORM_HEADER));
	     }
	}

	
}
