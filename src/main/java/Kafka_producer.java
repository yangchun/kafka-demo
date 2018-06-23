/**
 * Created by yangchun on 2017/5/4.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.PartitionInfo;

public class Kafka_producer {
    public static void main(String[] ads){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        for(int i = 0; i < 20; i++)
            producer.send(new ProducerRecord<String, String>("test", Integer.valueOf(2),Integer.toString(i), Integer.toString(i)));
        producer.send(new ProducerRecord<String, String>("test","12","this is a test msg"));

        //列出topic的相关信息
        List<PartitionInfo> partitions = producer.partitionsFor("test");
        for(PartitionInfo p:partitions)
        {
            System.out.println(p);
        }

        System.out.println("send message over.");

        producer.close();

    }
}
