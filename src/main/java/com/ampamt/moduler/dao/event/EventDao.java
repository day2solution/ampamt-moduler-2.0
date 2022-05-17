package com.ampamt.moduler.dao.event;

import java.util.List;

import com.ampamt.moduler.bean.event.AmpamtEventsBean;
import com.ampamt.moduler.model.SuccessModel;

public interface EventDao {

	SuccessModel createEvent(AmpamtEventsBean ampamtEventsBean);
	List<AmpamtEventsBean> getEventById(AmpamtEventsBean ampamtEventsBean);
	List<AmpamtEventsBean> getEventList(AmpamtEventsBean ampamtEventsBean);
	SuccessModel deleteEvent(AmpamtEventsBean ampamtEventsBean);
	SuccessModel updateEvent(AmpamtEventsBean ampamtEventsBean);
	List<AmpamtEventsBean> getNearestEventList(AmpamtEventsBean ampamtEventsBean);

}
