package audio.streaming;

import audio.streaming.schema.Event;

import java.util.Properties;

public class KafkaEventConsumer {

    protected final String topic; // = "listen_events"
    protected final String consumerGroupID; // = "listen.consumer.v1"
    protected Properties kafkaProps;
    protected String jsonDeserializerClass;


    public KafkaEventConsumer(String topic, String consumerGroupID, String jsonDeserializerClass) {

        this.topic = topic;
        this.consumerGroupID = consumerGroupID;
        this.jsonDeserializerClass = jsonDeserializerClass;

        // Init related to the passed parameters objects
        this.kafkaProps = KafkaProps.getConsumerProps(consumerGroupID, jsonDeserializerClass);

    }


}
