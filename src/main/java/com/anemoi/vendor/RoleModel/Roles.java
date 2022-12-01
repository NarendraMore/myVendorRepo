package com.anemoi.vendor.RoleModel;

import java.util.ArrayList;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Roles {

	private String roleId;

	private String roleName;

	private String descriptions;

//	private String checked;	

	private String roleStatus;

	private Long editedOn;

	private Long createdOn;

	private ArrayList<String> vendorTemplateAccess = new ArrayList<>();

	private ArrayList<String> dashboardAccess = new ArrayList<>();

	private ArrayList<String> masterRepoAccess = new ArrayList<>();

	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Roles(String roleId, String roleName, String descriptions, String roleStatus, Long editedOn, Long createdOn,
			ArrayList<String> vendorTemplateAccess, ArrayList<String> dashboardAccess,
			ArrayList<String> masterRepoAccess) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.descriptions = descriptions;
		this.roleStatus = roleStatus;
		this.editedOn = editedOn;
		this.createdOn = createdOn;
		this.vendorTemplateAccess = vendorTemplateAccess;
		this.dashboardAccess = dashboardAccess;
		this.masterRepoAccess = masterRepoAccess;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", roleName=" + roleName + ", descriptions=" + descriptions + ", roleStatus="
				+ roleStatus + ", editedOn=" + editedOn + ", createdOn=" + createdOn + ", vendorTemplateAccess="
				+ vendorTemplateAccess + ", dashboardAccess=" + dashboardAccess + ", masterRepoAccess="
				+ masterRepoAccess + "]";
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Long getEditedOn() {
		return editedOn;
	}

	public void setEditedOn(Long editedOn) {
		this.editedOn = editedOn;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public ArrayList<String> getVendorTemplateAccess() {
		return vendorTemplateAccess;
	}

	public void setVendorTemplateAccess(ArrayList<String> vendorTemplateAccess) {
		this.vendorTemplateAccess = vendorTemplateAccess;
	}

	public ArrayList<String> getDashboardAccess() {
		return dashboardAccess;
	}

	public void setDashboardAccess(ArrayList<String> dashboardAccess) {
		this.dashboardAccess = dashboardAccess;
	}

	public ArrayList<String> getMasterRepoAccess() {
		return masterRepoAccess;
	}

	public void setMasterRepoAccess(ArrayList<String> masterRepoAccess) {
		this.masterRepoAccess = masterRepoAccess;
	}

	
}
