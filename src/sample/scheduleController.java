package sample;

import java.nio.ByteBuffer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

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
    private ListView<?> customerFileListView;


    @FXML
    private TextField csvSearchTextField;

    @FXML
    private Button csvSearchButton;

    @FXML
    private Button generateScheduleButton;

    @FXML
    private Button fileToTableViewButton;

    @FXML
    void sendFileToTableView(MouseEvent event) {
        //get selected file from listview
        //send that file to table view arraylist
        //call setUpObservableList to update the tableview with the new file

    }


    @FXML
    void csvSearch(MouseEvent event) {
        //call downloadGarminData(user entered name)
        //call getCsvPaths to populate an ArrayList with all the file paths
        //read the data from the specific file path to get desired fields (call getName -> name and date)
        //populate listview with new arraylist of all the desired values

    }

    @FXML
    void generateSchedule(MouseEvent event) {

        //use the values in the tableview arraylist to generate a schedule
        //append that schedule to the Text Area
    }


    // private static final DataFormat format = new DataFormat("/Appointment");


    private static ObservableList<Appointment> observableAppointments = FXCollections
        .observableArrayList();


    public static void main(String[] args) {
        Application.launch(args);
    }

    /*@FXML
    void handleTableViewDrag(MouseEvent event) {
        Appointment selected = scheduleTable.getSelectionModel().getSelectedItem();
        Dragboard dragboard = scheduleTable.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        //content.putString("Test");
         content.put(format, selected);
        dragboard.setContent(content);
        event.consume();
    }*/

   /* @FXML
    void handleDragDrop(DragEvent event) {

}

    @FXML
    void handleDragDone(MouseEvent event) {

    }*/





    //Runs every time the scene for this controller is loaded.
    @FXML
    public void initialize() throws IOException {
        System.out.println("Initialize worked!");
        setUpObservableList();
        setupProductLineTable();
    }

    private void setUpObservableList() throws IOException {
        // Appointment testAppointment = new Appointment();

        intervalScheduler.main(null);

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
        // Create the FXMLLoader 
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/sample/schedule.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        VBox root = (VBox) loader.load(fxmlStream);

        // Create the Scene
        Scene scene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("TeeTimer");
        // Display the Stage
        stage.show();
    }
}