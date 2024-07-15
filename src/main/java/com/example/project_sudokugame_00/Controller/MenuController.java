package com.example.project_sudokugame_00.Controller;

import com.example.project_sudokugame_00.Constant.GameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public static GameMode gameMode;

    @FXML
    private Button easyBtn;
    @FXML
    private Button mediumBtn;
    @FXML
    private Button hardBtn;


    @FXML
    public void setEasyMode(ActionEvent actionEvent) throws IOException {
        gameMode = GameMode.EASY;
        setGameMode(actionEvent);
    }

    public void setMediumMode(ActionEvent actionEvent) throws IOException {
        gameMode = GameMode.MEDIUM;
        setGameMode(actionEvent);
    }

    public void setHardMode(ActionEvent actionEvent) throws IOException {
        gameMode = GameMode.HARD;
        setGameMode(actionEvent);
    }

    public void continueBtn(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/example/project_sudokugame_00/View/GameView.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void setGameMode(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/project_sudokugame_00/View/GameView.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(event);
    }
}
