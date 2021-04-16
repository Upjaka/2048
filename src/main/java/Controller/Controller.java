package Controller;

import Model.Field;
import View.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class Controller {
    private Field field;

    @FXML
    private Button startButton;
    @FXML
    private Button recordsButton;
    @FXML
    private Button restartButton;

    public Controller() {}

    public void startButtonClicked() throws IOException {
        System.out.println("Start!");
        field = new Field();
        Main.startGame();
    }

    public void restartButtonClicked() {
        System.out.println("Restart!");
        field = new Field();
        Main.restartGame();
    }
}
