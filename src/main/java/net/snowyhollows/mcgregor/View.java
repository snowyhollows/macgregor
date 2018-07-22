package net.snowyhollows.mcgregor;

import java.util.Optional;

public interface View<Node, Model, Context> {
    Node build(Optional<Node> current, Model model, Context ctx);
}
