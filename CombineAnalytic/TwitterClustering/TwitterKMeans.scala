import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

val data = sc.textFile("output.csv")
val parsedData = data.map(s => Vectors.dense(s.split(',').map(_.toDouble))).cache()

val numClusters = 100
val numIterations = 20
val clusters = KMeans.train(parsedData, numClusters, numIterations)

val WSSSE = clusters.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE)

clusters.save(sc, "result/output3")

case class Position (Latitude:Double, Longtitude:Double, Prediction:Int)

val posClass = parsedData.map(a => Position(a(0), a(1), clusters.predict(a))).toDF() 

posClass.show()

posClass.collect().forech(println)