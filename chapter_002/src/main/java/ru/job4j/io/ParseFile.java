package ru.job4j.io;

import ru.job4j.io.concurrent.ConcurrentFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;

/**
 * The ParseFile - read content, read only ASCII content and write content to file.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ParseFile {

    private final ConcurrentFile cFile;

    /**
     * @param path    init.
     * @param charset init.
     */
    public ParseFile(Path path, Charset charset) {
        this.cFile = new ConcurrentFile(path, charset);
    }

    /**
     * @return all content from file to string.
     * @throws IOException If an I/O error occurs.
     */
    public String getContent() throws IOException {
        return cFile.readString();
    }

    /**
     * @return only ASCII content from file to string.
     * @throws IOException If an I/O error occurs.
     */
    public String getContentASCII() throws IOException {
        String output = cFile.readString();
        return output.replaceAll("[^\\x00-\\x7E]", "");
    }

    /**
     * Inside java, characters are represented in UTF-16 encoding by two chars (codePoint),
     * so it is not correct to filter bytes.
     * This method is just an example here (can be used in cryptography?)
     *
     * @return content from file where bytes less then 128.
     * @throws IOException If an I/O error occurs.
     */
    public String getContent128() throws IOException {
        final StringBuilder sb = new StringBuilder();
        byte[] bytes = cFile.readAllBytes();
        for (byte b : bytes) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    /**
     * @param content which need saved with the instance charset.
     * @throws IOException If an I/O error occurs.
     */
    public void saveContent(String content) throws IOException {
        cFile.write(content);
    }

    /**
     * @param content which need saved with any charset.
     * @param charset any charset.
     * @throws IOException If an I/O error occurs.
     */
    public void saveContent(String content, Charset charset) throws IOException {
        charset = Optional.of(charset).get();
        cFile.write(content, charset);
    }
}