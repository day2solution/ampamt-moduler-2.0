package com.ampamt.moduler.bean.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ampamt_transactions")
@JsonInclude(Include.NON_NULL)
public class AmpamtTransactionBean {

	@Id
    @Column(name="TRANSACTION_ID")
	private String transactionId;
	
	@Column(name="CASH_ACCOUNT_ID")
	private String cashAccountId;
	
    @Column(name="ACCOUNT_ID")
    private String accountId;
	
    @Column(name="PAID_FLAG")
    private String paidFlag;
	
    @Column(name="AMOUNT")
    private double amount;
	
    @Column(name="CREATE_DATE")
    private Date createDate;
	
    @Column(name="TRANSACTION_DATE")
    private Date transactionDate;
    
    @Column(name="UPDATE_DATE")
    private Date updateDate;
	
    @Column(name="STATUS")
    private String status;
	
    @Column(name="REMARKS")
    private String remarks;
    
    @Column(name="PAYMENT_METHOD")
    private String paymentMethod;
    
    @Column(name="ORDER_ID")
    private String orderId;
    
    @Column(name="PAYMENT_ID")
    private String paymentId;
    
    @Column(name="SIGNATURE")
    private String signature;
    
    @Column(name="EXTRA")
    private String extra;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCashAccountId() {
		return cashAccountId;
	}

	public void setCashAccountId(String cashAccountId) {
		this.cashAccountId = cashAccountId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
    
}
