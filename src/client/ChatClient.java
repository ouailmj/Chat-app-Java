package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    BufferedReader in;
    PrintWriter out;

    ChatClient() throws IOException {

        Socket socket = new Socket("127.0.0.1", 9010);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean connect(String username, String password) throws IOException {
        out.println("connect " + username + ";" + password);
        return in.readLine().equals("registered");
    }

    public boolean register(String username, String password , String confirm , String email) throws IOException {
        out.println("register " + username + ";" + password + ";" + confirm + ";" + email);
        return !in.readLine().equals("registered");
    }

}