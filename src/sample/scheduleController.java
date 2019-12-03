package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javafx.util.Duration;

public class scheduleController extends Application {

    @FXML
    private TableView<Appointment> scheduleTable;
    @FXML
    private TableColumn<Appointment, String> nameColumn;
    @FXML
    private TableColumn<Appointment, String> prefTimeColumn;
    @FXML
    private TableColumn<Appointment, String> aveRoundColumn;
    @FXML
    private TableColumn<Appointment, String> numOfPlayersColumn;

    @FXML
    private ComboBox<Integer> numOfPlayerComboBox;

    @FXML
    private TextArea scheduleTextArea;

    @FXML
    private TextField prefferedTimeTextField;

    @FXML
    private ListView<String> customerFileListView;

    @FXML
    private ChoiceBox<String> highlightSelector;

    @FXML
    private Label validationLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button showCsvFileButton;

    @FXML
    private TextField csvSearchTextField;

    @FXML
    private Label scheduleValidationLabel;

    @FXML
    private Button csvSearchButton;

    @FXML
    private Button generateScheduleButton;

    @FXML
    private Button fileToTableViewButton;

    private static ObservableList<String> observableListView = FXCollections
            .observableList(csvFunctions.fileNames);

    private static ObservableList<Appointment> observableAppointments = FXCollections
            .observableArrayList();

    ObservableList<Integer> numOfPlayersList =
            FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    ObservableList<String> highlightSelectorFill =
            FXCollections.observableArrayList("Distance", "Calories", "Time");

    public static String selectedFilePath;


    @FXML
    void sendFileToTableView(MouseEvent event) throws IOException {
        //get selected file from listview
        String selectedFile = customerFileListView.getSelectionModel().getSelectedItem();
        String testTimeSelection = prefferedTimeTextField.getText();

        if (!(selectedFile == null) && !(testTimeSelection.equals(""))) {

            for (String path : csvFunctions.filesPaths) {
                if (path.contains(selectedFile) && !(path.contains("out"))) {
                    selectedFilePath = path;
                    String fileName = csvFunctions.getName(selectedFilePath, "title");
                    String fileDuration = csvFunctions.getName(selectedFilePath, "duration");
                    int numOfPlayers = numOfPlayerComboBox.getSelectionModel().getSelectedItem();
                    String prefferedTime = prefferedTimeTextField.getText();

                    Appointment newAppointment = new Appointment(fileName, prefferedTime, fileDuration,
                            numOfPlayers);

                    Appointment.appointmentArrayList.add(newAppointment);
                    System.out
                            .println("Appointment array list for table view: " + Appointment.appointmentArrayList);

                    observableAppointments.add(newAppointment);

                   // Appointment.appointmentArrayList.clear();

                    createFileSentValidator(true);

                    //send that file to table view arraylist
                    //call setUpObservableList to update the tableview with the new file
                }
            }

        } else {
            createFileSentValidator(false);
        }
    }


    @FXML
    void csvSearch(MouseEvent event) throws IOException {
        //call downloadGarminData(user entered name)
        String enteredName = csvSearchTextField.getText();
        if (!enteredName.equals("")) {
            csvFunctions.downloadGarminData(enteredName);
            //call getCsvPaths to populate an ArrayList with all the file paths
            csvFunctions.getCsvPaths(csvFunctions.getDirectoryPath());

            createCsvSearchSuccessValidator(true);

            //read the data from the specific file path to get desired fields (call getName -> name and date)
            //populate listview with new arraylist of all the desired values
        } else {
            createCsvSearchSuccessValidator(false);
        }


    }

    @FXML
    void generateSchedule(MouseEvent event) {

        //Hard code schedule for now
        String tempSchedule = "1. Joe Smith -> 7:00 am Start time" + '\n'
                + "2. Bob John -> 7:35 am Start time" + '\n'
                + "3. Sam Walker -> 7:55 am Start time" + '\n'
                + "4. John Smith -> 8:15 am Start time" + '\n'
                + "5. Alan Samuel -> 8:50 am Start time" + '\n'
                + "6. Ron Don -> 9:20 am Start time" + '\n'
                + "7. Jake West -> 9:45 am Start time" + '\n'
                + "8. LeBron James -> 10:15 am Start time" + '\n'
                + "9. Blake Henderson -> 10:55 am Start time";

        scheduleTextArea.appendText(tempSchedule);

        if (!Appointment.appointmentArrayList.isEmpty()) {

      /*intervalSchedulerRevised.Job[][] testArray = intervalSchedulerRevised.generateSampleData();
      intervalSchedulerRevised.calcSchedule(testArray);
      scheduleTextArea.appendText(intervalSchedulerRevised.includedJobs.toString());*/

            //intervalSchedulerRevised.convertAppointmentArrayListToJobs(Appointment.appointmentArrayList);
            //use the values in the tableview arraylist to generate a schedule
            //append that schedule to the Text Area

            //show success on the screen when a schedule generates
            createScheduleSuccessValidator(true);
        } else {
            createScheduleSuccessValidator(false);
        }


    }

