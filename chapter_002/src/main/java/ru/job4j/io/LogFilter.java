package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Log filter frim file
 */
public class LogFilter {
    /**
     * @param file - path to file
     * @return - filtering result
     */
    public static List<String> filter(String file) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines().filter((s -> s.matches("(.*)404( \\S+)$"))).forEach(strings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    /**
     * @param log - log messages
     * @param file - output file
     */
    public static void save(List<String> log, String file) {
        try (final PrintWriter printWriter = new PrintWriter(file)) {
            for (String message : log) {
                printWriter.write(message);
                printWriter.write(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String read(String file) {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (final BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/404.txt"))) {
            reader.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * @param args - console arguments
     */
    public static void main(String[] args) {
        List<String> log = filter("chapter_002/src/main/resources/log.txt");
        save(log, "chapter_002/src/main/resources/404.txt");
    }
}