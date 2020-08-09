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
        final FileDownloader fileDownloader = new FileDownloader(options.getUrl(), options.getSpeedBMs(), downloadedFile);
        final Thread thread = new Thread(fileDownloader);
        thread.start();
        while (thread.isAlive()) {
            System.out.printf("\rDownload: %.2f KB, speed - %d KB/s",
                    fileDownloader.getDownload(),
                    fileDownloader.getAvgSpeed());
            Thread.sleep(100);
        }
        thread.join();
        System.out.println();
        System.out.println("File download: " + downloadedFile.getAbsolutePath());
    }


}
