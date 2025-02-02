package com.example.project_sudokugame_00.Logic;

import com.example.project_sudokugame_00.Constant.Block;
import com.example.project_sudokugame_00.Constant.Message;
import com.example.project_sudokugame_00.Interface.BoardInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.project_sudokugame_00.Controller.MenuController.gameMode;
import static com.example.project_sudokugame_00.Logic.SudokuGenerator.getGameGrid;

public class Logic implements BoardInterface.EventListener {
    public static final int MAX_CELL = 9;

    private static BoardInterface.View view;
    private static int[][] gameGrid;
    public static int[][] solvedGrid;

    public Logic(BoardInterface.View view) {
        this.view = view;
        gameGrid = getGameGrid();
        view.updateBoard(gameGrid);
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        gameGrid[x][y] = input;
        if (checkForCompletion(gameGrid)) {
            solvedGrid = gameGrid;
            view.showDialog(Message.COMPLETE);
        } else view.updateSquare(x, y, input);
    }

    public static boolean checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid)) return false;
        if (tilesAreNotFilled(grid)) return false;
        return true;
    }

    private static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < MAX_CELL; xIndex++) {
            for (int yIndex = 0; yIndex < MAX_CELL; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (squaresAreInvalid(grid)) return true;
        else return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        //top three squares
        if (blockOfSquaresIsInvalid(Block.TOP, grid)) return true;

        //middle three
        if (blockOfSquaresIsInvalid(Block.MIDDLE, grid)) return true;

        //bottom three
        if (blockOfSquaresIsInvalid(Block.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean blockOfSquaresIsInvalid(Block block, int[][] grid) {
        switch (block) {
            case TOP:
                //x FIRST = 0
                if (squareIsInvalid(0, 0, grid)) return true;
                //x SECOND = 3
                if (squareIsInvalid(0, 3, grid)) return true;
                //x THIRD = 6
                if (squareIsInvalid(0, 6, grid)) return true;

                //Otherwise squares appear to be valid
                return false;

            case MIDDLE:
                if (squareIsInvalid(3, 0, grid)) return true;
                if (squareIsInvalid(3, 3, grid)) return true;
                if (squareIsInvalid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if (squareIsInvalid(6, 0, grid)) return true;
                if (squareIsInvalid(6, 3, grid)) return true;
                if (squareIsInvalid(6, 6, grid)) return true;
                return false;

            default:
                return false;
        }

    }

    private static boolean squareIsInvalid(int yIndex, int xIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {

            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }

            //reset x to original value by subtracting by 2
            xIndex -= 3;

            yIndex++;
        }

        //if square has repeats, return true
        if (collectionHasRepeats(square)) return true;
        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < MAX_CELL; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < MAX_CELL; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < MAX_CELL; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < MAX_CELL; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }
            if (collectionHasRepeats(row)) return true;
        }
        return false;
    }

    private static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index = 1; index <= MAX_CELL; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }
        return false;
    }
}
