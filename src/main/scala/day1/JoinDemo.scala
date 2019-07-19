package day1

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

class JoinDemo {

  def main(args: Array[String]): Unit = {
    //创建sc
    val conf:SparkConf = new SparkConf().setAppName("CombineBykey").setMaster("local[*]")
    val sc:SparkContext = new SparkContext(conf)

    //求各科的平均成绩
    val rdd1 = sc.parallelize(List(("english",60),("math",80),("english",90),("chinese",70),("math",50)
      ,("math",80),("english",90),("math",80),("english",90),("art",90)))
    val rdd2 = sc.parallelize(List(("english",66),("math",88),("english",99),("chinese",77),("math",55)
      ,("math",88),("computer",77)))
    val rdd3:RDD[(String,(Int,Int))] =  rdd1.join(rdd2)
    val rdd4:RDD[(String,(Int,Option[Int]))] = rdd1.leftOuterJoin(rdd2)
    val rdd5 = rdd1.rightOuterJoin(rdd2)
    //cogroup

    val rdd6 =  rdd1.cogroup(rdd2)
    val rdd7 = sc.parallelize(List(("english",60)))
    val rdd8 = sc.parallelize(List(("english",66)))
    val rdd9 = rdd7.cogroup(rdd8)
    val rdd10 = rdd1.cogroup(rdd2,rdd7)
    //    println(rdd3.collect().toBuffer)
    //    println(rdd4.collect().toBuffer)
    //    println(rdd5.collect().toBuffer)
    //    println(rdd6.collect().toBuffer)
    //    println(rdd9.collect().toBuffer)
    println(rdd10.collect().toBuffer)
    //小作业：join算子的优化
  }
}
