#!/bin/sh
#Created by Dayou Du on Dec 2th, 2017
javac -classpath `yarn classpath`:.:\
./myUDF/sentiment:/usr/share/cmf/common_jars/hive-exec-1.1.0-cdh5.5.0.jar\
 ./*.java

jar -cvf DateUDF.jar\
 ./*.class

rm ./*.class

hdfs dfs -rm DateUDF.jar

hdfs dfs -put DateUDF.jar

rm ./DateUDF.jar
