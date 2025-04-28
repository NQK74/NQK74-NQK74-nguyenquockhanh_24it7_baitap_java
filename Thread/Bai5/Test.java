package BaiTap.Bai5;

public class Test {
    public static void main(String[] args) {
        VisibilityExample v1 = new VisibilityExample();
        Thread t1 = new Thread(v1);
        t1.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        v1.stop();
        System.out.println("Main thread changing flag");
    }
}
