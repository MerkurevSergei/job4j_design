package ru.job4j.concurrent.wget;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The options stored.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class Options {

    /**
     * Url to file.
     */
    private final URL url;

    /**
     * Download speed, Kb/s.
     */
    private final int speed;

    /**
     * Initialize url and speed.
     * arg[0] - url.
     * arg[1] - speed, Kb/s.
     * @param args store.
     */
    public Options(final String[] args) throws IllegalArgumentException {
        checkArgs(args);
        try {
            this.url = new URL(args[0]);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("URL invalid!");
        }

        try {
            this.speed = Integer.parseInt(args[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Download speed not the number!");
        }
    }

    /**
     * @return url to file.
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @return download speed, byte/ms.
     */
    public int getSpeedBMs() {
        return 1024 * speed / 1000;
    }

    /**
     * @param args must have length = 2.
     * @throws IllegalArgumentException if length != 2.
     */
    private void checkArgs(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("More or less arguments then 2!");
        }
    }
}
