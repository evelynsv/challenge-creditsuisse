
package com.challenge.creditsuisse.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.creditsuisse.business.EventBusiness;
import com.challenge.creditsuisse.canonical.Event;
import com.challenge.creditsuisse.entities.EventDetails;

public class EventItemProcessor implements ItemProcessor<Event, EventDetails> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventItemProcessor.class);

	@Autowired
	private EventBusiness eventBusiness;

	@Override
	public EventDetails process(final Event event) throws Exception {
		LOGGER.info("process(event={})", event);

		final EventDetails eventDetails = eventBusiness.processEvent(event);
		return eventDetails;
	}

}
