package server;

import provider.ApiCrud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
                verification();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void verification() throws IOException {
        String inLine = in.readLine();
        if (inLine.startsWith("connect")) {
            String information = inLine.substring(8);
            String[] infos = information.split(";");
            ApiCrud crud = new ApiCrud();
            if(crud.Verification_data_user(infos[0],infos[1]))
                out.println("registered");
            else
                out.println("unregistered");
        }
    }
}






