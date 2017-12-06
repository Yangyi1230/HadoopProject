#!/bin/sh
javac -classpath `yarn classpath`:. ./*.java

jar -cvf NYCCrimeParser.jar ./*.class

rm ./*.class

hdfs dfs -rm -r crimedata/washed

hadoop jar NYCCrimeParser.jar NYCCrimeParser crimedata/NYCCrimeRaw.csv crimedata/washed

rm NYCCrimeParser.jar
