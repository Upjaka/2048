package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GameScreen.fxml"));
        Scene startScene = new Scene(root, 400, 570);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("2048");
        primaryStage.show();
    }
}
