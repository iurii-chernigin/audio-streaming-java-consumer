package audio.streaming;

public class Main {
    public static void main(String[] args) {

        System.out.println("Enter point of Main!");

        ListenEventsConsumer listenEventsComsumer = new ListenEventsConsumer();
        listenEventsComsumer.consumeFromKafka();
    }
}