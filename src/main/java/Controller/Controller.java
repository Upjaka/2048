package Controller;

import Model.Field;
import Model.Sides;
import View.Main;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller {
    private static Field field;
    private static Label[][] labels;

    @FXML
    private Button startButton;
    @FXML
    private Button recordsButton;
    @FXML
    private Button restartButton;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label scores;

    public void startButtonClicked() {
        restartButton.setText("Restart");
        field = new Field();
        labels = new Label[field.getSize()][field.getSize()];
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j] = new Label();
                gridPane.add(labels[i][j], i, j);
                labels[i][j].setText(field.get(j, i));
                labels[i][j].getStyleClass().add("chip");
                labels[i][j].setVisible(true);
            }
        }
        scores.setText("Scores: " + field.getScores());
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) field.moveAll(Sides.UP);
        if (keyEvent.getCode() == KeyCode.A) field.moveAll(Sides.LEFT);
        if (keyEvent.getCode() == KeyCode.S) field.moveAll(Sides.DOWN);
        if (keyEvent.getCode() == KeyCode.D) field.moveAll(Sides.RIGHT);
        updateScene();
    }

    private void updateScene() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j].setText(String.valueOf(field.get(j, i)));
            }
        }
        scores.setText("Scores: " + field.getScores());
        scores.setVisible(true);
    }
}
