package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zipped files
 */
public class Zip {

    /**
     * @param sources - files for zipped
     * @param target  - target archive
     */
    public void packFiles(List<File> sources, File target) throws Exception {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }

    /**
     * @param source - file for zipped
     * @param target - target archive
     */
    public void packSingleFile(File source, File target) throws Exception {
        final List<File> files = new ArrayList<>();
        files.add(source);
        packFiles(files, target);
    }

    public void packFromArgZip(ArgZip argZip) throws Exception {
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Illegal ArgZip");
        }
        List<File> files = new ArrayList<>();
        try (final Stream<Path> pathStream = Files.walk(Paths.get(argZip.directory()))) {
            files = pathStream
                    .filter(path -> {
                        String pattern = String.format("glob:*%s", argZip.exclude());
                        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(pattern);
                        return !pathMatcher.matches(path) && !path.toFile().isDirectory();
                    })
                    .map(Path::toFile)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        packFiles(files, new File(argZip.output()));
    }

}