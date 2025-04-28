package IOStream.BaiTap;

import java.io.*;

public class Bai1 {
    public static void main(String[] args) {
        try{
            File directory = new File("MyFolder");
            if(!directory.exists()){
                directory.mkdir();
            }
            File inputFile = new File(directory,"input.txt");
            if(!inputFile.exists()){
                inputFile.createNewFile();
            }

            File outputFile = new File(directory,"output.txt");
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }


            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] inBuf = new byte[1024];
            int length;

            while((length = fis.read(inBuf))> 0){
                fos.write(inBuf, 0,length);
            }

            System.out.println("paste file complete");

        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
