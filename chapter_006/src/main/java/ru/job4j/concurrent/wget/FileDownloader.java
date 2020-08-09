package ru.job4j.concurrent.wget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloader implements Runnable {
    private final static int MAX_BLOCK = 5000000;
    private URL url;
    private int speed;
    private File dest;
    private double download;
    private long avgSpeed;

    public FileDownloader(URL url, int speed, File dest) {
        this.url = url;
        this.speed = speed;
        this.dest = dest;
    }

    public double getDownload() {
        return download;
    }

    public long getAvgSpeed() {
        return avgSpeed;
    }

    @Override
    public void run() {
        download = 0;
        avgSpeed = 0;
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
            byte[] dataBuffer = new byte[MAX_BLOCK];
            int bytesRead;
            long t0 = System.currentTimeMillis();
            Thread.sleep(1000);
            while ((bytesRead = in.read(dataBuffer, 0, MAX_BLOCK)) != -1) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long t1 = System.currentTimeMillis();
                avgSpeed = (avgSpeed + downloadSpeedKbS(t0, t1, bytesRead)) / 2;
                download = download + (double) bytesRead / 1024;
                long delay = calcDelay(t0, t1, avgSpeed, speed);
                t0 = System.currentTimeMillis();
                Thread.sleep(delay);
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static long calcDelay(long start, long end, long avgSpeed, int targetSpeed) {
        long delay = (end - start) * avgSpeed / targetSpeed;
        delay = delay > 2000 ? 2000 : delay;
        return delay < 0 ? 0 : delay;
    }

    private static long downloadSpeedKbS(long start, long end, int bytes) {
        return end - start <= 0 ? 1000 * bytes / 1024 : 1000 * bytes / 1024 / (end - start);
    }
}
