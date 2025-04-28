package IOStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayIOApp {
    public static void main(String[] args) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        String s = "This us a test.";
        for (int i = 0; i < s.length(); i++){
            outStream.write(s.charAt(i));
        }

        System.out.println("out Stream : "+outStream);
        System.out.println("Size : "+ outStream.size());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outStream.toByteArray());
        int inBytes = inputStream.available();

        System.out.println("in Stream: "+ inBytes + " available bytes");

        byte inBuf[] = new byte[inBytes];
        int bytesRead = inputStream.read(inBuf, 0 , inBytes);

        System.out.println(bytesRead + " bytes were read");
        System.out.println("They are : " + new String(inBuf));
    }
}
