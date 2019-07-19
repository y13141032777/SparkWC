package structedstreaming

import org.apache.spark.sql.SparkSession

object Spark_kafka_proudce1 {

  def main (args: Array[String]): Unit = {

    val list =(1 to 100).toList

    val session = SparkSession.builder().master("local").appName("kafka").getOrCreate()

    val sc = session.sparkContext

    val rdd = sc.parallelize(list).map(x=>(x,x))
    import session.implicits._
    val df = rdd.toDF("key","value")

    while(true){

      df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
        .write
        .format("kafka")
        .option("kafka.bootstrap.servers", "hadoop01:9092,hadoop02:9092")
        .option("topic", "topic0")
        .save()

      Thread.sleep(2000)
    }

  }
}
