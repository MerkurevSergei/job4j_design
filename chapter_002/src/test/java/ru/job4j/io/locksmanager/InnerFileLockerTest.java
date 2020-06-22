package ru.job4j.io.locksmanager;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class InnerFileLockerTest {

    private Path pathShared;
    private Path pathExclusive;
    private InnerFileLock lockShared;
    private InnerFileLock lockExclusive;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        pathShared = Files.createTempFile(null, null);
        pathExclusive = Files.createTempFile(null, null);
        lockShared = InnerFileLocker.MANAGER.tryLock(pathShared, true);
        lockExclusive = InnerFileLocker.MANAGER.tryLock(pathExclusive, false);
    }

    @Test(threadPoolSize = 5, invocationCount = 15, invocationTimeOut = 10000)
    public void testTryLockSharedOnShared() {
        try (final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(pathShared, true)) {
            assertNotNull(inLock);
        }
    }

    @Test(threadPoolSize = 2, invocationCount = 5, invocationTimeOut = 10000)
    public void testTryLockExclusiveOnShared() {
        try (final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(pathShared, false)) {
            assertNull(inLock);
        }
    }

    @Test(threadPoolSize = 2, invocationCount = 5, invocationTimeOut = 10000)
    public void testTryLockSharedOnExclusive() {
        try (final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(pathExclusive, true)) {
            assertNull(inLock);
        }
    }

    @Test
    public void testTryLockExclusiveOnExclusive() {
        try (final InnerFileLock inLock = InnerFileLocker.MANAGER.tryLock(pathExclusive, false)) {
            assertNull(inLock);
        }
    }

    @Test
    public void testReleaseLockSuccess() throws IOException {
        final Path path = Files.createTempFile(null, null);
        final InnerFileLock lock = InnerFileLocker.MANAGER.tryLock(path, true);

        InnerFileLocker.MANAGER.releaseLock(lock);
        assertEquals(lock.count(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testReleaseLockIllegalArgumentException() throws IOException {
        final Path path = Files.createTempFile(null, null);
        final InnerFileLock lock = InnerFileLocker.MANAGER.tryLock(path, true);

        InnerFileLocker.MANAGER.releaseLock(lock);
        InnerFileLocker.MANAGER.releaseLock(lock);
        fail();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testReleaseLockNullPointerException() {
        InnerFileLocker.MANAGER.releaseLock(null);
        fail();
    }


}