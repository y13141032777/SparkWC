package day1

import org.apache.spark.{SparkConf, SparkContext}

object SparkZY1 {
  def main(args: Array[String]): Unit = {
    //conf
    val conf: SparkConf = new SparkConf().setAppName("top3").setMaster("local[*]")
    //context
    val sc: SparkContext = new SparkContext(conf)

    //加载、切分、缓存
    val src = sc.textFile("D:/a/cdn1.txt",3).saveAsTextFile("t1")
//      .map(_.split(" "))
    //计算独立IP数
//    println(src.map(_(0)).distinct.count)
    //统计每个视频独立IP数 List((baidu,1.1.1,1) (baidu,1.1.1,1)(baidu,2.1.1,1))
//    src.map(x=>(x(6),x(0),1)).groupBy(_._1).mapValues((_.toList.map(x=>(x._2,x._3)).groupBy(_._1).mapValues((x=>x.toIterator.map(_._2).toList.size))   ))
//      .mapValues(_.toList.sortBy(_._2)).foreach(println)

//    src.map(x=>(x(6),x(0),1)).groupBy(_._1).mapValues((_.toList.map(x=>(x._2,x._3)).groupBy(_._1).mapValues(_.size)))
//      .mapValues(_.toList.sortBy(_._2)).foreach(println)
    //统计一天中每个小时的流量
//    val rdd4 = src.map(x=>(x(3).split(":")(1),1)).reduceByKey(_+_).foreach(println)





  }
}
