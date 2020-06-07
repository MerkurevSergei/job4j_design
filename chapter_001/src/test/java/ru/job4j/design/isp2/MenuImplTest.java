package ru.job4j.design.isp2;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MenuImplTest {

    @Test
    public void add() {
        Item top = new Item() {
            final List<Item> items = new ArrayList<>();

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public void addChild(Item item) {
                this.items.add(item);
            }

            @Override
            public Supplier<Boolean> getCommand() {
                return null;
            }

            @Override
            public List<Item> getChildren() {
                return items;
            }
        };

        final Item item = Mockito.mock(Item.class);
        Mockito.when(item.getId()).thenReturn(null);

        final MenuImpl menu = new MenuImpl(top);
        menu.add(item);
        assertEquals(2, menu.items().size());
    }

    @Test
    public void choose() {
        final Item item = Mockito.mock(Item.class);
        Mockito.when(item.getId()).thenReturn(null);
        Mockito.when(item.getName()).thenReturn("Main");

        final MenuImpl menu = new MenuImpl(item);
        assertEquals("Main", menu.choose(null).getName());
    }

    @Test
    public void items() {
        final Item item = Mockito.mock(Item.class);
        Mockito.when(item.getId()).thenReturn(null);

        final MenuImpl menu = new MenuImpl(item);
        assertFalse(menu.items().isEmpty());
    }
}