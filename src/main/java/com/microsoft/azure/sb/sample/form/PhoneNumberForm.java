package com.microsoft.azure.sb.sample.form;

public class PhoneNumberForm {

	private Long id;
	private String number;
	private String numberType;
	private Boolean preferred;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumberType() {
		return numberType;
	}
	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}
	public Boolean getPreferred() {
		return preferred;
	}
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	
}
