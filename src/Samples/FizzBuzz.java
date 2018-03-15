package Samples;

public class FizzBuzz {
    // For given number, loop from zero to number if number is divisible by 3 print "Fizz"
    // or if number is divisible by 5 print "Buzz". Print "FizzBuzz" is divisible by 5 & 3

    public static void fizzBuzz(int number) {
        for (int i = 0; i < number; i++) {
            if (i % 15 == 0) { // May be % 15
                System.out.println("FizzBuzz: " + i);
            } else if (i % 3 == 0) { // May be % 15
                System.out.println("Fizz: " + i);
            } else if (i % 5 == 0) { // May be % 15
                System.out.println("Buzz: " + i);
            }
        }
    }

    public static void fizzBuzzThread(int number) {
        FizzBuzzThread[] threads = new FizzBuzzThread[] {
                new FizzBuzzThread(true, true, number, "FizzBuzz"),
                new FizzBuzzThread(true, false, number, "Fizz"),
                new FizzBuzzThread(false, true, number, "Buzz"),
                new NumberThread(false, false, number,null)
        };
        for (FizzBuzzThread thread: threads) {
            thread.start();
        }
    }

    public static class FizzBuzzThread extends Thread {
        private static final Object lock = new Object();
        boolean div3;
        boolean div5;
        int max;
        public static int current = 1;
        String toPrint;

        FizzBuzzThread(boolean div3, boolean div5, int max, String toPrint) {
            this.div3 = div3;
            this.div5 = div5;
            this.max = max;
            this.toPrint = toPrint;
        }

        public void print(String toPrint) {
            System.out.println(toPrint);
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (current > max) {
                        return;
                    }

                    if ((current % 3 == 0) == div3 && ((current % 5 == 0) == div5)) {
                        print(toPrint);
                        current++;
                    }
                }
            }
        }
    }

    public static class NumberThread extends FizzBuzzThread {
        NumberThread(boolean div3, boolean div5, int max, String toPrint) {
            super(div3, div5, max, toPrint);
        }

        @Override
        public void print(String toPrint) {
            System.out.println(current);
        }
    }

}
