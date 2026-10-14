import java.util.Random;

class NumberGenerator extends Thread {
    public void run() {
        Random r = new Random();

        while (true) {
            int n = r.nextInt(100);
            System.out.println("Generated Number: " + n);

            if (n % 2 == 0) {
                new SquareThread(n).start();
            } else {
                new CubeThread(n).start();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class SquareThread extends Thread {
    int n;

    SquareThread(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("Square of " + n + " = " + (n * n));
    }
}

class CubeThread extends Thread {
    int n;

    CubeThread(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("Cube of " + n + " = " + (n * n * n));
    }
}

public class MultiThreadedNumber {
    public static void main(String[] args) {
        NumberGenerator t = new NumberGenerator();
        t.start();
    }
}
