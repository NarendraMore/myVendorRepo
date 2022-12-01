package com.anemoi.vendor.NewProjectModel;

import java.util.Arrays;

public class Document {
	
	private String docId;
	
	private String docName;
	
	private String docType;
	
	private byte[] docData;
	
	private long uploadedDate;
	
	private String projectId;
	
	private int version;

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getDocData() {
		return docData;
	}

	public void setDocData(byte[] docData) {
		this.docData = docData;
	}

	public long getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(long uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Document [docId=" + docId + ", docName=" + docName + ", docType=" + docType + ", docData="
				+ Arrays.toString(docData) + ", uploadedDate=" + uploadedDate + ", projectId=" + projectId
				+ ", version=" + version + "]";
	}

	public Document(String docId, String docName, String docType, byte[] docData, long uploadedDate, String projectId,
			int version) {
		super();
		this.docId = docId;
		this.docName = docName;
		this.docType = docType;
		this.docData = docData;
		this.uploadedDate = uploadedDate;
		this.projectId = projectId;
		this.version = version;
	}

	public Document() {
		super();
	}

	
	
}