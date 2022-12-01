package com.anemoi.vendor.RoleService;

import java.sql.SQLException;
import java.util.ArrayList;

import com.anemoi.vendor.RoleModel.Roles;

public interface RoleService {

	String getmsg();

	Roles createRole(Roles roles) throws SQLException,RoleServiceException;

	ArrayList<Roles> getAllRoles() throws SQLException, RoleServiceException;

	Roles getRoleById(String roleId) throws SQLException,RoleServiceException;

	Roles updateRole(Roles roles, String roleId) throws RoleServiceException;

	String deleteRoleById(String roleId) throws RoleServiceException;

}
