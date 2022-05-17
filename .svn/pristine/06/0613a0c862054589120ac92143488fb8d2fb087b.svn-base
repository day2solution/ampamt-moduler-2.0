package com.ampamt.moduler.constant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Application {

	MOBILE_APP_CLIENT("AMPAMT", "AMPAMT",null);

	private String application;
	private String displayName;
	private List<ApplicationUser> implementedAppUsers;

	Application(final String application, String displayName, final List<ApplicationUser> appUsers) {
		this.application = application;
		this.displayName = displayName;
		this.implementedAppUsers = appUsers;
	}

	public String toValue() {
		return this.application;
	}

	public String toDisplayName() {
		return displayName;
	}

	public List<ApplicationUser> getImplementedAppUsers() {
		return this.implementedAppUsers;
	}

	public class Constant {
		private Constant() {
		}

		public static final String APPLICATION_HEADER = "Application";
	}

	private static final Map<String, Application> BY_VALUE_MAP = new LinkedHashMap<>();
	static {
		for (Application applicationEnum : Application.values()) {
			BY_VALUE_MAP.put(applicationEnum.application, applicationEnum);
		}
	}

	public static Application getEnumByValue(final String value) {
		return BY_VALUE_MAP.get(value);
	}

}
