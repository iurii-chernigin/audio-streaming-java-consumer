package audio.streaming.consumer;

import audio.streaming.schema.ListenEvent;
import audio.streaming.schema.PageViewEvent;

import java.io.IOException;
import java.util.Collections;

public class KafkaConsumerStatusChangeEvent extends KafkaConsumer {

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, PageViewEvent> kafkaConsumer;

    public KafkaConsumerStatusChangeEvent(String topic, String consumerGroupID) {

        super(topic, consumerGroupID, PageViewEvent.class.getName());
        super.kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, ListenEvent>(super.kafkaProps);
        super.kafkaConsumer.subscribe(Collections.singleton(super.topic));

    }


    public static void main(String args[]) throws IOException {

        System.out.println("Enter point of main in the ListenEventKafkaConsumer class!");

        KafkaConsumerStatusChangeEvent pageViewKafkaConsumer = new KafkaConsumerStatusChangeEvent("listen_events", "listen.consumer.v1");
        pageViewKafkaConsumer.pollAndWriteToBigQuery("raw", "listen_events");

    }

}
