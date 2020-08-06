package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;
import ru.job4j.list.SimpleArray;

import java.util.Iterator;

@ThreadSafe
public final class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("this")
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public synchronized void add(T value) {
        simpleArray.add(value);
    }

    public synchronized T get(int index) {
        return simpleArray.get(index);
    }

    @Override
    public synchronized @NotNull Iterator<T> iterator() {
        return copy(this.simpleArray).iterator();
    }

    private Iterable<T> copy(SimpleArray<T> simpleArray) {
        final SimpleArray<T> nSimpleArray = new SimpleArray<>();
        for (T next : simpleArray) {
            nSimpleArray.add(next);
        }
        return nSimpleArray;
    }
}
