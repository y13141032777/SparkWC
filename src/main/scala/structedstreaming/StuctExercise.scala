package structedstreaming

import org.apache.spark.sql.SparkSession

object StuctExercise {

  def main (args: Array[String]): Unit = {

    val session = SparkSession.builder().master("local").appName("struct").getOrCreate()

    import  session.implicits._
    val frame = session.readStream.format("socket")
      .option("host", "hadoop01")
      .option("port", "7070")
      .load()
    // Split the lines into words
    val words = frame.as[String].flatMap(_.split(" "))

    val wordC = words.groupBy("value").count()

    wordC.writeStream.outputMode("complete")
      .format("console")
      .start().awaitTermination()

  }
}
