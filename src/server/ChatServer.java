package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
    public static void main(String[] args) throws IOException {

        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(9011);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
}
