package myUDF.sentiment;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.*;

import myUDF.sentiment.vader.*;
import myUDF.sentiment.vader.text.*;
import myUDF.sentiment.vader.lexicon.*;

public class EvalSentiment extends UDF {
	public double evaluate(Text str) {
		if (str == null) {
			return 0;
		}
		SentimentAnalysis sa = new SentimentAnalysis(new English(), new TokenizerEnglish());
		String content = str.toString();
		return sa.getSentimentAnalysis(content).get("compound");
	} 
}
