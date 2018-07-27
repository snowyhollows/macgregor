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
public class Option extends GenericTag {

	private boolean selected;

	@Override
	public void render(Writer out)
			throws IOException {
		super.render("option", out);
	}

	@Override
	void renderAttributes(Writer out)
			throws IOException {
		super.renderAttributes(out);
		if (selected) {
			renderAttribute(out,"selected","selected");
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public Option setSelected(boolean selected) {
		this.selected = selected;
		return this;
	}

	@Override
	public Option setTagName(String tagName) {
		return (Option) super.setTagName(tagName);
	}

	@Override
	public Option setId(String id) {
		return (Option) super.setId(id);
	}

	@Override
	public Option setClassNames(String classNames) {
		return (Option) super.setClassNames(classNames);
	}

	@Override
	public Option setStyle(String style) {
		return (Option) super.setStyle(style);
	}

	@Override
	public Option setOnclick(Event.EventListener onclick) {
		return (Option) super.setOnclick(onclick);
	}

	@Override
	public Option setOnchange(Event.EventListener onchange) {
		return (Option) super.setOnchange(onchange);
	}

	@Override
	public Option setKey(String key) {
		return (Option) super.setKey(key);
	}

	@Override
	public Option setChildren(List<Component> children) {
		return (Option) super.setChildren(children);
	}
}
