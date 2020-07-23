package ru.job4j.tictactoe;

import ru.job4j.tictactoe.presenters.Presenter;
import ru.job4j.tictactoe.services.router.usecase.Router;
import ru.job4j.tictactoe.views.View;

import java.io.IOException;

/**
 * Main application class includes initialization and the main program cycle.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class Application {


    /**
     * Application presenters.
     */
    private final PresenterProvider presenters;


    /**
     * Application router.
     */
    private final Router router;


    /**
     * Application initialization.
     *
     * @param startLink init.
     */
    public Application(String startLink) {
        this.presenters = new PresenterProvider();
        this.router = new ServiceProvider().getRouter();
        router.setLink(startLink);
    }

    /**
     * Check can execute.
     *
     * @return true if can execute.
     */
    public boolean canExecute() {
        return presenters.get(router.getPresenterId()) != null;
    }


    /**
     * Basic application method for the main program cycle.
     */
    public void execute() {
        Presenter presenter = presenters.get(router.getPresenterId());
        String newLink = presenter.execute(router.getMethodName(), router.getParams());
        router.setLink(newLink);
    }

    /**
     * Finalize application data.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException {
        View.READER.close();
    }

    /**
     * Application entry point.
     *
     * @param args init.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        Application app = new Application("default");
        while (app.canExecute()) {
            app.execute();
        }
        app.close();
    }
}