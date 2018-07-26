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

/**
 * @author efildre
 */
public class GenericTag extends AbstractTag {
	private final String tagName;

	public GenericTag(String id, String classNames, String style, Event.EventListener onclick, Event.EventListener onChange, List<Component> children, String tagName) {
		super(id, classNames, style, onclick, onChange, children);
		this.tagName = tagName;
	}

	public void render(Writer out)
			throws IOException {
		render(tagName, out);
	}

	void render(String tagName, Writer out)
			throws IOException {
		out.append('<');
		out.append(tagName);
		out.append(' ');
		renderAttributes(out);
		out.append('>');

		if (getChildren() != null) {
			renderChildren(out);
		}
		out.append("</");
		out.append(tagName);
		out.append('>');

	}

	void renderAttributes(Writer out)
			throws IOException {
		if (getId() != null && !getId().equals("")) {
			renderAttribute(out, "id", getId());
		}
		renderAttribute(out,"class", getClassNames());
		renderAttribute(out,"style", getStyle());
		if (this.getOnclick() != null) {
			renderAttribute(out, "onclick", "mc_click('" + this.getKey() + "')");
		}
	}

	private void renderChildren(Writer out)
			throws IOException {
		for (Component component : getChildren()) {
			component.render(out);
		}
	}

	void renderAttribute(Writer out, String attr, String val)
			throws IOException {
		if (val == null) return;
		out.append(attr);
		out.append('=');
		out.append('"');
		out.append(val);
		out.append('"');
	}
}
