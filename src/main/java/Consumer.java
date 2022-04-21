import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The consumer object is very simple.
 * TODO: Enable multithreading.
 */
public class Consumer implements Runnable {

    Broker broker;
    String name;

    // This is keep track of where THIS consumer is consuming different topics.
    HashMap<String, Integer> topicIndexes;

    public Consumer(Broker broker, String name) {
        this.broker = broker;
        this.topicIndexes = new HashMap<>();
        this.name = name;
    }

    /**
     * The Consumer keeps track of which topics it is consuming
     * When reading from a stream, we must update the HashMap that keeps track of that information
     * This way, it can read from any steam, without losing it's place in another
     *
     * @param topicName, the topic which we are about to read from
     */
    public String receiveMessage(String topicName) {
        String record;
        if (this.topicIndexes.containsKey(topicName)) {
            record = this.broker.receiveMessage(topicName, topicIndexes.get(topicName));
            this.topicIndexes.put(topicName, topicIndexes.get(topicName) + 1);
        } else {
            record = this.broker.receiveMessage(topicName, 0);
            this.topicIndexes.put(topicName, 1);
        }
        return record;
    }

    @Override
    public void run() {
        HashSet<String> completionStatus = new HashSet<>();
        while (completionStatus.size() != topicIndexes.size()) {

            for (Map.Entry<String, Integer> entry : this.topicIndexes.entrySet()) {

                String topicName = entry.getKey();
                Integer currentIndex = entry.getValue();

                if (completionStatus.contains(topicName)) {
                    continue;
                }

                String record;
                record = this.broker.receiveMessage(topicName, topicIndexes.get(topicName));
                if (record != null) {
                    this.topicIndexes.put(topicName, currentIndex + 1);
                    System.out.printf("consumer %s, topic: %s, value: %s%n", this.name, topicName, record);
                } else {
                    System.out.printf("No more records to consume from %s%n", topicName);
                    completionStatus.add(topicName);
                }
            }
        }
    }

    public void addTopicToConsume(String topicName) {
        if (this.topicIndexes.containsKey(topicName)) {
            System.out.println("We're already consuming that one");
        } else {
            this.topicIndexes.put(topicName, 0);
        }

    }
}
