package com.microsoft.azure.sb.sample.data;

public enum AddressType {

	HOME("Home"),
	WORK("Work"),
	BOX("Box"),
	Private("Private");
	
	final String typeName;
	
	AddressType(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}

}
