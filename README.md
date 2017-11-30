## Hadoop Group Project:

Created by Shiyao Lei, Dayou Du, Hao Chen at New York University

### Introduction



### Usage

The project contains three parts of codes:

#### DataCollectParse: ($PROJECTROOT)/DataCollectParse

##### TwitterCollector:
TwitterCollector is a tool used to collect tweets through Twitter Streamline API. 
Specifically, only tweets posted at NYC with geolocation info will be collected. 

**How to run?**
The jar file is included in the TwitterCollector/artifact. 
To run the collector, simply type `java -jar filename.jar`


##### 311Parser:

Describe the usage here(@Shiyao Lei)

##### NYCCrimeParser:



#### SingleSourceAnalytic: ($PROJECTROOT)/SingleSourceAnalytic

##### TwitterSentiment:

TwitterSentiment contains two parts:
1. Build python package VADER in java, and create a UDF in Hive.

**How to run?** 

	cd ($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment

	./compileAndPack.sh

Then the .jar package which contains sentiment module will be on HDFS 

1. A Hive SQL command file that perform sentiment analytic on tweets, and calcualte the
normalized average happyness polarity on differenct workdays an different day hours.

**How to run?**

Run ``($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment/sentiment_hive_command.sql'' using Hive.

##### TwitterAnalyzer:

TwitterKmeans.scala includes codes that train model for tweets clusting based on geolocation info.

**How to run?**
Copy the code to the spark-shell.


#### CombineAnalytic: ($PROJECTROOT)/CombineAnalytic
