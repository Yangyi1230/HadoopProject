import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class getWeekday extends UDF{
	public String evaluate(String datetime) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		Calendar cal = Calendar.getInstance();
		Date dates = null;
		dates = f.parse(datetime);
		cal.setTime(dates);
		int d = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(d < 0) d = 0;
		return weekDays[d];
	}	
}
