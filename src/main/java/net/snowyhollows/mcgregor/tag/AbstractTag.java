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

import java.util.function.Consumer;

import net.snowyhollows.mcgregor.Event;

/**
 * @author efildre
 */
abstract class AbstractTag extends PureContainer {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onclick;
	private Event.EventListener onchange;
	private String key;

	public String getId() {
		return id;
	}

	public AbstractTag setId(String id) {
		this.id = id;
		return this;
	}

	public String getClassNames() {
		return classNames;
	}

	public AbstractTag setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public String getStyle() {
		return style;
	}

	public AbstractTag setStyle(String style) {
		this.style = style;
		return this;
	}

	public Event.EventListener getOnclick() {
		return onclick;
	}

	public AbstractTag setOnclick(Event.EventListener onclick) {
		this.onclick = onclick;
		return this;
	}

	public Event.EventListener getOnchange() {
		return onchange;
	}

	public AbstractTag setOnchange(Event.EventListener onchange) {
		this.onchange = onchange;
		return this;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public AbstractTag setKey(String key) {
		this.key = key;
		return this;
	}
}
