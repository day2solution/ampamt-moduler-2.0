package com.ampamt.moduler.service.payment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.dao.transaction.TransactionDao;
import com.ampamt.moduler.model.PaymentOrderModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.util.CommonValidator;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	@Autowired
	private Environment environment;

	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public SuccessModel getPaymentData(AmpamtTransactionBean ampamtTransactionBean) throws ParseException {
		logger.debug("*******************start getPaymentData***************************");
		
		ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_N);
		ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_PENDING);
		List<AmpamtTransactionBean> ampamtTransactionBeanList=transactionDao.getTransactionDetail(ampamtTransactionBean);
//		
		SuccessModel successModel=new SuccessModel();
		if(ampamtTransactionBeanList!=null && ampamtTransactionBeanList.size()>0) {

			successModel.setOrderId(ampamtTransactionBeanList.get(0).getOrderId());
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setId(ampamtTransactionBeanList.get(0).getTransactionId());
		}else {
			
		
			String secret = environment.getProperty("razorpay.secret");
			String key = environment.getProperty("razorpay.key");
			String strAmount = environment.getProperty("ampamt.doctors.amount");
			logger.debug("strAmount=" + strAmount);
			double amount=ApplicationConstant.AMOUNT;
			if(!CommonValidator.isBlank(strAmount)) {
				amount = Double.parseDouble(strAmount);
			}else {
				amount = ApplicationConstant.AMOUNT;
			}
			double amountInRupees=amount/100;
			String trxnNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
			trxnNo = "TRXN" + trxnNo;
			logger.debug("trxnNo=" + trxnNo);
			
			try {
				org.json.JSONObject options = new org.json.JSONObject();
				options.put("amount", amount);
				options.put("currency", "INR");
				options.put("receipt", trxnNo);
				RazorpayClient razorpayClient = new RazorpayClient(key, secret);
				Order order = razorpayClient.Orders.create(options);
				JSONParser parser = new JSONParser();
				
				logger.debug("order=" + order);
				if(order!=null) {
					org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(order.toString());
					String orderId=(String) json.get("id");
					
					ampamtTransactionBean.setOrderId(orderId);
					ampamtTransactionBean.setExtra(order.toString());
					ampamtTransactionBean.setTransactionId(trxnNo);
					
					ampamtTransactionBean.setAmount(amountInRupees);
					successModel = transactionDao.createTransaction(ampamtTransactionBean);
					successModel.setOrderId(orderId);
				}else {
					successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				}
				
				
			} catch (RazorpayException e) {
				// Handle Exception
				logger.debug(e.getMessage());
			}
		}
		logger.debug("*******************end getPaymentData***************************");
		return successModel;
	}

	@Override
	public PaymentOrderModel getOrderDetails(AmpamtTransactionBean ampamtTransactionBean){
		logger.debug("*******************start getPaymentData***************************");
		
		PaymentOrderModel paymentOrderModel=new PaymentOrderModel();
		
			String secret = environment.getProperty("razorpay.secret","rdqgnSNytN29pXipCRlJ2Iwo");
			String key = environment.getProperty("razorpay.key","rzp_live_RovxCktSDjNyUB");
			
			try {
				
				RazorpayClient razorpayClient = new RazorpayClient(key, secret);
				Order order = razorpayClient.Orders.fetch(ampamtTransactionBean.getOrderId());
				logger.debug("order="+order);
				if(org.json.JSONObject.NULL.equals(order.get("offer_id"))) {
					logger.debug("offer_id null found");
				}else {
					logger.debug("offer_id null not found");
				}
				
				paymentOrderModel.setAmount(order.get("amount"));
				paymentOrderModel.setAmountPaid(order.get("amount_paid"));
				paymentOrderModel.setNotes(order.get("notes"));
				paymentOrderModel.setCreatedAt(order.get("created_at"));
				paymentOrderModel.setAmountDue(order.get("amount_due"));
				paymentOrderModel.setCurrency(order.get("currency"));
				paymentOrderModel.setTransactionId(order.get("receipt"));
				paymentOrderModel.setOrderId(order.get("id"));
				paymentOrderModel.setOfferId(org.json.JSONObject.NULL.equals(order.get("offer_id"))?"":order.get("offer_id"));
				paymentOrderModel.setStatus(order.get("status"));
				paymentOrderModel.setAttempts(order.get("attempts"));
				paymentOrderModel.setFetchStatus(ApplicationConstant.STATUS_SUCCESS);
			
			} catch (RazorpayException e) {
				
				logger.debug(e.getMessage());
				paymentOrderModel.setMsg(e.getMessage());
				paymentOrderModel.setFetchStatus(ApplicationConstant.STATUS_FAILED);
			}
			 
		logger.debug("*******************end getPaymentData***************************");
		return paymentOrderModel;
	}

//	@Override
//	public SuccessModel getPaymentResponse(AmpamtTransactionBean ampamtTransactionBean) throws SignatureException {
//		logger.debug("*******************start getPaymentResponse***************************");
//		String secret = environment.getProperty("razorpay.secret");
//		RozarpaySignature rozarpaySignature=new RozarpaySignature();
//		SuccessModel successModel=new SuccessModel();
//		String generated_signature =rozarpaySignature.calculateRFC2104HMAC(ampamtTransactionBean.getOrderId() + "|" + ampamtTransactionBean.getPaymentId(), secret);
//		
//		if(generated_signature.equals(ampamtTransactionBean.getSignature())) {
//			ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_Y);
//			ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_SUCCESS);
//		}else {
//			ampamtTransactionBean.setPaidFlag(ApplicationConstant.FLAG_N);
//			ampamtTransactionBean.setStatus(ApplicationConstant.STATUS_FAILED);
//		}
//		successModel = transactionDao.updateTransaction(ampamtTransactionBean);
//		successModel.setPaidFlag(ampamtTransactionBean.getPaidFlag());
//		logger.debug("*******************end getPaymentResponse***************************");
//		return successModel;
//	}

}
