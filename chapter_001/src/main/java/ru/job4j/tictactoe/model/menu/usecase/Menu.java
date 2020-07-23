package ru.job4j.tictactoe.model.menu.usecase;

import ru.job4j.tictactoe.model.menu.entities.Node;

/**
 * The {@code Menu} provide interface for the Tic-tac-toe game menu.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface Menu {

    /**
     * @param id for the search.
     * @return node with searched id or null.
     */
    Node findById(String id);
}