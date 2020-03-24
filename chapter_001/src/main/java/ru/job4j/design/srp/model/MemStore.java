package ru.job4j.design.srp.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
public class MemStore implements Store {

    private final List<Employer> employers = new ArrayList<>();

    @Override
    public void add(Employer em) {
        employers.add(em);
    }

    @Override
    public List<Employer> findBy(Predicate<Employer> filter) {
        return employers.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Employer> execute(Predicate<Employer> filter, Comparator<Employer> sorter) {
        return employers.stream().filter(filter).sorted(sorter).collect(Collectors.toList());
    }
}