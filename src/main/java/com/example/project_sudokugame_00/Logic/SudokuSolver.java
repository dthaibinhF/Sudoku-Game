package com.example.project_sudokugame_00.Logic;

import com.example.project_sudokugame_00.GameObject.Coordinate;

import static com.example.project_sudokugame_00.Logic.Logic.MAX_CELL;

public class SudokuSolver {

    public static boolean puzzleIsSolvable(int[][] puzzle, int numberCellHidden) {

        //step 1:
        Coordinate[] emptyCells = typeWriterEnumerate(puzzle, numberCellHidden);

        //I would like to stress that using lots of nested loops is only appropriate if you are certain that
        //the size of input O(n) is small.
        int index = 0;
        int input = 1;
        while (index < 2) {
            Coordinate current = emptyCells[index];
            input = 1;
            while (input < numberCellHidden) {
                puzzle[current.getX()][current.getY()] = input;
                //if puzzle is invalid....
                if (Logic.sudokuIsInvalid(puzzle)) {
                    //if we hit GRID_BOUNDARY and it is still invalid
                    if (index == 0 && input == MAX_CELL) {
                        //first cell can't be solved
                        return false;
                    } else if (input == MAX_CELL) {
                        //decrement by 2 since the outer loop will increment by 1 when it finishes; we want the previous
                        //cell
                        index--;
                    }

                    input++;
                } else {
                    index++;

                    if (index == numberCellHidden - 1) {
                        //last cell, puzzle solved
                        return true;
                    }

                    //input = 10 to break the loop
                    input = 10;
                }
                //move to next cell over
            }
        }

        return false;
    }

    private static Coordinate[] typeWriterEnumerate(int[][] puzzle, int numberCellHidden) {
        Coordinate[] emptyCells = new Coordinate[numberCellHidden];
        int iterator = 0;
        for (int y = 0; y < MAX_CELL; y++) {
            for (int x = 0; x < MAX_CELL; x++) {
                if (puzzle[x][y] == 0) {
                    emptyCells[iterator] = new Coordinate(x, y);
                    if (iterator == numberCellHidden - 1) return emptyCells;
                    iterator++;
                }
            }
        }
        return emptyCells;
    }

    public static int[][] solveCurrentGame(int[][] puzzle) {
        solveSudoku(puzzle, 0, 0);
        return puzzle;
    }

    public static boolean solveSudoku(int[][] grid, int row, int col) {

        /*if we have reached the 8th
           row and 9th column (0
           indexed matrix) ,
           we are returning true to avoid further
           backtracking       */
        if (row == MAX_CELL - 1 && col == MAX_CELL)
            return true;

        // Check if column value  becomes 9 ,
        // we move to next row
        // and column start from 0
        if (col == MAX_CELL) {
            row++;
            col = 0;
        }

        // Check if the current position
        // of the grid already
        // contains value >0, we iterate
        // for next column
        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {

            // Check if it is safe to place
            // the num (1-9)  in the
            // given row ,col ->we move to next column
            if (isRowValid(num, row, grid) &&
                    isColValid(num, col, grid) &&
                    isBlockValid(num, row, col, grid)) {

                /*  assigning the num in the current
                (row,col)  position of the grid and
                assuming our assigned num in the position
                is correct */
                grid[row][col] = num;

                // Checking for next
                // possibility with next column
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            /* removing the assigned num , since our
               assumption was wrong , and we go for next
               assumption with diff num value   */
            grid[row][col] = 0;
        }
        return false;
    }


    private static boolean isBlockValid(int value, int row, int col, int[][] puzzle) {
        int xIndex = (row / 3) * 3;
        int yIndex = (col / 3) * 3;

        int xIndexEnd = xIndex + 3;
        int yIndexEnd = yIndex + 3;

        while (xIndex < xIndexEnd) {
            while (yIndex < yIndexEnd) {
                if (puzzle[xIndex][yIndex] == value)
                    return false;
                yIndex++;
            }
            yIndex -= 3;
            xIndex++;
        }
        return true;
    }

    private static boolean isColValid(int value, int column, int[][] puzzle) {
        for (int row = 0; row < 9; row++) {
            if (puzzle[row][column] == value)
                return false;
        }
        return true;
    }

    private static boolean isRowValid(int value, int row, int[][] puzzle) {
        for (int col = 0; col < 9; col++) {
            if (puzzle[row][col] == value)
                return false;
        }

        return true;
    }

}
