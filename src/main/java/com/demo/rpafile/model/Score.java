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
@Table(name = "score")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "score")
	private Integer score;
	
	@ManyToOne(targetEntity = Question_Step.class)
	@JoinColumn(name = "question_step")
	private String question_step;
	
	@ManyToOne(targetEntity = Organization.class)
	@JoinColumn(name = "organization")
	private String organization;

	public Score() {
		super();	
	}

	public Score(Integer id, Integer score, String question_step, String organization) {
		super();
		this.id = id;
		this.score = score;
		this.question_step = question_step;
		this.organization = organization;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getQuestion_step() {
		return question_step;
	}

	public void setQuestion_step(String question_step) {
		this.question_step = question_step;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", score=" + score + ", question_step=" + question_step + ", organization="
				+ organization + "]";
	}
	
	
	
}

