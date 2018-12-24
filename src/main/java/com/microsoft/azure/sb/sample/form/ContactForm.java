package com.microsoft.azure.sb.sample.form;

public class ContactForm {

	private Long id;
	private String firstName;
	private String lastName;
	private String groupName;
	private String organization;
	private String notes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		if (null == firstName)
			return "";
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		if (null == lastName)
			return "";
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGroupName() {
		if (null == groupName)
			return "";
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getOrganization() {
		if (null == organization)
			return "";
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getNotes() {
		if (null == notes)
			return "";
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
