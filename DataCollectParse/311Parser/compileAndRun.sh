#!/bin/sh
javac -classpath `yarn classpath`:. ./*.java

jar -cvf 311Parser.jar ./*.class

rm ./*.class

hdfs dfs -rm -r 311data/output2

hadoop jar 311Parser.jar data 311data/311.csv 311data/output2

