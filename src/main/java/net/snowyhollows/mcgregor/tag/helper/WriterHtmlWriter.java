package net.snowyhollows.mcgregor.tag.helper;

import java.io.IOException;
import java.io.Writer;

public class WriterHtmlWriter implements HtmlWriter, AttributesWriter{
    private final Writer writer;
    private boolean canWriteAttributes;

    public WriterHtmlWriter(Writer writer) {
        this.writer = writer;
    }

    private void assertInsideStartTag() {
        if (!canWriteAttributes) throw  new IllegalStateException();
    }

    private void assertOutsideStartTag() throws IOException {
        if (canWriteAttributes) {
            writer.append('>');
            canWriteAttributes = false;
        }
    }

    @Override
    public void writeAttribute(String name, String value) throws IOException {
        if (value == null) return;
        writer.append(' ');
        writer.append(name);
        writer.append('=');
        writer.append('"');
        writer.append(value);
        writer.append('"');
    }

    @Override
    public void startTag(String tagName) throws IOException {
        assertOutsideStartTag();
        writer.append('<');
        writer.append(tagName);
        canWriteAttributes = true;
    }

    @Override
    public void endTag(String tagName) throws IOException {
        assertOutsideStartTag();
        writer.append("</");
        writer.append(tagName);
        writer.append('>');
    }

    @Override
    public void writeText(String text) throws IOException {
        assertOutsideStartTag();
        writer.append(text);
    }

    @Override
    public void writeComment(String comment) throws IOException {
        assertOutsideStartTag();
        writer.append("<!--");
        writer.append(comment);
        writer.append("-->");

    }

    @Override
    public AttributesWriter writeAttributes() {
        return this;
    }

}
