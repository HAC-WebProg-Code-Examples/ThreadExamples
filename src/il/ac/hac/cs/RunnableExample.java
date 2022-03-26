package il.ac.hac.cs;

public class RunnableExample {

    public static void main(String[] args) {

        class MyRunnable implements Runnable {
            @Override
            public void run() {
                System.out.println("I am a runnable and I run on a thread named: " + Thread.currentThread().getName());
            }
        }

        try {


            // A runnable cannot be executed as a different thread by itself:
            new MyRunnable().run(); // I am a runnable, and I run on a thread named: main

            // But it can be used to dynamically "change" the definition of a Thread's run() method:
            new Thread(new MyRunnable()).start(); // I am a runnable and I run on a thread named: Thread-0

            Thread.sleep(10); // Why sleep? (comment this line to see the difference, then explain it to yourself!)

            // Just to make things clearer, a separate thread is only created when we call a Thread's start() method:
            new Thread(new MyRunnable()).run(); // I am a runnable, and I run on a thread named: main

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
