package com.anemoi.vendor.templateBuilderService;


import com.anemoi.vendor.templateBuilderModel.TemplateDetails;

public interface templateDetailsService {

	String getMessage();

	String createTemplatedetails(TemplateDetails templateDetails) throws Exception;
	
//	String getAllTemplatedetails();
//	
//	TemplateDetails getTemplatedetails(String templateId);
//	
//	String updateTemplatedetails(TemplateDetails templateDetails,String templateId);
//	
//	String deleteTemplatedetails(String templateId);
//	



}
