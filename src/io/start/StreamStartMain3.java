package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {

    public static void main(String[] args) throws IOException {
        // fos.write() 한줄씩 출력 => 파일 입력이 아닌, 한번에 입력.(배열 사용.)
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);    // int 를 넣을수 있고, byte[] 를 넣을 수도 있다.
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        // byte 배열을 선언해줘야 한다. (넉넉하게 10개 짜리 byte 배열을 선언해주자.)
        byte[] buffer = new byte[10];

        // ("내가 몇개를 읽었어"를 의미하는) readCount = fis.read( 읽은 값을 담을 대상, byte 배열에 몇번째 부터 읽을 것인지의 값, (한번에 읽을 때) 길이 )
        int readCount = fis.read(buffer, 0, 10);
        System.out.println("readCount = " + readCount);    // readCount = 3 ( buffer 에 10개를 한번에 담을 수 있지만, 읽을 수 있는 값이 3개밖에 없기 때문 )
        System.out.println(Arrays.toString(buffer));       // [65, 66, 67, 0, 0, 0, 0, 0, 0, 0]
        fis.close();

        // int readCount = fis.read(buffer, 1, 10); 로 실행시 => 인덱스 1부터 10개를 읽으려고 했는데 buffer 변수 최대 길이가 10 이므로 => IndexOutOfBoundsException 발생.
        // int readCount = fis.read(buffer, 1, 9); 로 실행시 => 정상 실행됨.

    }
}
