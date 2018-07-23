package net.snowyhollows.mcgregor.tag;

import java.util.List;

public class GenericTagBuilder {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onClick;
	private Event.EventListener onChange;
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

	public GenericTagBuilder setOnClick(Event.EventListener onClick) {
		this.onClick = onClick;
		return this;
	}

	public GenericTagBuilder setOnChange(Event.EventListener onChange) {
		this.onChange = onChange;
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
		return new GenericTag(id, classNames, style, onClick, onChange, children, tagName);
	}
}