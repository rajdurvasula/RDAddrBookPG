package com.microsoft.azure.sb.sample.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact extends AuditModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6526386863353806314L;

	@Id
	@GeneratedValue(generator="contact_generator")
	@SequenceGenerator(name="contact_generator", sequenceName="contact_id_sequence", initialValue=1)
	private long id;
	@Column(name="first_name", length=50)
	private String firstName;
	@Column(name="last_name", length=50)
	private String lastName;
	@Column(name="group_name", length=50)
	private String groupName;
	@Column(name="organization", length=50)
	private String organization;
	@Column(name="job", length=50)
	private String job;
	@Column(name="notes", length=100)
	private String notes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
}
