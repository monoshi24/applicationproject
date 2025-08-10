package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class UserDetailsController {

    @FXML
    private TextField fullNameField, surnameField, idField, addressField, carRegField;

    @FXML
    private Button uploadButton, submitButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Label imageLabel;

    private File selectedImageFile;

    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Car Image");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());

        if (file != null) {
            selectedImageFile = file;
            imageLabel.setText("Selected: " + file.getName());
            imageView.setImage(new Image(file.toURI().toString()));
        } else {
            imageLabel.setText("No file selected.");
        }
    }

    @FXML
    private void handleSubmit() {
        String fullName = fullNameField.getText();
        String surname = surnameField.getText();
        String id = idField.getText();
        String address = addressField.getText();
        String carReg = carRegField.getText();

        System.out.println("Full Name: " + fullName);
        System.out.println("Surname: " + surname);
        System.out.println("ID: " + id);
        System.out.println("Address: " + address);
        System.out.println("Car Reg: " + carReg);
        System.out.println("Image File: " + (selectedImageFile != null ? selectedImageFile.getAbsolutePath() : "None"));
    }
}
