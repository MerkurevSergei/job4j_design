package ru.job4j.list;

public final class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public final T poll() {
        return linked.deleteLast();
    }

    public final void push(T value) {
        linked.add(value);
    }

    public final boolean empty() {
        return !linked.iterator().hasNext();
    }
}