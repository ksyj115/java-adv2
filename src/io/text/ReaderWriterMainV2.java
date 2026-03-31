package io.text;

import java.io.*;

import static io.text.TextConst.*;
import static java.nio.charset.StandardCharsets.*;

public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {
        String writeString = "abc";
        System.out.println("write String: " + writeString);

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        //          문자를 쓸때는 "Writer" 라고 쓴다. => byte[] writeBytes = writeString.getBytes(UTF_8); 작업을 대신 해준다.
        OutputStreamWriter osw = new OutputStreamWriter(fos, UTF_8);

        osw.write(writeString);
        osw.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        //          문자를 읽을때는 "Reader" 라고 쓴다.
        InputStreamReader isr = new InputStreamReader(fis, UTF_8);

        StringBuilder content = new StringBuilder();
        int ch;
        // isr.read() 는 사실 char 타입을 반환하므로 한 번에 2byte 씩 반환해준다.
        // 그런데 int ch 로 받는 이유는 => java 의 char 형은 파일의 끝인 -1을 표현할 수 없으므로 대신 int 로 받는 것이다.
        while ((ch = isr.read()) != -1) {
//            content.append(ch);       // 656667 이 출력된다.
            content.append((char) ch);  // ABC 가 출력된다. (char 타입으로 캐스팅)
        }
        isr.close();

        System.out.println("read String: " + content);
    }
}
