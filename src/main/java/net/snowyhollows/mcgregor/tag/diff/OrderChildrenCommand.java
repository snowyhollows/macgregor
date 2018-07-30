package net.snowyhollows.mcgregor.tag.diff;

import java.util.List;

public class OrderChildrenCommand implements DiffCommand {
    private final String key;
    private final List<String> keys;

    public OrderChildrenCommand(String key, List<String> keys) {
        this.key = key;
        this.keys = keys;
    }

    public String getKey() {
        return key;
    }

    public List<String> getKeys() {
        return keys;
    }
}
