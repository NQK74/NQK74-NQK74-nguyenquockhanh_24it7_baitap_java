package IOStream.ThreadIOStream.Bai1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePath extends  Thread {
    private java.io.File directory;
    private java.io.File outputFile;
    private List<String> contentList = new ArrayList<>();

    public FilePath() {
        directory = new java.io.File("MyFolder");
        if (!directory.exists()) {
            directory.mkdir();
        }

        outputFile = new java.io.File(directory, "ThreadOutput.txt");
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readFile(String fileName) {
        try{
            java.io.File input = new java.io.File(directory,fileName);
            if(!input.exists()){
                System.out.println("File khong ton tai " + fileName);
                input.createNewFile();
                return;
            }

            StringBuilder content = new StringBuilder();

            content.append("Noi dung file : "+fileName+"\n");

            try(BufferedReader br = new BufferedReader(new FileReader(input))){
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                    System.out.println(line);
                }
            }
            contentList.add(content.toString());
            System.out.println("Da doc file: "+fileName+"\n\n");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void writeToOutputFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String content : contentList) {
                writer.write(content);
                writer.write("\n");
            }
            System.out.println("Đã ghi nội dung vào file: " + outputFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
