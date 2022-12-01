package com.anemoi.vendor.VendorService;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.anemoi.vendor.VendorConstant.VendorConstant;
import com.anemoi.vendor.VendorDao.VendorDao;
import com.anemoi.vendor.VendorModel.Vendor;
import com.anemoi.vendor.configuration.DatabaseName;

@Singleton
public class VendorServiceImpl implements VendorService {

	private static final Object STATUS = "status";
	private static final Object SUCCESS = "success";
	private static final Object MSG = "msg";
	private static String DATABASENAME = "databasename";

	@Inject
	private VendorDao vendorDao;

	private static String databaseName() {
		List<String> tenentList = DatabaseName.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = DatabaseName.dataBaseName(tenent);
		}
		return DATABASENAME;
	}

	@Override
	public Vendor createNewVendor(Vendor vendor) throws VendorServiceException {
		try {
			String dataBaseName = VendorServiceImpl.databaseName();
			String email=vendor.getEmail();
			String mobileNumbr=vendor.getContactNumber();
			aplyValidation(vendor);
			Vendor createNewVendor = this.vendorDao.createNewVendor(vendor, dataBaseName);
			return createNewVendor;

		} catch (Exception e) {
			e.printStackTrace();
			throw new VendorServiceException("Unable to create Vendor" + e.getMessage());
		}

	}

	private void aplyValidation(Vendor vendor) throws Exception {
		Pattern pattern;
		// TODO Auto-generated method stub
		if(vendor.getVendorName()==null || vendor.getVendorName().isEmpty())
		{
			 throw new Exception("vendor name cannot be null or empty");
		}
		
		if(vendor.getSpocName()==null || vendor.getSpocName().isEmpty())
		{
			 throw new Exception("spoc name cannot be null or empty");
		}
		if(vendor.getContactNumber()==null || vendor.getContactNumber().isEmpty())
		{
			 throw new Exception("contact number cannot be null or empty");
		}
		
		pattern = Pattern.compile("[7-9]{1}[0-9]{9}");
		boolean result = pattern.matcher(vendor.getContactNumber()).matches();
		if (!result) {
			throw new Exception("invalid mobile number formate");
		}
		
		if(vendor.getEmail()==null || vendor.getEmail().isEmpty())
		 {
			 throw new Exception("email cannot be null or empty");
		 }
		String emailRegex = "([a-zA-Z0-9]+)([\\.{1}])?([a-zA-Z0-9]+)\\@(?:gmail|GMAIL)([\\.])(?:com|COM)";
		 pattern = Pattern.compile(emailRegex);
		boolean result3 = pattern.matcher(vendor.getEmail()).matches();
		if (!result3) {
			throw new Exception(vendor.getEmail() + " is invalid email formate");
		}
	}

	@Override
	public Vendor getVendorById(String vendorId) throws VendorServiceException {
		try {
			String dataBaseName = VendorServiceImpl.databaseName();
			if (vendorId == null || vendorId.isEmpty()) {
				throw new VendorServiceException("Vendor Id must not be null");
			}
			Vendor vendor = this.vendorDao.getVendorById(vendorId, dataBaseName);
			return vendor;

		} catch (Exception e) {
			e.printStackTrace();
			throw new VendorServiceException("Unable to get vendor by id " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getAllVendor() throws VendorServiceException {
		try {
			String dataBaseName = VendorServiceImpl.databaseName();
			List<Vendor> vendors = this.vendorDao.getAllVendor(dataBaseName);
			JSONObject object = new JSONObject();
			JSONArray vendorJasonData = getJSONFromVendorList(vendors);
			object.put(vendors, vendorJasonData);
			return vendors;

		} catch (Exception e) {
			e.getStackTrace();
			throw new VendorServiceException("Unble to get list of vendors");

		}

	}

	@SuppressWarnings("unchecked")
	private JSONArray getJSONFromVendorList(List<Vendor> vendors) {
		JSONArray array = new JSONArray();
		for (Vendor vendor : vendors) {
			JSONObject object = buildJsonFromVendor(vendor);
			array.add(object);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromVendor(Vendor vendor) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(VendorConstant.VENDORID, vendor.getVendorId());
		jsonObject.put(VendorConstant.VENDOR_NAME, vendor.getVendorName());
		jsonObject.put(VendorConstant.SPOCNAME, vendor.getSpocName());
		jsonObject.put(VendorConstant.EMAIL, vendor.getEmail());
		jsonObject.put(VendorConstant.CONTACTNUMBER, vendor.getContactNumber());
		jsonObject.put(VendorConstant.LINEOFBUSINESS, vendor.getLineOfBusiness());
		jsonObject.put(VendorConstant.CREATEDON, vendor.getCreatedOn());

		return jsonObject;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Vendor updateVendor(Vendor vendor, String vendorId) throws VendorServiceException {
		try {
			List<String> tenentList = DatabaseName.getAllTenant();
			for (String tenent : tenentList) {
				String dataBaseName = DatabaseName.dataBaseName(tenent);
				if (vendorId == null || vendorId.isEmpty()) {
					throw new VendorServiceException("vendor Id must notg be null");
				}
				Vendor updateVendor = this.vendorDao.editVendorById(vendor, vendorId, dataBaseName);
				JSONObject object = new JSONObject();
				JSONObject jsonFromvendor = buildJsonFromUpdatedVendor(updateVendor);
				object.put(vendorId, jsonFromvendor);
				return updateVendor;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new VendorServiceException("Unable to update vendor " + e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromUpdatedVendor(Vendor vendor) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(VendorConstant.VENDOR_NAME, vendor.getVendorName());
		jsonObject.put(VendorConstant.SPOCNAME, vendor.getSpocName());
		jsonObject.put(VendorConstant.EMAIL, vendor.getEmail());
		jsonObject.put(VendorConstant.CONTACTNUMBER, vendor.getContactNumber());
		jsonObject.put(VendorConstant.LINEOFBUSINESS, vendor.getLineOfBusiness());
		jsonObject.put(VendorConstant.CREATEDON, vendor.getCreatedOn());

		return jsonObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteVendorById(String vendorId) throws VendorServiceException {

		try {
			String dataBaseName = VendorServiceImpl.databaseName();
			if (vendorId == null || vendorId.isEmpty()) {
//					System.out.println("vendor id can not be null");
				throw new VendorServiceException("Vendor Id must not be null");
			}
			Vendor vendor = vendorDao.getVendorById(vendorId, dataBaseName);
			if (vendor == null) {
//					System.out.println("vendor not found");
				throw new VendorServiceException("Vendor not found");

			}
			this.vendorDao.deleteVendorById(vendorId, dataBaseName);
			JSONObject reposneJSON = new JSONObject();
			reposneJSON.put(STATUS, SUCCESS);
			reposneJSON.put(MSG, "Vendor deleted suucessfully");
			return reposneJSON.toString();

		} catch (Exception e) {
			e.printStackTrace();
			throw new VendorServiceException("Unable to delete vendor " + e.getMessage());
		}

	}
	
	

	
	

}
