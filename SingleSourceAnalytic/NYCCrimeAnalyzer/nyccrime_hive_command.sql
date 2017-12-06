drop table nycCrimeOri;

create external table nycCrimeOri (id bigint, date string, time string , latitude double, longitude double)
row format delimited fields terminated by '\t'
location '/user/dd2645/crimedata/washed';

show tables;

select * from nycCrimeOri limit 5;

drop function DateBefore;

create function DateBefore as 'DateBefore'
using JAR 'hdfs:///user/dd2645/DateUDF.jar';

drop table profilednyccrime;

create table profilednyccrime as
select * from nycCrimeOri
where DateBefore("12/31/2007",date);


