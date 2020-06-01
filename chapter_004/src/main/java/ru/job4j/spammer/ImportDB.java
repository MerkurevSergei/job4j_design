package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ImportDB parse file with format {name;email} and write to database.
 * Exercise 4.3. XML XSLT JDBC Оптимизация [#256433]
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ImportDB {

    /**
     * File with database properties.
     */
    private final Properties cfg;

    /**
     * Output file path.
     */
    private final String dump;

    /**
     * @param cfg init
     * @param dump init
     */
    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Load data from file.
     *
     * @return Users
     * @throws IOException exception
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> {
                final String[] data = line.split(";");
                if (data.length > 1) {
                    users.add(new User(data[0].trim(), data[1].trim()));
                }
            });
        }
        return users;
    }

    /**
     * Save users to database.
     *
     * @param users list
     * @throws ClassNotFoundException exception
     * @throws SQLException exception
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    /**
     * User - helper class.
     */
    private static class User {
        /**
         * user name.
         */
        String name;

        /**
         * user e-mail.
         */
        String email;

        /**
         * @param name init
         * @param email init
         */
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    /**
     * Entry point
     * @param args command line arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./chapter_004/src/main/resources/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./chapter_004/src/main/resources/dump.txt");
        db.save(db.load());
    }
}