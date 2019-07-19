package Sql

import org.apache.spark.sql.SparkSession

object UDFdemo {

  def main(args: Array[String]): Unit = {
    val session=SparkSession.builder().appName("udf").master("local").getOrCreate()
    val sc=session.sparkContext
    val r1 =sc.textFile("D:/a/1.txt").flatMap(_.split(" ")).map((_,1))

    import session.implicits._
    val df = r1.toDF()
//    df.show()

    val f1=(word:String)=>{
      word.concat("-------")
    }

    session.udf.register("udf",f1)

    df.createOrReplaceTempView("words")

    session.sql("select udf(_1) from words").show()

  }
}
