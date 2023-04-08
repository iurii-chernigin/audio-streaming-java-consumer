package audio.streaming.consumer;

import audio.streaming.schema.ListenEvent;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Collections;

public class KafkaConsumerListenEvent extends KafkaConsumerBase {

    public KafkaConsumerListenEvent(String topic, String consumerGroupID) {

        super(topic, consumerGroupID, ListenEvent.class.getName());
        super.kafkaConsumer = new KafkaConsumer<String, ListenEvent>(super.kafkaProps);
        super.kafkaConsumer.subscribe(Collections.singleton(super.topic));

    }


    public static void main(String args[]) throws IOException {

        System.out.println("Enter point of main in the ListenEventKafkaConsumer class!");

        KafkaConsumerListenEvent listenEventKafkaConsumer = new KafkaConsumerListenEvent("listen_events", "listen.consumer.v1");
        listenEventKafkaConsumer.pollAndWriteToBigQuery("raw", "listen_events");

    }

}
