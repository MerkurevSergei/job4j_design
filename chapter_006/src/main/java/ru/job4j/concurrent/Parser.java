package ru.job4j.concurrent;

import java.util.Arrays;

/**
 * The ParseFile - read content, read only ASCII content and write content to file.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public final class Parser {

    /**
     * @return only ASCII content from file to string.
     */
    public String getContentASCII(final String string) {
        final StringBuilder sb = new StringBuilder();
        final char[] chars = string.toCharArray();
        for (char c: chars) {
            if (c < 127) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Inside jvm, characters are represented in UTF-16 encoding by two chars (codePoint),
     * so it is not correct to filter bytes.
     * This method is just an example here (can be used in cryptography?)
     *
     * @return content from file where bytes less then 128.
     */
    public String getContent128(final byte[] bytes) {
        byte[] tmpBytes = Arrays.copyOf(bytes, bytes.length);
        final StringBuilder sb = new StringBuilder();
        for (byte b : tmpBytes) {
            sb.append((char) b);
        }
        return sb.toString();
    }
}