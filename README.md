# Java Consumer for Audio Streaming Data Platfrom

This application is an essential component of the data platform for an audio streaming service. Before running it, be sure to check out the architecture by following this link: https://github.com/iurii-chernigin/audio-streaming-data-platform

### Prerequisites

- [ ] Create a project in Google Cloud 
- [ ] Create a service account, grant a Big Query Data Owner role, and generate json key
- [ ] [Set up a connection to BigQuery](https://github.com/iurii-chernigin/audio-streaming-java-consumer/blob/main/src/main/java/audio/streaming/BigQueryWriter.java)
- [ ] [Enable BigQuery API](https://console.cloud.google.com/flows/enableapi?apiid=bigquery&_ga=2.241857153.2099755602.1683145202-1316396118.1673887289&_gac=1.50205780.1683056672.Cj0KCQjw6cKiBhD5ARIsAKXUdyayoB5QK9gojokZQPZLWF1WxTuF8ygbpLrQAG7Qq5bN2X1IzsZbD2QaAtDwEALw_wcB)
- [ ] [Set up a connection to Kafka](https://github.com/iurii-chernigin/audio-streaming-java-consumer/blob/main/src/main/java/audio/streaming/consumer/KafkaProps.java)
- [ ] Create tables in BigQuery according to the schema: [audio-streaming-data-platform/data-warehouse/bigquery/schema](https://github.com/iurii-chernigin/audio-streaming-data-platform/tree/main/data-warehouse/bigquery/schema)

###  Plans To-Do

- Implement support for the Avro date format
- Implement support for the Kafka Schema Registry
- Change the data writing mechanism in BigQuery: add support of microbatch writing
- Create package to run application in Docker
