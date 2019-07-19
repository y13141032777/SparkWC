package day1

import java.net.URL
import java.util.regex.Pattern

import org.apache.spark.{Partitioner, SparkConf, SparkContext}

import scala.collection.mutable

object Top3 {

  //文件参考access.txt,统计各学科访问热度前三名的模块。
  //最后统计结果类似
  //ArrayBuffer((http://h5.learn.com/h5/course.shtml,47), (http://h5.learn.com/h5/teacher.shtml,17)...)

  def main(args: Array[String]): Unit = {
    //conf
    val conf:SparkConf = new SparkConf().setAppName("top3").setMaster("local[*]")
    //context
    val sc:SparkContext = new SparkContext(conf)

    //统计各学科访问热度top3
    val res = sc.textFile("D:/a/access.txt").map((x:String) =>(x.split("\t")(1),1)).reduceByKey(_+_)
      .map(f = x => {
        val url = x._1
        val count = x._2
        val sub = new URL(url).getHost
        (sub,url, count)
      }).groupBy(_._1)
      .mapValues(_.toList.sortBy(_._3).reverse.take(3))

    println("============================================================")

    val res1 = sc.textFile("D:/a/access.txt").map((x:String) =>(x.split("\t")(1),1)).reduceByKey(_+_)
      .map(f = x => {
        val url = x._1
        val count = x._2
        val sub = new URL(url).getHost
        (sub,(url, count))
      }).cache()

      val subjects =res1.keys.distinct().collect()
      res1.partitionBy(new SubPartitioner(subjects))
        .mapPartitions(it=>
      it.toList.sortBy(_._2._2).reverse.take(3).toIterator
      )


  }
}

 class SubPartitioner(subject:Array[String])  extends Partitioner{
   private  val subMap = new mutable.HashMap[String,Int]()

   var i =0
   for(sub <- subject){
     subMap.put(sub,i)
     i+=1
   }

   override def numPartitions: Int = subject.length

   override def getPartition(key: Any): Int = subMap.getOrElse(key.toString,0)
 }