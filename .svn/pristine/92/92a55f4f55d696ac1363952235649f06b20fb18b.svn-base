package com.ampamt.moduler.dao.event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ampamt.moduler.bean.event.AmpamtEventsBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.dao.AccountDaoImpl;
import com.ampamt.moduler.model.SuccessModel;

@Repository
public class EventDaoImpl implements EventDao{

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	
	@Autowired
    private SessionFactory sessionFactory;
	
    protected Session currentSession()
	{

    	Session session = null;
		try
		{
			session = sessionFactory.getCurrentSession();

		}catch(Exception ex)
		{
			logger.debug(" ++++ getSession catch ++++ ", ex);
			session = sessionFactory.openSession();
		}
		logger.debug("current session==>"+session);
    	logger.debug("current sessionFactory==>"+sessionFactory.hashCode());
		return session;
	}
	@Override
	public SuccessModel createEvent(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("***************start createEvent******************");

		String eventId=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		Session session=currentSession();
		eventId="EV"+eventId;
		logger.debug("eventId="+eventId);
		SuccessModel successModel= new SuccessModel();
		String status=null;
		try
		{
//			AES.setKey("");
//			AES.decrypt(ampamtEventsBean.getAccountId());
			ampamtEventsBean.setAccountId(ampamtEventsBean.getAccountId());
			session.getTransaction().begin();
			ampamtEventsBean.setEventId(eventId);
			
			ampamtEventsBean.setCreateDate(new Date());
			status=(String) session.save(ampamtEventsBean);
			session.getTransaction().commit();
			status=ApplicationConstant.STATUS_SUCCESS;
			successModel.setId(ampamtEventsBean.getEventId());
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			status=ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("status createEvent save="+status);
		successModel.setStatus(status);
		logger.debug("************************end createEvent***************************");
		return successModel;
	}
	@Override
	public List<AmpamtEventsBean> getEventList(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("***************start getEventList******************");
		Session session=currentSession();
		List<AmpamtEventsBean> ampamtEventsBeanList=new ArrayList<AmpamtEventsBean>();
		
		try
		{
			session.getTransaction().begin();
	
			Criteria cr = session.createCriteria(AmpamtEventsBean.class);
			if(ampamtEventsBean.getAccountId()!=null)
			{
//				AES.setKey("");
//				AES.decrypt(ampamtEventsBean.getAccountId());
				cr.add(Restrictions.eq("accountId", ampamtEventsBean.getAccountId()));
			}
			
			if(ampamtEventsBean.getEventId()!=null)
			{
				cr.add(Restrictions.eq("eventId", ampamtEventsBean.getEventId()));
			}
			if(ampamtEventsBean.getEventCity()!=null)
			{
				cr.add(Restrictions.eq("eventCity", ampamtEventsBean.getEventCity()));
			}
			if(ampamtEventsBean.getEventState()!=null)
			{
				cr.add(Restrictions.eq("eventState", ampamtEventsBean.getEventState()));
			}
			if(ampamtEventsBean.getEventDate()!=null)
			{
				cr.add(Restrictions.eq("eventDate", ampamtEventsBean.getEventDate()));
			}
			if(ampamtEventsBean.getEventTitle()!=null)
			{
				cr.add(Restrictions.eq("eventTitle", ampamtEventsBean.getEventTitle()));
			}
			if(ampamtEventsBean.getActiveFlag()!=null)
			{
				cr.add(Restrictions.eq("activeFlag", ampamtEventsBean.getActiveFlag()));
			}
			if(ampamtEventsBean.getEventImg()!=null)
			{
				cr.add(Restrictions.eq("eventImg", ampamtEventsBean.getEventImg()));
			}
			cr.addOrder(Order.asc("eventDate"));
			ampamtEventsBeanList = cr.list();
			logger.debug("ampamtEventsBeanList size="+ampamtEventsBeanList.size());
			
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("***************end getEventList******************");
		
		return ampamtEventsBeanList;
	}
	
	@Override
	public List<AmpamtEventsBean> getEventById(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("***************start getEventById******************");
		Session session=currentSession();
		List<AmpamtEventsBean> ampamtEventsBeanList=new ArrayList<AmpamtEventsBean>();
		
		try
		{
			session.getTransaction().begin();
	
			Criteria cr = session.createCriteria(AmpamtEventsBean.class);
			
			
			if(ampamtEventsBean.getEventId()!=null)
			{
				cr.add(Restrictions.eq("eventId", ampamtEventsBean.getEventId()));
				
				if(ampamtEventsBean.getAccountId()!=null)
				{
//					AES.setKey("");
//					AES.decrypt(ampamtEventsBean.getAccountId());
					cr.add(Restrictions.eq("accountId", ampamtEventsBean.getAccountId()));
				}
				
				if(ampamtEventsBean.getEventCity()!=null)
				{
					cr.add(Restrictions.eq("eventCity", ampamtEventsBean.getEventCity()));
				}
				if(ampamtEventsBean.getEventState()!=null)
				{
					cr.add(Restrictions.eq("eventState", ampamtEventsBean.getEventState()));
				}
				if(ampamtEventsBean.getEventDate()!=null)
				{
					cr.add(Restrictions.eq("eventDate", ampamtEventsBean.getEventDate()));
				}
				if(ampamtEventsBean.getEventTitle()!=null)
				{
					cr.add(Restrictions.eq("eventTitle", ampamtEventsBean.getEventTitle()));
				}
				if(ampamtEventsBean.getActiveFlag()!=null)
				{
					cr.add(Restrictions.eq("activeFlag", ampamtEventsBean.getActiveFlag()));
				}
				if(ampamtEventsBean.getEventImg()!=null)
				{
					cr.add(Restrictions.eq("eventImg", ampamtEventsBean.getEventImg()));
				}
				ampamtEventsBeanList = cr.list();
			}
			
			
			logger.debug("ampamtEventsBeanList size="+ampamtEventsBeanList.size());
			
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("***************end getEventById******************");
		
		return ampamtEventsBeanList;
	}
	
	@Override
	public SuccessModel deleteEvent(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("************************start deleteEvent***************************");

		SuccessModel successModel=new SuccessModel();
		String status=null;
		Session session=currentSession();
		try
		{
		
		
			session.getTransaction().begin();
			session.remove(ampamtEventsBean);
			session.getTransaction().commit();
			status=ApplicationConstant.STATUS_SUCCESS;
		}
		catch(Exception e)
		{
			logger.debug("exception during delete>>"+e);
			session.getTransaction().rollback();
			status=ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("deleteEvent delete="+status);
		successModel.setStatus(status);
		logger.debug("************************end deleteEvent***************************");
		return successModel;
	}
	@Override
	public SuccessModel updateEvent(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("***************start updateEvent******************");
		AmpamtEventsBean ampamtEventsBeanOld=new AmpamtEventsBean();
		List<AmpamtEventsBean> ampamtEventsBeanList=getEventById(ampamtEventsBean);

		Session session=currentSession();
		SuccessModel successModel= new SuccessModel();
		String status=null;
		try
		{
		
			if(ampamtEventsBeanList.size()>0) 
			{
				ampamtEventsBeanOld=ampamtEventsBeanList.get(0);
				
				session.getTransaction().begin();
				if(ampamtEventsBean.getEventTitle()!=null) {
					ampamtEventsBeanOld.setEventTitle(ampamtEventsBean.getEventTitle());
				}
				
				if(ampamtEventsBean.getEventCity()!=null) {
					ampamtEventsBeanOld.setEventCity(ampamtEventsBean.getEventCity());
				}
				if(ampamtEventsBean.getEventState()!=null) {
					ampamtEventsBeanOld.setEventState(ampamtEventsBean.getEventState());
				}
				if(ampamtEventsBean.getEventDescription()!=null) {
					ampamtEventsBeanOld.setEventDescription(ampamtEventsBean.getEventDescription());
				}
				if(ampamtEventsBean.getEventImg()!=null) {
					ampamtEventsBeanOld.setEventImg(ampamtEventsBean.getEventImg());
				}
				if(ampamtEventsBean.getActiveFlag()!=null) {
					ampamtEventsBeanOld.setActiveFlag(ampamtEventsBean.getActiveFlag());
				}
				if(ampamtEventsBean.getEventDate()!=null) {
					ampamtEventsBeanOld.setEventDate(ampamtEventsBean.getEventDate());
				}

				session.update(ampamtEventsBeanOld);
				session.getTransaction().commit();
				status=ApplicationConstant.STATUS_SUCCESS;
				successModel.setId(ampamtEventsBeanOld.getEventId());
			}
			else 
			{
				status=ApplicationConstant.STATUS_FAILED;
			}
			
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			status=ApplicationConstant.STATUS_FAILED;
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("status updateEvent save="+status);
		successModel.setStatus(status);
		logger.debug("************************end updateEvent***************************");
		return successModel;

		
	}
	@Override
	public List<AmpamtEventsBean> getNearestEventList(AmpamtEventsBean ampamtEventsBean) {
		logger.debug("***************start getNearestEventList******************");
		Session session=currentSession();
		List<AmpamtEventsBean> ampamtEventsBeanList=new ArrayList<AmpamtEventsBean>();
		StringBuilder sql=new StringBuilder();
		try
		{
			session.getTransaction().begin();
	
			
			sql.append(" SELECT * FROM ampamt_events where trunc(event_date) >=trunc(sysdate) and event_date <=trunc(ADD_MONTHS(sysdate, 6)) order by event_date asc");
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.addEntity(AmpamtEventsBean.class);
			ampamtEventsBeanList = query.list();
			logger.debug("ampamtEventsBeanList size="+ampamtEventsBeanList.size());
			
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("***************end getNearestEventList******************");
		
		return ampamtEventsBeanList;
	}

}
