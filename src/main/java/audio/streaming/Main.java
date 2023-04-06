package audio.streaming;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Enter point of Main!");

        ListenEventsConsumer listenEventsConsumer = new ListenEventsConsumer();
        listenEventsConsumer.consumeFromKafka();
    }
}