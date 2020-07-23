package ru.job4j.tictactoe.services.router.entites;

import org.jetbrains.annotations.NotNull;

/**
 * The {@code RequestImpl} implements {@code Request},
 * provide access to request parts.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public class RequestImpl implements Request {

    /**
     * Presenter id aka name.
     */
    private final String presenter;

    /**
     * Method name called by the presenter.
     */
    private final String method;

    /**
     * Parameters for method called by the presenter.
     */
    private final String[] params;

    /**
     * @param request is init string.
     */
    public RequestImpl(@NotNull String request) {
        String[] tmp = request.split("/");
        this.presenter = tmp[0];
        this.method = tmp[1];
        this.params = new String[tmp.length - 2];
        System.arraycopy(tmp, 2, this.params, 0, tmp.length - 2);
    }

    /**
     * @return presenter id aka name.
     */
    @Override
    public String getPresenterId() {
        return presenter;
    }

    /**
     * @return method name called by the presenter.
     */
    @Override
    public String getMethodName() {
        return method;
    }

    /**
     * @return parameters for method called by the presenter.
     */
    @Override
    public String[] getParams() {
        return params;
    }
}