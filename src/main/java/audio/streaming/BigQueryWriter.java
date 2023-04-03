package audio.streaming;

import com.google.cloud.bigquery.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Insert data from Kafka to a BigQuery table
public class BigQueryWriter {

    private static String datasetName;
    private static String tableName;
    private static Map<String, Object> record = new HashMap<>();

    public static void insertRecord(String datasetName, String tableName, Map<String, Object> record) {

        try {
            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

            // Get table
            TableId tableId = TableId.of(datasetName, tableName);

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
