package il.ac.hac.cs;

public class MainThreadExample {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        System.out.println(threadName);

    }
}
