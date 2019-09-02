
package com.challenge.creditsuisse.enums;

import java.util.stream.Stream;

public enum State {

	STARTED("STARTED"),
	FINISHED("FINISHED");

	private final String value;

	private State(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static State fromString(final String value) {
		return Stream.of(State.values()) //
				.filter(e -> e.value.equalsIgnoreCase(value)) //
				.findFirst() //
				.orElseThrow(() -> new EnumConstantNotPresentException(State.class, value));
	}

}
