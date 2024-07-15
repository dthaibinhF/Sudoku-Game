package com.example.project_sudokugame_00.Logic;

import com.example.project_sudokugame_00.Constant.GameMode;
import com.example.project_sudokugame_00.GameObject.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.project_sudokugame_00.Controller.MenuController.gameMode;
import static com.example.project_sudokugame_00.Logic.Logic.MAX_CELL;

public class SudokuGenerator {

    public static int[][] getGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;

        //note: not actually solvable until the algorithm below finishes!
        int[][] solvableArray = new int[MAX_CELL][MAX_CELL];

        while (solvable == false) {

            //Take values from solvedGame and write to new unsolved; i.e. reset to initial state
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

            //remove random numbers
            int index = 0;
            while (index < numberHiddenCell(gameMode)) {
                int xCoordinate = random.nextInt(MAX_CELL);
                int yCoordinate = random.nextInt(MAX_CELL);

                if (solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[MAX_CELL][MAX_CELL];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);
            //check if result is solvable
            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved, numberHiddenCell(gameMode) );

            //TODO Delete after tests
            System.out.println(solvable);
        }

        return solvableArray;

    }

    private static int numberHiddenCell(GameMode gameMode) {
        if (gameMode == GameMode.HARD)
            return 40;
        else if (gameMode == GameMode.MEDIUM)
            return 30;
        else return 20;
    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[MAX_CELL][MAX_CELL];

        //Value represents potential values for each square. Each value must be allocated 9 times.
        for (int value = 1; value <= MAX_CELL; value++) {
            //allocations refers to the number of times in which a square has been given a value.
            int allocations = 0;

            //If too many allocation attempts are made which end in an invalid game, we grab the most recent
            //allocations stored in the List below, and reset them all to 0 (empty).
            int interrupt = 0;

            //Keep track of what has been allocated in the current frame of the loop
            List<Coordinate> allocTracker = new ArrayList<>();

            //As a failsafe, if we keep rolling back allocations on the most recent frame, and the game still
            //keeps breaking, after 500 times we reset the board entirely and start again.
            int attempts = 0;

            while (allocations < MAX_CELL) {

                //error solution
                if (interrupt > 200) {
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    if (attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                int xCoordinate = random.nextInt(MAX_CELL);
                int yCoordinate = random.nextInt(MAX_CELL);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    //if value results in an invalid game, then re-assign that element to 0 and try again
                    if (Logic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    }
                    //otherwise, indicate that a value has been allocated, and add it to the allocation tracker.
                    else {
                        allocTracker.add(new Coordinate(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }
        }
        return newGrid;

    }

    private static void clearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < MAX_CELL; xIndex++) {
            for (int yIndex = 0; yIndex < MAX_CELL; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
