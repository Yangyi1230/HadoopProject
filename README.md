# Hadoop Group Project: Regional Happiness Index Assessment in New York City

Created by Dayou Du, Shiyao Lei, Hao Chen at New York University

## Introduction

Happiness Index has always been difficult to measure, because it could be 
influnced by a lot of filelds and it also involves a large amount of data 
analysis. Traditionally, Happiness Index are measured by using the data from 
nationwide  survey which would consume a lot of human and material resources. 
In this project, we analyzed the Happiness Index combining MapReduce framwork 
and a distributed database model. We used Apache Hadoop MapReduce, Apache Hive 
and Apache Spark to profile and integrate our three data sources, which are 
311 Service Requests, NYPD Complaint Data Historic and geo-location based 
twitter sentiment analysis. Investigations of Happiness often relate to various 
indicators. We combined these three data with geological information and mainly 
focus on three indicators, which are safety, environment, and the sentiment 
revealed in Twitter.



## Note To Graders

The project repo is on DUMBO: `/home/dd2645/HadoopProject`

All the `.sh` shell scripts can be run in this position
 
Simply because this is where I created and maintained them:P

Well I tried to make all the data source paths in codes/scripts absolute paths, 
but I am not so sure whether I fixed all the paths/authority issues.



## Usage

The project contains four parts of codes:




## DataCollectParse: ($PROJECTROOT)/DataCollectParse

This part contains the data Collect/ETL codes

### 1.TwitterCollector:
TwitterCollector is a tool used to collect tweets through Twitter Streamline API. 
Specifically, only tweets posted at NYC with geolocation info will be collected. 

**How to run?**
The jar file is included in the TwitterCollector/artifact. 
To run the collector, simply type `java -jar filename.jar`



### 2.311Parser:

Profile the row data and clean out the irrelevant data information. Extract the 
unique key, created time, complaint type, longitude and latitude. Also filter 
out the transactions with missing fields.

**How to run?**

`cd ($PROJECTROOT/DataCollectParse/311Parser)`

`./compileAndRun.sh`

**Input File Location**

DUMBO HDFS: `/user/dd2645/311data/311.csv`



### 3.NYCCrimeParser:

profile the row data and clean out the irrelevant data information. Extract the 
unique key, create date\&time, law category, longitude and latitude. Also filter 
out the transactions with missing fields. 

**How to run?**

`cd ($PROJECTROOT/DataCollectParse/NYCCrimeParser)`

`./compileAndRun.sh`

**Input File Location**

DUMBO HDFS: `/user/dd2645/crimedata/NYCCrimeRaw.csv`




## SingleSourceAnalytic: ($PROJECTROOT)/SingleSourceAnalytic

Analytics based on single source data

### 1.TwitterSentiment:

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

**Input File Location**

DUMBO HDFS: `/user/dd2645/tweetOrigin`



### 2.NYCCrimeAnalyzer

Analyze the relationship between the amount of NYC Crime complaints and 
the specific day of one week or time in one day. 

**How to run?**

Run `($PROJECTROOT)/SingleSourceAnalytic/NYCCrimeAnalyzer/nyccrime_hive_command.sql` using Hive.

Note: to use the hive commands, please first build `($PROJECTROOT)/DateUDFs`

**Input File Location**

DUMBO HDFS: `/user/dd2645/crimedata/washed`



### 3.311Analyzer

Analyze the relationship between the amount of 311Complaints and the specific day of one week, the time in one day and the location.

**How to run?**

Run `($PROJECTROOT)/SingleSourceAnalytic/311Analyzer/311data_hive_command.sql` using Hive.

Note: to use the hive commands, please first build `($PROJECTROOT)/DateUDFs`

**Input File Location**

DUMBO HDFS:

`/user/dd2645/311data/output2`



## CombineAnalytic: ($PROJECTROOT)/CombineAnalytic

Analytics based on the combination of multi-data sources

### TwitterClustering:

TwitterKmeans.scala includes codes that train model for tweets clusting based on geolocation info.

**Input File Location**
Dumbo HDFS: `/user/hc2416/output.csv`

**How to run?**
Copy the code to the spark-shell.



### 311Classify, CrimeClassify, SentimentClassify

Classify the profield data into the clusters generated above.

**Input File Location**

DUMBO HDFS:

`/user/dd2645/311data/output2/part-r-*`

`/user/dd2645/SparkInput/crime.csv`

`/user/dd2645/SparkInput/sentiment.csv`

**Model Location**

DUMBO HDFS:

`/user/dd2645/KMeansModel`


**How to run?**
Run corresponding .scala files in spark-shell.



### Happiness Index Generator
This file include the code used to generated the final regional happiness index value.

**Input File Location**
Dumbo: `hc2416/RBDA/result.csv`

**How to run?**
Be sure that jar file and result.csv file are in the same file level, then run the following command
`java -jar ScoreGenerator-1.0-SNAPSHOT-shaded.jar`




## dateUDFs : ($PROJECTROOT)/DateUDFs

Contains the common date related hive UDF functions.

**How to run?**

`cd ($PROJECTROOT)/DateUDFs`

`./compileAndPack.sh`




## Credits

The java version of VADER is based on Nuno A. C. Henriques's project [nunoachenriques.net]

And also based on Hutto's original Python project VADER
@see <a href="http://comp.social.gatech.edu/papers/icwsm14.vader.hutto.pdf">VADER:
A Parsimonious Rule-based Model for Sentiment Analysis of Social Media Text</a>

