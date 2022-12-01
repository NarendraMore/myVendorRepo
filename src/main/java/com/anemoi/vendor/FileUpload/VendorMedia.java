package com.anemoi.vendor.FileUpload;

import java.io.InputStream;
import java.util.Arrays;

public class VendorMedia {
	private String mediaId;
	
	private byte[] mediaInputStream;
	
	private String mediaName;
	
	private long length;
	
	private String mediaType;
	
	private long uploadDate;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public byte[] getMediaInputStream() {
		return mediaInputStream;
	}

	public void setMediaInputStream(byte[] mediaInputStream) {
		this.mediaInputStream = mediaInputStream;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public long getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(long uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		return "VendorMedia [mediaId=" + mediaId + ", mediaInputStream=" + Arrays.toString(mediaInputStream)
				+ ", mediaName=" + mediaName + ", length=" + length + ", mediaType=" + mediaType + ", uploadDate="
				+ uploadDate + "]";
	}

	public VendorMedia(String mediaId, byte[] mediaInputStream, String mediaName, long length, String mediaType,
			long uploadDate) {
		super();
		this.mediaId = mediaId;
		this.mediaInputStream = mediaInputStream;
		this.mediaName = mediaName;
		this.length = length;
		this.mediaType = mediaType;
		this.uploadDate = uploadDate;
	}

	public VendorMedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setMediaInputStream1(byte[] bytes) {
		// TODO Auto-generated method stub
		
	}//
	
	
	
}
