#!/bin/bash
javac -classpath `yarn classpath`:.:\
./myUDF/sentiment:/usr/share/cmf/common_jars/hive-exec-1.1.0-cdh5.5.0.jar\
 ./myUDF/sentiment/EvalSentiment.java\
 ./myUDF/sentiment/vader/*.java\
 ./myUDF/sentiment/vader/lexicon/*.java\
 ./myUDF/sentiment/vader/text/*.java

jar -cvf sentiment.jar\
 ./myUDF/sentiment/*.class\
 ./myUDF/sentiment/vader/*.class\
 ./myUDF/sentiment/vader/lexicon/*.class\
 ./myUDF/sentiment/vader/text/*.class\
 ./resources

rm  ./myUDF/sentiment/*.class\
 ./myUDF/sentiment/vader/*.class\
 ./myUDF/sentiment/vader/lexicon/*.class\
 ./myUDF/sentiment/vader/text/*.class

hdfs dfs -rm sentiment.jar

hdfs dfs -put sentiment.jar

javac -classpath `yarn classpath` -d . dataMapper.java

javac -classpath `yarn classpath`:. -d . data.java

jar -cvf data.jar *.class

hadoop jar data.jar data /user/dd2645/311data/311.csv /user/dd2645/311data/output2
