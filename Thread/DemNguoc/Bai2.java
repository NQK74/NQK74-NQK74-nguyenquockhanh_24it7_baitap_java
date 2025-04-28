package BaiTap.DemNguoc;

public class Bai2 implements  Runnable{

    @Override
    public synchronized void run() {
        for (int i = 10; i>= 0; i--){
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            System.out.println("so : "+i);
        }
    }
}
