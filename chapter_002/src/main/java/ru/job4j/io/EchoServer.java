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
            String answer = "";
            while (!answer.toLowerCase().equals("exit")) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    answer = answer(str);
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(answer.getBytes());
                }
            }
        }
    }

    /**
     * @param query - query
     * @return parsing result
     */
    public static String answer(String query) {
        String res = "";
        String[] s = query.split(" ");
        if (s.length < 1) {
            return res;
        }
        s = s[1].split("=");
        if (s.length > 1) {
            res = s[1];
        }
        return res;
    }
}