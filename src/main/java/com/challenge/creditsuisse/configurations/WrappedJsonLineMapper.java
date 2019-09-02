
package com.challenge.creditsuisse.configurations;

import java.util.Map;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import com.challenge.creditsuisse.canonical.Event;

/**
 * @author evelynvieira
 */
public class WrappedJsonLineMapper implements LineMapper<Event> {

	public static final String ID = "id";
	public static final String STATE = "state";
	public static final String TYPE = "type";
	public static final String HOST = "host";
	public static final String TIMESTAMP = "timestamp";

	private JsonLineMapper delegate;

	@Override
	public Event mapLine(final String line, final int lineNumber) throws Exception {

		final Map<String, Object> eventAsMap = delegate.mapLine(line, lineNumber);

		final Event event = new Event();
		event.setId((String) eventAsMap.get(ID));
		event.setState((String) eventAsMap.get(STATE));
		event.setType((String) eventAsMap.get(TYPE));
		event.setHost((String) eventAsMap.get(HOST));
		event.setTimestamp(Long.parseLong(eventAsMap.get(TIMESTAMP).toString()));

		return event;
	}

	public JsonLineMapper getDelegate() {
		return delegate;
	}

	public void setDelegate(final JsonLineMapper delegate) {
		this.delegate = delegate;
	}

}
