/*Created by Dayou Du on Dec 5th, 2017*/

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateBefore extends UDF{
	//if data is before basedate, return true
	//else return false
	public boolean evaluate(String sDate, String sBaseDate) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		Date date = f.parse(sDate);
		Date baseDate = f.parse(sBaseDate);
		return date.before(baseDate);
	}	
}
