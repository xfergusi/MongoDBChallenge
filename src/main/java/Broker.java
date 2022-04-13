import java.util.HashMap;

/**
 * The Broker is responsible for interacting with topics for both the consumers and producers
 * The Broker is simple, and the only thing that can interact with topics
 * TODO: Right now the consumer is keeping track of where it last looked, it might smart for the broker to keep track of this info as well
 * TODO: Broker is making the topic, that functionality might belong somewhere else (maybe a separate object that we don't have yet)
 */
public class Broker {

    // This is how I'm storing topics. I'm not happy about it but it will work for now. In the future, I would utilize the "name" attribute within the Topic
    HashMap<String, Topic> topicMap = new HashMap<>();

    /**
     * Sets up three topics that consumers/producers can interact with
     */
    public Broker() {
        this.createTopic("topic0");
        this.createTopic("topic1");
        this.createTopic("topic2");
    }

    /**
     * creates a topic that producers/consumers can interact with
     * Assumption / design notes
     *  I assume the broker will not be given a top name that already exists
     *
     *
     * @param topicName, name of top, unique
     */
    public void createTopic(String topicName) {
        System.out.println("new topic made : " + topicName);
        Topic topic = new Topic(topicName);
        topicMap.put(topicName, topic);
    }

    /**
     * Sends a single message to a particular topic
     *
     * @param topicName, id
     * @param message, any string written in a log style
     */
    public void sendMessage(String topicName, String message) {
        topicMap.get(topicName).addARecord(message);
    }

    /**
     * Consumes one message from a given topic
     *
     * @param topicName, any string, id
     */
    public String receiveMessage(String topicName, Integer index) {
        return topicMap.get(topicName).consumeOneRecord(index);
    }



}
