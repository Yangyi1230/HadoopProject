import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class dataMapper extends Mapper<LongWritable, Text, Text, Text>{
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException{
                String[] tokens = (value.toString() + ",a").split(",");
                String uniqueKey = tokens[0].trim();
		String datetime = tokens[1].trim();
		String type = tokens[5].trim();
		String latitude = tokens[50].trim();
		String langitude = tokens[51].trim();
		if(uniqueKey.length() == 0 || datetime.length() == 0 ||
			type.length() == 0 || latitude.length() == 0 ||
			langitude.length() == 0 )return;
		if(latitude.charAt(0) > '9' || latitude.charAt(0) < '0' ||
			langitude.charAt(0) != '-')return;
		String info = datetime + "	" + type + "	" +
				latitude + "	" + langitude;
		context.write(new Text(uniqueKey), new Text(info));
        }
}
