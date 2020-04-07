package ru.job4j.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Analyze server log
 */
public class Analizy {
    /**
     * @param source - source log
     * @param target - target log
     */
    public void unavailable(String source, String target) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(source));
             final FileWriter fileWriter = new FileWriter(target)) {
            AtomicBoolean flag = new AtomicBoolean(false);
            reader.lines()
                    .filter(s -> s.split(" ").length > 1)
                    .filter(s -> {
                        boolean res = false;
                        if (!flag.get() && (s.startsWith("400 ") || s.startsWith("500 "))) {
                            flag.set(true);
                            res = true;
                        } else if (flag.get() && !s.startsWith("400 ") && !s.startsWith("500 ")) {
                            flag.set(false);
                            res = true;
                        }
                        return res;
                    })
                    .map(s -> s.split(" ")[1])
                    .forEach(csq -> {
                        try {
                            String separator = flag.get() ? "" : System.lineSeparator();
                            fileWriter.append(csq).append(";").append(separator);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}