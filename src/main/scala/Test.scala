import utils.JdbcPool

object Test {
  def main (args: Array[String]): Unit = {
   val conn = JdbcPool.getconn()
    val s ="CREATE TABLE IF NOT EXISTS jdbc_test(sname VARCHAR(5),sage int)"
    val statement = conn.createStatement()
    statement.executeUpdate(s)
    val sql="INSERT INTO jdbc_test VALUES(?,?)"
    val ps=conn.prepareStatement(sql)
    for(i <-1 to 10){
      ps.setString(1,"an"+i)
      ps.setInt(2,18+i)
      ps.executeUpdate()
    }


    conn.close()
  }

}
