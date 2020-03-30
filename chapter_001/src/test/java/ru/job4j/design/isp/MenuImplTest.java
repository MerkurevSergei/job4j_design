package ru.job4j.design.isp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MenuImplTest {
    private Menu menu;
    private Command commandFalse;
    @Before
    public void setUp() {
        commandFalse = () -> false;
        menu = new MenuImpl("", "Main menu");
        menu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        menu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        menu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        menu.addNode(new NodeImpl("2", "Second", commandFalse), "");
    }

    @Test
    public void whenAddNodeSuccess() {
        menu.addNode(new NodeImpl("2.1", "Sub Second 2", Command.EMPTY), "");

        Menu expectedMenu = new MenuImpl("", "Main menu");
        expectedMenu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        expectedMenu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("2", "Second", commandFalse), "");
        expectedMenu.addNode(new NodeImpl("2.1", "Sub Second 2", Command.EMPTY), "");

        List<Node> expectedList = expectedMenu.toList();
        List<Node> list = menu.toList();
        assertThat(expectedList, is(list));
    }

    @Test
    public void whenAddNodeFailed() {
        menu.addNode(new NodeImpl("2.1", "Sub Second 2", Command.EMPTY), "3");

        Menu expectedMenu = new MenuImpl("", "Main menu");
        expectedMenu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        expectedMenu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("2", "Second", commandFalse), "");

        List<Node> expectedList = expectedMenu.toList();
        List<Node> list = menu.toList();
        assertThat(expectedList, is(list));
    }


    @Test
    public void whenFindNodeSuccess() {
        Node rsl = menu.findNode("1");

        Menu expectedMenu = new MenuImpl("", "Main menu");
        Node expectedNode = new NodeImpl("1", "First", Command.EMPTY);
        expectedMenu.addNode(expectedNode, "");
        expectedMenu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("2", "Second", Command.EMPTY), "");
        assertThat(expectedNode, is(rsl));
    }

    @Test
    public void whenFindNodeFailed() {
        Node rsl = menu.findNode("3");
        assertThat(Node.EMPTY, is(rsl));
    }


    @Test
    public void whenIteratorHasNextTrue() {
        MenuIterator rsl = menu.iterator();

        Menu expectedMenu = new MenuImpl("", "Main menu");
        expectedMenu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        expectedMenu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("2", "Second", Command.EMPTY), "");

        assertThat(expectedMenu.iterator().hasNext(), is(rsl.hasNext()));
    }

    @Test
    public void whenIteratorHasNextFalse() {
        MenuIterator rsl = menu.iterator();

        Menu expectedMenu = new MenuImpl("", "Main menu");
        expectedMenu.addNode(new NodeImpl("1", "First", Command.EMPTY), "");
        expectedMenu.addNode(new NodeImpl("1.1", "Sub First 1", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("1.2", "Sub First 2", Command.EMPTY), "1");
        expectedMenu.addNode(new NodeImpl("2", "Second", Command.EMPTY), "");
        MenuIterator expectedIterator = expectedMenu.iterator();

        while (rsl.hasNext()) {
            rsl.next();
            expectedIterator.next();
        }
        assertThat(expectedIterator.hasNext(), is(false));
    }

    @Test
    public void whenIteratorReset() {
        MenuIterator rsl = menu.iterator();
        rsl.reset();

        assertThat("", is(rsl.next().getId()));
    }

    @Test
    public void whenExecuteTrue() {
        assertTrue(menu.findNode("").execute());
    }

    @Test
    public void whenExecuteFalse() {
        assertFalse(menu.findNode("2").execute());
    }
}