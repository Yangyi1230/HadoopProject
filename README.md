## Hadoop Group Project:

Created by Shiyao Lei, Dayou Du, Hao Chen at New York University

### Introduction

Neighborhood quality has always been difficult to quantify. Tranditionally, 
neighborhood quality are measured by using the data from housing survey and 
urban development cusyomer satisfication survey. In this paper, we analyze 
the neighborhood quality and livibility based on three data source which are 
311 Service Requests, NYPD Complaint Data Historic and geolocation based twitter 
sentiment analysis. Investifations of neighborhood quality often relate to 
various indicators. Combining these three data with geological information 
and foucusing mainly in three indicators which are safety, environment, and 
residents' happiness level.




### Usage

The project contains four parts of codes:




#### DataCollectParse: ($PROJECTROOT)/DataCollectParse


##### 1.TwitterCollector:
TwitterCollector is a tool used to collect tweets through Twitter Streamline API. 
Specifically, only tweets posted at NYC with geolocation info will be collected. 

**How to run?**
The jar file is included in the TwitterCollector/artifact. 
To run the collector, simply type `java -jar filename.jar`



##### 2.311Parser:

Profile the row data and clean out the irrelevant data information. Extract the 
unique key, created time, complaint type, longitude and latitude. Also filter 
out the transactions with missing fields.

**How to run?**

`cd ($PROJECTROOT/DataCollectParse/311Parser)`

`./compileAndRun.sh`



##### 3.NYCCrimeParser:

profile the row data and clean out the irrelevant data information. Extract the 
unique key, create date\&time, law category, longitude and latitude. Also filter 
out the transactions with missing fields. 

**How to run?**

`cd ($PROJECTROOT/DataCollectParse/NYCCrimeParser)`

`./compileAndRun.sh`




#### SingleSourceAnalytic: ($PROJECTROOT)/SingleSourceAnalytic

##### 1.TwitterSentiment:

TwitterSentiment contains two parts:
1. Build java version of the python package VADER, and create a UDF in Hive.

   `cd ($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment`

   `./compileAndPack.sh`

   Then the .jar package which contains sentiment module will be on HDFS 

2. A Hive SQL command file that perform sentiment analytic on tweets, and calcualte the
  normalized average happyness polarity on differenct workdays an different day hours.
  It also create a table which contains geolocation and sentiment score for further use.

   Run `($PROJECTROOT)/SingleSourceAnalytic/TwitterSentiment/sentiment_hive_command.sql` using Hive.

   Note: to use the hive commands, please first build `($PROJECTROOT)/DateUDFs`



##### 2.NYCCrimeAnalyzer

Analyze the relationship between the amount of NYC Crime complaints and 
the specific day of one week or time in one day. 

**How to run?**

Run `($PROJECTROOT)/SingleSourceAnalytic/NYCCrimeAnalyzer/nyccrime_hive_command.sql` using Hive.



##### 3.311Analyzer

Analyze the relationship between the amount of 311Complaints and the specific day of one week, the time in one day and the location.

**How to run?**

Run `($PROJECTROOT)/SingleSourceAnalytic/311Analyzer/311data_hive_command.sql` using Hive.




#### CombineAnalytic: ($PROJECTROOT)/CombineAnalytic

##### TwitterClustering:

TwitterKmeans.scala includes codes that train model for tweets clusting based on geolocation info.

**How to run?**

Copy the code to the spark-shell.



##### 311Classify, CrimeClassify, SentimentClassify

Classify the profield data into the clusters generated above.

**Input File Location**
Dumbo HDFS: `/user/hc2416/output.csv`

**How to run?**
Run corresponding .scala files in spark-shell.



##### Happiness Index Generator
This file include the code used to generated the final regional happiness index value.

**Input File Location**
Dumbo: `hc2416/RBDA/result.csv`

**How to run?**
Be sure that jar file and result.csv file are in the same file level, then run the following command
`java -jar ScoreGenerator-1.0-SNAPSHOT-shaded.jar`





#### dateUDFs : ($PROJECTROOT)/DateUDFs

Contains the common date related hive UDF functions.

**How to run?**

`cd ($PROJECTROOT)/DateUDFs`

`./compileAndPack.sh`




### Credits

The java version of VADER is based on Nuno A. C. Henriques's project [nunoachenriques.net]

And also based on Hutto's original Python project VADER
@see <a href="http://comp.social.gatech.edu/papers/icwsm14.vader.hutto.pdf">VADER:
A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text</a>

