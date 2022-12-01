package com.anemoi.vendor.FileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.json.simple.JSONObject;

import io.micronaut.http.multipart.CompletedFileUpload;

@Singleton
public class FileUploadService {
	private static final String UPLOAD_MEDIA_ID = "uploadMediaId";
	private static final String STATUS = "status";

	@Inject
	private MediaDao mediaDao;

	@SuppressWarnings("unchecked")
	public String uplaodMedia(CompletedFileUpload file) {
		try {

			VendorMedia media = new VendorMedia();

			String fileName = file.getFilename();
			String type = file.getContentType().toString();
			byte[] data = file.getBytes();

//			String filePatter = UUID.randomUUID().toString() + "_" + fileName;
//			File docFile = new File(filePatter);
//
//			docFile.createNewFile();
//			FileOutputStream fos = new FileOutputStream(docFile);
//			fos.write(data);
//			fos.flush();
//			fos.close();
//			InputStream inputStream = new FileInputStream(docFile);

			media.setMediaName(fileName);
			media.setMediaType(type);
			media.setMediaInputStream(data);

			// media.setMediaInputStream(inputStream);

//			System.out.println("this is media " + media);

			String uploadedMediaId = mediaDao.uploadMedia(media);

			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put(STATUS, "success");
			jsonResponse.put(UPLOAD_MEDIA_ID, uploadedMediaId);
			return jsonResponse.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public VendorMedia downloadFile(String id) {

		VendorMedia media = mediaDao.downloadDoc(id);

		return media;

	}

}
