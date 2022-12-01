package com.anemoi.vendor.NewProjectDao;

public class BusinessOwnerQueryConstant {

public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
	public static final String BUSINESSID = "projectOwnerId";
	
	public static final String PROJECTOWNERID ="projectOwnerId";
	
	public static final String FIRSTNAME = "firstname";
	
	public static final String LASTNAME = "lastname";
	
	public static final String EMAIL = "email";
	
	
	
	
	public static final String  INSERT_INTO_BUSINESS = "INSERT INTO #$DataBaseName#$.dbo.businessOwner values(?,?,?,?,?)";
	
	public static final String UPDATE_BUSINESS ="UPDATE #$DataBaseName#$.dbo.businessOwner SET firstname=?, lastname=?, email=?  WHERE projectId=? AND projectOwnerId=? ";

	public static final String DELETE_BUSINESS ="DELETE FROM #$DataBaseName#$.dbo.businessOwner WHERE projectId=?";
	
	public static final String DELETE_PROJECT ="DELETE FROM #$DataBaseName#$.dbo.newProject WHERE projectId=?";
}
