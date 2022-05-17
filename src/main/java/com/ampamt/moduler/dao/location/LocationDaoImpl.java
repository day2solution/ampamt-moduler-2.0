package com.ampamt.moduler.dao.location;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ampamt.moduler.bean.location.TblCityBean;
import com.ampamt.moduler.bean.location.TblDistrictBean;
import com.ampamt.moduler.bean.location.TblStateBean;
import com.ampamt.moduler.model.SuccessModel;

@Repository
public class LocationDaoImpl implements LocationDao{

	private static final Logger logger = LoggerFactory.getLogger(LocationDaoImpl.class);
	
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
	public SuccessModel addDistrict(TblDistrictBean tblDistrictBean) {
		logger.debug("***************start addDistrict******************");

		String districtCode=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		Session session=currentSession();
		districtCode="DT"+districtCode;
		logger.debug("districtCode="+districtCode);
		SuccessModel successModel= new SuccessModel();
		String status=null;
		try
		{
		
		
			session.getTransaction().begin();
//			tblDistrictBean.setDistrictCode(districtCode);
			
			status=(String) session.save(tblDistrictBean);
			session.getTransaction().commit();
			status="SUCCESS";
			successModel.setId(districtCode);
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			status="FAILED";
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("status addDistrict save="+status);
		successModel.setStatus(status);
		logger.debug("************************end addDistrict***************************");
		return successModel;

		
	}

	@Override
	public SuccessModel addCity(TblCityBean tblCityBean) {
		logger.debug("***************start addCity******************");
		Session session=currentSession();
		SuccessModel successModel= new SuccessModel();
		String status=null;
		try
		{
			session.getTransaction().begin();
			status=(String) session.save(tblCityBean);
			session.getTransaction().commit();
			status="SUCCESS";
			successModel.setId(tblCityBean.getCityCode());
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			status="FAILED";
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("status addCity save="+status);
		successModel.setStatus(status);
		logger.debug("************************end addCity***************************");
		return successModel;

		
	}

	@Override
	public SuccessModel addState(TblStateBean tblStateBean) {
		logger.debug("***************start addState******************");
		Session session=currentSession();
		SuccessModel successModel= new SuccessModel();
		String status=null;
		try
		{
			session.getTransaction().begin();
			status=(String) session.save(tblStateBean);
			session.getTransaction().commit();
			status="SUCCESS";
			successModel.setId(tblStateBean.getStateCode());
		}
		catch(Exception e)
		{
			logger.debug("exception during saving>>"+e);
			session.getTransaction().rollback();
			status="FAILED";
			e.printStackTrace();
			
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		logger.debug("status addState save="+status);
		successModel.setStatus(status);
		logger.debug("************************end addState***************************");
		return successModel;

		
	}

	@Override
	public List<TblStateBean> getStateList(TblStateBean tblStateBean) {
		logger.debug("***************start getCandidateAccounts******************");
		Session session=currentSession();
		
		List<TblStateBean> tblStateBeanList=new ArrayList<TblStateBean>();
		
		try
		{
			session.getTransaction().begin();
	
			Criteria cr = session.createCriteria(TblStateBean.class);
			if(tblStateBean.getStateCode() !=null)
			{
				cr.add(Restrictions.eq("stateCode", tblStateBean.getStateCode().toUpperCase()));
			}
			
			if(tblStateBean.getStateName() !=null)
			{
				cr.add(Restrictions.eq("stateName", tblStateBean.getStateName().toUpperCase()));
			}
			
			if(tblStateBean.getStateCountry()!=null)
			{
				cr.add(Restrictions.eq("stateCountry", tblStateBean.getStateCountry().toUpperCase()));
			}
		
			tblStateBeanList = cr.list();
			logger.debug("tblStateBeanList size="+tblStateBeanList.size());
			
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
		logger.debug("***************end getCandidateAccounts******************");
		
		return tblStateBeanList;
	}

	@Override
	public List<TblDistrictBean> getDistrictList(TblDistrictBean tblDistrictBean) {
		logger.debug("***************start getDistrictList******************");
		Session session=currentSession();
		
		List<TblDistrictBean> tblDistrictBeanList=new ArrayList<TblDistrictBean>();
		
		try
		{
			session.getTransaction().begin();
	
			Criteria cr = session.createCriteria(TblDistrictBean.class);
//			if(tblDistrictBean.getStateCode() !=null)
//			{
//				cr.add(Restrictions.eq("stateCode", tblDistrictBean.getStateCode().toUpperCase()));
//			}
//			
//			if(tblDistrictBean.getCityCode() !=null)
//			{
//				cr.add(Restrictions.eq("cityCode", tblDistrictBean.getCityCode().toUpperCase()));
//			}
//			
//			if(tblDistrictBean.getDistrictCode() !=null)
//			{
//				cr.add(Restrictions.eq("districtCode", tblDistrictBean.getDistrictCode().toUpperCase()));
//			}
//		
			tblDistrictBeanList = cr.list();
			logger.debug("tblDistrictBeanList size="+tblDistrictBeanList.size());
			
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
		logger.debug("***************end getDistrictList******************");
		
		return tblDistrictBeanList;
	}

	@Override
	public List<TblCityBean> getCityList(TblCityBean tblCityBean) {
		logger.debug("***************start getCityList******************");
		Session session=currentSession();
		
		List<TblCityBean> tblCityBeanList=new ArrayList<TblCityBean>();
		
		try
		{
			session.getTransaction().begin();
	
			Criteria cr = session.createCriteria(TblCityBean.class);
			if(tblCityBean.getStateCode() !=null)
			{
				cr.add(Restrictions.eq("stateCode", tblCityBean.getStateCode().toUpperCase()));
			}
			
			if(tblCityBean.getCityCode() !=null)
			{
				cr.add(Restrictions.eq("cityCode", tblCityBean.getCityCode().toUpperCase()));
			}
			
			
		
			tblCityBeanList = cr.list();
			logger.debug("tblCityBeanList size="+tblCityBeanList.size());
			
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
		logger.debug("***************end getCityList******************");
		
		return tblCityBeanList;
	}
}
