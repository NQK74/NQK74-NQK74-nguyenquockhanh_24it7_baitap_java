package BaiTap.SCSL;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        SoChanSoLe printer = new SoChanSoLe();
        Thread even = new Thread(() -> printer.Even());
        Thread odd = new Thread(new Runnable() {
            @Override
            public void run() {
                printer.Odd();
            }
        });
        odd.start();

        even.start();
    }
}
