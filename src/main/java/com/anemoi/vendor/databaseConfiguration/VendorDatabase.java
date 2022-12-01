package com.anemoi.vendor.databaseConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anemoi.vendor.configuration.DatabaseName;

public class VendorDatabase extends VendorDatabaseTables {

	public static String DB_CREATE = "CREATE DATABASE ";

	private static final Logger logger = LoggerFactory.getLogger(VendorDatabase.class);

	/**
	 * Check if the database is exist or not.
	 * 
	 * @throws ClassNotFoundException
	 */
	private static boolean isDBExist(String databaseName) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = VendorDatabaseUtill.getConnection();

			resultSet = connection.getMetaData().getCatalogs();
			while (resultSet.next()) {
				String currentDBName = resultSet.getString(1);
				if (currentDBName.contentEquals(databaseName)) {
//					isTableExist(currentDBName);
					return true;
				}
			}
		} finally {

			VendorDatabaseUtill.close(resultSet, null, connection);
		}
		return false;
	}

	private static boolean isTableExist(String tableName) throws SQLException {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = VendorDatabaseUtill.getConnection();

			resultSet = connection.getMetaData().getTables(null, null, tableName, new String[] { "table" });

			while (resultSet.next()) {
				String currentTableName = resultSet.getString(3);
				if (currentTableName.contentEquals(tableName)) {
					logger.info("tables are exists");
					return true;
				}
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return false;

	}

	public static void createDataBases(String dataBaseName) {
		Connection connection = null;
		Statement statment = null;

		try {
			boolean isDbExist = isDBExist(dataBaseName);
			if (!isDbExist) {
				logger.info(dataBaseName + " database Not exits creating database ......");
				connection = VendorDatabaseUtill.getConnection();
				statment = connection.createStatement();
				statment.executeUpdate(DB_CREATE + dataBaseName);
				logger.info("checking tables are existing");
				createTables(statment, dataBaseName);
				logger.info("** create [" + dataBaseName + "] database successfully... **");
			} else {
				logger.info(" ** [" + dataBaseName + "] database already exists .....");
				
				

			}

		} catch (Exception e) {
			logger.error("unable to create [" + dataBaseName + "] database , " + e.getMessage());

		} finally {
			VendorDatabaseUtill.close(statment, connection);
		}

	}

	private static void createTables(Statement statment, String dataBaseName)
			throws SQLException, ClassNotFoundException {
//		if(!isTableExist(dataBaseName)) {
//		logger.info("checking tables are existing");
		createTemplateRelatedTables(statment, dataBaseName);
		createRoleRelatedTables(statment, dataBaseName);
		createUserRelatedTables(statment, dataBaseName);
		createNew_ProjectRelatedTables(statment, dataBaseName);
		createBusinessOwnerRelatedTables(statment, dataBaseName);
		createVendorRelatedTables(statment, dataBaseName);
		createMediaTables(statment,dataBaseName);
		createDocTable(statment,dataBaseName);
		
//			}

	}
	
	private static void createDocTable(Statement statment, String dataBaseName) throws SQLException {
		statment.executeUpdate(CREATE_DOC_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Document table created successfully");

	}

	private static void createMediaTables(Statement statment, String dataBaseName) throws SQLException {
		statment.executeUpdate(CREATE_MEDIA_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Media table created successfully");

	}

	private static void createRoleRelatedTables(Statement statment, String dataBaseName) throws SQLException {
		statment.executeUpdate(CREATE_ROLE_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Roles table created successfully");

	}

	private static void createTemplateRelatedTables(Statement statment, String dataBaseName) throws SQLException {
		statment.executeUpdate(CREATE_TEMPLATE_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("templateBuilder tabel is created successfully");
	}

	private static void createUserRelatedTables(Statement statement, String dataBaseName) throws SQLException {
		statement.executeUpdate(CREATE_USER_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("userDeatails table is created successfully");

	}

	private static void createNew_ProjectRelatedTables(Statement statement, String dataBaseName) throws SQLException {
		statement.executeUpdate(CREATE_NEW_PROJECTS.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("New_Project table is created successfully");

	}

	private static void createBusinessOwnerRelatedTables(Statement statement, String dataBaseName) throws SQLException {

		statement.executeUpdate(CERATE_BUSINESSOWNER.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("BusinessOwner table is created successfully");

	}

	private static void createVendorRelatedTables(Statement statement, String dataBaseName) throws SQLException {

		statement.executeUpdate(CREATE_VENDOR_TABLE.replace(DATA_BASE_PLACE_HOLDER, dataBaseName));
		logger.info("Vendor table is created successfully");

	}

//	private static void createVendorRelatedTables(Statement statement,String dataBaseName) throws SQLException {
//		statement.executeUpdate(CREATE_VENDOR_TABLE.replace(DATA_BASE_PLACE_HOLDER,dataBaseName));
//		logger.info("userDeatails table is created successfully");
//}
}