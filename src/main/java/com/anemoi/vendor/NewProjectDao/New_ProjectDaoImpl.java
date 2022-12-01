package com.anemoi.vendor.NewProjectDao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.io.FilenameUtils;

import com.anemoi.vendor.NewProjectModel.BusinessOwner;
import com.anemoi.vendor.NewProjectModel.Document;
import com.anemoi.vendor.NewProjectModel.NewProject;
import com.anemoi.vendor.RoleDao.RoleQueryConstant;
import com.anemoi.vendor.configuration.DatabaseName;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;

import org.apache.commons.io.IOUtils;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;


@Singleton
public class New_ProjectDaoImpl implements New_ProjectDao {
	
	
	

	@Override
	public NewProject saveProject(NewProject projects, String databaseName) throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {

			
			
			String managerName = projects.getManagerName();
			System.out.println("Calling save bussiness"+managerName);

			connection = VendorDatabaseUtill.getConnection();
			System.out.println("getting connection");
			pstmt = connection.prepareStatement(ProjectQueryConstant.INSERT_INTO_PROJECTS
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));

			Date date = new Date();
			String projectId = UUID.randomUUID().toString();
			projects.setProjectid(projectId);
			String id = projects.getProjectid();
			pstmt.setString(1, projectId);
			pstmt.setString(2, projects.getClientName());
			pstmt.setString(3, projects.getIndustry());
			pstmt.setString(4, projects.getProjectName());
			pstmt.setString(5, projects.getProjectCode());
			pstmt.setString(6, projects.getPartnerName());
			pstmt.setString(7, projects.getManagerName());
			pstmt.setString(8, projects.getBusinessuser().toString());
			pstmt.setString(9, projects.getRfpName());
			pstmt.setString(10, projects.getDescription());
			pstmt.setString(11, projects.getDocument());
			pstmt.setLong(12, date.getTime());
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + " project created");
			
			
			System.out.println(projects.getBusinessOwner()+" data for business owner");
			
			saveBusiness(projectId, projects.getBusinessOwner(),databaseName);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException("Unable to create project" + e.getMessage());
		}
		return projects;
	}

	@Override
	public ArrayList<NewProject> getAllNew_Project(String dataBaseName) throws SQLException, New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<NewProject> listOfProject = new ArrayList<>();
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.SELECT_PROJECT
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			System.out.println("new project data" + result.toString());
			while (result.next()) {
				NewProject project = buildProject(result, dataBaseName);
				listOfProject.add(project);
			}
			System.out.println(listOfProject.toString());
			return listOfProject;
		} catch (Exception e) {
			throw new New_ProjectDaoException("Unable to get list of project " + e.getMessage());

		} finally {
			VendorDatabaseUtill.close(result, pstmt, connection);
		}
	}

	private NewProject buildProject(ResultSet result, String databaseName) throws SQLException {
		NewProject project = new NewProject();
		ArrayList<BusinessOwner> businessOwner = new ArrayList<>();

		project.setProjectid(result.getString(ProjectQueryConstant.PROJECTID));
		project.setProjectName(result.getString(ProjectQueryConstant.PROJECTNAME));
		project.setClientName(result.getString(ProjectQueryConstant.CLIENTNAME));
		project.setIndustry(result.getString(ProjectQueryConstant.INDUSTRY));
		project.setProjectCode(result.getString(ProjectQueryConstant.PROJECTCODE));
		project.setPartnerName(result.getString(ProjectQueryConstant.PARTNERNAME));
		project.setManagerName(result.getString(ProjectQueryConstant.MANAGERNAME));
		ArrayList<String> buser = new ArrayList<>();
		buser.add(result.getString(ProjectQueryConstant.BUSINESSUSER));
		project.setBusinessuser(buser);

		project.setRfpName(result.getString(ProjectQueryConstant.RFPNAME));
		try {

			businessOwner = getAllBusinessOwnerDetailsById(databaseName,result.getString(ProjectQueryConstant.PROJECTID));

			System.out.println("businss owner data" + businessOwner.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (New_ProjectDaoException e) {
			e.printStackTrace();
		}
		project.setBusinessOwner(businessOwner);
		System.out.println(project.getBusinessOwner().toString() + " !!!!!!!");
		project.setDescription(result.getString(ProjectQueryConstant.DESCRIPTION));
//		project.setDocument(result.getString(ProjectQueryConstant.DOCUMENT));
		project.setCreatedOn(result.getLong(ProjectQueryConstant.CREATEDON));
		return project;
	}

	public ArrayList<BusinessOwner> getAllBusinessOwnerDetails(String dataBaseName)

			throws SQLException, New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		BusinessOwner project = null;

		ArrayList<BusinessOwner> businessOwner = new ArrayList<>();

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.SELECT_PROJECTOWNER
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			result = pstmt.executeQuery();
			while (result.next()) {
				project = buildBusinessOwner(result);
				businessOwner.add(project);
			}

			System.out.println(businessOwner + " anemoi");
			return businessOwner;
		} catch (Exception e) {
			throw new New_ProjectDaoException("Unable to get list of project " + e.getMessage());

		} finally {
			VendorDatabaseUtill.close(result, pstmt, connection);
		}
	}

	public ArrayList<BusinessOwner> getAllBusinessOwnerDetailsById(String dataBaseName, String id)
			throws SQLException, New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		BusinessOwner project = null;
		System.out.println(id + " gorkh's data");

		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.SELECT_PROJECTOWNER1
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, id);
			result = pstmt.executeQuery();
			ArrayList<BusinessOwner> projectOwner = new ArrayList<>();

			while (result.next()) {

				project = buildBusinessOwner(result);

				projectOwner.add(project);
				System.out.println(project + " gorkh's data");
			}
			return projectOwner;
		} catch (Exception e) {
			throw new New_ProjectDaoException("Unable to get list of project " + e.getMessage());

		} finally {
			VendorDatabaseUtill.close(result, pstmt, connection);
		}
	}

	private BusinessOwner buildBusinessOwner(ResultSet result) throws SQLException {
		BusinessOwner project = new BusinessOwner();
		project.setProjectOwnerid(result.getString(BusinessOwnerQueryConstant.BUSINESSID));
		project.setFirstname(result.getString(BusinessOwnerQueryConstant.FIRSTNAME));
		project.setLastname(result.getString(BusinessOwnerQueryConstant.LASTNAME));
		project.setEmail(result.getString(BusinessOwnerQueryConstant.EMAIL));
		project.setProjectOwnerId(result.getString(BusinessOwnerQueryConstant.PROJECTOWNERID));
		return project;
	}

	@Override
	public NewProject getNew_ProjectById(String projectId, String databaseName) throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.SELECT__BY_ID
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));
			pstmt.setString(1, projectId);
			result = pstmt.executeQuery();
			System.out.println(result.toString() + " all fetched data");
			while (result.next()) {
				NewProject project = buildProjectById(result, databaseName);
				return project;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException("Unable to get project by project id " + projectId + "" + e.getMessage());
		}
		return null;
	}

	private NewProject buildProjectById(ResultSet result, String databaseName) throws SQLException {
		NewProject project = new NewProject();
		ArrayList<BusinessOwner> businessOwner=new ArrayList<>();
		
		
		project.setProjectid(result.getString(ProjectQueryConstant.PROJECTID));
		project.setProjectName(result.getString(ProjectQueryConstant.PROJECTNAME));
		project.setClientName(result.getString(ProjectQueryConstant.CLIENTNAME));
		project.setIndustry(result.getString(ProjectQueryConstant.INDUSTRY));
		project.setProjectCode(result.getString(ProjectQueryConstant.PROJECTCODE));
		project.setPartnerName(result.getString(ProjectQueryConstant.PARTNERNAME));
		project.setManagerName(result.getString(ProjectQueryConstant.MANAGERNAME));
		ArrayList<String> buser = new ArrayList<>();
		buser.add(result.getString(ProjectQueryConstant.BUSINESSUSER));
		project.setBusinessuser(buser);
		project.setRfpName(result.getString(ProjectQueryConstant.RFPNAME));
		try {
			businessOwner = getAllBusinessOwnerDetailsById(databaseName,
					result.getString(ProjectQueryConstant.PROJECTID));
			System.out.println("businss owner data" + businessOwner.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (New_ProjectDaoException e) {
			e.printStackTrace();
		}
		project.setBusinessOwner(businessOwner);
		project.setDescription(result.getString(ProjectQueryConstant.DESCRIPTION));
//		project.setDocument(result.getString(ProjectQueryConstant.DOCUMENT));
		project.setCreatedOn(result.getLong(ProjectQueryConstant.CREATEDON));
		return project;
	}

	public BusinessOwner getBusinessById(String projectOwnerId, String databaseName) throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.SELECT_BUSINESS
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));

			pstmt.setString(1, projectOwnerId);
			result = pstmt.executeQuery();
			while (result.next()) {
				BusinessOwner project = buildBusiness(result);
				return project;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException(
					"Unable to get business by project id " + projectOwnerId + "" + e.getMessage());
		}
		return null;
	}

	private BusinessOwner buildBusiness(ResultSet result) throws SQLException {
		BusinessOwner project = new BusinessOwner();
		project.setProjectOwnerid(result.getString(BusinessOwnerQueryConstant.BUSINESSID));
		project.setFirstname(result.getString(BusinessOwnerQueryConstant.FIRSTNAME));
		project.setLastname(result.getString(BusinessOwnerQueryConstant.LASTNAME));
		project.setEmail(result.getString(BusinessOwnerQueryConstant.EMAIL));
		return project;
	}

	@Override
	public NewProject editNew_ProjectById(NewProject projects, String projectId, String databaseName) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ArrayList<BusinessOwner> businessOwner = new ArrayList<>();

		try {
		
			System.out.println(" project data "+projects.toString());
			System.out.println("Calling save bussiness");
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.UPDATE_PROJECT
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, databaseName));
			Date date = new Date();
			pstmt.setString(1, projects.getClientName());
			pstmt.setString(2, projects.getIndustry());
			pstmt.setString(3, projects.getProjectName());
			pstmt.setString(4, projects.getProjectCode());
			pstmt.setString(5, projects.getPartnerName());
			pstmt.setString(6, projects.getManagerName());
			pstmt.setString(7, projects.getBusinessuser().toString());
			pstmt.setString(8, projects.getRfpName());
			pstmt.setString(9, projects.getDescription());
			pstmt.setString(10, projects.getDocument());
			pstmt.setLong(11, date.getTime());
			pstmt.setString(12, projectId);
			editBusiness1(projects.getBusinessOwner(), projectId, databaseName);
			System.out.println("This is project id " + projectId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + "rows updated");
			return projects;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return projects;
	}

	public String editBusiness1(ArrayList<BusinessOwner> business, String ProjectId, String dataBaseName)
			throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;

		BusinessOwner business1 = new BusinessOwner();

		try {

			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BusinessOwnerQueryConstant.UPDATE_BUSINESS
					.replace(BusinessOwnerQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			for (int i = 0; i < business.size(); i++) {

				System.out.println("hello");
				business1.setFirstname(business.get(i).getFirstname());
				business1.setLastname(business.get(i).getLastname());
				business1.setEmail(business.get(i).getEmail());
				business1.setProjectOwnerId(ProjectId);
			
				pstmt.setString(1, business1.getFirstname());
				pstmt.setString(2, business1.getLastname());
				pstmt.setString(3, business1.getEmail());
				pstmt.setString(4, ProjectId);	
				pstmt.setString(5,business.get(i).getProjectOwnerId() );	
			
				int executeUpdate = pstmt.executeUpdate();
				System.out.println("business id is:"+business.get(i).getProjectOwnerId());
				System.out.println(executeUpdate + " project business");
			
			}

			return ProjectId;
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException("Unable to create business" + e.getMessage());
		}

	}

	@Override
	public void deleteNew_Project(String projectId, String dataBaseName) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;
		try {

			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(ProjectQueryConstant.DELETE_PROJECT
					.replace(ProjectQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, projectId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + " project deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBusinessOwner(String projectOwnerId, String dataBaseName) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {

			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BusinessOwnerQueryConstant.DELETE_BUSINESS
					.replace(BusinessOwnerQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, projectOwnerId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + " Business deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteProject(String projectId, String dataBaseName) throws SQLException {

		Connection connection = null;
		PreparedStatement pstmt = null;

		try {

			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BusinessOwnerQueryConstant.DELETE_PROJECT
					.replace(BusinessOwnerQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));
			pstmt.setString(1, projectId);
			int executeUpdate = pstmt.executeUpdate();
			System.out.println(executeUpdate + " project deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String saveBusiness(String projectid, ArrayList<BusinessOwner> business1, String dataBaseName)
			throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		BusinessOwner business = new BusinessOwner();
		String id = null;
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement(BusinessOwnerQueryConstant.INSERT_INTO_BUSINESS
					.replace(BusinessOwnerQueryConstant.DATA_BASE_PLACE_HOLDER, dataBaseName));

			 for (int i=0; i<business1.size(); i++){
				 String businessOwnerId = UUID.randomUUID().toString();	 
				 business.setProjectOwnerid(businessOwnerId);
				 id=businessOwnerId;
				 business.setFirstname(business1.get(i).getFirstname());
				 business.setLastname(business1.get(i).getLastname());
				 business.setEmail(business1.get(i).getEmail());
				 business.setProjectOwnerId(projectid);
				 pstmt.setString(1, business.getProjectOwnerid());
				 pstmt.setString(2, business.getProjectOwnerId());
				 pstmt.setString(3, business.getFirstname());
				 pstmt.setString(4, business.getLastname());
				 pstmt.setString(5, business.getEmail());
				 int executeUpdate = pstmt.executeUpdate();
				 System.out.println(executeUpdate + " project business");

			 }
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException("Unable to create business" + e.getMessage());
		}

	}

//	@Override
//	public Document uplaodfile(CompletedFileUpload file, String dataBaseName) {
//
//		Connection connection = null;
//		PreparedStatement preparedStatment = null;
//		Date date = new Date();
//		
//		try {
//			
//			Document document =new Document();
//			
//			String filename = file.getFilename();
//			String string = file.getContentType().toString();
//			byte[] bytes = file.getBytes();
//			
//			document.setDocName(filename);
//			document.setDocData(bytes);
//			document.setDocData(bytes);
//			
//			
//			
//			connection = VendorDatabaseUtill.getConnection();
//			preparedStatment = connection
//					.prepareStatement("INSERT INTO " + dataBaseName + ".dbo.Document values(?,?,?,?,?)");
//
//
//			String docId = UUID.randomUUID().toString();			
//			preparedStatment.setString(1,docId);
//			preparedStatment.setString(2, document.getDocName());
//			preparedStatment.setString(3, document.getDocType());
//			preparedStatment.setBytes(4, document.getDocData());
//			preparedStatment.setLong(5, date.getTime());
//			//preparedStatment.setString(6, projectId);
//			int executeUpdate = preparedStatment.executeUpdate();
//			System.out.println(executeUpdate);
//			return  document;
//			
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		
//		
//		
//		
//		return null;
//	}

	@Override
	public String fileupload(CompletedFileUpload file,String projectid,String dataBaseName,int count) {
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		Date date = new Date();
		
		try {
			
			Document document =new Document();
			
			String filename = file.getFilename();
			String type = file.getContentType().toString();
			byte[] bytes = file.getBytes();
			
			
			document.setDocName(filename);
			document.setDocType(type);
			document.setDocData(bytes);
		
			
			
			
			connection = VendorDatabaseUtill.getConnection();
			preparedStatment = connection
					.prepareStatement("INSERT INTO " + dataBaseName + ".dbo.Document values(?,?,?,?,?,?,?)");


			String docId = UUID.randomUUID().toString();			
			preparedStatment.setString(1,docId);
			preparedStatment.setString(2, document.getDocName());
			preparedStatment.setString(3, document.getDocType());
			preparedStatment.setBytes(4, document.getDocData());
			preparedStatment.setLong(5, date.getTime());
			preparedStatment.setString(6, projectid);
			preparedStatment.setInt( 7, count);
			int executeUpdate = preparedStatment.executeUpdate();
			System.out.println(executeUpdate);
			return  null;
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
		
		return null;
	}

	@Override
	public Document downloadDoc(String projectid) {
		List<String> tenantList = DatabaseName.getAllTenant();
		for (String tenant : tenantList) {
			String dataBaseName = DatabaseName.dataBaseName(tenant);
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement preparedStatment = null;
			try {
				connection = VendorDatabaseUtill.getConnection();
				preparedStatment = connection
						.prepareStatement("SELECT * FROM " + dataBaseName + ".dbo.Document WHERE projectId =?");
				preparedStatment.setString(1, projectid);
				resultSet = preparedStatment.executeQuery();
				
				List<Integer> list=new ArrayList<>();
				
				
				while (resultSet.next()) {
					
					Document media = buildMediaFrom(resultSet);
					
					list.add(media.getVersion());
					
				}
				
				
				int count;
				
				if (list.isEmpty() != true) {
					System.out.println(" old Version is " + list.get(0));
					System.out.println("size is"+list.size());

					count = list.size();
					
				
					Document media=downloadByProjectIdAndVersion(projectid,count,dataBaseName);
					return media;
					}
				
				else
				{
					System.out.println("File does not exist");
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			}

		return null;
	}

	private Document buildMediaFrom(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		Document media = new Document();
		int version=resultSet.getInt(7);
		media.setVersion(version);
		return media;
	}

	private Document downloadByProjectIdAndVersion(String projectid,int version,String dataBaseName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("project data"+projectid+" "+version+" "+dataBaseName);
	Connection connection = VendorDatabaseUtill.getConnection();
	PreparedStatement preparedStatment = connection
				.prepareStatement("SELECT * FROM " + dataBaseName + ".dbo.Document WHERE projectId =? AND version=?");
		preparedStatment.setString(1, projectid);
		preparedStatment.setInt(2, version);

	ResultSet resultSet = preparedStatment.executeQuery();
		while(resultSet.next()) {
		Document media = buildMediaFromResultSet(resultSet);
		System.out.println("data is: "+media.toString());
		return media;
		}
		return null;
	}

	private Document buildMediaFromResultSet(ResultSet resultSet) throws Exception {

		Document media = new Document();
		String docId = resultSet.getString(1);
		String docName = resultSet.getString(2);
		String docType = resultSet.getString(3);
		byte[] bytes= resultSet.getBytes(4);

		System.out.println("doc id" +bytes);
//		byte[] docBytes = new ByteArrayInputStream(bytes);
    
		
		media.setDocId(docId);
		media.setDocName(docName);
		media.setDocType(docType);
		media.setDocData(bytes);
		String extension = FilenameUtils.getExtension(docName);

		String newFileName = media.getDocName();

		String newFileNameWithExt = newFileName + "." + extension;

		InputStream targetStream = new ByteArrayInputStream(bytes);

		String home = System.getProperty("user.home");
		File withFile = new File(home + "/Downloads/" + newFileNameWithExt);
		if (docName == null || docName.isEmpty()) {
			System.out.println(docName + "Doesnt have any content to show");
		}
		boolean removeAllExtensions = false;

		String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
		String filenamewithoutextension = docName.replaceAll(extPattern, "");

		int count = 0;

		while (withFile.exists()) {
             System.out.println("with file " +withFile);
			newFileNameWithExt = filenamewithoutextension + "(" + (count++) + ")" + "." + extension;
			withFile = new File(home + "/Downloads/" + newFileNameWithExt);
			System.out.println(count + ":          no of times file is being downloaded");
			System.out.println(filenamewithoutextension + "    :new file without extensoin");
			System.out.println(newFileNameWithExt + "    :with extensoin");
		}

		FileOutputStream fos = new FileOutputStream(withFile);
		IOUtils.copy(targetStream, fos);
		fos.write(bytes);
		fos.close();
		FileInputStream fis = null;
		Document vendorMedia = null;
		fis = new FileInputStream(withFile);
		byte[] byteArrayOfFile = IOUtils.toByteArray(fis);
		vendorMedia = new Document();
		vendorMedia.setDocName(docName);
		System.out.println("download file " + vendorMedia);
		return vendorMedia;


	}

	@Override
	public ArrayList<Document> uploadById(String projectid, String dataBaseName) throws New_ProjectDaoException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Document> list=new ArrayList<>();
		try {
			connection = VendorDatabaseUtill.getConnection();
			pstmt = connection.prepareStatement("SELECT * FROM " + dataBaseName + ".dbo.Document WHERE projectId=?");

			pstmt.setString(1, projectid);
			result = pstmt.executeQuery();
			while (result.next()) {
				Document project = buildUpload(result);
				list.add(project);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new New_ProjectDaoException(
					"Unable to get business by project id " + projectid + "" + e.getMessage());
		}
	
	}

	private Document buildUpload(ResultSet result) throws SQLException {
	    Document project = new Document();
		project.setDocId(result.getString(1));
		project.setDocName(result.getString(2));
		project.setDocType(result.getString(3));
//		project.setDocData(result.getBytes(4));
		project.setUploadedDate(result.getLong(5));
		project.setProjectId(result.getString(6));
		project.setVersion(result.getInt(7));
		return project;
	}

	@Override
	public Document downloadDocByVersion(String projectId, Integer version, String dataBaseName) {
		// TODO Auto-generated method stub
		
		System.out.println("dataaaa"+projectId+" "+version+" "+dataBaseName);
		try {
		Document media=downloadByProjectIdAndVersion(projectId,version,dataBaseName);
		return media;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
