public class MemoryTester implements Runnable{

    Integer counter = 0;

    @Override
    public void run() {
        System.out.println("before: " + counter);
        counter++;
        System.out.println("after: " + counter);
    }

    public void printer() {
        System.out.println(counter);
    }
}
