import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Spikerman
 * Created Date: 11/4/17
 */


//file format testing, checking if each record is ended with /n and if each attribute is splited by /t
public class Test {

    public static void main(String[] args) {
        try {
            File sourceFile = new File("data/tweets.txt");
            File targetFile = new File("output.csv");
            FileWriter fileWriter = new FileWriter(targetFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileReader fileReader = new FileReader(sourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\\t");
                String id = arr[0];
                String latitude = arr[1];
                String longitude = arr[2];
                String createdDateString = arr[3];
                DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                Date createdDate = dateFormat.parse(createdDateString);
                String text = arr[4];
                String userId = arr[5];
                System.out.println(latitude + " " + longitude);
                bufferedWriter.write(latitude + "," + longitude + "\n");
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
