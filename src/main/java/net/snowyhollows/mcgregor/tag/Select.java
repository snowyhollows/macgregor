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
public class Select extends GenericTag {
	private String value;

	@Override
	public void render(Writer out)
			throws IOException {
		super.render("select", out);
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

	public String getValue() {
		return value;
	}

	public Select setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public Select setTagName(String tagName) {
		return (Select) super.setTagName(tagName);
	}

	@Override
	public Select setId(String id) {
		return (Select) super.setId(id);
	}

	@Override
	public Select setClassNames(String classNames) {
		return (Select) super.setClassNames(classNames);
	}

	@Override
	public Select setStyle(String style) {
		return (Select) super.setStyle(style);
	}

	@Override
	public Select setOnclick(Event.EventListener onclick) {
		return (Select) super.setOnclick(onclick);
	}

	@Override
	public Select setOnchange(Event.EventListener onchange) {
		return (Select) super.setOnchange(onchange);
	}

	@Override
	public Select setKey(String key) {
		return (Select) super.setKey(key);
	}

	@Override
	public Select setChildren(List<Component> children) {
		return (Select) super.setChildren(children);
	}
}
