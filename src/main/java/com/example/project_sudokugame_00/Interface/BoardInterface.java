package com.example.project_sudokugame_00.Interface;

import com.example.project_sudokugame_00.Constant.Message;
import javafx.scene.layout.GridPane;

public interface BoardInterface {
    interface EventListener {
        void onSudokuInput(int x, int y, int input);
    }

    interface View {
        void initiate(GridPane sudokuBoard);

        void setListener(EventListener listener);

        //update a single square after user input
        void updateSquare(int x, int y, int input);

        //update the entire board, such as after game completion or initial execution of the program
        void updateBoard(int[][] newGridState);

        void showDialog(Message message);

        void showError(String message);
    }
}
