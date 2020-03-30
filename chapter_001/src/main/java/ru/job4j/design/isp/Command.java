package ru.job4j.design.isp;

/**
 * Common command interface.
 */
public interface Command {
    /**
     * Implements empty command.
     */
    Command EMPTY = () -> true;

    /**
     * Executing command.
     *
     * @return result of executing command
     */
    boolean execute();
}
