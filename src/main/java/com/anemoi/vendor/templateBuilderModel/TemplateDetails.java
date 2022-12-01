package com.anemoi.vendor.templateBuilderModel;

public class TemplateDetails {
	
	
	private String templateId;
	
	private String template_name;
	
	private String discription;
	
	private String createdBy;
	
	private long createdOn;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "TemplateDetails [templateId=" + templateId + ", template_name=" + template_name + ", discription="
				+ discription + ", createdBy=" + createdBy + ", createdOn=" + createdOn + "]";
	}

	public TemplateDetails(String templateId, String template_name, String discription, String createdBy,
			long createdOn) {
		super();
		this.templateId = templateId;
		this.template_name = template_name;
		this.discription = discription;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public TemplateDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
