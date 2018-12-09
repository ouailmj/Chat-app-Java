package client;

import Local_Environement.Dialog;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login {

    public static boolean registered = false;

    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton button;
    @FXML
    private JFXButton buttonSignIn;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private TextField erreur;
    @FXML
    private StackPane mainPane;

    private String usernameRegister;

    @FXML
    public void initialize() {
        imageView.setVisible(false);
        Platform.runLater(() -> {
            if (this.usernameRegister != null || !this.usernameRegister.isEmpty()) {
                Dialog.loadDialog(usernameRegister,mainPane);
                usernameRegister = "";
            }
        });
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {

        if(user.getText().trim().isEmpty() || password.getText().trim().isEmpty()){
            erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
            erreur.setText("Veuillez remplir tout les champs");
        }
        else{
            Task<Boolean> loginTask = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return ChatMain.chatClient.connect(user.getText(), password.getText());
                }
            };
            loginTask.setOnSucceeded(e -> {
                if (loginTask.getValue()) {
                    App.loadScreen(event);
                } else {
                    erreur.setStyle("-fx-text-inner-color:red;-fx-background-color:transparent");
                    erreur.setText("Username ou mot de pass incorrect");
                }
                imageView.setVisible(false);
                button.setDisable(false);
                buttonSignIn.setDisable(false);
            });
            loginTask.setOnFailed(e -> {
                erreur.setText("Erreur System!!!");
                imageView.setVisible(false);
            });
            Thread thread = new Thread(loginTask);
            thread.setDaemon(true);
            imageView.setVisible(true);
            button.setDisable(true);
            buttonSignIn.setDisable(true);
            thread.start();
        }
    }

    public void setUser(String user_id){
        this.usernameRegister = user_id;
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        imageView.setVisible(true);
        Parent signUpWindow = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(signUpWindow);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
        imageView.setVisible(false);
    }

    @FXML
    public void closeprog(ActionEvent event) throws IOException {
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

}