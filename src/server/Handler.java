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
                    Verfification_data_User(infos[0],infos[1]);
                }
                else if(inLine.startsWith("register")){
                    String information = inLine.substring(9);
                    String[] infos = information.split(";");
                    User_Register(infos[0],infos[1],infos[2],infos[3]);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void User_Register(String info, String info1, String info2, String info3) throws SQLException, ParseException {
        User user = new User(info,info1,info2,info3);
        if(!user.Verification_data_user(info,null)){
            user.Add_data_user();
            out.println("unregistered");
        }
        else{
            out.println("registered");
        }
    }

    private void Verfification_data_User(String info, String info1) throws SQLException, ParseException {
        User user = new User(info,info1);
        if(user.Verification_data_user(info,info1))
            out.println("registered");
        else{
            out.println("unregistered");
        }
    }

    public static void main(String[] args) throws SQLException, ParseException {
        User user = new User("dqlkh","ouail");
    }

}






