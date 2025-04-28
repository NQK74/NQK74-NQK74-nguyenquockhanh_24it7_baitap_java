package IOStream;

import java.io.*;

public class Read {
    public static void main(String[] args) {
        try {
            // tao thu muc
            File directory = new File("MyFolder");
            if(!directory.exists()){
                directory.mkdir();
            }

            // tao file
            File file = new File(directory,"file.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            try (BufferedWriter bw = new BufferedWriter( new FileWriter(file))){
                bw.write("Xin chao minh la nguyen quoc khanh");
            }
            // doc file
            try (BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
