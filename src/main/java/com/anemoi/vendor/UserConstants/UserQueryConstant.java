package com.anemoi.vendor.UserConstants;



public class UserQueryConstant {
	
	
	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";
	
	public static final String USERID = "id";
	
	public static final String FIRST_NAME = "firstName";
	
	public static final String LAST_NAME = "lastName";
	
	public static final String MOBILE = "mobileNumber";
	
	public static final String EMAIL = "email";

	public static final String MANAGER = "manager";
	
	public static final String PARTNER = "partner";
	
	public static final String STATUS = "userStatus";
	
	public static final String CREATEDON = "createdOn";
	
	public static final String USERNAME = "roleName";

	
	public static final String  INSERT_INTO_USERDETAILS = "INSERT INTO #$DataBaseName#$.dbo.userDeatils values(?,?,?,?,?,?,?,?,?,?)";
	
	public static final String SELECT_USER_BY_ID = "SELECT * FROM #$DataBaseName#$.dbo.userDeatils where id=?";
	
	public static final String SELECT_ALL_USERS = "SELECT *FROM #$DataBaseName#$.dbo.userDeatils";
	
	public static final String UPDATE_USER ="UPDATE #$DataBaseName#$.dbo.userDeatils SET firstName=?, lastName=?, email=?, mobileNumber=?, roleName=?, manager=?, partner=?, createdOn=?,UserStatus=? WHERE id=?";
 
	public static final String DELETE_USER_BY_ID = "DELETE #$DataBaseName#$.dbo.userDeatils WHERE id=?";
}
