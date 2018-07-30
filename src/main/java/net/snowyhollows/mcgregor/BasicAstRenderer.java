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
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasicAstRenderer {

	private String builderFromTagName(String t) {
		switch (t) {
			case "##text": return "new net.snowyhollows.mcgregor.tag.HtmlText()";
//			case "select": return "new net.snowyhollows.mcgregor.tag.Select()";
//			case "input": return "new net.snowyhollows.mcgregor.tag.Input()";
//			case "option": return "new net.snowyhollows.mcgregor.tag.Option()";
			default: return "new net.snowyhollows.mcgregor.tag.GenericTag().setTagName(\"" + t + "\")";
		}
	}

	private String createFromTagName(String t) {
		return "";
	}

	private String capitalize(String t) {
		return t.substring(0, 1).toUpperCase() + t.substring(1);
	}

	public String render(ComponentDescription componentDescription) {
		String name = componentDescription.getName();
		List<ComponentDescription> children = componentDescription.getChildren();
		Map<String, String> attributes = componentDescription.getAttributes();
		String singleChildrenList = " java.util.Arrays.asList(\n" + children.stream().map(c -> render(c)).collect(Collectors.joining(", ")) + ")";
		String childrenList = null;
		if (attributes.containsKey("x-list")) {
			String variable = attributes.get("x-variable");
			String list = processValue(attributes.get("x-list"));
			childrenList = list + ".stream().flatMap(" + variable + " -> " + singleChildrenList + ".stream()).collect(java.util.stream.Collectors.toList())";
		} else {
			childrenList = singleChildrenList;
		}
		String ordinaryChildren = (!children.isEmpty() ? ".setChildren(" + childrenList + ")" : "");

		return builderFromTagName(componentDescription.getName())
				+ ".setKey(keys.create())"
				+ attributes.entrySet().stream()
					.filter(e -> !e.getKey().startsWith("x-"))
					.map(e ->
					String.format(getAttributeSetterFormat(name, e.getKey()), prepareSetterName(name, e.getKey()), processValue(e.getValue()))).collect(Collectors.joining())
				+ ordinaryChildren;
	}

	public String getAttributeSetterFormat(String tagName, String attr) {
		if (isGenericAttribute(tagName, attr)) {
			return ".setGenericAttr(%s, %s)";
		} else {
			return ".set%s(%s)";
		}
	}

	private String prepareSetterName(String tagName, String attr) {
		if (isGenericAttribute(tagName, attr)) {
			return '\"' + attr + '\"';
		}
		switch (attr) {
			case "class": return "ClassNames";
			default: return capitalize(attr);
		}
	}

	private boolean isGenericAttribute(String tagName, String attr) {
	    if (tagName.equals("##text")) {
	        return false;
        } else {
            switch (attr) {
                case "class":
                case "value":
                case "onclick":
                case "onchange":
                case "selected":
                    return false;
                default:
                    return true;
            }
        }
	}

	private void consumeBrackets (PushbackReader input)
			throws IOException {
		input.read();
		input.read();
	}

	private void consumeWhitespace (PushbackReader input)
			throws IOException {
		while (true) {
			int x = input.read();
			if (x == -1) {
				return;
			}
			if (!Character.isWhitespace((char)x)) {
				input.unread(x);
				return;
			}
		}
	}

	private boolean hasNextDelimiterTwice (PushbackReader input, char delimiter)
			throws IOException {
		if (isFinished(input)) {
			return false;
		}
		int c = input.read();
		if (isFinished(input)) {
			input.unread(c);
			return false;
		}
		int n = input.read();
		input.unread(n);
		input.unread(c);
		return c == n && n == delimiter;
	}

	private boolean hasNextCode(PushbackReader input)
			throws IOException {
		return !isFinished(input) && hasNextDelimiterTwice(input, '{');
	}

	private boolean isFinished(PushbackReader input)
			throws IOException {
		int c = input.read();
		if (c != -1) {
			input.unread(c);
		}
		return c == -1;
	}

	private void consumeUntilDelimiterTwice (StringBuilder sb, PushbackReader input, char delimiter)
			throws IOException {
		while (true) {
			if (isFinished(input) || hasNextDelimiterTwice(input, delimiter)) {
				return;
			}
			int c = input.read();
			sb.append((char) c);
		}
	}

	private void consumeString(StringBuilder sb, PushbackReader input)
			throws IOException {
		consumeUntilDelimiterTwice(sb, input, '{');
	}

	private void consumeCode(StringBuilder sb, PushbackReader input)
			throws IOException {
		consumeUntilDelimiterTwice(sb, input, '}');
	}

	public String processValue(String value) {
		try {

			PushbackReader input = new PushbackReader(new StringReader(value), 2);
			StringBuilder result = new StringBuilder();

			processNext(input, result);
			while (!isFinished(input)) {
				result.append(" + ");
				processNext(input, result);
			}

			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void processNext(PushbackReader input, StringBuilder result)
			throws IOException {
		if (hasNextCode(input)) {
			consumeBrackets(input);
			consumeWhitespace(input);
			consumeCode(result, input);
			consumeBrackets(input);
		} else {
			result.append('"');
			consumeString(result, input);
			result.append('"');
		}
	}
}
