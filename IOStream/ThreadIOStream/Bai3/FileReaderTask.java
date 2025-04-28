package IOStream.ThreadIOStream.Bai3;

import java.io.RandomAccessFile;
import java.util.concurrent.Callable;

public class FileReaderTask implements Callable<ChunkResult> {
    private final String filePath;
    private final int chunkIndex;
    private final long startPosition;
    private final long endPosition;

    public FileReaderTask(String filePath, int chunkIndex, long startPosition, long endPosition) {
        this.filePath = filePath;
        this.chunkIndex = chunkIndex;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public ChunkResult call() throws Exception {
        int size = (int) (endPosition - startPosition);
        byte[] buffer = new byte[size];

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            file.seek(startPosition);
            file.readFully(buffer);
            System.out.println("Đã đọc đoạn " + chunkIndex + " (từ byte " + startPosition + " đến " + endPosition + ")");
            return new ChunkResult(chunkIndex, buffer);
        }
    }
}
