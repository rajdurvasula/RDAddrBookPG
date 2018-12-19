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
@Table(name="address")
@TypeDef(name = "pgsql_enum", typeClass=PostgreSQLEnumType.class)
public class Address extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8857776590662004310L;

	@Id
	@GeneratedValue(generator="address_generator")
	@SequenceGenerator(name="address_generator", sequenceName="address_id_sequence",initialValue=1)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name="address_type", length=10)
	@Type(type = "pgsql_enum")
	private AddressType addressType;
	@Column(name="preferred", nullable=false)
	private Boolean preferred;
	@Column(name="unit_number", length=20)
	private String unitNumber;
	@Column(name="street", length=50)
	private String street;
	@Column(name="locality", length=50)
	private String locality;
	@Column(name="city", length=50)
	private String city;
	@Column(name="territory", length=50)
	private String territory;
	@Column(name="country", length=50)
	private String country;
	@Column(name="postal_code", length=20)
	private String postalCode;
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
	public AddressType getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	public Boolean getPreferred() {
		return preferred;
	}
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
}
