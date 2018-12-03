package server;

import java.io.*;
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
                String inLine = in.readLine();
                if (inLine.startsWith("connect")) {
                    String line = null;
                    try {
                        FileReader fileReader = new FileReader("Inscription_data");
                        BufferedReader reader = new BufferedReader(fileReader);
                        while ((line = reader.readLine()) != null) {
                            String information = inLine.substring(8);
                            if (line.equals(information)) {
                                out.println("registered");
                                break;
                            }
                        }
                    } catch (IOException exept) {
                        System.out.println("nothing here");
                    }


                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}







