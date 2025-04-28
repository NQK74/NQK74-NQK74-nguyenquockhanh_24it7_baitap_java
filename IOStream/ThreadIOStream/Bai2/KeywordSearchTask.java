package IOStream.ThreadIOStream.Bai2;

import java.io.*;
import java.util.concurrent.Callable;

public class KeywordSearchTask implements Callable<Integer> {
    private final File file;
    private final String keyword;

    public KeywordSearchTask(File file, String keyword) {
        this.file = file;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = -1;
                while ((index = line.indexOf(keyword, index + 1)) != -1) {
                    count++;
                }
            }
        }
        System.out.println("Found " + count + " occurrences in " + file.getName());
        return count;
    }
}