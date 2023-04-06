package audio.streaming;

import audio.streaming.schema.Event;
import audio.streaming.schema.ListenEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Properties;
import java.util.Map;

public class KafkaEventConsumer {

    protected final String topic; // = "listen_events"
    protected final String consumerGroupID; // = "listen.consumer.v1"
    protected Properties kafkaProps;


    public KafkaEventConsumer (String topic, String consumerGroupID) {

        this.topic = topic;
        this.consumerGroupID = consumerGroupID;

        // Init related to the passed parameters objects
        this.kafkaProps = KafkaProps.getConsumerProps(consumerGroupID);

    }


}
