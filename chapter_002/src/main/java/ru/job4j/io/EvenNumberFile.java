package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Create data set even / odd numbers from the file.
 */
public class EvenNumberFile {
    /**
     * data set even / odd numbers
     */
    List<Pair> result = new ArrayList<>();

    /**
     * @return pairs
     */
    public List<Pair> execute() {
        String[] strings = read();
        for (String strNum : strings) {
            int anInt = Integer.parseInt(strNum);
            boolean rsl = anInt % 2 == 0;
            result.add(new Pair(anInt, rsl));
        }
        return result;
    }

    /**
     * @return strings read from the file
     */
    private String[] read() {
        String[] strings = new String[0];
        try (InputStream in = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                stringBuilder.append((char) read);
            }
            strings = stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    /**
     * Helper class
     */
    public static class Pair {
        final private int number;
        final private boolean even;

        public Pair(int number, boolean even) {
            this.number = number;
            this.even = even;
        }

        public int getNumber() {
            return number;
        }

        public boolean isEven() {
            return even;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return number == pair.number
                    && even == pair.even;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, even);
        }
    }

    /**
     * @param args - command line args
     */
    public static void main(String[] args) {
        EvenNumberFile evenNumberFile = new EvenNumberFile();
        List<Pair> pairs = evenNumberFile.execute();
        for (Pair pair : pairs) {
            System.out.println(pair.getNumber() + " : " + (pair.isEven() ? "even" : "odd"));
        }
    }
}

