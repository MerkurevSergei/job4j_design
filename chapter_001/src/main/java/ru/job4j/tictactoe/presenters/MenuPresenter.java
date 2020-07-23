package ru.job4j.tictactoe.presenters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.job4j.tictactoe.model.menu.entities.Node;
import ru.job4j.tictactoe.model.menu.usecase.Menu;
import ru.job4j.tictactoe.views.View;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@code MenuPresenter} implements {@code Presenter} interface,
 * links {@code Menu} and {@code View} for the menu model.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public final class MenuPresenter implements Presenter {

    /**
     * Presenter name.
     */
    private final String name;

    /**
     * Menu instance.
     */
    private final Menu menu;

    /**
     * Acceptable views in map for Game2DPresenter instance.
     */
    private final Map<String, View> views;

    /**
     * Acceptable actions in map for Game2DPresenter instance.
     */
    private final Map<String, Function<String[], String>> actions;

    /**
     * @param name  init.
     * @param menu  init.
     * @param views init.
     */
    public MenuPresenter(String name, Menu menu, Map<String, View> views) {
        this.name = name;
        this.menu = menu;
        this.views = views;
        this.actions = new HashMap<>();
        actions.put("show", actionShow());
    }

    /**
     * Method execute {@code action} method with {@code params} and return result.
     *
     * @param actionName is method called by the presenter.
     * @param params     for method called by the presenter.
     * @return String result after execute method called by the presenter.
     */
    @Override
    public String execute(String actionName, String[] params) {
        final Function<String[], String> action = actions.get(actionName);
        return action.apply(params);
    }

    /**
     * Action show menu page by top node.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionShow() {
        return (params) -> {
            final View view = views.get("ViewMenu");
            Node current = menu.findById(String.join("", params));
            if (current == null) {
                view.put("message_error", "Action unknown");
                return view.showError();
            }
            view.put("title", current.getName());
            view.put("prefixes", Arrays.stream(current.getChildren()).map(Node::getPrefix).toArray(String[]::new));
            view.put("names", Arrays.stream(current.getChildren()).map(Node::getName).toArray(String[]::new));
            final String answer = view.show();
            return getLink(name, "show", params, answer);
        };
    }

    /**
     * Generates a response based on parts of the request.
     *
     * @param presenter name.
     * @param method    name.
     * @param params    for method.
     * @param answer    part.
     * @return full answer.
     */
    private @NotNull String getLink(String presenter, String method, String[] params, String answer) {
        return presenter + "/" + method + "/" + String.join("", params) + answer;
    }
}
