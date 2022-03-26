package il.ac.hac.cs;

public class PreemptiveTest {
    public static void main(String[] args) {

        new Thread(() -> {
           for (int i = 0; i < 5; ++i) {
               for (int j = 0; j < 50; ++j) {
                   System.out.print("-");
               }
               System.out.println();
           }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 50; ++j) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }).start();

        // If your runtime is not preemptive you should see 5 lines of 50 '-' and then 5 lines of 50 '*'.
        // If your output is mixed then your runtime is preemptive.
    }
}
