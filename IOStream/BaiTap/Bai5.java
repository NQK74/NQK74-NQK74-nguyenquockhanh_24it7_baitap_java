package IOStream.BaiTap;

import java.io.File;

public class Bai5 {
    public static void main(String[] args) {
//      //  Đường dẫn
//        String directoryPath = "D:/JavaTITV";
//      // Tạo đối tượng File với đường dẫn thư mục
//        File directory = new File(directoryPath);
       File directory = new File("MyFolder");

        if(directory.exists() && directory.isDirectory()){
            File[] files = directory.listFiles();
            if(files.length >0 && files != null){
                System.out.println("Dang sach file trong thu muc " + directory.getName()+ " la : ");
                for(File file : files){
                    if(file.isFile()){
                        System.out.println("File:  "+file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println("Directory: "+file.getName());

                    }

                }
            }else{
                System.out.println("Thu muc nay khong chu file  hoac directory con nao");
            }


        }else{
            System.out.println("Thư mục không tồn tại hoặc không phải là một thư mục.");
        }
    }
}
