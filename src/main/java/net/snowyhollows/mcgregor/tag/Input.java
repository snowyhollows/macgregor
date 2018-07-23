/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.mcgregor.tag;

import java.util.List;

/**
 * @author efildre
 */
public class Input extends GenericTag {
	private final String value;

	public Input(String id, String classNames, String style, Event.EventListener onClick, Event.EventListener onChange, List<Component> children, String tagName, String value) {
		super(id, classNames, style, onClick, onChange, children, tagName);
		this.value = value;
	}
}
