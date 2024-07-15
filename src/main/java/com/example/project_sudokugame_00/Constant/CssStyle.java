package com.example.project_sudokugame_00.Constant;

public class CssStyle {
    public static final String defaultColor = "none";
    public static final String hoverColor = "#FF9999";

    public static final String hoverTextColor = "red";
    public static final String defaultTextColor = "white";


    public static final String tileOnHoverEvent = "-fx-background-color: " + hoverColor + ";" +
            "-fx-text-fill: " + hoverTextColor + ";" +
            "-fx-opacity: 0.5";

    public static final String tileOnDefaultEvent = "-fx-background-color: " + defaultColor + ";" +
            "-fx-text-fill: " + defaultTextColor + ";" +
            "-fx-opacity: 1";


}
