package client;

import Entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;

public class ChatClient {
    BufferedReader in;
    PrintWriter out;
    private User user;

    ChatClient() throws IOException {

        Socket socket = new Socket("127.0.0.1", 9010);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public boolean connect(String username, String password) throws IOException, SQLException, ParseException {
        user = new User(username,password);
        out.println("connect " + user.getUsername() + ";" + user.getPassword());
        return in.readLine().equals("registered");
    }

    public boolean register(String username, String password , String confirm , String email ,boolean sexe) throws IOException {
        user = new User(username,password,email,Boolean.toString(sexe));
        System.out.println("etape1");
        out.println("register " + user.getUsername() + ";" + user.getPassword() + ";" + user.getEmail() + ";" + user.isSexe());
        return !in.readLine().equals("registered");
    }

}