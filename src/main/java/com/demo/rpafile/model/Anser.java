package com.demo.rpafile.model;

import javax.persistence.*;

@Entity
@Table(name = "anser")
public class Anser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "anser")
	private String ansers;
	
	@Column(name = "uuid")
	private String uuid;
	
	@ManyToOne(targetEntity = Question.class)
	@JoinColumn(name = "question")
	private Integer question;
	
	@Column(name = "text")
	private String text;

	public Anser() {
		super();
	}

	public Anser(Integer id, String ansers, String uuid, Integer question, String text) {
		super();
		this.id = id;
		this.ansers = ansers;
		this.uuid = uuid;
		this.question = question;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnsers() {
		return ansers;
	}

	public void setAnsers(String ansers) {
		this.ansers = ansers;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Anser [id=" + id + ", ansers=" + ansers + ", uuid=" + uuid + ", question=" + question + ", text=" + text
				+ "]";
	}
	
	
}





