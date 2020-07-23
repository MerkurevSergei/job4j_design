package ru.job4j.tictactoe.services.router.usecase;


import ru.job4j.tictactoe.services.router.entites.RedirectRoute;
import ru.job4j.tictactoe.services.router.entites.RedirectRouteImpl;
import ru.job4j.tictactoe.services.router.entites.Request;
import ru.job4j.tictactoe.services.router.entites.RequestImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The {@code RouterImpl} implements {@code Router} for routing in the application.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.2
 * @since 0.1
 */
public class RouterImpl implements Router {
    private String prevRoute;
    private String currentRoute;
    private Request request;
    private final List<RedirectRoute> redirectRoutes;

    public RouterImpl() {
        this.redirectRoutes = new LinkedList<>();
    }

    /**
     * Add redirected route.
     *
     * @param source         incoming.
     * @param destination redirected.
     */
    @Override
    public void addRedirect(String source, String destination) {
        redirectRoutes.add(new RedirectRouteImpl(source, destination));
    }

    /**
     * Set the link for making the request.
     *
     * @param link for request.
     */
    @Override
    public void setLink(String link) {
        String route = resolve(link);
        prevRoute = (route == null) ? prevRoute : currentRoute;
        currentRoute = (route == null) ? prevRoute : route;
        request = new RequestImpl(currentRoute);
    }

    /**
     * Get presenter id from the specified link.
     *
     * @return presenter id from the specified link.
     */
    @Override
    public String getPresenterId() {
        return request.getPresenterId();
    }

    /**
     * Get method name from the specified link.
     *
     * @return method name from the specified link.
     */
    @Override
    public String getMethodName() {
        return request.getMethodName();
    }

    /**
     * Get parameters from the specified link.
     *
     * @return parameters from the specified link.
     */
    @Override
    public String[] getParams() {
        return request.getParams();
    }


    /**
     * Resolve and return link redirected or not.
     *
     * @param link for resolve.
     * @return resolved link
     */
    private String resolve(String link) {
        String resultLink = link;
        if (link == null) {
            return null;
        }
        for (RedirectRoute redirectRoute : redirectRoutes) {
            if (Pattern.matches(redirectRoute.getSourceLink(), link)) {
                resultLink = redirectRoute.getDestinationLink();
                break;
            }
        }
        return resultLink;
    }

}
