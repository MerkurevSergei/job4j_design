package ru.job4j.design.srp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.srp.model.Employer;
import ru.job4j.design.srp.model.MemStore;
import ru.job4j.design.srp.model.Store;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ReportEngineTest {
    private Store store;
    private Employer[] workers = new Employer[3];

    @Before
    public void setUp() {
        store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employer worker = new Employer("Ivan", now, now, 100);
        workers[0] = worker;
        store.add(worker);
        worker = new Employer("Vasia", now, now, 300);
        workers[1] = worker;
        store.add(worker);
        worker = new Employer("Lena", now, now, 200);
        workers[2] = worker;
        store.add(worker);
    }

    @Test
    public void whenOldGenerated() {
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            expect.append("Name; Hired; Fired; Salary")
                    .append(System.lineSeparator())
                    .append(workers[i].getName()).append(";")
                    .append(workers[i].getHired()).append(";")
                    .append(workers[i].getFired()).append(";")
                    .append(workers[i].getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenSimpleReport() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner("");
        for (int i = 0; i < 3; i++) {
            expect.add("Name; Hired; Fired; Salary")
                    .add(System.lineSeparator())
                    .add(workers[i].getName()).add(";")
                    .add(workers[i].getHired().toString()).add(";")
                    .add(workers[i].getFired().toString()).add(";")
                    .add(workers[i].getSalary().toString());
            if (i < 2) {
                expect.add(System.lineSeparator());
            }
        }
        Request<Employer> request = RequestPatterns.employerSimpleReport();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test
    public void whenHtmlReportForProgrammers() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add("<!doctype html>")
                .add("<html lang=\"en\">")
                .add("<head>")
                .add("\t<meta charset=\"utf-8\">")
                .add("\t<title>Employer report</title>")
                .add("</head>")
                .add("<body>");
        for (int i = 0; i < 3; i++) {
            expect.add(String.format("\t<p>%s : %s<p>", "Name", workers[i].getName()))
                    .add(String.format("\t<p>%s : %s<p>", "Hired", workers[i].getHired()))
                    .add(String.format("\t<p>%s : %s<p>", "Fired", workers[i].getFired()))
                    .add(String.format("\t<p>%s : %.1f<p>", "Salary", workers[i].getSalary()));
        }
        expect.add("</body>")
                .add("</html>");
        Request<Employer> request = RequestPatterns.employerReportForProgrammers();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test
    public void whenSimpleReportForBuh() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner("");
        for (int i = 0; i < 3; i++) {
            expect.add("Name; Hired; Fired; Salary")
                    .add(System.lineSeparator())
                    .add(workers[i].getName()).add(";")
                    .add(workers[i].getHired().toString()).add(";")
                    .add(workers[i].getFired().toString()).add(";")
                    .add(String.format("%.2f", workers[i].getSalary()));
            if (i < 2) {
                expect.add(System.lineSeparator());
            }
        }
        Request<Employer> request = RequestPatterns.employerSimpleReportForBuh();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test
    public void whenSimpleReportForHRSorted() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner("");
        Arrays.sort(workers, Comparator.comparingDouble(Employer::getSalary));
        for (int i = 0; i < 3; i++) {
            expect.add("Name; Salary")
                    .add(System.lineSeparator())
                    .add(workers[i].getName()).add(";")
                    .add(String.format("%s", workers[i].getSalary()));
            if (i < 2) {
                expect.add(System.lineSeparator());
            }
        }
        Request<Employer> request = RequestPatterns.employerSimpleReportForHR();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test
    public void whenXMLReport() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add("<?xml version=\"1.0\" encoding=\"ISO8859-1\" ?>")
                .add("<report>")
                .add("\t<header>Employer report</header>");
        for (int i = 0; i < 3; i++) {
            expect.add("<\tnode>")
                    .add(String.format("\t\t<p>%s : %s<p>", "Name", workers[i].getName()))
                    .add(String.format("\t\t<p>%s : %s<p>", "Hired", workers[i].getHired()))
                    .add(String.format("\t\t<p>%s : %s<p>", "Fired", workers[i].getFired()))
                    .add(String.format("\t\t<p>%s : %s<p>", "Salary", workers[i].getSalary()))
                    .add("<\t/node>");
        }
        expect.add("</report>");
        Request<Employer> request = RequestPatterns.employerXMLReport();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test
    public void whenJSONReport() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add("{")
                .add("\t\"report\": {")
                .add("\t\t\"title\": \"Employer report\"");
        for (int i = 0; i < 3; i++) {
            expect.add("\t\t\"node\": {")
                    .add(String.format("\t\t\t\"%s\": \"%s\"", "Name", workers[i].getName()))
                    .add(String.format("\t\t\t\"%s\": \"%s\"", "Hired", workers[i].getHired()))
                    .add(String.format("\t\t\t\"%s\": \"%s\"", "Fired", workers[i].getFired()))
                    .add(String.format("\t\t\t\"%s\": \"%s\"", "Salary", workers[i].getSalary()))
                    .add("\t\t}");
        }
        expect.add("\t}")
                .add("}");
        Request<Employer> request = RequestPatterns.employerJSONReport();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test(expected = NoSuchMethodException.class)
    public void whenInvalidRequest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner("");
        for (int i = 0; i < 3; i++) {
            expect.add("Name; Hired; Fired; Salary")
                    .add(System.lineSeparator())
                    .add(workers[i].getName()).add(";")
                    .add(workers[i].getHired().toString()).add(";")
                    .add(workers[i].getFired().toString()).add(";")
                    .add(workers[i].getSalary().toString());
            if (i < 2) {
                expect.add(System.lineSeparator());
            }
        }
        Request<Employer> request = RequestPatterns.invalidRequestInvalidField();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }

    @Test(expected = NullPointerException.class)
    public void whenInvalidRequest2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringJoiner expect = new StringJoiner("");
        for (int i = 0; i < 3; i++) {
            expect.add("Name; Hired; Fired; Salary")
                    .add(System.lineSeparator())
                    .add(workers[i].getName()).add(";")
                    .add(workers[i].getHired().toString()).add(";")
                    .add(workers[i].getFired().toString()).add(";")
                    .add(workers[i].getSalary().toString());
            if (i < 2) {
                expect.add(System.lineSeparator());
            }
        }
        Request<Employer> request = RequestPatterns.invalidRequestInvalidFunctionalInterface();
        ReportEngine engine = new ReportEngine(store);
        assertThat(engine.generate(request), is(expect.toString()));
    }
}