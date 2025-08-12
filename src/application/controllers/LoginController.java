package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.SceneManager;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;
    
    
    

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in both fields.");
            return;
        }

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("data/accounts.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                messageLabel.setText("Login successful!");

                // Switch to fuelPurchase page on successful login
                SceneManager.switchScene("views/fuelPurchase.fxml");

                // Alternatively, if you want to use event to get stage, you can do:
                // Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                // FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/fuelPurchase.fxml"));
                // Parent root = loader.load();
                // stage.setScene(new Scene(root));
                // stage.show();

            } else {
                messageLabel.setText("Invalid credentials.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Error reading account file.");
        }
    }

    @FXML
    private void handleForgotPassword() {
        SceneManager.switchScene("views/ForgotPassword.fxml");
    }
    @FXML
    public void handleRegister(ActionEvent event) {
        goToRegister(event); // delegate to your real method
    }

    @FXML
    private void goToRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/userDetails.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("User Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Unable to open user details page.");
        }
    }
}

