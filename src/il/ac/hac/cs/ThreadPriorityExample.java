package il.ac.hac.cs;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPriorityExample {
    public static void main(String[] args) {

        // First we grab a hold of the monitor to prevent our threads from running until we finish starting them all
        synchronized (PrioritisedThread.monitor) {

            // Let's create one thread for each priority:
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY; ++i) {
                threads.add(new PrioritisedThread(i));
            }

            // Now let's start them all:
            threads.forEach(Thread::start);

            // Now that all of our threads are started and waiting, we wake them up and release the monitor,
            // to see which one will be chosen by the scheduler to run first:
            PrioritisedThread.monitor.notifyAll();
        }
    }
}

class PrioritisedThread extends Thread {

    static final Object monitor = new Object();

    private static final AtomicBoolean firstSleeperWokeAlready = new AtomicBoolean(false);

    PrioritisedThread(int priority) {
        setPriority(priority);
    }

    @Override
    public synchronized void start() {
        System.out.printf("My name is %s and I am started now%n", getName());
        super.start();
    }

    @Override
    public void run() {

        // Each thread waits on the monitor in order to let all other threads start:
        synchronized (monitor) {

            // If I am the first to wake up I will wake up all others and add a nice separator to output:
            if (!firstSleeperWokeAlready.get()) {
                firstSleeperWokeAlready.set(true);
                System.out.println("\n------------- Threads Wake Up -------------\n");
            }

            System.out.printf("My name is %s with priority %d and I just got hold of the monitor!%n",
                    getName(), getPriority());
        }
    }
}
