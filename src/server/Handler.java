package server;

import Entity.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;

public class Handler extends Thread {
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String inLine = in.readLine();
                if (inLine.startsWith("connect")) {
                    String information = inLine.substring(8);
                    String[] infos = information.split(";");
                    User user = new User(infos[0],infos[1]);
                    if(user.Verification_data_user(infos[0],infos[1]))
                        out.println("registered");
                    else{
                        out.println("unregistered");
                    }
                }
                else if(inLine.startsWith("register")){
                    String information = inLine.substring(9);
                    String[] infos = information.split(";");
                    User user = new User(infos[0],infos[1],infos[3]);
                    if(!user.Verification_data_user(infos[0],infos[1])){
                        user.Add_data_user();
                        out.println("unregistered");
                    }
                    else{
                        out.println("registered");
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws SQLException, ParseException {
        User user = new User("dqlkh","ouail");
    }

}






