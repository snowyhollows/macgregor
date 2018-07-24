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
abstract class AbstractTag implements Component {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onclick;
	private Event.EventListener onChange;
	private List<Component> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Event.EventListener getOnclick() {
		return onclick;
	}

	public void setOnclick(Event.EventListener onclick) {
		this.onclick = onclick;
	}

	public Event.EventListener getOnChange() {
		return onChange;
	}

	public void setOnChange(Event.EventListener onChange) {
		this.onChange = onChange;
	}

	public List<Component> getChildren() {
		return children;
	}

	public void setChildren(List<Component> children) {
		this.children = children;
	}

	public AbstractTag(String id, String classNames, String style, Event.EventListener onclick, Event.EventListener onChange, List<Component> children) {
		this.id = id;
		this.classNames = classNames;
		this.style = style;
		this.onclick = onclick;
		this.onChange = onChange;
		this.children = children;
	}
}
