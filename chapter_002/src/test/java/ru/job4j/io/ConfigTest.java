package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/test/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }

    @Test
    public void whenLoadParameters() {
        String expected = "{hibernate.connection.driver_class=org.postgresql.Driver, "
                + "hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio, "
                + "hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect, name=Petr Arsentev, "
                + "hibernate.connection.password=password, "
                + "hibernate.connection.username=postgres}";

        String path = "src/test/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                expected,
                is(config.getValues().toString())
        );
    }
}