package View;

import Model.Field;
import Model.Sides;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class FXController {
    private static Field field;
    private static Label[][] labels;

    @FXML
    private Label gameOver;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Button restartButton;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label scores;

    public void startButtonClicked() {
        restartButton.setText("RESTART");
        field = new Field();
        labels = new Label[field.getSize()][field.getSize()];
        gridPane.getChildren().removeAll(gridPane.getChildren());
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j] = new Label();
                gridPane.add(labels[i][j], i, j);
                labels[i][j].getStyleClass().add("chip");
                if (field.get(j, i) != 1) {
                    labels[i][j].setText(field.get(j, i).toString());
                    labels[i][j].getStyleClass().add("chip2");
                }
            }
        }
        scores.setText("Scores: " + field.getScores());
    }

    public void keyReleased(KeyEvent keyEvent) {
        final KeyCode keyCode = keyEvent.getCode();
        if (keyCode == KeyCode.UP || keyCode == KeyCode.W) field.moveAll(Sides.UP);
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A) field.moveAll(Sides.LEFT);
        if (keyCode == KeyCode.DOWN || keyCode == KeyCode.S) field.moveAll(Sides.DOWN);
        if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) field.moveAll(Sides.RIGHT);
        updateScene();
    }

    private void updateScene() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j].getStyleClass().removeAll(labels[i][j].getStyleClass());
                if (field.get(j, i) != 1) {
                    labels[i][j].setText(field.get(j, i).toString());
                    labels[i][j].getStyleClass().addAll("chip" + field.get(j, i), "chip");
                } else {
                    labels[i][j].setText("");
                }
            }
        }
        scores.setText("Scores: " + field.getScores());
        if (field.isLose()) {
            rectangle.setVisible(true);
            gameOver.setVisible(true);
        }
    }
}
