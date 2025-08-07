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

public class SignupController {
    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField idNumberField;
    @FXML private TextField addressField;
    @FXML private TextField carRegField;
    @FXML private Button btnRegister;
    @FXML private Button btnNext;
    @FXML private Label lblInform;

    @FXML
    private void handleRegister(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();
        String idNumber = idNumberField.getText();
        String address = addressField.getText();
        String carReg = carRegField.getText();

        // Validate inputs
        if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || idNumber.isEmpty() || address.isEmpty() || carReg.isEmpty()) {
            lblInform.setText("All fields are required");
            return;
        }

        // Validate ID number (13 digits, placeholder for Home Affairs)
        if (!idNumber.matches("\\d{13}")) {
            lblInform.setText("Invalid ID number: Must be 13 digits");
            return;
        }

        // Validate car registration (South African province formats)
        String carRegPattern = "^([A-Z]{1,3}\\s\\d{1,4}\\s[A-Z]{1,2}|[A-Z]{1,2}\\s\\d{1,4}|[A-Z]{1,2}\\s\\d{1,3}-\\d{1,3})$";
        if (!carReg.matches(carRegPattern)) {
            lblInform.setText("Invalid car registration number (e.g., ABC 123 GP, CA 1234, ND 123-456)");
            return;
        }

        // Save to file (per Section 1.2.2)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_details.txt", true))) {
            writer.write(name + "," + surname + "," + password + "," + idNumber + "," + address + "," + carReg);
            writer.newLine();
            lblInform.setText("User details saved successfully");
        } catch (IOException e) {
            lblInform.setText("Error saving user details");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNext(ActionEvent event) {
        // Validate inputs before proceeding
        String name = nameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();
        String idNumber = idNumberField.getText();
        String address = addressField.getText();
        String carReg = carRegField.getText();

        if (name.isEmpty() || surname.isEmpty() || password.isEmpty() || idNumber.isEmpty() || address.isEmpty() || carReg.isEmpty()) {
            lblInform.setText("All fields are required before proceeding");
            return;
        }

        if (!idNumber.matches("\\d{13}")) {
            lblInform.setText("Invalid ID number: Must be 13 digits");
            return;
        }

        String carRegPattern = "^([A-Z]{1,3}\\s\\d{1,4}\\s[A-Z]{1,2}|[A-Z]{1,2}\\s\\d{1,4}|[A-Z]{1,2}\\s\\d{1,3}-\\d{1,3})$";
        if (!carReg.matches(carRegPattern)) {
            lblInform.setText("Invalid car registration number (e.g., ABC 123 GP, CA 1234, ND 123-456)");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CarDetails.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnNext.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Car Details");
            stage.setMaximized(true);
            stage.show();
        } catch (IOException e) {
            lblInform.setText("Error loading car details page");
            e.printStackTrace();
        }
    }
}