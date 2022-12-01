package com.anemoi.vendor.VendorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import com.anemoi.vendor.UserConstants.UserQueryConstant;
import com.anemoi.vendor.UserDao.UserDaoException;
import com.anemoi.vendor.UserModel.User;
import com.anemoi.vendor.VendorConstant.VendorConstant;
import com.anemoi.vendor.VendorModel.Vendor;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;

@Singleton
public class VendorDaoImpl implements VendorDao {

	@Override
	public Vendor createNewVendor(Vendor vendor, String dataBaseName) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = VendorDatabaseUtill.getConnection();

			pstmt = connection.prepareStatement(VendorConstant.INSERT_INTO_VENDORDETAILS
					.replace(VendorConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			System.out.println("before insertion");
			String id = UUID.randomUUID().toString();
			vendor.setVendorId(id);
			String vendorId = vendor.getVendorId();
			System.out.println(id + " " + vendor);
			System.out.println("after inserton");

			Date date=new Date();
			
			
			pstmt.setString(1, vendorId);
//			pstmt.setString(2, vendor.getVendorId());
			pstmt.setString(2, vendor.getVendorName());
			pstmt.setString(3, vendor.getSpocName());
			pstmt.setString(4, vendor.getEmail());
			pstmt.setString(5, vendor.getContactNumber());
			pstmt.setString(6, vendor.getLineOfBusiness());
			pstmt.setLong(7, date.getTime());

			pstmt.executeUpdate();
			System.out.println("vendor created");
			return vendor;
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();

		} finally {

			VendorDatabaseUtill.close(pstmt, connection);
		}
		return null;
	}

	@Override
	public Vendor getVendorById(String vendorId, String dataBaseName) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(VendorConstant.SELECT_VENDORBY_VENDORID
					.replace(VendorConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, vendorId);
			result = pstmt.executeQuery();
			while (result.next()) {
				Vendor vendor = buildVendor(result);
				return vendor;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			VendorDatabaseUtill.close(result, pstmt, connection);
		}
		return null;

	}

	private Vendor buildVendor(ResultSet result) throws SQLException {
		Vendor vendor = new Vendor();
		vendor.setVendorId(result.getString(VendorConstant.VENDORID));
		vendor.setVendorName(result.getString(VendorConstant.VENDOR_NAME));
		vendor.setSpocName(result.getString(VendorConstant.SPOCNAME));
		vendor.setEmail(result.getString(VendorConstant.EMAIL));
		vendor.setContactNumber(result.getString(VendorConstant.CONTACTNUMBER));
		vendor.setLineOfBusiness(result.getString(VendorConstant.LINEOFBUSINESS));
		vendor.setCreatedOn(result.getLong(VendorConstant.CREATEDON));
		return vendor;

	}

	@Override
	public List<Vendor> getAllVendor(String dataBaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		List<Vendor> listofvender = new ArrayList<>();
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					VendorConstant.SELECT__VENDORDETAILS.replace(VendorConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			result = pstmt.executeQuery();
			while (result.next()) {

				Vendor vendor = buildVendor(result);
				listofvender.add(vendor);

			}
			return listofvender;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			VendorDatabaseUtill.close(pstmt, connection);
		}

		return null;

	}

	@Override
	public Vendor editVendorById(Vendor vendor, String vendorId, String dataBaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					VendorConstant.UPDATE__VENDORDETAILS.replace(VendorConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			pstmt.setString(1, vendor.getVendorName());
			pstmt.setString(2, vendor.getSpocName());
			pstmt.setString(3, vendor.getEmail());
			pstmt.setString(4, vendor.getContactNumber());
			pstmt.setString(5, vendor.getLineOfBusiness());
			pstmt.setLong(6, vendor.getCreatedOn());

			pstmt.setString(7, vendorId);

			int executeUpdate = pstmt.executeUpdate();

			System.out.println(executeUpdate);
			System.out.println(" User updated successfully");
			return vendor;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			VendorDatabaseUtill.close(pstmt, connection);
		}
		return vendor;
	}

	@Override
	public void deleteVendorById(String vendorId, String dataBaseName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(VendorConstant.DELETE__VENDOR_BY_VENDORID
					.replace(VendorConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, vendorId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(" user deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			VendorDatabaseUtill.close(pstmt, connection);
		}

	}

}
