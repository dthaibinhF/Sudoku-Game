module com.example.project_sudokugame_00 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires fontawesomefx;

    opens com.example.project_sudokugame_00.Controller to javafx.fxml;
    opens com.example.project_sudokugame_00.Interface to javafx.fxml;

    exports com.example.project_sudokugame_00;
    exports com.example.project_sudokugame_00.Controller;
    exports com.example.project_sudokugame_00.Logic;
    exports com.example.project_sudokugame_00.Interface;
    exports com.example.project_sudokugame_00.GameObject;
    exports com.example.project_sudokugame_00.Constant;
}