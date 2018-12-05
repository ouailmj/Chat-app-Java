package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {
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

    @FXML
    void inscription(ActionEvent event) {
        String passwordRegisterText = passwordRegister.getText();
        String confirmPasswordText = confirmPassword.getText();
        if(emailRegister.getText().trim().isEmpty() || passwordRegister.getText().trim().isEmpty() || confirmPassword.getText().trim().isEmpty() || usernameRegister.getText().trim().isEmpty()){
            if(emailRegister.getText().trim().isEmpty())
                emailRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
            if(usernameRegister.getText().trim().isEmpty())
                usernameRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
            if(passwordRegister.getText().trim().isEmpty())
                passwordRegister.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
            if(confirmPassword.getText().trim().isEmpty())
                confirmPassword.getParent().setStyle("-fx-border-color:red;-fx-background-color:#565a60;-fx-border-radius:5px;-fx-background-radius: 5px");
            erreur.setText("Veuillez remplir tout les champs");

        }else {
            if (passwordRegisterText.equals(confirmPasswordText)) {
                if(!validate(emailRegister.getText())){
                    erreur.setText("Email incorrect");
                }
                else {
                    try {

                        PrintWriter writer = new PrintWriter(new FileWriter("Inscription_data", true));
                        System.out.println(usernameRegister.getText() + ";" + passwordRegisterText);
                        writer.println(usernameRegister.getText() + ";" + passwordRegisterText);
                        writer.close();
                        ouvrirFenetre(event, "login.fxml");

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

}