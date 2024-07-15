package com.example.project_sudokugame_00.GameObject;


import java.util.HashMap;


public class SudokuHashMap extends HashMap<Coordinate, SudokuTextField> {

    public void setOnHoverEvent(int xOfTile, int yOfTile) {
        setStyleColumnOnHover(yOfTile);
        setStyleRowOnHover(xOfTile);
        setStyleTileOnHoverSameValueOf(get(new Coordinate(xOfTile, yOfTile)).getText());
    }

    private void setStyleTileOnHoverSameValueOf(String value) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (get(new Coordinate(x, y)).getText().equals(value)) {
                    get(new Coordinate(x, y)).setStyleTileOnHover();
                }
            }
        }
    }

    private void setStyleRowOnHover(int xOfTile) {
        for (int yIndex = 0; yIndex < 9; yIndex++) {
            get(new Coordinate(xOfTile, yIndex)).setStyleTileOnHover();
        }
    }

    private void setStyleColumnOnHover(int yOfTile) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            get(new Coordinate(xIndex, yOfTile)).setStyleTileOnHover();
        }
    }

    public void setOnDefaultEvent(int xOfTile, int yOfTile) {
        setStyleColumnOnDefault(yOfTile);
        setStyleRowOnDefault(xOfTile);
        setStyleTileOnDefaultSameValueOf(get(new Coordinate(xOfTile, yOfTile)).getText());
    }

    private void setStyleTileOnDefaultSameValueOf(String value) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (get(new Coordinate(x, y)).getText().equals(value)) {
                    get(new Coordinate(x, y)).setStyleTileOnDefault();
                }
            }
        }
    }

    private void setStyleRowOnDefault(int xOfTile) {
        for (int yIndex = 0; yIndex < 9; yIndex++) {
            get(new Coordinate(xOfTile, yIndex)).setStyleTileOnDefault();
        }
    }

    private void setStyleColumnOnDefault(int yOfTile) {
        for (int xIndex = 0; xIndex < 9; xIndex++) {
            get(new Coordinate(xIndex, yOfTile)).setStyleTileOnDefault();
        }
    }
}
