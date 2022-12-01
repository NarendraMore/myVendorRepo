package com.anemoi.vendor.NewProjectModel;

import java.util.Arrays;

public class FileUpload {
	
	private byte[] file;
	
	private String filename;
	
	private String filetype;
	
	private String id;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FileUpload [file=" + Arrays.toString(file) + ", filename=" + filename + ", filetype=" + filetype
				+ ", id=" + id + "]";
	}

	public FileUpload(byte[] file, String filename, String filetype, String id) {
		super();
		this.file = file;
		this.filename = filename;
		this.filetype = filetype;
		this.id = id;
	}

	public FileUpload() {
		super();
	}

	
}

