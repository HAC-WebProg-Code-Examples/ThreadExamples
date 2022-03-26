package il.ac.hac.cs;

public class StackExample {

    public static void main(String[] args) {

        class MyThread extends Thread {

            @Override
            public synchronized void start() {
                System.out.println(getName() + " starts");
                super.start();
            }

            @Override
            public void run() {
                int x = (int) (Math.random() * 10) + 1;
                Thread.yield();
                System.out.println(x);
            }
        }

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();

        t1.start();
        t2.start();
        t3.start();
    }
}
