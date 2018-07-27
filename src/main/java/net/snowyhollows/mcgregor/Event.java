/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.mcgregor;

/**
 * @author efildre
 */
public class Event {
	public interface EventListener {
		void onEvent(Event event);
	}

	public final String sourceKey;
	public final String value;

	public Event(String sourceKey, String value) {
		this.sourceKey = sourceKey;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Event{" +
				"sourceKey='" + sourceKey + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
