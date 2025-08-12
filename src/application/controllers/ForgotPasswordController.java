package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import application.SceneManager; // Import SceneManager from application package

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private void handleReset() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your email address.");
            return;
        }

        // Simulate sending email
        showAlert(Alert.AlertType.INFORMATION, "Success", "A reset link has been sent to " + email);
    }

    @FXML
    private void handleBack() {
        SceneManager.switchScene("views/Login.fxml"); // matches your folder structure
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}