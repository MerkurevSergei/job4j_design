package ru.job4j.tictactoe.presenters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.job4j.tictactoe.model.game.entities.Player;
import ru.job4j.tictactoe.model.game.usecase.Game;
import ru.job4j.tictactoe.views.View;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * The {@code Game2DPresenter} implements {@code Presenter} interface,
 * links {@code Game} and {@code View} for the 2D Tic-tac-toe game.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.3
 * @since 0.1
 */
public final class Game2DPresenter implements Presenter {

    /**
     * Presenter name.
     */
    private final String name;

    /**
     * Game instance.
     */
    private final Game game;

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
     * @param game  init.
     * @param views init.
     */
    public Game2DPresenter(String name, Game game, Map<String, View> views) {
        this.name = name;
        this.game = game;
        this.views = views;
        this.actions = new HashMap<>();
        actions.put("start", actionStart());
        actions.put("end", actionEnd());
        actions.put("HUMAN", actionHuman());
        actions.put("AI", actionAI());
        actions.put("setPlayer", actionSetFirstPlayer());
        actions.put("setSize", actionSetSize());
        actions.put("setChain", actionSetWinLength());
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
     * Action execute when the game starts.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionStart() {
        return (params) -> {
            game.start();
            return actions.get(game.getFirstPlayer().toString()).apply(params);
        };
    }

    /**
     * Action execute when the game finished.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionEnd() {
        return (params) -> {
            final View view = views.get("ViewEnd");
            view.put("game", game);
            return view.show();
        };
    }

    /**
     * Action executes the {@code Player.HUMAN} turn.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionHuman() {
        return (params) -> {
            final View view = views.get("ViewBoard");
            view.put("game", game);
            final String answer = view.show();
            final String[] s = answer.split(" ");

            int i;
            int j;

            try {
                i = Integer.parseInt(s[0]);
                j = Integer.parseInt(s[1]);
            } catch (NumberFormatException e) {
                view.put("message_error", "Coordinates not the number: " + Arrays.toString(s) + "!");
                return getLink(name, Player.HUMAN.toString(), params, view.showError());
            } catch (ArrayIndexOutOfBoundsException e) {
                view.put("message_error", "Invalid point: " + Arrays.toString(s) + "!");
                return getLink(name, Player.HUMAN.toString(), params, view.showError());
            }

            try {
                game.setHumanMark(i, j);
            } catch (IllegalArgumentException e) {
                view.put("message_error", e.toString());
                return getLink(name, Player.HUMAN.toString(), params, view.showError());
            }

            return (game.getWinner() == null)
                    ? getLink(name, Player.AI.toString(), new String[0], "")
                    : getLink(name, "end", new String[]{"HUMAN"}, "");
        };
    }

    /**
     * Action executes the {@code Player.AI} turn.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionAI() {
        return (params) -> {
            game.moveAI();
            return (game.getWinner() == null)
                    ? getLink(name, Player.HUMAN.toString(), new String[0], "")
                    : getLink(name, "end", new String[]{"AI"}, "");
        };
    }

    /**
     * Action set the player whose turn is first in the game.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionSetFirstPlayer() {
        return (params) -> {
            final View view = views.get("ViewOptionChanged");
            Player player;
            try {
                player = Player.valueOf(params[0]);
            } catch (IllegalArgumentException e) {
                view.put("message_error", "The player type not found: " + params[0] + "!");
                return view.showError();
            }
            game.setFirstPlayer(player);
            view.put("title", "Change success. First player: " + player);
            return view.show();
        };
    }

    /**
     * Action set size for {@code Board}.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionSetSize() {
        return (params) -> {
            final View view = views.get("ViewOptionChanged");
            int size;
            try {
                size = Integer.parseInt(params[0]);
            } catch (NumberFormatException e) {
                view.put("message_error", "Size not the number: " + params[0] + "!");
                return view.showError();
            }
            try {
                game.setBoardSize(size, size);
            } catch (NumberFormatException e) {
                view.put("message_error", e);
                return view.showError();
            }
                        view.put("title", "Change success. Board size: " + size + "x" + size);
            return view.show();
        };
    }

    /**
     * Action set continuous sequence of identical marks required to win.
     *
     * @return answer from action.
     */
    @Contract(pure = true)
    private @NotNull Function<String[], String> actionSetWinLength() {
        return (params) -> {
            final View view = views.get("ViewOptionChanged");
            int chainLength;
            try {
                chainLength = Integer.parseInt(params[0]);
            } catch (NumberFormatException e) {
                view.put("message_error", "Chain length not the number: " + params[0] + "!");
                return view.showError();
            }
            game.setWinLength(chainLength);
            view.put("title", "Change success. Win chain length: " + chainLength);
            return view.show();
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