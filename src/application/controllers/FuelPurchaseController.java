package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import application.models.Car;


public class FuelPurchaseController {

    @FXML
    private ComboBox<Car> carComboBox; // Use Car objects directly

    @FXML
    private Label petrolPriceLabel;

    @FXML
    private Label dieselPriceLabel;

    @FXML
    private Label lpgPriceLabel;

    @FXML
    private ComboBox<String> fuelTypeComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Label totalPriceLabel;

    private Map<String, Double> fuelPrices = new HashMap<>();

    @FXML
    public void initialize() {
        loadCarModels();
        loadFuelPrices();

        // Setup fuel type dropdown options
        fuelTypeComboBox.setItems(FXCollections.observableArrayList("Petrol 95", "Diesel", "LPG"));
        fuelTypeComboBox.getSelectionModel().selectFirst();
    }

    private void loadCarModels() {
        // For example, load cars from XML or create manually
        // Here, adding manually for demonstration
        ObservableList<Car> cars = FXCollections.observableArrayList(
            new Car("Toyota Corolla", 50),
            new Car("Honda Civic", 45),
            new Car("Ford Focus", 55)
        );
        carComboBox.setItems(cars);
        carComboBox.getSelectionModel().selectFirst();
    }

    private void loadFuelPrices() {
        try (InputStream input = getClass().getResourceAsStream("/fuelprices.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(input))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String fuelType = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    fuelPrices.put(fuelType, price);
                }
            }

            petrolPriceLabel.setText(String.format("Petrol 95: R%.2f", fuelPrices.getOrDefault("Petrol 95", 0.0)));
            dieselPriceLabel.setText(String.format("Diesel: R%.2f", fuelPrices.getOrDefault("Diesel", 0.0)));
            lpgPriceLabel.setText(String.format("LPG: R%.2f", fuelPrices.getOrDefault("LPG", 0.0)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCalculateTotal() {
        Car selectedCar = carComboBox.getValue();
        String selectedFuel = fuelTypeComboBox.getValue();
        String qtyText = quantityField.getText();

        if (selectedCar == null) {
            showAlert("Please select a car.");
            return;
        }
        if (selectedFuel == null || qtyText.isEmpty()) {
            showAlert("Please select fuel type and enter quantity.");
            return;
        }

        try {
            double quantity = Double.parseDouble(qtyText);
            if (quantity <= 0) {
                showAlert("Quantity must be positive.");
                return;
            }

            Double pricePerLiter = fuelPrices.get(selectedFuel);
            if (pricePerLiter == null) {
                showAlert("Fuel price not available.");
                return;
            }

            // Calculate total based on entered quantity (litres)
            double total = quantity * pricePerLiter;
            totalPriceLabel.setText(String.format("R%.2f", total));

        } catch (NumberFormatException e) {
            showAlert("Invalid quantity format.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
