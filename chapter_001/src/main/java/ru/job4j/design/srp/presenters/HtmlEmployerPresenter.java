package ru.job4j.design.srp.presenters;

import ru.job4j.design.srp.model.Employer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Html template for employer report.
 */
public class HtmlEmployerPresenter implements Presenter {
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
        rsl.add("<!doctype html>");
        rsl.add("<html lang=\"en\">");
        rsl.add("<head>");
        rsl.add("\t<meta charset=\"utf-8\">");
        rsl.add("\t<title>Employer report</title>");
        rsl.add("</head>");
        rsl.add("<body>");
        for (Employer employer : data) {
            for (Map.Entry<String, String> fieldSet : fieldsSet.entrySet()) {
                String methodName = "get" + fieldSet.getKey();
                String formatString = fieldSet.getValue();
                String fieldString = String.format(formatString, Employer.class.getMethod(methodName).invoke(employer));
                rsl.add(String.format("\t<p>%s : %s<p>", fieldSet.getKey(), fieldString));
            }
        }
        rsl.add("</body>");
        rsl.add("</html>");
        return rsl.toString();
    }
}
