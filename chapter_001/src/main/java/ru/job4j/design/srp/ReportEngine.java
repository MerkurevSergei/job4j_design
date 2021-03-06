package ru.job4j.design.srp;

import ru.job4j.design.srp.model.Employer;
import ru.job4j.design.srp.model.Store;
import ru.job4j.design.srp.presenters.Presenter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Predicate;

/**
 * Application.
 */
public class ReportEngine {
    /**
     * Store
     */
    private Store store;

    /**
     * Constructor set store.
     * @param store - Store
     */
    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Generate old report.
     *
     * @param filter - implements functional interface Predicate
     * @return - report String result
     */
    public String generate(Predicate<Employer> filter) {
        StringBuilder text = new StringBuilder();
        for (Employer employer : store.findBy(filter)) {
            text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
            text.append(employer.getName()).append(";")
                    .append(employer.getHired()).append(";")
                    .append(employer.getFired()).append(";")
                    .append(employer.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    /**
     * Generate new report.
     *
     * @param request - processed user request
     * @return - report String result
     * @throws NoSuchMethodException - exception
     * @throws IllegalAccessException - exception
     * @throws InvocationTargetException - exception
     */
    public String generate(Request<Employer> request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Employer> data = store.execute(request.getFilter(), request.getSorter());
        Presenter presenter = request.getPresenter();
        return presenter.execute(data, request.getFieldsSet());
    }
}