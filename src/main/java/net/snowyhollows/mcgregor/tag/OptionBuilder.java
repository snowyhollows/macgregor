package net.snowyhollows.mcgregor.tag;

import java.util.List;

public class OptionBuilder {
	private String id;
	private String classNames;
	private String style;
	private Event.EventListener onClick;
	private Event.EventListener onChange;
	private List<Component> children;
	private String tagName;
	private boolean selected;

	public OptionBuilder setId(String id) {
		this.id = id;
		return this;
	}

	public OptionBuilder setClassNames(String classNames) {
		this.classNames = classNames;
		return this;
	}

	public OptionBuilder setStyle(String style) {
		this.style = style;
		return this;
	}

	public OptionBuilder setOnClick(Event.EventListener onClick) {
		this.onClick = onClick;
		return this;
	}

	public OptionBuilder setOnChange(Event.EventListener onChange) {
		this.onChange = onChange;
		return this;
	}

	public OptionBuilder setChildren(List<Component> children) {
		this.children = children;
		return this;
	}

	public OptionBuilder setTagName(String tagName) {
		this.tagName = tagName;
		return this;
	}

	public OptionBuilder setSelected(boolean selected) {
		this.selected = selected;
		return this;
	}

	public OptionBuilder setSelected(String selected) {
		this.selected = selected.equalsIgnoreCase("selected");
		return this;
	}

	public Option createOption() {
		return new Option(id, classNames, style, onClick, onChange, children, tagName, selected);
	}
}