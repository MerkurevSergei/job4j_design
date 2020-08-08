package ru.job4j.cachenb;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The {@code Cache} stored data.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
@ThreadSafe
public final class Cache {

    private final ConcurrentHashMap<Integer, Base> data;
    private final AtomicReference<Exception> ex = new AtomicReference<>();

    /**
     * Constructor.
     */
    public Cache() {
        this.data = new ConcurrentHashMap<>();
    }

    /**
     * @param model add to cache.
     */
    public void add(Base model) {
        data.putIfAbsent(model.getId(), model);
    }

    /**
     * @param model update in cache.
     */
    public void update(Base model) {
        data.computeIfPresent(model.getId(), (k, m) -> {
            if (m.getVersion() + 1 != model.getVersion()) {
                ex.set(new OptimisticException());
                return m;
            }
            return model;
        });
    }

    /**
     * @param model delete from cache.
     */
    public void delete(Base model) {
        data.remove(model.getId(), model);
    }

    /**
     * @param id for search.
     * @return {@code Base}
     */
    public Base get(int id) {
        return data.get(id);
    }

    /**
     * @return {@code true} if exception happens.
     */
    public boolean withException() {
        return (ex.get() != null);
    }
}
