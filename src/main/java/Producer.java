/**
 * The Producer is implementing Runnable. I generally like to implement runnable, so we can still extend later if desired
 */
public class Producer implements Runnable {

    Broker broker;
    Integer name;

    public Producer(Broker broker, Integer name) {
        this.broker = broker;
        this.name = name;
    }

    /**
     * Simple call to broker
     *
     * @param topicName, topic to be written to
     * @param message, message to written in above topic
     */
    public void sendMessage(String topicName, String message) {
        this.broker.sendMessage(topicName, message);
    }

    /**
     * This is the implemented run function which is called with the thread is started
     * TODO: we are hard coding the topic in the function, we need to make the configurable
     */
    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            this.sendMessage("topic"+this.name.toString(), Integer.toString(i));
        }


    }

}
