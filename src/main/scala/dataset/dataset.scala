package dataset

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession


object dataset {

  def main(args: Array[String]): Unit = {

//    val session = SparkSession.builder().appName("sparksqlDemo")
//      .master("local").getOrCreate()

    val conf:SparkConf = new SparkConf().setAppName("top31").setMaster("local[*]")
    //context
    val sc:SparkContext = new SparkContext(conf)


    val rdd = sc.textFile("D:/a/1.txt").flatMap(_.split(" ")).map((_,1))



//      .foreach(println)

    val session=SparkSession.builder().config(conf).getOrCreate()

    import session.implicits._
    val df = rdd.toDF("word","count")

    df.createGlobalTempView("words")
    df.createOrReplaceTempView("words")

    val sql="select * from words"
    val dataFrame = session.sql(sql)

    df.show()

    sc.stop()
    session.stop()

  }

}
