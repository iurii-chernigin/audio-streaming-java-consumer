package audio.streaming;

import audio.streaming.schema.ListenEvent;
import audio.streaming.schema.PageViewEvent;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

public class PageViewEventKafkaConsumer extends KafkaEventConsumer {

    private KafkaConsumer<String, PageViewEvent> kafkaConsumer;

    public PageViewEventKafkaConsumer(String topic, String consumerGroupID) {
        super(topic, consumerGroupID, PageViewEvent.class.getName());

        this.kafkaConsumer = new KafkaConsumer<String, PageViewEvent>(super.kafkaProps);
        this.kafkaConsumer.subscribe(Collections.singleton(super.topic));

    }

    public void pollAndWriteToBigQuery(String datasetName, String tableName) throws IOException {

        Integer loopCounter = 0;
        while (true) {

            ConsumerRecords<String, PageViewEvent> records = this.kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));

            System.out.println("Poll iteration: " + Integer.toString(++loopCounter));
            System.out.println(String.format("Read records (%s) from topic %s", records.count(), super.topic));

            for(ConsumerRecord<String, PageViewEvent> record: records) {
                System.out.println(String.format("\nOffset %s read", record.offset()));
                System.out.println(
                        String.format("Record values: sessionId=\"%s\", userId=\"%s\" ...",
                                record.value().sessionId,
                                record.value().userId
                        )
                );
                //BigQueryWriter.insertRecord(datasetName, tableName, record.value().getEventMap());
            }

            kafkaConsumer.commitSync();
        }
    }

}
