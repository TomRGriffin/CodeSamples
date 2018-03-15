package Samples;

public class Threads {
    public static class Data {
        public int count = 0;
        public Boolean canSub = false;
        public void addByTwo() {
            count += 2;
        }

        public void subByOne() {
            count--;
        }
    }

    public static void testThreadSync() {
        Data data = new Data();
        final Object lock = new Object();
        Thread thread1 = new Thread(){
            public void run(){
                synchronized (lock) {
                    if (!data.canSub) {
                        for (int i = 0; i < 100; i++) {
                            data.addByTwo();
                            data.canSub = true;
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                synchronized (lock) {
                    if (data.canSub) {
                        for (int i = 0; i < 100; i++) {
                            data.subByOne();
                            System.out.println("Count = " + data.count);
                            data.canSub = false;
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }
            }
        };
        thread1.start();
        thread2.start();

    }
}
