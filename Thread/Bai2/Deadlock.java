package BaiTap.Bai2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock implements Runnable {
    private static final Lock resource1 = new ReentrantLock();
    private static final Lock resource2 = new ReentrantLock();

    public void thread1() {
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        try {
            // Try to acquire the first lock
            gotFirstLock = resource1.tryLock(50, TimeUnit.MILLISECONDS);
            if (gotFirstLock) {
                System.out.println("Thread1: Locked resource 1");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                gotSecondLock = resource2.tryLock(50, TimeUnit.MILLISECONDS);
                if (gotSecondLock) {
                    System.out.println("Thread1: Locked resource 2");

                    System.out.println("Thread1: Working with both resources");
                } else {
                    System.out.println("Thread1: Could not lock resource 2, giving up");
                }
            } else {
                System.out.println("Thread1: Could not lock resource 1, giving up");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            if (gotSecondLock) {
                resource2.unlock();
                System.out.println("Thread1: Released resource 2");
            }
            if (gotFirstLock) {
                resource1.unlock();
                System.out.println("Thread1: Released resource 1");
            }
        }
    }

    public void thread2() {
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;

        try {

            gotFirstLock = resource2.tryLock(50, TimeUnit.MILLISECONDS);
            if (gotFirstLock) {
                System.out.println("Thread2: Locked resource 2");


                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                gotSecondLock = resource1.tryLock(50, TimeUnit.MILLISECONDS);
                if (gotSecondLock) {
                    System.out.println("Thread2: Locked resource 1");

                    System.out.println("Thread2: Working with both resources");
                } else {
                    System.out.println("Thread2: Could not lock resource 1, giving up");
                }
            } else {
                System.out.println("Thread2: Could not lock resource 2, giving up");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            if (gotSecondLock) {
                resource1.unlock();
                System.out.println("Thread2: Released resource 1");
            }
            if (gotFirstLock) {
                resource2.unlock();
                System.out.println("Thread2: Released resource 2");
            }
        }
    }

    @Override
    public void run() {
        // Empty implementation since we're using anonymous classes
    }
}