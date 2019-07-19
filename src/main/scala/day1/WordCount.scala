package day1

import org.apache.hadoop.mapred.FileInputFormat
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {

    //conf
    val conf:SparkConf = new SparkConf().setAppName("WC").setMaster("local")
    //context
    val sc:SparkContext = new SparkContext(conf)

    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1))
      .reduceByKey(_+_).sortBy(_._2,false).saveAsTextFile(args(1))

    sc.stop()



  }

}
