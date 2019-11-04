package sample;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class csvFunctions {
    private static final String SAMPLE_CSV_FILE_PATH = "C:\\Users\\dpwba\\IdeaProjects\\TeeTimer\\src\\Activities.csv";

    public static void main(String[] args) throws IOException {
        int count = 0;
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String activityType = csvRecord.get(0);
                String date = csvRecord.get(1);
                String favorite = csvRecord.get(2);
                String title = csvRecord.get(3);
                String distance = csvRecord.get(4);
                String calories = csvRecord.get(5);
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