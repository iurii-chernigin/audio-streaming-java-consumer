package audio.streaming.consumer;

import audio.streaming.BigQueryWriter;
import audio.streaming.schema.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

public abstract class KafkaConsumerBase {

    protected final String topic; // = "listen_events"
    protected final String consumerGroupID; // = "listen.consumer.v1"
    protected Properties kafkaProps;
    protected String jsonDeserializerClass;
    protected KafkaConsumer<String, ? extends Event> kafkaConsumer;


    public KafkaConsumerBase(String topic, String consumerGroupID, String jsonDeserializerClass) {

        this.topic = topic;
        this.consumerGroupID = consumerGroupID;
        this.jsonDeserializerClass = jsonDeserializerClass;

        // Init related to the passed parameters objects
        this.kafkaProps = KafkaProps.getConsumerProps(consumerGroupID, jsonDeserializerClass);

    }

    public void pollAndWriteToBigQuery(String datasetName, String tableName) throws IOException {

        Integer loopCounter = 0;
        while (true) {

            ConsumerRecords<String, ? extends Event> records = this.kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));

            System.out.println("Poll iteration: " + Integer.toString(++loopCounter));
            System.out.println(String.format("Read records (%s) from topic %s", records.count(), this.topic));

            for (ConsumerRecord<String, ? extends Event> record : records) {
                System.out.println(String.format("\nOffset %s read", record.offset()));
                System.out.println(
                        String.format("Record values: sessionId=\"%s\", userId=\"%s\", timestamp=\"%s\", registration=\"%s\" ...",
                                record.value().sessionId,
                                record.value().userId,
                                record.value().ts,
                                record.value().registration
                        )
                );
                BigQueryWriter.insertRecord(datasetName, tableName, record.value().getEventMap());
            }

            kafkaConsumer.commitSync();
        }
    }


}
