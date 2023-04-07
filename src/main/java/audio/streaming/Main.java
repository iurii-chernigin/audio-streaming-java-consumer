package audio.streaming;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Enter point of Main!");

        PageViewEventKafkaConsumer pageViewKafkaConsumer = new PageViewEventKafkaConsumer("page_view_events", "page_view.consumer.v1");
        pageViewKafkaConsumer.pollAndWriteToBigQuery("raw", "page_view_events");
    }
}