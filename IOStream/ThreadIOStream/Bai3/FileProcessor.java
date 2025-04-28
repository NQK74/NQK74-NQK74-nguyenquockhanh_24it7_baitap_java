package IOStream.ThreadIOStream.Bai3;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileProcessor {
    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    private final String inputFile;
    private final String outputFile;

    public FileProcessor(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void process() throws IOException, InterruptedException, ExecutionException {
        Path inputPath = Paths.get(inputFile);
        long fileSize = Files.size(inputPath);
        System.out.println("Kích thước file: " + fileSize + " bytes");

        int numChunks = (int) Math.ceil((double) fileSize / CHUNK_SIZE);
        System.out.println("Chia thành " + numChunks + " đoạn");

        ExecutorService executor = Executors.newFixedThreadPool(Math.min(numChunks, MAX_THREADS));
        List<Future<ChunkResult>> futures = new ArrayList<>();

        for (int i = 0; i < numChunks; i++) {
            long startPosition = (long) i * CHUNK_SIZE;
            long endPosition = Math.min(startPosition + CHUNK_SIZE, fileSize);

            FileReaderTask task = new FileReaderTask(inputFile, i, startPosition, endPosition);
            futures.add(executor.submit(task));
        }

        executor.shutdown();

        ChunkResult[] results = new ChunkResult[numChunks];

        for (Future<ChunkResult> future : futures) {
            ChunkResult result = future.get();
            results[result.getChunkIndex()] = result;
        }

        writeResults(results);
    }

    private void writeResults(ChunkResult[] results) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            for (ChunkResult result : results) {
                bos.write(result.getData());
            }
        }

        System.out.println("Đã ghép và ghi nội dung vào file " + outputFile);
    }
}
