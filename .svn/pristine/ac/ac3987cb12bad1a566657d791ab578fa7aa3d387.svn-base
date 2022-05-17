package com.ampamt.moduler.util;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IpUtil {

	private static Logger logger = LoggerFactory.getLogger(IpUtil.class);

	public static final String IP = "Ip";

	public static final String getClientIp() {
		String remoteAddr = ""; // default client ip address for protected API
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		try {
			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
			remoteAddr = httpServletRequest.getHeader("X-Forwarded-For");
			if (StringUtils.isEmpty(remoteAddr)) {
				remoteAddr = httpServletRequest.getRemoteAddr();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			logger.error("error getting client ip - null pointer getting request");
		}
		return remoteAddr;

	}

	public static final String getServerIp() {
		String serverIP = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();

			byte[] ipAddr = addr.getAddress();
			String ipAddrStr = "";
			for (int i = 0; i < ipAddr.length; i++) {
				if (i > 0)
					ipAddrStr += ".";
				ipAddrStr += ipAddr[i] & 0xFF;
			}
			serverIP = ipAddrStr;
		} catch (Exception e) {
			serverIP = "";
		}
		return serverIP;
	}

}
