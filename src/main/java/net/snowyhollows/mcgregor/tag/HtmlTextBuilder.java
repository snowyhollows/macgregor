package net.snowyhollows.mcgregor.tag;

public class HtmlTextBuilder {
	private String text;

	public HtmlTextBuilder setText(String text) {
		this.text = text;
		return this;
	}

	public HtmlText createHtmlText() {
		return new HtmlText(text);
	}
}