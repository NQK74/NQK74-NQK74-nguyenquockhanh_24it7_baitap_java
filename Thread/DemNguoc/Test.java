package BaiTap.DemNguoc;

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(new Bai2());

        thread.start();
    }
}
