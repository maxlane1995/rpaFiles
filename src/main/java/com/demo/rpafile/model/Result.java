package com.demo.rpafile.model;

import java.util.ArrayList;
import java.util.List;

public class Result {
	private Boolean success;
	private List<String> errors;
	private String successMessage;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	public void setResult(List<String> errors) {
		this.errors=errors;
		if(errors.isEmpty()) {
			this.success=true;
		}else {
			this.success=false;
		}
	}
	
	public void setError(String error) {
	errors=new ArrayList<String>();
	if(error.isEmpty()) {
		this.success=true;
	}else {

		errors.add(error);
	}
	}
	public void show() {
		System.out.println("You got the object");
	}
}
