package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenIteratorNext() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchAfterInit() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        Iterator<Integer> it = linked.iterator();
        it.next();
        fail();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoSuchAfterNext() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        Iterator<Integer> it = linked.iterator();
        it.next();
        it.next();
        fail();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenThrowConcurrentModificationException() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        Iterator<Integer> it = linked.iterator();
        linked.add(1);
        it.next();
        fail();
    }


    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}