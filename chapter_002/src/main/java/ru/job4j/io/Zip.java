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
    public void packFiles(List<File> sources, File target) throws AccessDeniedException, NoSuchFileException, FileNotFoundException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.toPath().normalize().toString()));
                Files.copy(source.toPath(), zip);
            }
        } catch (AccessDeniedException | NoSuchFileException | FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param source - file for zipped
     * @param target - target archive
     */
    public void packSingleFile(File source, File target) throws NoSuchFileException, FileNotFoundException, AccessDeniedException {
        final List<File> files = new ArrayList<>();
        files.add(source);
        packFiles(files, target);
    }

    public void packFromArgZip(ArgZip argZip) throws IllegalArgumentException, NoSuchFileException, FileNotFoundException, AccessDeniedException {
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