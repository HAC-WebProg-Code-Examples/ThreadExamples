package il.ac.hac.cs;

public class PreemptiveTest {

    public static void main(String[] args) {

        try {

            System.out.println("--- Starting PrintThread1 ---\n");

            PrintThread1 starlet1 = new PrintThread1("*");
            PrintThread1 dash1 = new PrintThread1("-");
            PrintThread1 underscore1 = new PrintThread1("_");

            starlet1.start();
            dash1.start();
            underscore1.start();

            // Let the PrintThread1 instances finish execution before executing PrintThread2:
            starlet1.join();
            dash1.join();
            underscore1.join();

            System.out.println("\n--- PrintThread1 is done, starting PrintThread2 ---\n");

            PrintThread2 starlet2 = new PrintThread2("*");
            PrintThread2 dash2 = new PrintThread2("-");
            PrintThread2 underscore2 = new PrintThread2("_");

            starlet2.start();
            dash2.start();
            underscore2.start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class PrintThread1 extends Thread {
    String name;

    public PrintThread1(String name) {
        this.name = name;
    }

    public void run() {
        printName();
    }

    private void printName() {
        for (int i = 1; i < 31; i++) {
            try {
                sleep((long) (Math.random() * 100));
                // or yield()
            } catch (InterruptedException ie) {
            }
            System.out.print(name);
        }
    }
}

class PrintThread2 extends Thread {
    String name;

    public PrintThread2(String name) {
        this.name = name;
    }

    public void run() {
        printName();
    }

    private void printName() {
        for (int i = 1; i < 31; i++) {
            System.out.print(name);
        }
    }
}
