package ru.job4j.list;

/**
 * Simple queue based on couple stack
 *
 * @param <T> - item type
 */
public class SimpleQueue<T> {

    /**
     * in stack
     */
    SimpleStack<T> in;

    /**
     * out stack
     */
    SimpleStack<T> out;

    /**
     * Simple queue constructor
     */
    public SimpleQueue() {
        this.in = new SimpleStack<>();
        this.out = new SimpleStack<>();
    }

    /**
     * @return item
     */
    public T poll() {
        if (out.empty()) {
            while (!in.empty()) {
                out.push(in.poll());
            }
        }
        return out.poll();
    }

    /**
     * @param value - item
     */
    public void push(T value) {
        in.push(value);
    }
}
