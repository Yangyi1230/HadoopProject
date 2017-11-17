import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Spikerman
 * Created Date: 11/4/17
 */


//file format testing, checking if each record is ended with /n and if each attribute is splitted by /t
public class Test {


    public static void main(String[] args) {
        try {
            File file = new File("tweets.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int count = 0;

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
                count++;
            }
            fileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
