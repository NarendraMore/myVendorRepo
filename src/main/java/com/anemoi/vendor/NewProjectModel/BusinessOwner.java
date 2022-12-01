package com.anemoi.vendor.NewProjectModel;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class BusinessOwner {
	
	private String projectOwnerid;
	private String firstname;
	private String lastname;
	private String email;
	public  String projectOwnerId;
	public String getProjectOwnerid() {
		return projectOwnerid;
	}
	public void setProjectOwnerid(String projectOwnerid) {
		this.projectOwnerid = projectOwnerid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProjectOwnerId() {
		return projectOwnerId;
	}
	public void setProjectOwnerId(String projectOwnerId) {
		this.projectOwnerId = projectOwnerId;
	}
	@Override
	public String toString() {
		return "BusinessOwner [projectOwnerid=" + projectOwnerid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", projectOwnerId=" + projectOwnerId + "]";
	}
	public BusinessOwner(String projectOwnerid, String firstname, String lastname, String email,
			String projectOwnerId2) {
		super();
		this.projectOwnerid = projectOwnerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		projectOwnerId = projectOwnerId2;
	}
	public BusinessOwner() {
		super();
	}
	
	
}
