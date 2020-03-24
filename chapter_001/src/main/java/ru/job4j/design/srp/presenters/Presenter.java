package ru.job4j.design.srp.presenters;

import ru.job4j.design.srp.model.Employer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Presenter interface.
 */
public interface Presenter {
    /**
     * @param data - employers for present
     * @param fieldsSet - fields and fields format
     * @return String - report
     * @throws NoSuchMethodException - exception
     * @throws InvocationTargetException - exception
     * @throws IllegalAccessException - exception
     */
    String execute(List<Employer> data, Map<String, String> fieldsSet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
