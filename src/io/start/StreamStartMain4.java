package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readBytes = fis.readAllBytes();  // 스트림이 끝날 때까지 (파일의 끝에 도달할 때까지) 모든 데이터를 한 번에 읽어올 수 있다.
        System.out.println(Arrays.toString(readBytes));
        fis.close();
    }
}
