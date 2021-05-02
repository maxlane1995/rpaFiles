package com.demo.rpafile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "weightage")
	private Integer weightage;
	
	@ManyToOne(targetEntity = Question_Type.class)
	@JoinColumn(name = "question_type")
	private Integer question_type;
	
	@ManyToOne(targetEntity = Question_Step.class)
	@JoinColumn(name = "question_step")
	private Integer question_step;

	public Question() {
		super();
	}

	public Question(Integer id, String uuid, Integer weightage, Integer question_type, Integer question_step) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.weightage = weightage;
		this.question_type = question_type;
		this.question_step = question_step;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getWeightage() {
		return weightage;
	}

	public void setWeightage(Integer weightage) {
		this.weightage = weightage;
	}

	public Integer getQuestion_type() {
		return question_type;
	}

	public void setQuestion_type(Integer question_type) {
		this.question_type = question_type;
	}

	public Integer getQuestion_step() {
		return question_step;
	}

	public void setQuestion_step(Integer question_step) {
		this.question_step = question_step;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", uuid=" + uuid + ", weightage=" + weightage + ", question_type=" + question_type
				+ ", question_step=" + question_step + "]";
	}
	
	
}


