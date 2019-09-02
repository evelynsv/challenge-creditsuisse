
package com.challenge.creditsuisse.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author evelynvieira
 */
@Entity
@Table(name = "event_details")
public class EventDetails implements Serializable {

	private static final long serialVersionUID = 1490899127146744456L;

	@Id
	@Column(name = "event_id", length = 36, nullable = false)
	private String eventId;

	@Column(name = "event_duration", length = 100, nullable = true)
	private String eventDuration;

	@Column(name = "type", length = 100, nullable = true)
	private String type;

	@Column(name = "host", length = 100, nullable = true)
	private String host;

	@Column(name = "alert")
	private Boolean alert;

	public EventDetails() {}

	public EventDetails(final String eventId,
			final String eventDuration,
			final String type,
			final String host,
			final Boolean alert) {
		super();
		this.eventId = eventId;
		this.eventDuration = eventDuration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(final String eventId) {
		this.eventId = eventId;
	}

	public String getEventDuration() {
		return eventDuration;
	}

	public void setEventDuration(final String eventDuration) {
		this.eventDuration = eventDuration;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(final Boolean alert) {
		this.alert = alert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alert == null) ? 0 : alert.hashCode());
		result = prime * result + ((eventDuration == null) ? 0 : eventDuration.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EventDetails other = (EventDetails) obj;
		if (alert == null) {
			if (other.alert != null)
				return false;
		} else if (!alert.equals(other.alert))
			return false;
		if (eventDuration == null) {
			if (other.eventDuration != null)
				return false;
		} else if (!eventDuration.equals(other.eventDuration))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventDetails [eventId="
				+ eventId
				+ ", eventDuration="
				+ eventDuration
				+ ", type="
				+ type
				+ ", host="
				+ host
				+ ", alert="
				+ alert
				+ "]";
	}

}
