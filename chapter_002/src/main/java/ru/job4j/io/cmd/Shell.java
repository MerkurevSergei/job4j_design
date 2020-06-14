package ru.job4j.io.cmd;

import java.util.*;

/**
 * The Shell is class emulate command shell.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
class Shell {

    /**
     * Stored the elements of path.
     */
    private final LinkedList<String> path = new LinkedList<>();

    /**
     * Stored available operators.
     */
    private final Map<String, Command> operators = new HashMap<>();

    /**
     * Initialization operators.
     */
    public Shell() {
        operators.put("/", path::clear);
        operators.put("", () -> { });
        operators.put(".", () -> { });
        operators.put("..", path::pollLast);
    }

    /**
     * @param path is destination path.
     * @return this object with new state after execute command from destination path.
     */
    public Shell cd(final String path) {
        if (path.startsWith("/")) {
            operators.get("/").execute();
        }
        final String[] commands = path.trim().split("/");
        for (String command : commands) {
            final Command operator = operators.getOrDefault(command, null);
            if (operator == null) {
                this.path.add(command);
            } else {
                operator.execute();
            }
        }
        return this;
    }

    /**
     * @return current path.
     */
    public String path() {
        return "/" + String.join("/", path);
    }

    /**
     * The Command interface.
     */
    private interface Command {
        /**
         * Execute command logic.
         */
        void execute();
    }
}