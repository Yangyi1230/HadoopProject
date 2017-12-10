#!/bin/bash
#Created by Dayou Du on Nov 15th, 2017
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
