package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FuelPurchaseController {

    @FXML
    private ComboBox<String> fuelTypeCombo;

    @FXML
    private TextField litresField;

    @FXML
    private Label priceLabel;

    @FXML
    public void initialize() {
        // Populate fuel types
        fuelTypeCombo.getItems().addAll("Petrol", "Diesel");
    }

    @FXML
    private void handleCalculate() {
        String fuelType = fuelTypeCombo.getValue();
        String litresText = litresField.getText();

        if (fuelType == null || fuelType.isEmpty()) {
            showAlert("Please select a fuel type.");
            return;
        }

        if (litresText == null || litresText.isEmpty()) {
            showAlert("Please enter the number of litres.");
            return;
        }

        try {
            double litres = Double.parseDouble(litresText);
            double pricePerLitre = fuelType.equals("Petrol") ? 23.50 : 21.20;
            double totalPrice = litres * pricePerLitre;
            priceLabel.setText(String.format("Total Price: R%.2f", totalPrice));
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid number for litres.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
