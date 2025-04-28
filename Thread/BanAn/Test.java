package BaiTap.BanAn;

public class Test {
    public static void main(String[] args) {
        DauBep dauBep = new DauBep();

        Thread thread1 = new Thread(new KhachHang(dauBep));
        Thread thread2 = new Thread(dauBep);

        thread2.start();
        thread1.start();
    }
}
