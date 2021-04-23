package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GameScreen.fxml"));
        Scene startScene = new Scene(root, 400, 570);
        stage = primaryStage;
        stage.setScene(startScene);
        stage.setTitle("2048");
        stage.show();
    }

    /*public static void startGame() throws IOException {
        URL GameScreenUrl = new File("/GameScreen.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(GameScreenUrl);
        stage.getScene().setRoot(root);
    }*/
}
