package day1

import java.sql.DriverManager

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.JdbcRDD

object test1 {
  def main(args: Array[String]): Unit = {
    val url="jdbc:mysql://localhost:3306/mydb12"
    val user="root"
    val pass= "123456"

    val conn=()=>{
      Class.forName("com.mysql.jdbc.Driver").newInstance()
      DriverManager.getConnection(url,user,pass)
    }

    val conf=new SparkConf().setAppName("jdbc").setMaster("local")
    val sc = new SparkContext(conf)
    val sql= "select * from t_stu1 where id>? and id<?"

    val jrdd= new JdbcRDD(sc,conn,sql,0,100,1,
      res=>{
        val name=res.getString("sname")
        val id =res.getInt("id")
        val score=res.getInt("score")
        (name,id,score)
      })

    println("===========")

  }
}

