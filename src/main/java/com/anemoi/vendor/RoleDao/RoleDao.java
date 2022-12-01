package com.anemoi.vendor.RoleDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anemoi.vendor.RoleModel.Roles;

public interface RoleDao {

	String msg();

	Roles createRole(Roles roles, String databaseName) throws RoleDaoException;

	ArrayList<Roles> getAllRoles(String dataBaseName) throws SQLException, RoleDaoException;

	Roles getRoleById(String roleId, String databaseName) throws RoleDaoException;

	Roles editRoleById(Roles roles, String roleId, String databaseName);

	void deleteRole(String roleId, String dataBaseName) throws SQLException;

}
