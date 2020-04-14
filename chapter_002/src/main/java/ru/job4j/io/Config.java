package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Config manager.
 */
public class Config {
    /**
     * path to config file
     */
    private final String path;
    /**
     * values
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * @param path - path to config file
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * load parameters from config file
     */
    public void load() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            values.putAll(
                    reader
                            .lines()
                            .filter(s -> !s.startsWith("#") && s.split("=").length >= 2)
                            .collect(Collectors.toMap(
                                    s -> s.split("=")[0], s -> s.split("=")[1]))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param key - parameter key
     * @return - parameter
     */
    public String value(String key) {
        return values.getOrDefault(key, "");
    }

    /**
     * @return all parameters
     */
    public Map<String, String> getValues() {
        return values;
    }

    /**
     * @return string
     */
    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        System.out.println(new Config("chapter_002/src/main/resources/app.properties"));
    }
}