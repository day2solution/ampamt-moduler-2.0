package com.ampamt.moduler.service.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.advertisement.AdvertisementsBean;
import com.ampamt.moduler.dao.advertisement.AdvertisementDao;
import com.ampamt.moduler.model.SuccessModel;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	AdvertisementDao advertisementDao;

	@Override
	public SuccessModel createAdvertisement(AdvertisementsBean advertisementsBean) {
		advertisementsBean.setExtras(null);
		SuccessModel scuccessModel = advertisementDao.createAdvertisement(advertisementsBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel updateAdvertisement(AdvertisementsBean advertisementsBean) {
		SuccessModel scuccessModel = advertisementDao.updateAdvertisement(advertisementsBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel deleteAdvertisement(AdvertisementsBean advertisementsBean) {
		SuccessModel scuccessModel = advertisementDao.deleteAdvertisement(advertisementsBean);
		return scuccessModel;
	}

	@Override
	public List<AdvertisementsBean> getAdvertisementList(AdvertisementsBean advertisementsBean) {
		List<AdvertisementsBean> advertisementsBeanList = advertisementDao.getAdvertisementList(advertisementsBean);
		return advertisementsBeanList;
	}
}
