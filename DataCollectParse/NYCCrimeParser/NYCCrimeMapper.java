import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NYCCrimeMapper extends Mapper<LongWritable, Text, Text, Text>{
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException{
                String[] tokens = value.toString().split(",");
                String uniqueKey = tokens[0].trim();
                String info = tokens[1].trim() + "	" + tokens[5].trim() + "	" + tokens[50].trim() + "	" + tokens[51].trim();
		context.write(new Text(uniqueKey), new Text(info));
        }
}
