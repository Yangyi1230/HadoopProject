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

##### TwitterAnalyzer:

TwitterKmeans.scala includes codes that train model for tweets clusting based on geolocation info.

**How to run?**
Copy the code to the spark-shell.


#### CombineAnalytic: ($PROJECTROOT)/CombineAnalytic
