package com.ampamt.moduler.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ampamt_audit_trail")
@JsonInclude(Include.NON_NULL)
public class AmpamtAuditTrailBean {

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="ACCOUNT_ID")
	private String accountId;
	
	@Column(name="ACTION")
	private String action;
	
	@Column(name="SERVER_IP")
	private String serverIp;
	
	@Column(name="CLIENT_IP")
	private String clientip;
	
	@Column(name="AUDIT_DATE")
	private Date auditDate;
	
	@Column(name="MODULE")
	private String module;
	
	@Column(name="APP_VERSION")
	private String appVersion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	
}
