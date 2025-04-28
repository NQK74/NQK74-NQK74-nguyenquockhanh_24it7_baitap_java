package IOStream.ThreadIOStream.Bai1;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        List<String> inputFileNames= new ArrayList<>();
        inputFileNames.add("ThreadInput1.txt");
        inputFileNames.add("ThreadInput2.txt");

        FilePath file = new FilePath();

        List<Thread> threads = new ArrayList<>();

        for (String fileName : inputFileNames) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    file.readFile(fileName);
                }
            });
            threads.add(t);
        }

        for (Thread t : threads) {
            t.start();
        }

        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        file.writeToOutputFile();

    }

}
