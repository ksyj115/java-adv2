package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

public class CreateFileV3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        // BufferedOutputStream : 버퍼 기능을 내부에서 대신 처리해준다.
        // BufferedOutputStream 선언 시 인자로 fos 와 버퍼사이즈를 넣어주면, 이 2개 인자는 이후로 작성할 일 없이 bos 만 사용하면 된다.
        // BufferedOutputStream 은 fos 가 (출력 => 입력)을 하는데 버퍼 기능을 지원해줄뿐 직접 (출력 => 입력)을 하지는 않는다. 따라서 인자로 fos 와 같은 기본 스트림을 꼭 넣어줘야 한다.
        // ※ 2번째 인자 BUFFER_SIZE 는 안넣을 경우 => 기본으로 4KB 또는 8KB 로 적용된다. (java 버전마다 달라짐.)
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }

        // BufferedOutputStream 은 close() 를 호출하면, (내부에서) flush() 를 한 번 호출한다.
        // 그리고 연결되어 있는(인자로 받은 fos 의 close() 도 호출한다.
        bos.close();

        // [!] bos.close() 을 안하고, fos.close() 를 호출하면,
        //     정상적으로 실행은 되나, bos.flush() 가 호출되지 않아 => 버퍼 bos 남아 있는 byte가 마저 출력되지 않고 남아있게 되고 파일이 온전히 입력되지 않는 문제가 발생한다.
        //     그리고 버퍼 bos 자체도 자원 정리가 되지 않고 남아 있게된다.
        //     따라서 마지막에 연결한 스트림(BufferedOutputStream)만 닫아주면 => flush() 호출로 남은 데이터 출력 및 저장 후 연쇄적으로 close() 가 호출되어 안전하게 자원을 정리할 수 있다.

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
