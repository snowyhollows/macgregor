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

import java.util.List;

/**
 * @author efildre
 */
abstract class AbstractTag implements Component {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onClick;
	private Event.EventListener onChange;
	private List<Component> children;

	public AbstractTag(String id, String classNames, String style, Event.EventListener onClick, Event.EventListener onChange, List<Component> children) {
		this.id = id;
		this.classNames = classNames;
		this.style = style;
		this.onClick = onClick;
		this.onChange = onChange;
		this.children = children;
	}
}
