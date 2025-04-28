package IOStream.BaiTap;

import java.io.*;

public class Bai2 {
    public static void main(String[] args) {
        try {
            File directory = new File("MyFolder");
            if(!directory.exists()){
                directory.mkdir();
            }

            File file = new File(directory,"fileBai2.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            System.out.println("Nhap du lieu de luu vao file (go exit de ket thuc)");
            try (BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter( new FileWriter(file))
            ){
                String line;
                while (!(line = key.readLine()).equalsIgnoreCase("exit")){
                    bw.write(line);
                    bw.newLine();
                }

            }

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
