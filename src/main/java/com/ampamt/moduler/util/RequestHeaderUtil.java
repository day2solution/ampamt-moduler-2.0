package com.ampamt.moduler.util;

import com.ampamt.moduler.constant.AppUser;

public class RequestHeaderUtil {

    public static final String HEADER_NAME = "MODULAR-REQ-HEADER";
    private String platform;
    private String ip;
    private String application;
    private AppUser user;

    public RequestHeaderUtil() {
    }

    public RequestHeaderUtil(String platform, String ip, String application) {
        this.platform = platform;
        this.ip = ip;
        this.application = application;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

}
