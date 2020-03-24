package ru.job4j.design.srp;

import ru.job4j.design.srp.presenters.Presenter;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Consist parameters from user request.
 * @param <T> - business entity, data for report
 */
public class Request<T> {
    private Presenter presenter;
    private Predicate<T> filter;
    private Comparator<T> sorter;
    private Map<String, String> fieldsSet;

    /**
     * @param presenter - presenter implementation
     * @param filter - filter function
     * @param sorter - sort function
     * @param fieldsSet - set of matches [field; formatter string]
     */
    public Request(Presenter presenter, Predicate<T> filter, Comparator<T> sorter, Map<String, String> fieldsSet) {
        this.presenter = presenter;
        this.filter = filter;
        this.sorter = sorter;
        this.fieldsSet = fieldsSet;
    }

    /**
     * Presenter getter.
     * @return presenter implementation
     */
    public Presenter getPresenter() {
        return presenter;
    }

    /**
     * Filter function.
     * @return Functional interface Predicate implementation
     */
    public Predicate<T> getFilter() {
        return filter;
    }

    /**
     * Sort function.
     * @return Functional interface Comparator implementation
     */
    public Comparator<T> getSorter() {
        return sorter;
    }

    /**
     * Getter set of matches [field; formatter string].
     * @return Map of matches [field; formatter string]
     */
    public Map<String, String> getFieldsSet() {
        return fieldsSet;
    }
}
