package com.anemoi.vendor.RoleController;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;

import com.anemoi.vendor.RoleModel.Roles;
import com.anemoi.vendor.RoleService.RoleService;
import com.anemoi.vendor.RoleService.RoleServiceException;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

@Controller("vendor/role")
public class RoleController {

	@Inject
	private RoleService roleService;

	@Get("/")
	public String display() {
		String getmsg = this.roleService.getmsg();
		return getmsg;
	}

	@Post("/")
	public Roles createRole(@Body Roles roles) throws SQLException, RoleControllerException {
		try {
			Roles role = this.roleService.createRole(roles);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleControllerException("unble to create role "+e.getMessage());
		}
	}

	@Get("/getAll")
	ArrayList<Roles> getRoles() throws SQLException, RoleControllerException {
		try {
			ArrayList<Roles> allRoles = this.roleService.getAllRoles();
			return allRoles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleControllerException("unable to get roles "+ e.getMessage());
		}
	}

	@Get("/{roleId}")
	HttpResponse<Roles> getRoleById(@PathVariable("roleId") String roleId) throws RoleControllerException {
		try {
			Roles roleById = this.roleService.getRoleById(roleId);
			return HttpResponse.status(HttpStatus.OK).body(roleById);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RoleControllerException("unable to get role by id "+e.getMessage());
		}
	}

	@Patch("/edit/{roleId}")
	Roles updateRole(@Body Roles roles, @PathVariable("roleId") String roleId) throws RoleControllerException {
		Roles updatedRole;
		try {
			updatedRole = this.roleService.updateRole(roles, roleId);
			return updatedRole;
		} catch (RoleServiceException e) {
			e.printStackTrace();
			throw new RoleControllerException(" unable to update role "+e.getMessage());
		}

	}

	@Delete("/{roleId}")
	HttpResponse<String> deleteRoleById(@PathVariable("roleId") String roleId) throws RoleControllerException {
		String deleteRoleById;
		try {
			deleteRoleById = this.roleService.deleteRoleById(roleId);
			return HttpResponse.status(HttpStatus.OK).body(deleteRoleById);

		} catch (RoleServiceException e) {
			e.printStackTrace();
			throw new RoleControllerException("unable to delete role " + e.getMessage());

		}

	}

}
