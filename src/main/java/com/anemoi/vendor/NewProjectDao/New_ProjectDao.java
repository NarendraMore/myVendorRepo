package com.anemoi.vendor.NewProjectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anemoi.vendor.NewProjectModel.BusinessOwner;
import com.anemoi.vendor.NewProjectModel.Document;
import com.anemoi.vendor.NewProjectModel.NewProject;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;




public interface New_ProjectDao {
	
	NewProject saveProject(NewProject projects, String databaseName) throws New_ProjectDaoException;

	ArrayList<NewProject> getAllNew_Project(String dataBaseName) throws SQLException, New_ProjectDaoException;

	NewProject getNew_ProjectById(String projectId, String databaseName) throws New_ProjectDaoException;

	NewProject editNew_ProjectById(NewProject projects, String projectId, String databaseName);

	void deleteNew_Project(String projectId, String dataBaseName) throws SQLException;

	static List<NewProject> getBusinessOwner(String firstname, String lastname, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	String saveBusiness(String projectId, ArrayList<BusinessOwner> businessOwner,String dataBaseName)throws New_ProjectDaoException;

	void deleteBusinessOwner(String projectOwnerId, String dataBaseName) throws SQLException;
	
	void deleteProject(String projectId, String dataBaseName) throws SQLException;

	//Document uplaodfile(CompletedFileUpload file, String dataBaseName);

	String fileupload(CompletedFileUpload file, String dataBaseName,String projectid,int count);

	Document downloadDoc(String projectid);

	ArrayList<Document> uploadById(String projectid, String dataBaseName)throws New_ProjectDaoException;
	
	
	Document downloadDocByVersion(String projectId,Integer version,String dataBaseName);

//	String fileupload(FileUpload file,String id)throws New_ProjectDaoException;

	

//	String saveBusiness(BusinessOwner business, String databaseName) throws New_ProjectDaoException;

}
