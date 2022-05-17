package com.ampamt.moduler.service.payment;

import org.json.simple.parser.ParseException;

import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.model.PaymentOrderModel;
import com.ampamt.moduler.model.SuccessModel;

public interface PaymentService {

	SuccessModel getPaymentData(AmpamtTransactionBean ampamtTransactionBean) throws ParseException;

	PaymentOrderModel getOrderDetails(AmpamtTransactionBean ampamtTransactionBean);

//	SuccessModel getPaymentResponse(AmpamtTransactionBean ampamtTransactionBean) throws SignatureException;

}
