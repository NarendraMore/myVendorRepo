package com.anemoi.vendor.NewProjectModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class NewProject {

	private String projectid;

	private String clientName;

	private String industry;

	private String projectName;

	private String projectCode;

	private String partnerName;

	private String managerName;

	private ArrayList<String> businessuser=new ArrayList<>();

	private String rfpName;

	private String description;

	private String document;
	
	private ArrayList<BusinessOwner> businessOwner=new ArrayList<>();
	
//	private Document doc;
	
	private long createdOn;

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public ArrayList<String> getBusinessuser() {
		return businessuser;
	}

	public void setBusinessuser(ArrayList<String> businessuser) {
		this.businessuser = businessuser;
	}

	public String getRfpName() {
		return rfpName;
	}

	public void setRfpName(String rfpName) {
		this.rfpName = rfpName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public ArrayList<BusinessOwner> getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(ArrayList<BusinessOwner> businessOwner) {
		this.businessOwner = businessOwner;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "NewProject [projectid=" + projectid + ", clientName=" + clientName + ", industry=" + industry
				+ ", projectName=" + projectName + ", projectCode=" + projectCode + ", partnerName=" + partnerName
				+ ", managerName=" + managerName + ", businessuser=" + businessuser + ", rfpName=" + rfpName
				+ ", description=" + description + ", document=" + document + ", businessOwner=" + businessOwner
				+ ", createdOn=" + createdOn + "]";
	}

	public NewProject(String projectid, String clientName, String industry, String projectName, String projectCode,
			String partnerName, String managerName, ArrayList<String> businessuser, String rfpName, String description,
			String document, ArrayList<BusinessOwner> businessOwner, long createdOn) {
		super();
		this.projectid = projectid;
		this.clientName = clientName;
		this.industry = industry;
		this.projectName = projectName;
		this.projectCode = projectCode;
		this.partnerName = partnerName;
		this.managerName = managerName;
		this.businessuser = businessuser;
		this.rfpName = rfpName;
		this.description = description;
		this.document = document;
		this.businessOwner = businessOwner;
		this.createdOn = createdOn;
	}

	public NewProject() {
		super();
	}

	
}