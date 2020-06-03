package ru.job4j.io.cmd;

import java.util.*;

class Shell {
    private final LinkedList<String> path = new LinkedList<>();
    private final Map<String, Runnable> operators = new HashMap<>();

    public Shell() {
        operators.put("/", path::clear);
        operators.put("", () -> { });
        operators.put(".", () -> { });
        operators.put("..", path::pollLast);
    }

    public Shell cd(final String path) {
        if (path.startsWith("/")) {
            operators.get("/").run();
        }
        final String[] commands = path.trim().split("/");
        for (String command : commands) {
            final Runnable operator = operators.getOrDefault(command, null);
            if (operator == null) {
                this.path.add(command);
            } else {
                operator.run();
            }
        }
        return this;
    }

    public String path() {
        return "/" + String.join("/", path);
    }
}

