package ru.job4j.tictactoe.views;

import java.util.HashMap;
import java.util.Map;

public class Game2DOptionView implements View {

    /**
     * Contents for displays.
     */
    Map<String, Object> content = new HashMap<>();

    /**
     * Added data to view.
     *
     * @param id   data.
     * @param data for displays.
     */
    @Override
    public void put(String id, Object data) {
        content.put(id, data);
    }

    /**
     * Show added data and return user input.
     *
     * @return user input.
     */
    @Override
    public String show() {
        final String title = (String) content.get("title");
        System.out.println(title);
        System.out.print("Press ENTER to continue...");
        ask();
        System.out.println();
        return "default";
    }

    /**
     * Show error.
     *
     * @return null or other special answer.
     */
    @Override
    public String showError() {
        final String msg = (String) content.get("message_error");
        System.out.println(msg);
        System.out.print("Press ENTER to continue...");
        ask();
        System.out.println();
        return null;
    }
}
