package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class App {

    @FXML
    private TextFlow tofl;

    @FXML
    void send(ActionEvent event) {

    }

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

}