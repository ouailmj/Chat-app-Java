package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class App {

    static void loadScreen(ActionEvent event){
        Parent appWindow = null;
        try {
            appWindow = FXMLLoader.load(App.class.getResource("app.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage mainWindow;
        mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();

        Scene newScene = new Scene(appWindow);

        mainWindow.setScene(newScene);
    }

    void runSocket() throws IOException {

        BufferedReader in;
        PrintWriter out;
        Socket socket = new Socket("127.0.0.1", 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {

        }
    }

}