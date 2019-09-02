
package com.challenge.creditsuisse.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.challenge.creditsuisse.builders.EventDetailsBuilder;
import com.challenge.creditsuisse.canonical.Event;
import com.challenge.creditsuisse.entities.EventDetails;

/**
 * @author evelynvieira
 */
@Component
public class EventToEventDetailsConverter implements Converter<Event, EventDetails> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventToEventDetailsConverter.class);

	@Override
	public EventDetails convert(final Event source) {
		LOGGER.debug("convert(source={})", source);

		final Boolean sourceIsNull = source == null;

		final String eventId = sourceIsNull ? null : source.getId();
		final String type = sourceIsNull ? null : source.getType();
		final String host = sourceIsNull ? null : source.getHost();

		return new EventDetailsBuilder().setEventId(eventId).setType(type).setHost(host).build();
	}

}
