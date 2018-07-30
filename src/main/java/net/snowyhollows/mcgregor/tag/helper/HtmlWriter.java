package net.snowyhollows.mcgregor.tag.helper;

import java.io.IOException;

public interface HtmlWriter {
    void startTag(String tag) throws IOException;
    void endTag(String tag) throws IOException;
    void writeText(String text) throws IOException;
    void writeComment(String comment) throws IOException;
    AttributesWriter writeAttributes();
}
