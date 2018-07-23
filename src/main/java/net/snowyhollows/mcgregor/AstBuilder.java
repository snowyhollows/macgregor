/*
 * Copyright (c) 2009-2018 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.mcgregor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * @author efildre
 */
public class AstBuilder {

	public static ComponentDescription process(InputStream is)
			throws IOException {
		Document document = Jsoup.parse(is, "UTF-8", "http://invalid.com");
		return doNode(document.body());
	}

	private static ComponentDescription doNode(Node node) {
		if (node instanceof Element) {
			return doElement((Element)node);
		}
		if (node instanceof TextNode) {
			return doTextNode((TextNode)node);
		}
		throw new IllegalArgumentException("unknown type of node: " + node);
	}

	private static ComponentDescription doTextNode(TextNode node) {
		ComponentDescription componentDescription = new ComponentDescription();
		componentDescription.setName("##text");
		componentDescription.setAttributes(Collections.singletonMap("text", node.text()));
		componentDescription.setChildren(Collections.emptyList());
		return componentDescription;
	}

	public static ComponentDescription doElement(Element element) {
		ComponentDescription componentDescription = new ComponentDescription();
		componentDescription.setName(element.nodeName());
		componentDescription.setAttributes(
				element.attributes().asList().stream().collect(Collectors.toMap(Attribute::getKey, Attribute::getValue)));
		componentDescription.setChildren(element.childNodes().stream().map(AstBuilder::doNode).collect(Collectors.toList()));
		return componentDescription;
	}
}
