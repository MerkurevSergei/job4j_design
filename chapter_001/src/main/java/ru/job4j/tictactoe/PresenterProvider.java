package ru.job4j.tictactoe;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.job4j.tictactoe.model.game.entities.Player;
import ru.job4j.tictactoe.model.game.usecase.Game;
import ru.job4j.tictactoe.model.game.usecase.Game2D;
import ru.job4j.tictactoe.model.menu.entities.NodeImpl;
import ru.job4j.tictactoe.model.menu.usecase.Menu;
import ru.job4j.tictactoe.model.menu.usecase.MenuImpl;
import ru.job4j.tictactoe.presenters.Game2DPresenter;
import ru.job4j.tictactoe.presenters.MenuPresenter;
import ru.job4j.tictactoe.presenters.Presenter;
import ru.job4j.tictactoe.views.Game2DEndView;
import ru.job4j.tictactoe.views.Game2DMoveView;
import ru.job4j.tictactoe.views.Game2DOptionView;
import ru.job4j.tictactoe.views.MenuView;

import java.util.HashMap;
import java.util.Map;

/**
 * Initializes the set of {@code Presenter} for the application.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
class PresenterProvider {

    /**
     * Set of the {@code Presenter} for the application.
     */
    private final Map<String, Presenter> presenters = new HashMap<>();

    /**
     * Constructor.
     */
    PresenterProvider() {
        presenters.put(
                "menu",
                new MenuPresenter("menu", getMenu(),
                        Map.of("ViewMenu", new MenuView()))
        );
        presenters.put(
                "game",
                new Game2DPresenter("game", getGame(),
                        Map.of("ViewBoard", new Game2DMoveView(),
                                "ViewOptionChanged", new Game2DOptionView(),
                                "ViewEnd", new Game2DEndView()))
        );
    }

    /**
     * Get {@code Presenter} by id for the application..
     *
     * @param id presenter.
     * @return {@code Presenter}.
     */
    Presenter get(String id) {
        return presenters.get(id);
    }

    /**
     * Get {@code Menu}for the application..
     *
     * @return {@code Menu}.
     */
    private @NotNull Menu getMenu() {
        NodeImpl top =
                new NodeImpl("0", "GAME MENU", "0", new NodeImpl[]{
                        new NodeImpl("01", "First move", "1", new NodeImpl[]{
                                new NodeImpl("011", "AI", "1"),
                                new NodeImpl("012", "Human", "2"),
                                new NodeImpl("010", "Back", "0"),
                        }),
                        new NodeImpl("02", "Board size", "2", new NodeImpl[]{
                                new NodeImpl("021", "3x3", "1"),
                                new NodeImpl("022", "5x5", "2"),
                                new NodeImpl("023", "Back", "0"),
                        }),
                        new NodeImpl("03", "Chain length", "3", new NodeImpl[]{
                                new NodeImpl("031", "3 item to win", "1"),
                                new NodeImpl("032", "5 item to win", "2"),
                                new NodeImpl("030", "Back", "0"),
                        }),
                        new NodeImpl("04", "Start", "4"),
                        new NodeImpl("05", "Exit", "5"),
                });
        return new MenuImpl(top);
    }

    /**
     * Get {@code Game}for the application..
     *
     * @return {@code Game}.
     */
    @Contract(value = " -> new", pure = true)
    private @NotNull Game getGame() {
        return new Game2D(3, 3, 3, Player.AI);
    }
}



