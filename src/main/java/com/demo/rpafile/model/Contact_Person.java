package com.demo.rpafile.model;

import javax.persistence.*; 

@Entity
@Table(name = "contact_person")
public class Contact_Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "email_id")
	private String email_id;
	
	@Column(name = "mobile_number")
	private String mobile_number;
	
	public Contact_Person() {
		super();
	}
	
	public Contact_Person(Integer id, String name, String designation, String email_id, String mobile_number) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.email_id = email_id;
		this.mobile_number = mobile_number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	@Override
	public String toString() {
		return "Contact_Person [id=" + id + ", name=" + name + ", designation=" + designation + ", email_id=" + email_id
				+ ", mobile_number=" + mobile_number + "]";
	}

	
	
}








