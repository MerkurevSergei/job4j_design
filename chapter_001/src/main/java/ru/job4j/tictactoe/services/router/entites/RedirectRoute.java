package ru.job4j.tictactoe.services.router.entites;

/**
 * The {@code RedirectLink} provide interface for access to request elements.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface RedirectRoute {

    /**
     * @return base link.
     */
    String getSourceLink();

    /**
     * @return redirected link.
     */
    String getDestinationLink();
}