package com.anemoi.vendor.databaseConfiguration;

public class VendorDatabaseTables {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	// template Related tables
	public static final String CREATE_TEMPLATE_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.templatebuilder(templateId uniqueidentifier NOT NULL,template_name varchar(255),discription varchar(255), createdBy varchar(255), createdOn bigint, CONSTRAINT PK_templateId PRIMARY KEY CLUSTERED (templateId))";

//		user related tables
	public static final String CREATE_USER_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.userDeatils(id uniqueidentifier ,roleName varchar(155) ,firstName varchar(255), lastName varchar(255), mobileNumber varchar(255) NOT NULL unique, email varchar(255) NOT NULL unique,manager varchar(25), partner varchar(50), userStatus varchar(10), createdOn bigint,PRIMARY KEY(id),CONSTRAINT fk_role FOREIGN KEY (roleName) REFERENCES #$DataBaseName#$.dbo.roles(roleName))";

//		roles related tables
	public static final String CREATE_ROLE_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.roles(roleId uniqueidentifier ,roleName varchar(155) unique, descriptions varchar(255) NOT NULL,roleStatus varchar(10),vendorTemplateAccess varchar(550),dashboardAccess varchar(550),masterRepoAccess varchar(550), editedOn bigint, createdOn bigint, CONSTRAINT PK_roleName PRIMARY KEY CLUSTERED (roleName))";

//		New_Pproject related tables
	public static final String CREATE_NEW_PROJECTS = "CREATE TABLE #$DataBaseName#$.dbo.newProject(projectId varchar(255) ,clientName varchar(25), industry varchar(255) NOT NULL,projectName varchar(550),projectCode varchar(550),partnerName varchar(550),managerName varchar(550),businessUser varchar(550),rfpName varchar(550),description varchar(550),document varchar(550),createdOn bigint,CONSTRAINT PK_projectId PRIMARY KEY CLUSTERED(projectId)  )";

	// BusinessOwner related tables
	public static final String CERATE_BUSINESSOWNER = "CREATE TABLE #$DataBaseName#$.dbo.businessOwner(projectOwnerId uniqueidentifier,projectId varchar(255),firstname varchar(25), lastname varchar(30), email varchar(50)NOT NULL ,CONSTRAINT fk_projectId FOREIGN KEY (projectId) REFERENCES #$DataBaseName#$.dbo.newProject(projectId))";

	// vendor tables
	public static final String CREATE_VENDOR_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.vendor(vendorId uniqueidentifier,vendorName varchar(26),spocName varchar(255) NOT NULL,email varchar(50),contactNumber bigint,lineOfBusiness varchar(255),createdOn bigint,CONSTRAINT PK_vendorId PRIMARY KEY CLUSTERED(vendorId))";

	public static final String CREATE_MEDIA_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.MediaData(mediaId uniqueidentifier, mediaName varchar(300),  type varchar(100),inputStream VARBINARY(MAX),CONSTRAINT PK_MediaId PRIMARY KEY CLUSTERED (mediaId))";

	public static final String CREATE_DOC_TABLE = "CREATE TABLE #$DataBaseName#$.dbo.Document (docId uniqueidentifier,docName varchar(300),  docType varchar(100), docData VARBINARY(MAX),uploadedDate bigint,projectId varchar(255),version int )";
}
