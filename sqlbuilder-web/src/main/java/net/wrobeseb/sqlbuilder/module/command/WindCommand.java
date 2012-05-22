package net.wrobeseb.sqlbuilder.module.command;

import java.io.Serializable;

import org.apache.myfaces.custom.fileupload.UploadedFile;

public class WindCommand implements Serializable {

	private static final long serialVersionUID = -4976602893715971702L;

	private UploadedFile deviceFile;
	private UploadedFile syntheticFile;
	
	private UploadedFile mdbFile;
	
	public UploadedFile getDeviceFile() {
		return deviceFile;
	}
	public void setDeviceFile(UploadedFile deviceFile) {
		this.deviceFile = deviceFile;
	}
	public UploadedFile getSyntheticFile() {
		return syntheticFile;
	}
	public void setSyntheticFile(UploadedFile syntheticFile) {
		this.syntheticFile = syntheticFile;
	}
	public UploadedFile getMdbFile() {
		return mdbFile;
	}
	public void setMdbFile(UploadedFile mdbFile) {
		this.mdbFile = mdbFile;
	}
	
	
}
