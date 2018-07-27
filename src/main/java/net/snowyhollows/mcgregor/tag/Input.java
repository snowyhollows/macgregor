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
import java.io.Writer;
import java.util.List;

import net.snowyhollows.mcgregor.Event;

/**
 * @author efildre
 */
public class Input extends GenericTag {
	private String value;

	public String getValue() {
		return value;
	}

	public Input setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public void render(Writer out)
			throws IOException {
		super.render("input", out);
	}

	@Override
	void renderAttributes(Writer out)
			throws IOException {
		super.renderAttributes(out);
		if (this.getOnchange() != null) {
			renderAttribute(out, "onchange", "mc_change('" + this.getKey() + "', this.value)");
		}
		if (this.value != null) {
			renderAttribute(out, "value", this.value);
		}
	}

	@Override
	public Input setTagName(String tagName) {
		return (Input) super.setTagName(tagName);
	}

	@Override
	public Input setId(String id) {
		return (Input) super.setId(id);
	}

	@Override
	public Input setClassNames(String classNames) {
		return (Input) super.setClassNames(classNames);
	}

	@Override
	public Input setStyle(String style) {
		return (Input) super.setStyle(style);
	}

	@Override
	public Input setOnclick(Event.EventListener onclick) {
		return (Input) super.setOnclick(onclick);
	}

	@Override
	public Input setOnchange(Event.EventListener onchange) {
		return (Input) super.setOnchange(onchange);
	}

	@Override
	public Input setKey(String key) {
		return (Input) super.setKey(key);
	}

	@Override
	public Input setChildren(List<Component> children) {
		return (Input) super.setChildren(children);
	}
}
