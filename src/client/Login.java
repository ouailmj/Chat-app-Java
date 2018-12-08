package client;

import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    public static boolean registered = false;

    @FXML
    public JFXButton signin;

    @FXML
    public ImageView imageView;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private TextField erreur;

    private Image image = new Image(getClass().getResourceAsStream("load.gif"));

    Service<Boolean> databaseLoading = new Service<Boolean>(){

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>(){

                @Override
                protected Boolean call() throws Exception {
                    imageView.setImage(image);
                    return registered;
                }
            };
        }
    };

    @FXML
    void signIn(ActionEvent event) throws IOException {
        String line = null;
        if(user.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
            erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
            erreur.setText("Veuillez remplir tout les champs");
        }
        else{
            databaseLoading.reset();
            databaseLoading.start();
            imageView.setStyle("-fx-opacity:1");
            registered = ChatMain.chatClient.connect(user.getText(), password.getText());
            if (!registered) {
                imageView.setStyle("-fx-opacity:0");
                erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
                erreur.setText("Username ou mot de pass incorrect");
            } else {
                imageView.setStyle("-fx-opacity:0");
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