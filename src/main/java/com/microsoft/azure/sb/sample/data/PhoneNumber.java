package com.microsoft.azure.sb.sample.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="phonenumber")
@TypeDef(name = "pgsql_enum", typeClass=PostgreSQLEnumType.class)
public class PhoneNumber extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 743389240889945895L;

	@Id
	@GeneratedValue(generator="phonenumber_generator")
	@SequenceGenerator(name="phonenumber_generator", sequenceName="phonenumber_id_sequence", initialValue=1)
	private Long id;
	
	@Column(name="number", length=20)
	private String number;
	
	@Enumerated(EnumType.STRING)
	@Column(name="number_type", length=10)
	@Type(type = "pgsql_enum")
	private PhoneNumberType numberType;
	
	@Column(name="preferred", nullable=false)
	private Boolean preferred;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "contact_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Contact contact;

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

	public PhoneNumberType getNumberType() {
		return numberType;
	}

	public void setNumberType(PhoneNumberType numberType) {
		this.numberType = numberType;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
}
