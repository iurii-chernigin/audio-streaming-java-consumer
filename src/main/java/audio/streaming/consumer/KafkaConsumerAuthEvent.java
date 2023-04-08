package audio.streaming.consumer;

import audio.streaming.schema.AuthEvent;
import audio.streaming.schema.PageViewEvent;

import java.io.IOException;
import java.util.Collections;

public class KafkaConsumerAuthEvent extends KafkaConsumerBaseEvent {

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, PageViewEvent> kafkaConsumer;

    public KafkaConsumerAuthEvent(String topic, String consumerGroupID) {

        super(topic, consumerGroupID, AuthEvent.class.getName());
        super.kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, AuthEvent>(super.kafkaProps);
        super.kafkaConsumer.subscribe(Collections.singleton(super.topic));
        
    }


    public static void main(String args[]) throws IOException {

        System.out.println("Enter point of main in the ListenEventKafkaConsumer class!");

        KafkaConsumerAuthEvent pageViewKafkaConsumer = new KafkaConsumerAuthEvent("auth_events", "auth.consumer.v1");
        pageViewKafkaConsumer.pollAndWriteToBigQuery("raw", "auth_events");

    }

}
