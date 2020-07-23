package ru.job4j.tictactoe.services.router.entites;

/**
 * The {@code Request} provide interface for access to request elements.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public interface Request {

    /**
     * @return presenter id.
     */
    String getPresenterId();

    /**
     *
     * @return method name in the presenter.
     */
    String getMethodName();

    /**
     *
     * @return parameters for method name in the presenter.
     */
    String[] getParams();
}