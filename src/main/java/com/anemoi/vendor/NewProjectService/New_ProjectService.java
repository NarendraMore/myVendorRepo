package com.anemoi.vendor.NewProjectService;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import com.anemoi.vendor.NewProjectController.NewProjectControllerException;
import com.anemoi.vendor.NewProjectDao.New_ProjectDaoException;
import com.anemoi.vendor.NewProjectModel.BusinessOwner;
import com.anemoi.vendor.NewProjectModel.Document;
import com.anemoi.vendor.NewProjectModel.NewProject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;

public interface New_ProjectService {
	
	NewProject saveProject(NewProject projects) throws SQLException,New_ProjectException;
	
	String saveBusiness(String projectId,ArrayList<BusinessOwner> business,String databaseName) throws SQLException,New_ProjectException;
	
	
	ArrayList<NewProject> getAllNew_Project() throws SQLException, New_ProjectException, New_ProjectDaoException;

	NewProject getNew_ProjectById(String projectId) throws SQLException,New_ProjectException;

	String updateNew_Project(NewProject projects, String projectId);

	String deleteNewProject(String projectId);
	
	HttpResponse<Document> upload(CompletedFileUpload file,String projectid);

	Document downloadFile(String projectid);

	ArrayList<Document> getuploadById(String projectid);
	
	Document downloadByVersion(String projectId,Integer version);



}
