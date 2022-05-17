package com.ampamt.moduler.controller.payment;

import java.security.SignatureException;
import java.util.List;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.PaymentOrderModel;
import com.ampamt.moduler.model.PaymentResponseModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.service.payment.PaymentService;
import com.ampamt.moduler.service.transaction.TransactionService;
import com.ampamt.moduler.util.CommonValidator;
import com.ampamt.moduler.util.DumpBean;
import com.ampamt.moduler.util.RozarpaySignature;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/payment")
public class PaymentController {

	private final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value = "/get-payment-data", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> getPaymentData(@RequestBody AmpamtTransactionBean ampamtTransactionBean) throws ParseException {
		logger.debug("*******************start updateBusinessDocuments***************************");
	
		SuccessModel scuccessModel = paymentService.getPaymentData(ampamtTransactionBean);

		logger.debug("*******************end updateBusinessDocuments***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-order-details", method = RequestMethod.POST)
	public ResponseEntity<PaymentOrderModel> getOrderDetails(@RequestBody AmpamtTransactionBean ampamtTransactionBean) throws ParseException {
		logger.debug("*******************start getOrderDetails***************************");
	
		PaymentOrderModel scuccessModel = paymentService.getOrderDetails(ampamtTransactionBean);

		logger.debug("*******************end getOrderDetails***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-payment-response", method = RequestMethod.POST)
	public RedirectView getPaymentResponse(@RequestParam Optional <String> razorpay_payment_id,
			@RequestParam Optional <String> razorpay_order_id,@RequestParam Optional <String> razorpay_signature,@RequestParam Optional <String> transactionId) throws SignatureException {
		
		String returnUrl="";
		String razorpayPaymentId="";
		String razorpayOrderId="";
		String razorpaySignature="";
		String trxnId=null;
		
		if(razorpay_payment_id.isPresent() && razorpay_order_id.isPresent() && razorpay_signature.isPresent() && transactionId.isPresent()) {
			razorpayPaymentId=razorpay_payment_id.get();
			razorpayOrderId=razorpay_order_id.get();
			razorpaySignature=razorpay_signature.get();
			trxnId=transactionId.get();
		}else {
			returnUrl=ApplicationConstant.PAYMENT_FAILED_URL+"?transactionId="+transactionId;
			return new RedirectView(returnUrl);
		}
		
		if(trxnId==null || trxnId=="") {
			AmpamtTransactionBean ampamtTransactionBean=new AmpamtTransactionBean();
			ampamtTransactionBean.setOrderId(razorpayOrderId);
			List<AmpamtTransactionBean> ampamtTransactionBeanList=transactionService.getTransactionDetail(ampamtTransactionBean);
			if(ampamtTransactionBeanList.size()==1) {
				trxnId=ampamtTransactionBeanList.get(0).getOrderId();
			}else {
				trxnId=null;
			}
			
			
		}
		logger.debug("*******************start getPaymentResponse***************************");
		logger.debug("*******************saving payment response***************************");
		logger.debug("razorpay_signature="+razorpay_signature+" razorpay_payment_id="+razorpay_payment_id+" razorpay_order_id="+razorpay_order_id);
		logger.debug("razorpaySignature="+razorpaySignature+" razorpayPaymentId="+razorpayPaymentId+" razorpayOrderId="+razorpayOrderId);
		
		String secret = environment.getProperty("razorpay.secret");
		RozarpaySignature rozarpaySignature=new RozarpaySignature();
		SuccessModel successModel=new SuccessModel();
		AmpamtTransactionBean ampamtTransactionBean=new AmpamtTransactionBean();
		ampamtTransactionBean.setOrderId(razorpayOrderId);
		ampamtTransactionBean.setSignature(razorpaySignature);
		ampamtTransactionBean.setTransactionId(trxnId);
		ampamtTransactionBean.setPaymentId(razorpayPaymentId);
		DumpBean dumpBean=new DumpBean();
		logger.debug(dumpBean.dumpBean(ampamtTransactionBean));
		
		String generated_signature =rozarpaySignature.calculateRFC2104HMAC(ampamtTransactionBean.getOrderId() + "|" + ampamtTransactionBean.getPaymentId(), secret);
		logger.debug("generated_signature="+generated_signature);
		
		if(generated_signature.equals(ampamtTransactionBean.getSignature())) {
			ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_Y);
			ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_SUCCESS);
			returnUrl=ApplicationConstant.PAYMENT_SUCCESS_URL+"?transactionId="+trxnId;
		}else {
			ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_N);
			ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_FAILED);
			returnUrl=ApplicationConstant.PAYMENT_FAILED_URL+"?transactionId="+trxnId;
		}
		
		successModel = transactionService.updateTransaction(ampamtTransactionBean);
		successModel.setPaidFlag(ampamtTransactionBean.getPaidFlag());
		logger.debug("returnUrl="+returnUrl);
		logger.debug("*******************end getPaymentResponse***************************");

		return new RedirectView(returnUrl);
	}
	
	@RequestMapping(value = "/save-app-payment-response", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> getAppPaymentResponse(@RequestBody PaymentResponseModel paymentResponseModel ) throws SignatureException {
		
		String transactionId="";
		SuccessModel successModel=new SuccessModel();
		if(!CommonValidator.isBlank(paymentResponseModel.getOrderId())) {
		
			if(paymentResponseModel.getTransactionId()==null || paymentResponseModel.getTransactionId()=="") {
				if(paymentResponseModel.getOrderId()!=null || paymentResponseModel.getOrderId()!="") {
					AmpamtTransactionBean ampamtTransactionBean=new AmpamtTransactionBean();
					ampamtTransactionBean.setOrderId(paymentResponseModel.getOrderId());
					ampamtTransactionBean.setAccountId(paymentResponseModel.getAccountId());
					List<AmpamtTransactionBean> ampamtTransactionBeanList=transactionService.getTransactionDetail(ampamtTransactionBean);
					if(ampamtTransactionBeanList.size()>0) {
						transactionId=ampamtTransactionBeanList.get(0).getTransactionId();
					}
				}
			}
			else {
				transactionId=paymentResponseModel.getTransactionId();
			}
			
			logger.debug("*******************start getAppPaymentResponse***************************");
			logger.debug("*******************saving payment response***************************");
			logger.debug("getSignature="+paymentResponseModel.getSignature()+" getPaymentId="+paymentResponseModel.getPaymentId()+" getOrderId="+paymentResponseModel.getOrderId());
			
			String secret = environment.getProperty("razorpay.secret");
			RozarpaySignature rozarpaySignature=new RozarpaySignature();
			AmpamtTransactionBean ampamtTransactionBean=new AmpamtTransactionBean();
			ampamtTransactionBean.setOrderId(paymentResponseModel.getOrderId());
			ampamtTransactionBean.setSignature(paymentResponseModel.getSignature());
			ampamtTransactionBean.setPaymentId(paymentResponseModel.getPaymentId());
			ampamtTransactionBean.setTransactionId(transactionId);
			
			DumpBean dumpBean=new DumpBean();
			logger.debug(dumpBean.dumpBean(ampamtTransactionBean));
			
			String generated_signature =rozarpaySignature.calculateRFC2104HMAC(ampamtTransactionBean.getOrderId() + "|" + ampamtTransactionBean.getPaymentId(), secret);
			logger.debug("generated_signature="+generated_signature);
			logger.debug("ampamtTransactionBean getSignature="+ampamtTransactionBean.getSignature());
			
			if(generated_signature.equals(ampamtTransactionBean.getSignature())) {
				logger.debug("############## signature mattched #################");
				ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_Y);
				ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_SUCCESS);
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			}else {
				logger.debug("############## signature not mattched #################");
				ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_N);
				ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_FAILED);
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			}
			
			successModel = transactionService.updateTransaction(ampamtTransactionBean);
			successModel.setPaidFlag(ampamtTransactionBean.getPaidFlag());
			successModel.setStatus(ampamtTransactionBean.getStatus());
			logger.debug("*******************end getAppPaymentResponse***************************");
		}
		else 
		{
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
		}
		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
}
