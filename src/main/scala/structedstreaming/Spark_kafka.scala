package structedstreaming

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Spark_kafka {

  def main (args: Array[String]): Unit = {

    val session = SparkSession.builder().master("local").appName("kafka").getOrCreate()
    val sc = session.sparkContext

    import session.implicits._

    val df1 = session
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "hadoop01:9092,hadoop02:9092")
      .option("subscribe", "topic0")
      .load()
    val value = df1.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)].groupBy("value").count


     value.writeStream.outputMode("complete")
      .format("console")
      .start().awaitTermination()
  }
}
