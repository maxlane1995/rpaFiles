package com.demo.rpafile.model;

import javax.persistence.*;

@Entity
@Table(name = "question_step")
public class Question_Step {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "uuid")
	private String uuid;

	public Question_Step() {
		super();
	}

	public Question_Step(Integer id, String name, String uuid) {
		super();
		this.id = id;
		this.name = name;
		this.uuid = uuid;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "Question_Step [id=" + id + ", name=" + name + ", uuid=" + uuid + "]";
	}
	
	

}



