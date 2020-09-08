package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");

        // Преобразуем объект person в json-строку.
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        // Модифицируем и получим из json-строки объект person.
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
