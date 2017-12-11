//Created by Daayou Du on Nov 28th, 2017
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NYCCrimeMapper extends Mapper<LongWritable, Text, Text, Text>{
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException{
                String[] tokens = (value.toString() + ",a").split(",");
                String uniqueKey = tokens[0].trim();
		String date = tokens[1].trim();
		String time = tokens[2].trim();
		String latitude = tokens[21].trim();
		String langitude = tokens[22].trim();
		if(date.length() == 0 || time.length() == 0 || 
			latitude.length() == 0 || langitude.length() == 0)return;
		if(latitude.charAt(0) > '9' || latitude.charAt(0) < '0' || langitude.charAt(0) != '-')return;
		String info = date + "	" + time + "	" +
				latitude + "	"+ langitude;
		context.write(new Text(uniqueKey), new Text(info));
        }
}
