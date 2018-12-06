package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    public static boolean registered = false;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

        @FXML
    void signIn(ActionEvent event) throws IOException {
        String line = null;
        registered = ChatMain.chatClient.connect(user.getText(), password.getText());
        if (!registered) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("wrong Information");
            alert.setHeaderText(null);
            alert.setContentText("incorrect username or password");
            alert.showAndWait();
        } else {
            App.loadScreen(event);
        }
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

        Parent signUpWindow;
        signUpWindow = FXMLLoader.load(getClass().getResource("register.fxml"));

        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene newScene = new Scene(signUpWindow);

        mainWindow.setScene(newScene);

    }

    @FXML
    public void closeprog(ActionEvent event) throws IOException {

        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }



}