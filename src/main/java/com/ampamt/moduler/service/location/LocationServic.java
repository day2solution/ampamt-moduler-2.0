package com.ampamt.moduler.service.location;

import java.util.List;

import com.ampamt.moduler.bean.location.TblCityBean;
import com.ampamt.moduler.bean.location.TblDistrictBean;
import com.ampamt.moduler.bean.location.TblStateBean;
import com.ampamt.moduler.model.SuccessModel;

public interface LocationServic {

	SuccessModel addDistrict(TblDistrictBean tblDistrictBean);

	SuccessModel addCity(TblCityBean tblCityBean);

	SuccessModel addState(TblStateBean tblStateBean);

	List<TblDistrictBean> getDistrictList(TblDistrictBean tblDistrictBean);

	List<TblStateBean> getStateList(TblStateBean tblStateBean);

	List<TblCityBean> getCityList(TblCityBean tblCityBean);

}
