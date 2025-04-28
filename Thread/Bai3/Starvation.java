package BaiTap.Bai3;

public class Starvation implements  Runnable{
    private volatile boolean running = true;
//    public void highPriority(){
//        while(running){
//            System.out.println("High priority thread running...");
//
//        }
//    }
//
//    public void lowPriority(){
//        while(running){
//            System.out.println("Low priority thread running..");
//            Thread.yield();
//        }
//    }
    public void highPriority() {
        int count = 0;
        while (running && count < 1000) {
            System.out.println("High priority thread running...");
            count++;

        }
        System.out.println("High priority thread completed: " + count + " iterations");
    }

    public void lowPriority() {
        int count = 0;
        while (running && count < 1000) {
            System.out.println("Low priority thread running...");
            count++;
            Thread.yield();
        }
        System.out.println("Low priority thread completed: " + count + " iterations");
    }


    public void stop(){
        running = false;
    }
    @Override
    public void run() {
    }
}
