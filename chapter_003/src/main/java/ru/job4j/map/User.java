package ru.job4j.map;

import java.util.Calendar;

/**
 * User model.
 */
public class User {

    /**
     * User name.
     */
    private final String name;

    /**
     * User children.
     */
    private final int children;

    /**
     * User birthday;
     */
    private final Calendar birthday;

    /**
     * @param name - to be init user name
     * @param children - to be init user children
     * @param birthday - to be init user birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
