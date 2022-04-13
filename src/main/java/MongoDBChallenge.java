public class MongoDBChallenge {

    public static void main(String[] args) {

        // First, we create the broker.
        // This broker will be used by all further consumers and producers
        Broker broker = new Broker();

        // Here we are spinning up 3 producers, which are hard coded to write to 3 different topics
        // Design assumption: I made the producers only write to one topic. This can be enhanced so they can write to multiple
        for(int i = 0; i < 3; i++){
            Producer producer = new Producer(broker, i);
            Thread thread = new Thread(producer);
            thread.start();
        }

        // Next, we create a few consumers, which can read from any topic
        // Design assumption: These consumers are not multithreaded
        Consumer consumer1 = new Consumer(broker);
        Consumer consumer2 = new Consumer(broker);

        // Let's print out consumer1 reading all the way through topic 0 thus far
        // The last call will show there is nothing new to read
        System.out.println(consumer1.receiveMessage("topic0"));
        System.out.println(consumer1.receiveMessage("topic0"));
        System.out.println(consumer1.receiveMessage("topic0"));
        System.out.println(consumer1.receiveMessage("topic0"));
        System.out.println(consumer1.receiveMessage("topic0"));
        System.out.println(consumer1.receiveMessage("topic0")); // Notice that it returns null when there is nothing new to consume. This will have to change if null is an expected return type

        // Now let's have consumer1 read from topic 1
        // Notice that customer1 has no problem reading from either topic
        System.out.println(consumer1.receiveMessage("topic1"));
        System.out.println(consumer1.receiveMessage("topic1"));
        System.out.println(consumer1.receiveMessage("topic1"));
        System.out.println(consumer1.receiveMessage("topic1"));
        System.out.println(consumer1.receiveMessage("topic1"));

        // Now we get consumer2 involved. When it reads the topics, it starts back at the beginning
        // This demonstrates that each consumer is responsible for keep track of where it should be reading from teh topic
        System.out.println(consumer2.receiveMessage("topic0"));
        System.out.println(consumer2.receiveMessage("topic1"));
        System.out.println(consumer2.receiveMessage("topic2"));

    }

}
