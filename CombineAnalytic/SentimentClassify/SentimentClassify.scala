//Created by Dayou Du on Dec 9th, 2017

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val data = sc.textFile("/user/dd2645/SparkInput/sentiment.csv")

val dataArray = data.map(s => s.split(',').map(_.toDouble))

val model = KMeansModel.load(sc, "/user/dd2645/KMeansModel") 

case class Result (DataSourceId:Int, PredictionId:Int, Latitude:Double, Longtitude:Double, Score: Double)

val res = dataArray.map(a => Result(0, model.predict(Vectors.dense(a(2),a(3))),a(2),a(3),a(1))).toDF() 

res.coalesce(1).write.format("com.databricks.spark.csv").option("header","false").save("SparkOutput/outSentiment")
