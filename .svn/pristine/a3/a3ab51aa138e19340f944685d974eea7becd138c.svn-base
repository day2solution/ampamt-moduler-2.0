package com.ampamt.moduler.dao.transaction;

import java.util.List;

import com.ampamt.moduler.bean.transaction.AmpamtCashAccountBean;
import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.model.SuccessModel;

public interface TransactionDao {

	List<AmpamtTransactionBean> getTransactionDetail(AmpamtTransactionBean ampamtTransactionBean);

	List<AmpamtCashAccountBean> getCashAccountDetail(AmpamtCashAccountBean ampamtCashAccountBean);
	
	List<AmpamtTransactionBean> getTransactionDetailById(AmpamtTransactionBean ampamtTransactionBean);

	List<AmpamtCashAccountBean> getCashAccountDetailById(AmpamtCashAccountBean ampamtCashAccountBean);

	SuccessModel createTransaction(AmpamtTransactionBean ampamtTransactionBean);

	SuccessModel createCashAccount(AmpamtCashAccountBean ampamtCashAccountBean);

	SuccessModel updateTransaction(AmpamtTransactionBean ampamtTransactionBean);

	SuccessModel updateCashAccount(AmpamtCashAccountBean ampamtCashAccountBean);

}
