package test_spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON


object test_spark {

  def main(args: Array[String]): Unit = {
    //需求：release_status =1 的sid数量
    val  conf = new SparkConf().setMaster("local").setAppName("777")
    val  sc=new SparkContext(conf)
    val  session= SparkSession.builder().config(conf).getOrCreate()
    val  df=session.read.parquet("D:/a.txt")

    //sql
//    df.createOrReplaceTempView("adds")
//    val sql="select exts from adds where release_status ='01'"
//    val json =session.sql(sql).limit(1).show()

   val json= df.select("exts").limit(2).rdd.map(
     a=>{
      val b=JSON.parseFull(a.getString(0))
       b.getOrElse(0)
     }
//
   ).foreach(println)






    //core
//    import  session.implicits._
//    val rdd= df.rdd
//    val count= rdd.filter(_.get(3) =="01")
//    print("RDD计算结果："+count)

//    JsonUtils.sample()



    session.close()
  }


}
