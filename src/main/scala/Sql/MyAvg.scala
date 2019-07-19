package Sql


import org.apache.spark.sql.{Encoder, Encoders, Row}
import org.apache.spark.sql.expressions.{Aggregator, MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

case class People(name:String,age:Int)

case class Avg(var sum:Int,var count:Int)

class MyAvg extends Aggregator[People,Avg,Double]{

  //初始化中间结果
  override def zero: Avg = Avg(0,0)
  //分区内聚合，把每一条数据聚合到中间结果对象
  override def reduce(b: Avg, a: People): Avg = {
    b.sum = b.sum + a.age
    b.count = b.count + 1
    b
  }

  //分区结果汇总
  override def merge(b1: Avg, b2: Avg): Avg = {
    b1.sum = b1.sum + b2.sum
    b1.count = b1.count+b2.count
    b1
  }

  override def finish(reduction: Avg): Double = reduction.sum/reduction.count.toDouble

  override def bufferEncoder: Encoder[Avg] = Encoders.product

  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

class MyAvg1 extends UserDefinedAggregateFunction{

  override def inputSchema: StructType = new StructType().add("age",IntegerType)
//    new StructType().add("age",IntegerType)
  override def bufferSchema: StructType = new StructType().add("sum",IntegerType).add("count",IntegerType)

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0
    buffer(1)=0
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if (!input.isNullAt(0)) {
      buffer(0) = buffer.getInt(0) + input.getInt(0)
      buffer(1) = buffer.getInt(1) + 1
    }
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1(0)= buffer1.getInt(0)+buffer2.getInt(0)
    buffer1(1)=buffer1.getInt(1)+buffer2.getInt(1)
  }

  override def evaluate(buffer: Row): Any = buffer.getInt(0)/buffer.getInt(1).toDouble

}