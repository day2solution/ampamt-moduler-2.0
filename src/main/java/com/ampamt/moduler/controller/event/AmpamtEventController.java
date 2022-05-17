package com.ampamt.moduler.controller.event;

import java.io.IOException;
import java.util.ArrayList;
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

import com.ampamt.moduler.bean.event.AmpamtEventsBean;
import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.controller.upload.UploadDocumentsController;
import com.ampamt.moduler.model.EventUploadModel;
import com.ampamt.moduler.model.SuccessModel;
import com.ampamt.moduler.service.event.EventService;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/event")
public class AmpamtEventController {

private final Logger logger = LoggerFactory.getLogger(AmpamtEventController.class);
	
	@Autowired
	EventService eventService;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/create-event", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> createEvent(@RequestBody AmpamtEventsBean ampamtEventsBean) {
		logger.debug("*******************start createEvent***************************");
	
		SuccessModel successModel = eventService.createEvent(ampamtEventsBean);

		logger.debug("*******************end createEvent***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-event-list", method = RequestMethod.POST)
	public ResponseEntity<List<EventUploadModel>> getEventList(@RequestBody AmpamtEventsBean ampamtEventsBean) throws IOException {
		logger.debug("*******************start getEventList***************************");
	
		List<AmpamtEventsBean> ampamtEventsBeanList = eventService.getEventList(ampamtEventsBean);
		EventUploadModel eventUploadModel=new EventUploadModel();
		List<EventUploadModel> eventUploadModelList=new ArrayList<EventUploadModel>();
		if(ampamtEventsBeanList.size()>0) {
		
			String imagebase64=null,imagePath=environment.getProperty("home.file.path")+"EVENTS_IMAGES";
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			for(int i=0;i<ampamtEventsBeanList.size();i++) {
				imagebase64=null;
				
				eventUploadModel=new EventUploadModel();
				eventUploadModel.setActiveFlag(ampamtEventsBeanList.get(i).getActiveFlag());
				eventUploadModel.setAccountId(ampamtEventsBeanList.get(i).getAccountId());
				eventUploadModel.setEventId(ampamtEventsBeanList.get(i).getEventId());
				eventUploadModel.setEventDate(ampamtEventsBeanList.get(i).getEventDate());
				eventUploadModel.setEventCity(ampamtEventsBeanList.get(i).getEventCity());
				eventUploadModel.setEventState(ampamtEventsBeanList.get(i).getEventState());
				eventUploadModel.setEventDescription(ampamtEventsBeanList.get(i).getEventDescription());
				eventUploadModel.setEventTitle(ampamtEventsBeanList.get(i).getEventTitle());
			
//				imagePath=imagePath+"\\"+ampamtEventsBeanList.get(i).getAccountId()+"\\EVENT_"+ampamtEventsBeanList.get(i).getEventId()+".jpeg";
				imagebase64=uploadDocumentsController.getImages(imagePath+"\\"+ampamtEventsBeanList.get(i).getAccountId()+"\\EVENT_"+ampamtEventsBeanList.get(i).getEventId()+".jpeg");
				
				eventUploadModel.setEventImgBase64(imagebase64);
				eventUploadModelList.add(eventUploadModel);
			}
		}
		logger.debug("*******************end getEventList***************************");
		return new ResponseEntity<>(eventUploadModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-nearest-event-list", method = RequestMethod.POST)
	public ResponseEntity<List<EventUploadModel>> getNearestEventList(@RequestBody AmpamtEventsBean ampamtEventsBean) throws IOException {
		logger.debug("*******************start getNearestEventList***************************");
	
		List<AmpamtEventsBean> ampamtEventsBeanList = eventService.getNearestEventList(ampamtEventsBean);
		EventUploadModel eventUploadModel=new EventUploadModel();
		List<EventUploadModel> eventUploadModelList=new ArrayList<EventUploadModel>();
		if(ampamtEventsBeanList.size()>0) {
		
			String imagebase64=null,imagePath=environment.getProperty("home.file.path")+"EVENTS_IMAGES";
			UploadDocumentsController uploadDocumentsController=new UploadDocumentsController();
			for(int i=0;i<ampamtEventsBeanList.size();i++) {
				imagebase64=null;
				
				eventUploadModel=new EventUploadModel();
				eventUploadModel.setActiveFlag(ampamtEventsBeanList.get(i).getActiveFlag());
				eventUploadModel.setAccountId(ampamtEventsBeanList.get(i).getAccountId());
				eventUploadModel.setEventId(ampamtEventsBeanList.get(i).getEventId());
				eventUploadModel.setEventDate(ampamtEventsBeanList.get(i).getEventDate());
				eventUploadModel.setEventCity(ampamtEventsBeanList.get(i).getEventCity());
				eventUploadModel.setEventState(ampamtEventsBeanList.get(i).getEventState());
				eventUploadModel.setEventDescription(ampamtEventsBeanList.get(i).getEventDescription());
				eventUploadModel.setEventTitle(ampamtEventsBeanList.get(i).getEventTitle());
			
//				imagePath=imagePath+"\\"+ampamtEventsBeanList.get(i).getAccountId()+"\\EVENT_"+ampamtEventsBeanList.get(i).getEventId()+".jpeg";
				imagebase64=uploadDocumentsController.getImages(imagePath+"\\"+ampamtEventsBeanList.get(i).getAccountId()+"\\EVENT_"+ampamtEventsBeanList.get(i).getEventId()+".jpeg");
				
				eventUploadModel.setEventImgBase64(imagebase64);
				eventUploadModelList.add(eventUploadModel);
			}
		}
		logger.debug("*******************end getNearestEventList***************************");
		return new ResponseEntity<>(eventUploadModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-event-byId", method = RequestMethod.POST)
	public ResponseEntity<List<AmpamtEventsBean>> getEventById(@RequestBody AmpamtEventsBean ampamtEventsBean) {
		logger.debug("*******************start getEventList***************************");
	
		List<AmpamtEventsBean> ampamtEventsBeanList = eventService.getEventById(ampamtEventsBean);

		logger.debug("*******************end getEventList***************************");
		return new ResponseEntity<>(ampamtEventsBeanList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-event", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> updateEvent(@RequestBody AmpamtEventsBean ampamtEventsBean) {
		logger.debug("*******************start updateEvent***************************");
	
		SuccessModel scuccessModel = eventService.updateEvent(ampamtEventsBean);

		logger.debug("*******************end updateEvent***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete-event", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> deleteEvent(@RequestBody AmpamtEventsBean ampamtEventsBean) {
		logger.debug("*******************start deleteEvent***************************");
	
		SuccessModel scuccessModel = eventService.deleteEvent(ampamtEventsBean);

		logger.debug("*******************end deleteEvent***************************");

		return new ResponseEntity<>(scuccessModel, HttpStatus.OK);
	}
}
