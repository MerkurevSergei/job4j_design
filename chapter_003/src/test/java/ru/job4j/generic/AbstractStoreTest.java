package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {

    @Test
    public void whenAddSuccess() {
        final Role expected = new Role("Q");

        final AbstractStore<Role> store = new RoleStore(2);
        store.add(expected);

        assertThat(expected, is(store.findById("Q")));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddThrowArrayIndexOutOfBounds() {
        final AbstractStore<Role> store = new RoleStore(0);
        store.add(new Role("Q"));
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddThrowIllegalArgument() {
        final AbstractStore<Role> store = new RoleStore(0);
        store.add(null);
        fail();
    }

    @Test
    public void whenReplaceSuccess() {
        final Role expected = new Role("Q");

        final AbstractStore<Role> store = new RoleStore(2);
        store.add(new Role("1"));
        store.replace("1", expected);

        assertThat(expected, is(store.findById("Q")));
        assertNull(store.findById("1"));
    }

    @Test
    public void whenReplaceFailed() {
        final AbstractStore<Role> store = new RoleStore(2);
        store.add(new Role("Q"));
        assertFalse(store.replace("Q", null));
        assertFalse(store.replace("NotSuchID", new Role("0")));
    }

    @Test
    public void whenDeleteSuccess() {
        final AbstractStore<User> store = new UserStore(2);
        store.add(new User("Q"));
        store.add(new User("1"));
        assertTrue(store.delete("Q"));
        assertNull(store.findById("Q"));
        assertNotNull(store.findById("1"));
    }

    @Test
    public void iterator() {
    }
}