package com.ampamt.moduler.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ApplicationUser {

    ADMIN("admin", true),
    USER("user", false),
    ANONYMOUS("anonymous", false);

    private String user;
    private boolean isAdmin;

    ApplicationUser(final String user, final boolean isAdmin) {
        this.user = user;
        this.isAdmin = isAdmin;
    }

    public String toValue() {
        return this.user;
    }
    public boolean isAdmin() {
        return this.isAdmin;
    }

    public class Constant {
        private Constant() {
        }

        public static final String APPLICATION_USER_HEADER = "Application-User";
    }

    private static final Map<String, ApplicationUser> BY_VALUE_MAP = new LinkedHashMap<>();
    static {
        for (ApplicationUser applicationUserEnum : ApplicationUser.values()) {
            BY_VALUE_MAP.put(applicationUserEnum.user, applicationUserEnum);
        }
    }

    public static ApplicationUser getEnumByValue(final String value) {
        return BY_VALUE_MAP.get(value);
    }

}
