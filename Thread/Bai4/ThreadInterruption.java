package BaiTap.Bai4;

public class ThreadInterruption implements Runnable {

    @Override
    public synchronized void run(){
        while(!Thread.currentThread().isInterrupted()){
            try{
                System.out.println("Worker is running...");
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Worker thread interrupted!");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Worker stopped");
    }
}
