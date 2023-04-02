package audio.streaming;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class KafkaProps {

    private static Properties props = new Properties();
    private static final String bootstrapServer = "localhost:9092";
    private static final String schemaRegistryEndpoint = "http://localhost:8081";
    private static final String offsetResetConfig = "earliest";
    private static final String autoCommitConfig = "false";

    public static Properties getConsumerProps(String groupID) {

        // Basic Configs
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetResetConfig);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommitConfig);

        // Avro Deserialization Propeties
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryEndpoint);
        props.put("specific.avro.reader", "true");

        return props;
    }
}
