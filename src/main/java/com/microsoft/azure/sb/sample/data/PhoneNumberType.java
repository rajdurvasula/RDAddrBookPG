package com.microsoft.azure.sb.sample.data;

public enum PhoneNumberType {

	HOME("Home"),
	WORK("Work"),
	MOBILE("Mobile"),
	PRIVATE("Private");
	
	private final String numberType;
	
	PhoneNumberType(String numberType) {
		this.numberType = numberType;
	}
	
	public String getNumberType() {
		return numberType;
	}

}
