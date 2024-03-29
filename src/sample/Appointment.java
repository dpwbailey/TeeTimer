package sample;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Appointment extends intervalScheduler {

    private static String csvPath = "C:\\Users\\alann\\OneDrive\\Entrepreneurship\\TeeTimer\\src\\Activities.csv";
    //  Appointment appointment = new Appointment();
    private String name = "Default Name";
    private String preferredTime = "Default Preferred Time";
    private String averageRoundDuration = "Default Round Duration";
    private int numPlayers = 1;//Default Number of Players in Group
    //need to convert duration string from 1:00:00 format to a numeric value using convertDurationToInteger whenever called


    public static ArrayList<Appointment> appointmentArrayList = new ArrayList<>();


    public Appointment(String name,
        String averageRoundDuration) {
        this.name = name;
        this.averageRoundDuration = averageRoundDuration;
        //default constructor
    }

    public Appointment(String name, String preferredTime,
                       String averageRoundDuration, int numPlayers) throws IOException {

        this.name = name;
        this.preferredTime = preferredTime;
        this.averageRoundDuration = averageRoundDuration;
        this.numPlayers = numPlayers;

        /*Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        CSVParser csvParser;

        File file = new File(name + ".csv");
        if (file.exists()) {
            System.out.println("File exists");

            try {
                csvParser = new CSVParser(reader, CSVFormat.EXCEL.withFirstRecordAsHeader()
                        .withIgnoreHeaderCase().withTrim());

                for (CSVRecord csvRecord : csvParser) {
                    // Accessing values by the names assigned to each column
//          name = csvRecord.get("Name");
//          preferredTime = csvRecord.get("Preferred Time");
                    this.averageRoundDuration = csvRecord.get("Time");
                    //this.name = csvRecord.get("Name");
                    appointmentArrayList.add(this);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("File not found");
            appointmentArrayList.add(this);
        }*/

    }

    /*public Appointment[] makeAppointmentsForCSVinDirectory(String[] nameList) {
        String directoryCSVs[] = csvFunctions.getCsvPaths(nameList);
        Appointment appointmentList[] = new Appointment[nameList.length];
        for (int i = 0; i < nameList.length; i++) {
            //appointmentList[i] = new Appointment(getName(), get);
        }
        return null;
    }*/

    public static void setCsvPath(String csvPath) {
        Appointment.csvPath = csvPath;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public ArrayList<Appointment> getAppointmentArrayList() {
        return appointmentArrayList;
    }

    public void addToAppointmentArrayList(ArrayList<Appointment> appointmentArrayList, Appointment appointment) {
        this.appointmentArrayList = appointmentArrayList;
        appointmentArrayList.add(appointment);
    }

    /*
        public Appointment getAppointment() {
          return appointment;
        }

        public void setAppointment(Appointment appointment) {
          this.appointment = appointment;
        }
      */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getAverageRoundDuration() {
        return averageRoundDuration;
    }

    public void setAverageRoundDuration(String averageRoundDuration) {
        this.averageRoundDuration = averageRoundDuration;
    }

    public static String getCsvPath() {
        return csvPath;
    }

    @Override
    public String toString() {
        return "Appointment: " +
                " name='" + name + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                ", averageRoundDuration='" + averageRoundDuration + '\'' +
                ", numPlayers=" + numPlayers;
    }

    public static void main(String[] args) {
        try {
            Appointment appointment = new Appointment("Joe Smith", "9:00:00", "3:00:00", 4);
            System.out.println(appointment.toString());
            System.out.println(appointmentArrayList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
