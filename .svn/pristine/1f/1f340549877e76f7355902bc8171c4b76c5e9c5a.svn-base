package com.ampamt.moduler.service.location;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.location.TblCityBean;
import com.ampamt.moduler.bean.location.TblDistrictBean;
import com.ampamt.moduler.bean.location.TblStateBean;
import com.ampamt.moduler.dao.location.LocationDao;
import com.ampamt.moduler.model.SuccessModel;

@Service
public class LocationServicImpl implements LocationServic{

	private final Logger logger = LoggerFactory.getLogger(LocationServicImpl.class);

	@Autowired
	LocationDao locationDao;
	
	@Override
	public SuccessModel addDistrict(TblDistrictBean tblDistrictBean) {
		logger.debug("*******************start addDistrict***************************");
		
		SuccessModel successModel = locationDao.addDistrict(tblDistrictBean);

		logger.debug("*******************end addDistrict***************************");
		return successModel;
	}

	@Override
	public SuccessModel addCity(TblCityBean tblCityBean) {
		logger.debug("*******************start addCity***************************");
		
		SuccessModel successModel = locationDao.addCity(tblCityBean);

		logger.debug("*******************end addCity***************************");
		return successModel;
	}

	@Override
	public SuccessModel addState(TblStateBean tblStateBean) {
		logger.debug("*******************start addState***************************");
		
		SuccessModel successModel = locationDao.addState(tblStateBean);

		logger.debug("*******************end addState***************************");
		return successModel;
	}

	@Override
	public List<TblDistrictBean> getDistrictList(TblDistrictBean tblDistrictBean) {
		logger.debug("*******************start getDistrictList***************************");
		List<TblDistrictBean> tblDistrictBeanList = locationDao.getDistrictList(tblDistrictBean);
		logger.debug("*******************end getDistrictList***************************");
		return tblDistrictBeanList;
	}

	@Override
	public List<TblStateBean> getStateList(TblStateBean tblStateBean) {
		logger.debug("*******************start getStateList***************************");
		List<TblStateBean> tblStateBeanList = locationDao.getStateList(tblStateBean);
		logger.debug("*******************end getStateList***************************");
		return tblStateBeanList;
	}

	@Override
	public List<TblCityBean> getCityList(TblCityBean tblCityBean) {
		logger.debug("*******************start getCityList***************************");
		List<TblCityBean> tblCityBeanList = locationDao.getCityList(tblCityBean);
		logger.debug("*******************end getCityList***************************");
		return tblCityBeanList;
	}

}
