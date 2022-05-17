package com.ampamt.moduler.dao.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ampamt.moduler.bean.transaction.AmpamtCashAccountBean;
import com.ampamt.moduler.bean.transaction.AmpamtTransactionBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.SuccessModel;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	private static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	protected Session currentSession() {

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();

		} catch (Exception ex) {
			logger.debug(" ++++ getSession catch ++++ ", ex);
			session = sessionFactory.openSession();
		}
		logger.debug("current session==>" + session);
		logger.debug("current sessionFactory==>" + sessionFactory.hashCode());
		return session;
	}

	@Override
	public List<AmpamtTransactionBean> getTransactionDetail(AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("***************start getTransactionDetail******************");
		Session session = currentSession();
		List<AmpamtTransactionBean> ampamtTransactionBeanList = new ArrayList<AmpamtTransactionBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtTransactionBean.class);
			if (ampamtTransactionBean.getAccountId() != null) {
//				AES.setKey("");
//				AES.decrypt(ampamtTransactionBean.getAccountId());
				cr.add(Restrictions.eq("accountId", ampamtTransactionBean.getAccountId()));
			}

			if (ampamtTransactionBean.getCashAccountId() != null) {
				cr.add(Restrictions.eq("cashAccountId", ampamtTransactionBean.getCashAccountId()));
			}

			if (ampamtTransactionBean.getTransactionId() != null) {
				cr.add(Restrictions.eq("transactionId", ampamtTransactionBean.getTransactionId()));
			}
			
			if (ampamtTransactionBean.getOrderId() != null) {
				cr.add(Restrictions.eq("orderId", ampamtTransactionBean.getOrderId()));
			}
			
			if (ampamtTransactionBean.getPaymentId() != null) {
				cr.add(Restrictions.eq("paymentId", ampamtTransactionBean.getPaymentId()));
			}
			
			if (ampamtTransactionBean.getPaidFlag() != null) {
				cr.add(Restrictions.eq("paidFlag", ampamtTransactionBean.getPaidFlag()));
			}
			
			if (ampamtTransactionBean.getStatus() != null) {
				cr.add(Restrictions.eq("status", ampamtTransactionBean.getStatus()));
			}
			
			ampamtTransactionBeanList = cr.list();
			logger.debug("ampamtTransactionBeanList size=" + ampamtTransactionBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getTransactionDetail******************");

		return ampamtTransactionBeanList;
	}

	@Override
	public List<AmpamtCashAccountBean> getCashAccountDetail(AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("***************start getCashAccountDetail******************");
		Session session = currentSession();
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = new ArrayList<AmpamtCashAccountBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtCashAccountBean.class);
			if (ampamtCashAccountBean.getAccountId() != null) {
//				AES.setKey("");
//				AES.decrypt(ampamtCashAccountBean.getAccountId());
				cr.add(Restrictions.eq("accountId", ampamtCashAccountBean.getAccountId()));
			}

			if (ampamtCashAccountBean.getCashAccountId() != null) {
				cr.add(Restrictions.eq("cashAccountId", ampamtCashAccountBean.getCashAccountId()));
			}

			cr.addOrder(Order.desc("createDate"));
			
			ampamtCashAccountBeanList = cr.list();
			logger.debug("ampamtCashAccountBeanList size=" + ampamtCashAccountBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getCashAccountDetail******************");

		return ampamtCashAccountBeanList;
	}

	@Override
	public SuccessModel createTransaction(AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("***************start createTransaction******************");

		String trxnNo = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		
		trxnNo = "TRXN" + trxnNo;
		logger.debug("trxnNo=" + trxnNo);
		if(ampamtTransactionBean.getTransactionId()==null) {
			ampamtTransactionBean.setTransactionId(trxnNo);
		}
		
		SuccessModel successModel = new SuccessModel();
		AmpamtCashAccountBean ampamtCashAccountBean=new AmpamtCashAccountBean();
		ampamtCashAccountBean.setAccountId(ampamtTransactionBean.getAccountId());
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = getCashAccountDetailById(ampamtCashAccountBean);
		if(ampamtCashAccountBeanList.size()==0) {
			createCashAccount(ampamtCashAccountBean);
			ampamtCashAccountBeanList = getCashAccountDetailById(ampamtCashAccountBean);
		}
		Session session = currentSession();
		String status = null;
		try {
			session.getTransaction().begin();
			if(ampamtCashAccountBeanList.size()>0) {
				
				
				
				ampamtTransactionBean.setTransactionDate(new Date());
				ampamtTransactionBean.setCreateDate(new Date());
				ampamtTransactionBean.setCashAccountId(ampamtCashAccountBeanList.get(0).getCashAccountId());
				ampamtTransactionBean.setAccountId(ampamtCashAccountBeanList.get(0).getAccountId());
				status = (String) session.save(ampamtTransactionBean);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;

				successModel.setId(ampamtTransactionBean.getTransactionId());
			}
			
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createTransaction save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createTransaction***************************");
		return successModel;

	}

	@Override
	public SuccessModel createCashAccount(AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("***************start createCashAccount******************");

		String cashAccId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		Session session = currentSession();
		cashAccId = "CA" + cashAccId;
		logger.debug("cashAccId=" + cashAccId);
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

//			AES.setKey("");
//			AES.decrypt(ampamtCashAccountBean.getAccountId());
			
			session.getTransaction().begin();
			ampamtCashAccountBean.setAccountId(ampamtCashAccountBean.getAccountId());
			ampamtCashAccountBean.setCashAccountId(cashAccId);
			ampamtCashAccountBean.setCreateDate(new Date());
			ampamtCashAccountBean.setUpdateDate(new Date());
			ampamtCashAccountBean.setAvailableAmount(0);
			status = (String) session.save(ampamtCashAccountBean);
			session.getTransaction().commit();
			status = ApplicationConstant.STATUS_SUCCESS;

			successModel.setId(cashAccId);
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status createCashAccount save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end createCashAccount***************************");
		return successModel;

	}

	@Override
	public List<AmpamtTransactionBean> getTransactionDetailById(AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("***************start getTransactionDetail******************");
		Session session = currentSession();
		List<AmpamtTransactionBean> ampamtTransactionBeanList = new ArrayList<AmpamtTransactionBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtTransactionBean.class);

			if (ampamtTransactionBean.getTransactionId() != null) {
				cr.add(Restrictions.eq("transactionId", ampamtTransactionBean.getTransactionId()));

				if (ampamtTransactionBean.getAccountId() != null) {
//					AES.setKey("");
//					AES.decrypt(ampamtTransactionBean.getAccountId());
					cr.add(Restrictions.eq("accountId",ampamtTransactionBean.getAccountId()));
				}

				if (ampamtTransactionBean.getCashAccountId() != null) {
					cr.add(Restrictions.eq("cashAccountId", ampamtTransactionBean.getCashAccountId()));
				}

				ampamtTransactionBeanList = cr.list();
			}

			logger.debug("ampamtTransactionBeanList size=" + ampamtTransactionBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getTransactionDetail******************");

		return ampamtTransactionBeanList;
	}

	@Override
	public List<AmpamtCashAccountBean> getCashAccountDetailById(AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("***************start getCashAccountDetail******************");
		Session session = currentSession();
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = new ArrayList<AmpamtCashAccountBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AmpamtCashAccountBean.class);

			if (ampamtCashAccountBean.getCashAccountId() != null || ampamtCashAccountBean.getAccountId() != null) {
				
				if (ampamtCashAccountBean.getCashAccountId() != null) {
					cr.add(Restrictions.eq("cashAccountId", ampamtCashAccountBean.getCashAccountId()));
				}

				if (ampamtCashAccountBean.getAccountId() != null) {
//					AES.setKey("");
//					AES.decrypt(ampamtCashAccountBean.getAccountId());
					cr.add(Restrictions.eq("accountId",ampamtCashAccountBean.getAccountId()));
				}

				ampamtCashAccountBeanList = cr.list();
			}

			logger.debug("ampamtCashAccountBeanList size=" + ampamtCashAccountBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("***************end getCashAccountDetail******************");

		return ampamtCashAccountBeanList;
	}

	@Override
	public SuccessModel updateTransaction(AmpamtTransactionBean ampamtTransactionBean) {
		logger.debug("***************start updateTransaction******************");
		AmpamtTransactionBean ampamtTransactionBeanOld = new AmpamtTransactionBean();
		List<AmpamtTransactionBean> ampamtTransactionBeanList = getTransactionDetailById(ampamtTransactionBean);

		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (ampamtTransactionBeanList.size() > 0) {
				ampamtTransactionBeanOld = ampamtTransactionBeanList.get(0);

				session.getTransaction().begin();
				if (ampamtTransactionBean.getAccountId() != null) {
					ampamtTransactionBeanOld.setAccountId(ampamtTransactionBeanOld.getAccountId());
				}

				if (ampamtTransactionBean.getAmount() > 0) {
					ampamtTransactionBeanOld.setAmount(ampamtTransactionBean.getAmount());
				}
				if (ampamtTransactionBean.getCashAccountId() != null) {
					ampamtTransactionBeanOld.setCashAccountId(ampamtTransactionBean.getCashAccountId());
				}
				if (ampamtTransactionBean.getCreateDate() != null) {
					ampamtTransactionBeanOld.setCreateDate(ampamtTransactionBean.getCreateDate());
				}
				if (ampamtTransactionBean.getPaidFlag() != null) {
					ampamtTransactionBeanOld.setPaidFlag(ampamtTransactionBean.getPaidFlag());
				}
				if (ampamtTransactionBean.getRemarks() != null) {
					ampamtTransactionBeanOld.setRemarks(ampamtTransactionBean.getRemarks());
				}
				if (ampamtTransactionBean.getStatus() != null) {
					ampamtTransactionBeanOld.setStatus(ampamtTransactionBean.getStatus());
				}

				ampamtTransactionBeanOld.setUpdateDate(new Date());

				if (ampamtTransactionBean.getTransactionId() != null) {
					ampamtTransactionBeanOld.setTransactionId(ampamtTransactionBean.getTransactionId());
				}
				
				if (ampamtTransactionBean.getPaymentMethod() != null) {
					ampamtTransactionBeanOld.setPaymentMethod(ampamtTransactionBean.getPaymentMethod());
				}
				if (ampamtTransactionBean.getExtra() != null) {
					ampamtTransactionBeanOld.setExtra(ampamtTransactionBean.getExtra());
				}
				
				if (ampamtTransactionBean.getPaymentId() != null) {
					ampamtTransactionBeanOld.setPaymentId(ampamtTransactionBean.getPaymentId());
				}
				
				if (ampamtTransactionBean.getSignature() != null) {
					ampamtTransactionBeanOld.setSignature(ampamtTransactionBean.getSignature());
				}
				
				
				session.update(ampamtTransactionBeanOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(ampamtTransactionBeanOld.getTransactionId());
				successModel.setAmount(ampamtTransactionBeanOld.getAmount());
				successModel.setCashAccountId(ampamtTransactionBeanOld.getCashAccountId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateTransaction save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateTransaction***************************");
		return successModel;

	}

	@Override
	public SuccessModel updateCashAccount(AmpamtCashAccountBean ampamtCashAccountBean) {
		logger.debug("***************start updateCashAccount******************");
		AmpamtCashAccountBean ampamtCashAccountBeanOld = new AmpamtCashAccountBean();
		List<AmpamtCashAccountBean> ampamtCashAccountBeanList = getCashAccountDetailById(ampamtCashAccountBean);

		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		String status = null;
		try {

			if (ampamtCashAccountBeanList.size() > 0) {
				ampamtCashAccountBeanOld = ampamtCashAccountBeanList.get(0);

				session.getTransaction().begin();
//				if (ampamtCashAccountBean.getAccountId() != null) {
//					ampamtCashAccountBeanOld.setAccountId(ampamtCashAccountBean.getAccountId());
//				}

				if (ampamtCashAccountBean.getAvailableAmount() >= 0) {
					ampamtCashAccountBeanOld.setAvailableAmount(ampamtCashAccountBeanOld.getAvailableAmount()+ampamtCashAccountBean.getAvailableAmount());
				}
				
				if (ampamtCashAccountBean.getCreateDate() != null) {
					ampamtCashAccountBeanOld.setCreateDate(ampamtCashAccountBean.getCreateDate());
				}

				ampamtCashAccountBeanOld.setUpdateDate(new Date());

				if (ampamtCashAccountBean.getUpdatedBy() != null) {
					ampamtCashAccountBeanOld.setUpdatedBy(ampamtCashAccountBean.getUpdatedBy());
				}

				session.update(ampamtCashAccountBeanOld);
				session.getTransaction().commit();
				status = ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(ampamtCashAccountBeanOld.getCashAccountId());
			} else {
				status = ApplicationConstant.STATUS_FAILED;
			}

		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			status = ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("status updateCashAccount save=" + status);
		successModel.setStatus(status);
		logger.debug("************************end updateCashAccount***************************");
		return successModel;

	}

}
