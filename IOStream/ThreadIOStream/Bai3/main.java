package IOStream.ThreadIOStream.Bai3;

public class main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Sử dụng: java Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            long startTime = System.currentTimeMillis();

            FileProcessor processor = new FileProcessor(inputFile, outputFile);
            processor.process();

            long endTime = System.currentTimeMillis();
            System.out.println("Hoàn thành trong " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
