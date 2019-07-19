package ai

import scala.math.{pow, sqrt}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Flower_teacher {

  case class LabeledPoint(label: String, point: Array[Double])

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SimpleKNN").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")
    val K = 16

    //    0）分别读取测试数据、训练数据集；
    val filePath = "C:\\Users\\wangsu\\Desktop\\Spark MLlib\\iris.dat"
    val lines = sc.textFile(filePath)
    val rawRDD: RDD[LabeledPoint] = lines.map(line => {
      val arr: Array[String] = line.split(",")
      if (arr.length == 5)
        LabeledPoint(arr.last, arr.init.map(_.toDouble))
      else
        LabeledPoint("", arr.map(_.toDouble))
    })
    val testData: Array[LabeledPoint] = rawRDD.filter(_.label=="").collect()
    val sampleRDD: RDD[LabeledPoint] = rawRDD.filter(_.label!="")

    testData.foreach(elem => {
      // 1）计算测试数据(testData)与训练数据(sampleRDD)之间的距离；
      val allDists: RDD[(Double, String)] = sampleRDD.map(labeledpoint => {
        val point1: Array[Double] = elem.point
        val point2: Array[Double] = labeledpoint.point
        (getDistance(point1, point2), labeledpoint.label)
      })

      //2）按照距离排序；选取距离最小的K个点；
      val labels: Array[String] = allDists.sortBy(_._1).map(_._2).take(K)

      // 3）wordcount
      val result = labels.groupBy(x=>x).mapValues(_.length)
      println(s"point = ${elem.point.toBuffer}; label = ${elem.label}; result = $result")
    })

    def getDistance(x: Array[Double], y: Array[Double]): Double = {
      sqrt(x.zip(y).map(elem => pow(elem._1 - elem._2, 2)).sum)
    }

    sc.stop()
  }
}
