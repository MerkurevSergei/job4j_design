package ru.job4j.design.srp;

import ru.job4j.design.srp.model.Employer;
import ru.job4j.design.srp.presenters.*;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Request patterns generator
 * create patterns from departments
 */
public class RequestPatterns {

    /**
     * Generate pattern from simple department
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerSimpleReport() {
        Presenter presenter = new SimpleEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from programmers department
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerReportForProgrammers() {
        Presenter presenter = new HtmlEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%.1f");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from buh department
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerSimpleReportForBuh() {
        Presenter presenter = new SimpleEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%.2f");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from HR department
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerSimpleReportForHR() {
        Presenter presenter = new SimpleEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = Comparator.comparingDouble(Employer::getSalary).reversed();
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from all XML
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerXMLReport() {
        Presenter presenter = new XMLEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from all JSON
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> employerJSONReport() {
        Presenter presenter = new JSONEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern Invalid request, invalid field
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> invalidRequestInvalidField() {
        Presenter presenter = new SimpleEmployerPresenter();
        Predicate<Employer> predicate = (em -> true);
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("InvalidField", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }

    /**
     * Generate pattern from HR department
     * type - employer
     *
     * @return pattern
     */
    public static Request<Employer> invalidRequestInvalidFunctionalInterface() {
        Presenter presenter = new SimpleEmployerPresenter();
        Predicate<Employer> predicate = null;
        Comparator<Employer> comparator = (e1, e2) -> 0;
        Map<String, String> fieldsSet = new LinkedHashMap<>();
        fieldsSet.put("Name", "%s");
        fieldsSet.put("Hired", "%s");
        fieldsSet.put("Fired", "%s");
        fieldsSet.put("Salary", "%s");
        return new Request<>(
                presenter,
                predicate,
                comparator,
                fieldsSet
        );
    }
}
