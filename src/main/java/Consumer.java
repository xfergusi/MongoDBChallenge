import java.util.HashMap;

/**
 * The consumer object is very simple.
 * TODO: Enable multithreading.
 */
public class Consumer {

    Broker broker;

    // This is keep track of where THIS consumer is consuming different topics.
    HashMap<String, Integer> topicIndexes;

    public Consumer(Broker broker) {
        this.broker = broker;
        this.topicIndexes = new HashMap<>();

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
        if(this.topicIndexes.containsKey(topicName)) {
            record = this.broker.receiveMessage(topicName, topicIndexes.get(topicName));
            this.topicIndexes.put(topicName, topicIndexes.get(topicName)+1);
        }

        else {
            record = this.broker.receiveMessage(topicName, 0);
            this.topicIndexes.put(topicName, 1);
        }
        return record;
    }

}
