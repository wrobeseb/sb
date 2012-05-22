package net.wrobeseb.sqlbuilder.module.command;

import java.io.Serializable;
import java.util.Date;

public class SokxCommand implements Serializable {
	
	private static final long serialVersionUID = -6454613081769836579L;
	
	private String sokxId;
	private String objectId;
	private Date createdDt;
	private Date modifiedDt;
	private String statusId;
	private String messageBody;
	private String errorCode;
	private String errorDesc;

	public String getSokxId() {
		return sokxId;
	}
	public void setSokxId(String sokxId) {
		this.sokxId = sokxId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Date getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	public Date getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(Date modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
