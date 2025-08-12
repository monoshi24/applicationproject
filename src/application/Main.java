package application;

import application.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the primary stage in SceneManager BEFORE loading any scenes
        SceneManager.setStage(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/Login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("My App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}