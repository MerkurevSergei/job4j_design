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

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            values.putAll(
                    reader
                            .lines()
                            .filter(s -> s.split("=").length >= 2)
                            .map((s -> s.split("=")))
                            .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, "");
    }

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

    public static void main(String[] args) {
        System.out.println(new Config("chapter_002/src/main/resources/app.properties"));
    }
}