package audio.streaming;

import audio.streaming.schema.Event;
import io.confluent.kafka.serializers.KafkaJsonDeserializerConfig;
import io.confluent.kafka.serializers.KafkaJsonDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class KafkaProps {

    private static Properties props = new Properties();
    private static final String bootstrapServer = "localhost:9092";
    private static final String schemaRegistryEndpoint = "http://localhost:8081";
    private static final String offsetResetConfig = "earliest";
    private static final String autoCommitConfig = "true";

    public static Properties getConsumerProps(String groupID, String jsonDeserializerClass) {

        // Basic Configs
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetResetConfig);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommitConfig);

        // Deserialization Propeties
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaJsonDeserializer.class.getName());
        props.put(KafkaJsonDeserializerConfig.JSON_VALUE_TYPE, jsonDeserializerClass);

        return props;
    }
}
