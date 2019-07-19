package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class MyProuucer {

    Properties prop= new Properties();

    KafkaProducer<String,String> p = new KafkaProducer(prop);
}
