package client;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class Register {
    @FXML
    TextField usernameRegister;
    @FXML
    PasswordField passwordRegister;
    @FXML
    PasswordField confirmPassword;

    @FXML
    void inscription(ActionEvent event) {
        String passwordRegisterText = passwordRegister.getText();
        String confirmPasswordText = confirmPassword.getText();
        if (passwordRegisterText.equals(confirmPasswordText)) {
            try {

                PrintWriter writer = new PrintWriter(new FileWriter("Inscription_data", true));
                System.out.println(usernameRegister.getText() + ";" + passwordRegisterText);
                writer.println(usernameRegister.getText() + ";" + passwordRegisterText);
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}