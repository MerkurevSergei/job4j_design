package ru.job4j.io.locksmanager;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;

public class InnerFileLockTest {

    private Path path;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        path = Files.createTempFile(null, null);
    }

    @Test
    public void testGetPath() {
        InnerFileLock inLock = new InnerFileLock(path, false);
        assertThat(inLock.getPath(), is(path.toAbsolutePath()));
    }

    @Test
    public void testIsShared() {
        InnerFileLock inLock0 = new InnerFileLock(path, false);
        assertFalse(inLock0.isShared());
        InnerFileLock inLock1 = new InnerFileLock(path, true);
        assertTrue(inLock1.isShared());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testClose() {
        InnerFileLock inLock = new InnerFileLock(path, false);
        inLock.close();
    }
}