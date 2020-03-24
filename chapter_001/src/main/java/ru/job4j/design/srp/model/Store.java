package ru.job4j.design.srp.model;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
public interface Store {
    void add(Employer employer);
    List<Employer> findBy(Predicate<Employer> filter);
    List<Employer> execute(Predicate<Employer> filter, Comparator<Employer> sorter);
}