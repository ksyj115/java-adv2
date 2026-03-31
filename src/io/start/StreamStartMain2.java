package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");

        int data;
        while ((data = fis.read()) != -1) {
            System.out.println(data);
        }
        fis.close();
        /*
            ※ int result = fis.read();

            파일에 저장할 때는 byte 로 저장했는데
            읽을 때는 왜 int 로 반환하지?

            =>
            java 에서 byte 는 부호가 있는 8 bit 값(-128 ~ 127)이다.
            byte 는 -128 에서 127 까지 256 종류의 값을 가질 수 있지만, EOF(End of File) 표시 -1 을 위한 특별한 값을 할당하기 어렵다.

            int 의 경우,
            int 로 반환함으로써 0 에서 255 까지의 모든 가능한 byte 값을 부호 없이 표현할 수 있고
            여기에 추가로 -1 을 반환하여 스트림의 끝 EOF 도 나타낼 수 있다.

            참고로 int 도 음수 값을 가질 수 있지만 (0~255)값을 반환하는 이유는,
            음수 값을 갖게 되면 문서의 끝 EOF 를 표현하는 -1 값을 따로 예약할 수 없기 때문이다.

            write() 의 경우도 비슷한 이유로 int 타입을 입력 받는다.
            따라서 fos.write() 안에 들어갈 수 있는 값은 0~255 이다.
        */
    }
}
