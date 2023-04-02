package audio.streaming;

import audio.streaming.schema.ListenEvent;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Properties;

public class ListenEventsConsumer {

    private static final String topic = "listen_events";
    private static final String consumerGroupID = "listen.consumer.v1";
    private KafkaConsumer<String, ListenEvent> kafkaConsumer;
    private Properties kafkaProps;

    public ListenEventsConsumer () {

        kafkaProps = KafkaProps.getConsumerProps(consumerGroupID);
        kafkaConsumer = new KafkaConsumer<String, ListenEvent>(kafkaProps);
        kafkaConsumer.subscribe(Collections.singleton(topic));
    }

    public void consumeFromKafka() {

        Integer loopCounter = 0;
        while (true) {

            System.out.println("Poll iteration: " + Integer.toString(++loopCounter));
            ConsumerRecords<String, ListenEvent> records = kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));


            for(ConsumerRecord<String, ListenEvent> record: records) {
                System.out.println("");
                System.out.println(record.key());
                System.out.println(record.value());
            }
            kafkaConsumer.commitSync();
            System.out.println("Commited sync...");
        }
    }

}
