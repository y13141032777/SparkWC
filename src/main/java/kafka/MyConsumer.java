package kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.Properties;

public class MyConsumer {

    public static void main(String[] args) {
        Properties prop= new Properties();
        prop.put("zookeeper.connect","hadoop01:2181,hadoop02:2181");
        prop.put("group.id","g1");
        prop.put("auto.offset.reset","smallest");

//    KafkaConsumer<String,String> p = new KafkaConsumer(prop);

        ConsumerConfig conf=new ConsumerConfig(prop);
        ConsumerConnector conn= Consumer.createJavaConsumerConnector(conf);

    }


}
