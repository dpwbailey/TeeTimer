package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button generateScheduleButton;

    @FXML
    void generateSchedule(MouseEvent event) throws IOException {

        Stage thisStage = (Stage) generateScheduleButton.getScene().getWindow();
        Parent scheduleScreen = FXMLLoader.load(getClass().getResource("displaySchedule.fxml"));
        thisStage.setScene(new Scene(scheduleScreen, 750, 500));
    }

}
