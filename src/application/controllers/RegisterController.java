package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button btnSignIn;
    @FXML private Button btnSignup;
    @FXML private Label lblInform;

    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            lblInform.setText("Username and password are required");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
            lblInform.setText("Registration successful");
        } catch (IOException e) {
            lblInform.setText("Error saving registration");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        try {
            // Load Signup.fxml with leading slash
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Signup.fxml"));
            Parent root = loader.load();
            // Get the current stage
            Stage stage = (Stage) btnSignup.getScene().getWindow();
            // Set new scene
            stage.setScene(new Scene(root));
            stage.setTitle("User Registration");
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            lblInform.setText("Error loading signup page");
            e.printStackTrace();
        }
    }
}