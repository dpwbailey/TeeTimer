package sample;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class csvFunctions {
    private static File directoryPath = new File("C:\\Users\\dpwba\\IdeaProjects\\TeeTimer\\src");
    private static String csvPath = "C:\\Users\\dpwba\\IdeaProjects\\TeeTimer\\src\\Activities.csv";


    public static String[] getCsvPaths(int i){
        String[] pathList = new String[64];
        while (i > 0){
        pathList[i] = directoryPath.getPath();
        }
        return pathList;
    }

    public static String getName(String csvPath, String desiredField){
        String activityType="", date="", favorite="", title="", distance="", calories="", duration = "", name = "";
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

        }
        catch(Exception e){
            e.printStackTrace();
        }
        switch(desiredField){
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

    public static void main(String[] args) throws IOException {
        int count = 0;
        String fieldName = getName(csvPath, "duration");
        System.out.println(fieldName);
        try (
                Reader reader = Files.newBufferedReader(Paths.get(csvPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
              /*  String activityType = csvRecord.get(0);
                String date = csvRecord.get(1);
                String favorite = csvRecord.get(2);
                String title = csvRecord.get(3);
                String distance = csvRecord.get(4);
                String calories = csvRecord.get(5);
               */
                String time = csvRecord.get(6);


                if (count > 0) {
                    System.out.println("Record No - " + csvRecord.getRecordNumber());
                    System.out.println("---------------");
                    System.out.println("Group Number: " + count);
                    System.out.println("Round Duration: " + time);

             /*   System.out.println("Email : " + email);
                System.out.println("Phone : " + phone);
                System.out.println("Country : " + country);
                */

                    System.out.println("---------------\n\n");
                }
                count++;
            }
        }
    }
}