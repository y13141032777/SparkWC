package utils

import com.alibaba.druid.pool.DruidDataSource

object JdbcPool {




        //	设计连接属性
        def getconn()={
                val ds = new DruidDataSource
                ds.setDriverClassName("com.mysql.jdbc.Driver")
                ds.setUrl("jdbc:mysql://hadoop01:3306/ods")
                ds.setUsername("hive")
                ds.setPassword("hive")

                val  conn = ds.getConnection

                conn
        }



}
