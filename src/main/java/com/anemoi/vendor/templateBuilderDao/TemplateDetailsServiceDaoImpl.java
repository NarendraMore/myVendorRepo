package com.anemoi.vendor.templateBuilderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.UUID;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;
import com.anemoi.vendor.templateBuilderModel.TemplateDetails;

@Singleton
public class TemplateDetailsServiceDaoImpl implements TemplateDetailServiceDao {

	private static final Logger logger = LoggerFactory.getLogger(TemplateDetailsServiceDaoImpl.class);

	@Override
	public String createTemplate(TemplateDetails templateDetails, String dataBaseName)
			throws SQLException, ClassNotFoundException {
		Connection connection=null;
		PreparedStatement pstmt=null;
		logger.info("getting the connection");
		connection = VendorDatabaseUtill.getConnection();
		 pstmt = connection.prepareStatement(TemplateBuilderQueryConstant.INSERT_INTO_TEMPLATEBUILDER
				.replace(TemplateBuilderQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info(dataBaseName);
		logger.info("creating new template");
		String id = UUID.randomUUID().toString();
		templateDetails.setTemplateId(id);
		String templateId = templateDetails.getTemplateId();
		pstmt.setString(1, templateId);
		pstmt.setString(2, templateDetails.getTemplate_name());
		pstmt.setString(3, templateDetails.getDiscription());
		pstmt.setString(4, templateDetails.getCreatedBy());
		pstmt.setLong(5, templateDetails.getCreatedOn());
		pstmt.executeUpdate();
		return templateId;

	}

}
