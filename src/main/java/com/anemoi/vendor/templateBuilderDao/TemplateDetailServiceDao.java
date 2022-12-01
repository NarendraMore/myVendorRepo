package com.anemoi.vendor.templateBuilderDao;

import java.sql.SQLException;
import java.util.List;


import com.anemoi.vendor.templateBuilderModel.TemplateDetails;

public interface TemplateDetailServiceDao {

	String createTemplate(TemplateDetails templateDetails, String dataBaseName) throws SQLException, ClassNotFoundException;
//	
//	List<TemplateDetails> getAllTemplatedetails(String dataBaseName);
//	
//	TemplateDetails getTemplatedetailsById(String templateId, String dataBaseName);
//	
//	TemplateDetails editTemplatedetails(TemplateDetails templatedetails,String templateId,String dataBaseName);
//	
//	void deleteTemplatedetails(String templateId,String dataBaseName);



}
