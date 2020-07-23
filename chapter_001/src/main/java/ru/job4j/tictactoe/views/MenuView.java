package ru.job4j.tictactoe.views;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code MenuView} implements {@code View} and provides displays
 * model data and redirects user input for {@code Menu} model.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public class MenuView implements View {

    /**
     * Contents for displays.
     */
    Map<String, Object> content = new HashMap<>();

    /**
     * Added data to view.
     * @param id data.
     * @param data for displays.
     */
    @Override
    public void put(String id, Object data) {
        content.put(id, data);
    }

    /**
     * Show added data and return user input.
     * @return user input.
     */
    @Override
    public String show() {
        final String title = (String) content.get("title");
        final String[] prefixes = (String[]) content.get("prefixes");
        final String[] names = (String[]) content.get("names");
        System.out.println(title);
        for (int i = 0; i < prefixes.length; i++) {
            System.out.println(prefixes[i] + "." + names[i]);
        }
        System.out.print("Select item: ");
        final String ask = ask();
        System.out.println();
        return ask;
    }

    /**
     * Show error.
     * @return null or other special answer.
     */
    @Override
    public String showError() {
        String msg = (String) content.get("message_error");
        System.out.println(msg);
        System.out.print("Press ENTER to continue...");
        ask();
        System.out.println();
        return null;
    }

}