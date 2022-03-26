package il.ac.hac.cs;

import java.util.Arrays;

public class RaceConditionExample {

    public static void main(String[] args) {
        boolean stop = false;
        final Unsafe unsafe = new Unsafe();

        while (!stop) {
            try {
                stop = startRace(unsafe);
                System.out.println(unsafe);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean startRace(Unsafe unsafe) throws InterruptedException {

        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(), "racers");

        new Thread(threadGroup, () -> {
            for (int i = 0; i < 1000000; ++i) {
                unsafe.setX(0);
            }
        }).start();

        new Thread(threadGroup, () -> {
            for (int i = 0; i < 1000000; ++i) {
                unsafe.setX(2);
            }
        }).start();

        new Thread(threadGroup, () -> {
            for (int i = 0; i < 1000000; ++i) {
                unsafe.setX(4);
            }
        }).start();

        new Thread(threadGroup, () -> {
            for (int i = 0; i < 1000000; ++i) {
                unsafe.setX(8);
            }
        }).start();

        threadGroup.interrupt();

        Thread.sleep(10);

        return !unsafe.validate();
    }
}

class Unsafe {

    private final int[] x = new int[10];

    public boolean validate() {
        return Arrays.stream(x).allMatch(val -> val == x[0]);
    }

    public void setX(int val) {
        Arrays.fill(x, val);
    }

    @Override
    public String toString() {
        return "Unsafe{" +
                "x=" + Arrays.toString(x) +
                '}';
    }
}