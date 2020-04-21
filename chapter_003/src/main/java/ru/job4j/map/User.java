package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

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

    /**
     * @param o - any object
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
