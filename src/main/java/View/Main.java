package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL startScreenUrl = new File("files/StartScreen.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(startScreenUrl);

        Scene startScene = new Scene(root, 400, 600);
        stage = primaryStage;
        stage.setScene(startScene);
        stage.setTitle("2048");
        stage.show();
    }

    public static void startGame() throws IOException {
        URL GameScreenUrl = new File("files/GameScreen.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(GameScreenUrl);
        Scene gameScene = new Scene(root, 400, 600);
        stage.setScene(gameScene);
        stage.setTitle("2048");
        stage.show();
    }

    public static void restartGame() {

    }
}
