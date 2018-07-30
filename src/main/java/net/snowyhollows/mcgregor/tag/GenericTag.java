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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snowyhollows.mcgregor.Event;
import net.snowyhollows.mcgregor.tag.helper.AttributesWriter;
import net.snowyhollows.mcgregor.tag.helper.HtmlWriter;
import sun.net.www.content.text.Generic;

/**
 * @author efildre
 */
public class GenericTag implements Container, HasAttributes {
	private String tagName;
	private String classNames;
	private String value;
	private Event.EventListener onclick;
	private Event.EventListener onchange;
	private String key;
	private boolean selected;
	private Map<String, String> genericAttributes;
	private List<Component> children;

	private Map<String, String> genericAttributes() {
		if (genericAttributes == null) {
			genericAttributes = new HashMap<>(8);
		}
		return genericAttributes;
	}

	public GenericTag setGenericAttr(String key, String value) {
		genericAttributes().put(key, value);
		return this;
	}

	public String getValue() {
		return value;
	}

	public GenericTag setValue(String value) {
		this.value = value;
		return this;
	}

	public boolean isSelected() {
		return selected;
	}

	public GenericTag setSelected(boolean selected) {
		this.selected = selected;
		return this;
	}

	public String getClassNames() {
		return classNames;
	}

	public GenericTag setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public Event.EventListener getOnclick() {
		return onclick;
	}

	public GenericTag setOnclick(Event.EventListener onclick) {
		this.onclick = onclick;
		return this;
	}

	public Event.EventListener getOnchange() {
		return onchange;
	}

	public GenericTag setOnchange(Event.EventListener onchange) {
		this.onchange = onchange;
		return this;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void render(HtmlWriter out) throws IOException {

		out.startTag(tagName);
		AttributesWriter attrs = out.writeAttributes();
		renderAttributes(attrs);
		if (getChildren() != null) {
			for (Component component : getChildren()) {
				component.render(out);
			}
		}
		out.endTag(tagName);
	}

	@Override
	public void renderAttributes(AttributesWriter attrs) throws IOException {
		if (getKey() != null && !getKey().equals("")) {
			attrs.writeAttribute("data-key", getKey());
		}
		attrs.writeAttribute("class", getClassNames());
		String filteredEvents = "";
		if (this.getOnclick() != null) {
			filteredEvents += ":click";
		}
		if (this.getOnchange() != null) {
			filteredEvents += ":onchange";
		}
		if (!filteredEvents.equals("")) {
			attrs.writeAttribute("data-events", filteredEvents);
		}
		if (this.value != null) {
			attrs.writeAttribute( "value", this.value);
		}
		if (selected) {
			attrs.writeAttribute("selected","selected");
		}
		if (genericAttributes != null) {
			for (Map.Entry<String, String> stringStringEntry : genericAttributes.entrySet()) {
				attrs.writeAttribute(stringStringEntry.getKey(), stringStringEntry.getValue());
			}
		}
	}

	@Override
	public boolean isIdentifiableOnClient() {
		return false;
	}

	@Override
	public GenericTag setKey(String key) {
		this.key = key;
		return this;
	}

	public String getTagName() {
		return tagName;
	}

	public GenericTag setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	@Override
	public List<Component> getChildren() {
		return children;
	}

	@Override
	public GenericTag setChildren(List<Component> children) {
		this.children = children;
		return this;
	}
}
