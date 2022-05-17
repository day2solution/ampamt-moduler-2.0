package com.ampamt.moduler.exception;

import java.util.HashMap;
import java.util.Map;

public class CustomeException extends Exception{

private static final long serialVersionUID = 4525278265090158765L;
	
	private final Map<String, String> errorDataMap = new HashMap<>();
	
	public enum ErrorCode {
		INVALID_ACCOUNT("invalid.account"),
		INVALID_FUND("invalid.fund"),
		INVALID_ACCOUNT_TYPE("invalid.account.type"),
		INVALID_TRANSACTION("invalid.transaction"),
		INVALID_CURRENCY("invalid.currency"),
		INVALID_CASH_ACCOUNT("missing.cash.account"),
		INVALID_CASH_FUND("missing.cash.fund"),
		INVALID_DATA("invalid.data"),
		INVALID_PARAMETER("invalid.parameter"),
		INVALID_PASSWORD("invalid.password"),
		INVALID_ADVISOR("invalid.advisor"),
		INVALID_ADVISOR_LOGIN("invalid.advisor.login"),
		INVALID_FORM_TYPE("invalid.form.type");

		private String code;

		private ErrorCode(String code) {
			this.code = code;
		}

		public String toValue() {
			return this.code;
		}
	}
	
	public CustomeException(String errorMessage) {
		super(errorMessage);
	}

	public CustomeException(String errorMessage, String... params) {
		super(errorMessage);
		for (int i = 0; i < params.length; i++) {
			this.errorDataMap.put(String.valueOf(i), params[i]);
		}
	}

	public Map<String, String> getErrorDataMap() {
		return errorDataMap;
	}
	
	
}
