package sample;

import static jdk.nashorn.internal.objects.Global.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class csvFunctions {

  private static File directoryPath = new File(
      "C:\\Users\\alann\\OneDrive\\Entrepreneurship\\TeeTimerJar");
  private static File activitiesCSV = new File(
      "C:\\Users\\Dan\\Documents\\GitHub\\TeeTimer\\src\\Activities.csv");
  private static String csvPath = "C:\\Users\\dpwba\\IdeaProjects\\TeeTimer\\src\\Activities.csv";

  public static List<String> fileNames = new LinkedList<>();
  public static List<String> filesPaths = new LinkedList<>();


  public static File getDirectoryPath() {
    return directoryPath;
  }


  public static String getCsvPaths(File folder) {
    String returnString = null;
    
    for (File fileEntry : folder.listFiles()) {
      String path = fileEntry.getName();
      returnString = path.replace(".csv", "");

      if (fileEntry.isDirectory()) {
        getCsvPaths(fileEntry);
      } else {
          if (fileEntry.getName().contains(".csv")) {
              fileNames.add(returnString);
              filesPaths.add(fileEntry.getPath());
          }
      }
    }
    System.out.println("filenames array: " + fileNames);
    return returnString;
  }

  //pass the argument -d 'Client's Name' into to check if file already exists. if it does, appends to end of clientname.csv
  //if it doesnt, creates new directory called Client's Name
  public static void downloadGarminData(String clientName) throws IOException {
    String args1 = " --username Thegolfguys1@gmail.com --password Cmlo1031";
    String args2 = " -d " + clientName; //"Firstname Lastname"
    //TODO:
    Runtime rt = Runtime.getRuntime();
    try {
      rt.exec(new String[]{"cmd.exe", "/c",
          "python C:\\Users\\dpwba\\IdeaProjects\\TeeTimer\\getgarmindata1.py" + args1 + args2});

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static String getName(String csvPath, String desiredField) {
    String activityType = "", date = "", favorite = "", title = "", distance = "", calories = "", duration = "", name = "";
    try (
        Reader reader = Files.newBufferedReader(Paths.get(csvPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        // Accessing Values by Column Index
        activityType = csvRecord.get(0);
        date = csvRecord.get(1);
        favorite = csvRecord.get(2);
        title = csvRecord.get(3);
        distance = csvRecord.get(4);
        calories = csvRecord.get(5);
        duration = csvRecord.get(6);
        name = "Default Name";
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    switch (desiredField) {
      case "activityType":
        return activityType;
      case "date":
        return date;
      case "favorite":
        return favorite;
      case "title":
        return title;
      case "distance":
        return distance;
      case "calories":
        return calories;
      case "duration":
        return duration;
      case "name":
        return name;
      default:
        return "invalid desired field selected";
    }
  }

  public static void createCustomerFile(String customerName, String preferredTime,
      String averageDuration) {

  }

  public static String[] toArray(CSVRecord rec) {
    String[] arr = new String[rec.size()];
    int i = 0;
    for (String str : rec) {
      arr[i++] = str;
    }
    return arr;
  }


  /**
   * creates a csv file for a client given the path to the csv file containing their data and a
   * string containing their full name should maybe edit to accept multiple clients at once
   *
   * @param fullName the client's name, which becomes the name of the CSV file
   * @return nothing, it just creates the CSV file itself
   */
  public static void addNamesToGarminCSV(Path dataSource, String fullName) throws IOException {
    Path newCSVpath = Paths.get(fullName + ".csv");
    CSVFormat format = CSVFormat.EXCEL.withHeader();
    try (
        Reader reader = Files.newBufferedReader(dataSource);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        BufferedWriter writer = Files.newBufferedWriter(newCSVpath, StandardCharsets.UTF_8);
        CSVPrinter printer = new CSVPrinter(writer, format);
    ) {
      printer.printRecords(csvParser.getRecords());
    }
  }

  /*
   * TODO: below function
   *
   *
   *
   *
   */
  public static void updateCsvFile(File f) throws Exception {
    CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.DEFAULT);

    List<CSVRecord> list = parser.getRecords();
    String edited = f.getAbsolutePath();

    f.delete();
    CSVPrinter printer = new CSVPrinter(new FileWriter(edited),
        CSVFormat.DEFAULT.withRecordSeparator(","));
    for (CSVRecord record : list) {
      String[] s = toArray(record);

      if (s[0].equalsIgnoreCase("Actual Text")) {
        s[0] = "Replacement Text";
      }
      print(printer, s);
    }
    parser.close();
    printer.close();

    System.out.println("CSV file was updated successfully !!!");
  }

  public static void main(String[] args) throws IOException {

//test making client csv file
    //  addNamesToGarminCSV(Paths.get(csvPath), "Dan Bailey");
    /*downloadGarminData("Sample Name");
    int count = 0;
    //String fieldName = getName(csvPath, "duration");
    //System.out.println(fieldName);
    try (
        Reader reader = Files.newBufferedReader(Paths.get(svPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
    ) {
      for (CSVRecord csvRecord : csvParser) {
        // Accessing Values by Column Index
              *//*  String activityType = csvRecord.get(0);
                String date = csvRecord.get(1);
                String favorite = csvRecord.get(2);
                String title = csvRecord.get(3);
                String distance = csvRecord.get(4);
                String calories = csvRecord.get(5);
               *//*
        String time = csvRecord.get(6);
        if (count > 0) {
          System.out.println("Record No - " + csvRecord.getRecordNumber());
          System.out.println("---------------");
          System.out.println("Group Number: " + count);
          System.out.println("Round Duration: " + time);
             *//*   System.out.println("Email : " + email);
                System.out.println("Phone : " + phone);
                System.out.println("Country : " + country);
                *//*
          System.out.println("---------------\n\n");
        }
        count++;

      }*/

  }
}