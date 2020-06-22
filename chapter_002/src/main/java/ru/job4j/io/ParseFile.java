package ru.job4j.io;

import ru.job4j.io.locksmanager.InnerFileLock;
import ru.job4j.io.locksmanager.InnerFileLocker;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * The ParseFile - read content, read only ASCII content and write content to file.
 *
 * @author Merkurev Sergei (merkurevsergei@yandex.ru)
 * @version 0.1
 * @since 0.1
 */
public class ParseFile {

    /**
     * The path to file.
     */
    private final Path path;

    /**
     * The file charset.
     */
    private final Charset charset;

    /**
     * @param path    init.
     * @param charset init.
     */
    public ParseFile(Path path, Charset charset) {
        this.path = path;
        this.charset = charset;
    }

    /**
     * @return all content from file to string.
     * @throws IOException If an I/O error occurs.
     */
    public String getContent() throws IOException {
        String output;
        try (final FileInputStream in = new FileInputStream(path.toFile());
             final FileChannel channel = in.getChannel();
             final FileLock extLook = channel.tryLock(0, Long.MAX_VALUE, true);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, true)) {
            checkExternalOverlap(extLook);
            checkInnerOverlap(inLock);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            output = new String(in.readAllBytes(), charset);
        }
        return output;
    }

    /**
     * @return only ASCII content from file to string.
     * @throws IOException If an I/O error occurs.
     */
    public String getContentASCII() throws IOException {
        String output;
        try (final FileInputStream in = new FileInputStream(path.toFile());
             final FileChannel channel = in.getChannel();
             final FileLock extLook = channel.tryLock(0, Long.MAX_VALUE, true);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, true)) {
            checkExternalOverlap(extLook);
            checkInnerOverlap(inLock);
            output = new String(in.readAllBytes(), charset);
        }
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
        byte[] bytes;
        try (final FileInputStream in = new FileInputStream(path.toFile());
             final FileChannel channel = in.getChannel();
             final FileLock extLook = channel.tryLock(0, Long.MAX_VALUE, true);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, true)) {
            checkExternalOverlap(extLook);
            checkInnerOverlap(inLock);
            bytes = in.readAllBytes();
        }
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
        saveContent(content, charset);
    }

    /**
     * @param content which need saved with any charset.
     * @param charset any charset.
     * @throws IOException If an I/O error occurs.
     */
    public void saveContent(String content, Charset charset) throws IOException {
        try (final FileOutputStream out = new FileOutputStream(path.toFile());
             final FileChannel channel = out.getChannel();
             final FileLock extLock = channel.tryLock(0, Long.MAX_VALUE, false);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, false)) {
            checkExternalOverlap(extLock);
            checkInnerOverlap(inLock);
            out.write(content.getBytes(charset));
        }
    }

    private void checkExternalOverlap(FileLock extLook) {
        if (extLook == null) {
            throw new OverlappingFileLockException();
        }
    }

    private void checkInnerOverlap(InnerFileLock inLock) throws IOException {
        if (inLock == null) {
            throw new IOException("The file is already exclusive open: " + path.toAbsolutePath());
        }
    }
}