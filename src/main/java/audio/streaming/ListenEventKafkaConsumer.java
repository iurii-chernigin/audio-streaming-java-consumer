package audio.streaming;

import audio.streaming.schema.Event;
import audio.streaming.schema.ListenEvent;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;


import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

public class ListenKafkaEventConsumer extends KafkaEventConsumer {


    private KafkaConsumer<String, ListenEvent> kafkaConsumer;

    public ListenKafkaEventConsumer(String topic, String consumerGroupID) {
        super(topic, consumerGroupID);

        this.kafkaConsumer = new KafkaConsumer<String, ListenEvent>(super.kafkaProps);
        this.kafkaConsumer.subscribe(Collections.singleton(super.topic));

    }


    public void pollAndWriteToBigQuery(String datasetName, String tableName) throws IOException {

        Integer loopCounter = 0;
        while (true) {

            ConsumerRecords<String, ListenEvent> records = kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));

            System.out.println("Poll iteration: " + Integer.toString(++loopCounter));
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
