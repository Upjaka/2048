package View;

import Model.Field;
import Model.Direction;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class FXController {
    private static Field field;
    private static Label[][] labels;

    @FXML
    private Button backButton;
    @FXML
    private BorderPane border;
    @FXML
    private StackPane gameOver;
    @FXML
    private Button restartButton;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label scores;

    public void startButtonClicked() {
        gameOver.setVisible(false);
        restartButton.setText("RESTART");
        field = new Field();
        labels = new Label[field.getSize()][field.getSize()];
        gridPane.getChildren().removeAll(gridPane.getChildren());
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j] = new Label();
                gridPane.add(labels[i][j], i, j);
                stylizeLabel(i, j);
            }
        }
        scores.setText("Scores: " + field.getScores());
        backButton.setOnAction(e -> backButtonClicked());
        border.setOnKeyReleased(this::keyReleased);
    }

    public void backButtonClicked() {
        field.back();
        updateScene();
        gameOver.setVisible(false);
    }

    public void keyReleased(KeyEvent keyEvent) {
        final KeyCode keyCode = keyEvent.getCode();
        if (keyCode == KeyCode.UP || keyCode == KeyCode.W) field.makeMove(Direction.UP);
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A) field.makeMove(Direction.LEFT);
        if (keyCode == KeyCode.DOWN || keyCode == KeyCode.S) field.makeMove(Direction.DOWN);
        if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) field.makeMove(Direction.RIGHT);
        updateScene();
    }

    private void updateScene() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                labels[i][j].getStyleClass().removeAll(labels[i][j].getStyleClass());
                stylizeLabel(i, j);
            }
        }
        scores.setText("Scores: " + field.getScores());
        if (field.isLose()) {
            gameOver.setVisible(true);
        }
    }

    private void stylizeLabel(int i, int j) {
        if (field.get(i, j) != 0) {
            labels[i][j].setText(field.get(i, j).toString());
            labels[i][j].getStyleClass().addAll("chip", "chip" + field.get(i, j));
            if (!field.getPrevious(i, j).equals(field.get(i, j))) spawnCheepAnimation(i, j);
        } else {
            labels[i][j].setText("");
        }
    }

    private void spawnCheepAnimation(int i, int j) {
        ScaleTransition st = new ScaleTransition(Duration.millis(100), labels[i][j]);
        st.setFromX(0.1);
        st.setToX(1.0);
        st.setFromY(0.1);
        st.setToY(1.0);
        st.play();
    }
}
