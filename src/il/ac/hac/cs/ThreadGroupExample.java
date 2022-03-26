package il.ac.hac.cs;

public class ThreadGroupExample {

    public static final Runnable SLEEPY_RUNNABLE = () -> {
        try {
            // Sleeping for 1 second, unless interrupted
            System.out.printf("My name is %s and I am going to sleep%n", Thread.currentThread().getName());
            Thread.sleep(1000);

            // Not interrupted
            System.out.printf("My name is %s and I was not interrupted%n", Thread.currentThread().getName());

        } catch (InterruptedException ignored) {

            System.out.printf("My name is %s and my priority is %d%n",
                    Thread.currentThread().getName(), Thread.currentThread().getPriority());

        }
    };

    public static void main(String[] args) {

        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), "group of prioritised threads");

        for (int i = Thread.MIN_PRIORITY; i < Thread.MAX_PRIORITY; ++i) {
            Thread t = new Thread(threadGroup, SLEEPY_RUNNABLE);
            t.setPriority(i);
            t.start();
        }

        try {

            Thread.sleep(10);

            System.out.println("\n------------- Threads Wake Up -------------\n");

            // Interrupt all threads in the group:
            threadGroup.interrupt();

        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted");
        }
    }
}
