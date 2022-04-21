import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {

    Consumer consumer;
    @BeforeEach
    void setUp() {
        Broker broker = new Broker();
        this.consumer = new Consumer(broker);
        Producer producer = new Producer(broker, 0);
        Thread thread = new Thread(producer);
        thread.start();

    }

    @Test
    void generalConsumer() {
        // first lets test the happy path
        assertEquals(this.consumer.receiveMessage("topic0"), "0");
        assertEquals(this.consumer.receiveMessage("topic0"), "1");
        assertEquals(this.consumer.receiveMessage("topic0"), "2");
        assertEquals(this.consumer.receiveMessage("topic0"), "3");
        assertEquals(this.consumer.receiveMessage("topic0"), "4");
        assertNull(this.consumer.receiveMessage("topic0"));
    }
    /**
     * To implement this test, I would need to build a few helper functions
     * These help functions would give me access to the data structure receive messages is working with
     */
    @Test
    void receiveMessage() {

    }
}