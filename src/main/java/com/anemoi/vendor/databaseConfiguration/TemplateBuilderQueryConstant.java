package com.anemoi.vendor.databaseConfiguration;

public class TemplateBuilderQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
	public static String CREATE_TEMPLATE_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.templatebuilder(templateId uniqueidentifier NOT NULL,template_name varchar(255),discription varchar(255), createdBy varchar(255), createdOn bigint, CONSTRAINT PK_templateId PRIMARY KEY CLUSTERED (templateId))";
	
	public static String INSERT_INTO_TEMPLATEBUILDER ="INSERT INTO #$DataBaseName#$.dbo.templatebuilder values(?,?,?,?,?)";
	
	public static String CREATE_USER_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.userDeatils(id uniqueidentifier ,firstName varchar(255), lastName varchar(255), mobileNumber varchar(255) NOT NULL unique, email varchar(255) NOT NULL unique,manager varchar(25), partner varchar(50), userStatus varchar(10), createdOn bigint, roleId varchar(25), CONSTRAINT PK_Id PRIMARY KEY CLUSTERED (id),CONSTRAINT fk_id FOREIGN KEY (id) REFERENCES #$DataBaseName#$.dbo.userDeatils(id))";

	public static String CREATE_ROLE_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.roles(id uniqueidentifier ,roleName varchar(25), descriptions varchar(255) NOT NULL,userStatus varchar(10),vendorTemplateAccess varchar(50),dashboardAccess varchar(50),masterRepoAccess varchar(50), createdOn varchar(10), CONSTRAINT PK_roleId PRIMARY KEY CLUSTERED (id))";
			
	public static String CREATE_VENDOR_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.vendordetails(vendorId uniqueidentifier ,vendorName varchar(25), spocName varchar(255) NOT NULL,email varchar(10),contactNumber varchar(50),lineOfBusiness varchar(50),createdOn varchar(50), CONSTRAINT PK_vendorId PRIMARY KEY CLUSTERED (vendorId))";
	
}