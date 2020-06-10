package ru.job4j.gcdemo;

import java.util.ArrayList;

/**
 * User class for gc test
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class User {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize");
    }

    /**
     * @param args - console args
     */
    public static void main(final String[] args) {
        ArrayList<User> t = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new User(i, String.valueOf(i));
        }
        System.out.println(t);

        // Проверим пункт 2 задания - 3* (16 [размер пустого объекта] + 4 [int] + 48 [empty String] +  2 * 3 [char 3 символа]) = 222 байта
        System.out.println("Start point 1");
        long m1 = getAvailableMemory();
        System.out.println(m1);
        User a1 = new User(1, "qwe");
        User h1 = new User(2, "asd");
        User hw1 = new User(3, "ert");
        System.out.println(a1.toString() + h1 + hw1);
        System.out.println(m1 - getAvailableMemory());
        // 3 105 256 - 55 322 4 = 2 551 808‬ = 2 492 kb. Вывод - память используется под что-то еще
        // замер через runtime не дает представления о занятой объектами памяти
    }

    private static long getAvailableMemory() {
        Runtime runtime = Runtime.getRuntime();
        return  (runtime.totalMemory() - runtime.freeMemory());
    }
}
