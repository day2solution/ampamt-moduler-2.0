package com.ampamt.moduler.dao.location;

import java.util.List;

import com.ampamt.moduler.bean.location.TblCityBean;
import com.ampamt.moduler.bean.location.TblDistrictBean;
import com.ampamt.moduler.bean.location.TblStateBean;
import com.ampamt.moduler.model.SuccessModel;

public interface LocationDao {

	SuccessModel addDistrict(TblDistrictBean tblDistrictBean);

	SuccessModel addCity(TblCityBean tblCityBean);

	SuccessModel addState(TblStateBean tblStateBean);

	List<TblStateBean> getStateList(TblStateBean tblStateBean);

	List<TblDistrictBean> getDistrictList(TblDistrictBean tblDistrictBean);

	List<TblCityBean> getCityList(TblCityBean tblCityBean);

}
