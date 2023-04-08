package audio.streaming.consumer;

import audio.streaming.schema.ListenEvent;
import audio.streaming.schema.PageViewEvent;
import audio.streaming.schema.StatusChangeEvent;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Collections;

public class KafkaConsumerStatusChangeEvent extends KafkaConsumerBase {

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, PageViewEvent> kafkaConsumer;

    public KafkaConsumerStatusChangeEvent(String topic, String consumerGroupID) {

        super(topic, consumerGroupID, StatusChangeEvent.class.getName());
        super.kafkaConsumer = new KafkaConsumer<String, StatusChangeEvent>(super.kafkaProps);
        super.kafkaConsumer.subscribe(Collections.singleton(super.topic));

    }


    public static void main(String args[]) throws IOException {

        System.out.println("Enter point of main in the StatusChangeEventKafkaConsumer class!");

        KafkaConsumerStatusChangeEvent pageViewKafkaConsumer = new KafkaConsumerStatusChangeEvent("status_change_events", "status_change.consumer.v1");
        pageViewKafkaConsumer.pollAndWriteToBigQuery("raw", "status_change_events");

    }

}
