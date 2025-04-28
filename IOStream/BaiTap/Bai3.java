package IOStream.BaiTap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Bai3 {
    public static void main(String[] args) {
        try{
            File directory = new File("MyFolder");
            if(!directory.exists()){
                directory.mkdir();
            }

            File file = new File(directory,"fileBai3.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line;
                int count=0;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                    count++;
                }

                System.out.println("number line in file is : "+ count);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
