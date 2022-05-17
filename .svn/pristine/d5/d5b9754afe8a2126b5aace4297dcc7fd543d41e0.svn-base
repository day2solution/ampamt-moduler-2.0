package com.ampamt.moduler.dao.advertisement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ampamt.moduler.bean.advertisement.AdvertisementsBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.SuccessModel;

@Repository
public class AdvertisementDaoImpl implements AdvertisementDao{


	private static final Logger logger = LoggerFactory.getLogger(AdvertisementDaoImpl.class);

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
	public SuccessModel createAdvertisement(AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start createAdvertisement***************************");
		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
//		String advId = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		try {
			session.getTransaction().begin();
			advertisementsBean.setCreateDate(new Date());
//			advertisementsBean.setAdvId(advId);
			session.save(advertisementsBean);
			session.getTransaction().commit();
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			successModel.setId(advertisementsBean.getAdvId());
		} catch (Exception e) {
			logger.debug("exception during saving>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("*******************end createAdvertisement***************************");
		return successModel;
	}

	@Override
	public SuccessModel updateAdvertisement(AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start updateAdvertisement***************************");
		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		AdvertisementsBean advertisementsBeanOldBean = new AdvertisementsBean();
		List<AdvertisementsBean> advertisementsBeanList = getAdvertisementById(advertisementsBean);
		try
		{
		
			if(advertisementsBeanList.size()>0) 
			{
				advertisementsBeanOldBean=advertisementsBeanList.get(0);
				
				session.getTransaction().begin();
				if(advertisementsBean.getDisplayFlag()!=null) {
					advertisementsBeanOldBean.setDisplayFlag(advertisementsBean.getDisplayFlag());
				}

				session.update(advertisementsBeanOldBean);
				session.getTransaction().commit();
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
				successModel.setId(advertisementsBeanOldBean.getAdvId());
			}
			else 
			{
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			}
			
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("*******************end updateAdvertisement***************************");
		return successModel;
	}

	@Override
	public SuccessModel deleteAdvertisement(AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start deleteAdvertisement***************************");
		Session session = currentSession();
		SuccessModel successModel = new SuccessModel();
		try {

			session.getTransaction().begin();
			session.remove(advertisementsBean);
			session.getTransaction().commit();
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (Exception e) {
			logger.debug("exception during delete>>" + e);
			session.getTransaction().rollback();
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("*******************end deleteAdvertisement***************************");
		return successModel;
	}

	@Override
	public List<AdvertisementsBean> getAdvertisementList(AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start getAdvertisementList***************************");
		Session session = currentSession();
		List<AdvertisementsBean> advertisementsBeanList = new ArrayList<AdvertisementsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AdvertisementsBean.class);
			if (advertisementsBean.getAdvId() != null) {
				cr.add(Restrictions.eq("advId", advertisementsBean.getAdvId()));

			}

			if (advertisementsBean.getAccountId() != null) {
				cr.add(Restrictions.eq("accountId", advertisementsBean.getAccountId()));

			}

			if (advertisementsBean.getDisplayFlag() != null) {
				cr.add(Restrictions.eq("displayFlag", advertisementsBean.getDisplayFlag()));

			}

			if (advertisementsBean.getImageName() != null) {
				cr.add(Restrictions.eq("imageName", advertisementsBean.getImageName()));

			}

			advertisementsBeanList = cr.list();

			logger.debug("advertisementsBeanList size=" + advertisementsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("*******************end advertisementsBeanList***************************");
		return advertisementsBeanList;
	}

	@Override
	public List<AdvertisementsBean> getAdvertisementById(AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start getAdvertisementById***************************");
		Session session = currentSession();
		List<AdvertisementsBean> advertisementsBeanList = new ArrayList<AdvertisementsBean>();

		try {
			session.getTransaction().begin();

			Criteria cr = session.createCriteria(AdvertisementsBean.class);
			if (advertisementsBean.getAdvId() != null) {
				cr.add(Restrictions.eq("advId", advertisementsBean.getAdvId()));

				if (advertisementsBean.getAccountId() != null) {
					cr.add(Restrictions.eq("accountId", advertisementsBean.getAccountId()));

				}

				advertisementsBeanList = cr.list();
			}

			

			logger.debug("advertisementsBeanList size=" + advertisementsBeanList.size());

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		logger.debug("*******************end getAdvertisementById***************************");
		return advertisementsBeanList;
	}
}
