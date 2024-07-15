package com.example.project_sudokugame_00.Interface;

import com.example.project_sudokugame_00.Constant.Message;
import com.example.project_sudokugame_00.GameObject.Coordinate;
import com.example.project_sudokugame_00.GameObject.SudokuHashMap;
import com.example.project_sudokugame_00.GameObject.SudokuTextField;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import static com.example.project_sudokugame_00.GameObject.Messenger.showCompleteGDialog;

public class SudokuBoard implements BoardInterface.View, EventHandler<KeyEvent>, BoardInterface {

    private final SudokuHashMap textFieldCoordinates;
    private BoardInterface.EventListener listener;
    //    F99AAA

    public SudokuBoard(GridPane sudokuBoard) {
        textFieldCoordinates = new SudokuHashMap();
        initiate(sudokuBoard);
    }

    @Override
    public void initiate(GridPane sudokuBoard) {
        SudokuHashMap textFieldCoordinates = this.textFieldCoordinates;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                SudokuTextField tile = new SudokuTextField(x, y);
                styleSudokuTile(tile);
                tile.setOnKeyPressed(this);
                handleTileMouseEnter(tile);
                textFieldCoordinates.put(new Coordinate(x, y), tile);
                sudokuBoard.add(tile, x, y);
            }
        }
    }

    private void handleTileMouseEnter(SudokuTextField tile) {
        tile.setOnMouseEntered(mouseEvent -> setTileOnHover(tile.getX(), tile.getY()));
        tile.setOnMouseExited(mouseEvent -> setTileOnDefault(tile.getX(), tile.getY()));
    }

    private void setTileOnDefault(int xOfTile, int yOfTile) {
        textFieldCoordinates.setOnDefaultEvent(xOfTile, yOfTile);
    }

    private void setTileOnHover(int xOfTile, int yOfTile) {
        textFieldCoordinates.setOnHoverEvent(xOfTile, yOfTile);
    }

    private void styleSudokuTile(SudokuTextField tile) {
        Font numberFont = new Font(20);
        tile.setFont(numberFont);
        tile.setPrefHeight(50);
        tile.setPrefWidth(50);
        tile.setBackground(Background.EMPTY);
        tile.setAlignment(Pos.CENTER);
    }


    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            if (keyEvent.getText().equals("0")
                    || keyEvent.getText().equals("1")
                    || keyEvent.getText().equals("2")
                    || keyEvent.getText().equals("3")
                    || keyEvent.getText().equals("4")
                    || keyEvent.getText().equals("5")
                    || keyEvent.getText().equals("6")
                    || keyEvent.getText().equals("7")
                    || keyEvent.getText().equals("8")
                    || keyEvent.getText().equals("9")
            ) {
                int value = Integer.parseInt(keyEvent.getText());
                ((TextField) keyEvent.getSource()).setText(keyEvent.getText());
                handleInput(value, keyEvent.getSource());
            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                ((TextField) keyEvent.getSource()).setText("");
                handleInput(0, keyEvent.getSource());
            } else {
                ((TextField) keyEvent.getSource()).setText("");
            }
        }
        keyEvent.consume();
    }

    private void handleInput(int value, Object source) {
        listener.onSudokuInput(((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                value);
    }

    @Override
    public void setListener(BoardInterface.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateSquare(int x, int y, int input) {
        SudokuTextField tile = textFieldCoordinates.get(new Coordinate(x, y));
        String value = Integer.toString(
                input
        );

        if (value.equals("0")) value = "";
        tile.textProperty().setValue(value);
    }

    @Override
    public void updateBoard(int[][] unsolvedGame) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            for (int yIndex = 0; yIndex < 9; yIndex++) {
                TextField tile = textFieldCoordinates.get(new Coordinate(xIndex, yIndex));

                String value = Integer.toString(
                        unsolvedGame[xIndex][yIndex]
                );

                if (value.equals("0")) value = "";

                tile.setText(value);

                if (tile.getText().isEmpty()) {
                    tile.setStyle("-fx-opacity: 1");
                    tile.setEditable(true);
                } else {
                    tile.setStyle("-fx-opacity: 0.8;");
                    tile.setEditable(false);
                }
            }
        }
    }

    @Override
    public void showDialog(Message message) {
        if (message == Message.COMPLETE) {
            showCompleteGDialog();
        }
    }

    @Override
    public void showError(String message) {

    }
}
