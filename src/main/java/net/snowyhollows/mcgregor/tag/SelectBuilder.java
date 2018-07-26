package net.snowyhollows.mcgregor.tag;

import java.util.List;

public class SelectBuilder {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onClick;
	private Event.EventListener onChange;
	private List<Component> children;
	private String tagName;
	private String value;

	public SelectBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public SelectBuilder setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public SelectBuilder setStyle(String style) {
		this.style = style;
		return this;
	}

	public SelectBuilder setOnclick(Event.EventListener onClick) {
		this.onClick = onClick;
		return this;
	}

	public SelectBuilder setOnchange(Event.EventListener onChange) {
		this.onChange = onChange;
		return this;
	}

	public SelectBuilder setChildren(List<Component> children) {
		this.children = children;
		return this;
	}

	public SelectBuilder setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public SelectBuilder setValue(String value) {
		this.value = value;
		return this;
	}

	public Select createSelect() {
		return new Select(id, classNames, style, onClick, onChange, children, tagName, value);
	}
}