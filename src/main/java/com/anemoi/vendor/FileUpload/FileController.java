package com.anemoi.vendor.FileUpload;

import java.io.IOException;

import javax.inject.Inject;

//import org.springframework.core.io.ByteArrayResource;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;

@Controller("/vendor/file")
public class FileController {

	@Inject
	private FileUploadService uploadService;

	public static final String CONTENT_DISPOSITION_VALUE = "attachment; filename=";

//	@Post(uri = "/upload", consumes = MediaType.APPLICATION_JSON)
//	HttpResponse<String> UplaodFile(@Parameter("file") File file)  throws Exception{
//
//		try {
//			System.out.println(file.getName());
//			System.out.println(file.getTotalSpace());
//			System.out.println(file.getAbsolutePath());
//			System.out.println(file.getPath());
//			
//			
//
//			return HttpResponse.status(HttpStatus.CREATED).body("upload file");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}
//	
//	
	@Post(uri = "/uploadDoc", consumes = { MediaType.MULTIPART_FORM_DATA })
	public String uploadFile(CompletedFileUpload file) throws IOException {
//		System.out.println(file.getFilename());
//		System.out.println(file.getContentType());
		String uplaodMedia = uploadService.uplaodMedia(file);
		return uplaodMedia;
	}

//	 
	@Get(uri = "/download/{id}", produces = MediaType.APPLICATION_OCTET_STREAM)
	public String downLoadFile(@PathVariable String id) throws IOException {
		try {
			VendorMedia media = uploadService.downloadFile(id);
//			StreamedFile streamedFile = new StreamedFile(media.getMediaInputStream(),
//					MediaType.APPLICATION_OCTET_STREAM_TYPE);

/*			return HttpResponse.ok().body(streamedFile).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*").header(
					HttpHeaders.CONTENT_DISPOSITION, FileController.CONTENT_DISPOSITION_VALUE + media.getMediaName());
*/
//		return HttpResponse.ok().contentType(MediaType.APPLICATION_OCTET_STREAM_TYPE).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"*")
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attatchment:filename-\"" + media.getMediaName() + "\"")
//				.body(new ByteArrayResource(media.getMediaInputStream()));
			
			return media.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	

}
