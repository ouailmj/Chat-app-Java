package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    public static boolean registered = false;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private TextField erreur;

    @FXML
    private ImageView gif;

    @FXML
    void signIn(ActionEvent event) throws IOException {
        String line = null;
        if(user.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
            gif.setStyle("-fx-opacity:1");
            erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
            erreur.setText("Veuillez remplir tout les champs");
        }
        else{
            gif.setStyle("visibility:true");
            registered = ChatMain.chatClient.connect(user.getText(), password.getText());
            if (!registered) {
                gif.setStyle("visibility:false");
                erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
                erreur.setText("Username ou mot de pass incorrect");
            } else {
                App.loadScreen(event);
            }
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