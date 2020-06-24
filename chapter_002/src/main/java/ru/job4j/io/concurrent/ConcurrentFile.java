package ru.job4j.io.concurrent;

import ru.job4j.io.locksmanager.InnerFileLock;
import ru.job4j.io.locksmanager.InnerFileLocker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

public class ConcurrentFile {
    private final Path path;
    private final Charset charset;

    /**
     * @param path init
     */
    public ConcurrentFile(Path path) {
        this(path, StandardCharsets.UTF_8);
    }

    /**
     * @param path    init
     * @param charset init
     */
    public ConcurrentFile(Path path, Charset charset) {
        this.path = Optional.of(path).get();
        this.charset = Optional.of(charset).get();
    }

    /**
     * @return all content from file to string with standard charset.
     * @throws IOException If an I/O error occurs.
     */
    public String readString() throws IOException {
        return readString(charset);
    }

    /**
     * @param charset that the file is encoded in.
     * @return all content from file to string with any charset.
     * @throws IOException If an I/O error occurs.
     */
    public String readString(Charset charset) throws IOException {
        charset = Optional.of(charset).get();
        String output;
        try (final FileInputStream in = new FileInputStream(path.toFile());
             final FileChannel channel = in.getChannel();
             final FileLock extLook = channel.tryLock(0, Long.MAX_VALUE, true);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, true)) {
            checkExternalOverlap(extLook);
            checkInnerOverlap(path, inLock);
            output = new String(in.readAllBytes(), charset);
        }
        return output;
    }

    /**
     * @return all byte from file.
     * @throws IOException If an I/O error occurs.
     */
    public byte[] readAllBytes() throws IOException {
        byte[] bytes;
        try (final FileInputStream in = new FileInputStream(path.toFile());
             final FileChannel channel = in.getChannel();
             final FileLock extLook = channel.tryLock(0, Long.MAX_VALUE, true);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, true)) {
            checkExternalOverlap(extLook);
            checkInnerOverlap(path, inLock);
            bytes = in.readAllBytes();
        }
        return bytes;
    }

    /**
     * @param content which need saved with the instance charset.
     * @throws IOException If an I/O error occurs.
     */
    public void write(String content) throws IOException {
        write(content, charset);
    }

    /**
     * @param content which need saved with any charset.
     * @param charset any charset.
     * @throws IOException If an I/O error occurs.
     */
    public void write(String content, Charset charset) throws IOException {
        charset = Optional.of(charset).get();
        try (final FileOutputStream out = new FileOutputStream(path.toFile());
             final FileChannel channel = out.getChannel();
             final FileLock extLock = channel.tryLock(0, Long.MAX_VALUE, false);
             final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(path, false)) {
            checkExternalOverlap(extLock);
            checkInnerOverlap(path, inLock);
            out.write(content.getBytes(charset));
        }
    }

    private static void checkExternalOverlap(FileLock extLook) {
        if (extLook == null) {
            throw new OverlappingFileLockException();
        }
    }

    private static void checkInnerOverlap(Path path, InnerFileLock inLock) throws IOException {
        if (inLock == null) {
            throw new IOException("The file is already exclusive open: " + path.toAbsolutePath());
        }
    }
}
