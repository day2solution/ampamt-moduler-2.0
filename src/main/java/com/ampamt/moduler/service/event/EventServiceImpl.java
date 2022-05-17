package com.ampamt.moduler.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ampamt.moduler.bean.event.AmpamtEventsBean;
import com.ampamt.moduler.dao.event.EventDao;
import com.ampamt.moduler.model.SuccessModel;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventDao eventDao;

	@Override
	public SuccessModel createEvent(AmpamtEventsBean ampamtEventsBean) {
		SuccessModel successModel = eventDao.createEvent(ampamtEventsBean);
		return successModel;
	}

	@Override
	public List<AmpamtEventsBean> getEventList(AmpamtEventsBean ampamtEventsBean) {
		List<AmpamtEventsBean> ampamtEventsBeanList = eventDao.getEventList(ampamtEventsBean);
		return ampamtEventsBeanList;
	}

	@Override
	public SuccessModel deleteEvent(AmpamtEventsBean ampamtEventsBean) {
		SuccessModel scuccessModel = eventDao.deleteEvent(ampamtEventsBean);
		return scuccessModel;
	}

	@Override
	public SuccessModel updateEvent(AmpamtEventsBean ampamtEventsBean) {
		SuccessModel scuccessModel = eventDao.updateEvent(ampamtEventsBean);
		return scuccessModel;
	}

	@Override
	public List<AmpamtEventsBean> getEventById(AmpamtEventsBean ampamtEventsBean) {
		List<AmpamtEventsBean> ampamtEventsBeanList = eventDao.getEventById(ampamtEventsBean);
		return ampamtEventsBeanList;
	}

	@Override
	public List<AmpamtEventsBean> getNearestEventList(AmpamtEventsBean ampamtEventsBean) {
		List<AmpamtEventsBean> ampamtEventsBeanList = eventDao.getNearestEventList(ampamtEventsBean);
		return ampamtEventsBeanList;
	}
}
