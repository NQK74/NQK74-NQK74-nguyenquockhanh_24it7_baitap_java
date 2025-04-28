package BaiTap.Bai5;

public class VisibilityExample implements Runnable {
    private static volatile boolean flag = false;

    @Override
    public void run() {
        while (!flag) {
            System.out.println("Flag changed!");
        }
        System.out.println("Thoat vong lap");
    }

    public void stop() {
        System.out.println("Setting flag to true");
        flag = true;
    }

}
