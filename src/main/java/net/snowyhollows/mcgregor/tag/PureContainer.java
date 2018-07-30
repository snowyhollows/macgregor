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

import net.snowyhollows.mcgregor.tag.helper.HtmlWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * @author efildre
 */
public class PureContainer implements Container {
	private List<Component> children;

	void renderChildren(HtmlWriter out)
			throws IOException {
		for (Component component : getChildren()) {
			component.render(out);
		}
	}

	@Override
	public List<Component> getChildren() {
		return children;
	}

	@Override
	public PureContainer setChildren(List<Component> children) {
		this.children = children;
		return this;
	}

	@Override
	public void render(HtmlWriter out) throws IOException {
		renderChildren(out);
	}

	@Override
	public boolean isIdentifiableOnClient() {
		return false;
	}
}
