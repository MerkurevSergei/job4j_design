package ru.job4j.concurrent.wget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Wget {
    static int maxBlock = 5000000;
    public static void main(String[] args) throws InterruptedException, IOException {
        //args = new String[] {"http://d.zaix.ru/e6zR.mp3", "1000"};
        Options options = new Options(args);
        final File downloadedFile = File.createTempFile("tmp", null);
        try (BufferedInputStream in = new BufferedInputStream(options.getUrl().openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(downloadedFile)) {
            byte[] dataBuffer = new byte[maxBlock];
            int bytesRead;
            long avgSpeed = 0;
            double download = 0;
            long t0 = System.currentTimeMillis();
            Thread.sleep(1000);
            while ((bytesRead = in.read(dataBuffer, 0, maxBlock)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long t1 = System.currentTimeMillis();
                avgSpeed = (avgSpeed + downloadSpeedKbS(t0, t1, bytesRead)) / 2;
                download = download + (double) bytesRead / 1024;
                System.out.printf("\rDownload: %.2f KB, speed - %d KB/s", download, avgSpeed);
                long delay = calcDelay(t0, t1, avgSpeed, options.getSpeedBMs());
                t0 = System.currentTimeMillis();
                Thread.sleep(delay);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("File download: " + downloadedFile.getAbsolutePath());
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
