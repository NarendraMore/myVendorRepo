package com.anemoi.vendor.VendorDao;

import java.util.List;

import com.anemoi.vendor.VendorModel.Vendor;

public interface VendorDao {
	
	
	Vendor createNewVendor(Vendor vendor, String dataBaseName);

	Vendor getVendorById(String vendorId, String dataBaseName);

	List<Vendor> getAllVendor(String dataBaseName);

	Vendor editVendorById(Vendor vendor, String vendorId, String dataBaseName);

	void deleteVendorById(String vendorId, String dataBaseName);
}
