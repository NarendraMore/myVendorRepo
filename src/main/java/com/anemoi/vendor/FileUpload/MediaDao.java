package com.anemoi.vendor.FileUpload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import javax.inject.Singleton;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.anemoi.vendor.configuration.DatabaseName;
import com.anemoi.vendor.databaseConfiguration.VendorDatabaseUtill;


@Singleton
public class MediaDao {

	public String uploadMedia(VendorMedia media) {

		List<String> tenantList = DatabaseName.getAllTenant();
		for (String tenant : tenantList) {
			String dataBaseName = DatabaseName.dataBaseName(tenant);
			Connection connection = null;
			PreparedStatement preparedStatment = null;
			try {
				connection = VendorDatabaseUtill.getConnection();
				preparedStatment = connection
						.prepareStatement("INSERT INTO " + dataBaseName + ".dbo.MediaData values(?,?,?,?)");

				String mediaId = UUID.randomUUID().toString();
				byte[] inputStream = media.getMediaInputStream();
				String fileName = media.getMediaName();
				String mediaType = media.getMediaType();

				preparedStatment.setString(1, mediaId);
				preparedStatment.setString(2, fileName);
				preparedStatment.setString(3, mediaType);
				preparedStatment.setBytes(4, inputStream);
				int executeUpdate = preparedStatment.executeUpdate();
				System.out.println(executeUpdate);
				return mediaId;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

//	method to download the document
	public VendorMedia downloadDoc(String id) {
		List<String> tenantList = DatabaseName.getAllTenant();
		for (String tenant : tenantList) {
			String dataBaseName = DatabaseName.dataBaseName(tenant);
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement preparedStatment = null;
			try {
				connection = VendorDatabaseUtill.getConnection();
				preparedStatment = connection
						.prepareStatement("SELECT * FROM " + dataBaseName + ".dbo.MediaData WHERE mediaId =?");
				preparedStatment.setString(1, id);
				resultSet = preparedStatment.executeQuery();
				while (resultSet.next()) {
					VendorMedia media = buildMediaFromResultSet(resultSet);
					return media;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return null;
	}

	private VendorMedia buildMediaFromResultSet(ResultSet resultSet) throws Exception {

		VendorMedia media = new VendorMedia();
		String mediaId = resultSet.getString("mediaId");
		String mediaName = resultSet.getString("mediaName");
		String mediaType = resultSet.getString("type");
		byte[] bytes = resultSet.getBytes("inputstream");

//		byte[] docBytes = new ByteArrayInputStream(bytes);

		media.setMediaId(mediaId);
		media.setMediaName(mediaName);
		media.setMediaType(mediaType);
		media.setMediaInputStream(bytes);
		String extension = FilenameUtils.getExtension(mediaName);

		String newFileName = media.getMediaName();

		String newFileNameWithExt = newFileName + "." + extension;

		InputStream targetStream = new ByteArrayInputStream(bytes);

		String home = System.getProperty("user.home");
		File withFile = new File(home + "/Downloads/" + newFileNameWithExt);
		if (mediaName == null || mediaName.isEmpty()) {
			System.out.println(mediaName + "Doesnt have any content to show");
		}
		boolean removeAllExtensions = false;

		String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
		String filenamewithoutextension = mediaName.replaceAll(extPattern, "");

		int count = 0;

		while (withFile.exists()) {

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
		VendorMedia vendorMedia = null;
		fis = new FileInputStream(withFile);
		byte[] byteArrayOfFile = IOUtils.toByteArray(fis);
		vendorMedia = new VendorMedia();
		vendorMedia.setMediaName(mediaName);
		System.out.println("download file " + vendorMedia);
		return vendorMedia;

//		String fileFolderName = media.getMediaName();
//		File fileName = new File("C:\\Users\\dell\\Downloads");
//		String home = System.getProperty("user.home");
//		File file = new File(home+"/Downloads/");
//		
//		File fileName = new File(file);
//
//		if (fileName.exists()) {
//
//			FileUtils.deleteDirectory(fileName);
//			System.out.println("Deleting exsisting directory....creating a new directory");
//		}
//		if (fileName.mkdir()) {
//			System.out.println("....creating a new directory");
//
//		}
//		VendorMedia docFile = createFilesFromInputStream(media);
//
//		return docFile;

	}

	@SuppressWarnings("unused")
	private VendorMedia createFilesFromInputStream(VendorMedia media) throws Exception {

//			VendorMedia fileMedia = new VendorMedia();
		String mediaName = media.getMediaName();
		String mediaId = media.getMediaId().toLowerCase();
		String extension = FilenameUtils.getExtension(mediaName);
		byte[] data = media.getMediaInputStream();
		String newFileName = media.getMediaName();
		String newFileNameWithExt = newFileName + "." + extension;
		InputStream targetStream = new ByteArrayInputStream(data);
		String home = System.getProperty("user.home");
		File withFile = new File(home + "/Downloads/" + File.separator + newFileNameWithExt);

		System.out.println("creating file " + withFile);
		FileOutputStream fos = new FileOutputStream(withFile);
		IOUtils.copy(targetStream, fos);

		fos.write(data);

		fos.close();
		FileInputStream fis = null;
		VendorMedia vendorMedia = null;
		fis = new FileInputStream(withFile);
		byte[] byteArrayOfFile = IOUtils.toByteArray(fis);
		vendorMedia = new VendorMedia();
		vendorMedia.setMediaName(mediaName);
//		vendorMedia.setMediaInputStream(new ByteArrayInputStream(byteArrayOfFile));
//		vendorMedia.setMediaInputStream(targetStream);

		return vendorMedia;

	}

}
