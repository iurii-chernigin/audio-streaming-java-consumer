package audio.streaming;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;

// Insert data from Kafka to a BigQuery table
public class BigQueryWriter {

    private static final String projectId = "audio-streaming-data-platform";
    private static final String credentialPath = "/Users/y.chernigin/.gcp/audio-streaming-data-platform-32efe7c70104.json";
    private static String datasetName;
    private static String tableName;
    private static Map<String, Object> record = new HashMap<>();

    public static void insertRecord(String datasetName, String tableName, Map<String, Object> record) throws IOException {

        try {

            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(credentialPath));
            BigQuery bigquery = BigQueryOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(credentials)
                    .build()
                    .getService();

            // Get table
            TableId tableId = TableId.of(projectId, datasetName, tableName);

            // Insert record into datasetName:tableId.
            InsertAllResponse repsponse = bigquery.insertAll(
                    InsertAllRequest.newBuilder(tableId)
                            .addRow(record)
                            .build()
            );

            if (repsponse.hasErrors()) {
                for(Map.Entry<Long, List<BigQueryError>> entry: repsponse.getInsertErrors().entrySet()) {
                    System.out.println("Response error \n" + entry.getValue());
                }
            }
            System.out.println("Rows successfully inserted into table " + tableName);

        } catch (BigQueryException e) {
            System.out.println("Insert operation didn't perform: \n" + e.toString());
        }

    }

}
