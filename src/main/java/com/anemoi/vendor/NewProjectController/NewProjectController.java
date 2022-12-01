package com.anemoi.vendor.NewProjectController;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.inject.Inject;

import com.anemoi.vendor.FileUpload.FileController;
import com.anemoi.vendor.NewProjectDao.New_ProjectDaoException;
import com.anemoi.vendor.NewProjectModel.Document;
import com.anemoi.vendor.NewProjectModel.NewProject;
import com.anemoi.vendor.NewProjectService.New_ProjectException;
import com.anemoi.vendor.NewProjectService.New_ProjectService;
import com.anemoi.vendor.RoleModel.Roles;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.RequestBean;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;

@Controller("vendor/project")
public class NewProjectController {

	private static final String REDIRECT_PATH = null;
	@Inject
	private New_ProjectService new_projectService;

//	@Post("/")
//	HttpResponse<String> saveProject(@Body NewProject projects) throws SQLException,NewProjectControllerException{	
//	System.out.println("data "+projects.toString());
//		try {
//		String project = this.new_projectService.saveProject(projects);
//		return HttpResponse.status(HttpStatus.CREATED).body(project);
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new NewProjectControllerException("unable to create new project"+e.getMessage());
//		}
//		
//	}

	@Post("/")
	HttpResponse<NewProject> saveProject(@Body NewProject projects ) throws SQLException,NewProjectControllerException{	
//	System.out.println("data "+projects.toString());
		try {
		NewProject project = this.new_projectService.saveProject(projects);
		return HttpResponse.status(HttpStatus.CREATED).body(project);
		}catch (Exception e) {
			e.printStackTrace();
			throw new NewProjectControllerException("unable to create new project"+e.getMessage());
		}
		
	}


	@Get("/getproject")
	HttpResponse<ArrayList<NewProject>> getProjectData()
			throws SQLException, New_ProjectException, New_ProjectDaoException {
		System.out.println("all projects");
		ArrayList<NewProject> projectData = new ArrayList<>();
		projectData = this.new_projectService.getAllNew_Project();
		return HttpResponse.status(HttpStatus.OK).body(projectData);
	}

	@Get("/{projectId}")
	HttpResponse<NewProject> getNew_ProjectById(@PathVariable("projectId") String projectId) {
		try {
			NewProject projectById = this.new_projectService.getNew_ProjectById(projectId);
			return HttpResponse.status(HttpStatus.OK).body(projectById);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Patch("/edit/{projectId}")
	HttpResponse<String> updateNewProject(@Body NewProject project, @PathVariable("projectId") String projectId) {
		String updatedProject = this.new_projectService.updateNew_Project(project, projectId);
		return HttpResponse.status(HttpStatus.OK).body(updatedProject);
	}

	@Delete("/{projectId}")
	HttpResponse<String> deleteProjectById(@PathVariable("projectId") String projectId) {
		String deleteProjectById = this.new_projectService.deleteNewProject(projectId);
		return HttpResponse.status(HttpStatus.OK).body(deleteProjectById);

	}
	
	@Post(uri ="/upload", consumes = { MediaType.MULTIPART_FORM_DATA } ) 
    public HttpResponse upload(CompletedFileUpload  file, String projectid) { 
        
        new_projectService.upload( file,projectid);
        return HttpResponse.seeOther(URI.create(REDIRECT_PATH)); 
    }
	
	@Get("/download/{projectid}")
	public HttpResponse<Document> downLoadFile(@PathVariable String projectid)  {
		System.out.println(projectid);
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
		Document media = new_projectService.downloadFile(projectid);

		
		return HttpResponse.ok().body(media).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*").header(
				HttpHeaders.CONTENT_DISPOSITION, FileController.CONTENT_DISPOSITION_VALUE + media.getDocName());
	}
	
	
	@Get("/{projectId}/{version}")
	public HttpResponse<Document> downloadByVersion(@PathVariable("projectId") String projectId,@PathVariable("version") Integer version)
	{
		System.out.println("data" +projectId+version);
		Document media=this.new_projectService.downloadByVersion(projectId, version);
		
		return HttpResponse.ok().body(media).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*").header(
				HttpHeaders.CONTENT_DISPOSITION, FileController.CONTENT_DISPOSITION_VALUE + media.getDocName());
		
//		return media;
	}
	
	
	@Get("/docu/{projectId}")
	ArrayList<Document> getuploadById(@PathVariable("projectId") String projectId) {
		try {
			ArrayList<Document> list = this.new_projectService.getuploadById(projectId);
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
