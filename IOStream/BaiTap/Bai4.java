package IOStream.BaiTap;

import java.io.*;

public class Bai4 {
    public static void main(String[] args) {
        try {
            File directory = new File("MyFolder");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File inputFile = new File(directory, "input4.txt");
            if (!inputFile.exists()) {
                inputFile.createNewFile();
            }

            File outputFile = new File(directory, "output4.txt");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            // Ghi các số nguyên dưới dạng văn bản vào input4.txt
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
                bw.write("1234\n");
                bw.write("5678\n");
                bw.write("9101112\n");
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Đọc các số nguyên từ input4.txt và ghi chúng vào output4.txt bằng DataOutputStream
            try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
                 DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    dos.writeInt(number);
                }
            }

            // Đọc các số nguyên từ output4.txt bằng DataInputStream
            try (DataInputStream dis = new DataInputStream(new FileInputStream(outputFile))) {
                while (dis.available() > 0) {
                    int number = dis.readInt();
                    System.out.println(number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}