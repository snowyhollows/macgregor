package net.snowyhollows.mcgregor.tag;

import java.util.List;

public class GenericTagBuilder {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onclick;
	private Event.EventListener onchange;
	private List<Component> children;
	private String tagName;

	public GenericTagBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public GenericTagBuilder setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public GenericTagBuilder setStyle(String style) {
		this.style = style;
		return this;
	}

	public GenericTagBuilder setOnclick(Event.EventListener onclick) {
		this.onclick = onclick;
		return this;
	}

	public GenericTagBuilder setOnchange(Event.EventListener onchange) {
		this.onchange = onchange;
		return this;
	}

	public GenericTagBuilder setChildren(List<Component> children) {
		this.children = children;
		return this;
	}

	public GenericTagBuilder setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public GenericTag createGenericTag() {
		return new GenericTag(id, classNames, style, onclick, onchange, children, tagName);
	}
}