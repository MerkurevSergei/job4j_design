package ru.job4j.design.srp.presenters;

import ru.job4j.design.srp.model.Employer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * JSON template for employer report.
 */
public class JSONEmployerPresenter implements Presenter {
    /**
     * @param data - employers for present
     * @param fieldsSet - fields and fields format
     * @return String - report
     * @throws NoSuchMethodException - exception
     * @throws InvocationTargetException - exception
     * @throws IllegalAccessException - exception
     */
    @Override
    public String execute(List<Employer> data, Map<String, String> fieldsSet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        rsl.add("{")
                .add("\t\"report\": {")
                .add("\t\t\"title\": \"Employer report\"");
        for (Employer employer : data) {
            rsl.add("\t\t\"node\": {");
            for (Map.Entry<String, String> fieldSet : fieldsSet.entrySet()) {
                String methodName = "get" + fieldSet.getKey();
                String formatString = fieldSet.getValue();
                String fieldString = String.format(formatString, Employer.class.getMethod(methodName).invoke(employer));
                rsl.add(String.format("\t\t\t\"%s\": \"%s\"", fieldSet.getKey(), fieldString));
            }
            rsl.add("\t\t}");
        }
        rsl.add("\t}")
                .add("}");
        return rsl.toString();
    }
}
