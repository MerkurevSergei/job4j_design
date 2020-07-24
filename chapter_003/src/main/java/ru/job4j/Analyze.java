package ru.job4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Analyze two list and provide information about differences between lists.
 * Suppose that two {@code User} with some id don't exist in one list.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class Analyze {

    /**
     * @param previous user list.
     * @param current  user list.
     * @return information about differences between lists.
     */
    public Info diff(List<User> previous, List<User> current) {
        final Info info = new Info();
        final Map<Integer, User> mapUsers = previous.stream().collect(Collectors.toMap(User::getId, user -> user));
        for (User currentUser : current) {
            int currentUserId = currentUser.getId();
            if (!mapUsers.containsKey(currentUserId)) {
                info.incrementAdded();
            } else if (!currentUser.equals(mapUsers.get(currentUserId))) {
                info.incrementChanged();
            }
            mapUsers.remove(currentUser.getId());
        }
        info.setDeleted(mapUsers.size());
        return info;
    }

    /**
     * User model.
     *
     * @author Merkurev Sergei (merkurevsergei@yandex.ru)
     * @version 0.1
     * @since 0.1
     */
    public static class User {

        /**
         * The user id.
         */
        private int id;

        /**
         * The user name.
         */
        private String name;

        /**
         * @param id   init.
         * @param name init.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Get user id.
         *
         * @return user id.
         */
        public int getId() {
            return id;
        }

        /**
         * Set user id.
         *
         * @param id user.
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * Get user name.
         *
         * @return user name.
         */
        public String getName() {
            return name;
        }

        /**
         * Set user id.
         *
         * @param name user.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @param o object.
         * @return true if equals.
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
            return id == user.id && Objects.equals(name, user.name);
        }

        /**
         * @return hash code.
         */
        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    /**
     * Info model provide information about differences.
     */
    public static class Info {

        /**
         * Information about the number of added {@code User}.
         */
        private int added;

        /**
         * Information about the number of changed {@code User}.
         */
        private int changed;

        /**
         * Information about the number of deleted {@code User}.
         */
        private int deleted;

        /**
         * Get information about the number of added {@code User}.
         *
         * @return information about the number of added {@code User}.
         */
        public int getAdded() {
            return added;
        }

        /**
         * Increment the number of added.
         */
        public void incrementAdded() {
            added++;
        }

        /**
         * Get information about the number of changed {@code User}.
         *
         * @return information about the number of changed {@code User}.
         */
        public int getChanged() {
            return changed;
        }

        /**
         * Increment the number of changed.
         */
        public void incrementChanged() {
            changed++;
        }

        /**
         * Get information about the number of deleted {@code User}.
         *
         * @return information about the number of deleted {@code User}.
         */
        public int getDeleted() {
            return deleted;
        }

        /**
         * Set the number of deleted.
         */
        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}