    @FXML
    void openCsvFile(MouseEvent event) throws IOException {
        String fileSelected = customerFileListView.getSelectionModel().getSelectedItem();
        Desktop desktop = Desktop.getDesktop();
        if (!(fileSelected == null)) {
            for (String path : csvFunctions.filesPaths) {
                if (path.contains(fileSelected)) {
                    selectedFilePath = path;
                    File file = new File(selectedFilePath);
                    desktop.open(file);
                }
            }
        }

    }

    @FXML
    void highlightFilesByCategory(MouseEvent event) {
//Highlight a file in the listview based on if that file fits the criteria set by the user
        //If duration is longer than 3 but less than 4 highlight all those files red


    }

    @FXML
    void resetProgram(MouseEvent event) throws IOException {
// get a handle to the stage
        Stage stage = (Stage) resetButton.getScene().getWindow();
        // do what you have to do
        stage.close();
        customerFileListView.getItems().clear();


        //Appointment.appointmentArrayList.clear();


        //stage.show();


        System.out
                .println("Appointment array list for table view before reload: " + Appointment.appointmentArrayList);

        Parent root = FXMLLoader.load(getClass().getResource("schedule.fxml"));
        stage.setTitle("TeeTimer");
        root.getStylesheets().add
                (scheduleController.class.getResource("teeTimer.css").toExternalForm());
        stage.setScene(new Scene(root, 640, 400));
        stage.show();

        System.out
                .println("Appointment array list for table view after reload: " + Appointment.appointmentArrayList);


    }

    private void setUpListView() {
        customerFileListView.getItems().clear();
        customerFileListView.setItems(observableListView);


    }

    private void setUpComboBox() {

        numOfPlayerComboBox.setItems(numOfPlayersList);
        numOfPlayerComboBox.getSelectionModel().selectFirst();

    }

    private void setUpChoiceBox() {
        highlightSelector.setItems(highlightSelectorFill);
        highlightSelector.getSelectionModel().selectFirst();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private FadeTransition loginFadeOut = new FadeTransition(Duration.millis(2000));

    // Transition effect for fading out Success/Failed indicators
    private FadeTransition fadeOut = new FadeTransition(
            Duration.millis(2000)
    );

    private void createFileSentValidator(boolean success) {
        if (success) {
            validationLabel.setText("Success");
        } else {
            validationLabel.setText("Select File To Send or "
                    + '\n' + "Add Preferred Time");
        }
        validationLabel.setVisible(true);
        loginFadeOut.playFromStart();

    }

    private void createScheduleSuccessValidator(boolean success) {
        if (success) {
            scheduleValidationLabel.setText("Success");
        } else {
            scheduleValidationLabel.setText("Schedule Wasn't Valid");
        }
        scheduleValidationLabel.setVisible(true);
        loginFadeOut.playFromStart();

    }

    private void createCsvSearchSuccessValidator(boolean success) {
        if (success) {
            validationLabel.setText("Success");
        } else {
            validationLabel.setText("Search Failed");
        }
        validationLabel.setVisible(true);
        loginFadeOut.playFromStart();

    }


    //Runs every time the scene for this controller is loaded.
    @FXML
    public void initialize() {
        System.out.println("Initialize worked!");
        // Loops through comboBox and adds values 1 to 10

        Appointment.appointmentArrayList.clear();
        observableAppointments.clear();
        observableListView.clear();
        csvFunctions.filesPaths.clear();
        csvFunctions.fileNames.clear();

        validationLabel.setVisible(false);
        scheduleValidationLabel.setVisible(false);
        loginFadeOut.setNode(scheduleValidationLabel);
        loginFadeOut.setFromValue(1.0);
        loginFadeOut.setToValue(0.0);
        loginFadeOut.setCycleCount(1);
        loginFadeOut.setAutoReverse(false);
        loginFadeOut.setNode(validationLabel);
        loginFadeOut.setFromValue(1.0);
        loginFadeOut.setToValue(0.0);
        loginFadeOut.setCycleCount(1);
        loginFadeOut.setAutoReverse(false);
        setUpChoiceBox();
        setUpComboBox();
        setUpObservableList();
        setupProductLineTable();
        csvFunctions.getCsvPaths(csvFunctions.getDirectoryPath());
        setUpListView();

    }

    private void setUpObservableList() {
        // Appointment testAppointment = new Appointment();

        // intervalScheduler.main(null);


        observableAppointments.addAll(Appointment.appointmentArrayList);

    }

    //Sets up each column of the table view to receive a field from the Appointment class
    private void setupProductLineTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        prefTimeColumn.setCellValueFactory(new PropertyValueFactory<>("preferredTime"));
        aveRoundColumn.setCellValueFactory(new PropertyValueFactory<>("averageRoundDuration"));
        numOfPlayersColumn.setCellValueFactory(new PropertyValueFactory<>("numPlayers"));
        scheduleTable.setItems(observableAppointments);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("schedule.fxml"));
        stage.setTitle("TeeTimer");
        root.getStylesheets().add
                (scheduleController.class.getResource("teeTimer.css").toExternalForm());
        stage.setScene(new Scene(root, 640, 400));
        stage.show();

    }
}