package com.anemoi.vendor.RoleDao;

public class RoleQueryConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String INSERT_INTO_ROLES = "INSERT INTO #$DataBaseName#$.dbo.roles values(?,?,?,?,?,?,?,?,?) ";
	
	public static final String UPDATE_ROLE ="UPDATE #$DataBaseName#$.dbo.roles SET roleName= ?, descriptions=? , roleStatus=?, vendorTemplateAccess= ?, dashboardAccess = ?, masterRepoAccess = ?, editedOn=? WHERE roleId = ?";
	
	public static final String SELECT_BY_ID ="SELECT *FROM #$DataBaseName#$.dbo.roles WHERE roleId =?";
	
	public static final String SELECT_ROLE ="SELECT *FROM #$DataBaseName#$.dbo.roles LEFT JOIN  #$DataBaseName#$.dbo.userDeatils ON  roles.roleName=userDeatils.roleName";
	
	public static final String DELETE_ROLE ="DELETE FROM #$DataBaseName#$.dbo.roles WHERE roleId = ?"; 
	
	public static final String ID ="roleId";
	
	public static final String ROLE_NAME ="roleName";
	
	public static final String DESCRIPTIONS ="descriptions";
	
	public static final String ROLE_STATUS ="roleStatus";
	
	public static final String CREATEDON ="createdOn";
	
	public static final String EDITEDON ="editedOn";
	
	public static final String VENDOR_TEMPLATE_ACCESS ="vendorTemplateAccess";
	
	public static final String DASHBOARDACCESS ="dashboardAccess";
	
	public static final String MASTERREPOACCESS ="masterRepoAccess";
	
	




			

}
