package ru.job4j.tictactoe.services.router.usecase;

/**
 * The {@code Router} provides an interface for routing in the application.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface Router {

    /**
     * Add redirected route.
     *
     * @param source  incoming link.
     * @param destination link.
     */
    void addRedirect(String source, String destination);

    /**
     * Set the link for making the request.
     *
     * @param link for request.
     */
    void setLink(String link);

    /**
     * Get presenter id from the specified link.
     *
     * @return presenter id from the specified link.
     */
    String getPresenterId();

    /**
     * Get method name from the specified link.
     *
     * @return method name from the specified link.
     */
    String getMethodName();

    /**
     * Get parameters from the specified link.
     *
     * @return parameters from the specified link.
     */
    String[] getParams();
}
