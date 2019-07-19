package day1

import java.util.regex.Pattern

import org.apache.hadoop.mapred.lib.HashPartitioner
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object test {
  def main(args: Array[String]): Unit = {
//    val url="http://java.learn.com/java/javaee.shtml"
//    val reg ="^(http://)([a-z]*)(\\.)(.*)$"
//
//    println(url.matches(reg))
//
//    val sub = Pattern.compile(reg).matcher(url).group(1)
//      print(sub)
//    println(url.matches(reg))

//    val conf:SparkConf = new SparkConf().setAppName("top3").setMaster("local[*]")
//    //context
//    val sc:SparkContext = new SparkContext(conf)


//    //求各科的平均成绩
//    val rdd1 = sc.parallelize(List(("english",60)))
//    val rdd2 = sc.parallelize(List(("english",66),("english",2)))
//    val rdd7 = sc.parallelize(List(("english",96)))
//    rdd1.join(rdd2).foreach(println)

//    println(rdd3)

//    rdd1.cogroup(rdd2).foreach(println)

//    val rdd4:RDD[(String,(Int,Option[Int]))] = rdd1.leftOuterJoin(rdd2)
//    val rdd5 = rdd1.rightOuterJoin(rdd2)
    //cogroup

    var ipLongNum = 0l

    println(ipLongNum.getClass)



////    val c =sc.
//       val a = sc.parallelize(List(("a",1),("a",2),("b",2),("b",3)))
////    val rdd7 = sc.parallelize(List(("english",60)))
//       val b = sc.parallelize(List(("a",1),("a",2),("c",1)))
////      a.join(b).foreach(println)
////      a.leftOuterJoin(b).foreach(println)
//      println("-------------")
////    a.rightOuterJoin(b).foreach(println)
////      a.cogroup(b).foreach(println)
//    val res = sc.textFile("D:/a/access.txt",2)
//    res.foreach(println)
//    val res1:RDD[()] = sc.parallelize(List(("a",1),("a",2)))
//    val res2 = res1.map(x=>x)
//    val res3 = res1.map(x=>x)
//      res1.count()
//    println(res2==res3)
//
//    sc.setCheckpointDir("hdfs://hadoop01:9000/ck20180929")
//    println(sc.getCheckpointDir.head)
//
//    res.checkpoint()
//    println(res.isCheckpointed)
//    res.take(1)
//
//    println(res.getCheckpointFile)



  }

}
