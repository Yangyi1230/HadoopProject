drop table 311complainTable;

create external table 311complainTable (id bigint, time string, type string, latitude double, longitude double)
row format delimited fields terminated by '\t'
location '/user/dd2645/311data/output2';

show tables;

select * from 311complaintable limit 5;

drop function getWeekday;

create function getWeekday as 'getWeekday'
using JAR 'hdfs:///user/dd2645/DateUDF.jar';

drop table profiled311;

create table profiled311 as
select * from
(select id, substr(time,11,12) hour, getWeekday(substr(time,1,10)) weekday from
311complaintable ) tmp1;

select * from profiled311 limit 5;

drop table countweekday;

create table countweekday as
select weekday, COUNT(id) from
profiled311
group by weekday;
