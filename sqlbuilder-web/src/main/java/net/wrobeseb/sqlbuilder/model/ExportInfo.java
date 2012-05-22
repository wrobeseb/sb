package net.wrobeseb.sqlbuilder.model;

import java.io.Serializable;

public class ExportInfo implements Serializable {

	private static final long serialVersionUID = -617308552706863706L;
	
	private Integer exportFileType;

	private String[] emailAddresses;
	
	private String otherEmails;

	public Integer getExportFileType() {
		return exportFileType;
	}
	public void setExportFileType(Integer exportFileType) {
		this.exportFileType = exportFileType;
	}
	public String[] getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(String[] emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	public String getOtherEmails() {
		return otherEmails;
	}
	public void setOtherEmails(String otherEmails) {
		this.otherEmails = otherEmails;
	}
}
