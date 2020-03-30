package ru.job4j.design.isp;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuPainterTest {

    private Menu menu;

    @Before
    public void setUp() {
        menu = new MenuImpl("", "Main menu");
        menu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        menu.addNode(new NodeImpl("1.1", "Sub First 1", () -> {
            System.out.println("do 1.1");
            return true;
        }), "1");
        menu.addNode(new NodeImpl("1.2", "Sub First 2", () -> {
            System.out.println("do 1.2");
            return true;
        }), "1");
        menu.addNode(new NodeImpl("2", "Second", () -> {
            System.out.println("exit program");
            return false;
        }), "");
    }

    @Test
    public void show() {
        PrintStream out = System.out;
        InputStream in = System.in;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        String data = "1.1" + '\n'
                + '\n'
                + "1.2" + '\n'
                + '\n'
                + "2" + '\n';

        System.setOut(new PrintStream(byteArrayOutputStream));
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        MenuPainter mp = new MenuPainter(menu);
        mp.run();

        System.setOut(out);
        System.setIn(in);

        StringJoiner expected = new StringJoiner(System.lineSeparator());
        expected.add("Main menu").add("1 - First").add("1.1 - Sub First 1").add("1.2 - Sub First 2").add("2 - Second")
                .add("Enter id: do 1.1")
                .add("Press enter to continue...")
                .add("Main menu").add("1 - First").add("1.1 - Sub First 1").add("1.2 - Sub First 2").add("2 - Second")
                .add("Enter id: do 1.2")
                .add("Press enter to continue...")
                .add("Main menu").add("1 - First").add("1.1 - Sub First 1").add("1.2 - Sub First 2").add("2 - Second")
                .add("Enter id: exit program")
                .add("Press enter to continue...").add("");
        assertThat(expected.toString(), is(byteArrayOutputStream.toString()));
    }

}