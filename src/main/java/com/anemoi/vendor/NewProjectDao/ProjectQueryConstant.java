package com.anemoi.vendor.NewProjectDao;

public class ProjectQueryConstant {
	
public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	
	public static final String PROJECTID = "projectid";
	
	
	public static final String CLIENTNAME = "clientName";
	
	public static final String INDUSTRY = "industry";
	
	public static final String BUSINESSFORM = "businessForm";
	
	public static final String FIRSTNAME = "firstname";
	
	public static final String LASTNAME = "lastname";
	
	public static final String EMAIL ="email";
	
	public static final String PROJECTNAME = "projectName";

	public static final String PROJECTCODE = "projectCode";
	
	public static final String PARTNERNAME = "partnerName";
	
	public static final String MANAGERNAME = "managerName";
	
	public static final String BUSINESSUSER = "businessUser";
	
	public static final String RFPNAME = "rfpName";
	
	public static final String DESCRIPTION = "description";
	
	public static final String DOCUMENT = "document";
	
	public static final String CREATEDON = "createdOn";
	

	
	public static final String  INSERT_INTO_PROJECTS = "INSERT INTO #$DataBaseName#$.dbo.newProject values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT__BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.newProject where projectId=?";
	public static final String SELECT_BUSINESS = "SELECT * FROM #$DataBaseName#$.dbo.businessOwner WHERE projectOwnerId =?";
	
	public static final String SELECT_PROJECT = "SELECT * FROM #$DataBaseName#$.dbo.newProject";
	public static final String SELECT_PROJECTOWNER = "SELECT * FROM #$DataBaseName#$.dbo.businessOwner ";
	
	public static final String SELECT_PROJECTOWNER1 = "SELECT * FROM #$DataBaseName#$.dbo.businessOwner WHERE projectId =?";
	
	
	public static final String UPDATE_PROJECT ="UPDATE #$DataBaseName#$.dbo.newProject SET  clientName=?, industry=?, projectName=?, projectCode=?, partnerName=?, managerName=?, businessUser=?, rfpName=?,description=?,document=?,createdOn=? WHERE projectId=?";
 
	public static final String DELETE_PROJECT ="DELETE FROM #$DataBaseName#$.dbo.newProject WHERE projectId = ?";
}