package IOStream.ThreadIOStream.Bai2;

import java.io.*;
import java.util.concurrent.*;

public class MultiThreadedKeywordSearch {
    public static void main(String[] args) throws Exception {
        String keyword = "example";
        File directory = new File("MyFolder");
        if (!directory.exists()) {
            directory.mkdir();
        }


        File[] files = createAndWriteFiles(directory);

        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        Future<Integer>[] results = new Future[files.length];

        for (int i = 0; i < files.length; i++) {
            results[i] = executor.submit(new KeywordSearchTask(files[i], keyword));
        }

        int totalOccurrences = 0;
        for (Future<Integer> result : results) {
            totalOccurrences += result.get();
        }

        executor.shutdown();
        System.out.println("Total occurrences of keyword: " + totalOccurrences);
    }

    private static File[] createAndWriteFiles(File directory) throws IOException {
        File[] files = new File[3];
        String[] fileNames = {"KeywordSearch1.txt", "KeywordSearch2.txt", "KeywordSearch3.txt"};

        for (int i = 0; i < fileNames.length; i++) {
            files[i] = new File(directory, fileNames[i]);
            if (!files[i].exists()) {
                files[i].createNewFile();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(files[i]))) {
                writer.write("This is an example text\n");
                writer.write("Another example line\n");
                writer.write("Text without the keyword");
            }
        }
        return files;
    }
}