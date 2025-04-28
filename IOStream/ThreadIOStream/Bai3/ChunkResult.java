package IOStream.ThreadIOStream.Bai3;

public class ChunkResult {
    private final int chunkIndex;
    private final byte[] data;

    public ChunkResult(int chunkIndex, byte[] data) {
        this.chunkIndex = chunkIndex;
        this.data = data;
    }

    public int getChunkIndex() {
        return chunkIndex;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ChunkResult{" +
            "chunkIndex=" + chunkIndex +
            ", dataSize=" + data.length +
            " bytes}";
    }
}
