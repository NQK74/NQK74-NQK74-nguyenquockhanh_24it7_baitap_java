package BaiTap.BanAn;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class DauBep implements Runnable{
    private ArrayList<Integer> banAn;
    private final int size =5;
    private boolean isSanXuat = true;

    public DauBep() {
        this.banAn = new ArrayList<>();
    }

    public synchronized void NauAn(int item){
        try {
            while (banAn.size() == size) {
                System.out.println("Ban day , doi khach an");
                wait();
            }
            banAn.add(item);
            System.out.println("Nau xong mon : "+item);
            System.out.println("Cac mon tren ban an : "+banAn);
            notify();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized int consumer(){
        try{
            while (banAn.isEmpty()){
                if(!isSanXuat){
                    return 0;
                }
                System.out.println("het do an roi, cho nau");
                wait();
            }
            int item = banAn.remove(0);
            System.out.println("An xong mon "+item);
            System.out.println("Cac mon con lai"+banAn);
            notify();
            return  item;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }


    }

    @Override
    public void run() {
        try{
            for (int i =0; i < 5;i++){
                NauAn(i);
                Thread.sleep(2000);
            }
            isSanXuat = false;
            synchronized (this){
                notifyAll();
            }

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();

        }
    }
}
