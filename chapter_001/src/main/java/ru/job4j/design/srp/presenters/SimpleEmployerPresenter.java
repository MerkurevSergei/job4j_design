package ru.job4j.design.srp.presenters;

import ru.job4j.design.srp.model.Employer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class SimpleEmployerPresenter implements Presenter {

    @Override
    public String execute(List<Employer> data, Map<String, String> fieldsSet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        for (Employer employer : data) {
            StringJoiner header = new StringJoiner("; ");
            StringJoiner body = new StringJoiner(";");
            for (Map.Entry<String, String> fieldSet : fieldsSet.entrySet()) {
                header.add(fieldSet.getKey());
                String methodName = "get" + fieldSet.getKey();
                String formatString = fieldSet.getValue();
                String fieldString = String.format(formatString, Employer.class.getMethod(methodName).invoke(employer));
                body.add(fieldString);
            }
            rsl.add(header.toString());
            rsl.add(body.toString());
        }
        return rsl.toString();
    }
}
