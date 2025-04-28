package BaiTap.Bai3;

public class Test {
    public static void main(String[] args) {
        Starvation print = new Starvation();

        Thread highPriority = new Thread(()->{
            print.highPriority();
        });

        Thread lowPriority = new Thread(()->{
            print.lowPriority();
        });

        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);
        highPriority.start();
        lowPriority.start();

        try{
            Thread.sleep(1000);
            print.stop();
            highPriority.join();
            lowPriority.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
