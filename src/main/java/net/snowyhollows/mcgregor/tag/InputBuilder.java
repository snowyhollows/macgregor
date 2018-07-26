package net.snowyhollows.mcgregor.tag;

import java.util.List;

public class InputBuilder {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onclick;
	private Event.EventListener onchange;
	private List<Component> children;
	private String tagName;
	private String value;

	public InputBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public InputBuilder setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public InputBuilder setStyle(String style) {
		this.style = style;
		return this;
	}

	public InputBuilder setOnclick(Event.EventListener onclick) {
		this.onclick = onclick;
		return this;
	}

	public InputBuilder setOnchange(Event.EventListener onchange) {
		this.onchange = onchange;
		return this;
	}

	public InputBuilder setChildren(List<Component> children) {
		this.children = children;
		return this;
	}

	public InputBuilder setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public InputBuilder setValue(String value) {
		this.value = value;
		return this;
	}

	public Input createInput() {
		return new Input(id, classNames, style, onclick, onchange, children, tagName, value);
	}
}