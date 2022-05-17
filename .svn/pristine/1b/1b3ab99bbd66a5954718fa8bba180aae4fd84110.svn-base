package com.ampamt.moduler.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ampamt.moduler.constant.AppUser;
import com.ampamt.moduler.constant.Application;
import com.ampamt.moduler.constant.Channel;
import com.ampamt.moduler.constant.CommandType;
import com.ampamt.moduler.constant.Platform;
import com.ampamt.moduler.constant.PlatformType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtil {

    private static Logger logger = LoggerFactory.getLogger(ApiUtil.class);

    private ApiUtil() {
    }

    public static final Integer MAX_RESPONSE_RESULT = 50;

    public static Platform getPlatform() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        return Platform.getEnumByValue(req.getHeader(Platform.Constant.PLATFORM_HEADER));
    }

    public static PlatformType getPlatformType() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        return PlatformType.getEnumByValue(req.getHeader(Platform.Constant.PLATFORM_HEADER));
    }

    public static Application getApplication() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        return Application.getEnumByValue(req.getHeader(Application.Constant.APPLICATION_HEADER));
    }

    public static CommandType getCommandType() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        return CommandType.getEnumByValue(req.getHeader(CommandType.Constant.COMMAND_HEADER));
    }

    public static String getIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        return req.getHeader(IpUtil.IP);
    }

	public static String getChannel() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

		return req.getHeader(Channel.Constant.CHANNEL_HEADER);
	}

    public static AppUser getAppUser() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();

        AppUser appUser = null;

        Application application = getApplication();
        logger.debug("application in APIutil="+application);
        if (application != null && CollectionUtils.isNotEmpty(application.getImplementedAppUsers())) {
            try {
            	logger.debug("application.getImplementedAppUsers in APIutil="+application.getImplementedAppUsers());
                String applicationUserString = req.getHeader(ApplicationUser.Constant.APPLICATION_USER_HEADER);
                logger.debug("applicationUserString ="+applicationUserString);
                AppUser tempAppUser = new ObjectMapper().readValue(applicationUserString, AppUser.class);
                logger.debug("application in tempAppUser="+tempAppUser);
                if (application.getImplementedAppUsers().contains(tempAppUser.getUser())) {
                    appUser = tempAppUser;
                }

            } catch (Exception e) {
                logger.error(String.format("[ApiUtil] Invalid required header %s.", ApplicationUser.Constant.APPLICATION_USER_HEADER));
            }
        }

        return appUser;
    }

    public static String getVersionInfo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();
        return req.getHeader("User-Agent");
    }
}
