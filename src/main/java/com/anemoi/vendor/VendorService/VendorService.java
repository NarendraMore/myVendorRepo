package com.anemoi.vendor.VendorService;

import java.util.List;

import com.anemoi.vendor.VendorModel.Vendor;

public interface VendorService {

	Vendor createNewVendor(Vendor vendor) throws VendorServiceException;
	
	Vendor getVendorById(String vendorId) throws VendorServiceException;
	
	List<Vendor> getAllVendor() throws VendorServiceException;
	
	Vendor updateVendor(Vendor vendor,String vendorId)throws VendorServiceException;
	
	String deleteVendorById(String vendorId)throws VendorServiceException;
	
	
}
