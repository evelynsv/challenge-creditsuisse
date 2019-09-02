
package com.challenge.creditsuisse.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.creditsuisse.canonical.Event;
import com.challenge.creditsuisse.converters.EventToEventDetailsConverter;
import com.challenge.creditsuisse.entities.EventDetails;
import com.challenge.creditsuisse.enums.State;

/**
 * 
 * Processes an Event. Calculates the duration of the event, flag an event and returns an EventDeatails entity.
 * 
 * @author evelynvieira
 */
@Component
public class EventBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventBusiness.class);

	@Autowired
	private EventToEventDetailsConverter converter;

	public static final Map<String, Event> eventMap = new HashMap<>();

	public EventDetails processEvent(final Event event) {
		LOGGER.debug("processEvent(event={})", event);

		final Event eventMapped = eventMap.get(event.getId());

		if (eventMapped == null) {
			eventMap.put(event.getId(), event);
			return convertToEventDetails(event);
		} else {
			final Long duration = calculateDuration(eventMapped.getTimestamp(), event.getTimestamp(), event.getState());
			final Boolean alert = flagEvent(duration);
			final EventDetails eventDetails = convertToEventDetails(event);
			eventDetails.setAlert(alert);
			eventDetails.setEventDuration(duration.toString());
			eventMap.remove(event.getId());
			return eventDetails;
		}
	}

	private EventDetails convertToEventDetails(final Event event) {
		return converter.convert(event);
	}

	private Long calculateDuration(final Long timestampEventMap, final Long timestampEvent, final String state) {

		Long duration = null;

		if (state.equals(State.STARTED.getValue())) {
			duration = Math.subtractExact(timestampEventMap, timestampEvent);
		} else {
			duration = Math.subtractExact(timestampEvent, timestampEventMap);
		}

		return duration;

	}

	private Boolean flagEvent(final Long duration) {

		if (duration > 4) {
			return true;
		} else {
			return false;
		}

	}

}
