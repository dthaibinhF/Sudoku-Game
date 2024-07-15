package com.example.project_sudokugame_00.Logic;

import static com.example.project_sudokugame_00.Logic.Logic.MAX_CELL;

public class SudokuUtilities {
    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
        for (int xIndex = 0; xIndex < MAX_CELL; xIndex++) {
            for (int yIndex = 0; yIndex < MAX_CELL; yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    public static int[][] copyToNewArray(int[][] oldArray) {
        int[][] newArray = new int[MAX_CELL][MAX_CELL];
        copySudokuArrayValues(oldArray, newArray);
        return newArray;
    }
}
