package com.ampamt.moduler.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum PlatformType {

    ANDROIDCLIENT("ANDROIDCLIENT"),
    ANDROIDPARTNER("ANDROIDPARTNER"),
    COMMON("COMMON");

    private String platformType;

    PlatformType(final String platformType) {
        this.platformType = platformType;
    }

    public String toValue() {
        return this.platformType;
    }


    private static final Map<String, PlatformType> BY_VALUE_MAP = new LinkedHashMap<>();
    static {
        for (PlatformType platformEnum : PlatformType.values()) {
            BY_VALUE_MAP.put(platformEnum.platformType, platformEnum);
        }
    }

    public static PlatformType getEnumByValue(final String value) {
        if(BY_VALUE_MAP.containsKey(value)){
            return BY_VALUE_MAP.get(value);
        }
        return PlatformType.COMMON;
    }

}
