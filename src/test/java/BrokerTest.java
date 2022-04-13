import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrokerTest {

    /**
     * This would have to test the topicMap HashMap and make sure it was building properly
     * (built a topic, build another, try one with the same name, try one with no name, what does topicMap look like throughout this)
     */
    @Test
    void createTopic() {
    }

    /**
     * Pretty simple code but lots of ways to test
     * I would run through sending messages in a number of ways
     */
    @Test
    void sendMessage() {
    }

    /**
     * This one is pretty simple as well. Considering it doesn't need to keep track of that index, we are just checking
     * to make sure it actually gets the right message from the topic
     */
    @Test
    void receiveMessage() {
    }
}