package com.anemoi.vendor.RoleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import com.anemoi.vendor.RoleModel.Roles;
import com.anemoi.vendor.UserConstants.UserQueryConstant;
import com.anemoi.vendor.UserModel.User;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;

@Singleton
public class RoleDaoImpl implements RoleDao {

	@Override
	public String msg() {
		System.out.println("Hello roles");
		return "All classes connected";
	}

	@Override
	public Roles createRole(Roles roles, String databaseName) throws RoleDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(RoleQueryConstant.INSERT_INTO_ROLES
					.replace(RoleQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));

			String roleId = UUID.randomUUID().toString();
			roles.setRoleId(roleId);
			String id = roles.getRoleId();
			Date date = new Date();
			pstmt.setString(1, id);
			pstmt.setString(2, roles.getRoleName());
			pstmt.setString(3, roles.getDescriptions());
			pstmt.setString(4, roles.getRoleStatus());
			pstmt.setString(5, roles.getVendorTemplateAccess().toString());
			pstmt.setString(6, roles.getDashboardAccess().toString());
			pstmt.setString(7, roles.getMasterRepoAccess().toString());
			pstmt.setLong(8, date.getTime());
			pstmt.setLong(9, date.getTime());
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + " role created");
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleDaoException("Unable to create role" + e.getMessage());
		}

	}

	@Override
	public ArrayList<Roles> getAllRoles(String dataBaseName) throws SQLException, RoleDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Roles> listOfRoles = new ArrayList<>();
		
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					RoleQueryConstant.SELECT_ROLE.replace(RoleQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			result = pstmt.executeQuery();
			while (result.next()) {
//				System.out.println(result.toString());
				Roles buildRole = buildRole(result);
				listOfRoles.add(buildRole);
				
//				System.out.println(listOfRoles.toString()+"*****************************");
			}
			return listOfRoles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleDaoException("unble to get list of roles" + e.getMessage());
		}

	}

	
	
	
	@SuppressWarnings("unchecked")
	private Roles buildRole(ResultSet result) throws SQLException {
		Roles roles = new Roles();
		User user = new User();
		
		ArrayList<String> list=new ArrayList<>();
		roles.setRoleId(result.getString(RoleQueryConstant.ID));
		roles.setRoleName(result.getString(RoleQueryConstant.ROLE_NAME));
		roles.setDescriptions(result.getString(RoleQueryConstant.DESCRIPTIONS));
		roles.setRoleStatus(result.getString(RoleQueryConstant.ROLE_STATUS));
		roles.setCreatedOn(result.getLong(RoleQueryConstant.CREATEDON));
		roles.setEditedOn(result.getLong(RoleQueryConstant.EDITEDON));

		ArrayList<String> vta = new ArrayList<>();
		vta.add(result.getString(RoleQueryConstant.VENDOR_TEMPLATE_ACCESS));
		roles.setVendorTemplateAccess(vta);

		ArrayList<String> dba = new ArrayList<>();
		dba.add(result.getString(RoleQueryConstant.DASHBOARDACCESS));
		roles.setDashboardAccess(dba);

		ArrayList<String> mra = new ArrayList<>();
		mra.add(result.getString(RoleQueryConstant.MASTERREPOACCESS));
		roles.setMasterRepoAccess(mra);
		
		user.setId(result.getString(UserQueryConstant.USERID));
		user.setFirstName(result.getString(UserQueryConstant.FIRST_NAME));
		user.setLastName(result.getString(UserQueryConstant.LAST_NAME));
		user.setMobileNumber(result.getString(UserQueryConstant.MOBILE));
		user.setEmail(result.getString(UserQueryConstant.EMAIL));
		user.setManager(result.getString(UserQueryConstant.MANAGER));
		user.setPartner(result.getString(UserQueryConstant.PARTNER));
		user.setUserStatus(result.getString(UserQueryConstant.STATUS));
		user.setCreatedOn(result.getLong(UserQueryConstant.CREATEDON));
		user.setRoleName(result.getString(UserQueryConstant.USERNAME));
		
		return roles;
	}

	@Override
	public Roles getRoleById(String roleId, String databaseName) throws RoleDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					RoleQueryConstant.SELECT_BY_ID.replace(RoleQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));

			pstmt.setString(1, roleId);
			result = pstmt.executeQuery();
			while (result.next()) {
				Roles roles = buildRole(result);
				return roles;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleDaoException("Unable to get role by role id " + roleId + "" + e.getMessage());
		}
		return null;
	}

	@Override
	public Roles editRoleById(Roles roles, String roleId, String databaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(
					RoleQueryConstant.UPDATE_ROLE.replace(RoleQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));
			
			Date date = new Date();

			pstmt.setString(1, roles.getRoleName());
			pstmt.setString(2, roles.getDescriptions());
			pstmt.setString(3, roles.getRoleStatus());
			pstmt.setString(4, roles.getVendorTemplateAccess().toString());
			pstmt.setString(5, roles.getDashboardAccess().toString());
			pstmt.setString(6, roles.getMasterRepoAccess().toString());
			pstmt.setLong(7, date.getTime());
			pstmt.setString(8, roleId);

			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + "rows updated");
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteRole(String roleId, String dataBaseName) throws SQLException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = VendorDatabaseUtill.getConnection();

			pstmt = connection.prepareStatement(
					RoleQueryConstant.DELETE_ROLE.replace(RoleQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, roleId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate+" Role deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
