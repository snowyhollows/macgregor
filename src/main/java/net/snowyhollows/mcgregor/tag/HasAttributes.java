package net.snowyhollows.mcgregor.tag;

import net.snowyhollows.mcgregor.tag.helper.AttributesWriter;

import java.io.IOException;

public interface HasAttributes {
    void renderAttributes(AttributesWriter attrs) throws IOException;
}
