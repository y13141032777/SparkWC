package test;

import scala.collection.Seq;

import java.io.*;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties prop1 = new Properties();
//        prop1.setProperty("name","zhangsan");
//        prop1.setProperty("name1","zhangsan1");
//        prop1.store(new BufferedOutputStream(new FileOutputStream("prop.properties")),"test777777");
        prop1.load(new BufferedInputStream(new FileInputStream("prop.properties")));
        System.out.println(prop1.getProperty("user"));


    }
}
