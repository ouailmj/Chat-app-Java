package client;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {

    public static boolean register = false;

    @FXML
    public JFXRadioButton male;

    @FXML
    public JFXRadioButton femal;

    @FXML
    public ImageView imageView;

    @FXML
    public JFXButton cancelAction;

    @FXML
    public JFXButton signup;

    @FXML
    TextField usernameRegister;
    @FXML
    PasswordField passwordRegister;
    @FXML
    PasswordField confirmPassword;
    @FXML
    TextField emailRegister;
    @FXML
    TextField erreur;

    private Image image = new Image(getClass().getResourceAsStream("load.gif"));

    void ouvrirFenetre(ActionEvent event,String str) throws IOException{

        Parent signUpWindow;
        signUpWindow = FXMLLoader.load(getClass().getResource(str));

        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene newScene = new Scene(signUpWindow);

        mainWindow.setScene(newScene);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void Style(){
        if(emailRegister.getText().trim().isEmpty()) {
            emailRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
        }
        if(usernameRegister.getText().trim().isEmpty()) {
            usernameRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
        }
        if(passwordRegister.getText().trim().isEmpty()) {
            passwordRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
        }
        if(confirmPassword.getText().trim().isEmpty()) {
            confirmPassword.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
        }
        if(!male.isSelected() && !femal.isSelected())
            male.setSelected(true);
        erreur.setText("Veuillez remplir tout les champs");
    }

    Service<Boolean> databaseLoading = new Service<Boolean>(){

        @Override
        protected Task<Boolean> createTask() {
            return new Task<Boolean>(){

                @Override
                protected Boolean call() throws Exception {
                    imageView.setStyle("-fx-opacity:0");
                    imageView.setImage(image);
                    return true;
                }
            };
        }
    };

    @FXML
    void inscription(ActionEvent event) {
        String passwordRegisterText = passwordRegister.getText();
        String confirmPasswordText = confirmPassword.getText();
        if(emailRegister.getText().trim().isEmpty() || passwordRegister.getText().trim().isEmpty() || confirmPassword.getText().trim().isEmpty() || usernameRegister.getText().trim().isEmpty() || (!male.isSelected() && !femal.isSelected())
        ){
            Style();
        }else {
            if (passwordRegisterText.equals(confirmPasswordText)) {
                if(!validate(emailRegister.getText())){
                    erreur.setText("Email incorrect");
                }
                else {
                    try {
                        boolean sexe = true;
                        sexe = male.isSelected();
                        databaseLoading.reset();
                        databaseLoading.start();
                        cancelAction.setDisable(true);
                        signup.setDisable(true);
                        register = ChatMain.chatClient.register(usernameRegister.getText(), passwordRegister.getText(),confirmPassword.getText(),emailRegister.getText(),sexe);
                        if(!register){
                            erreur.setText("User existe deja");
                        }
                        else{
                            Parent signUpWindow;
                            signUpWindow = FXMLLoader.load(getClass().getResource("login.fxml"));

                            Stage mainWindow;
                            mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

                            Scene newScene = new Scene(signUpWindow);

                            mainWindow.setScene(newScene);
                        }
                        databaseLoading.cancel();
                        imageView.setStyle("-fx-opacity:0");
                        cancelAction.setDisable(false);
                        signup.setDisable(false);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                passwordRegister.setStyle("-fx-text-fill: rgb(211, 76, 76);-fx-background-color:transparent");
                confirmPassword.setStyle("-fx-text-fill: rgb(211, 76, 76);-fx-background-color:transparent");
                erreur.setText("Veuillez ressaisir le Password");
            }
        }
    }

    @FXML
    public void closeprog(ActionEvent event) throws IOException {

        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @FXML
    public void retour(ActionEvent event) throws IOException {
        ouvrirFenetre(event,"login.fxml");
    }

    @FXML
    public void selectMale(ActionEvent actionEvent) {
        femal.setSelected(false);
    }

    @FXML
    public void selectFemal(ActionEvent actionEvent) {
        male.setSelected(false);
    }
}