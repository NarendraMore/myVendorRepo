package com.anemoi.vendor.NewProjectService;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.anemoi.vendor.NewProjectDao.New_ProjectDao;
import com.anemoi.vendor.NewProjectDao.New_ProjectDaoException;
import com.anemoi.vendor.NewProjectDao.ProjectQueryConstant;
import com.anemoi.vendor.NewProjectModel.BusinessOwner;
import com.anemoi.vendor.NewProjectModel.Document;
import com.anemoi.vendor.NewProjectModel.NewProject;

import com.anemoi.vendor.configuration.DatabaseName;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;

@Singleton

public class New_ProjectServiceImpl implements New_ProjectService {

	public static final String SUCEESS = "success";
	public static final String STATUS = "status";
	public static final String MSG = "msg";
	private static String DATABASENAME = "databasename";

	@Inject
	private New_ProjectDao new_projectDao;

	private static String dataBaseName() {
		List<String> tenentList = DatabaseName.getAllTenant();
		for (String tenent : tenentList) {
			DATABASENAME = DatabaseName.dataBaseName(tenent);
		}
		return DATABASENAME;
	}

	@Override
	public NewProject saveProject(NewProject projects) throws SQLException, New_ProjectException {
		try {
			String dataBaseName = New_ProjectServiceImpl.dataBaseName();

			NewProject createNew_Project = this.new_projectDao.saveProject(projects, dataBaseName);

//				String projectid = projects.getProjectid();		

			// String projectid ="AD8FB516-2FBC-4090-9616-DF5F65382727";

//				Document uplaodfile = this.new_projectDao.uplaodfile(file,dataBaseName);
//				System.out.println(uplaodfile+"@@@@@@@@@");
//				
//				

			return projects;
//				return createNew_Project;
		} catch (Exception e) {
			throw new New_ProjectException("unable to create project " + e.getMessage());
		}

	}

	private void applyValidation(NewProject projects) throws Exception {

		if (projects.getProjectName() == null || projects.getProjectName().isEmpty()) {
			throw new Exception("project name cannot be null or empty");
		}
		if (projects.getClientName() == null || projects.getClientName().isEmpty()) {
			throw new Exception("client name cannot be null or empty");
		}
		if (projects.getIndustry() == null || projects.getIndustry().isEmpty()) {
			throw new Exception("industry cannot be null or empty");
		}
		if (projects.getProjectCode() == null || projects.getProjectCode().isEmpty()) {
			throw new Exception("project code cannot be null or empty");
		}
		if (projects.getPartnerName() == null || projects.getPartnerName().isEmpty()) {
			throw new Exception("partner name cannot be null or empty");
		}
		if (projects.getManagerName() == null || projects.getManagerName().isEmpty()) {
			throw new Exception("manager name cannot be null or empty");
		}
		if (projects.getBusinessuser() == null || projects.getBusinessuser().isEmpty()) {
			throw new Exception("business user cannot be null or empty");
		}
		if (projects.getRfpName() == null || projects.getRfpName().isEmpty()) {
			throw new Exception("rfp name cannot be null or empty");
		}
		if (projects.getDescription() == null || projects.getDescription().isEmpty()) {
			throw new Exception("description cannot be null or empty");
		}
		if (projects.getDocument() == null || projects.getDocument().isEmpty()) {
			throw new Exception("document cannot be null or empty");
		}
		Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]{2,14}");

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<NewProject> getAllNew_Project()
			throws SQLException, New_ProjectException, New_ProjectDaoException {

		String dataBaseName = New_ProjectServiceImpl.dataBaseName();
		ArrayList<NewProject> allProjectData = new ArrayList<>();
		allProjectData = this.new_projectDao.getAllNew_Project(dataBaseName);
		JSONObject object = new JSONObject();
		JSONArray projectjsonData = getjsonfromProjectList(allProjectData);
		object.put(allProjectData, projectjsonData);
		return allProjectData;

	}

