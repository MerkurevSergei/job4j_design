package ru.job4j.design.isp;

import java.util.*;

public class NodeImpl implements Node {
    private String id;
    private String name;
    private Command command;
    private List<Node> children = new ArrayList<>();

    public NodeImpl(String id, String name, Command command) {
        this.id = id;
        this.name = name;
        this.command = command;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean execute() {
        return command.execute();
    }

    @Override
    public void addChildren(Node node) {
        children.add(node);
    }

    @Override
    public ListIterator<Node> childIterator() {
        return children.listIterator();
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeImpl node = (NodeImpl) o;
        return Objects.equals(id, node.id)
                && Objects.equals(name, node.name)
                && Objects.equals(command, node.command)
                && Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, command, children);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" - ");
        if (!id.isEmpty()) {
            sj.add(id);
        }
        if (!name.isEmpty()) {
            sj.add(name);
        }
        return sj.toString();
    }
}
