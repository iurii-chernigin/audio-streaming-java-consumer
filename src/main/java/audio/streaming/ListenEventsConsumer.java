package audio.streaming;

import audio.streaming.schema.ListenEvent;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;


import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Properties;

public class ListenEventsConsumer {

    private static final String topic = "listen_events";
    private static final String consumerGroupID = "listen.consumer.v1";
    private static final String datasetName = "raw";
    private static final String tableName = "listen_events";
    private KafkaConsumer<String, ListenEvent> kafkaConsumer;
    private Properties kafkaProps;

    public ListenEventsConsumer () {

        kafkaProps = KafkaProps.getConsumerProps(consumerGroupID);
        kafkaConsumer = new KafkaConsumer<String, ListenEvent>(kafkaProps);
        kafkaConsumer.subscribe(Collections.singleton(topic));
    }

    public void consumeFromKafka() throws IOException {

        Integer loopCounter = 0;
        while (true) {

            System.out.println("\nPoll iteration: " + Integer.toString(++loopCounter));
            ConsumerRecords<String, ListenEvent> records = kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));
            System.out.println(String.format("Read records (%s) from topic %s", records.count(), topic));

            for(ConsumerRecord<String, ListenEvent> record: records) {
                System.out.println(String.format("\nOffset %s read", record.offset()));
                System.out.println(
                        String.format("Record values: sessionId=\"%s\", userId=\"%s\", artist=\"%s\" ...",
                                record.value().sessionId,
                                record.value().userId,
                                record.value().artist
                        )
                );
                BigQueryWriter.insertRecord(datasetName, tableName, record.value().getRecordMap());
            }
            kafkaConsumer.commitSync();
            System.out.println("Commited sync...");
        }
    }

}
