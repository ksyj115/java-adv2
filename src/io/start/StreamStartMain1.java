package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain1 {

    public static void main(String[] args) throws IOException {
        // Output : java 기준으로 밖으로 출력하여 => temp 폴더의 hello.dat 파일로 입력한다.
        // 처음 작성 시 체크 예외처리 하라는 마크 뜨는데, 예외를 밖으로 throws 해주자.
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");  // hello.dat 파일이 없으면 생성하지만 temp 폴더는 못만든다.(미리 생성해줘야 함.)
        fos.write(65);  // fos.write 안에 원하는 Byte 를 넣으면 된다.
        fos.write(66);
        fos.write(67);
        fos.close();        // 외부에 있는 "temp/hello.dat" 자원을 쓰고 있느 개념이기 때문에, 자원을 다 쓰고 나면 꼭 close 해주어야 한다.
                            // 내부에 있는 객체는 자동으로 Garbage Collector 가 치워주지만, 외부 자원은 사용 후 반드시 닫아주어야 한다.

        //                                                             append 옵션 : 실행될 때 기존 문서 끝에 이어서 저장할지 여부. ABCABC 로 보이게 된다.
        // FileOutputStream fos = new FileOutputStream("temp/hello.dat", true);


        // 여기까지 소스를 실행해보면 => hello.dat 에 ABC 로 저장된 것을 확인할 수 있다.
        // 이유는? => 실제 파일에는 65,66,67 이라는 3개의 byte 로 저장된다.
        //           하지만 (인텔리제이에서 제공하는)텍스트 문서가 읽어질 때는
        //           저장된 시스템 기본 charset( 내 경우 : UTF-8 => 하지만 호환되는 본질은 ASCII )로 디코딩하여 보여지는 것이다.

        // Input : 생성된 파일을 불러들이기
        FileInputStream fis = new FileInputStream("temp/hello.dat");
        // read() 라고 하면 읽어 들인다는 뜻이다.
        System.out.println(fis.read());     // 65
        System.out.println(fis.read());     // 66
        System.out.println(fis.read());     // 67
        System.out.println(fis.read());     // -1 : 파일의 끝에 도달해서 더 읽을 것이 없다는 뜻.
        fis.close();    // 외부 자원을 가져다 쓰는 것이므로, 끝나면 꼭 닫아줘야 한다.
    }
}
