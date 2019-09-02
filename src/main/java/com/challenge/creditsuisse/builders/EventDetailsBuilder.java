
package com.challenge.creditsuisse.builders;

import com.challenge.creditsuisse.entities.EventDetails;

/**
 * @author evelynvieira
 */
public class EventDetailsBuilder {

	private final EventDetails instance;

	public EventDetailsBuilder() {
		instance = new EventDetails();
	}

	public EventDetailsBuilder setEventId(final String eventId) {
		instance.setEventId(eventId);
		return this;
	}

	public EventDetailsBuilder setEventDuration(final String eventDuration) {
		instance.setEventDuration(eventDuration);
		return this;
	}

	public EventDetailsBuilder setType(final String type) {
		instance.setType(type);
		return this;
	}

	public EventDetailsBuilder setHost(final String host) {
		instance.setHost(host);
		return this;
	}

	public EventDetailsBuilder setAlert(final Boolean alert) {
		instance.setAlert(alert);
		return this;
	}

	public EventDetails build() {
		return instance;
	}

}
