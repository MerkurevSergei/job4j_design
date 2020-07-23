package ru.job4j.tictactoe.services.router.entites;

/**
 * The {@code RedirectLinkImpl} implements {@code RedirectLink}
 * interface for access to request elements.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public final class RedirectRouteImpl implements RedirectRoute {

    /**
     * Base link.
     */
    private final String source;

    /**
     * Redirected link.
     */
    private final String destination;

    /**
     * @param source init.
     * @param destination init.
     */
    public RedirectRouteImpl(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * @return base link.
     */
    @Override
    public String getSourceLink() {
        return source;
    }

    /**
     * @return redirected link.
     */
    @Override
    public String getDestinationLink() {
        return destination;
    }
}