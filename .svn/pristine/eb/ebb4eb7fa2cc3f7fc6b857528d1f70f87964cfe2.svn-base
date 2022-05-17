package com.ampamt.moduler.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum Platform {

	APP("ANDROID","ANDROID");

	private String platform;
	private String displayName;
	
	Platform(final String platform, String displayName) {
		this.platform = platform;
		this.displayName = displayName;
	}

	public String toValue() {
		return this.platform;
	}
	
	public String toDisplayName(){
		return this.displayName;
	}

	public class Constant {
		private Constant() {
		}
		public static final String PLATFORM_HEADER = "Platform";
	}

	private static final Map<String, Platform> BY_VALUE_MAP = new LinkedHashMap<>();
	static {
		for (Platform platformEnum : Platform.values()) {
			BY_VALUE_MAP.put(platformEnum.platform, platformEnum);
		}
	}

	public static Platform getEnumByValue(final String value) {
		return BY_VALUE_MAP.get(value);
	}

}
