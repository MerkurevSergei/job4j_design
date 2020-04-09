package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * EchoServer
 */
public class EchoServer {
    /**
     * @param args - command line arguments
     * @throws IOException - exception
     */
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            String msg = "";
            while (!msg.equals("GET /?msg=Bye HTTP/1.1")) {
                msg = "";
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        if (msg.isEmpty()) {
                            msg = str;
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK".getBytes());
                }
            }
        }
    }
}