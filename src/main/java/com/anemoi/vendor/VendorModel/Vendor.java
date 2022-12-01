package com.anemoi.vendor.VendorModel;

import java.util.Date;

public class Vendor {
	
	private String vendorId;
	
	private String vendorName;
	
	private String spocName;
	
	private String email;
	
	private String contactNumber;
	
	private String lineOfBusiness;
	
	private long createdOn;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getSpocName() {
		return spocName;
	}

	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", spocName=" + spocName + ", email="
				+ email + ", contactNumber=" + contactNumber + ", lineOfBusiness=" + lineOfBusiness + ", createdOn="
				+ createdOn + "]";
	}

	public Vendor(String vendorId, String vendorName, String spocName, String email, String contactNumber,
			String lineOfBusiness, Long createdOn) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.spocName = spocName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.lineOfBusiness = lineOfBusiness;
		this.createdOn = createdOn;
	}

	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
