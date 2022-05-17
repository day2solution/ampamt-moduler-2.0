package com.ampamt.moduler.util;

public class CommonValidator {

	public static boolean isBlank(String str) {
		if(str==null || str=="") {
			return true;
		}else {
			if(str.trim()=="") {
				return true;
			}
		}
		
		return false;
	}
}
