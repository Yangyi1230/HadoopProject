//created by Dayou Du on Dec 8th, 2017

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val data = sc.textFile("311data/output2/part-r-*")

val dataArray = data.map(s => s.split('\t'))

val model = KMeansModel.load(sc, "/user/dd2645/KMeansModel") 

case class Result (DataSourceId:Int, PredictionId:Int, Latitude:Double, Longtitude:Double, Score: Double)

val res = dataArray.map(a => Result(2, model.predict(Vectors.dense(a(3).toDouble,a(4).toDouble)),a(3).toDouble,a(4).toDouble,1)).toDF() 

res.coalesce(1).write.format("com.databricks.spark.csv").option("header","false").save("SparkOutput/out311")
