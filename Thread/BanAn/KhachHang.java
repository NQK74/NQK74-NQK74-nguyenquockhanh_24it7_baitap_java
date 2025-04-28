package BaiTap.BanAn;

public class KhachHang implements Runnable {
    private DauBep nauAn;
    private final int maxItem =5;

    public KhachHang(DauBep nauAn) {
        this.nauAn = nauAn;
    }


    @Override
    public void run() {
        try {
            for(int i =0; i< maxItem; i++){
                int item = nauAn.consumer();
                if(item == -1) {
                    break;
                }
                Thread.sleep(3000);
            }

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
