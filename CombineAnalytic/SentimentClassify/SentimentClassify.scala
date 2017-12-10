import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

// file format: latitude, longitude, score
val data = sc.textFile("SparkInput/sentiment.csv")

val dataArray = data.map(s => s.split(',').map(_.toDouble))

// val parsedData = dataArray.map(s => Vectors.dense(s(1),(2),s(3))).cache()

val model = KMeansModel.load(sc, "/user/dd2645/KMeansModel") 

case class Result (DataSourceId:Int, PredictionId:Int, Latitude:Double, Longtitude:Double, Score: Double)

val res = dataArray.map(a => Result(0, model.predict(Vectors.dense(a(2),a(3))),a(2),a(3),a(1))).toDF() 

res.coalesce(1).write.format("com.databricks.spark.csv").option("header","false").save("SparkOutput/outSentiment")
