package net.snowyhollows.mcgregor.tag.diff;

public class RerenderCommand implements DiffCommand {
    private final String key;
    private final String innerHtml;

    public RerenderCommand(String key, String innerHtml) {
        this.key = key;
        this.innerHtml = innerHtml;
    }

    public String getKey() {
        return key;
    }

    public String getInnerHtml() {
        return innerHtml;
    }
}
