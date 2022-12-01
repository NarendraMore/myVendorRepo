package com.anemoi.vendor.templateBuilderService;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anemoi.vendor.configuration.DatabaseName;
import com.anemoi.vendor.templateBuilderDao.TemplateDetailServiceDao;
import com.anemoi.vendor.templateBuilderModel.TemplateDetails;

@Singleton
public class TemplatedetailsServiceImpl implements templateDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(TemplatedetailsServiceImpl.class);

	@Inject
	private TemplateDetailServiceDao templateDetailServiceDao;

	@Override
	public String getMessage() {
		System.out.println("Hello Shubham welcome to vendor backend");
		return "Success";
	}

	@Override
	public String createTemplatedetails(TemplateDetails templateDetails) throws Exception {

		String template = null;
		try {
			List<String> tenantList = DatabaseName.getAllTenant();
			for (String tenant : tenantList) {
				String dataBaseName = DatabaseName.dataBaseName(tenant);
                 
				applyValidation(templateDetails);
				
				template = this.templateDetailServiceDao.createTemplate(templateDetails, dataBaseName);
			}
		} catch (ClassNotFoundException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}

		return template;
	}

	private void applyValidation(TemplateDetails templateDetails) throws Exception {
		// TODO Auto-generated method stub
		if(templateDetails.getTemplate_name()==null || templateDetails.getTemplate_name().isEmpty())
		 {
			 throw new Exception("template name cannot be null or empty");
		 }
		if(templateDetails.getDiscription()==null || templateDetails.getDiscription().isEmpty())
		 {
			 throw new Exception("discription cannot be null or empty");
		 }
		if(templateDetails.getCreatedBy()==null || templateDetails.getCreatedBy().isEmpty())
		 {
			 throw new Exception("created by cannot be null or empty");
		 }
	}

}
