package com.anemoi.vendor.RoleService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.anemoi.vendor.RoleDao.RoleDao;
import com.anemoi.vendor.RoleDao.RoleQueryConstant;
import com.anemoi.vendor.RoleModel.Roles;
import com.anemoi.vendor.configuration.DatabaseName;

@Singleton
public class RoleServiceImpl implements RoleService {

	public static final String STATUS = "status";

	public static final String MSG = "msg";

	public static final String SUCEESS = "success";

	private static String DATABASENAME = "databasename";

	@Inject
	private RoleDao roleDao;

	@Override
	public String getmsg() {
		String msg = this.roleDao.msg();
		return msg;
	}

	private static String dataBaseName() {
		List<String> tenentList = DatabaseName.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = DatabaseName.dataBaseName(tenent);
		}
		return DATABASENAME;

	}

	@Override
	public Roles createRole(Roles roles) throws SQLException, RoleServiceException {
		try {
			String dataBaseName = RoleServiceImpl.dataBaseName();
			applyValidation(roles);
			
			Roles role = this.roleDao.createRole(roles, dataBaseName);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleServiceException("unable to create role" + e.getMessage());
		}

	}

	private void applyValidation(Roles roles) throws Exception {
		// TODO Auto-generated method stub
		if(roles.getRoleName()==null || roles.getRoleName().isEmpty())
		 {
			 throw new Exception("role name cannot be null or empty");
		 }
		if(roles.getDescriptions()==null || roles.getDescriptions().isEmpty())
		 {
			 throw new Exception("discription cannot be null or empty");
		 }
		
		if(roles.getRoleStatus()==null || roles.getRoleStatus().isEmpty())
		 {
			 throw new Exception("role status cannot be null or empty");
		 }
		if(roles.getVendorTemplateAccess()==null || roles.getVendorTemplateAccess().isEmpty())
		 {
			 throw new Exception("vendor template cannot be null or empty");
		 }
		if(roles.getDashboardAccess()==null || roles.getDashboardAccess().isEmpty())
		 {
			 throw new Exception("dash board access cannot be null or empty");
		 }
		if(roles.getMasterRepoAccess()==null || roles.getMasterRepoAccess().isEmpty())
		 {
			 throw new Exception("master repo cannot be null or empty");
		 }
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Roles> getAllRoles() throws SQLException, RoleServiceException {
		try {
			String dataBaseName = RoleServiceImpl.dataBaseName();
			ArrayList<Roles> allRoles = this.roleDao.getAllRoles(dataBaseName);
//				return allRoles.toString();
			JSONObject jsonObject = new JSONObject();
			JSONArray roleJsonData = getJSonFromRoleList(allRoles);
			jsonObject.put(allRoles, roleJsonData);
			return allRoles;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleServiceException("unable to get roles " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private JSONArray getJSonFromRoleList(List<Roles> allRoles) {
		JSONArray roleArray = new JSONArray();
		for (Roles roles : allRoles) {
			JSONObject object = buildJsonFromRole(roles);
			roleArray.add(object);
		}
		return roleArray;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromRole(Roles roles) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RoleQueryConstant.ID, roles.getRoleId());
		jsonObject.put(RoleQueryConstant.ROLE_NAME, roles.getRoleName());
		jsonObject.put(RoleQueryConstant.DESCRIPTIONS, roles.getDescriptions());
		jsonObject.put(RoleQueryConstant.ROLE_STATUS, roles.getRoleStatus());
		jsonObject.put(RoleQueryConstant.CREATEDON, roles.getCreatedOn());
		jsonObject.put(RoleQueryConstant.VENDOR_TEMPLATE_ACCESS, roles.getVendorTemplateAccess());
		jsonObject.put(RoleQueryConstant.DASHBOARDACCESS, roles.getDashboardAccess());
		jsonObject.put(RoleQueryConstant.MASTERREPOACCESS, roles.getMasterRepoAccess());
		return jsonObject;
	}

	@Override
	public Roles getRoleById(String roleId) throws SQLException, RoleServiceException {

		try {
			String dataBaseName = RoleServiceImpl.dataBaseName();
			Roles roleById = this.roleDao.getRoleById(roleId, dataBaseName);
			return roleById;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleServiceException("unable to get role by id " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Roles updateRole(Roles roles, String roleId) throws RoleServiceException {
		try {
			String dataBaseName = RoleServiceImpl.dataBaseName();
			if (roleId == null || roleId.isEmpty()) {
				throw new RoleServiceException(" role id cannot be null or empty");

			}
			Roles editRoleById = this.roleDao.editRoleById(roles, roleId, dataBaseName);
			JSONObject object = new JSONObject();
			JSONObject buildJsonFromRole = buildJsonFromRole(editRoleById);
			object.put(editRoleById, buildJsonFromRole);
			return editRoleById;
//				return editRoleById.toString();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleServiceException("Unable to update role by role id" + e.getMessage());
		}
		
	}

//	@SuppressWarnings("unchecked")
//	private JSONObject buildJsonFromRoleObject(Roles roles) {
//		JSONObject jsonObject = new JSONObject();
//
////		jsonObject.put(RoleQueryConstant.ID, roles.getId()))
//		jsonObject.put(RoleQueryConstant.ROLE_NAME, roles.getRoleName());
//		jsonObject.put(RoleQueryConstant.DESCRIPTIONS, roles.getDescriptions());
//		jsonObject.put(RoleQueryConstant.ROLE_STATUS, roles.getRoleStatus());
//		jsonObject.put(RoleQueryConstant.CREATEDON, roles.getRoleStatus());
//		jsonObject.put(RoleQueryConstant.VENDOR_TEMPLATE_ACCESS, roles.getVendorTemplateAccess());
//		jsonObject.put(RoleQueryConstant.DASHBOARDACCESS, roles.getDashboardAccess());
//		jsonObject.put(RoleQueryConstant.MASTERREPOACCESS, roles.getMasterRepoAccess());
//		return jsonObject;
//
//	}

	@SuppressWarnings("unchecked")
	@Override
	public String deleteRoleById(String roleId) throws RoleServiceException {
		try {
			String dataBaseName = RoleServiceImpl.dataBaseName();
			if (roleId == null || roleId.isEmpty()) {
			this.roleDao.deleteRole(roleId, dataBaseName);
			JSONObject responseObject = new JSONObject();
			responseObject.put(STATUS, SUCEESS);
			responseObject.put(MSG, "Role deleted successfully");
			return responseObject.toString();

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleServiceException("Unable to delete role by role id"+ e.getMessage());
			
		}
		return null;
	}

}
