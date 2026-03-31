package charset;

import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");

        // StandardCharsets.US_ASCII => import static java.nio.charset.StandardCharsets.*;

                                        // A 를 US-ASCII 로 인코딩 하면, 65 가 나오며, 1바이트를 사용한다.
        encoding("A", US_ASCII);    // A -> [US-ASCII] 인코딩 -> [65] 1byte
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE);    // A -> [UTF-16BE] 인코딩 -> [0, 65] 2byte
        /*
            java.nio.charset.StandardCharsets 에 UTF-16 상수가 [UTF_16, UTF_16BE, UTF_16LE]로 여러 개 존재하는 이유는
            바이트 순서(Endianness)와 BOM(Byte Order Mark) 처리 방식의 차이 때문.

            UTF-16 은 2 바이트(16비트) 단위로 문자를 표현하는데,
            데이터를 바이트 배열로 저장하거나 네트워크로 전송할 때
            => 바이트의 순서를 어떻게 할지
                (ex) UTF_16BE : [-84, 0]
                     UTF_16LE : [0, -84]
                     ※ UTF_16 : 인코딩한 문자가 BE, LE 중에 어떤 것인지 알려주는 2byte 가 앞에 추가로 붙는다.
            => 시작 부분에 BOM 을 넣을지 말지에 따라 종류가 나뉜다.

            ※ BOM(Byte Order Mark) :
                텍스트 파일의 맨 앞에 숨겨진 특수 문자(U+FEFF)를 추가하여,
                프로그램이 => 해당 파일의 유니코드 인코딩 방식(UTF-8, UTF-16 등)과 바이트 순서(엔디언)를 올바르게 인식하도록 돕는 메타데이터.

            우리는 UTF_16BE 를 사용하면 된다. BE, LE는 byte 의 순서의 차이다.
            이제 UTF_16 을 잘 사용하지 않고, UTF-8 은 이런 이슈가 없으므로 참고만 하고 넘어가자.
         */

        //      2 byte 단위로 끊어서 읽기 때문에( 저장할 때도 [0, 65] 2개를 저장하기 때문에 ), 다른 인코딩과 호환이 안된다.

        System.out.println("== 한글 지원 ==");
        encoding("가", EUC_KR);      // 가 -> [EUC-KR] 인코딩 -> [-80, -95] 2byte
        encoding("가", MS_949);      // 가 -> [x-windows-949] 인코딩 -> [-80, -95] 2byte
        encoding("가", UTF_8);       // 가 -> [UTF-8] 인코딩 -> [-22, -80, -128] 3byte (2byte 인코딩과 호환이 안된다.)
        encoding("가", UTF_16BE);    // 가 -> [UTF-16BE] 인코딩 -> [-84, 0] 2byte

        String str = "A";
        byte[] bytes = str.getBytes();  // 캐릭터셋을 지정해주지 않으면 => 시스템 default 캐릭터셋 (UTF-8, PC 환경에 따라 다를 수 있다.)을 지정한다.
        System.out.println("(default charset) bytes = " + Arrays.toString(bytes));
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
