package BaiTap.Bai1;

public class Counter {
     private  int count =0;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void increment(){
        count++;
    }

}
