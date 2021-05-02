package com.demo.rpafile.model;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(targetEntity = Contact_Person.class)
	@JoinColumn(name = "contact_persion")
	private Integer contact_person;
	
	@Column(name = "industry")
	private String industry;
	
	@Column(name = "services")
	private String services;
	
	@Column(name = "volume_of_operations")
	private String volume_of_operations;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;

	public Organization() {
		super();
	}

	public Organization(Integer id, Integer contact_person, String industry, String services,
			String volume_of_operations, String address, String latitude, String longitude) {
		super();
		this.id = id;
		this.contact_person = contact_person;
		this.industry = industry;
		this.services = services;
		this.volume_of_operations = volume_of_operations;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContact_person() {
		return contact_person;
	}

	public void setContact_person(Integer contact_person) {
		this.contact_person = contact_person;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getVolume_of_operations() {
		return volume_of_operations;
	}

	public void setVolume_of_operations(String volume_of_operations) {
		this.volume_of_operations = volume_of_operations;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", contact_person=" + contact_person + ", industry=" + industry
				+ ", services=" + services + ", volume_of_operations=" + volume_of_operations + ", address=" + address
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
}