	@SuppressWarnings("unchecked")
	private JSONArray getjsonfromProjectList(List<NewProject> allProjectData) {
		JSONArray array = new JSONArray();
		for (NewProject project : allProjectData) {
			JSONObject object = buildJsonFromProject(project);
			array.add(object);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromProject(NewProject project) {
		JSONObject object = new JSONObject();
		object.put(ProjectQueryConstant.PROJECTID, project.getProjectid());
		object.put(ProjectQueryConstant.CLIENTNAME, project.getClientName());
		object.put(ProjectQueryConstant.PROJECTNAME, project.getProjectName());
		object.put(ProjectQueryConstant.INDUSTRY, project.getIndustry());
		object.put(ProjectQueryConstant.CLIENTNAME, project.getClientName());
		object.put(ProjectQueryConstant.MANAGERNAME, project.getManagerName());
		object.put(ProjectQueryConstant.PARTNERNAME, project.getPartnerName());
		object.put(ProjectQueryConstant.RFPNAME, project.getRfpName());
		object.put(ProjectQueryConstant.CREATEDON, project.getCreatedOn());

		return object;
	}

	@Override
	public NewProject getNew_ProjectById(String projectId) throws SQLException, New_ProjectException {
		try {
			String dataBaseName = New_ProjectServiceImpl.dataBaseName();
			NewProject projectById = this.new_projectDao.getNew_ProjectById(projectId, dataBaseName);
			return projectById;

		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectException("unable to get project by id " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String updateNew_Project(NewProject projects, String projectId) {
		try {
			String dataBaseName = New_ProjectServiceImpl.dataBaseName();
			if (projectId == null || projectId.isEmpty()) {
				throw new New_ProjectException(" project id cannot be null or empty");

			}
			NewProject editprojectById = this.new_projectDao.editNew_ProjectById(projects, projectId, dataBaseName);
			JSONObject object = new JSONObject();
			JSONObject buildJsonFromProject = buildJsonFromProject(editprojectById);
			object.put(editprojectById, buildJsonFromProject);
			return buildJsonFromProject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private JSONObject buildJsonFromProjectObject(NewProject projects) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put(ProjectQueryConstant.PROJECTID, projects.getProjectid());
		jsonObject.put(ProjectQueryConstant.CLIENTNAME, projects.getClientName());
		jsonObject.put(ProjectQueryConstant.PROJECTNAME, projects.getProjectName());
		jsonObject.put(ProjectQueryConstant.INDUSTRY, projects.getIndustry());
		jsonObject.put(ProjectQueryConstant.MANAGERNAME, projects.getManagerName());
		jsonObject.put(ProjectQueryConstant.PARTNERNAME, projects.getPartnerName());
		jsonObject.put(ProjectQueryConstant.RFPNAME, projects.getRfpName());
		jsonObject.put(ProjectQueryConstant.CREATEDON, projects.getCreatedOn());

		return jsonObject;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String deleteNewProject(String projectId) {
		try {
			String dataBaseName = New_ProjectServiceImpl.dataBaseName();
			NewProject project = this.new_projectDao.getNew_ProjectById(projectId, dataBaseName);
			this.new_projectDao.deleteBusinessOwner(projectId, dataBaseName);
			this.new_projectDao.deleteNew_Project(projectId, dataBaseName);
			this.new_projectDao.deleteProject(projectId, dataBaseName);
			JSONObject responseObject = new JSONObject();
			responseObject.put(STATUS, SUCEESS);
			responseObject.put(MSG, "project deleted successfully");
			return responseObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String deleteBusinessOwner(String projectOwnerId, String dataBaseName) {
		try {
			String dataBaseName1 = New_ProjectServiceImpl.dataBaseName();
			this.new_projectDao.deleteBusinessOwner(projectOwnerId, dataBaseName1);
			JSONObject responseObject = new JSONObject();
			responseObject.put(STATUS, SUCEESS);
			responseObject.put(MSG, "BusinessOwner deleted successfully");
			return responseObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String saveBusiness(String projectId, ArrayList<BusinessOwner> business, String dataBaseName)
			throws New_ProjectException {
		try {
			dataBaseName = New_ProjectServiceImpl.dataBaseName();
			String createBusinessOwner = this.new_projectDao.saveBusiness(projectId, business, dataBaseName);
			return createBusinessOwner;
		} catch (Exception e) {
			throw new New_ProjectException("unable to create business " + e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public HttpResponse<Document> upload(CompletedFileUpload file, String projectid) {
		String uplodid = projectid;
		Connection connection = null;
		PreparedStatement preparedStatment = null;

		int count = 1;

		String docname = file.getFilename();
		String dataBaseName = New_ProjectServiceImpl.dataBaseName();

		String uploadfile;

		Document media = null;

		try {
			connection = VendorDatabaseUtill.getConnection();
			preparedStatment = connection
					.prepareStatement("SELECT * FROM " + dataBaseName + ".dbo.Document WHERE projectId =?");
			preparedStatment.setString(1, projectid);

			ResultSet resultset = preparedStatment.executeQuery();
//
//			for(Document i:alldoc)
//			{
//				System.out.println("documents "+i.getDocName());
//			}

			System.out.println("preparestatement is" + preparedStatment);

			ArrayList<Integer> list = new ArrayList<>();
			System.out.println("Arraylist is created  :");

			while (resultset.next()) {

				System.out.println("in while loop");
				media = buildMediaFromResultSet(resultset);

				list.add(media.getVersion());
//                System.out.println("Doc id is  :"+media.getProjectId());
			}
			ArrayList<String> ids = new ArrayList<>();

//			System.out.println("Out of while loop");
//			Document doc = new Document();
//			System.out.println("Projectid is   :"+uplodid);

//            String projectid2 = media.getProjectId();
//			while (projectid2 == uplodid) {

			if (list.isEmpty() != true) {
				System.out.println(" old Version is " + list.get(0));
				count = list.size() + 1;

				if (count > 1) {
					uploadfile = this.new_projectDao.fileupload(file, projectid, dataBaseName, count);
				}
			} else {
				uploadfile = this.new_projectDao.fileupload(file, projectid, dataBaseName, 1);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

//		
//		 int version = doc.getVersion();
//		for (int i = 0; i < 3; i++) {
//			// String indexedFileName = i.getDocName();
//			if (projectid.equals(projectid)) {
//				count++;
//				System.out.println("Count is :     "+count);
//				//uploadfile = this.new_projectDao.fileupload(file, projectid, dataBaseName);
//				
//			} 
//			else {
//			}

//		}
//		if (count > 1) {
//			System.out.println("File already exists");
//			return (HttpResponse<Document>) HttpResponse.status(io.micronaut.http.HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
//					.body();
//		} else {
//
//			System.out.println("uploading the file . . . . ");
//			HttpResponse<Document> s = upload(file, projectid);
//
//		}

		return null;

	}

	private Document buildMediaFromResultSet(ResultSet resultset) throws Exception {

		Document media = new Document();
		String docId = resultset.getString("docId");
		String docName = resultset.getString("docName");
		String docType = resultset.getString("docType");
		String projectid = resultset.getString("projectId");
		byte[] bytes = resultset.getBytes("docData");

//		byte[] docBytes = new ByteArrayInputStream(bytes);

		media.setDocId(docId);
		media.setDocName(docName);
		media.setDocType(docType);
		media.setDocData(bytes);
		media.setProjectId(projectid);
//		System.out.println("media is "+media.toString());
		return media;
	}

	@Override
	public Document downloadFile(String projectid) {
		Document media = new_projectDao.downloadDoc(projectid);

		return media;
	}

	@Override
	public ArrayList<Document> getuploadById(String projectid) {
	String dataBaseName = New_ProjectServiceImpl.dataBaseName();
	ArrayList<Document> list;
	try {
		list = this.new_projectDao.uploadById(projectid, dataBaseName);
		
		return list;
	} catch (New_ProjectDaoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;


}

	@Override
	public Document downloadByVersion(String projectId, Integer version) {

		String dataBaseName = New_ProjectServiceImpl.dataBaseName();
		Document media=this.new_projectDao.downloadDocByVersion(projectId, version, dataBaseName);
		return media;
	}
}