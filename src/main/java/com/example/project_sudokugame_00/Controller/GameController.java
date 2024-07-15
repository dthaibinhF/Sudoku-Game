package com.example.project_sudokugame_00.Controller;

import com.example.project_sudokugame_00.Interface.BoardInterface;
import com.example.project_sudokugame_00.Interface.SudokuBoard;
import com.example.project_sudokugame_00.Logic.Logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static final double BOARD_X_TO_Y = 450;
    @FXML
    public AnchorPane background;
    @FXML
    private GridPane SudokuBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GridPane sudokuBoard = this.SudokuBoard;
        drawGridLines(background);
        com.example.project_sudokugame_00.Interface.SudokuBoard uiImpl;
        uiImpl = new SudokuBoard(sudokuBoard);
        BoardInterface.EventListener uiLogic = new Logic(uiImpl);
        uiImpl.setListener(uiLogic);
    }

    private void drawGridLines(AnchorPane root) {
        double X = 26;
        double Y = 101;
        int index = 0;
        while (index < 10) {
            int thickness;
            if (index == 0 || index == 3 || index == 6 || index == 9) {
                thickness = 4;
            } else {
                thickness = 2;
            }

            Rectangle verticalLine = getLine(
                    X + 50 * index,
                    Y,
                    BOARD_X_TO_Y,
                    thickness
            );

            Rectangle horizontalLine = getLine(
                    X,
                    Y + 50 * index,
                    thickness,
                    BOARD_X_TO_Y
            );

            root.getChildren().addAll(
                    verticalLine,
                    horizontalLine
            );

            index++;
        }
    }

    public Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();

        line.setX(x);
        line.setY(y);

        line.setHeight(height);
        line.setWidth(width);

        line.setFill(Color.web("#FF9999"));
        return line;

    }
}
