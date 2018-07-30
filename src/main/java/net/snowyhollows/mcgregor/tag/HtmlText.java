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

/**
 * @author efildre
 */
public class HtmlText implements Component {
	private String text;

	public String getText() {
		return text;
	}

	public HtmlText setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public HtmlText setKey(String k) {
		return this;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public void render(HtmlWriter out) throws IOException {
		out.writeText(text);
	}

	@Override
	public boolean isIdentifiableOnClient() {
		return false;
	}
}
