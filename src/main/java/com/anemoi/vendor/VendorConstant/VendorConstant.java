package com.anemoi.vendor.VendorConstant;

public class VendorConstant {

	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String INSERT_INTO_VENDORDETAILS = "INSERT INTO #$DataBaseName#$.dbo.vendor values(?,?,?,?,?,?,?) ";
	
	public static final String UPDATE__VENDORDETAILS ="UPDATE #$DataBaseName#$.dbo.vendor SET vendorName= ?, spocName=? , email=?, contactNumber= ?, lineOfBusiness = ?, createdOn = ? WHERE vendorId = ?";
	
	public static final String SELECT_VENDORBY_VENDORID ="SELECT *FROM #$DataBaseName#$.dbo.vendor WHERE vendorId =?";
	
	public static final String SELECT__VENDORDETAILS ="SELECT *FROM #$DataBaseName#$.dbo.vendor";
	
	public static final String DELETE__VENDOR_BY_VENDORID ="DELETE FROM #$DataBaseName#$.dbo.vendor WHERE vendorId = ?"; 
	
	public static final String VENDORID ="vendorId";
	
	public static final String VENDOR_NAME ="vendorName";
	
	public static final String SPOCNAME ="spocName";
	
	public static final String EMAIL ="email";
	
	public static final String CONTACTNUMBER ="contactNumber";
	
	public static final String LINEOFBUSINESS ="lineOfBusiness";
	
	public static final String CREATEDON ="createdOn";
	
	
	
	



}
