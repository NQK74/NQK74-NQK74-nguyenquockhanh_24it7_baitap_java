package BaiTap.Bai1;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(()->
        {
            for (int i = 0; i < 1000; i++){
                counter.increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< 1000; i++){
                    counter.increment();
                }
            }
        }
        );

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println("Final count : "+counter.getCount());
    }
}
