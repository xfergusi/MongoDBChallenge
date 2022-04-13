import java.util.ArrayList;

/**
 * A topic contains a name, which is unique, and a partition,
 */
public class Topic {

    // This name is unused, but I will keep it. I think it should be used at some point
    private final String name;
    private final ArrayList<String> partition = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    /**
     * Given an index, we are checking the log at that location. The consumer is responsible for knowing where to look and pass it to the topic
     * @param index where in the log it wants to look
     * @return the value at that log
     */
    public String consumeOneRecord(Integer index) {

        String returnString = null;
        if(index < partition.size()) {
            returnString = this.partition.get(index);
        }
        else {
            System.out.println("nothing new to consume");
        }

        return returnString;
    }


    public void addARecord(String record) {
        this.partition.add(record);
    }

}
