package ru.job4j.design.isp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Paint menu implements.
 */
public class MenuPainter {
    /**
     * menu object.
     */
    Menu menu;
    /**
     * console reader.
     */
    BufferedReader reader;

    /**
     * @param menu - object menu implements
     */
    public MenuPainter(Menu menu) {
        this.menu = menu;
    }

    /**
     * @return menu view
     */
    private String show() {
        String rsl = "";
        MenuIterator iterator = menu.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.print("Enter id: ");
        try {
            rsl = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rsl;
    }

    /**
     * Execute node command.
     * @param id - node id from execute node command
     * @return - result of execute
     */
    private boolean execute(String id) {
        Node node = menu.findNode(id);
        boolean rsl = node.execute();
        System.out.println("Press enter to continue...");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Start menu painter.
     */
    public void run() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        boolean rsl = true;
        while (rsl) {
            rsl = execute(show());
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Demonstrate method main.
     * @param args - command line args
     */
    public static void main(String[] args) {
        Menu menu = new MenuImpl("", "Main menu");
        menu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");

        menu.addNode(new NodeImpl("1.1", "Sub First 1", () -> {
            System.out.println("do Sub First 1");
            return true;
        }), "1");
        menu.addNode(new NodeImpl("1.2", "Sub First 2", () -> {
            System.out.println("do Sub First 2");
            return true;
        }), "1");
        menu.addNode(new NodeImpl("2", "Second", () -> false), "");
        MenuPainter mp = new MenuPainter(menu);
        mp.run();
    }
}
