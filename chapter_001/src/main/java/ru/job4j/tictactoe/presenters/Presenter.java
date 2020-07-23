package ru.job4j.tictactoe.presenters;

/**
 * The {@code Presenter} provide interface for the application presenters.
 * Typically, the implementation of this interface links the model and
 * the view that provides the user's display and input.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface Presenter {

    /**
     * Method execute {@code action} method with {@code params} and return result.
     *
     * @param actionName is method called by the presenter.
     * @param params     for method called by the presenter.
     * @return String result after execute method called by the presenter.
     */
    String execute(String actionName, String[] params);
}