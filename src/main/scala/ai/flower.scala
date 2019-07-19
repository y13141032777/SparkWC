package ai

import org.apache.spark.{SparkConf, SparkContext}

object flower {

  def main (args: Array[String]): Unit = {
    //conf
    val conf:SparkConf = new SparkConf().setAppName("flower").setMaster("local")
    //context
    val sc:SparkContext = new SparkContext(conf)

    val rdd = sc.textFile("D:/a/iris.dat")
    val rdd1 = rdd.map(
      x => {
        val line: Array[String] = x.split(",")

        val a = line(0)
        val b = line(1)
        val c = line(2)
        val d = line(3)
        val e = line(4)

        (a, b, c, d, e)//改一行
//        (a, b)
      }
    )

    println("添加一行")
//    rdd1.reduceByKey()
//    val tests: Array[(Double, Double, Double, Double)] = rdd1.map(x => {
//      (x._1.toDouble, x._2.toDouble, x._3.toDouble, x._4.toDouble)
//    }).collect()



  //删了好几行



  }

}
