package net.snowyhollows.mcgregor.tag.diff;

import java.util.List;

public interface DiffWriter {
    void rerender(String key, String innerHtml);

    void reorder(String key, List<String> childKeys);
}
