package com.ampamt.moduler.model;

import java.util.List;

import com.ampamt.moduler.constant.ApplicationConstant;

public class SuccessModel {

	private String status=ApplicationConstant.NOTFOUNT;
	private String name;
	private String id;
	private String msg;
	private String activeFlag;
	private String emailId;
	private Integer count;
	private String accountType;
	private String otp;
	private String statusType;
	private String data;
	private String accountExists;
	private String orderId;
	private String paidFlag;
	private double amount;
	private String cashAccountId;
	private String msgTitle;
	
	private List<DocumentsVerificationModel> documentsVerificationModelList;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public List<DocumentsVerificationModel> getDocumentsVerificationModelList() {
		return documentsVerificationModelList;
	}

	public void setDocumentsVerificationModelList(List<DocumentsVerificationModel> documentsVerificationModelList) {
		this.documentsVerificationModelList = documentsVerificationModelList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAccountExists() {
		return accountExists;
	}

	public void setAccountExists(String accountExists) {
		this.accountExists = accountExists;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaidFlag() {
		return paidFlag;
	}

	public void setPaidFlag(String paidFlag) {
		this.paidFlag = paidFlag;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCashAccountId() {
		return cashAccountId;
	}

	public void setCashAccountId(String cashAccountId) {
		this.cashAccountId = cashAccountId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	
	
	
}
