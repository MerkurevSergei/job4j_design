package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {
    @Test
    public void whenTwoUserAddToMap() {
        User user1 = new User("User1", 5, new GregorianCalendar(2015, Calendar.JANUARY, 1));
        User user2 = new User("User1", 5, new GregorianCalendar(2015, Calendar.JANUARY, 1));
        User user3 = new User("User3", 10, new GregorianCalendar(2010, Calendar.JANUARY, 1));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.put(user3, new Object());
        System.out.println(map);
    }
}