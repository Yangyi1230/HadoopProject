drop table tweetori;

create external table tweetOri (id bigint, latitude double, longitude double, time string, content string)
row format delimited fields terminated by '\t'
location '/user/dd2645/tweetOrigin';

show tables;

select * from tweetori limit 5;

drop function EvalSentiment;

create function EvalSentiment as 'myUDF.sentiment.EvalSentiment'
using JAR 'hdfs:///user/dd2645/sentiment.jar';

drop table profiledtweet

create table profiledtweet as
select * from
(select id, substr(time,0,3) weekday, substr(time,12,2) hour, EvalSentiment(content) sentiment from
(select * from tweetori) tmp1
) tmp2
where sentiment != 0;

drop table normalizedtweet;

create table normalizedtweet as
select id, weekday, hour, sentiment/ stddev(sentiment) over () sentiment
from profiledtweet;

select hour, AVG(sentiment) avg_sentiment from
normalizedtweet
group by hour;

select weekday, AVG(sentiment) avg_sentiment from
normalizedtweet
group by weekday;


