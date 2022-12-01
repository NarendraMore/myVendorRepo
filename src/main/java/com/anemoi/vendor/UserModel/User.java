package com.anemoi.vendor.UserModel;


import java.util.Date;

import io.micronaut.core.annotation.Introspected;


@Introspected
public class User {
	
	private String id;	
	
	private String firstName;
	
	private String lastName;	
	
	private String mobileNumber;
	
	private String email;	
	
	private String manager;
	
	private String partner;
	
//	private ArrayList<String> vendorTemplateAccess = new ArrayList<>();
//	
//	private ArrayList<String> dashboardAccess = new ArrayList<>();
//	
//	private ArrayList<String> masterRepoAccess = new ArrayList<>();
//	
	private String userStatus;
	
	private long createdOn;

	private String roleName;	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber="
				+ mobileNumber + ", email=" + email + ", manager=" + manager + ", partner=" + partner + ", userStatus="
				+ userStatus + ", createdOn=" + createdOn + ", roleName=" + roleName + "]";
	}

	public User(String id, String firstName, String lastName, String mobileNumber, String email, String manager,
			String partner, String userStatus, long createdOn, String roleName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.manager = manager;
		this.partner = partner;
		this.userStatus = userStatus;
		this.createdOn = createdOn;
		this.roleName = roleName;
	}

	public User() {
		super();
	}

	

	
}
