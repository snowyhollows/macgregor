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

import java.util.List;
import java.util.Map;

/**
 * @author efildre
 */
public class ComponentDescription {
	private String name;
	private Map<String, String> attributes;
	private List<ComponentDescription> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<ComponentDescription> getChildren() {
		return children;
	}

	public void setChildren(List<ComponentDescription> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "ComponentDescription{" +
				"name='" + name + '\'' +
				", attributes=" + attributes +
				", children=" + children +
				'}';
	}
}
