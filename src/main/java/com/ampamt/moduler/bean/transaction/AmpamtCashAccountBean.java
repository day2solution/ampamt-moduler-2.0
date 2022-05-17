package com.ampamt.moduler.bean.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="ampamt_cash_account")
@JsonInclude(Include.NON_NULL)
public class AmpamtCashAccountBean {

	@Id
    @Column(name="CASH_ACCOUNT_ID")
	private String cashAccountId;
	
    @Column(name="ACCOUNT_ID")
    private String accountId;
	
    @Column(name="UPDATED_BY")
    private String updatedBy;
	
    @Column(name="AVAILABLE_AMOUNT")
    private double availableAmount;
	
    @Column(name="CREATE_DATE")
    private Date createDate;
	
    @Column(name="UPDATE_DATE")
    private Date updateDate;

	public String getCashAccountId() {
		return cashAccountId;
	}

	public void setCashAccountId(String cashAccountId) {
		this.cashAccountId = cashAccountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public double getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
    
	
}
