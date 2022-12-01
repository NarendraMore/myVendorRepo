package com.anemoi.vendor.VendorController;

import java.util.List;

import javax.inject.Inject;

import com.anemoi.vendor.VendorModel.Vendor;
import com.anemoi.vendor.VendorService.VendorService;
import com.anemoi.vendor.VendorService.VendorServiceException;
import com.anemoi.vendor.userController.UserControllerException;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

@Controller("/vendor/newVendor")
public class VendorController {

	@Inject
	private VendorService vendorService;

	@Post("/")
	public Vendor createVendor(@Body Vendor vendor) {
		try {
			System.out.println(vendor + " creation..vendor..vendor.!!!");
			Vendor vendor11=this.vendorService.createNewVendor(vendor);
			return vendor11;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		

	}

	@Get("/list")
	public List<Vendor> getAllVendor() throws VendorServiceException {
		
		List<Vendor> vendor=this.vendorService.getAllVendor();
		return vendor;
	}

	@Get("/{vendorId}")
	public Vendor getSingleVendor(@PathVariable("vendorId") String vendorId) throws VendorServiceException {
		Vendor vendor = this.vendorService.getVendorById(vendorId);
		return vendor;
	}

	@Delete("/{vendorId}")
	public String deleteVendor(@PathVariable("vendorId") String vendorId) throws VendorServiceException {
		return this.vendorService.deleteVendorById(vendorId);

	}

	@Patch("/update/{vendorId}")
	public Vendor updateVendor(@Body Vendor vendor, @PathVariable("vendorId") String vendorId) throws VendorServiceException {
		System.out.println("inside update vndor");
		Vendor updateVendor = this.vendorService.updateVendor(vendor, vendorId);
		return updateVendor;
	}

}
