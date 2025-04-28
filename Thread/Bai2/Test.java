package BaiTap.Bai2;

public class Test {
    public static void main(String[] args) {
        Deadlock print = new Deadlock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                print.thread1();
            }
        });

        Thread t2 = new Thread(()-> print.thread2());
        t1.start();
        t2.start();
    }
}
