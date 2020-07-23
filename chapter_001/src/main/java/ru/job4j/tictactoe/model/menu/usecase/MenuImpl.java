package ru.job4j.tictactoe.model.menu.usecase;

import org.jetbrains.annotations.NotNull;
import ru.job4j.tictactoe.model.menu.entities.Node;

import java.util.Optional;

/**
 * The {@code MenuImpl} implements {@code Menu} interface
 * for the Tic-tac-toe game menu.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public final class MenuImpl implements Menu {

    /**
     * The top node menu.
     */
    private final Node top;

    /**
     * @param top init.
     */
    public MenuImpl(Node top) {
        this.top = top;
    }

    /**
     * @param id for the search.
     * @return node with searched id or null.
     */
    @Override
    public Node findById(String id) {
        if (Optional.of(id).get().equals(top.getId())) {
            return top;
        }
        return findById(top, id);
    }

    /**
     * @param top node submenu.
     * @param id for search.
     * @return node with searched id or null.
     */
    private Node findById(@NotNull Node top, String id) {
        Node result = null;
        for (Node n : top.getChildren()) {
            result = findById(n, id);
            if (result != null) {
                break;
            }
            if (id.equals(n.getId())) {
                result = n;
                break;
            }
        }
        return result;
    }
}