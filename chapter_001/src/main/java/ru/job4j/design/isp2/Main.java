package ru.job4j.design.isp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main class.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class Main {

    /**
     * The Menu instance.
     */
    final private Menu menu;

    /**
     * The entry point.
     */
    public Main() {
        this.menu = new MenuImpl(new ItemImpl(null, "Main menu", () -> true));
    }

    /**
     * @return filled menu.
     */
    public Main fill() {

        menu.add(new ItemImpl("1", "Point 1", () -> {
            System.out.println("It's point 1");
            return true;
        }));
        menu.add(new ItemImpl("2", "Point 2", () -> {
            System.out.println("It's point 2");
            return true;
        }));
        menu.add(new ItemImpl("2.1", "Point 2.1", () -> {
            System.out.println("It's point 2.1");
            return true;
        }), "2");
        menu.add(new ItemImpl("3", "Exit", () -> {
            System.out.println("Exit");
            return false;
        }));
        return this;
    }

    /**
     * Run menu program.
     */
    public void run() {
        boolean rsl = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (rsl) {
                final List<Item> items = menu.items();
                for (Item item : items) {
                    System.out.println(item);
                }
                System.out.print("Enter id: ");
                final Item item = menu.choose(reader.readLine());
                rsl = item.getCommand().get();
                System.out.println("Press enter to continue...");
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().fill().run();
    }

}
