package audio.streaming.consumer;

import audio.streaming.schema.PageViewEvent;

import java.io.IOException;
import java.util.Collections;

public class KafkaConsumerPageViewEvent extends KafkaConsumer {

    public KafkaConsumerPageViewEvent(String topic, String consumerGroupID) {
        super(topic, consumerGroupID, PageViewEvent.class.getName());
        super.kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, PageViewEvent>(super.kafkaProps);
        super.kafkaConsumer.subscribe(Collections.singleton(super.topic));
    }

    public static void main(String args[]) throws IOException {

        System.out.println("Enter point of main in the PageViewEventKafkaConsumer class!");

        KafkaConsumerPageViewEvent pageViewKafkaConsumer = new KafkaConsumerPageViewEvent("page_view_events", "page_view.consumer.v1");
        pageViewKafkaConsumer.pollAndWriteToBigQuery("raw", "page_view_events");

    }

}
