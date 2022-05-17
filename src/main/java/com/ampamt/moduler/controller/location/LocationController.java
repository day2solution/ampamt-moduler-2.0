package com.ampamt.moduler.controller.location;

import java.util.ArrayList;
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

import com.ampamt.moduler.bean.location.TblCityBean;
import com.ampamt.moduler.bean.location.TblStateBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.model.StateCityModel;
import com.ampamt.moduler.service.location.LocationServic;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/location")
public class LocationController {
	private final Logger logger = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	LocationServic locationServic;
	
//	@RequestMapping(value = "/add-state", method = RequestMethod.POST)
//	public ResponseEntity<SuccessModel> addState(@RequestBody TblStateBean tblStateBean) {
//		logger.debug("*******************start addState***************************");
//	
//		SuccessModel successModel = locationServic.addState(tblStateBean);
//
//		logger.debug("*******************end addState***************************");
//
//		return new ResponseEntity<>(successModel, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/add-city", method = RequestMethod.POST)
//	public ResponseEntity<SuccessModel> addCity(@RequestBody TblCityBean tblCityBean) {
//		logger.debug("*******************start addCity***************************");
//	
//		SuccessModel successModel = locationServic.addCity(tblCityBean);
//
//		logger.debug("*******************end addCity***************************");
//
//		return new ResponseEntity<>(successModel, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/add-district", method = RequestMethod.POST)
//	public ResponseEntity<SuccessModel> addDistrict(@RequestBody TblDistrictBean tblDistrictBean) {
//		logger.debug("*******************start addDistrict***************************");
//	
//		SuccessModel successModel = locationServic.addDistrict(tblDistrictBean);
//
//		logger.debug("*******************end addDistrict***************************");
//
//		return new ResponseEntity<>(successModel, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/get-district-list", method = RequestMethod.POST)
//	public ResponseEntity<List<TblDistrictBean>> getDistrictList(@RequestBody TblDistrictBean tblDistrictBean) {
//		logger.debug("*******************start getDistrictList***************************");
//	
//		List<TblDistrictBean> tblDistrictBeanList = locationServic.getDistrictList(tblDistrictBean);
//
//		logger.debug("*******************end getDistrictList***************************");
//
//		return new ResponseEntity<>(tblDistrictBeanList, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/get-state-list", method = RequestMethod.POST)
	public ResponseEntity<List<TblStateBean>> getStateList(@RequestBody TblStateBean tblStateBean) {
		logger.debug("*******************start getStateList***************************");
	
		List<TblStateBean> tblStateBeanList = locationServic.getStateList(tblStateBean);

		logger.debug("*******************end getStateList***************************");

		return new ResponseEntity<>(tblStateBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-city-list", method = RequestMethod.POST)
	public ResponseEntity<List<TblCityBean>> getCityList(@RequestBody TblCityBean tblCityBean) {
		logger.debug("*******************start getStateList***************************");
	
		List<TblCityBean> tblCityBeanList = locationServic.getCityList(tblCityBean);

		logger.debug("*******************end getStateList***************************");

		return new ResponseEntity<>(tblCityBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-state-city-list", method = RequestMethod.POST)
	public ResponseEntity<List<StateCityModel>> getStateWithCityList(@RequestBody TblStateBean tblStateBean) {
		logger.debug("*******************start getStateList***************************");
	
		List <StateCityModel> stateCityModelList=new ArrayList<StateCityModel>();
		List<TblStateBean> tblStateBeanList = locationServic.getStateList(tblStateBean);
		if(tblStateBeanList!=null && tblStateBeanList.size()>0) {
			TblCityBean tblCityBean=new TblCityBean();
			StateCityModel stateCityModel=new StateCityModel();
			List<TblCityBean> tblCityBeanList=new ArrayList<TblCityBean>();
			for(int i=0;i<tblStateBeanList.size();i++) {
				stateCityModel=new StateCityModel();
				tblCityBean=new TblCityBean();
				
				tblCityBean.setStateCode(tblStateBeanList.get(i).getStateCode());
				tblCityBeanList = locationServic.getCityList(tblCityBean);
				stateCityModel.setStateCode(tblStateBeanList.get(i).getStateCode());
				stateCityModel.setStateName(tblStateBeanList.get(i).getStateName());
				stateCityModel.setTblCityBean(tblCityBeanList);
				stateCityModelList.add(stateCityModel);
			}
		}

		logger.debug("*******************end getStateList***************************");

		return new ResponseEntity<>(stateCityModelList, HttpStatus.OK);
	}
}
