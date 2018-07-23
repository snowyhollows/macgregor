package net.snowyhollows.mcgregor.api;

import java.util.Optional;

public interface View<Node, Model, Context> {
    Node build(Node current, Model model, Context ctx);
}
