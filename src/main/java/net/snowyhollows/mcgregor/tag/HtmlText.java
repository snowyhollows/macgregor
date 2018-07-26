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

/**
 * @author efildre
 */
public class HtmlText implements Component {
	public String getText() {
		return text;
	}


	private final String text;

	public HtmlText(String text) {
		this.text = text;
	}

	public void render(Writer out)
			throws IOException {
		out.append(text);
	}

	@Override
	public void setKey(String k) {
	}

	@Override
	public String getKey() {
		return null;
	}
}
