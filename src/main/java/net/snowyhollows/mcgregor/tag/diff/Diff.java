package net.snowyhollows.mcgregor.tag.diff;

import java.util.ArrayList;
import java.util.List;

public class Diff implements DiffWriter {
    private List<DiffCommand> commands = new ArrayList<>();

    @Override
    public void rerender(String key, String innerHtml) {
        commands.add(new RerenderCommand(key, innerHtml));
    }

    @Override
    public void reorder(String key, List<String> childKeys) {
        commands.add(new OrderChildrenCommand(key, childKeys));
    }

    public List<DiffCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<DiffCommand> commands) {
        this.commands = commands;
    }
}
