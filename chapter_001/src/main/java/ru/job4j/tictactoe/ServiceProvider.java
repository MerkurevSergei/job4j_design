package ru.job4j.tictactoe;

import ru.job4j.tictactoe.services.router.usecase.Router;
import ru.job4j.tictactoe.services.router.usecase.RouterImpl;

/**
 * Initializes {@code Router} for the application.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
class ServiceProvider {

    /**
     * @return {@code Router}
     */
    Router getRouter() {
        Router routes = new RouterImpl();
        routes.addRedirect("menu/show/010", "menu/show/0");
        routes.addRedirect("menu/show/011", "game/setPlayer/AI");
        routes.addRedirect("menu/show/012", "game/setPlayer/HUMAN");
        routes.addRedirect("menu/show/020", "menu/show/0");
        routes.addRedirect("menu/show/021", "game/setSize/3");
        routes.addRedirect("menu/show/022", "game/setSize/5");
        routes.addRedirect("menu/show/030", "menu/show/0");
        routes.addRedirect("menu/show/031", "game/setChain/3");
        routes.addRedirect("menu/show/032", "game/setChain/5");
        routes.addRedirect("menu/show/04", "game/start");
        routes.addRedirect("menu/show/05", "exit/exit");
        routes.addRedirect("default", "menu/show/0");
        return routes;
    }
}