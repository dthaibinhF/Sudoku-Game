package com.example.project_sudokugame_00.GameObject;

import javafx.scene.control.TextField;

import static com.example.project_sudokugame_00.Constant.CssStyle.tileOnDefaultEvent;
import static com.example.project_sudokugame_00.Constant.CssStyle.tileOnHoverEvent;

public class SudokuTextField extends TextField {
    private int x;
    private int y;

    public SudokuTextField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /*
    For some reason, when I override these two functions, the TextFields stop duplicating numeric inputs...
     */
    @Override
    public void replaceText(int i, int i1, String s) {
        if (!s.matches("[0-9]")) {
            super.replaceText(i, i1, s);
        }
    }

    @Override
    public void replaceSelection(String s) {
        if (!s.matches("[0-9]")) {
            super.replaceSelection(s);
        }
    }

    public void setStyleTileOnHover() {
        setStyle(tileOnHoverEvent);
    }

    public void setStyleTileOnDefault() {
        setStyle(tileOnDefaultEvent);
    }
}
