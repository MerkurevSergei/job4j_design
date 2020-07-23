package ru.job4j.tictactoe.views;

import ru.job4j.tictactoe.model.game.entities.Player;
import ru.job4j.tictactoe.model.game.usecase.Game;

import java.util.HashMap;
import java.util.Map;

import static ru.job4j.tictactoe.model.game.entities.Player.*;

public class Game2DEndView implements View {

    /**
     * Representation user mark.
     */
    Map<Player, String> playerViews = Map.of(HUMAN, "0", AI, "X", NOBODY, "_");

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
        final Game game = (Game) content.get("game");
        int[] size = game.getBoardSize();
        int n = size[0];
        int m = size[1];
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();

        System.out.print("  -");
        for (int i = 0; i < n; i++) {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            System.out.printf("%d: ", i);
            for (int j = 0; j < n; j++) {
                System.out.printf("%s ", playerViews.get(game.getMark(i, j)));
            }
            System.out.println();
        }
        System.out.println("***********************");
        System.out.println("WIN " + game.getWinner() + "!!!");
        System.out.println("Press ENTER to menu...");
        System.out.print("***********************");
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
        String msg = (String) content.get("message_error");
        System.out.println(msg);
        System.out.print("Press ENTER to continue...");
        ask();
        System.out.println();
        return null;
    }
}
