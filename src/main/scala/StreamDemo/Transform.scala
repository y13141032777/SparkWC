package StreamDemo

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object Transform {

  def main(args: Array[String]): Unit = {
    val conf =new SparkConf().setAppName("trans").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val ssc = new StreamingContext(sc,Seconds(10))

    //黑名单数据集
    val arr=Array(("tony",true),("mark",true))
    val black=sc.parallelize(arr)

    val read = ssc.socketTextStream("hadoop01",8989)

    val user =read.map(x=>{
      val name = x.split(" ")(0)
      val info = x.split(" ")(1)
      (name,info)
    })

//    val result=  user.transform(userInfoRdd=>{
//      val resRDD: RDD[(String, (String, Option[Boolean]))] = userInfoRdd.leftOuterJoin(black)
//      //把黑名单用户过滤掉
//      val filteredRDD = resRDD.filter(!_._2._2.getOrElse(false))
//      //过滤黑名单之后的数据，保持原先的格式返回
//      val res: RDD[String] = filteredRDD.map(_._2._1)
//      res
//    })


//    val res=user.transform(x=>{
//     val res= x.leftOuterJoin(black).filter(x=>{
//        if(x._2._2.getOrElse(false))
//          false
//        else
//          true
//      }).map(_._2._1)
//
//      res
//    })


    user.print()
    ssc.start()

    ssc.awaitTermination()


  }

}
