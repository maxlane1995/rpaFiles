package com.demo.rpafile.model;
import javax.persistence.*;

@Entity
@Table(name = "organization_answer")
public class Organization_Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(targetEntity = Organization.class)
	@JoinColumn(name = "organization")
	private Integer organization;
	
	@ManyToOne(targetEntity = Question.class)
	@JoinColumn(name = "question")
	private Integer question;
	
	@ManyToOne(targetEntity = Anser.class)
	@JoinColumn(name = "anser")
	private Integer anser;
	
	@Column(name = "text")
	private String text;

	public Organization_Answer() {
		super();
	}

	public Organization_Answer(Integer id, Integer organization, Integer question, Integer anser, String text) {
		super();
		this.id = id;
		this.organization = organization;
		this.question = question;
		this.anser = anser;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganization() {
		return organization;
	}

	public void setOrganization(Integer organization) {
		this.organization = organization;
	}

	public Integer getQuestion() {
		return question;
	}

	public void setQuestion(Integer question) {
		this.question = question;
	}

	public Integer getAnser() {
		return anser;
	}

	public void setAnser(Integer anser) {
		this.anser = anser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Organization_Answer [id=" + id + ", organization=" + organization + ", question=" + question
				+ ", anser=" + anser + ", text=" + text + "]";
	}
	
	
	
}

