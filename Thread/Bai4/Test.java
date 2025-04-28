package BaiTap.Bai4;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new ThreadInterruption());
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();

    }
}
