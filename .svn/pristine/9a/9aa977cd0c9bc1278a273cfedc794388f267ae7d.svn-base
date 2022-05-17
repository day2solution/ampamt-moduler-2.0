package com.ampamt.moduler.controller.advertisement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ampamt.moduler.bean.advertisement.AdvertisementsBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.controller.upload.UploadDocumentsController;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.service.advertisement.AdvertisementService;
import com.ampamt.moduler.util.DumpBean;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/advertisement")
public class AdvertisementController {

private final Logger logger = LoggerFactory.getLogger(AdvertisementController.class);
	
	@Autowired
	AdvertisementService advertisementService;
	
	@Autowired
	private Environment environment;
	
	
	@RequestMapping(value = "/create-advertisement", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createAdvertisement(@RequestBody AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start createAdvertisement***************************");
		DumpBean dumpBean=new DumpBean();
		logger.debug(dumpBean.dumpBean(advertisementsBean));
		String imageBase64=advertisementsBean.getExtras();
		
		String advId = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
		advertisementsBean.setAdvId("ADVT"+advId);
		if(advertisementsBean.getImageName()==null) {
			advertisementsBean.setImageName(advertisementsBean.getAdvId());
		}
		
		SuccessModel scuccessModel = advertisementService.createAdvertisement(advertisementsBean);
		if(scuccessModel!=null && scuccessModel.getStatus().equalsIgnoreCase(ApplicationConstant.STATUS_SUCCESS)) {
			
			String folderName=ApplicationConstant.ADVERTISEMENTS;
			
			String path1=environment.getProperty("home.file.path") + "//"+folderName+"//"+advertisementsBean.getAccountId();
			String path2 = environment.getProperty("physical.file.image.storage.path") + "//"+folderName+"//"+advertisementsBean.getAccountId();
			
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			
			scuccessModel=uploadDocumentsController.uploadImage(true, true, advertisementsBean.getAccountId(), advertisementsBean.getAdvId(), imageBase64, folderName,path1,path2);
			
		}

		logger.debug("*******************end createAdvertisement***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-advertisement", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateAdvertisement(@RequestBody AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start updateAdvertisement***************************");
	
		SuccessModel scuccessModel = advertisementService.updateAdvertisement(advertisementsBean);

		logger.debug("*******************end updateAdvertisement***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete-advertisement", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> deleteAdvertisement(@RequestBody AdvertisementsBean advertisementsBean) {
		logger.debug("*******************start deleteAdvertisement***************************");
	
		SuccessModel scuccessModel = advertisementService.deleteAdvertisement(advertisementsBean);

		logger.debug("*******************end deleteAdvertisement***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/get-advertisement-list", method = RequestMethod.POST)
	public ResponseEntity<List<AdvertisementsBean>> getAdvertisementList(@RequestBody AdvertisementsBean advertisementsBean) throws IOException {
		logger.debug("*******************start getAdvertisementList***************************");
	
		List<AdvertisementsBean> advertisementsBeanList = advertisementService.getAdvertisementList(advertisementsBean);
		List<AdvertisementsBean> advertisementsBeanList2=new ArrayList<AdvertisementsBean>();
		if(advertisementsBeanList.size()>0) {
			AdvertisementsBean advertisementsBean2=new AdvertisementsBean();
			
			String imagebase64=null,imagePath=environment.getProperty("home.file.path")+ApplicationConstant.ADVERTISEMENTS;

			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			for(int i=0;i<advertisementsBeanList.size();i++) {
				advertisementsBean2=new AdvertisementsBean();
				advertisementsBean2=advertisementsBeanList.get(i);
				
				imagebase64=uploadDocumentsController.getImages(imagePath+"//"+advertisementsBeanList.get(i).getAccountId()+"//"+advertisementsBeanList.get(i).getAdvId()+".jpeg");
				advertisementsBean2.setExtras(imagebase64);
				
				advertisementsBeanList2.add(advertisementsBean2);
			}
			
		}

		logger.debug("*******************end getAdvertisementList***************************");
		return new ResponseEntity<>(advertisementsBeanList2, HttpStatus.OK);
	}
}
