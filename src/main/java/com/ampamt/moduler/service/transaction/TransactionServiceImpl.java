package com.ampamt.moduler.service.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.transaction.AmpamtCashAccountBean;
import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.dao.transaction.TransactionDao;
import com.ampamt.moduler.model.SuccessModel;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public List<AmpamtTransactionBean> getTransactionDetail(AmpamtTransactionBean ampamtTransactionBean) {
		List<AmpamtTransactionBean> ampamtTransactionBeanList = transactionDao.getTransactionDetail(ampamtTransactionBean);
		return ampamtTransactionBeanList;
	}

	@Override
	public List<AmpamtCashAccountBean> getCashAccountDetail(AmpamtCashAccountBean ampamtCashAccountBean) {
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = transactionDao.getCashAccountDetail(ampamtCashAccountBean);
		return ampamtCashAccountBeanList;
	}

	@Override
	public SuccessModel createTransaction(AmpamtTransactionBean ampamtTransactionBean) {
		SuccessModel scuccessModel = transactionDao.createTransaction(ampamtTransactionBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel createCashAccount(AmpamtCashAccountBean ampamtCashAccountBean) {
		SuccessModel scuccessModel = transactionDao.createCashAccount(ampamtCashAccountBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel updateTransaction(AmpamtTransactionBean ampamtTransactionBean) {
		SuccessModel scuccessModel = transactionDao.updateTransaction(ampamtTransactionBean);
		if(scuccessModel.getStatus().equals(ApplicationConstant.STATUS_SUCCESS) && 
				(ampamtTransactionBean.getPaidFlag()!=null && ampamtTransactionBean.getPaidFlag().equals(ApplicationConstant.FLAG_Y))) {
			AmpamtCashAccountBean ampamtCashAccountBean=new AmpamtCashAccountBean();
			ampamtCashAccountBean.setAvailableAmount(scuccessModel.getAmount());
			ampamtCashAccountBean.setCashAccountId(scuccessModel.getCashAccountId());
			transactionDao.updateCashAccount(ampamtCashAccountBean);
		}
		return scuccessModel;
	}

	@Override
	public SuccessModel updateCashAccount(AmpamtCashAccountBean ampamtCashAccountBean) {
		SuccessModel scuccessModel = transactionDao.updateCashAccount(ampamtCashAccountBean);
		return scuccessModel;
	}

}
