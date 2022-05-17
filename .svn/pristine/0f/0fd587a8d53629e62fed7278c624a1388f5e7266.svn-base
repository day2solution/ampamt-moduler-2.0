package com.ampamt.moduler.controller.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ampamt.moduler.bean.transaction.AmpamtCashAccountBean;
import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.service.transaction.TransactionService;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/transaction")
public class TransactionController {

private final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	
	@RequestMapping(value = "/get-transaction-detail", method = RequestMethod.POST)
	public ResponseEntity<List<AmpamtTransactionBean>> getTransactionDetail(@RequestBody AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("*******************start getTransactionDetail***************************");
	
		List<AmpamtTransactionBean> ampamtTransactionBeanList = transactionService.getTransactionDetail(ampamtTransactionBean);

		logger.debug("*******************end getTransactionDetail***************************");

		return new ResponseEntity<>(ampamtTransactionBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-cash-account-detail", method = RequestMethod.POST)
	public ResponseEntity<List<AmpamtCashAccountBean>> getCashAccountDetail(@RequestBody AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("*******************start getCashAccountDetail***************************");
	
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = transactionService.getCashAccountDetail(ampamtCashAccountBean);

		logger.debug("*******************end getCashAccountDetail***************************");

		return new ResponseEntity<>(ampamtCashAccountBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-transaction", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createTransaction(@RequestBody AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("*******************start createTransaction***************************");
	
		SuccessModel scuccessModel = transactionService.createTransaction(ampamtTransactionBean);

		logger.debug("*******************end createTransaction***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/create-cash-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createCashAccount(@RequestBody AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("*******************start createCashAccount***************************");
	
		SuccessModel scuccessModel = transactionService.createCashAccount(ampamtCashAccountBean);

		logger.debug("*******************end createCashAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-transaction", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateTransaction(@RequestBody AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("*******************start updateTransaction***************************");
	
		SuccessModel scuccessModel = transactionService.updateTransaction(ampamtTransactionBean);

		logger.debug("*******************end updateTransaction***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-cash-account", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateCashAccount(@RequestBody AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("*******************start updateCashAccount***************************");
	
		SuccessModel scuccessModel = transactionService.updateCashAccount(ampamtCashAccountBean);

		logger.debug("*******************end updateCashAccount***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
}
