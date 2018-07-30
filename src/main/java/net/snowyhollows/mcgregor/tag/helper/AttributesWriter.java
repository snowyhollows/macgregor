package net.snowyhollows.mcgregor.tag.helper;

import java.io.IOException;

public interface AttributesWriter {
    public void writeAttribute(String name, String value) throws IOException;
}
