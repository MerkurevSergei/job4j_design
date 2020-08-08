package ru.job4j;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CASCountTest {

    private final CASCount casCount = new CASCount();

    @AfterClass
    public void tearDown() {
        assertEquals(60, casCount.get());
    }

    @Test(threadPoolSize = 3, invocationCount = 30, timeOut = 1000)
    public void incrementAndGet() {
        casCount.increment();
        casCount.increment();
    }


}